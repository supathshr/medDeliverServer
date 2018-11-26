package net;

public abstract class Request {
    public final RequestType requestType;

    public Request(RequestType requestType) {
        this.requestType = requestType;
    }
}
