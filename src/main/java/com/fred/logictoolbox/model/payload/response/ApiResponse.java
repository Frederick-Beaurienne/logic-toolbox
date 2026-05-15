package com.fred.logictoolbox.model.payload.response;

/**
 * Standard API response wrapper.
 *
 * @param <T> the response payload type
 */
public class ApiResponse<T> {

    // ---------- ATTRIBUTES ---------- //

    /**
     * Indicates whether the request was successful.
     */
    private boolean success;

    /**
     * Response message.
     */
    private String message;

    /**
     * Response payload.
     */
    private T data;

    // ---------- CONSTRUCTORS ---------- //

    public ApiResponse() {
    }

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // ---------- STATIC FACTORY METHODS ---------- //

    /**
     * Creates a successful API response.
     *
     * @param data the response payload
     * @param <T>  the payload type
     * @return a successful API response
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Succès", data);
    }

    /**
     * Creates a successful API response with a custom message.
     *
     * @param message the success message
     * @param data    the response payload
     * @param <T>     the payload type
     * @return a successful API response
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    /**
     * Creates an error API response.
     *
     * @param message the error message
     * @return an error API response
     */
    public static ApiResponse<Void> error(String message) {
        return new ApiResponse<>(false, message, null);
    }

    // ---------- GETTERS & SETTERS ---------- //

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // ---------- TO STRING ---------- //


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApiResponse{");
        sb.append("success=").append(success);
        sb.append(", message='").append(message).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}