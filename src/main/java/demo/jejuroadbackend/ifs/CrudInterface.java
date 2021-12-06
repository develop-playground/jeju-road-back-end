package demo.jejuroadbackend.ifs;

import demo.jejuroadbackend.model.network.HTTPHeader;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudInterface<Req,Res> {

  HTTPHeader<Res> create(HTTPHeader<Req> request);

  HTTPHeader<Res> read(Long id);

  HTTPHeader<Res> update(HTTPHeader<Req> request);

  HTTPHeader delete(Long id);

  HTTPHeader<List<Res>> findAll(Pageable pageable);

}
