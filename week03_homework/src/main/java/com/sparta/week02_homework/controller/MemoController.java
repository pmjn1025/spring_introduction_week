package com.sparta.week02_homework.controller;


import com.sparta.week02_homework.models.*;
import com.sparta.week02_homework.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MemoController {
    private final MemoRepository memoRepository;
    private final PasswordRepostiory passwordRepostiory;
    private final MemoService memoService;

    // 의존성 주입
    @Autowired
    public MemoController(MemoRepository memoRepository,
                          PasswordRepostiory passwordRepostiory,
                          MemoService memoService
                          )
    {
    this.memoRepository = memoRepository;
    this.passwordRepostiory = passwordRepostiory;
    this.memoService = memoService;

    }

    // 작성 메시지 출력
    @GetMapping("/api/post")
    // httpentity를 상속받는 ResponseEntity생성후 제너릭 타입으로 BasicResponse 입력.
    public ResponseEntity<BasicResponse> readPostAll() {

        // 메세지 작성 최신순으로 내림차순
        List<Memo> memberList = memoRepository.findAllByOrderByCreatedAtDesc();

        // 해당 BasicResponse를 빌더 패턴으로 입력.
        BasicResponse basicResponse = BasicResponse.builder()
                //code,httpstaus,message는 생략.
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("전체 사용자 조회 성공")
                .success(true)
                .data(new ArrayList<>(memberList)).build();
                //.count(memberList.size())

        // 반환 값으로 basicResponse를 반환.
        return new ResponseEntity<>(basicResponse, HttpStatus.OK);


    }
    // 해당 번호의 데이터 출력
    @GetMapping("/api/post/{id}")
    // @pathVariable로 해당 URL의 id를 받아옴.
    public ResponseEntity<BasicResponse> readPostOne(@PathVariable Long id) {

        BasicResponse basicResponse = new BasicResponse();
        // 메모리포지터리에서 가져온 값을 exeption오류 처리해서 가져올 수 있도록 Optional로 가져옴
        Optional<Memo> member = memoRepository.findById(id);

        // 해당 데이터가 있으면
        if (member.isPresent()) {
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("사용자 조회 성공")
                    .success(true)
                    .data(Arrays.asList(member.get())).build();


        } else {   // 해당 데이터가 없으면
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .error( id+ "번째의 사용자를 찾을 수 없습니다.")
                    .data(Collections.emptyList()).build();


        }

        // 반환값.
        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());



    }
    // 데이터 추가
    @PostMapping("/api/post")
    // json타입으로 클라이언트에서 입력을 받음
    public ResponseEntity<BasicResponse> createPost(@RequestBody MemoRequestDto requestDto){

        // 메모 인스턴스에 DTO를 넣어서 인스턴스를 생성
        Memo memo = new Memo(requestDto);

        // basicResponse를 생성
        BasicResponse basicResponse = new BasicResponse();
        // 이름, 제목, 코멘트, 비밀번호 입력 받아옴.
        String username = memo.getUsername();
        String title = memo.getTitle();
        String comment = memo.getComment();
        String userpassword = memo.getUserPassword();
        //Optional<Memo> member = memoRepository.findById(id);

        // 데이터가 입력 체크
        if (!username.equals("")  && !title.equals("") &&
                !comment.equals("") && !userpassword.equals("")) {
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("사용자 등록 성공")
                    .success(true)
                    .data(Arrays.asList(memo)).build();

            // 정상적으로 있으면 저장
            memoRepository.save(memo);

        } else { // 하나라도 없으면 에러 출력 및 저장 X
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .error( "사용자 등록 오류입니다.")
                    .data(Collections.emptyList()).build();
                    //.count(0).build();

        }


        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());


    }

    // 위 해당 데이터의 비밀번호 추가
    @PostMapping("/api/post/{id}")
    public ResponseEntity<BasicResponse> createPassword(@PathVariable Long id, @RequestBody PasswordDto passwordDto){
        // 비밀번호 엔티티를 새로 만들고 거기에 새로운 비밀번호 입력.
        Password password = new Password(passwordDto);

        BasicResponse basicResponse = new BasicResponse();

        String password1 = password.getPassword();

        // 새로운 비밀번호 체크
        if (!password1.equals("")) {
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("비밀번호 입력 성공")
                    .success(true)
                    .data(Arrays.asList(true)).build();
                    //.count(1).build();

            // 비밀번호가 입력되면 비밀번호 저장
            passwordRepostiory.save(password);

        } else { // 없으면 에러출력
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .error( "비밀번호 입력 오류입니다 비밀번호를 입력해주세요.")
                    .data(Collections.emptyList()).build();
                    //.count(0)

        }


        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());




    }
    // 해당 id에 해당하는 데이터 수정
    @PutMapping("/api/post/{id}")
    public ResponseEntity<BasicResponse> updateMemo(@PathVariable Long id,
                                                    @RequestBody MemoRequestDto memoRequestDto


    ){


        return memoService.update(id, memoRequestDto);
        //return memoService.update(id, memoRequestDto);
    }

    // 해당 id에 해당하는 데이터 삭제
    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<BasicResponse> deleteMemo(@PathVariable Long id){

        return memoService.delete(id);



    }




}
