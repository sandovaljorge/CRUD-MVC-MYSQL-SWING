package com.latin.producto.Service;

import com.latin.producto.Model.Producto;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public interface IProducto {
    public ArrayList<Producto> list();
    public void create(Producto producto);
    public void update(Producto producto);
    public void delete(short codigo);
}
