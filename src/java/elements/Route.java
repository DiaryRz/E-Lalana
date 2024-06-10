package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Route {
    int idRoute;
    int idType;
    String nomRoute;
    int identifiant;

    public Route() {}
    
    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public void setIdRoute(String idRoute) {
        int a=Integer.parseInt(idRoute);
        this.setIdRoute(a);
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setIdType(String idType) {
        int a=Integer.parseInt(idType);
        this.setIdType(a);
    }

    public String getNomRoute() {
        return nomRoute;
    }

    public void setNomRoute(String nomRoute) {
        this.nomRoute = nomRoute;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int Identifiant) {
        this.identifiant = Identifiant;
    }

    public void setIdentifiant(String Identifiant) {
        int a=Integer.parseInt(Identifiant);
        this.setIdentifiant(a);
    }
    
    public Route(int idRoute,int idType,String nomRoute,int identifiant) {
        this.setIdRoute(idRoute);
        this.setIdType(idType);
        this.setNomRoute(nomRoute);
        this.setIdentifiant(identifiant);
    }
    
    public Route(String idRoute,String idType,String nomRoute,String identifiant) {
        this.setIdRoute(idRoute);
        this.setIdType(idType);
        this.setNomRoute(nomRoute);
        this.setIdentifiant(identifiant);
    }
    
    public Route(String nomRoute,String idType) {
        this.setIdType(idType);
        this.setNomRoute(nomRoute);
    }
    
    public void InsertRoute(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO Route (idType,nomRoute) values (?,?) ";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,this.getIdType());
            preparedStatement.setString(2,this.getNomRoute());
            //preparedStatement.setInt(7,this.getIdentifiant());

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


    public List<Route> SelectRoute() throws Exception{
        Connection c = Connexion.Connect();
        List<Route> mat = new ArrayList<Route>();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from Route");
            while(rs.next()){
                mat.add(new Route(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4)));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    
    public Route SelectRouteParIdRoute(Connection conn, int idRoute) throws Exception{
        Connection c = Connexion.Connect();
        Route mat = new Route();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from Route where idRoute="+idRoute);
            while(rs.next()){
                 mat= new Route(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    
    
}
