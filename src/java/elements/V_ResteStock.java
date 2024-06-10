package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class V_ResteStock {
    int IdMateriel;
    double Quantite;
    String NomMateriel;

    public int getIdMateriel() {
        return IdMateriel;
    }

    public void setIdMateriel(int IdMateriel) {
        this.IdMateriel = IdMateriel;
    }

    public double getQuantite() {
        return Quantite;
    }

    public void setQuantite(double Quantite) {
        this.Quantite = Quantite;
    }

    public String getNomMateriel() {
        return NomMateriel;
    }

    public void setNomMateriel(String NomMateriel) {
        this.NomMateriel = NomMateriel;
    }

    public V_ResteStock() {
    }

    public V_ResteStock(int IdMateriel, double Quantite, String NomMateriel) {
        this.IdMateriel = IdMateriel;
        this.Quantite = Quantite;
        this.NomMateriel = NomMateriel;
    }
    
    public List<V_ResteStock> SelectV_ResteStock()throws Exception{
        List<V_ResteStock> type = new ArrayList<V_ResteStock>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM V_ResteStock");
        while (rs.next()) {
           type.add(new V_ResteStock(rs.getInt(1),rs.getDouble(2),rs.getString(3)));
        }
        c.close();
        return type;
    }
}
