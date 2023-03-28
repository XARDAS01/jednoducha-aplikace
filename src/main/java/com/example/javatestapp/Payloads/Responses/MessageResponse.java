package com.example.javatestapp.Payloads.Responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageResponse {
    private String message;
    private int code;
    private Object payload;

    public MessageResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public MessageResponse(String message, int code, Object payload) {
        this.message = message;
        this.code = code;
        this.payload = payload;
    }

    public String toJson () throws JsonProcessingException {
        String responseJSON;
        final ObjectMapper objectMapper = new ObjectMapper();
        responseJSON = objectMapper.writeValueAsString(this);
        return responseJSON;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Object getPayload() {
        return payload;
    }
}
