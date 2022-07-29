
package com.latin.producto.Controller;

import com.latin.colegio.Resource.Conexion;
import com.latin.producto.Model.Producto;
import com.latin.producto.Service.IProducto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public class ProductoController implements IProducto{

    private PreparedStatement ps;
    private ResultSet rs;
    private final Conexion conexion=new Conexion();
    private String query;
    
    @Override
    public ArrayList<Producto> list() {
        ArrayList<Producto> list=new ArrayList();
        query = "SELECT * FROM PRODUCTO";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Producto p=new Producto();
                p.setCodigo(rs.getShort("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setCantidad(rs.getShort("cantidad"));
                p.setLugarProduccion(rs.getString("lugar_produccion"));
                p.setFechaIngreso(rs.getDate("fecha_ingreso"));
                list.add(p);
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
        return list;
    }

    @Override
    public void create(Producto producto) {
        query = "INSERT INTO PRODUCTO(nombre,cantidad,lugar_produccion,fecha_ingreso) "
                + "VALUES(?,?,?,?)";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getCantidad());
            ps.setString(3, producto.getLugarProduccion());
            ps.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
            //System.out.println("Inserted...");
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public void update(Producto producto) {
        query = "UPDATE PRODUCTO SET NOMBRE=?, CANTIDAD=?, LUGAR_PRODUCCION=? "
                + "WHERE CODIGO=?";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getCantidad());
            ps.setString(3, producto.getLugarProduccion());
            ps.setInt(4, producto.getCodigo());
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public void delete(short codigo) {
        query = "DELETE FROM PRODUCTO WHERE CODIGO="+codigo+"";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }
    
}
