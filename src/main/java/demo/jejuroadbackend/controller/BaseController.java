package demo.jejuroadbackend.controller;

import demo.jejuroadbackend.ifs.CrudInterface;
import demo.jejuroadbackend.model.network.HTTPHeader;
import demo.jejuroadbackend.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public abstract class BaseController<Req,Res,Entity> implements CrudInterface<Req,Res> {

  @Autowired(required = false)
  protected BaseService<Req,Res,Entity> baseService;

  @Override
  @PostMapping("")
  public HTTPHeader<Res> create(@RequestBody HTTPHeader<Req> request) {
    return baseService.create(request);
  }

  @Override
  @GetMapping("{id}")
  public HTTPHeader<Res> read(@PathVariable Long id) {
    return baseService.read(id);
  }

  @Override
  @PutMapping("")
  public HTTPHeader<Res> update(@RequestBody HTTPHeader<Req> request) {
    return baseService.update(request);
  }

  @Override
  @DeleteMapping("{id}")
  // todo : delete() 시에도 삭제된 데이터를 반환할 것인가?
  public HTTPHeader delete(@PathVariable Long id) {
    return baseService.delete(id);
  }

  @Override
  @GetMapping("")
  // 페이징 시 맛집의 이름(name)으로 정렬
  public HTTPHeader<List<Res>> findAll(@PageableDefault(sort = "name", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
    return baseService.findAll(pageable);
  }
}
