package ar.edu.itba.paw2019a2.model;

public enum UserTokenState {
    USER_ENABLED_EXPIRED_TOKEN("User enabled, expired token"),
    USER_ENABLED_VALID_TOKEN("User enabled, valid token"),
    USER_DISABLED_EXPIRED_TOKEN("User disabled, expired token"),
    USER_DISABLED_VALID_TOKEN("User disabled, valid token"),
    ;

    final String description;

    UserTokenState(String description) {
        this.description = description;
    }
}
