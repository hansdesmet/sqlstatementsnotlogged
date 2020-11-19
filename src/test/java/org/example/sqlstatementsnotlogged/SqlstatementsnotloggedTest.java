package org.example.sqlstatementsnotlogged;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class SqlstatementsnotloggedTest {
    @Autowired
    PersonRepository personRepository;

    @Test
    void sqlStatementIsNotLogged() {
        var count= personRepository.count();
        StepVerifier.create(count).expectNextCount(1).verifyComplete();
    }

}
