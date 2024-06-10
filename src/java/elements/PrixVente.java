package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrixVente {
    int IdPrixVente;
    int IdRouteQualite;
    double Prix;

    public int getIdPrixVente() {
        return IdPrixVente;
    }

    public void setIdPrixVente(int IdPrixVente) {
        this.IdPrixVente = IdPrixVente;
    }

   public int getIdRouteQualite() {
        return IdRouteQualite;
    }

    public void setIdRouteQualite(int IdRouteQualite) {
        this.IdRouteQualite = IdRouteQualite;
    }
    
    public void setIdRouteQualite(String IdRouteFabrique) {
        int a = Integer.parseInt(IdRouteFabrique);
        this.setIdRouteQualite(a);
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }
    
    public void setPrix(String Prix) {
        double a = Double.parseDouble(Prix);
        this.setPrix(a);
    }

    public PrixVente() {
    }

    public PrixVente(int IdPrixVente, int IdRouteFabrique, double Prix) {
        this.setIdPrixVente(IdPrixVente);
        this.setIdRouteQualite(IdRouteFabrique);
        this.setPrix(Prix);
    }
    
    public PrixVente(int IdRouteFabrique, double Prix) {
        this.setIdRouteQualite(IdRouteFabrique);
        this.setPrix(Prix);
    }
    
    public PrixVente(String IdRouteFabrique,String Prix) {
       this.setIdRouteQualite(IdRouteFabrique);
        this.setPrix(Prix);
    }
    
    public void InsertPrixVente(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO prixVente (idroutequalite,prix) values (?,?) ";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,this.getIdRouteQualite());
            preparedStatement.setDouble(2,this.getPrix());

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
    
    public PrixVente SelectPrixVente(int idRouteQualite)throws Exception{
        PrixVente liste = new PrixVente();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM PrixVente where idroutequalite="+idRouteQualite);
        while (rs.next()) {
           liste = new PrixVente(rs.getInt(1),rs.getInt(2),rs.getDouble(3));
        }
        c.close();
        return liste;
    }
    
}
