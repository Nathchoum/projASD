package src;


public class AVL {
    private int bal;
    private double poids = -1;
    private AVL G;
    private AVL D;
    private Tree2D origine; //noeud venant du tree2d

    /**
     * Constructeur sommet AVL
     * @param p       le poids
     * @param origine l'arbre 2D auquel il fait référence
     */
    public AVL(double p, Tree2D origine) {
        poids = p;
        this.origine = origine;
        bal = 0;
        this.G = new AVL();
        this.D = new AVL();
    }

    /**
     * Constructeur d'un noeud vide
     */
    public AVL() {
        poids = -1;
        bal = 0;
    }

    /**
     * Fonction permettant de retourner si l'AVL est vide ou pas.
     * Complexité : O(1)
     * @return boolean
     */
    public boolean estVide() {
        return this.getPoids() == -1;
    }

    /**
     * Fonction retournant si oui ou non le noeud concerné est une feuille.
     * Complexité : O(1)
     * @return boolean
     */
    public boolean estFeuille() {
        if (this.getPoids() > -1 && this.getG().estVide() && this.getD().estVide()) {
            return true;
        }
        return false;
    }

    /**
     * Fonction retournant la hauteur d'un noeud.
     * Complexité : O(n) avec le nombre de noeuds
     * @return int, la hauteur
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
     * Complexité : O(n) avec le nombre de noeuds
     */
    private void majBalance() {
        if (!(this.estVide())) {
            if (this.estFeuille()) {
                this.setBal(0);
            } else {
                this.setBal(this.getD().hauteur() - this.getG().hauteur());
                this.getG().majBalance();
                this.getD().majBalance();
            }

        }
    }

    /**
     * Fonction permettant d'ajouter un double à un AVL, cette fonction appellera majBalance() et Equilibrer() pour garder les propriétés de l'AVL
     *  Complexité : O(n) avec le nombre de noeuds
     * @param p Le poids
     * @return AVL
     */
    public AVL ajouter(double p, Tree2D origine) {
        if (this.estVide()) {
            // Mise à jour de l'AVL en un AVL avec seulement une racine
            this.setPoids(p);
            this.setOrigine(origine);
            this.setBal(0);
            this.setD(new AVL());
            this.setG(new AVL());
            return this;

        } else {
            // la nouvelle feuille passe avant la racine si : son poids est plus grand, son poids est égale et son X plus petit ou poids et X égaux et Y plus petit
            if (this.getPoids() < p || ((this.getPoids() == p) && (origine.getX() < this.getOrigine().getX())) || ((this.getPoids() == p) && (origine.getX() == this.getOrigine().getX()) && (origine.getY() < this.getOrigine().getY()))) {
                this.setD(this.getD().ajouter(p, origine));
            } else {
                this.setG(this.getG().ajouter(p, origine));
            }
        }
        // Mise à jour de l'AVL pour respecter les conditions de ce dernier
        return this.equilibre();
    }

    /**
     * Fonction faisant une ROTD sur un AVL et le renvoie.
     * Complexité : O(1)
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
     * Complexité : O(1)
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
     * Complexité : O(1)
     * @return AVL
     */
    public AVL doubleRotationDroite() {
        if (this.G == null) {
            return this;
        }
        this.G = this.G.rotationGauche();
        return this.rotationDroite();
    }

    /**
     * Fonction faisant une DROTG sur un AVL et le renvoie
     * Complexité : O(1)
     * @return AVL
     */
    public AVL doubleRotationGauche() {
        if (this.D == null) {
            return this;
        }
        this.D = this.D.rotationDroite();
        return this.rotationGauche();
    }

    /**
     * Fonction équilibrant un AVL pour lui faire conserver ses conditions et le retourne.
     * Complexité : O(n) avec le nombre de feuilles
     * @return AVL
     */
    public AVL equilibre() {
        this.majBalance(); // Met à jour toutes les balances.
        if (this.getBal() == 2) {
            if (this.getD().getBal() >= 0) {
                return this.rotationGauche();
            } else {
                return this.doubleRotationGauche();
            }
        } else {
            if (this.getBal() == -2) {
                if (this.getG().getBal() <= 0) {
                    return this.rotationDroite();
                } else {
                    return this.doubleRotationDroite();
                }
            }
            return this;
        }
    }

    /**
     * Fonction permettant de supprimer le maximum d'un AVL et renvoie l'arbre après suppression
     * Complexité : O(log n) avec n le nombre de noeuds
     * @return
     */
    public Tree2D chooseFeuille(int min) {

        if (!(this.estVide())) {
            if (this.estFeuille()) {
                Tree2D res = this.origine;
                this.poids = -1;
                if (res.getW() >= min && res.getH() >= min) {
                    return res;
                }
                return null;
            }
            if (this.getD().estVide()) { // Cas racine = max
                Tree2D res = this.getOrigine();
                // Remplacement de la racine par le fils gauche.
                // Sauvegarde du filsG de la racine pour éviter les bugs si on set le filsG à la place de la racine trop tôt
                AVL filsG = this.getG();
                this.setPoids(filsG.getPoids());
                this.setG(filsG.getG());
                this.setD(filsG.getD());
                this.setOrigine(filsG.getOrigine());
                this.setBal(filsG.getBal());

                if (res.getW() >= min && res.getH() >= min) {
                    return res;
                } else {
                    return null;
                }
            } else {
                return this.getD().chooseFeuille(min);
            }
        } else {
            return null;
        }
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
}
