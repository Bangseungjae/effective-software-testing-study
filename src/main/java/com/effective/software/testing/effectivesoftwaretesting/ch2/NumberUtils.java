package com.effective.software.testing.effectivesoftwaretesting.ch2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.max;

public class NumberUtils {

    public List<Integer> add(List<Integer> left, List<Integer> right) {
        if (left == null || right == null) {
            return null;
        }

        Collections.reverse(left);
        Collections.reverse(right);

        LinkedList<Integer> result = new LinkedList<>();

        int carry = 0;

        for (int i = 0; i < max(left.size(), right.size()); i++) {
            int leftDigit = left.size() > i ? left.get(i) : 0;
            int rightDigit = right.size() > i ? right.get(i) : 0;

            if (leftDigit < 0 || leftDigit > 9 || rightDigit < 0 || rightDigit > 9) {
                throw new IllegalArgumentException();
            }

            int sum = leftDigit + rightDigit + carry;
            result.addFirst(sum % 10);

            carry  = sum / 10;
        }

        if (carry > 0) {
            result.addFirst(carry);
        }

        while (result.size() > 1 && result.getFirst() == 0) {
            result.removeFirst();
        }

        return result;
    }
}
