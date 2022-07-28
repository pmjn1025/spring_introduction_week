package com.sparta.week02_homework.service;


import com.sparta.week02_homework.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Service
public class MemoService {

    private final MemoRepository memoRepository;
    private final PasswordRepostiory passwordRepostiory;



    @Autowired
    public MemoService(MemoRepository memoRepository, PasswordRepostiory passwordRepostiory){

        this.memoRepository = memoRepository;
        this.passwordRepostiory = passwordRepostiory;

    }




    @Transactional
    public ResponseEntity<BasicResponse> update(Long id, MemoRequestDto memoRequestDto){

        BasicResponse basicResponse = new BasicResponse();


        String str1="";

        //1. 업데이트 반환타입 String으로 변경
        //2. 비밀번호 입력받을 DTO 하나 만들기
        //3. 기존 비밀번호 입력비밀번호 체크
        //4. 같으면 수정 가능
        //5. 틀리면 오류메시지 띄우기

        // 이부분 비밀번호 비교
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(" 해당 아이디가 없습니다.")
        );

        // 새로운 비밀번호가 id+1 번째가되어 번호에 맞게 동기화.
        Password password = passwordRepostiory.findById(id+1).orElseThrow(
                () -> new IllegalArgumentException(" 해당 아이디가 없습니다.")
        );

        // 수정시 입력하는 비밀번호
        memo.passupdate1(memoRequestDto.getUserPassword());

        // 수정시 입려하는 비밀번호를 p1으로 선언
        String p1 = memo.getUserPassword();
        System.out.println(p1);
        // 새롭게 바뀐 비밀번호
        String p2 = password.getPassword();
        //String p2 = passwordDto.getPassword();

        System.out.println(p2);

        // 수정시 입력받는 비밀번호와 새롭게 바뀐 비밀번호를 비교. equals()
        if (p1.equals(p2)) {
            memo.update(memoRequestDto);
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("내용변경 성공")
                    .success(true)
                    .data(Arrays.asList(memo)).build();




        } else {
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .error( "비밀번호가 틀려 내용 변경 오류입니다.")
                    .data(Collections.emptyList()).build();


        }


        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());


    }




    @Transactional
    public ResponseEntity<BasicResponse> delete(Long id){

        BasicResponse basicResponse = new BasicResponse();

        String str1="";

        //1. 업데이트 반환타입 String으로 변경
        //2. 비밀번호 입력받을 DTO 하나 만들기
        //3. 기존 비밀번호 입력비밀번호 체크
        //4. 같으면 수정 가능
        //5. 틀리면 오류메시지 띄우기

        // 해당 id가 있는지 체크
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(" 해당 아이디가 없습니다.")
        );


//        String p1 = memo.getUserPassword();
//        System.out.println(p1);
//        String p2 = password.getPassword();
//        System.out.println(p2);

        long id_ex = memo.getId();
        // id값이 0이 아니면
        if (id_ex != 0) {
            memoRepository.deleteById(id);
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("삭제 ok")
                    .success(true)
                    .data(Arrays.asList(true)).build();

        } else { // id가 0이면 뭔가 오류가 난것이다.
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .error( "삭제 오류입니다.")
                    .data(Collections.emptyList()).build();


        }


        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());


    }









}




