package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    int IdStock;
    int IdMateriel;
    double Quantite;
    Date DateStock;
    double PrixEnStock;

    public int getIdStock() {
        return IdStock;
    }

    public void setIdStock(int IdStock) {
        this.IdStock = IdStock;
    }

    public int getIdMateriel() {
        return IdMateriel;
    }

    public void setIdMateriel(int IdMateriel) {
        this.IdMateriel = IdMateriel;
    }
    
    public void setIdMateriel(String IdMateriel) {
        int a = Integer.valueOf(IdMateriel);
        this.setIdMateriel(a);
    }

    public double getQuantite() {
        return Quantite;
    }

    public void setQuantite(double Quantite) {
        this.Quantite = Quantite;
    }
    
    public void setQuantite(String Quantite) {
        double a = Double.parseDouble(Quantite);
        this.setQuantite(a);
    }

    public Date getDateStock() {
        return DateStock;
    }

    public void setDateStock(Date DateStock) {
        this.DateStock = DateStock;
    }

    public double getPrixEnStock() {
        return PrixEnStock;
    }

    public void setPrixEnStock(double PrixEnStock) {
        this.PrixEnStock = PrixEnStock;
    }

    public Stock() {
    }

    public Stock(int IdStock, int IdMateriel, double Quantite, Date DateStock, double PrixEnStock) {
        this.setIdStock(IdStock);
        this.setIdMateriel(IdMateriel);
        this.setQuantite(Quantite);
        this.setDateStock(DateStock);
        this.setPrixEnStock(PrixEnStock);
    }
    
    public Stock(int IdMateriel, double Quantite, Date DateStock, double PrixEnStock) {
        this.setIdMateriel(IdMateriel);
        this.setQuantite(Quantite);
        this.setDateStock(DateStock);
        this.setPrixEnStock(PrixEnStock);
    }
    
    public Stock(int IdMateriel, double Quantite,double PrixEnStock) {
        this.setIdMateriel(IdMateriel);
        this.setQuantite(Quantite);
        this.setPrixEnStock(PrixEnStock);
    }
    
    public Stock(int IdMateriel, double Quantite) {
        this.setIdMateriel(IdMateriel);
        this.setQuantite(Quantite);
    }
    
    public Stock(String IdMateriel, String Quantite) {
        this.setIdMateriel(IdMateriel);
        this.setQuantite(Quantite);
    }
    
    public void AjouterStock(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO Stock (idmateriel,quantite,prixenstock) values (?,?,?) ";
            preparedStatement = c.prepareStatement(sql);
            Materiel mat = this.SelectMaterielParIdMateriel(this.getIdMateriel());
            preparedStatement.setInt(1,this.getIdMateriel());
            preparedStatement.setDouble(2,this.getQuantite());
            preparedStatement.setDouble(3,mat.getPrix());

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
    
    public Materiel SelectMaterielParIdMateriel(int idMateriel) throws Exception{
        Connection c = Connexion.Connect();
        Materiel mat = new Materiel();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from Materiel where idMateriel = "+ idMateriel +" order by dateprixMateriel desc limit 1");
            while(rs.next()){
                mat=new Materiel(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getDate(5));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    public Stock QuantiteStockParMateriel(int IdMateriel) throws Exception{
        Connection c = Connexion.Connect();
        Stock mat = new Stock();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from ResteStock where idMateriel="+IdMateriel);
            while(rs.next()){
                mat= new Stock(rs.getInt(1),rs.getDouble(2));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    public void EnleverStock(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO Stock (idmateriel,quantite,prixenstock) values (?,?,?) ";
            preparedStatement = c.prepareStatement(sql);
            Materiel mat = this.SelectMaterielParIdMateriel(this.getIdMateriel());
            preparedStatement.setInt(1,this.getIdMateriel());
            preparedStatement.setDouble(2,-this.getQuantite());
            preparedStatement.setDouble(3,mat.getPrix());

            c.setAutoCommit(false);
            
            preparedStatement.executeUpdate();
           
            c.commit();
            
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            c.rollback();
        }
    }
    
    
}
