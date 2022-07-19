package com.activity.four.service;

import com.activity.four.model.Users;
import com.activity.four.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private UsersRepository usersRepositoryTest;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UsersService usersServiceTest;

    private Users user;

    @BeforeEach
    void setUp() {
        usersServiceTest = new UsersService(usersRepositoryTest, passwordEncoder);
        user = new Users(1L, "user", passwordEncoder.encode("user"), "USER");
    }

    @Test
    void getUsers() {
        usersServiceTest.getUsers();
        verify(usersRepositoryTest).findAll();
    }

    @Test
    void addUser() {
        usersServiceTest.addUser(user);
        ArgumentCaptor<Users> userArgumentCaptor = ArgumentCaptor.forClass(Users.class);
        verify(usersRepositoryTest).save(userArgumentCaptor.capture());
        Users capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void deleteUser() {
        Optional<Users> userOptional = Optional.of(user);
        when(usersRepositoryTest.findById(user.getId())).thenReturn(userOptional);
        usersServiceTest.deleteUser(user.getId());
        verify(usersRepositoryTest, times(1)).deleteById(user.getId());
    }

    @Test
    void deleteUser_UserDoesNotExist() {
        assertThatThrownBy(() -> usersServiceTest.deleteUser(user.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void updateUser() {
        Optional<Users> userOptional = Optional.of(user);
        when(usersRepositoryTest.findById(user.getId())).thenReturn(userOptional);
        usersServiceTest.updateUser(user.getId(), "new", "new", "ADMIN");
        assertEquals(user.getUsername(), "new");
        assertEquals(user.getPassword(), passwordEncoder.encode("new"));
        assertEquals(user.getRole(), "ADMIN");
    }

    @Test
    void updateUser_Null() {
        Optional<Users> userOptional = Optional.of(user);
        when(usersRepositoryTest.findById(user.getId())).thenReturn(userOptional);
        usersServiceTest.updateUser(user.getId(), null, null, null);
        assertEquals(user.getUsername(), "user");
        assertEquals(user.getPassword(), passwordEncoder.encode("user"));
        assertEquals(user.getRole(), "USER");
    }

    @Test
    void updateUser_ZeroLength() {
        Optional<Users> userOptional = Optional.of(user);
        when(usersRepositoryTest.findById(user.getId())).thenReturn(userOptional);
        usersServiceTest.updateUser(user.getId(), "", "", "");
        assertEquals(user.getUsername(), "user");
        assertEquals(user.getPassword(), passwordEncoder.encode("user"));
        assertEquals(user.getRole(), "USER");
    }

    @Test
    void updateUser_Duplicate() {
        Optional<Users> userOptional = Optional.of(user);
        when(usersRepositoryTest.findById(user.getId())).thenReturn(userOptional);
        usersServiceTest.updateUser(user.getId(), "user", passwordEncoder.encode("user"), "USER");
        assertEquals(user.getUsername(), "user");
        assertEquals(user.getPassword(), passwordEncoder.encode("user"));
        assertEquals(user.getRole(), "USER");
    }

    @Test
    void updateUser_UserDoesNotExist() {
        assertThatThrownBy(() -> usersServiceTest.updateUser(user.getId(), "new", "new", "ADMIN"))
                .isInstanceOf(IllegalStateException.class);
    }
}