package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Personnel {
    int IdPersonnel;
    String NomPersonnel;
    double ExpMin;
    double ExpMax;
    double Augmentation;

    public double getExpMin() {
        return ExpMin;
    }

    public void setExpMin(double ExpMin) {
        this.ExpMin = ExpMin;
    }
    
    public void setExpMin(String ExpMin) {
        double a = Double.parseDouble(ExpMin);
        this.setExpMin(a);
    }

    public double getExpMax() {
        return ExpMax;
    }

    public void setExpMax(double ExpMax) {
        this.ExpMax = ExpMax;
    }
    
    public void setExpMax(String ExpMax) {
        double a = Double.parseDouble(ExpMax);
        this.setExpMax(a);
    }

    public double getAugmentation() {
        return Augmentation;
    }

    public void setAugmentation(double Augmentation) {
        this.Augmentation = Augmentation;
    }
    
    public void setAugmentation(String Augmentation) {
        double a = Double.parseDouble(Augmentation);
        this.setAugmentation(a);
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

    public Personnel() {
    }

    public Personnel(int IdPersonnel, String NomPersonnel) {
        this.setIdPersonnel(IdPersonnel);
        this.setNomPersonnel(NomPersonnel);
    }
    
    public Personnel(int IdPersonnel, String NomPersonnel,double min , double max , double augmentation) {
        this.setIdPersonnel(IdPersonnel);
        this.setNomPersonnel(NomPersonnel);
        this.setExpMin(min);        
        this.setExpMax(max);
        this.setAugmentation(augmentation);
    }
    
    public Personnel(String NomPersonnel,double min , double max , double augmentation) {
        this.setNomPersonnel(NomPersonnel);
        this.setExpMin(min);        
        this.setExpMax(max);
        this.setAugmentation(augmentation);
    }
    
    public Personnel(String NomPersonnel,String min , String max , String augmentation) {
        this.setNomPersonnel(NomPersonnel);
        this.setExpMin(min);        
        this.setExpMax(max);
        this.setAugmentation(augmentation);
    }
    
    public Personnel(String NomPersonnel) {
        this.setNomPersonnel(NomPersonnel);
    }
   
    public void InsertPersonnel(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO personnel (nomPersonnel,expMin,expMax,augmentation) values (?,?,?,?)";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1,this.getNomPersonnel());
            preparedStatement.setDouble(2,this.getExpMin());
            preparedStatement.setDouble(3,this.getExpMax());
            preparedStatement.setDouble(4,this.getAugmentation());

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
    
    public List<Personnel> SelectPersonnel() throws Exception{
        Connection c = Connexion.Connect();
        List<Personnel> mat = new ArrayList<Personnel>();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from personnel");
            while(rs.next()){
                mat.add(new Personnel(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            c.close();
        }
        return mat;
    }
    
    public Personnel SelectPersonnelAnciennete(Connection conn,double ancienne) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        Personnel mat = new Personnel();
        Statement stat = c.createStatement();
        try{
            ResultSet rs=stat.executeQuery("select * from personnel where "+ancienne+" >= expMin and "+ancienne+" < expMax");
            while(rs.next()){
                mat=new Personnel(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return mat;
    }
}
