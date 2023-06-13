package com.vodafone.database;

public enum ConfigVariables {
    // TODO

    // DB_URL("jdbc:mysql://db:3306/payment"), docker version
    DB_URL("jdbc:mysql://127.0.0.1:3306/payment"),
    DB_USER("root"),
    DB_PASSWORD("P@ssw0rd");

    String value;

    ConfigVariables(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
