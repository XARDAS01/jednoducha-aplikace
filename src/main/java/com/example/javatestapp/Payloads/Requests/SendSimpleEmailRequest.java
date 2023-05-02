package com.example.javatestapp.Payloads.Requests;

public class SendSimpleEmailRequest {
    private String to, subject, body;

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
