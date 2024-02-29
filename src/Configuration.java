import java.util.Arrays;

public class Configuration {
    final private String DESCRIPTION;
    private double prixMax;
    private Composant[] composant;
    private int MAX_COMPOSANTS = 20;
    int nbComposant;
    public Configuration(String DESCRIPTION, double prixMax, Composant[] composant){
        this.DESCRIPTION = DESCRIPTION.toUpperCase();
        this.prixMax = prixMax;
        this.composant = composant;
        this.nbComposant = composant.length;
    }

    public Configuration(Configuration originale){
        this.DESCRIPTION = originale.DESCRIPTION.toUpperCase();
        this.prixMax = originale.prixMax;
        this.nbComposant = originale.nbComposant;

        this.composant = new Composant[originale.composant.length];
        for (int i = 0; i < originale.composant.length; i++) {
            if (originale.composant[i] != null) {
                this.composant[i] = originale.composant[i].copier();
            }
        }
    }


    public double calculerTotal(double taxe){
        double prixTotal = 0;
        double prixTotalnotaxe = 0;
        for (int i =0; i < this.composant.length; i++) {
            if (this.composant[i] != null) {
               prixTotalnotaxe += composant[i].getPrix();
            }
        }
        taxe = prixTotalnotaxe * taxe;
        prixTotal = prixTotalnotaxe + taxe;
        return  prixTotal;
    }


    public Composant rechercher(String categorie){
        for(int i = 0; i < this.composant.length; i++){
            if(composant[i].getCategorie().equalsIgnoreCase(categorie)){
                return composant[i];
            }
        }
        return null;
    }
    public boolean ajouter(Composant composant) {
        double prixtotal = 0;
        if (nbComposant > MAX_COMPOSANTS || nbComposant < 0) {
            return false;
        }

        for (int i = 0; i < this.composant.length; i++) {
            if (composant.getCategorie().equals(this.composant[i].getCategorie())) {
                System.out.println("Il y a déjà un composant de cette catégorie: " + this.composant[i].toString());
                return false;
            }
        }
        for (int i = 0; i < this.composant.length; i++) {
            prixtotal += this.composant[i].getPrix();
        }
        if (prixtotal + composant.getPrix() > prixMax) {
            System.out.println("L'ajout de ce composant ferait dépasser le prix maximum: " + composant.toString());
            return false;

        }
            Composant[] composants = new Composant[this.composant.length + 1];
            for (int i = 0; i < this.composant.length; i++) {
                composants[i] = this.composant[i];
            }
            for (int index = 0; index < composants.length; index++) {
                if (composants[index] == null) {
                    composants[index] = composant;
                    nbComposant++;
                    this.composant = composants;
                    System.out.println(this.composant[index].toString() + " ajouté à la configuration ( total=" + calculerTotal(0) +")") ;
                }

            }

        return true;
    }
    public boolean retirer(Composant composant) {
        boolean trouve = false;
        for (int i = 0; i < this.composant.length; i++) {
            if (this.composant[i] != null && this.composant[i].equals(composant)) {
                trouve = true;
                for (int index = i; index < this.composant.length - 1; index++) {
                    this.composant[index] = this.composant[index + 1];
                }
                nbComposant--;
                this.composant[this.composant.length - 1] = null;
                System.out.println(composant.toString() + " retiré de la configuration ( total=" + calculerTotal(0) +")") ;
                return true;
            }
        }
        if(!trouve) {
            System.out.println("Composant introuvable : " + composant.toString());
            return false;
        }
        return false;
    }
    public boolean remplacer(Composant composant){
        for(int i = 0; i < this.composant.length; i++){
            if(this.composant[i].getCategorie().equals(composant.getCategorie())){
                System.out.println(this.composant[i].toString() + " retiré de la configuration ( total=" + calculerTotal(0) +")") ;
                this.composant[i] = composant;
                System.out.println(composant.toString() + " ajouté à la configuration ( total=" + calculerTotal(0) +")") ;
                return true;
            }
        }
        return false;
    }
    public String toString(){

        String result = DESCRIPTION + " (max " + prixMax + ") :\n";
        for (int i = 0; i < composant.length; i++) {
            if (composant[i]!= null){
                result += (i + 1) + " : " + composant[i].toString() +" ("+ composant[i].getPrix() +"$)" + "\n";
                }
        }
        return result;
    }


    public int getNbComposants() {
        int nbComposant1 = 0;
        for (int i = 0; i < this.composant.length; i++){
            if(this.composant[i] != null)
                nbComposant1++;
        }
        return nbComposant1;
    }

    public Composant[] getComposants() {
        return this.composant;
      }
}
