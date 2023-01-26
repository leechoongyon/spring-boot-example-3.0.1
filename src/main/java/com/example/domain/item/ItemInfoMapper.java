package com.example.domain.item;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ItemInfoMapper {

    ItemInfoMapper INSTANCE = Mappers.getMapper(ItemInfoMapper.class);

    @Mapping(source = "id", target = "itemId")
    @Mapping(expression = "java(Item.resolveDescList(item))", target = "descList")
    ItemInfo.Base of(Item item);

}
