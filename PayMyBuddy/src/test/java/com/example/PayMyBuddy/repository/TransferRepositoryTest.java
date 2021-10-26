package com.example.PayMyBuddy.repository;

import com.example.PayMyBuddy.model.Transfer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class TransferRepositoryTest {
    @Autowired
    private TransferRepository repository;

    @Test
    public void testFindByFromId() {
        Integer id = 1;
        List<Transfer> transfer= repository.findByFromId(id);
        assertThat(transfer).isNotNull();

    }
}
