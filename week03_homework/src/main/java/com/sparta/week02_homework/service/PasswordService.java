package com.sparta.week02_homework.service;


import com.sparta.week02_homework.models.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PasswordService {


    private final MemoRepository memoRepository;
    private final PasswordRepostiory passwordRepostiory;

    public PasswordService(MemoRepository memoRepository, PasswordRepostiory passwordRepostiory) {

        this.memoRepository = memoRepository;
        this.passwordRepostiory = passwordRepostiory;

    }


    @Transactional
    public void passupdate(Long id, Password password) {

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(" 해당 아이디가 없습니다.")
        );



        //memo.passupdate(password);


    }


}
