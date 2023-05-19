package com.example.oa_attendance;

import java.time.OffsetDateTime;

public class timeset {
    public static void main(String[] args) {
        String str = "2023-05-20T11:23:33.000Z";
        OffsetDateTime odt = OffsetDateTime.parse(str);
        System.out.println(odt);

    }
}
