/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diary
 */
public class JoinRouteFabrique {
    int idroutequalite;
    String NomRouteQualite ;

    public int getIdroutequalite() {
        return idroutequalite;
    }

    public void setIdroutequalite(int idroutequalite) {
        this.idroutequalite = idroutequalite;
    }

    public String getNomRouteQualite() {
        return NomRouteQualite;
    }

    public void setNomRouteQualite(String NomRouteQualite) {
        this.NomRouteQualite = NomRouteQualite;
    }

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre = nombre;
    }
    double nombre;

    public JoinRouteFabrique(int idroutequalite, String NomRouteQualite, double nombre) {
        this.idroutequalite = idroutequalite;
        this.NomRouteQualite = NomRouteQualite;
        this.nombre = nombre;
    }

    public JoinRouteFabrique() {
    }
    
    public List<JoinRouteFabrique> SelectJoinRouteFabrique()throws Exception{
        List<JoinRouteFabrique> materiel = new ArrayList<JoinRouteFabrique>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM V_JoinRouteFabrique");
        while (rs.next()) {
           materiel.add(new JoinRouteFabrique(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
        }
        c.close();
        return materiel;
    }
}
