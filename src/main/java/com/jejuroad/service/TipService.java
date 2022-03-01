package com.jejuroad.service;

import com.jejuroad.common.mapper.TipMapper;
import com.jejuroad.dto.TipResponse;
import com.jejuroad.repository.TipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TipService {

    private TipRepository tipRepository;
    private TipMapper mapper;

    public List<TipResponse.Find> find() {
        return tipRepository
            .findAll()
            .stream()
            .map((domain) -> mapper.mapToFindFrom(domain))
            .collect(Collectors.toList());
    }

}
