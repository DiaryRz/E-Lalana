package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diary
 */
public class Qualite {
    int idQualite;
    String nomQualite;
    double Longueur;
    double Largeur;
    int NiveauQualite;

    public double getLongueur() {
        return Longueur;
    }

    public void setLongueur(double Longueur) {
        this.Longueur = Longueur;
    }
    
    public void setLongueur(String Longueur) {
        double a = Double.parseDouble(Longueur);
        this.setLongueur(a);
    }

    public double getLargeur() {
        return Largeur;
    }

    public void setLargeur(double Largeur) {
        this.Largeur = Largeur;
    }
    
     public void setLargeur(String Large) {
        double a = Double.parseDouble(Large);
        this.setLargeur(a);
    }
     
    

    public int getNiveauQualite() {
        return NiveauQualite;
    }

    public void setNiveauQualite(int NiveauQualite) {
        this.NiveauQualite = NiveauQualite;
    }
    
    public void setNiveauQualite(String NiveauQualite) {
        int niveau = Integer.parseInt(NiveauQualite);
        this.setNiveauQualite(niveau);
    }

    public int getIdQualite() {
        return idQualite;
    }

    public void setIdQualite(int idQualite) {
        this.idQualite = idQualite;
    }
    
    public void setIdQualite(String idQualite) {
        int id = Integer.parseInt(idQualite);
        this.setIdQualite(id);
    }

    public String getNomQualite() {
        return nomQualite;
    }

    public void setNomQualite(String nomQualite) {
        this.nomQualite = nomQualite;
    }
    
    public Qualite(){};
    
    public Qualite(int idQualite, String nomQualite , double Long , double Large , int Niveau) {
        this.setIdQualite(idQualite);
        this.setNomQualite(nomQualite);
        this.setNiveauQualite(Niveau);
        this.setLongueur(Long);
        this.setLargeur(Large);
    }
    
    public Qualite(String idQualite, String nomQualite , String Long , String Large , String Niveau) {
        this.setIdQualite(idQualite);
        this.setNomQualite(nomQualite);
        this.setNiveauQualite(Niveau);
        this.setLongueur(Long);
        this.setLargeur(Large);
    }
    
    public Qualite(int idQualite) {
        this.setIdQualite(idQualite);
    }
    
    public Qualite( String nomQualite , double Long , double Large, int Niveau) {
        this.setNomQualite(nomQualite);
        this.setNiveauQualite(Niveau);
        this.setLongueur(Long);
        this.setLargeur(Large);
    }
    
    public Qualite( String nomQualite , String Long , String Large, String Niveau) {
        this.setNomQualite(nomQualite);
        this.setNiveauQualite(Niveau);
        this.setLongueur(Long);
        this.setLargeur(Large);
    }
    
    public void InsertQualite(Connection conn) throws Exception{
        Connection c;
        if(conn!=null){
            c=conn;
        }else{
            c=Connexion.Connect();
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO Qualite (nomqualite,longueur,largeur,niveauqualite) values (?,? ,? ,?)";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1,this.getNomQualite());
            preparedStatement.setDouble(2,this.getLongueur());
            preparedStatement.setDouble(3,this.getLargeur());
            preparedStatement.setInt(4,this.getNiveauQualite());

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
    
    public List<Qualite> SelectQualite()throws Exception{
        List<Qualite> qualite = new ArrayList<Qualite>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Qualite");
        while (rs.next()) {
           qualite.add(new Qualite(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5)));
        }
        c.close();
        return qualite;
    }
    
    public Qualite SelectQualiteParIdQualite(int IdQualite)throws Exception{
        Qualite qualite = new Qualite();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Qualite where idQualite= "+IdQualite);
        while (rs.next()) {
           qualite=new Qualite(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5));
        }
        c.close();
        return qualite;
    }
}
