package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PostePersonne {
    int IdPostePersonne;
    int IdPersonne;
    int IdPersonnel;
    double TauxHoraire;

    public int getIdPostePersonne() {
        return IdPostePersonne;
    }

    public void setIdPostePersonne(int IdPostePersonne) {
        this.IdPostePersonne = IdPostePersonne;
    }

    public int getIdPersonne() {
        return IdPersonne;
    }

    public void setIdPersonne(int IdPersonne) {
        this.IdPersonne = IdPersonne;
    }

    public int getIdPersonnel() {
        return IdPersonnel;
    }

    public void setIdPersonnel(int IdPersonnel) {
        this.IdPersonnel = IdPersonnel;
    }

    public double getTauxHoraire() {
        return TauxHoraire;
    }

    public void setTauxHoraire(double TauxHoraire) {
        this.TauxHoraire = TauxHoraire;
    }

    public PostePersonne() {
    }

    public PostePersonne(int IdPostePersonne, int IdPersonne, int IdPersonnel, double TauxHoraire) {
        this.setIdPostePersonne(IdPostePersonne);
        this.setIdPersonne(IdPersonne);
        this.setIdPersonnel(IdPersonnel);
        this.setTauxHoraire(TauxHoraire);
    }
    
    public PostePersonne(int IdPersonne, int IdPersonnel, double TauxHoraire) {
        this.setIdPersonne(IdPersonne);
        this.setIdPersonnel(IdPersonnel);
        this.setTauxHoraire(TauxHoraire);
    }
            
    public void UpdatePostePersonne(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "Update postepersonne set idPersonnel = ? , tauxhoraire = ? where idPersonne = ?";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,this.getIdPersonnel());
            preparedStatement.setDouble(2,this.getTauxHoraire());
            preparedStatement.setInt(3,this.getIdPersonne());

            c.setAutoCommit(false);
            
            preparedStatement.executeUpdate();
           
            c.commit();
            
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            c.rollback();
        }
    }
    
}
