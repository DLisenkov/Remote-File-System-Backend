package com.project.repositories;

import com.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<User, Integer> {
}
