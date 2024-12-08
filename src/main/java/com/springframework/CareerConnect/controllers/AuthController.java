package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.domain.Role;
import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.enums.ERole;
import com.springframework.CareerConnect.payload.request.LoginRequest;
import com.springframework.CareerConnect.payload.request.SignupRequest;
import com.springframework.CareerConnect.payload.response.JwtResponse;
import com.springframework.CareerConnect.payload.response.MessageResponse;
import com.springframework.CareerConnect.repositories.RoleRepository;
import com.springframework.CareerConnect.repositories.UserRepository;
import com.springframework.CareerConnect.security.JwtUtils;
import com.springframework.CareerConnect.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser (@Valid @RequestBody LoginRequest loginRequest) {
        try {
            logger.info("Authentication attempt for username: {}",
                    obfuscateUsername(loginRequest.getUsername()));

            return getResponseEntity(loginRequest);
        } catch (Exception e) {
            logger.error("Authentication process failed", e);
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Authentication failed: " + getSafeErrorMessage(e)));
        }
    }

    private ResponseEntity<?> getResponseEntity(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )

            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            logger.info("Authentication successful for user: {}", loginRequest.getUsername());
            Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
            user.get().setLastLoginDate(LocalDateTime.now());
            userRepository.save(user.get());

            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles
            ));


        } catch (BadCredentialsException e) {
            logger.warn("Failed authentication attempt for username: {}, Reason: Invalid Credentials",
                    obfuscateUsername(loginRequest.getUsername()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Error: Invalid username or password"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        try {
            logger.info("Signup Request Received - Username: {}, Email: {}",
                    signupRequest.getUsername(), signupRequest.getEmail());

            if (Boolean.TRUE.equals(userRepository.existsByUsername(signupRequest.getUsername()))) {
                logger.warn("Username already exists: {}", signupRequest.getUsername());
                return ResponseEntity.badRequest().body("Error: Username is already taken!");
            }
            if (Boolean.TRUE.equals(userRepository.existsByEmail(signupRequest.getEmail()))) {
                logger.warn("Email already exists: {}", signupRequest.getEmail());
                return ResponseEntity.badRequest().body("Error: Email is already in use!");
            }
            User user = new User(
                    signupRequest.getUsername(),
                    signupRequest.getEmail(),
                    passwordEncoder.encode(signupRequest.getPassword()
                    ));

            user.setCreatedDate(LocalDateTime.now());
            user.setStatus("ACTIVE");
            user.setUpdatedDate(LocalDateTime.now());

            Set<String> strRoles = signupRequest.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null || strRoles.isEmpty()) {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> {
                            logger.error("Default USER role not found in database");
                            return new RuntimeException("Error: Default role not found.");
                        });
                roles.add(userRole);
                logger.info("Assigned default USER role");

            } else {
                strRoles.forEach(roleName -> {
                    try {
                        switch (roleName.toLowerCase()) {
                            case "admin":
                                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                        .orElseThrow(() -> new RuntimeException("Admin role not found"));
                                roles.add(adminRole);
                                break;
                            case "company":
                                Role companyRole = roleRepository.findByName(ERole.ROLE_COMPANY)
                                        .orElseThrow(() -> new RuntimeException("Company role is not found."));
                                roles.add(companyRole);
                                break;
                            default:
                                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                        .orElseThrow(() -> new RuntimeException("User role is not found."));
                                roles.add(userRole);
                        }
                    } catch (Exception e) {
                        logger.error("Role assignment error for role: {}", roleName, e);
                    }
                });
            }
            user.setRoles(roles);
            userRepository.save(user);
            logger.info("User registered successfully: {}", user.getUsername());
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (Exception e) {
            logger.error("Signup error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Signup failed: " + e.getMessage()));
        }
    }

    private String obfuscateUsername(String username) {
        if (username == null || username.length() <= 2) {
            return "***";
        }
        return username.substring(0, 2) + "***" + username.substring(username.length() - 1);
    }

    private String getSafeErrorMessage(Exception e) {
        String message = e.getMessage();
        // Prevent leaking sensitive information
        return message != null && message.length() > 50
                ? "Authentication error occurred"
                : message;
    }


}
