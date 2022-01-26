package com.jejuroad.service;

import com.jejuroad.common.mapper.CategoryMapper;
import com.jejuroad.dto.CategoryResponse;
import com.jejuroad.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper mapper;

    public List<CategoryResponse.Find> find() {
        return categoryRepository
            .findAll()
            .stream()
            .map((domain) -> mapper.mapToFindFrom(domain))
            .collect(Collectors.toList());
    }

}
