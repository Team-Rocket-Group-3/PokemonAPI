package com.teamRocket.PokemonApi.support;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response {

    public static final int NOT_FOUND = 404;
    public static  final int OK = 200;
    public static final String NO_MESSAGE = "";
    public static final int ERROR_NOT_FOUND = 101;

    private Error error;

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class Error {
        private long errorCode;
        private String message;
    }

    public static Response noErrorResponse() {
        return new Response(new Error(OK, NO_MESSAGE));
    }

    public static Response errorResponse(int errorCode, String errorMessage) {
        return new Response(new Error(errorCode, errorMessage));
    }
}