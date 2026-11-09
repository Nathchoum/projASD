package src;

import java.awt.*;

public class Tree2D {
    private Tree2D g;
    private Tree2D d;
    private boolean feuille;
    private Color couleur;
    private int x;
    private int y;
    private int w;
    private int h;

    /**
     *Permet de calculer le poids d'un noeud, afin de l'envoyer dans l'avl
     * @return le poids d'un nœud
     */
    public double CalculPoids(){
        double denominateur = Math.pow((w+h),1.5);
        return ( (w * h) / denominateur);
    }

    /**
     * Constructeur du nœud racine
     * @param weight
     * @param height
     */
    public Tree2D (int weight, int height){

    }

    /**
     *Constructeur d'un nœud quelconque
     * @param abs
     * @param ord
     * @param weight
     * @param height
     */
    public Tree2D (int abs, int ord, int weight, int height){

    }

    /**
     *Constructeur d'une feuille
     * @param x
     * @param y
     * @param w
     * @param h
     * @param couleur
     */
    public Tree2D (int x, int y, int w, int h, Color couleur){

    }

    //TODO Coupure (soit basique, soit avancé) pour déterminer l'axe de coupure.
    public void coupure(String type){
        if(type.equals("A")){
            if(((w/h)>2) || ((h/w) <= 2)){ // Cas coupure sur X
                Tree2D Gauche = new Tree2D(w/2, h, x, y);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w/2, h, x+w/2 , y);    // Attention à voir ce que ça donne si x, y impair
                this.g = Gauche;
                this.d = Droit;
            }
            else{ // Coupure sur Y
                Tree2D Gauche = new Tree2D(w, h/2 , x, y);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w, h/2, x , y+h/2);    // Attention à voir ce que ça donne si x, y impair
                this.g = Gauche;
                this.d = Droit;
            }
        } else if (type.equals("B")) {
            if((w/h)>1){ // Cas coupure sur X
                Tree2D Gauche = new Tree2D(w/2, h, x, y);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w/2, h, x+w/2 , y);    // Attention à voir ce que ça donne si x, y impair
                this.g = Gauche;
                this.d = Droit;
            }
            else{
                Tree2D Gauche = new Tree2D(w, h/2 , x, y);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w, h/2, x , y+h/2);    // Attention à voir ce que ça donne si x, y impair
                this.g = Gauche;
                this.d = Droit;
            }
        }
        else{ // Entrée du type incorrect
            System.out.println("Le type de coupure est incorrect.");
        }
    }

    //TODO Rechercher pour trouver le noeud à couper

    //TODO GenererImage qui va générer l'image resultat à partir du 2D Arbre complet

    //TODO NoeudToFeuille qui parcours l'arbre à la recherche de noeud sans fils. Si un noeud est sans fils, on remonte à son parent et on créer un nouveau noeud feuille.
}