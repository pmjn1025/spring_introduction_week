package com.sparta.week02_homework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data // data 어노테이션 사용
@Builder // builder 어노테이션 사용
@AllArgsConstructor
@NoArgsConstructor

// response Entity의 제네릭 타입 BasicResponse
public class BasicResponse {
    // @JsonIgnore(value = true)로 과제 제출 포멧에 맞게 생략
    @JsonIgnore(value = true)
    // 응답 코드
    public Integer code;
    @JsonIgnore(value = true)
    // 연결 상태
    public HttpStatus httpStatus;
    // json 응답 성공시 디폴트 값으로 true
    public boolean success = true;
    // 에러 발생시 에러 메시지 출력
    public String error;

    // 성공 혹은 실패시 메시지 보냄 과제 제출 포멧으로 생략함
    @JsonIgnore(value = true)
    public String message;

    // json 데이터 내용.
    public List<Object> data;



}
