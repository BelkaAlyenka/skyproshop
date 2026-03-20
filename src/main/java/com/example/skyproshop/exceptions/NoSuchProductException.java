package com.example.skyproshop.exceptions;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("Товар не найден");
    }
}
