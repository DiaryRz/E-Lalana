package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RouteQualite {
    int IdRouteQualite;
    int idRoute;
    int idQualite;
    int Identifiant;
    String NomRouteQualite;
    
    double PrixMateriel;

    public double getPrixMateriel() {
        return PrixMateriel;
    }

    public void setPrixMateriel(double PrixMateriel) {
        this.PrixMateriel = PrixMateriel;
    }

    public String getNomRouteQualite() {
        return NomRouteQualite;
    }

    public void setNomRouteQualite(String NomRouteQualite) {
        this.NomRouteQualite = NomRouteQualite;
    }

    public int getIdRouteQualite() {
        return IdRouteQualite;
    }

    public void setIdRouteQualite(int IdRouteQualite) {
        this.IdRouteQualite = IdRouteQualite;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }
    
    public void setIdRoute(String idRoute) {
        int a = Integer.valueOf(idRoute);
        this.setIdRoute(a);
    }

    public int getIdQualite() {
        return idQualite;
    }

    public void setIdQualite(int idQualite) {
        this.idQualite = idQualite;
    }
    
    public void setIdQualite(String idQualite) {
        int val = Integer.valueOf(idQualite);
        this.setIdQualite(val);
    }

    public int getIdentifiant() {
        return Identifiant;
    }

    public void setIdentifiant(int Identifiant) {
        this.Identifiant = Identifiant;
    }

    public RouteQualite() {
    }

    public RouteQualite(int IdRouteQualite, int idRoute, int idQualite, int Identifiant , String nom) {
        this.setIdRouteQualite(IdRouteQualite);
        this.setIdRoute(idRoute);
        this.setIdQualite(idQualite);
        this.setIdentifiant(Identifiant);
        this.setNomRouteQualite(nom);
    }
    
    public RouteQualite(int IdRouteQualite, double Prix) {
        this.setIdRouteQualite(IdRouteQualite);
       this.setPrixMateriel(Prix);
    }
    
    public RouteQualite(int idRoute, int idQualite, int Identifiant) {
        this.setIdRoute(idRoute);
        this.setIdQualite(idQualite);
        this.setIdentifiant(Identifiant);
    }
    
    public RouteQualite(int idRoute, int idQualite) {
        this.setIdRoute(idRoute);
        this.setIdQualite(idQualite);
    }
    
    public RouteQualite(String idRoute, String idQualite) {
        this.setIdRoute(idRoute);
        this.setIdQualite(idQualite);
    }
    
    public void InsertRouteQualite(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO routeQualite (idroute,idqualite,nomroutequalite) values (?,?,?) ";
            Route appelRoute = new Route();
            Route anaranaRoute = appelRoute.SelectRouteParIdRoute(null, this.getIdRoute());
            Qualite appelQualite = new Qualite(); 
            Qualite anaranaQualite = appelQualite.SelectQualiteParIdQualite(this.getIdQualite()) ;
            String NomRoute = anaranaRoute.getNomRoute()+"-"+anaranaQualite.getNomQualite() ;
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,this.getIdRoute());
            preparedStatement.setInt(2,this.getIdQualite());
            preparedStatement.setString(3,NomRoute);
          
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
    
    public List<RouteQualite> SelectRouteQualite() throws Exception{
        Connection c = Connexion.Connect();
        List<RouteQualite> mat = new ArrayList<RouteQualite>();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from RouteQualite");
            while(rs.next()){
                mat.add(new RouteQualite(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5)));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    public RouteQualite SelectRouteQualiteParId(int id) throws Exception{
        Connection c = Connexion.Connect();
        RouteQualite mat = new RouteQualite();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from RouteQualite where idRouteQualite = "+id);
            while(rs.next()){
                mat=new RouteQualite(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    
    public RouteQualite PrixMaterielParRoute(int idRouteQualite) throws Exception{
        Connection c = Connexion.Connect();
        RouteQualite mat = new RouteQualite();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from PrixMaterielParRouteQualite where idroutequalite="+idRouteQualite);
            while(rs.next()){
                mat=new RouteQualite(rs.getInt(1),rs.getDouble(2));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    public RouteQualite PrixDeRevient(int IdRouteQualite) throws Exception{
        SalaireParPersonnel appel = new SalaireParPersonnel();
        SalaireParPersonnel mainDOeuvre = appel.SelectMainDOeuvreParRouteQualite(IdRouteQualite);
        RouteQualite materiel = this.PrixMaterielParRoute(IdRouteQualite);
        double prixRevient = mainDOeuvre.getSalaireParPersonnel() + materiel.getPrixMateriel();
        RouteQualite route = new RouteQualite(IdRouteQualite,prixRevient);
        return route;
    }
    
   
    
}
