package com.example.interfaces.member;

import com.example.domain.member.MemberCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MemberDtoMapper {
    MemberCommand.Create of(MemberDto.Create create);
    MemberCommand.Create of(MemberDto.CreateV2 create);
    MemberCommand.Create of(MemberDto.CreateV3 create);
}
