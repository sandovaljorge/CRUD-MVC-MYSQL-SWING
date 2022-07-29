package com.latin.producto.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Sandoval
 */
public class Producto {
    private int codigo;
    private String nombre;
    private int cantidad;
    private String lugarProduccion;
    private Date fechaIngreso;
    private String date;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getLugarProduccion() {
        return lugarProduccion;
    }

    public void setLugarProduccion(String lugarProduccion) {
        this.lugarProduccion = lugarProduccion;
    }

    public Date getFechaIngreso() {
        this.date=new SimpleDateFormat("yyyy-MM-dd").format(fechaIngreso);
        return fechaIngreso=java.sql.Date.valueOf(this.date);
    }
    
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
