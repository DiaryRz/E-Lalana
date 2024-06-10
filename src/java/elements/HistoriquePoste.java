package elements;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HistoriquePoste {
    int IdHistoriquePoste;
    int IdPersonne;
    int IdPersonnel;
    double TauxHoraire;

    public int getIdHistoriquePoste() {
        return IdHistoriquePoste;
    }

    public void setIdHistoriquePoste(int IdHistoriquePoste) {
        this.IdHistoriquePoste = IdHistoriquePoste;
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

    public HistoriquePoste() {
    }

    public HistoriquePoste(int IdHistoriquePoste, int IdPersonne, int IdPersonnel, double TauxHoraire) {
        this.setIdHistoriquePoste(IdHistoriquePoste);
        this.setIdPersonne(IdPersonne);
        this.setIdPersonnel(IdPersonnel);
        this.setTauxHoraire(TauxHoraire);
    }
    
    public HistoriquePoste(int IdPersonne, int IdPersonnel, double TauxHoraire) {
        this.setIdPersonne(IdPersonne);
        this.setIdPersonnel(IdPersonnel);
        this.setTauxHoraire(TauxHoraire);
    }
    
    public void InsertHistoriquePoste(Connection conn) throws Exception{
        Connection c;
        if(conn==null || conn.isClosed()){
            c=Connexion.Connect();
        }else{
            c=conn;
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO historiquePoste(idpersonne,idpersonnel,tauxhoraire) values (?,?,?)";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,this.getIdPersonne());
            preparedStatement.setInt(2,this.getIdPersonnel());
            preparedStatement.setDouble(3,this.getTauxHoraire());

            c.setAutoCommit(false);
            
            preparedStatement.executeUpdate();
           
            c.commit();
            
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            c.rollback();
        }
    }
    
    public void MettreAJourPosteParPersonne(Connection conn , int idPersonne) throws Exception{
        Connection c = Connexion.Connect();
        try{
            Personne appelPersonne = new Personne();
            Personne anciennete = appelPersonne.SelectAnciennete(c,idPersonne);
            Personnel appelPersonnel = new Personnel();
            Personnel TypePersonnel = appelPersonnel.SelectPersonnelAnciennete(c,anciennete.getAnciennete());
            double ChangementSalaire = anciennete.getSalaireBase()*TypePersonnel.getAugmentation() ;
            
            System.out.println("elements.HistoriquePoste.MettreAJourPosteParPersonne()"+anciennete.getAnciennete());
            
            HistoriquePoste appelHistorique = new HistoriquePoste(idPersonne,TypePersonnel.getIdPersonnel(),ChangementSalaire);
            appelHistorique.InsertHistoriquePoste(c);
            PostePersonne appel = new PostePersonne(idPersonne,TypePersonnel.getIdPersonnel(),ChangementSalaire);
            appel.UpdatePostePersonne(c);
        }catch(Exception e){
            e.printStackTrace();
            c.rollback();
        }finally{
            c.close();
        }
    }
    
    public void MettreAJourTousLesEmployers() throws Exception{
        Personne appelPersonne = new Personne();
        List<Personne> ListePersonne = appelPersonne.SelectPersonne();
        for(int i = 0 ; i < ListePersonne.size() ; i++){
            this.MettreAJourPosteParPersonne(null, ListePersonne.get(i).getIdPersonne());
        }
    }
    
    
}
