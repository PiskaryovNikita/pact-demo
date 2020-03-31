package com.gongsi.product.management.impl.validation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;

/**
 * Keeps validation errors occurs during validate the request and get them in an appropriate form.
 */
public class ValidationContext {
    private final Set<ValidationError> violations = new HashSet<>();

    public void addViolation(String path, String code, String message) {
        this.violations.add(ValidationError.build(path, code, message));
    }

    public void addViolation(ConstraintViolation<?> violation) {
        this.violations.add(ValidationError.build(violation));
    }

    public <T> void addViolations(Collection<ConstraintViolation<T>> violations) {
        violations.forEach(this::addViolation);
    }

    public Set<ValidationError> getViolations() {
        return violations;
    }

    public boolean hasViolations() {
        return !violations.isEmpty();
    }

    /**
     * Get error messages which describe some general invalid request data.
     * Not depends on fields from the request body.
     *
     * @return list of general errors
     */
}
