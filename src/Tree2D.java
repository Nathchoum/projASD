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
    private Tree2D predecesseur;

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
    public Tree2D (int abs, int ord, int weight, int height, Tree2D pred){

    }

    /**
     *Constructeur d'une feuille
     * @param x
     * @param y
     * @param w
     * @param h
     * @param couleur
     */
    public Tree2D (int x, int y, int w, int h, Color couleur, Tree2D fred){

    }

    public void Coupure(String type){
        if(type.equals("A")){
            if(((w/h)>2) || ((h/w) <= 2)){ // Cas coupure sur X
                Tree2D Gauche = new Tree2D(w/2, h, x, y, this);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w/2, h, x+w/2 , y, this);    // Attention à voir ce que ça donne si x, y impair
                this.g = Gauche;
                this.d = Droit;
            }
            else{ // Coupure sur Y
                Tree2D Gauche = new Tree2D(w, h/2 , x, y, this);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w, h/2, x , y+h/2, this);    // Attention à voir ce que ça donne si x, y impair
                this.g = Gauche;
                this.d = Droit;
            }
        } else if (type.equals("B")) {
            if((w/h)>1){ // Cas coupure sur X
                Tree2D Gauche = new Tree2D(w/2, h, x, y, this);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w/2, h, x+w/2 , y, this);    // Attention à voir ce que ça donne si x, y impair
                this.g = Gauche;
                this.d = Droit;
            }
            else{
                Tree2D Gauche = new Tree2D(w, h/2 , x, y, this);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w, h/2, x , y+h/2, this);    // Attention à voir ce que ça donne si x, y impair
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
    public void NoeudToFeuille(){
         if (this.g == null && this.d==null){
             /*
             si la feuille est à gauche de son prédécesseur
              */
             if (this.equals(this.predecesseur.g)) {
                 this.predecesseur.g = new Tree2D(x, y, w, h, chooseColor, this.predecesseur);  //on aura une fonction dans le main qui va permettre de savoir la couleur
             }
             /*
             si la feuille est à droite de son prédécesseur
              */
             else {
                 this.predecesseur.d = new Tree2D(x, y, w, h, chooseColor, this.predecesseur);  //on aura une fonction dans le main qui va permettre de savoir la couleur
             }
         }
         this.g.NoeudToFeuille();
         this.d.NoeudToFeuille();
    }
}