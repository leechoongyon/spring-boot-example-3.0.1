package com.example.domain.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemInfoMapperTest {
    @Test
    void testItemInfoMapperTest() {
        final var item = Item.builder()
                .id("10")
                .itemName("testItem")
                .descriptionList(List.of(
                        Item.ItemDescription.builder()
                                .desc1("desc1")
                                .desc2("desc2")
                                .desc3("desc3")
                                .build(),
                        Item.ItemDescription.builder()
                                .desc1("desc11")
                                .desc2("desc22")
                                .desc3("desc33")
                                .build()
                ))
                .build();

        final ItemInfo.Base result = ItemInfoMapper.INSTANCE.of(item);

        final ItemInfo.Base expected = getExpected();

        Assertions.assertEquals(result, expected);
    }

    private ItemInfo.Base getExpected() {
        final var expected = ItemInfo.Base.builder()
                .itemId("10")
                .itemName("testItem")
                .descList(List.of(
                        ItemInfo.Sub.builder()
                                .desc1("desc1")
                                .desc2("desc2")
                                .desc3("desc3")
                                .build(),
                        ItemInfo.Sub.builder()
                                .desc1("desc11")
                                .desc2("desc22")
                                .desc3("desc33")
                                .build()))
                .build();
        return expected;
    }
}
