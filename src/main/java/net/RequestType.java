package net;

public enum RequestType {
    AUTHENTICATION("authentication"),
    VIEW_PRIVATE_MESSAGE("viewPrivateMessage"),
    VIEW_ALL_PRIVATE_MESSAGES("viewAllPrivateMessages"),
    VIEW_FRIENDS("viewFriends"),
    SEND_PRIVATE_MESSAGE("sendPrivateMessage");

    private String tag;
    RequestType(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return tag;
    }

}
