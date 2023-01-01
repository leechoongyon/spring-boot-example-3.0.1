package com.example.domain.member;

import lombok.Data;

public class MemberCommand {

    @Data
    public static class Create {
        private String id;
        private String name;
    }
}
