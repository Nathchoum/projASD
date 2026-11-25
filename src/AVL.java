package src;


public class AVL {
    private int bal;
    private double poids = -1;
    private AVL G;
    private AVL D;
    private Tree2D origine;

    /**
     * Constructeur sommet AVL
     *
     * @param p le poid
     * @param origine l'arbre 2D auquel il fait référence
     */
    public AVL(double p, Tree2D origine) {
        poids = p;
        this.origine = origine;
        bal = 0;
        this.G = new AVL();
        this.D = new AVL();
    }

    public AVL(){
        poids=-1;
        bal = 0;
    }


    /**
     * GETTER
     */

    public double getPoids() {
        return poids;
    }

    public AVL getD() {
        return D;
    }

    public AVL getG() {
        return G;
    }

    public Tree2D getOrigine() {
        return origine;
    }

    public int getBal() {
        return bal;
    }


    /**
     * SETTER
     */

    public void setPoids(double p) {
        this.poids = p;
    }

    public void setG(AVL g) {
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


    /**
     * Fonction permettant de retourner si l'AVL est vide ou pas.
     *
     * @return boolean
     */
    public boolean estVide() {
        return this.getPoids() == -1;
    }

    /**
     * Fonction retournant si oui ou non le noeud concerné est une feuille.
     *
     * @return boolean
     */
    public boolean estFeuille() {
        if (this.getPoids() > -1 || this.getG().estVide() || this.getD().estVide()) {
            return true;
        }
        return false;
    }

    /**
     * Fonction retournant la hauteur d'un noeud.
     *
     * @return int
     */
    public int hauteur() {
        if (this.estVide()) {
            return -1;
        } else {
            if (this.estFeuille()) {
                return 0;
            } else {
                int hg = this.getG().hauteur();
                int hd = this.getD().hauteur();
                return 1 + Math.max(hg, hd);
            }
        }
    }


    /**
     * Procédure faisant la mise à jour de toutes les balances de l'AVL.
     *
     */
    private void majBalance() {
        if (!(this.estVide())) {
            if (this.estFeuille()) {
                this.setBal(0);
            } else {
                this.setBal(Math.abs(this.getD().hauteur() - this.getG().hauteur()));
                this.getG().majBalance();
                this.getD().majBalance();
            }

        }
    }

    /**
     * Fonction permettant d'ajouter un double à un AVL, cette fonction appellera majBalance() et  Equilibrer() pour garder les proprièté de l'AVL
     *
     * @param p Le poid
     * @return AVL
     */
    public AVL ajouter(double p, Tree2D origine) {
        if (this.estVide()) {
            // Mise à jour de l'AVL en un AVL avec seulement une racine
            this.setPoids(p);
            this.setBal(0);
            this.setD(new AVL());
            this.setG(new AVL());
            this.setOrigine(origine);
            return this;
        } else {
            if (this.getPoids() < p) {
                this.getD().ajouter(p,origine);
            } else {
                this.getG().ajouter(p,origine);
            }
        }
            // Mise à jour de l'AVL pour respecter les conditions de ce dernier
            AVL newAvl = this.equilibre();
            this.setPoids(newAvl.getPoids());
            this.setBal(newAvl.getBal());
            this.setD(newAvl.getD());
            this.setG(newAvl.getG());
            this.setOrigine(newAvl.getOrigine());
            return this;
    }

    /**
     * Fonction faisant une ROTD sur un AVL et le renvoie.
     *
     * @return AVL
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
     * Fonction faisant une ROTG sur un AVL et le renvoie.
     *
     * @return AVL
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
     * Fonction faisant une DROTD sur un AVL et le renvoie
     *
     * @return AVL
     */
    public AVL rotationGaucheDroite() {
        if (this.G == null) {
            return this;
        }
        this.G = this.G.rotationGauche();
        return this.rotationDroite();
    }

    /**
     * Fonction faisant une DROTG sur un AVL et le renvoie
     *
     * @return AVL
     */
    public AVL rotationDroiteGauche() {
        if (this.D == null) {
            return this;
        }
        this.D = this.D.rotationDroite();
        return this.rotationGauche();
    }

    /**
     * Fonction équilibrant un AVL pour lui faire conserver ses conditions et le retourne.
     *
     * @return AVL
     */
    public AVL equilibre() {
        this.majBalance(); // Met à jour toutes les balances.
        if (this.getBal() == 2) {
            if (this.getD().getBal() >= 0) {
                return this.rotationGauche();
            } else {
                return this.rotationDroiteGauche();
            }
        } else {
            if (this.getBal() == -2) {
                if (this.getG().getBal() <= 0) {
                    return this.rotationDroite();
                } else {
                    return this.rotationGaucheDroite();
                }
            }
            return this;
        }
    }

    /**
     * Fonction permettant de supprimer le maximum d'un AVL et renvoie l'arbre après suppression
     * @return
     */
    public Tree2D chooseFeuille(int min){

        if(!(this.estVide())){

            if(this.estFeuille()) {
                Tree2D res = this.origine;
                this.poids = -1;
                if (res.getW()>=min && res.getH()>=min){
                    return res;
                }
                return null;
            }

            if(this.getG().estVide()) { // Fils gauche pas vide, fils droit vide. MAX = Racine
                return this.getD().chooseFeuille(min);
            }

            else {
                Tree2D res = this.getD().chooseFeuille(min);
                if (res.CalculPoids() == this.getPoids()){
                    if (this.origine.getX()< res.getX()) {
                        res = this.origine;
                        this.poids = this.getG().getPoids();
                        this.setG(this.getG().getG());
                        this.equilibre();
                        if (res.getW() >= min && res.getH() >= min) {
                            return res;
                        }
                        return null;
                    }
                    }
                }
            }
        return null;
    }

    /**
     * Fonction permettant de rechercher un nœud ayant le poids rechercher puis le renvoie.
     *
     * @param poids
     * @return AVL
     */

    public AVL rechercher(double poids) {
        if (!(this.estVide())) {
            if (this.getPoids() == poids) {
                return this;
            } else {
                if (this.getPoids() > poids) {
                    return this.getG().rechercher(poids);
                } else {
                    return this.getD().rechercher(poids);
                }
            }
        }
        return null;
    }

}
