package com.piper2.momo.database.models.auxilially;

import java.util.ArrayList;

public class Response {
    private String message;
    private Object data;

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data != null ? data : new ArrayList<>();
    }

    public void setData(Object data) {
        this.data = data != null ? data : new ArrayList<>();
    }
}
