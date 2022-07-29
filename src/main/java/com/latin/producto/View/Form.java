package com.latin.producto.View;

import com.latin.producto.Controller.ProductoController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import com.latin.producto.Model.Producto;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Sandoval
 */
public class Form extends JFrame{
    private final JPanel panel=new JPanel();
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtLugar;
    private JTable table=new JTable();
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    
    public Form(){
        setSize(500,400);
        setTitle("Formulario de Productos");
        setLocationRelativeTo(null);
        init();        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void init(){
        
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
        this.getContentPane().add(panel);

        initLabel();
        initTextFiel();
        initButton();
        initTable();
    }
    
    private void initLabel(){
        JLabel lb1=new JLabel("Nombre: ");
        lb1.setBounds(20, 30, 65, 10);
        panel.add(lb1);
        
        JLabel lb1Cantidad=new JLabel("Cantidad: ");
        lb1Cantidad.setBounds(280, 30, 80, 10);
        panel.add(lb1Cantidad);
        
        JLabel lb1Lugar=new JLabel("Lugar de Producción: ");
        lb1Lugar.setBounds(20, 70, 130, 10);
        panel.add(lb1Lugar);
    }
    
    private void initTextFiel(){
        txtNombre=new JTextField();
        txtNombre.setBounds(80, 25, 180, 20);
        panel.add(txtNombre);        
        txtCantidad=new JTextField();
        txtCantidad.setText("1");
        txtCantidad.setBounds(340, 25, 100, 20);
        panel.add(txtCantidad);        
        txtLugar=new JTextField();
        txtLugar.setBounds(165, 63, 275, 25);
        panel.add(txtLugar);
    }
    
    private void initButton(){
        JButton btn=new JButton();
        btn.setText("Guardar");
        btn.setBounds(20, 300, 85, 30);
        btn.addActionListener(eventInsert());
        panel.add(btn);
        
        JButton btnUpdate=new JButton();
        btnUpdate.setText("Actualizar");
        btnUpdate.setBounds(125, 300, 100, 30);
        //btnUpdate.addActionListener(eventInsert());
        panel.add(btnUpdate);
        
        JButton btnDelete=new JButton();
        btnDelete.setText("Eliminar");
        btnDelete.setBounds(245, 300, 100, 30);
        //btnUpdate.addActionListener(eventInsert());
        panel.add(btnDelete);
        
        JButton btnSalir=new JButton();
        btnSalir.setText("Salir");
        btnSalir.setBounds(370, 300, 85, 30);
        btnSalir.addActionListener(eventExit());
        panel.add(btnSalir);
    }
    
    private ActionListener eventInsert(){
        ActionListener event=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txtNombre.getText().equals("") && !txtLugar.getText().equals("")){
                    ProductoController pController=new ProductoController();
                    Producto p=new Producto();
                    p.setNombre(txtNombre.getText());
                    p.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    p.setLugarProduccion(txtLugar.getText());
                    pController.create(p);
                    cleanTextField();
                }else{
                    System.out.println("Campos obligatorio");
                }                
            }
        };
        return event;
    }
    
    private ActionListener eventExit(){
        ActionListener event=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        return event;
    }
    private ActionListener eventUpdate(){
        ActionListener event=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        };
        return event;
    }
    
    private void tableMouseClicked(java.awt.event.MouseEvent evt){
        int row = table.getSelectedRow();
        txtNombre.setText(table.getValueAt(row, 1).toString());
        txtCantidad.setText(table.getValueAt(row, 2).toString());
        txtLugar.setText(table.getValueAt(row, 3).toString());
    }
    
    private void cleanTextField(){
        txtNombre.setText("");
        txtCantidad.setText("1");
        txtLugar.setText("");
        //initTable();
    }
    
    private void initTable(){
        table =new JTable();
        table.setBounds(20, 120, 440, 100);
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event){
                tableMouseClicked(event);
            }
        });
        
        tableModel =new DefaultTableModel();
        tableModel.addColumn("");
        tableModel.addColumn("");
        tableModel.addColumn("");
        tableModel.addColumn("Lugar de Produccion");
        tableModel.addColumn("");
        Object [] obj= {"Codigo","Nombre","Cantidad","Lugar","Fecha"};
        tableModel.insertRow(0, obj);        
        ProductoController productoController=new ProductoController();
        for(Producto producto: productoController.list()){
            Object[] object={producto.getCodigo(),
                             producto.getNombre(),
                             producto.getCantidad(),
                             producto.getLugarProduccion(),
                             producto.getFechaIngreso()};
            tableModel.addRow(object);
        }
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        
        table.setModel(tableModel);
        panel.add(table);
    }
}
