package com.example.examenT2;


public class JsendResponse<T> {

        private String status;
        private T data;
        private String message;

        // Constructor para respuestas exitosas con data
        public JsendResponse(String status, T data) {
            this.status = status;
            this.data = data;
        }

        // Constructor para respuestas con mensaje (éxito o error)
        public JsendResponse(String status, String message) {
            this.status = status;
            this.message = message;
        }

        // Getters y Setters
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
