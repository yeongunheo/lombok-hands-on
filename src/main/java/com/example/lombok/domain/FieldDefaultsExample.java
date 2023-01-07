package com.example.lombok.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.experimental.PackagePrivate;

@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class FieldDefaultsExample {
    public final int a;
    int b;
    @NonFinal int c;
    @PackagePrivate int d;

    FieldDefaultsExample() {
        a = 0;
        b = 0;
        d = 0;
    }

    public void changeC(int num) {
        this.c = num;
    }

    public int getC() {
        return c;
    }
}
