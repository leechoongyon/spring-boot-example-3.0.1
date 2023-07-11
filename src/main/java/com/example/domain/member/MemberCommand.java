package com.example.domain.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class MemberCommand {

    @Data
    @Builder
    @AllArgsConstructor
    public static class Create {
        @NotBlank
        private String id;
        @NotBlank
        private String name;
    }
}
