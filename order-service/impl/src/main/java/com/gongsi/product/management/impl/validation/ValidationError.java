package com.gongsi.product.management.impl.validation;

import javax.validation.ConstraintViolation;
import org.apache.commons.lang3.StringUtils;

/**
 * Describes error message which might be occurred during validation a request to Service.
 */
public final class ValidationError {
    private final String path;
    private final String code;
    private final String message;

    private ValidationError(String path, String code, String message) {
        this.path = path;
        this.code = code;
        this.message = message;
    }

    public static ValidationError build(String path, String code, String message) {
        return new ValidationError(path, code, message);
    }

    public static ValidationError build(ConstraintViolation violation) {
        return new ValidationError(
            violation.getPropertyPath().toString(),
            violation.getMessageTemplate().replaceAll("[{}]", StringUtils.EMPTY),
            violation.getMessage()
        );
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public boolean isGlobal() {
        return StringUtils.isBlank(path);
    }

    public boolean isField() {
        return StringUtils.isNotBlank(path);
    }
}
