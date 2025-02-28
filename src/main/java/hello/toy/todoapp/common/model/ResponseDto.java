package hello.toy.todoapp.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "반환용 DTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {

    @Schema(description = "요청의 성공 여부")
    private boolean success; // 요청의 성공 여부

    @Schema(description = "메시지")
    private String message;  // 메시지

    @Schema(description = "반환할 데이터")
    private T data;         // 반환할 데이터

    @Schema(description = "에러 정보를 위한 추가 필드 (선택 사항)")
    // 에러 정보를 위한 추가 필드 (선택 사항)
    private String error;    // 에러 메시지 (있을 경우)

    public static <T> ResponseDto<T> of(boolean success, String message, T data) {
        return ResponseDto.<T>builder()
            .success(success)
            .message(message)
            .data(data)
            .build();
    }

    public static <T> ResponseDto<T> of(boolean success, String message, T data, String error) {
        return ResponseDto.<T>builder()
            .success(success)
            .message(message)
            .data(data)
            .error(error)
            .build();
    }

}