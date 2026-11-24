package src2;
import java.awt.*;

public class Tree2D {
    private Tree2D g;
    private Tree2D d;
    private Color couleur;
    private float x;
    private float y;
    private float w;
    private float h;

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
     *Constructeur d'un noeud
     * @param x
     * @param y
     * @param w
     * @param h
     * @param couleur
     */
    public Tree2D (float x, float y, float w, float h, Color couleur){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.couleur = couleur;
    }

    /**
     *
     * @param prctX
     * @param prctY
     * @param couleurG
     * @param couleurD
     */
    public void chooseDivisionA(float prctX, float prctY, Color couleurG, Color couleurD) {
        float coX = prctX * w;
        float coY = prctY * h;
        if (((w / h) > 2) || ((h / w) <= 2)) { // Cas chooseDivision sur X
            Tree2D Gauche = new Tree2D(x, y, coX, h, couleurG );
            Tree2D Droit = new Tree2D(x + coX, y,w-coX, h, couleurD);
            this.setG(Gauche);
            this.setD(Droit);
        } else { // chooseDivision sur Y
            Tree2D Gauche = new Tree2D(x, y, w, coY, couleurG );
            Tree2D Droit = new Tree2D(x, y +coY,w, h-coY, couleurD);
            this.setG(Gauche);
            this.setD(Droit);
        }
    }

    /**
     *
     * @param prctX
     * @param prctY
     * @param couleurG
     * @param couleurD
     */
    public void chooseDivisionB(float prctX, float prctY, Color couleurG, Color couleurD) {
        float coX = prctX * w;
        float coY = prctY * h;
            if((w/h)>1){ // Cas chooseDivision sur X
                Tree2D Gauche = new Tree2D(x, y, coX, h, couleurG );
                Tree2D Droit = new Tree2D(x + coX, y,w-coX, h, couleurD);
                this.setG(Gauche);
                this.setD(Droit);
            }
            else{
                Tree2D Gauche = new Tree2D(x, y, w, coY, couleurG );
                Tree2D Droit = new Tree2D(x, y +coY,w, h-coY, couleurD);
                this.setG(Gauche);
                this.setD(Droit);
            }
    }

    /**
     *Getters
     */

    public float getH() {
        return h;
    }

    public Tree2D getD() {
        return d;
    }

    public float getW() {
        return w;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Color getCouleur() {
        return couleur;
    }

    public Tree2D getG() {
        return g;
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

    public void setH(float h) {
        this.h = h;
    }

    public void setW(float w) {
        this.w = w;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
