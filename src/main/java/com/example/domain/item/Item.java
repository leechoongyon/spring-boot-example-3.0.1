package com.example.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String id;
    private String itemName;

    private List<ItemDescription> descriptionList;

    public static List<ItemInfo.Sub> resolveDescList(final Item item) {
        return item.getDescriptionList().stream().map(desc ->
                ItemInfo.Sub.builder()
                        .desc1(desc.getDesc1())
                        .desc2(desc.getDesc2())
                        .desc3(desc.getDesc3())
                        .build()
        ).collect(Collectors.toList());
    }

    ;

    @Data
    @AllArgsConstructor
    @Builder
    public static class ItemDescription {
        private String desc1;
        private String desc2;
        private String desc3;
    }
}
