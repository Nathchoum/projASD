package src2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage : java Main <chemin_image>");
            return;
        }

        try {
            // étape 1 : récupère les arguments d'entrée
            String strategie = args[0];
            String largeur = args[1];
            String hauteur = args[2];
            String nbFeuillesAdiviser = args[3];
            String minDimensionCoupe = args[4];
            String largeurLigne = args[5];
            String variante = args[6];
            String filename = args[7];

            //étape 2 : création tree2D ( nom origine ) avec les parametress de w et de h donné en entrée
            Tree2D racine = new Tree2D(largeur, hauteur);
            if(variante == "A"){
                racine.chooseDivisionA();
            }
            else{
                racine.chooseDivisionB();
            }
            /*
            étape 2 + : on coupe sur la racine, et on crée l'avl avant la boucle
            (début de la boucle, tant que nbNoeudADIviser n'est pas atteint)
            étape 3 : calculPoids du noeud, puis créer l'AVL avec ce poids
            étape 4 : recherche plsu gros poids dans l'avl
            étape 5 : la recherche va renvoyer le noeud voulu, puis coupure sur ce noeud
            étape 6 : création des 2 fils ajoutés dnas le tree2D
            étape 7 : on reboucle
            étape 8 : génération de l'image à partir du tree2D finale
             */

        }

        catch () {

        }
    }
}
