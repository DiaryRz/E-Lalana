
package elements;

public class Pourcentage {
    int IdRoute;
    int IdQualite;
    double NombreTotal;
    double NombrePrive;
    double NombrePublic;
    String NomRoute;
    String NomQualite;

    public String getNomRoute() {
        return NomRoute;
    }

    public void setNomRoute(String NomRoute) {
        this.NomRoute = NomRoute;
    }

    public String getNomQualite() {
        return NomQualite;
    }

    public void setNomQualite(String NomQualite) {
        this.NomQualite = NomQualite;
    }
    
    

    public int getIdRoute() {
        return IdRoute;
    }

    public void setIdRoute(int IdRoute) {
        this.IdRoute = IdRoute;
    }

    public int getIdQualite() {
        return IdQualite;
    }

    public void setIdQualite(int IdQualite) {
        this.IdQualite = IdQualite;
    }

    public double getNombreTotal() {
        return NombreTotal;
    }

    public void setNombreTotal(double NombreTotal) {
        this.NombreTotal = NombreTotal;
    }

    public double getNombrePrive() {
        return NombrePrive;
    }

    public void setNombrePrive(double NombrePrive) {
        this.NombrePrive = NombrePrive;
    }

    public double getNombrePublic() {
        return NombrePublic;
    }

    public void setNombrePublic(double NombrePublic) {
        this.NombrePublic = NombrePublic;
    }

    public Pourcentage() {
    }

    public Pourcentage(int IdRoute, int IdQualite, double NombreTotal, double NombrePrive, double NombrePublic , String NomRoute , String NomQualite) {
        this.setIdRoute(IdRoute);
        this.setIdQualite(IdQualite);
        this.setNombreTotal(NombreTotal);
        this.setNombrePrive(NombrePrive);
        this.setNombrePublic(NombrePublic);
        this.setNomRoute(NomRoute);
        this.setNomQualite(NomQualite);
    }
    
    public Pourcentage AvoirPourcentage(String idroute , String idtaille) throws Exception{
        int idRoute = Integer.parseInt(idroute);
        int taille = Integer.parseInt(idtaille);
        V_Vente appel = new V_Vente();
        int nbTotal = appel.NombreDeVenteParProduit(idRoute, taille) ;
        int nbTotalPrive = appel.NombreDeVenteParProduitPrive(idRoute, taille) ;        
        int nbTotalPublic = appel.NombreDeVenteParProduitPublic(idRoute, taille) ;
        System.out.println(nbTotal);
        int prive = nbTotalPrive*100/nbTotal;
        int publique = nbTotalPublic*100/nbTotal;
        Route appelRoute = new Route();
        Route v = appelRoute.SelectRouteParIdRoute(null,idRoute);
        Qualite appelQualite = new Qualite();
        Qualite q = appelQualite.SelectQualiteParIdQualite(taille);
        Pourcentage liste = new Pourcentage(idRoute,taille,nbTotal,prive,publique,v.getNomRoute(),q.getNomQualite());
        return liste;
    }
    
    
    
    
    
}
