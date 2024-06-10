package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrixMateriauxParRouteQualite {
    int IdRoute;
    String NomRoute;
    double Prix;
    int IdQualite;
    String NomQualite;
    int IdRouteQualite;
    int Identifiant;
    String NomRouteQualite;

    public int getIdRoute() {
        return IdRoute;
    }

    public void setIdRoute(int IdRoute) {
        this.IdRoute = IdRoute;
    }

    public String getNomRoute() {
        return NomRoute;
    }

    public void setNomRoute(String NomRoute) {
        this.NomRoute = NomRoute;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }

    public int getIdQualite() {
        return IdQualite;
    }

    public void setIdQualite(int IdQualite) {
        this.IdQualite = IdQualite;
    }

    public String getNomQualite() {
        return NomQualite;
    }

    public void setNomQualite(String NomQualite) {
        this.NomQualite = NomQualite;
    }

    public int getIdRouteQualite() {
        return IdRouteQualite;
    }

    public void setIdRouteQualite(int IdRouteQualite) {
        this.IdRouteQualite = IdRouteQualite;
    }

    public int getIdentifiant() {
        return Identifiant;
    }

    public void setIdentifiant(int Identifiant) {
        this.Identifiant = Identifiant;
    }

    public String getNomRouteQualite() {
        return NomRouteQualite;
    }

    public void setNomRouteQualite(String NomRouteQualite) {
        this.NomRouteQualite = NomRouteQualite;
    }

    public PrixMateriauxParRouteQualite() {
    }

    public PrixMateriauxParRouteQualite(int IdRoute, String NomRoute, double Prix, int IdQualite, String NomQualite, int IdRouteQualite, int Identifiant, String NomRouteQualite) {
        this.setIdRoute(IdRoute);
        this.setNomRoute(NomRoute);
        this.setPrix(Prix);
        this.setIdQualite(IdQualite);
        this.setNomQualite(NomQualite);
        this.setIdRouteQualite(IdRouteQualite);
        this.setIdentifiant(Identifiant);
        this.setNomRouteQualite(NomRouteQualite);
    }
    
    public PrixMateriauxParRouteQualite(String NomRoute, double Prix, int IdQualite, String NomQualite, int IdRouteQualite, int Identifiant, String NomRouteQualite) {
        this.setNomRoute(NomRoute);
        this.setPrix(Prix);
        this.setIdQualite(IdQualite);
        this.setNomQualite(NomQualite);
        this.setIdRouteQualite(IdRouteQualite);
        this.setIdentifiant(Identifiant);
        this.setNomRouteQualite(NomRouteQualite);
    }
    
    public PrixMateriauxParRouteQualite SelectRouteQualite(int idroutequalite) throws Exception{
        Connection c = Connexion.Connect();
        PrixMateriauxParRouteQualite mat = new PrixMateriauxParRouteQualite();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from PrixMateriauxParRouteQualite where idroutequalite="+idroutequalite);
            while(rs.next()){
                mat= new PrixMateriauxParRouteQualite(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    
}
