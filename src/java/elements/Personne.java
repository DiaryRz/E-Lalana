package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Personne {
    int IdPersonne;
    String NomPersonne;
    int IdPersonnel;
    Date DateNaissance;
    Date DateEmbauche;
    double SalaireBase;
    
    double anciennete;

    public double getAnciennete() {
        return anciennete;
    }

    public void setAnciennete(double anciennete) {
        this.anciennete = anciennete;
    }
    
    public int getIdPersonne() {
        return IdPersonne;
    }

    public void setIdPersonne(int IdPersonne) {
        this.IdPersonne = IdPersonne;
    }
    
    public void setIdPersonne(String IdPersonne) {
        int a = Integer.valueOf(IdPersonne);
        this.setIdPersonne(a);
    }

    public String getNomPersonne() {
        return NomPersonne;
    }

    public void setNomPersonne(String NomPersonne) {
        this.NomPersonne = NomPersonne;
    }

    public Date getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(Date DateNaissance) {
        this.DateNaissance = DateNaissance;
    }
    
    public void setDateNaissance(String DateNaissance) throws Exception {
        Date date = TransformerStringEnDate(DateNaissance);
        this.setDateNaissance(date);
    }

    public Date getDateEmbauche() {
        return DateEmbauche;
    }

    public void setDateEmbauche(Date DateEmbauche) {
        this.DateEmbauche = DateEmbauche;
    }
    
    public void setDateEmbauche(String DateN) throws Exception {
        Date date = TransformerStringEnDate(DateN);
        this.setDateEmbauche(date);
    }

    public double getSalaireBase() {
        return SalaireBase;
    }

    public void setSalaireBase(double SalaireBase) {
        this.SalaireBase = SalaireBase;
    }
    
    public void setSalaireBase(String SalaireBase) {
        double a = Double.parseDouble(SalaireBase);
        this.setSalaireBase(a);
    }
    
    public int getIdPersonnel() {
        return IdPersonnel;
    }

    public void setIdPersonnel(int IdPersonnel) {
        this.IdPersonnel = IdPersonnel;
    }
    
    public void setIdPersonnel(String IdPersonnel) {
        int a = Integer.parseInt(IdPersonnel);
        this.setIdPersonnel(a);
    }

    public Personne() {
    }

    public Personne(int IdPersonne, String NomPersonne,int IdPersonnel, Date DateNaissance, Date DateEmbauche, double SalaireBase) {
        this.setIdPersonne(IdPersonne);
        this.setNomPersonne(NomPersonne);
        this.setDateNaissance(DateNaissance);
        this.setDateEmbauche(DateEmbauche);
        this.setSalaireBase(SalaireBase);
        this.setIdPersonnel(IdPersonnel);
    }
    
    public Personne(int IdPersonne, String NomPersonne,int IdPersonnel, Date DateNaissance, Date DateEmbauche, double SalaireBase , double anciennete) {
        this.setIdPersonne(IdPersonne);
        this.setNomPersonne(NomPersonne);
        this.setDateNaissance(DateNaissance);
        this.setDateEmbauche(DateEmbauche);
        this.setSalaireBase(SalaireBase);
        this.setIdPersonnel(IdPersonnel);
        this.setAnciennete(anciennete);
    }
    
    public Personne(String NomPersonne,int IdPersonnel, Date DateNaissance, Date DateEmbauche, double SalaireBase) {
        this.setNomPersonne(NomPersonne);
        this.setDateNaissance(DateNaissance);
        this.setDateEmbauche(DateEmbauche);
        this.setSalaireBase(SalaireBase);
        this.setIdPersonnel(IdPersonnel);
    }
    
    public Personne(String NomPersonne,int IdPersonnel, Date DateNaissance, Date DateEmbauche, double SalaireBase , double anciennete) {
        this.setNomPersonne(NomPersonne);
        this.setDateNaissance(DateNaissance);
        this.setDateEmbauche(DateEmbauche);
        this.setSalaireBase(SalaireBase);
        this.setIdPersonnel(IdPersonnel);
        this.setAnciennete(anciennete);
    }
    
    public Personne(String NomPersonne,String IdPersonnel, String DateNaissance, String SalaireBase , String DateEmbauche) throws Exception {
        this.setNomPersonne(NomPersonne);
        this.setDateNaissance(DateNaissance);
        this.setSalaireBase(SalaireBase);
        this.setIdPersonnel(IdPersonnel);
        this.setDateEmbauche(DateEmbauche);
    }
    
    public void InsertPersonne(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO Personne(nompersonne,idpersonnel,datenaissance,dateembauche,salairebase) values (?,?,?,?,?)";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1,this.getNomPersonne());
            preparedStatement.setInt(2,this.getIdPersonnel());
            preparedStatement.setDate(3,this.getDateNaissance());
            preparedStatement.setDate(4,this.getDateEmbauche());
            preparedStatement.setDouble(5,this.getSalaireBase());

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
    
    public static Date TransformerStringEnDate(String dateString)throws Exception{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            // Convertir la chaîne en objet java.util.Date
            java.util.Date utilDate = sdf.parse(dateString);

            // Convertir java.util.Date en java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // Afficher le résultat
           return sqlDate;
    }
    
    public List<Personne> SelectPersonne() throws Exception{
        Connection c = Connexion.Connect();
        List<Personne> mat = new ArrayList<Personne>();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from personne");
            while(rs.next()){
                mat.add(new Personne(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getDouble(6)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    public Personne SelectAnciennete(Connection conn,int idPersonne) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        Personne mat = new Personne();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from anciennete where idPersonne = "+idPersonne);
            while(rs.next()){
                mat=new Personne(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getDouble(6),rs.getDouble(7));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return mat;
    }
    
}
