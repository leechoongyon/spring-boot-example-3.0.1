package com.example.domain.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    @Override
    public String registerMember(@Valid MemberCommand.Create memberCommandCreate) {
        return memberCommandCreate.getId();
    }
}
