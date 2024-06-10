package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SalaireParPersonnel {
    int IdRouteQualite;
    int IdQualite;
    int IdPersonnel;
    double Salaire;
    double HeureNecessaire;
    double SalaireParPersonnel;

    public int getIdRouteQualite() {
        return IdRouteQualite;
    }

    public void setIdRouteQualite(int IdRouteQualite) {
        this.IdRouteQualite = IdRouteQualite;
    }

    public int getIdQualite() {
        return IdQualite;
    }

    public void setIdQualite(int IdQualite) {
        this.IdQualite = IdQualite;
    }

    public int getIdPersonnel() {
        return IdPersonnel;
    }

    public void setIdPersonnel(int IdPersonnel) {
        this.IdPersonnel = IdPersonnel;
    }

    public double getSalaire() {
        return Salaire;
    }

    public void setSalaire(double Salaire) {
        this.Salaire = Salaire;
    }

    public double getHeureNecessaire() {
        return HeureNecessaire;
    }

    public void setHeureNecessaire(double HeureNecessaire) {
        this.HeureNecessaire = HeureNecessaire;
    }

    public double getSalaireParPersonnel() {
        return SalaireParPersonnel;
    }

    public void setSalaireParPersonnel(double SalaireParPersonnel) {
        this.SalaireParPersonnel = SalaireParPersonnel;
    }

    public SalaireParPersonnel() {
    }

    public SalaireParPersonnel(int IdRouteQualite, int IdQualite, int IdPersonnel, double Salaire, double HeureNecessaire, double SalaireParPersonnel) {
        this.setIdRouteQualite(IdRouteQualite);
        this.setIdQualite(IdQualite);
        this.setIdPersonnel(IdPersonnel);
        this.setSalaire(Salaire);
        this.setHeureNecessaire(HeureNecessaire);
        this.setSalaireParPersonnel(SalaireParPersonnel);
    }
    
    public SalaireParPersonnel(int IdRouteQualite, int IdQualite,double SalaireParPersonnel) {
        this.setIdRouteQualite(IdRouteQualite);
        this.setIdQualite(IdQualite);
        this.setSalaireParPersonnel(SalaireParPersonnel);
    }
    
    public SalaireParPersonnel SelectMainDOeuvreParRouteQualite(int idRouteQualite) throws Exception{
        Connection c = Connexion.Connect();
        SalaireParPersonnel mat = new SalaireParPersonnel();
        Statement stat = c.createStatement();
        try{
        ResultSet rs=stat.executeQuery("select * from SalaireParRouteQualite where idroutequalite="+idRouteQualite);
        while(rs.next()){
            mat=new SalaireParPersonnel(rs.getInt(1),rs.getInt(2),rs.getDouble(3));
        }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    
    
}
