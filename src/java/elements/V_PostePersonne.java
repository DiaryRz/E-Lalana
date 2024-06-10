package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class V_PostePersonne {
    int IdPostePersonne;
    int IdPersonne;
    String NomPersonne;
    int IdPersonnel;
    String NomPersonnel;
    double TauxHoraire;
    Date DateNaissance;
    Date DateEmbauche;
    double SalaireBase;
    double Anciennete;

    public int getIdPostePersonne() {
        return IdPostePersonne;
    }

    public void setIdPostePersonne(int IdPostePersonne) {
        this.IdPostePersonne = IdPostePersonne;
    }

    public int getIdPersonne() {
        return IdPersonne;
    }

    public void setIdPersonne(int IdPersonne) {
        this.IdPersonne = IdPersonne;
    }

    public String getNomPersonne() {
        return NomPersonne;
    }

    public void setNomPersonne(String NomPersonne) {
        this.NomPersonne = NomPersonne;
    }

    public int getIdPersonnel() {
        return IdPersonnel;
    }

    public void setIdPersonnel(int IdPersonnel) {
        this.IdPersonnel = IdPersonnel;
    }

    public String getNomPersonnel() {
        return NomPersonnel;
    }

    public void setNomPersonnel(String NomPersonnel) {
        this.NomPersonnel = NomPersonnel;
    }

    public double getTauxHoraire() {
        return TauxHoraire;
    }

    public void setTauxHoraire(double TauxHoraire) {
        this.TauxHoraire = TauxHoraire;
    }

    public Date getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(Date DateNaissance) {
        this.DateNaissance = DateNaissance;
    }

    public Date getDateEmbauche() {
        return DateEmbauche;
    }

    public void setDateEmbauche(Date DateEmbauche) {
        this.DateEmbauche = DateEmbauche;
    }

    public double getSalaireBase() {
        return SalaireBase;
    }

    public void setSalaireBase(double SalaireBase) {
        this.SalaireBase = SalaireBase;
    }

    public double getAnciennete() {
        return Anciennete;
    }

    public void setAnciennete(double Anciennete) {
        this.Anciennete = Anciennete;
    }

    public V_PostePersonne() {
    }

    public V_PostePersonne(int IdPostePersonne, int IdPersonne, String NomPersonne, int IdPersonnel, String NomPersonnel, double TauxHoraire, Date DateNaissance, Date DateEmbauche, double SalaireBase, double Anciennete) {
        this.setIdPostePersonne(IdPostePersonne);
        this.setIdPersonne(IdPersonne);
        this.setNomPersonne(NomPersonne);
        this.setIdPersonnel(IdPersonnel);
        this.setNomPersonnel(NomPersonnel);
        this.setTauxHoraire(TauxHoraire);
        this.setDateNaissance(DateNaissance);
        this.setDateEmbauche(DateEmbauche);
        this.setSalaireBase(SalaireBase);
        this.setAnciennete(Anciennete);
    }
    
    public V_PostePersonne(int IdPersonne, String NomPersonne, int IdPersonnel, String NomPersonnel, double TauxHoraire, Date DateNaissance, Date DateEmbauche, double SalaireBase, double Anciennete) {
        this.setIdPersonne(IdPersonne);
        this.setNomPersonne(NomPersonne);
        this.setIdPersonnel(IdPersonnel);
        this.setNomPersonnel(NomPersonnel);
        this.setTauxHoraire(TauxHoraire);
        this.setDateNaissance(DateNaissance);
        this.setDateEmbauche(DateEmbauche);
        this.setSalaireBase(SalaireBase);
        this.setAnciennete(Anciennete);
    }
    
    public List<V_PostePersonne> SelectV_PostePersonne() throws Exception{
        Connection c = Connexion.Connect();
        List<V_PostePersonne> mat = new ArrayList<V_PostePersonne>();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from V_PostePersonne");
            while(rs.next()){
                mat.add(new V_PostePersonne(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDouble(6),rs.getDate(7),rs.getDate(8),rs.getDouble(9),rs.getDouble(10)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
}
