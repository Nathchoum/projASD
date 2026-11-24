package src;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.io.FileWriter;
import java.io.IOException;


public class Tree2D {
    private Tree2D g;
    private Tree2D d;
    private Color couleur;
    private int x;
    private int y;
    private int w;
    private int h;
    private Tree2D predecesseur;

    /**
     *Permet de calculer le poids d'un nœud, afin de l'envoyer dans l'avl
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
        this.w = weight;
        this.h = height;
    }

    /**
     *Constructeur d'un nœud quelconque
     * @param x
     * @param y
     * @param weight
     * @param height
     */
    public Tree2D (int x, int y, int weight, int height, Tree2D pred){
        this.x = x;
        this.y = y;
        this.w = weight;
        this.h = height;
        this.predecesseur = pred;
    }

    /**
     *Constructeur d'une feuille
     * @param x
     * @param y
     * @param w
     * @param h
     * @param couleur
     */
    public Tree2D (int x, int y, int w, int h, Color couleur, Tree2D pred){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.couleur = couleur;
        this.predecesseur = pred;
    }



    public String noeud2String(){
        String x = String.valueOf(this.getX());
        String y = String.valueOf(this.getY());
        // Voir pour ajouter les var nécéssaire à la reproduction
        return x + " " + y;
    }


    /**
     *
     * @param type
     */
    public void chooseDivision(String type){
        if(type.equals("A")){
            if(((w/h)>2) || ((h/w) <= 2)){ // Cas chooseDivision sur X
                Tree2D Gauche = new Tree2D(w/2, h, x, y, this);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w/2, h, x+w/2 , y, this);    // Attention à voir ce que ça donne si x, y impair
                this.setG(Gauche);
                this.setD(Droit);
            }
            else{ // chooseDivision sur Y
                Tree2D Gauche = new Tree2D(w, h/2 , x, y, this);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w, h/2, x , y+h/2, this);    // Attention à voir ce que ça donne si x, y impair
                this.setG(Gauche);
                this.setD(Droit);
            }
        } else if (type.equals("B")) {
            if((w/h)>1){ // Cas chooseDivision sur X
                Tree2D Gauche = new Tree2D(w/2, h, x, y, this);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w/2, h, x+w/2 , y, this);    // Attention à voir ce que ça donne si x, y impair
                this.setG(Gauche);
                this.setD(Droit);
            }
            else{
                Tree2D Gauche = new Tree2D(w, h/2 , x, y, this);               // Attention à voir ce que ça donne si x, y impair
                Tree2D Droit = new Tree2D(w, h/2, x , y+h/2, this);    // Attention à voir ce que ça donne si x, y impair
                this.setG(Gauche);
                this.setD(Droit);
            }
        }
        else{ // Entrée du type incorrect
            System.out.println("Le type de chooseDivision est incorrect.");
        }
    }
    /**
     * Procédure qui parcourt l'arbre à la recherche de nœud sans fils. Si un nœud est sans fils, on remonte à son parent et on crée une nouvelle feuille.
     */
    public void toImage(Image image){
         if (this.getG() == null && this.getD() == null){
             /*
             si la feuille est à gauche de son prédécesseur
              */
             if (this.equals(this.getPredecesseur().getG())) {
                 this.getPredecesseur().getG() = new Tree2D(x, y, w, h, chooseColor, this.getPredecesseur());  //on aura une fonction dans le main qui va permettre de savoir la couleur
                 // Création du rectangle sur fils gauche
                 // Variables
                 int startX = this.getPredecesseur().getG().getX();
                 int startY = this.getPredecesseur().getG().getY();
                 int endX = this.getPredecesseur().getG().getX() + this.getPredecesseur().getG().getW();
                 int endY = this.getPredecesseur().getG().getY() + this.getPredecesseur().getG().getH();
                 Color color = this.getPredecesseur().getG().getCouleur();
                 // Création
                 image.setRectangle(
                         startX,
                         endX,
                         startY,
                         endY,
                         color);
             }
             /*
             si la feuille est à droite de son prédécesseur
              */
             else {
                 this.getPredecesseur().getD() = new Tree2D(x, y, w, h, chooseColor, this.getPredecesseur());  //on aura une fonction dans le main qui va permettre de savoir la couleur
                 // Création du rectangle sur fils droit
                 // Variables
                 int startX = this.getPredecesseur().getD().getX();
                 int startY = this.getPredecesseur().getD().getY();
                 int endX = this.getPredecesseur().getD().getX() + this.getPredecesseur().getD().getW();
                 int endY = this.getPredecesseur().getD().getY() + this.getPredecesseur().getD().getH();
                 Color color = this.getPredecesseur().getD().getCouleur();
                 // Création
                 image.setRectangle(
                         startX,
                         endX,
                         startY,
                         endY,
                         color);
             }
         }
         if(!(this.getG() == null)){
             this.getG().toImage(image);
         }
         if(!(this.getD() == null)){
             this.getD().toImage(image);
         }

    }

    /**
     * Fonction sauvegardant le Tree2D dans un fichier texte. En parcour largeur.
     */
    public void sauvegarde(String fileName){
        // Variables :
        try {
            FileWriter writer = new FileWriter(fileName);
            Queue<Tree2D> Q = new LinkedList<Tree2D>();
            Tree2D s;
            String noeud = "";
            int compteur = 0;
            // ------------

            Q.add(this);
            while(!(Q.isEmpty())){
                s = Q.poll(); // Retourne le premier element de la File et le retire.
                noeud = s.noeud2String();
                writer.write(compteur + " " + noeud + System.lineSeparator());
                compteur = compteur + 1;
                if(!(this.getG() == null)){
                    Q.add(this.getG());
                }
                if(!(this.getD() == null)){
                    Q.add(this.getD());
                }
            }
            writer.close();
            System.out.println("Sauvegarde Réussite : " + fileName);
        }
        catch(IOException e){
            System.out.println("Sauvegarde Impossible");
            e.printStackTrace();
        }


    }

    /**
     *Getters
     */

    public int getH() {
        return h;
    }

    public Tree2D getD() {
        return d;
    }

    public int getW() {
        return w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getCouleur() {
        return couleur;
    }

    public Tree2D getG() {
        return g;
    }

    public Tree2D getPredecesseur() {
        return predecesseur;
    }

    /**
     *Setters
     */

    public void setD(Tree2D d) {
        this.d = d;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setG(Tree2D g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setPredecesseur(Tree2D predecesseur) {
        this.predecesseur = predecesseur;
    }

    public void setY(int y) {
        this.y = y;
    }
}