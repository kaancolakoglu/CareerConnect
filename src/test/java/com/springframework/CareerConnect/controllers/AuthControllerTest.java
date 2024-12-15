package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.domain.Role;
import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.enums.ERole;
import com.springframework.CareerConnect.payload.request.CompanySignupRequest;
import com.springframework.CareerConnect.payload.request.LoginRequest;
import com.springframework.CareerConnect.payload.request.SignupRequest;
import com.springframework.CareerConnect.payload.response.JwtResponse;
import com.springframework.CareerConnect.payload.response.MessageResponse;
import com.springframework.CareerConnect.repositories.CompanyRepository;
import com.springframework.CareerConnect.repositories.RoleRepository;
import com.springframework.CareerConnect.repositories.UserRepository;
import com.springframework.CareerConnect.security.JwtUtils;
import com.springframework.CareerConnect.security.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @Test
    void testAuthenticateUser_Success() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        UserDetailsImpl userDetails = new UserDetailsImpl(
                1L, "testUser", "test@example.com", "encodedPassword",
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        User user = new User();
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("mockedJwtToken");
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JwtResponse jwtResponse = (JwtResponse) response.getBody();
        assertNotNull(jwtResponse);
        assertEquals("mockedJwtToken", jwtResponse.getAccessToken());
        assertEquals("testUser", jwtResponse.getUsername());
        assertEquals(List.of("ROLE_USER"), jwtResponse.getRoles());
    }

    @Test
    void testAuthenticateUser_InvalidCredentials() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("wrongPassword");
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        // Act
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        MessageResponse messageResponse = (MessageResponse) response.getBody();
        assertNotNull(messageResponse);
        assertEquals("Error: Invalid username or password", messageResponse.getMessage());
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("testUser");
        signupRequest.setPassword("password");
        signupRequest.setEmail("test@example.com");
        signupRequest.setRole(Set.of("USER"));

        when(userRepository.existsByUsername("testUser")).thenReturn(false);
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        Role userRole = new Role(ERole.ROLE_USER);
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(userRole));

        // Act
        ResponseEntity<?> response = authController.registerUser(signupRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        MessageResponse messageResponse = (MessageResponse) response.getBody();
        assertNotNull(messageResponse);
        assertEquals("User registered successfully!", messageResponse.getMessage());
    }

    @Test
    void testRegisterUser_UsernameAlreadyExists() {
        // Arrange
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("testUser");
        signupRequest.setPassword("password");
        signupRequest.setEmail("test@example.com");
        signupRequest.setRole(Set.of("USER"));

        when(userRepository.existsByUsername("testUser")).thenReturn(true);

        // Act
        ResponseEntity<?> response = authController.registerUser(signupRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Username is already taken!", response.getBody());
    }

    @Test
    void testRegisterCompany_Success() {
        // Arrange
        CompanySignupRequest signupRequest = new CompanySignupRequest();
        signupRequest.setUsername("testCompany");
        signupRequest.setCompanyName("testCompany");
        signupRequest.setCompanyRegistrationNumber("11");
        signupRequest.setSectorName("Technology");
        signupRequest.setCompanySize(600L);
        signupRequest.setRole(Set.of("COMPANY"));
        signupRequest.setPassword("password");
        signupRequest.setEmail("test@example.com");

        when(companyRepository.existsByUsername("testCompany")).thenReturn(false);
        when(companyRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        Role companyRole = new Role(ERole.ROLE_COMPANY);
        when(roleRepository.findByName(ERole.ROLE_COMPANY)).thenReturn(Optional.of(companyRole));

        // Act
        ResponseEntity<?> response = authController.registerCompany(signupRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        MessageResponse messageResponse = (MessageResponse) response.getBody();
        assertNotNull(messageResponse);
        assertEquals("Company registered successfully!", messageResponse.getMessage());
    }

    @Test
    void testAuthenticateCompany_Success() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("companyUser");
        loginRequest.setPassword("password");
        Authentication authentication = mock(Authentication.class);
        UserDetailsImpl userDetails = new UserDetailsImpl(
                2L, "companyUser", "company@example.com", "encodedPassword",
                List.of(new SimpleGrantedAuthority("ROLE_COMPANY"))
        );

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("mockedJwtToken");

        // Act
        ResponseEntity<?> response = authController.authenticateCompany(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JwtResponse jwtResponse = (JwtResponse) response.getBody();
        assertNotNull(jwtResponse);
        assertEquals("mockedJwtToken", jwtResponse.getAccessToken());
        assertEquals("companyUser", jwtResponse.getUsername());
        assertEquals(List.of("ROLE_COMPANY"), jwtResponse.getRoles());
    }
}