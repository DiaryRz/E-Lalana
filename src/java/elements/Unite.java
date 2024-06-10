/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Unite {
    int IdUnite;
    String NomUnite;

    public int getIdUnite() {
        return IdUnite;
    }

    public void setIdUnite(int IdUnite) {
        this.IdUnite = IdUnite;
    }

    public String getNomUnite() {
        return NomUnite;
    }

    public void setNomUnite(String NomUnite) {
        this.NomUnite = NomUnite;
    }

    public Unite() {
    }

    public Unite(int IdUnite, String NomUnite) {
        this.setIdUnite(IdUnite);
        this.setNomUnite(NomUnite);
    }
    
    public void InsertUnite(Connection conn) throws Exception{
        Connection c;
        if(conn!=null){
            c=conn;
        }else{
            c=Connexion.Connect();
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO Unite (NomUnite) values (?)";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1,this.getNomUnite());
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
    
    public List<Unite> SelectUnite()throws Exception{
        List<Unite> unite = new ArrayList<Unite>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Unite");
        while (rs.next()) {
           unite.add(new Unite(rs.getInt(1),rs.getString(2)));
        }
        c.close();
        return unite;
    }
}
