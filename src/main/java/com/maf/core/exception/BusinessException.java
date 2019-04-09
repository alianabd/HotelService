package com.maf.core.exception;

/**
 * {@link Exception} class represents the business validations error
 */
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }
}
