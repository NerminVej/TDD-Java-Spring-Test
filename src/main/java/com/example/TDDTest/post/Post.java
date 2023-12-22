package com.example.TDDTest.post;

public record Post(
        Integer id,
        Integer userId,
        String title,
        String body,
        Integer version
) {
}
