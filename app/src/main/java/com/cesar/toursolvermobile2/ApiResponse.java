package com.cesar.toursolvermobile2;

public class ApiResponse {
    private String status;
    private String message;

    // Getters y setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
