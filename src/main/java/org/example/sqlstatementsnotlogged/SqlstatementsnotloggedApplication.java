package org.example.sqlstatementsnotlogged;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@SpringBootApplication
public class SqlstatementsnotloggedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlstatementsnotloggedApplication.class, args);
    }
    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

        return initializer;
    }
}

@Table("persons")
class Person {
    @Id
    private final long id;
    private final String name;

    Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

}

interface PersonRepository extends ReactiveCrudRepository<Person, Long> {}