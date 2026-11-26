package src;

import java.awt.*;
import java.util.Stack;

public class Tree2Pile {
    Stack<Tree2DP> pile;

    /**
     * Constructeur permettant de créer une pile vide
     */
    public Tree2Pile(){
        this.pile = new Stack<Tree2DP>();
    }


    /**
     * Méthode permettant d'ajouter un noeud dans la pile, celle-ci vérifie qu'en cas de poids égal :
     * on vérifie que les coordonnées x et y du noeud sont inférieurs à celles du noeud du haut de la pile,dans ce cas on l'ajoute au-dessus de la pile, sinon le noeud sera ajouter en dessous
     * Commplexité : O(n) avec le nombre de noeuds
     * @param noeud, noeud que l'on souhaite ajoute dans la pile
     */
    public void ajouter(Tree2DP noeud){
        //
        if(this.pile.empty()){
            this.pile.add(noeud);
        }
        else{
            Tree2DP top = this.pile.peek();
            Stack<Tree2DP> savePile = new Stack<Tree2DP>();

            while((!(this.pile.empty())) && ((top.getPoids() > noeud.getPoids())
                    || ((top.getPoids() == noeud.getPoids()) && (top.getX() < noeud.getX())
                    || ((top.getPoids() == noeud.getPoids()) && (noeud.getX() == top.getX()) && (top.getY() < noeud.getY()))))){ // On dépile jusqu'à temps qu'on trouve un noeud inférieur au poids du noeud qu'on veut ajouter
                savePile.add(this.pile.pop());
                if(!(this.pile.empty())){
                    top = this.pile.peek();
                }
            } // une fois trouvé, on l'ajoute
            this.pile.add(noeud);
            // On rempile.
            while(!savePile.empty()){
                this.pile.add(savePile.pop());
            }
        }
    }

    /**
     * Méthode permettant de choisir la feuille avec le plus grand poids et que ses dimensions soient supérieurs aux dimensions minimales de découpe
     * Complexité : O(1)
     * @param min, correspond aux dimensions minimales de découpe
     * @return le noeud à découper
     */
    public Tree2DP chooseFeuille(int min) {
        Tree2DP Max = this.pile.peek();
        if (Max.getW() >= min && Max.getH() >= min) {
            return this.pile.pop();
        } else {
            this.pile.pop();
            return null;
        }
    }
}

