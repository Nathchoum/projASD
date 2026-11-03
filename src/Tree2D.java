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

    //TODO Coupure (soit basique, soit avancé) pour déterminer l'axe de coupure

    //TODO Ajouter

    //TODO Rechercher pour trouver le noeud à couper

    //TODO GenererImage qui va générer l'image resultat à partir du 2D Arbre complet


}