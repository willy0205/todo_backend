package hello.toy.todoapp.common.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDto<T> {

    private boolean success; // 요청의 성공 여부
    private String message;  // 메시지
    private T data;         // 반환할 데이터

    // 에러 정보를 위한 추가 필드 (선택 사항)
    private String error;    // 에러 메시지 (있을 경우)

}