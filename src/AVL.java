package src;

public class AVL {
    private double poids;
    private AVL G;
    private AVL D;
    private Tree2D origine;

    /**
     *  GETTER
     */

    public double getPoids(){
        return poids;
    }

    public AVL getD() {
        return D;
    }

    public AVL getG() {
        return G;
    }

    public Tree2D getOrigine(){
        return origine;
    }

    /**
     *  SETTER
     */

    public void setPoids(double p){
        this.poids = p;
    }

    public void setG(AVL g){
        this.G = g;
    }

    public void setD(AVL d) {
        D = d;
    }

    public void setOrigine(Tree2D origine) {
        this.origine = origine;
    }

    //TODO Ajouter, Supprimer, Rechercher, Equilibrer
}
