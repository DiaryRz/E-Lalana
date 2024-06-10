/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import connexion.Connexion;
import static elements.Materiel.TransformerStrinEnDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class V_Vente {
    int idVente;
    int idRouteQualite;
    String NomRouteQualite;
    int idClient;
    String NomClient;
    int idTypeClient;
    String NomTypeClient;
    Date DateVente;
    double prix;
    int IdRoute;
    int IdQualite;

    public int getIdRoute() {
        return IdRoute;
    }

    public void setIdRoute(int IdRoute) {
        this.IdRoute = IdRoute;
    }

    public int getIdQualite() {
        return IdQualite;
    }

    public void setIdQualite(int IdQualite) {
        this.IdQualite = IdQualite;
    }
    
    
    
    public int getIdVente() {
        return idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public String getNomTypeClient() {
        return NomTypeClient;
    }

    public void setNomTypeClient(String NomTypeClient) {
        this.NomTypeClient = NomTypeClient;
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
    public void setIdClient(String idClient) {
        int a = Integer.parseInt(idClient);
        this.setIdClient(idClient);
    }

    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String NomClient) {
        this.NomClient = NomClient;
    }

    public int getIdTypeClient() {
        return idTypeClient;
    }

    public void setIdTypeClient(int idTypeClient) {
        this.idTypeClient = idTypeClient;
    }
    public void setIdTypeClient(String idTypeClient) {
        int a = Integer.parseInt(idTypeClient);
        this.setIdTypeClient(idTypeClient);
    }

    public Date getDateVente() {
        return DateVente;
    }

    public void setDateVente(Date DateVente) {
        this.DateVente = DateVente;
    }
    public void setDateVente(String DateVente) throws Exception {
        Date a=TransformerStrinEnDate(DateVente);
        this.setDateVente(a);
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    public void setPrix(String prix) {
        double a=Double.parseDouble(prix);
        this.setPrix(a);
    }

    public String getNomRouteQualite() {
        return NomRouteQualite;
    }

    public void setNomRouteQualite(String NomRouteQualite) {
        this.NomRouteQualite = NomRouteQualite;
    }

    public V_Vente() {
    }
    
    

    public V_Vente(int idVente, int idRouteQualite, String NomRouteQualite, int idClient, String NomClient, int idTypeClient, String NomTypeClient, Date DateVente, double prix ,int idRoute , int idQualite) {
        this.setIdVente(idVente);
        this.setIdRouteQualite(idRouteQualite);
        this.setNomRouteQualite(NomRouteQualite);
        this.setIdClient(idClient);
        this.setNomClient(NomClient);
        this.setIdTypeClient(idTypeClient);
        this.setNomTypeClient(NomTypeClient);
        this.setDateVente(DateVente);
        this.setPrix(prix);
        this.setIdRoute(idRoute);
        this.setIdQualite(idQualite);
    }
    
    public int NombreDeVenteParProduit(int idRoute,int taille) throws Exception{
        Connection c = Connexion.Connect();
        Statement stat = c.createStatement();
        int nombre=0;
        try{
            ResultSet rs;
            if(idRoute != 0 && taille != 0){
                System.out.println("!= 0 izy roa");
                rs = stat.executeQuery("select count(*) from V_Vente where idRoute="+idRoute+" and idQualite="+taille);
            }
            else if(idRoute == 0 && taille == 0){
                rs = stat.executeQuery("select count(*) from V_Vente");
            }
            else if(idRoute == 0 ){
                rs = stat.executeQuery("select count(*) from V_Vente where idQualite="+taille);
            }
            else{
                rs = stat.executeQuery("select count(*) from V_Vente where idRoute="+idRoute);
            }
            while(rs.next()){
                nombre = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return nombre;
    }
    
    public int NombreDeVenteParProduitPrive(int idRoute , int taille) throws Exception{
        Connection c = Connexion.Connect();
        List<Personne> mat = new ArrayList<Personne>();
        Statement stat = c.createStatement();
        int nombre=0;
        try{
            ResultSet rs;
            if(idRoute != 0 && taille != 0){
                rs = stat.executeQuery("select count(*) from V_Vente where idRoute="+idRoute+" and idQualite="+taille+" and idTypeClient=1");
            }
            else if(idRoute == 0 && taille == 0){
                rs = stat.executeQuery("select count(*) from V_Vente where idTypeClient = 1");
            }
            else if(idRoute == 0 ){
                rs = stat.executeQuery("select count(*) from V_Vente where idQualite="+taille+" and idTypeClient = 1");
            }
            else{
                rs = stat.executeQuery("select count(*) from V_Vente where idRoute="+idRoute+" and idTypeClient=1");
            }
            while(rs.next()){
                nombre = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return nombre;
    }
    
    public int NombreDeVenteParProduitPublic(int idRoute , int taille) throws Exception{
        Connection c = Connexion.Connect();
        Statement stat = c.createStatement();
        int nombre=0;
        try{
            ResultSet rs;
            if(idRoute != 0 && taille != 0){
                rs = stat.executeQuery("select count(*) from V_Vente where idRoute="+idRoute+" and idQualite="+taille+" and idTypeClient=2");
            }
            else if(idRoute == 0 && taille == 0){
                rs = stat.executeQuery("select count(*) from V_Vente where idTypeClient = 2");
            }
            else if(idRoute == 0 ){
                rs = stat.executeQuery("select count(*) from V_Vente where idQualite="+taille+" and idTypeClient = 2");
            }
            else{
                rs = stat.executeQuery("select count(*) from V_Vente where idRoute="+idRoute+" and idTypeClient=2");
            }
            while(rs.next()){
                nombre = rs.getInt(1);
            }
            while(rs.next()){
                nombre = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return nombre;
    }
    
    
}
