package com.example.domain.user;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import com.navercorp.fixturemonkey.javax.validation.plugin.JavaxValidationPlugin;
import net.jqwik.api.Arbitraries;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserInfoTest {
    @Test
    void jakarataValidation_숫자_MIN_MAX_테스트() {
        final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .plugin(new JakartaValidationPlugin())
                .build();
        // when
        final UserInfo userInfo = fixtureMonkey.giveMeOne(UserInfo.class);

        // then
        assertAll(
                () -> assertThat(userInfo.getAge()).isBetween(1, 150)
        );
    }


    @DisplayName("값에 대한 범위를 지정해서 테스트 할 수 있음")
    @Test
    void 값에_대한_범위_지정_테스트() {
        final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .plugin(new JavaxValidationPlugin())
                .build();
        // when
        final UserInfo userInfo = fixtureMonkey.giveMeBuilder(UserInfo.class)
                .set("id", Arbitraries.longs().between(1, 100))
                .sample();

        // then
        assertThat(userInfo.getId()).isBetween(1L, 100L);
    }
}