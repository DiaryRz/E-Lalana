package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class V_RouteQualiteMateriel {
    int IdMaterielType;
    int IdType;
    String NomType;
    double QuantiteParMettreCarre;
    int IdMateriel;
    String NomMateriel;
    double Prix;
    int IdUnite;
    Date DateMateriel;
    int IdQualite;
    String NomQualite;
    int IdRoute;
    String NomRoute;
    double Longueur;
    double Largeur;

    public String getNomQualite() {
        return NomQualite;
    }

    public void setNomQualite(String NomQualite) {
        this.NomQualite = NomQualite;
    }
    
    public int getIdMaterielType() {
        return IdMaterielType;
    }

    public void setIdMaterielType(int IdMaterielType) {
        this.IdMaterielType = IdMaterielType;
    }

    public int getIdType() {
        return IdType;
    }

    public void setIdType(int IdType) {
        this.IdType = IdType;
    }

    public String getNomType() {
        return NomType;
    }

    public void setNomType(String NomType) {
        this.NomType = NomType;
    }

    public double getQuantiteParMettreCarre() {
        return QuantiteParMettreCarre;
    }

    public void setQuantiteParMettreCarre(double QuantiteParMettreCarre) {
        this.QuantiteParMettreCarre = QuantiteParMettreCarre;
    }

    public int getIdMateriel() {
        return IdMateriel;
    }

    public void setIdMateriel(int IdMateriel) {
        this.IdMateriel = IdMateriel;
    }

    public String getNomMateriel() {
        return NomMateriel;
    }

    public void setNomMateriel(String NomMateriel) {
        this.NomMateriel = NomMateriel;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }

    public int getIdUnite() {
        return IdUnite;
    }

    public void setIdUnite(int IdUnite) {
        this.IdUnite = IdUnite;
    }

    public Date getDateMateriel() {
        return DateMateriel;
    }

    public void setDateMateriel(Date DateMateriel) {
        this.DateMateriel = DateMateriel;
    }

    public int getIdQualite() {
        return IdQualite;
    }

    public void setIdQualite(int IdQualite) {
        this.IdQualite = IdQualite;
    }

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

    public double getLongueur() {
        return Longueur;
    }

    public void setLongueur(double Longueur) {
        this.Longueur = Longueur;
    }

    public double getLargeur() {
        return Largeur;
    }

    public void setLargeur(double Largeur) {
        this.Largeur = Largeur;
    }

    public V_RouteQualiteMateriel() {
    }

    public V_RouteQualiteMateriel(int IdMaterielType, int IdType, String NomType, double QuantiteParMettreCarre, int IdMateriel, String NomMateriel, double Prix, int IdUnite, Date DateMateriel, int IdQualite, String NomQualite, int IdRoute, String NomRoute, double Longueur, double Largeur) {
        this.setIdMaterielType(IdMaterielType);
        this.setIdType(IdType);
        this.setNomType(NomType);
        this.setQuantiteParMettreCarre(QuantiteParMettreCarre);
        this.setIdMateriel(IdMateriel);
        this.setNomMateriel(NomMateriel);
        this.setPrix(Prix);
        this.setIdUnite(IdUnite);
        this.setDateMateriel(DateMateriel);
        this.setIdQualite(IdQualite);
        this.setIdRoute(IdRoute);
        this.setNomRoute(NomRoute);
        this.setLongueur(Longueur);
        this.setLargeur(Largeur);
        this.setNomQualite(NomQualite);
    }
    
    public V_RouteQualiteMateriel(int IdType, String NomType, double QuantiteParMettreCarre, int IdMateriel, String NomMateriel, double Prix, int IdUnite, Date DateMateriel, int IdQualite, String NomQualite, int IdRoute, String NomRoute, double Longueur, double Largeur) {
        this.setIdType(IdType);
        this.setNomType(NomType);
        this.setQuantiteParMettreCarre(QuantiteParMettreCarre);
        this.setIdMateriel(IdMateriel);
        this.setNomMateriel(NomMateriel);
        this.setPrix(Prix);
        this.setIdUnite(IdUnite);
        this.setDateMateriel(DateMateriel);
        this.setIdQualite(IdQualite);
        this.setIdRoute(IdRoute);
        this.setNomRoute(NomRoute);
        this.setLongueur(Longueur);
        this.setLargeur(Largeur);
        this.setNomQualite(NomQualite);
    }
    
    public V_RouteQualiteMateriel(int IdMateriel, String NomMateriel,int IdRoute, String NomRoute,double QuantiteParMettreCarre) {
        this.setQuantiteParMettreCarre(QuantiteParMettreCarre);
        this.setIdMateriel(IdMateriel);
        this.setNomMateriel(NomMateriel);
        this.setIdRoute(IdRoute);
        this.setNomRoute(NomRoute);
    }
    
    public V_RouteQualiteMateriel(int IdRoute) {
        this.setIdRoute(IdRoute);
    }
    
    public V_RouteQualiteMateriel(int IdRoute, String NomRoute, double prix , int idQualite ,String NomQualite) {
        this.setIdRoute(IdRoute);
        this.setNomRoute(NomRoute);
        this.setPrix(prix);
        this.setIdQualite(idQualite);
        this.setNomQualite(NomQualite);
    }
    
    public List<V_RouteQualiteMateriel> AvoirPrixEntreMinMax(String min,String max) throws Exception{
        List<V_RouteQualiteMateriel> price= new ArrayList<V_RouteQualiteMateriel>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs ;
        rs = st.executeQuery("SELECT * FROM PrixEntreDeux where prix >= "+min+" and prix <= "+max);
        while (rs.next()) {
           price.add(new V_RouteQualiteMateriel(rs.getInt(1),rs.getString(2),rs.getDouble(3) ,rs.getInt(4),rs.getString(5))); 
        }
        c.close();
        return price;
    }
    
    public List<V_RouteQualiteMateriel> QuantiteMaterielNecessaire(int idRouteQualite) throws Exception{
        List<V_RouteQualiteMateriel> price= new ArrayList<V_RouteQualiteMateriel>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs ;
        rs = st.executeQuery("SELECT * FROM QuantiteNecessairePourChaqueRoute where idroutequalite ="+idRouteQualite);
        while (rs.next()) {
           price.add(new V_RouteQualiteMateriel(rs.getInt(1),rs.getString(2),rs.getInt(3) ,rs.getString(4),rs.getDouble(5))); 
        }
        c.close();
        return price;
    }
    
    
}
