package com.effective.software.testing.effectivesoftwaretesting.ch3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CountWordsTest {

    @Test
    void twoWordsEndingWithS() {
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }

    @Test
    void noWordsAtAll() {
        int words = new CountWords().count("dog cat");
        assertThat(words).isEqualTo(0);
    }

}
