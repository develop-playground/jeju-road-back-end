package com.jejuroad.common.mapper;

import com.jejuroad.domain.Tip;
import com.jejuroad.dto.TipResponse;
import com.jejuroad.repository.TipRepository;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = TipRepository.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class TipMapper {

    private TipRepository tipRepository;

    public TipResponse.Find mapToFindFrom(Tip tip) {
        return TipResponse.Find.builder()
            .content(tip.getContent())
            .build();
    }

}
