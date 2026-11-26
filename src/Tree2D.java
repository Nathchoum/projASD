package src;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class Tree2D {
    private Tree2D g;
    private Tree2D d;
    private Color couleur;
    private int x;
    private int y;
    private int w;
    private int h;
    private char axeDeDecoupe;
    private int coordDeDecoupe;

    /**
     * Constructeur du nœud racine
     * @param width
     * @param height
     */
    public Tree2D (int width, int height){
        this.w = width;
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
            System.out.println("ah non, tu t'es trompé...");
            return null;
        }
    }

    public boolean estFeuille(){
        return (this.getG()==null && this.getD()==null);
    }


    public void toImage(Image image, int largeurLigne){

        if(this.estFeuille()) {
            int startX = this.getX();
            int startY = this.getY();
            int endX = this.getX() + this.getW();
            int endY = this.getY() + this.getH();

            image.setRectangle(startX, endX, startY, endY, this.couleur);

        }
        else{
            this.getG().toImage(image, largeurLigne);
            this.getD().toImage(image, largeurLigne);

            // Création des lignes
            if(this.getAxeDeDecoupe() == 'X'){
                image.setRectangle(this.getCoordDeDecoupe() - (largeurLigne/2) , this.getCoordDeDecoupe() - (largeurLigne/2) + largeurLigne, this.getY(), this.getY() + this.getH(), Color.BLACK );
            }
            else{
                image.setRectangle(this.getX(), this.getX() + this.getW(), this.getCoordDeDecoupe() - (largeurLigne/2), this.getCoordDeDecoupe() - (largeurLigne/2) + largeurLigne, Color.BLACK );
            }
        }
    }

    public void chooseDivision(char variante, int ligne, List<String[]> pablo) {
        int coX = this.getX() + (int)Math.floor(this.getW()*Float.parseFloat(pablo.get(ligne)[1]));
        int coY = this.getY() + (int)Math.floor(this.getH()*Float.parseFloat(pablo.get(ligne)[2]));
        // Problème de calcul si on ne fait pas les divisions en double.
        if(variante == 'A'){
            if((double)this.getW() / (double)this.getH() > 0.5 || (double)this.getH()/ (double)this.getW() <= 2){
                // On set les attributs de découpe sur les noeuds pour pouvoir tracer les traîts
                this.setAxeDeDecoupe('X');
                this.setCoordDeDecoupe(coX);

                this.setG(new Tree2D(this.getX(), this.getY(), coX- this.getX(), this.getH(), this.chooseColor(pablo.get(ligne)[3])));
                this.setD(new Tree2D(coX, this.getY(),  this.getW()-coX+ this.getX(), this.getH(), this.chooseColor(pablo.get(ligne)[4])));
            }
            else {
                // On set les attributs de découpe sur les noeuds pour pouvoir tracer les traîts
                this.setAxeDeDecoupe('Y');
                this.setCoordDeDecoupe(coY);

                this.setG(new Tree2D(this.getX(), this.getY(), this.getW(), coY - this.getY(), this.chooseColor(pablo.get(ligne)[3])));
                this.setD(new Tree2D(this.getX(), coY, this.getW(), this.getH()-coY + this.getY() ,this.chooseColor(pablo.get(ligne)[4])));
            }
        }
        else{
            if((double)this.getW() / (double)this.getH() > 1 ){ // Axe X
                // On set les attributs de découpe sur les noeuds pour pouvoir tracer les traîts
                this.setAxeDeDecoupe('X');
                this.setCoordDeDecoupe(coX);

                this.setG(new Tree2D(this.getX(), this.getY(), coX- this.getX(), this.getH(), this.chooseColor(pablo.get(ligne)[3])));
                this.setD(new Tree2D(coX, this.getY(),  this.getW()-coX+ this.getX(), this.getH(), this.chooseColor(pablo.get(ligne)[4])));
            }
            else { // Axe Y
                // On set les attributs de découpe sur les noeuds pour pouvoir tracer les traîts
                this.setAxeDeDecoupe('Y');
                this.setCoordDeDecoupe(coY);

                this.setG(new Tree2D(this.getX(), this.getY(), this.getW(), coY - this.getY(), this.chooseColor(pablo.get(ligne)[3])));
                this.setD(new Tree2D(this.getX(), coY, this.getW(), this.getH()-coY + this.getY() ,this.chooseColor(pablo.get(ligne)[4])));
            }
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

    public char getAxeDeDecoupe() { return this.axeDeDecoupe;}

    public int getCoordDeDecoupe() {
        return coordDeDecoupe;
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

    public void setAxeDeDecoupe(char axeDeDecoupe) {
        this.axeDeDecoupe = axeDeDecoupe;
    }

    public void setCoordDeDecoupe(int coordDeDecoupe) {
        this.coordDeDecoupe = coordDeDecoupe;
    }
}
