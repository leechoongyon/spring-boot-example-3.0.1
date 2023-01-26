package com.example.domain.item;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class ItemInfo {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Base {
        private String itemId;
        private String itemName;
        List<Sub> descList;
    }


    @Data
    @SuperBuilder
    @EqualsAndHashCode(callSuper = true)
    public static class Sub extends SuperSub {
        private String etc1;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @SuperBuilder
    public static class SuperSub {
        private String desc1;
        private String desc2;
        private String desc3;
    }
}
