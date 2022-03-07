package com.jejuroad.common.mapper;

import com.jejuroad.domain.Menu;
import com.jejuroad.dto.MenuRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "image", source = "image")
    Menu mapToMenuFrom(MenuRequest.Register register);

}
