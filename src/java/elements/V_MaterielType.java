/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diary
 */
public class V_MaterielType {
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
    
    public void setIdType(String IdType) {
        int id = Integer.parseInt(IdType);
        this.setIdType(id);
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

    public V_MaterielType(int IdMaterielType, int IdType, String NomType, double QuantiteParMettreCarre, int IdMateriel, String NomMateriel, double Prix, int IdUnite, Date DateMateriel, int IdQualite) {
        this.IdMaterielType = IdMaterielType;
        this.IdType = IdType;
        this.NomType = NomType;
        this.QuantiteParMettreCarre = QuantiteParMettreCarre;
        this.IdMateriel = IdMateriel;
        this.NomMateriel = NomMateriel;
        this.Prix = Prix;
        this.IdUnite = IdUnite;
        this.DateMateriel = DateMateriel;
        this.IdQualite = IdQualite;
    }

    
    public V_MaterielType(String IdType){
        this.setIdType(IdType);
    }
    
    public List<V_MaterielType> SelectMaterielParType()throws Exception{
        List<V_MaterielType> materiel = new ArrayList<V_MaterielType>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM V_MaterielType where idType='"+this.getIdType()+"'");
        while (rs.next()) {
           materiel.add(new V_MaterielType(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getDouble(7),rs.getInt(8),rs.getDate(9),rs.getInt(10)));
        }
        c.close();
        return materiel;
    }
    
     public List<V_MaterielType> SelectRepMaterielParType()throws Exception{
        List<V_MaterielType> materiel = new ArrayList<V_MaterielType>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM v_materielType where idType='"+this.getIdType()+"'");
        while (rs.next()) {
           materiel.add(new V_MaterielType(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getDouble(7),rs.getInt(8),rs.getDate(9),rs.getInt(10)));
        }
        c.close();
        return materiel;
    }
    
    
}
