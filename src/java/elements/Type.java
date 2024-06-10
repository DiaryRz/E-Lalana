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
public class Type {
    int idType ;
    String nomType;
    
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

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
       this.nomType = nomType ;
    }
    
    public Type(){}
    
    public Type(int idType, String nomType) {
        this.setIdType(idType);
        this.setNomType(nomType);
    }
    
    // Constructor that takes only a String parameter
    public Type(String nomType) {
        this.setNomType(nomType);
    }
    
   
    public void InsertType(Connection conn) throws Exception{
        Connection c;
        if(conn!=null){
            c=conn;
        }else{
            c=Connexion.Connect();
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO Type (nomType) values (?)";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1,this.getNomType());
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
    
    public List<Type> SelectType()throws Exception{
        List<Type> type = new ArrayList<Type>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Type");
        while (rs.next()) {
           type.add(new Type(rs.getInt(1),rs.getString(2)));
        }
        c.close();
        return type;
    }
    
}
