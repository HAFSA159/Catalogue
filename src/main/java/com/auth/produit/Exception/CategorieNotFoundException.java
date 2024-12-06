package com.auth.produit.Exception;

public class CategorieNotFoundException extends RuntimeException {
    public CategorieNotFoundException(String message) {
        super(message);
    }
}
