package elements;


import connexion.Connexion;
import elements.Materiel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Client {
    int idClient;
    String NomClient;
    int idTypeClient;
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public void setIdClient(String idClient) {
        int a = Integer.parseInt(idClient);
            this.idClient = a;
    }
    public String getNomClient() {
        return NomClient;
    }
    public void setNomClient(String nomClient) {
        NomClient = nomClient;
    }
    public int getIdTypeClient() {
        return idTypeClient;
    }
    public void setIdTypeClient(int idTypeClient) {
        this.idTypeClient = idTypeClient;
    }
    public void setIdTypeClient(String idTypeClient) {
        int a = Integer.parseInt(idTypeClient);
        this.idTypeClient = a;
    }
    public Client() {
    }
    public Client(int idClient, String nomClient, int idTypeClient) {
        this.idClient = idClient;
        NomClient = nomClient;
        this.idTypeClient = idTypeClient;
    }

    public void InsertClient(Connection conn) throws Exception{
        Connection c;
        if(conn!=null){
            c=conn;
        }else{
            c=Connexion.Connect();
        }
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO Client (idClient,nomClient,IdTypeClient) values (?,?,?)";
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,this.getIdClient());
            preparedStatement.setString(2,this.getNomClient());
            preparedStatement.setInt(3,this.getIdTypeClient());

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
    
    public List<Client> SelectClient()throws Exception{
        List<Client> materiel = new ArrayList<Client>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Client");
        while (rs.next()) {
           materiel.add(new Client(rs.getInt(1),rs.getString(2),rs.getInt(3)));
        }
        c.close();
        return materiel;
    }
}