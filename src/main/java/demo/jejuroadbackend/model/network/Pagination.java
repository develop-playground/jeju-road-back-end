package demo.jejuroadbackend.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

  private Integer totalPage;

  private Long totalElements;

  private Integer currentPage;

  private Long currentElements;

}
