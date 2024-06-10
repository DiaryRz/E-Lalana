package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class V_RouteFabrique {
    int IdRouteFabrique;
    double Nombre;

    public int getIdRouteFabrique() {
        return IdRouteFabrique;
    }

    public void setIdRouteFabrique(int IdRouteFabrique) {
        this.IdRouteFabrique = IdRouteFabrique;
    }
    
    public void setIdRouteFabrique(String IdRouteFabrique) {
        int a = Integer.parseInt(IdRouteFabrique);
        this.setIdRouteFabrique(a);
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

    public V_RouteFabrique() {}

    public V_RouteFabrique(int IdRouteFabrique, double Nombre) {
        this.setIdRouteFabrique(IdRouteFabrique);
        this.setNombre(Nombre);
    }
    
    public V_RouteFabrique(String IdRouteFabrique, String Nombre) {
        this.setIdRouteFabrique(IdRouteFabrique);
        this.setNombre(Nombre);
    }
    
    public V_RouteFabrique StockProduit(int idRoute) throws Exception{
        Connection c = Connexion.Connect();
        V_RouteFabrique mat = new V_RouteFabrique();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from V_RouteFabrique where idroutequalite="+idRoute);
            while(rs.next()){
                mat=new V_RouteFabrique(rs.getInt(1),rs.getDouble(2));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    
}
