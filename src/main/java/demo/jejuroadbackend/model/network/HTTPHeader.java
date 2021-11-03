package demo.jejuroadbackend.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HTTPHeader<T> {

  private String code;

  private String message;

  private T information;

  private Pagination pagination;

  public static <T> HTTPHeader<T> OK(T information) {
    return (HTTPHeader<T>) HTTPHeader.builder()
        .code("200 OK")
        .message("요청이 정상적으로 처리되었습니다.")
        .information(information)
        .build();
  }

  public static <T> HTTPHeader<T> OK(T information, Pagination pagination) {
    return (HTTPHeader<T>) HTTPHeader.builder()
        .code("200 OK")
        .message("요청이 정상적으로 처리되었습니다.")
        .information(information)
        .pagination(pagination)
        .build();
  }

}
