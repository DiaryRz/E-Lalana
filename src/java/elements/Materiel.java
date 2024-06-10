/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diary
 */
public class Materiel {
    int idMateriel;
    String nomMateriel;
    double prix;
    int unite;
    Date dt;

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public void setDt(String dt) throws Exception {
        Date a=TransformerStrinEnDate(dt);
        this.setDt(a);
    }
    public int getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
    }

    public void setIdMateriel(String idMateriel) {
        int a=Integer.valueOf(idMateriel);
        this.setIdMateriel(a);
    }
 
    public String getNomMateriel() {
        return nomMateriel;
    }

    public void setNomMateriel(String nomMateriel) {
        this.nomMateriel = nomMateriel;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setPrix(String prix) {
     double a=Double.parseDouble(prix);
        this.setPrix(a);
    }
    public int getUnite() {
        return unite;
    }

    public void setUnite(int unite) {
        this.unite = unite;
    }
    
    public void setUnite(String unite) {
        int a=Integer.valueOf(unite);
        this.setUnite(a);
    }
    
        public Materiel(){};
    public Materiel(int idMateriel, String nomMateriel, double prix, int unite,Date dt) {
        this.setIdMateriel(idMateriel);
        this.setNomMateriel(nomMateriel);
        this.setPrix(prix);
        this.setUnite(unite);
        this.setDt(dt);
    }
    public Materiel(String nomMateriel, double prix, int unite,Date dt) {
        this.setNomMateriel(nomMateriel);
        this.setPrix(prix);
        this.setUnite(unite);
        this.setDt(dt);
    }
    public Materiel(String nomMateriel, String prix, String unite,Date dt) {
        this.setNomMateriel(nomMateriel);
        this.setPrix(prix);
        this.setUnite(unite);
        this.setDt(dt);
    }
    public Materiel(String nomMateriel, String prix, String unite) {
        this.setNomMateriel(nomMateriel);
        this.setPrix(prix);
        this.setUnite(unite);
    }
    public Materiel(String idMateriel, String nomMateriel, String prix, String unite,String dt) throws Exception {
        this.setIdMateriel(idMateriel);
        this.setNomMateriel(nomMateriel);
        this.setPrix(prix);
        this.setUnite(unite);
        this.setDt(dt);
        
    }

    public Materiel(String nomMateriel, String prix, String unite,String dt) throws Exception {
        this.setNomMateriel(nomMateriel);
        this.setPrix(prix);
        this.setUnite(unite);
        this.setDt(dt);
        
    }
    
    public Materiel(int idMateriel,String nomMateriel) throws Exception {
        this.setNomMateriel(nomMateriel);
        this.setIdMateriel(idMateriel);
        
    }
    
    public static Date TransformerStrinEnDate(String dateStr)throws Exception{
        java.sql.Date date = null;

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = inputFormat.parse(dateStr);
            
            date = new java.sql.Date(utilDate.getTime());
        } catch (ParseException | IllegalArgumentException e) {
            System.out.println(e);
        }

        return date;
    }

    public void InsertMateriel(Connection conn) throws Exception{
        Connection c;
        if(conn!=null){
            c=conn;
        }else{
            c=Connexion.Connect();
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO MaterielTsotra (nomMateriel) values (?)";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1,this.getNomMateriel());

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
    
    public void InsertPrixMateriel(Connection conn) throws Exception{
        Connection c;
        if(conn!=null){
            c=conn;
        }else{
            c=Connexion.Connect();
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO MaterielPrix (idMateriel,prix,idUnite) values (?,?,?)";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,this.getIdMateriel());
            preparedStatement.setDouble(2,this.getPrix());
            preparedStatement.setDouble(3,this.getUnite());

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
    
    public List<Materiel> SelectMateriel()throws Exception{
        List<Materiel> materiel = new ArrayList<Materiel>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Materiel");
        while (rs.next()) {
           materiel.add(new Materiel(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getDate(5)));
        }
        c.close();
        return materiel;
    }
    
    public List<Materiel> SelectMaterielTsotra()throws Exception{
        List<Materiel> materiel = new ArrayList<Materiel>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM MaterielTsotra");
        while (rs.next()) {
           materiel.add(new Materiel(rs.getInt(1),rs.getString(2)));
        }
        c.close();
        return materiel;
    }
}

