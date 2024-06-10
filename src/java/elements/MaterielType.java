package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MaterielType {
    int idMaterielType;
    int idType;
    int idMateriel;
    int idQualite;
    double QantiteParMettreCarre;

    public int getIdQualite() {
        return idQualite;
    }

    public void setIdQualite(int idQualite) {
        this.idQualite = idQualite;
    }
    
    public void setIdQualite(String idQualite) {
        int ql = Integer.parseInt(idQualite);
        this.setIdQualite(ql);
    }
    
    public double getQantiteParMettreCarre() {
        return QantiteParMettreCarre;
    }

    public void setQantiteParMettreCarre(double QantiteParMettreCarre) {
        this.QantiteParMettreCarre = QantiteParMettreCarre;
    }
    
    public void setQantiteParMettreCarre(String QantiteParMettreCarre) {
        double a = Double.parseDouble(QantiteParMettreCarre);
        this.setQantiteParMettreCarre(a);
    }

    public int getIdMaterielType() {
        return idMaterielType;
    }

    public void setIdMaterielType(int idMaterielType) {
        this.idMaterielType = idMaterielType;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }
    
    public void setIdType(String idType) {
        int a = Integer.parseInt(idType);
        this.setIdType(a);
    }

    public int getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
    }
    
    public void setIdMateriel(String IdMateriel) {
        int a = Integer.parseInt(IdMateriel);
        this.setIdMateriel(a);
    }

    public MaterielType() {
    }

    public MaterielType(int idMaterielType, int idType, int idMateriel , double quantite) {
        this.setIdMaterielType(idMaterielType);
        this.setIdType(idType);
        this.setIdMateriel(idMateriel);
        this.setQantiteParMettreCarre(quantite);
    }
    
    public MaterielType( int idType, int idMateriel,double quantite) {
        this.setIdType(idType);
        this.setIdMateriel(idMateriel);
        this.setQantiteParMettreCarre(quantite);
    }
    
    public MaterielType( int idType, int idMateriel ,double quantite , int qualite) {
        this.setIdType(idType);
        this.setIdMateriel(idMateriel);
        this.setQantiteParMettreCarre(quantite);
        this.setIdQualite(qualite);
    }
    
     public MaterielType(int idMt, int idType, int idMateriel ,double quantite , int qualite) {
         this.setIdMaterielType(idMt);
        this.setIdType(idType);
        this.setIdMateriel(idMateriel);
        this.setQantiteParMettreCarre(quantite);
        this.setIdQualite(qualite);
    }
    
    public MaterielType( String idType, String idMateriel , String quantite) {
        this.setIdType(idType);
        this.setIdMateriel(idMateriel);
        this.setQantiteParMettreCarre(quantite);
    }
    
    public MaterielType( String idType, String idMateriel , String quantite,  String qualite) {
        this.setIdType(idType);
        this.setIdMateriel(idMateriel);
        this.setQantiteParMettreCarre(quantite);
        this.setIdQualite(qualite);
    }
    
    public void InsertMaterielType(Connection conn) throws Exception{
        Connection c;
        if(conn!=null){
            c=conn;
        }else{
            c=Connexion.Connect();
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO MaterielType (idType,idMateriel,idqualite,QantiteParMettreCarre) values (?,?,?,?)";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,this.getIdType());
            preparedStatement.setInt(2,this.getIdMateriel());
            preparedStatement.setDouble(4,this.getQantiteParMettreCarre());
            preparedStatement.setInt(3,this.getIdQualite());

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
        
    public List<MaterielType> SelectMaterielType()throws Exception{
        List<MaterielType> materiel = new ArrayList<MaterielType>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM MaterielType");
        while (rs.next()) {
           materiel.add(new MaterielType(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDouble(5),rs.getInt(4)));
        }
        c.close();
        return materiel;
    }
}
