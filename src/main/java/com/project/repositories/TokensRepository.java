package com.project.repositories;

import com.project.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokensRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findOneByValue(String value);
}
