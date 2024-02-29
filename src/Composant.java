public class Composant {

    private final String CATEGORIE;
    private String marque;
    private String nom;
    private double prix;

    private double rabais;
    public Composant(String CATEGORIE, String marque, String nom, double prix){
        this.CATEGORIE = CATEGORIE.toUpperCase().trim();
        this.marque = marque;
        this.nom = nom;
        this.prix = prix;
        double rabais = 0;
    }


    public Composant copier(){
         Composant nouveau = new Composant(this.CATEGORIE, this.marque, this.nom, this.prix);
         return nouveau;
    }
    public boolean estIdentique(Composant x){
        boolean estvrai = false;
        if((this.CATEGORIE.equals(x.CATEGORIE)) && (this.marque.equalsIgnoreCase( x.marque)) && (this.nom.equalsIgnoreCase( x.nom))){
            estvrai = true;
            return estvrai;
        }
        return estvrai;
    }
    public String getCategorie(){return this.CATEGORIE;}

    public double setPrix(double lePrix){return this.prix = lePrix;}
    public double getPrix() {
        double rabaisTotal = 0;
        double prixTotal = 0;
        rabaisTotal = rabais * this.prix;
        prixTotal = this.prix - rabaisTotal;
        return prixTotal;
    }
    public double setRabais(double leRabais){return  this.rabais = leRabais;}
    public String getMarque() {return marque;}
    public String getNom() {return this.nom;}

    public double getRabais(){return this.rabais;}

    public  String toString() {
        return "[" + getCategorie() + "] " + getMarque() + " " + getNom();
    }
}





