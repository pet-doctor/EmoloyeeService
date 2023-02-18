package com.petdoctor.employee.settings.testdata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor
public enum DoctorTestData {

    I_CASE_DATA("Валидный параметр userId, который есть в базе данных", "11"),
    II_CASE_DATA("Валидный параметр userId, которого нет в базе данных", "14"),
    III_CASE_DATA("Пустой параметр userId", ""),
    IV_CASE_DATA("Невалидный параметр userId", "test");

    private final String description;
    private final String value;

    @Override
    public String toString() {
        return description;
    }
}
