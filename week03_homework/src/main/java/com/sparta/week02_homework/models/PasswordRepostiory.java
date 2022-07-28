package com.sparta.week02_homework.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepostiory extends JpaRepository<Password, Long> {
}
