package com.example.interfaces.member;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
