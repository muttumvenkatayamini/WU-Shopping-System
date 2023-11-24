package com.wu.productservice.dto;



import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class APIResponse<T> {


    public static <T> APIResponse<T> createSuccessResponse() {
        ResponseMessage message = new ResponseMessage();
        APIResponse<T> response = new APIResponse<>();
        message.setResponseCode(200);
        message.setResponseDescription("Successful Response");
        response.setResponseMessage(message);
        return response;
    }

    public static <T> APIResponse<T> createFailureResponse() {
        ResponseMessage message = new ResponseMessage();
        APIResponse<T> response = new APIResponse<>();
        message.setResponseCode(500);
        message.setResponseDescription("We are not able to process the request");
        response.setResponseMessage(message);
        return response;
    }


    private Exception exception;

    private T objectList;



    private ResponseMessage responseMessage;




    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseMessage getResponseMessage() {
        return this.responseMessage;
    }

    public T getObjectList() {
        return objectList;
    }

    public void setObjectList(T objectList) {
        this.objectList = objectList;
    }

    public static <T> APIResponse<T> parseAbstractResponse(String json, TypeToken type) {
        return new GsonBuilder()
                .create()
                .fromJson(json, type.getType());
    }

}
