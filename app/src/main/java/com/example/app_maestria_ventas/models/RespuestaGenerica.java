package com.example.app_maestria_ventas.models;

import java.util.List;

public class RespuestaGenerica<T> {
    private boolean error;
    private String message;
    private List<T> contenido;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getContenido() {
        return contenido;
    }

    public void setContenido(List<T> contenido) {
        this.contenido = contenido;
    }
}
