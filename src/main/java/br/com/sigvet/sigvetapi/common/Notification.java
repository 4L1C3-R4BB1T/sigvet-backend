package br.com.sigvet.sigvetapi.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Notification {
    
    private List<String> errors = new ArrayList<>();

    public void addErrors(List<String> errors) {
        this.errors.addAll(errors);
    }

    public void addError(String message) {
        this.errors.add(message);
    }

    public boolean hasAnyError() {
        return !this.errors.isEmpty();
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
