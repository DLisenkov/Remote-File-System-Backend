package com.project.repositories;

import com.project.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokensRepository extends JpaRepository<Token, Integer> {

}
