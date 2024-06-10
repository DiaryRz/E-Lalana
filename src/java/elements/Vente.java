package elements;

import connexion.Connexion;
import static elements.Materiel.TransformerStrinEnDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class Vente {

    int idVente;
    int idRouteQualite;
    int idClient;
    Date DateVente;
    double prix;

    public Vente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getIdVente() {
        return idVente;
    }
    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }
    public int getIdRouteQualite() {
        return idRouteQualite;
    }
    public void setIdRouteQualite(int idRouteQualite) {
        this.idRouteQualite = idRouteQualite;
    }
    public void setIdRouteQualite(String idRouteQualite) {
        int a = Integer.parseInt(idRouteQualite);
        this.setIdRouteQualite(a);
    }

    
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public void setIdClient(String idRouteQualite) {
        int a = Integer.parseInt(idRouteQualite);
        this.setIdClient(a);
    }
    public Date getDateVente() {
        return DateVente;
    }
    public void setDateVente(Date dateVente) {
        DateVente = dateVente;
    }
    public void setDateVente(String dateVente) throws Exception{
        Date a=TransformerStrinEnDate(dateVente);
        this.setDateVente(a);
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public void setPrix(String idRouteQualite) {
        double a =  Double.parseDouble(idRouteQualite);
        this.prix = a;
    }
    
    public Vente(int idVente, int idRouteQualite, int idClient,double prix) {
        this.setIdVente(idVente);
        this.setIdRouteQualite(idRouteQualite);
        this.setIdClient(idClient);
        this.setPrix(prix);
    }
    
    public Vente(int idVente, int idRouteQualite, int idClient, Date dateVente ,double prix) {
        this.setIdVente(idVente);
        this.setIdRouteQualite(idRouteQualite);
        this.setIdClient(idClient);
        this.setPrix(prix);
        this.setDateVente(dateVente);
    }
    
    public Vente(String idRouteQualite, String idClient) {
        this.setIdRouteQualite(idRouteQualite);
        this.setIdClient(idClient);
    }
    
    
    public void InsertVente(Connection conn) throws Exception{
        Connection c;
        if(conn!=null){
            c=conn;
        }else{
            c=Connexion.Connect();
        }
        PreparedStatement preparedStatement = null;
        try{
            V_RouteFabrique appel = new V_RouteFabrique();
            V_RouteFabrique stock = appel.StockProduit(this.getIdRouteQualite());
            c.setAutoCommit(false);
            if(stock.getNombre() > 0){
                String sql = "INSERT INTO Vente (idRouteQualite,idClient) values (?,?)";
                preparedStatement = c.prepareStatement(sql);
                preparedStatement.setInt(1,this.getIdRouteQualite());
                preparedStatement.setInt(2,this.getIdClient());

                
                RouteFabrique app = new RouteFabrique(this.getIdRouteQualite(),1);
                app.EnleverStockProduit(c);
                
                preparedStatement.executeUpdate();

                c.commit();

                preparedStatement.close();
            }else{
                throw new Exception("Stock de produit insuffisante");
            }
            
        }catch(Exception e){
            e.printStackTrace();
            c.rollback();
            throw e;
        }finally{
            c.close();
        }
    }
}