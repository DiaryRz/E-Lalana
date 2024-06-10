 package elements;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;

public class Benefice {
    int idRouteQualite;
    String NomRouteQualite;
    double PrixRevient;
    double PrixDeVente;
    double Benefice;

    public int getIdRouteQualite() {
        return idRouteQualite;
    }

    public void setIdRouteQualite(int idRouteQualite) {
        this.idRouteQualite = idRouteQualite;
    }

    public String getNomRouteQualite() {
        return NomRouteQualite;
    }

    public void setNomRouteQualite(String NomRouteQualite) {
        this.NomRouteQualite = NomRouteQualite;
    }

    public double getPrixRevient() {
        return PrixRevient;
    }

    public void setPrixRevient(double PrixRevient) {
        this.PrixRevient = PrixRevient;
    }

    public double getPrixDeVente() {
        return PrixDeVente;
    }

    public void setPrixDeVente(double PrixDeVente) {
        this.PrixDeVente = PrixDeVente;
    }

    public double getBenefice() {
        return Benefice;
    }

    public void setBenefice(double Benefice) {
        this.Benefice = Benefice;
    }

    public Benefice() {
    }

    public Benefice(int idRouteQualite, String NomRouteQualite, double PrixRevient, double PrixDeVente, double Benefice) {
        this.setIdRouteQualite(idRouteQualite);
        this.setNomRouteQualite(NomRouteQualite);
        this.setPrixRevient(PrixRevient);
        this.setPrixDeVente(PrixDeVente);
        this.setBenefice(Benefice);
    }
    
    public Benefice SelectBeneficeParRoute(int idRouteQualite) throws Exception{
        RouteQualite appelRouteQualite = new RouteQualite();
        RouteQualite revient = appelRouteQualite.PrixDeRevient(idRouteQualite);
        PrixVente appelPrixVente = new PrixVente();
        PrixVente prixvente = appelPrixVente.SelectPrixVente(idRouteQualite);
        double Valeurbenefice = prixvente.getPrix()-revient.getPrixMateriel();
        RouteQualite routeQual = appelRouteQualite.SelectRouteQualiteParId(idRouteQualite) ;
        Benefice benefice = new Benefice(idRouteQualite , routeQual.getNomRouteQualite() , revient.getPrixMateriel() , prixvente.getPrix() , Valeurbenefice);
        return benefice;
    }
    
    public List<Benefice> TousLesBenefices() throws Exception{
        RouteQualite appel = new RouteQualite();
        List<RouteQualite> ListeRouteQualite = appel.SelectRouteQualite();
        List<Benefice> ListeBenefice = new ArrayList<Benefice>();
        int i ;
        for(i = 0 ; i < ListeRouteQualite.size() ; i++){
            Benefice ChaqueBenefice = this.SelectBeneficeParRoute(ListeRouteQualite.get(i).getIdRouteQualite());
            ListeBenefice.add(ChaqueBenefice); 
        }
        return ListeBenefice;
    }
    
    public List<Benefice> FiltreBenefice(String min , String max) throws Exception{
        List<Benefice> benefice = this.TousLesBenefices();
        List<Benefice> ListeBenefice = new ArrayList<Benefice>();
        int i ;
        double minimum = Double.parseDouble(min);        
        double maximum = Double.parseDouble(max);
        for(i = 0 ; i < benefice.size() ; i++){
            if(benefice.get(i).getBenefice() >= minimum && benefice.get(i).getBenefice() <= maximum){
                ListeBenefice.add(benefice.get(i));
            }  
        }
        return ListeBenefice;
    } 
    
}
