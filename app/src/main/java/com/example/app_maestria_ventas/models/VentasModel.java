package com.example.app_maestria_ventas.models;

import java.util.Date;

public class VentasModel {

    private String id_detalleventa;
    private String id_comprobante;
    private String serie;
    private String codigo;
    private String id_cliente;
    private String id_producto;
    private String cantidad;
    private String precio_venta;
    private String total;
    private String subtotal;
    private String igv;

    public VentasModel() { }

    public String getId_detalleventa() {
        return id_detalleventa;
    }

    public void setId_detalleventa(String id_detalleventa) {
        this.id_detalleventa = id_detalleventa;
    }

    public String getId_comprobante() {
        return id_comprobante;
    }

    public void setId_comprobante(String id_comprobante) {
        this.id_comprobante = id_comprobante;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(String precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getIgv() {
        return igv;
    }

    public void setIgv(String igv) {
        this.igv = igv;
    }
}
