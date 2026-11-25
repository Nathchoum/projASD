package src;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Tree2D {
    private Tree2D g;
    private Tree2D d;
    private Color couleur;
    private int x;
    private int y;
    private int w;
    private int h;

    /**
     * Constructeur du nœud racine
     * @param weight
     * @param height
     */
    public Tree2D (int weight, int height){
        this.w = weight;
        this.h = height;
        this.x = 0;
        this.y = 0;
        this.couleur = new Color(255,255,255);
    }

    /**
     *Constructeur d'un noeud
     * @param x
     * @param y
     * @param w
     * @param h
     * @param couleur
     */
    public Tree2D (int x, int y, int w, int h, Color couleur){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.couleur = couleur;
    }

    /**
     *Permet de calculer le poids d'un nœud, afin de l'envoyer dans l'avl
     * @return le poids d'un nœud
     */
    public double CalculPoids(){
        double denominateur = Math.pow((w+h),1.5);
        return ( (w * h) / denominateur);
    }

    /**
     *
     * @param prctX
     * @param prctY
     * @param couleurG
     * @param couleurD
     */
    public void chooseDivisionA(float prctX, float prctY, Color couleurG, Color couleurD) {
        int coX = (int)(prctX * w);
        int coY = (int)(prctY * h);
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
        int coX = (int)(prctX * w);
        int coY = (int)(prctY * h);
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

    public Color chooseColor(String lettre){
        if (lettre.equals("r")){
            Color red = new Color(255, 0,0);
            return red;
        }
        if (lettre.equals("n")){
            Color noir = new Color(0, 0,0);
            return noir;
        }
        if (lettre.equals("w")){
            Color blanc = new Color(255, 255,255);
            return blanc;
        }
        if (lettre.equals("j")){
            Color jaune = new Color(255, 255,0);
            return jaune;
        }
        if (lettre.equals("b")){
            Color bleu = new Color(0, 0,255);
            return bleu;
        }
        if (lettre.equals("m")){
            return this.couleur;
        }
        else {
            System.out.println("ah non, tu t'es trompé");
            return null;
        }
    }

    public void toImage(Image image){
        // parcours l'arbre final
        Queue<Tree2D> Q = new LinkedList<Tree2D>();
        Tree2D s;
        // ------------

        Q.add(this);
        while(!(Q.isEmpty())){
            s = Q.poll(); // Retourne le premier element de la File et le retire.
            // Action sur le noeud
            int startX = this.getX();
            int startY = this.getY();
            int endX = this.getX() + this.getW();
            int endY = this.getY() + this.getH();

            image.setRectangle(startX,endX,startY,endY,this.couleur);


            if(!(this.getG() == null)){
                Q.add(this.getG());
            }
            if(!(this.getD() == null)){
                Q.add(this.getD());
            }
        }
        try{
            image.save("Michel");
        }
        catch (IOException e) {
            System.err.println("Impossible de générer l'image. " + e.getMessage());
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

    public void setY(int y) {
        this.y = y;
    }
}
