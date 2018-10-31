package io.nobel.myapp.common;

/**
 * Created by petre on 10/31/2018.
 */
public class Exceptions {

    public static ApiException badRequest(String message) {
        return new ApiException(400, message);
    }

    public static ApiException notFound(String message) {
        return new ApiException(404, message);
    }

}
