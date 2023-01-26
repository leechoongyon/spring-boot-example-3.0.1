package com.example.domain.member;

import lombok.Getter;

public class MemberInfo {

    @Getter
    public static class Base {
        private String id;
        private String name;
    }
}
