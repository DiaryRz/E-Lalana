/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeTravaillant {
    int IdEmployeTravaillant;
    int IdQualite;
    int idPersonnel;
    double Salaire;
    double Surface;
    double Nombre;

    public int getIdEmployeTravaillant() {
        return IdEmployeTravaillant;
    }

    public void setIdEmployeTravaillant(int IdEmployeTravaillant) {
        this.IdEmployeTravaillant = IdEmployeTravaillant;
    }

    public int getIdQualite() {
        return IdQualite;
    }

    public void setIdQualite(int idQualite) {
        this.IdQualite = idQualite;
    }
    
    public void setIdQualite(String idQualite) {
        int a = Integer.parseInt(idQualite);
        this.IdQualite = a;
    }

    public int getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(int idPersonnel) {
        this.idPersonnel = idPersonnel;
    }
    
    public void setIdPersonnel(String idPersonnel) {
        int a = Integer.parseInt(idPersonnel);
        this.idPersonnel = a;
    }

    public double getSalaire() {
        return Salaire;
    }

    public void setSalaire(double Salaire) {
        this.Salaire = Salaire;
    }
    
    public void setSalaire(String Salaire) {
        double a =  Double.parseDouble(Salaire);
        this.Salaire = a;
    }

    public double getSurface() {
        return Surface;
    }

    public void setSurface(double Surface) {
        this.Surface = Surface;
    }
    
    public void setSurface(String Surface) {
        double a = Double.parseDouble(Surface);
        this.Surface = a;
    }

    public double getNombre() {
        return Nombre;
    }

    public void setNombre(double Nombre) {
        this.Nombre = Nombre;
    }
    
    public void setNombre(String Nombre) {
        double a = Double.parseDouble(Nombre);
        this.Nombre = a;
    }

    public EmployeTravaillant() {
    }

    public EmployeTravaillant(int IdEmployeTravaillant, int idQualite, int idPersonnel, double Salaire, double Surface, double Nombre) {
        this.setIdEmployeTravaillant(IdEmployeTravaillant);
        this.setIdQualite(idQualite);
        this.setIdPersonnel(idPersonnel);
        this.setSalaire(Salaire);
        this.setSurface(Surface);
        this.setNombre(Nombre);
    }
    
    public EmployeTravaillant(int idQualite, int idPersonnel, double Salaire, double Surface, double Nombre) {
        this.setIdQualite(idQualite);
        this.setIdPersonnel(idPersonnel);
        this.setSalaire(Salaire);
        this.setSurface(Surface);
        this.setNombre(Nombre);
    }
    
    public EmployeTravaillant(int idQualite) {
        this.setIdQualite(idQualite);
    }
    
    public EmployeTravaillant(String idQualite, String idPersonnel, String Salaire, String Surface, String Nombre) {
        this.setIdQualite(idQualite);
        this.setIdPersonnel(idPersonnel);
        this.setSalaire(Salaire);
        this.setSurface(Surface);
        this.setNombre(Nombre);
    }
    
    public void InsertEmployeTravaillant(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO EmployeTravaillant (idQualite, idPersonnel, Salaire, Surface, Nombre) values (?,?,?,?,?)";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,this.getIdQualite());
            preparedStatement.setInt(2,this.getIdPersonnel());
            preparedStatement.setDouble(3,this.getSalaire());
            preparedStatement.setDouble(4,this.getSurface());
            preparedStatement.setDouble(5,this.getNombre());

            c.setAutoCommit(false);
            
            preparedStatement.executeUpdate();
           
            c.commit();
            
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            c.rollback();
        }finally{
            c.close();
        }
    }
    
    public List<EmployeTravaillant> SelectEmployeTravaillantParQualite() throws Exception{
        Connection c = Connexion.Connect();
        List<EmployeTravaillant> mat = new ArrayList<EmployeTravaillant>();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from EmployeTravaillant where idQualite ="+this.getIdQualite());
            while(rs.next()){
                mat.add(new EmployeTravaillant(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDouble(4),rs.getDouble(5),rs.getDouble(6)));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
}
