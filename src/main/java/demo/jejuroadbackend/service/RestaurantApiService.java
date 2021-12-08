package demo.jejuroadbackend.service;

import demo.jejuroadbackend.domain.Restaurant;
import demo.jejuroadbackend.model.network.HTTPHeader;
import demo.jejuroadbackend.model.network.request.RestaurantApiRequest;
import demo.jejuroadbackend.model.network.response.RestaurantApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantApiService extends BaseService<RestaurantApiRequest, RestaurantApiResponse, Restaurant>{

  @Override
  public HTTPHeader<RestaurantApiResponse> create(HTTPHeader<RestaurantApiRequest> request) {
    return null;
  }

  @Override
  public HTTPHeader<RestaurantApiResponse> read(Long id) {
    return null;
  }

  @Override
  public HTTPHeader<RestaurantApiResponse> update(HTTPHeader<RestaurantApiRequest> request) {
    return null;
  }

  @Override
  public HTTPHeader delete(Long id) {
    return null;
  }

  @Override
  public HTTPHeader<List<RestaurantApiResponse>> findAll(Pageable pageable) {
    return null;
  }

}
