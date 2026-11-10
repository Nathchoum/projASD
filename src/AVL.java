package src;

public class AVL {
    private int bal;
    private double poids;
    private AVL G;
    private AVL D;
    private Tree2D origine;

    /**
     *
     * @param p
     * @param origine
     * @param b
     */
    public AVL (double p, Tree2D origine, int b) {
        poids = p;
        this.origine= origine;
        bal = bal;
    }

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

    public int getBal() {
        return bal;
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

    public void setBal(int bal) {
        this.bal = bal;
    }

    //TODO Ajouter, Supprimer, Rechercher, Equilibrer

    /**
     * Fonction permettant de retourner si l'AVL est vide ou pas.
     * @return boolean
     */
    public boolean estVide() {
        if(this.getG() == null || this.getD() == null ){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Fonction permettant d'ajouter un double à un AVL, cette fonction appellera Equilibrer() pour garder les proprièté de l'AVL
     * @param poids
     * @return
     */
    public AVL Ajouter(double poids){
        if(this.estVide()){

        }
    }

    /**
     *
     * @return
     */
    public AVL rotationDroite() {
        AVL B = this.G;

        if (B == null) {
            return this;
        }

        int a = this.bal;
        int b = B.bal;

        this.G = B.D;
        B.D = this;

        this.bal = a - Math.min(b, 0) + 1;
        B.bal = Math.max(a + 2, Math.max(a + b + 2, b + 1));

        return B;
    }

    /**
     *
     * @return
     */
    public AVL rotationGauche() {
        AVL B = this.D;

        if (B == null) {
            return this;
        }

        int a = this.bal;
        int b = B.bal;

        this.D = B.G;
        B.G = this;

        this.bal = a - Math.max(b, 0) - 1;
        B.bal = Math.min(a - 2, Math.min(a + b - 2, b - 1));

        return B;
    }

    /**
     *
     * @return
     */
    public AVL rotationGaucheDroite() {
        if (this.G == null) {
            return this;
        }
        this.G = this.G.rotationGauche();
        return this.rotationDroite();
    }

    /**
     *
     * @return
     */
    public AVL rotationDroiteGauche() {
        if (this.D == null) {
            return this;
        }
        this.D = this.D.rotationDroite();
        return this.rotationGauche();
    }
}

