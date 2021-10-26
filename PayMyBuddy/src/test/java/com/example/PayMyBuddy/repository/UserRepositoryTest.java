package com.example.PayMyBuddy.repository;

import com.example.PayMyBuddy.form.RegisterForm;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("marvin@gmail.com");
        user.setFirstName("Marvin");
        user.setLastName("Kumar");
        user.setPassword("marvin2020");

        User savedUser = repository.save(user);
        User existUser = testEntityManager.find(User.class, savedUser.getId());
        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserbyEmail() {
        String email = "alex@gmail.com";
        Optional<User> user = repository.findByEmail(email);
        assertThat(user).isNotNull();
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Optional<User> user = repository.findById(id);
        assertThat(user).isNotNull();

    }
}