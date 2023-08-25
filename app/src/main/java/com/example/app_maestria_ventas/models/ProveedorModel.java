package com.example.app_maestria_ventas.models;

public class ProveedorModel {
    private String id_proveedor;
    private String ruc_proveedor;
    private String razon_social;
    private String direccion_fiscal;
    private String estado_empresa;
    private String condicion_empresa;


    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getRuc_proveedor() {
        return ruc_proveedor;
    }

    public void setRuc_proveedor(String ruc_proveedor) {
        this.ruc_proveedor = ruc_proveedor;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDireccion_fiscal() {
        return direccion_fiscal;
    }

    public void setDireccion_fiscal(String direccion_fiscal) {
        this.direccion_fiscal = direccion_fiscal;
    }

    public String getEstado_empresa() {
        return estado_empresa;
    }

    public void setEstado_empresa(String estado_empresa) {
        this.estado_empresa = estado_empresa;
    }

    public String getCondicion_empresa() {
        return condicion_empresa;
    }

    public void setCondicion_empresa(String condicion_empresa) {
        this.condicion_empresa = condicion_empresa;
    }
}
