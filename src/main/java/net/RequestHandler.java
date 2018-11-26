package net;

public abstract class RequestHandler <T extends Request> {
    public abstract RequestResponse handle(T message);
}