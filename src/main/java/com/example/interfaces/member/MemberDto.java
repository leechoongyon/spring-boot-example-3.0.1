package com.example.interfaces.member;

import com.example.domain.member.validator.MemberIdConstration;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

public class MemberDto {

    @Data
    @NoArgsConstructor
    public static class Create {
        @Length(max = 20)
        private String id;
        @NotNull
        private String name;
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class CreateV2 {
        @MemberIdConstration
        private String id;
        private String name;
    }

    @Getter
    @NoArgsConstructor
    public static class Base {
        private String id;
        private String name;

        public Base(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
