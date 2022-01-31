package com.example.handlecare.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter


public enum Status {
    UNCHECKED(0,"Непроверенный"),
    ACTIVE(1,"Активный"),
    BLOCKED(2,"Заблокирован"),
    DELETED(3,"Удалён");

    private final Integer id;
    private final String name;


    public static Status fromText(String text) {
        for(Status s: Status.values()) {
            if(s.getName().equals(text)){
                return s;
            }
        }
        throw new IllegalArgumentException();
    }

}
