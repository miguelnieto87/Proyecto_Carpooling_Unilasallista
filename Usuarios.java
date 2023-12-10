/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

/**
 *
 * @author Iñaki
 */
public class Usuarios extends Conexion {
    private int id;
    private String nombres;
    private String apellidos;
    private int estamento;
    private String fecha_nacimiento;
    
    public int getID(){
        return this.id;    
    }
    
    public void setID(int id){
        this.id = id;    
    }
    
    public String getNombres(){
        return this.nombres;    
    }
    
    public void setNombres(String nombres){
        this.nombres = nombres;    
    }
    
     public String getApellidos(){
        return this.apellidos;    
    }
    
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;    
    }
    
    public int getEstamento(){
        return this.estamento;    
    }
    
    public void setEstamento(int estamento){
        this.estamento = estamento;    
    }
    
    public String getFechaNacimiento(){
        return this.fecha_nacimiento;    
    }
    
    public void setFechaNacimiento(String fecha_nacimiento){
        this.fecha_nacimiento = fecha_nacimiento;    
    }
    
    public boolean insertar(){
      PreparedStatement ps;
      Connection conn;
      
      conn = getConexion();
      String sql = "INSERT INTO usuarios (nombres, apellidos,estamento,fecha_nacimiento) VALUES(?,?,?,?)";
      
       try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, this.nombres);
            ps.setString(2, this.apellidos);
            ps.setInt(3,this.estamento);
            ps.setString(4, this.fecha_nacimiento);
                 
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }      
    }
    
    public boolean actualizar(){
      PreparedStatement ps;
      Connection conn;
      
      conn = getConexion();
      String sql = "UPDATE usuarios SET nombres=?, apellidos=?,estamento=?,fecha_nacimiento=? WHERE id=?";
      
       try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, this.nombres);
            ps.setString(2, this.apellidos);
            ps.setInt(3,this.estamento);
            ps.setString(4, this.fecha_nacimiento);
            ps.setInt(5, this.id);                 
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }      
    }
    
    public boolean eliminar(){
      Connection conn;
      PreparedStatement ps;
      
      conn = getConexion();
      String sql = "DELETE FROM usuarios WHERE id=?";
       try {
            ps = conn.prepareStatement(sql);           
            ps.setInt(1, this.id);                 
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }    
      
        
    
    
    }
    
     public void cargarDatos(JTable tblDatos, String buscar){
         try{             
                PreparedStatement ps;
                Connection conn;

                conn = getConexion();
                     
                ps = conn.prepareStatement("SELECT * FROM usuarios WHERE nombres LIKE'%"+ buscar + "%'");
                ResultSet rs=ps.executeQuery();
                DefaultTableModel tm = (DefaultTableModel)tblDatos.getModel();
                tm.setRowCount(0);

                while(rs.next()){
                    Object o[] = {rs.getInt("id"),rs.getString("nombres"),rs.getString("apellidos"),rs.getInt("estamento"),rs.getString("fecha_nacimiento")};
                    tm.addRow(o);
                }


            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Error! "+e);
            }   
             
    
    
    
    
    }

  
    

        
    
    
}
