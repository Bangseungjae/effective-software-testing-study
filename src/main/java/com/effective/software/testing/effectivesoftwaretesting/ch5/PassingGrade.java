package com.effective.software.testing.effectivesoftwaretesting.ch5;

public class PassingGrade {
    /**
     *
     * @param grade 1에서 10 사이이다.
     * @return 5.0 이상이면 true, 미만이면 false를 반환한다.
     */
    public boolean passed(float grade) {
        if (grade < 1.0 || grade > 10.0) {
            throw new IllegalArgumentException();
        }
        return grade >= 5.0;
    }
}
