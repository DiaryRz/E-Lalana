package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

public class RouteFabrique {
    int IdRouteFabrique;
    int IdRouteQualite;
    Date DateFabrication;
    double Nombre;

    public int getIdRouteFabrique() {
        return IdRouteFabrique;
    }

    public void setIdRouteFabrique(int IdRouteFabrique) {
        this.IdRouteFabrique = IdRouteFabrique;
    }

    public int getIdRouteQualite() {
        return IdRouteQualite;
    }

    public void setIdRouteQualite(int IdRouteQualite) {
        this.IdRouteQualite = IdRouteQualite;
    }
    
    public void setIdRouteQualite(String IdRouteQualite) {
        int a = Integer.parseInt(IdRouteQualite);
        this.setIdRouteQualite(a);
    }

    public Date getDateFabrication() {
        return DateFabrication;
    }

    public void setDateFabrication(Date DateFabrication) {
        this.DateFabrication = DateFabrication;
    }

    public double getNombre() {
        return Nombre;
    }

    public void setNombre(double Nombre) {
        this.Nombre = Nombre;
    }
    
    public void setNombre(String Nombre) {
        double a = Double.parseDouble(Nombre);
        this.setNombre(a);
    }

    public RouteFabrique() {
    }

    public RouteFabrique(int IdRouteFabrique, int IdRouteQualite, Date DateFabrication, double Nombre) {
        this.setIdRouteFabrique(IdRouteFabrique);
        this.setIdRouteQualite(IdRouteQualite);
        this.setDateFabrication(DateFabrication);
        this.setNombre(Nombre);
    }
    
    public RouteFabrique(int IdRouteQualite, Date DateFabrication, double Nombre) {
        this.setIdRouteQualite(IdRouteQualite);
        this.setDateFabrication(DateFabrication);
        this.setNombre(Nombre);
    }
    
    public RouteFabrique(String IdRouteQualite, String Nombre) {
        this.setIdRouteQualite(IdRouteQualite);
        this.setNombre(Nombre);
    }
    
    public RouteFabrique(int IdRouteQualite, double Nombre) {
        this.setIdRouteQualite(IdRouteQualite);
        this.setNombre(Nombre);
    }
    
    
    public void InsertRouteFabrique(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            c.setAutoCommit(false);
            String sql = "INSERT INTO RouteFabrique ( idroutequalite,nombre) values (?,?) ";
            V_RouteQualiteMateriel appel = new V_RouteQualiteMateriel();
            List<V_RouteQualiteMateriel> ListeMaterielNecessaire = appel.QuantiteMaterielNecessaire(this.getIdRouteQualite());
            Stock appelStock = new Stock();
            int i = 0;
            for(i=0 ; i < ListeMaterielNecessaire.size() ; i++){
                Stock stock = appelStock.QuantiteStockParMateriel(ListeMaterielNecessaire.get(i).getIdMateriel());
                double StockNecessaire = ListeMaterielNecessaire.get(i).getQuantiteParMettreCarre() * this.getNombre();
                if(stock.getQuantite() < StockNecessaire ){
                    throw new Exception("Stock insuffisant");
                }else{
                    Stock appStock = new Stock(stock.getIdMateriel(),StockNecessaire,stock.getPrixEnStock());
                    appStock.EnleverStock(c);
                }
            }
                    
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,this.getIdRouteQualite());
            preparedStatement.setDouble(2,this.getNombre());
            
            preparedStatement.executeUpdate();
           
            c.commit();
            
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            c.rollback();
            throw e;
            
        }finally{
            c.close();
        }
    }
    
    public void EnleverStockProduit(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO routeFabrique (idroutequalite,nombre) values (?,?) ";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,this.getIdRouteQualite());
            preparedStatement.setDouble(2,-this.getNombre());
          
            c.setAutoCommit(false);
            
            preparedStatement.executeUpdate();
           
            //c.commit();
            
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            c.rollback();
        }
    }
    
    
    
}
