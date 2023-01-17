package kingict.carrest;

import kingict.carrest.entity.User;
import kingict.carrest.exception.ValidationException;
import kingict.carrest.repository.UserRepository;
import kingict.carrest.security.service.UserDetailsCustomService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsCustomServiceTest {

    @InjectMocks
    UserDetailsCustomService userDetailsCustomService;

    @Mock
    UserRepository userRepository;

    @Test
    public void testExistingUser(){
        User user = new User();
        user.setFirstname("Jakov");
        user.setLastname("Jakic");
        user.setUsername("jjakic");
        user.setPassword("jak");
        user.setRoles("EDITOR,VIEWER");

        when(userRepository.findByUsername("jjakic")).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsCustomService.loadUserByUsername("jjakic");

        assertEquals(userDetails.getUsername(), "jjakic");
        assertEquals(userDetails.getPassword(), "jak");
        assertEquals(userDetails.getAuthorities().size(), 2);
    }

    @Test
    public void testNonExistingUser(){
        when(userRepository.findByUsername("jjakic")).thenReturn(Optional.empty());
        ValidationException validationException = assertThrows(
                ValidationException.class,
                () -> userDetailsCustomService.loadUserByUsername("jjakic"),
                "Expected loadByUsername to throw, but it didn't");

        assertEquals(validationException.getMessage(), "Ne postoji user s usernameom: jjakic");
    }
}
