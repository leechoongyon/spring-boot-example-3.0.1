package com.example.domain.member;

import jakarta.validation.Valid;

public interface MemberService {
    String registerMember(MemberCommand.Create memberCommandCreate);
}
