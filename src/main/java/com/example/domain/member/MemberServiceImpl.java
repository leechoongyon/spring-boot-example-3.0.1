package com.example.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    @Override
    public String registerMember(MemberCommand.Create memberCommandCreate) {
        return memberCommandCreate.getId();
    }
}
