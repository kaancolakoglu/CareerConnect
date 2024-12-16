package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.domain.Company;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    private User testUser;
    private Company testCompany;
    private Role userRole;
    private Role companyRole;

    @BeforeEach
    void setUp() {
        // Setup common test data
        userRole = new Role(ERole.ROLE_USER);
        companyRole = new Role(ERole.ROLE_COMPANY);

        testUser = new User();
        testUser.setProfileId(1L);
        testUser.setUsername("testUser");
        testUser.setEmail("test@example.com");
        testUser.setPassword("encodedPassword");
        testUser.setRoles(Set.of(userRole));

        testCompany = new Company();
        testCompany.setProfileId(2L);
        testCompany.setUsername("testCompany");
        testCompany.setEmail("company@example.com");
        testCompany.setPassword("encodedPassword");
        testCompany.setRoles(Set.of(companyRole));
    }

    @Test
    void testAuthenticateUser_Success() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("rawPassword");

        Authentication authentication = mock(Authentication.class);
        UserDetailsImpl userDetails = new UserDetailsImpl(
                testUser.getProfileId(),
                testUser.getUsername(),
                testUser.getEmail(),
                testUser.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches("rawPassword", testUser.getPassword())).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("mockedJwtToken");

        // Act
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof JwtResponse);
        JwtResponse jwtResponse = (JwtResponse) response.getBody();
        assertEquals("mockedJwtToken", jwtResponse.getAccessToken());
        assertEquals("testUser", jwtResponse.getUsername());
        assertEquals(List.of("ROLE_USER"), jwtResponse.getRoles());

        // Verify interactions
        verify(userRepository, times(2)).findByUsername("testUser");
        verify(passwordEncoder).matches("rawPassword", testUser.getPassword());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtils).generateJwtToken(authentication);
    }

    @Test
    void testAuthenticateUser_InvalidCredentials() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("wrongPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches("wrongPassword", testUser.getPassword())).thenReturn(false);

        // Act
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("newUser");
        signupRequest.setPassword("password");
        signupRequest.setEmail("newuser@example.com");
        signupRequest.setRole(Set.of("USER"));

        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(userRepository.existsByEmail("newuser@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(userRole));

        // Act
        ResponseEntity<?> response = authController.registerUser(signupRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof MessageResponse);
        MessageResponse messageResponse = (MessageResponse) response.getBody();
        assertEquals("User registered successfully!", messageResponse.getMessage());

        // Verify interactions
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testRegisterUser_UsernameAlreadyExists() {
        // Arrange
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("existingUser");
        signupRequest.setPassword("password");
        signupRequest.setEmail("newuser@example.com");
        signupRequest.setRole(Set.of("USER"));

        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

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
        signupRequest.setUsername("newCompany");
        signupRequest.setCompanyName("New Tech Company");
        signupRequest.setCompanyRegistrationNumber("REG123");
        signupRequest.setSectorName("Technology");
        signupRequest.setCompanySize(500L);
        signupRequest.setPassword("password123");
        signupRequest.setEmail("newcompany@example.com");
        signupRequest.setRole(Set.of("COMPANY"));

        when(companyRepository.existsByUsername("newCompany")).thenReturn(false);
        when(companyRepository.existsByEmail("newcompany@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(roleRepository.findByName(ERole.ROLE_COMPANY)).thenReturn(Optional.of(companyRole));

        // Act
        ResponseEntity<?> response = authController.registerCompany(signupRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof MessageResponse);
        MessageResponse messageResponse = (MessageResponse) response.getBody();
        assertEquals("Company registered successfully!", messageResponse.getMessage());

        // Verify interactions
        verify(companyRepository).save(any(Company.class));
    }

    @Test
    void testAuthenticateCompany_Success() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testCompany");
        loginRequest.setPassword("rawPassword");

        Authentication authentication = mock(Authentication.class);
        UserDetailsImpl userDetails = new UserDetailsImpl(
                testCompany.getProfileId(),
                testCompany.getUsername(),
                testCompany.getEmail(),
                testCompany.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_COMPANY"))
        );

        when(companyRepository.findByUsername("testCompany")).thenReturn(Optional.of(testCompany));
        when(passwordEncoder.matches("rawPassword", testCompany.getPassword())).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("mockedJwtToken");

        // Act
        ResponseEntity<?> response = authController.authenticateCompany(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof JwtResponse);
        JwtResponse jwtResponse = (JwtResponse) response.getBody();
        assertEquals("mockedJwtToken", jwtResponse.getAccessToken());
        assertEquals("testCompany", jwtResponse.getUsername());
        assertEquals(List.of("ROLE_COMPANY"), jwtResponse.getRoles());

        // Verify interactions
        verify(companyRepository).findByUsername("testCompany");
        verify(passwordEncoder).matches("rawPassword", testCompany.getPassword());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtils).generateJwtToken(authentication);
    }

    @Test
    void testRegisterCompany_InvalidEmail() {
        // Arrange
        CompanySignupRequest signupRequest = new CompanySignupRequest();
        signupRequest.setUsername("newCompany");
        signupRequest.setCompanyName("New Tech Company");
        signupRequest.setCompanyRegistrationNumber("REG123");
        signupRequest.setSectorName("Technology");
        signupRequest.setCompanySize(500L);
        signupRequest.setPassword("password123");
        signupRequest.setEmail("invalid-email");
        signupRequest.setRole(Set.of("COMPANY"));

        // Act
        ResponseEntity<?> response = authController.registerCompany(signupRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid email format", response.getBody());
    }

    @Test
    void testRegisterCompany_ShortPassword() {
        // Arrange
        CompanySignupRequest signupRequest = new CompanySignupRequest();
        signupRequest.setUsername("newCompany");
        signupRequest.setCompanyName("New Tech Company");
        signupRequest.setCompanyRegistrationNumber("REG123");
        signupRequest.setSectorName("Technology");
        signupRequest.setCompanySize(500L);
        signupRequest.setPassword("short");
        signupRequest.setEmail("newcompany@example.com");
        signupRequest.setRole(Set.of("COMPANY"));

        // Act
        ResponseEntity<?> response = authController.registerCompany(signupRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Password must be at least 8 characters long", response.getBody());
    }
}