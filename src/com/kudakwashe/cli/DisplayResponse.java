package com.kudakwashe.cli;

public class DisplayResponse {
    private boolean isValid;
    private String message;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static DisplayResponse of(boolean isValid, String message) {
        DisplayResponse displayResponse = new DisplayResponse();
        displayResponse.setValid(isValid);
        displayResponse.setMessage(message);
        return displayResponse;
    }
}
