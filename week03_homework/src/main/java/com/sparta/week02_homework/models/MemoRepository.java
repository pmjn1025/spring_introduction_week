package com.sparta.week02_homework.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {


    List<Memo> findAllByOrderByCreatedAtDesc();

    Optional<Memo> findById(Long id);


}
