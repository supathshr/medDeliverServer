package net;

public abstract class RequestEncoder <T extends RequestResponse> {
    public abstract Packet encode(T message);
}