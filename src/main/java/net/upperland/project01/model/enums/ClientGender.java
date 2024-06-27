package net.upperland.project01.model.enums;

public enum ClientGender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    public final String name;

    private ClientGender(String name) {
        this.name = name;
    }
}
