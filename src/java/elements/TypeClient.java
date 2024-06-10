
import connexion.Connexion;
import elements.Client;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class TypeClient{
    int idTypeClient;
    String NomClient;


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
    public String getNomClient() {
        return NomClient;
    }
    public void setNomClient(String nomClient) {
        NomClient = nomClient;
    }


    public TypeClient(int idTypeClient, String nomClient) {
        this.idTypeClient = idTypeClient;
        NomClient = nomClient;
    }
    public TypeClient() {
    }

    public List<TypeClient> SelectTypeClient()throws Exception{
        List<TypeClient> materiel = new ArrayList<TypeClient>();
        Connection c = Connexion.Connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM TypeClient");
        while (rs.next()) {
           materiel.add(new TypeClient(rs.getInt(1),rs.getString(2)));
        }
        c.close();
        return materiel;
    }
    
}