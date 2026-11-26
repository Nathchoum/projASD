package src;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String[]> tableau;

    public static List<String[]> lis(String filename) throws IOException {
        List<String> listeLignes = Files.readAllLines(Paths.get(filename));
        String[] tabLignes = listeLignes.toArray(new String[0]);
        List<String[]> tousLesElements = new ArrayList<>();
        for (String ligne : tabLignes) {
            String[] elementsLigne = ligne.split("\\s+");
            tousLesElements.add(elementsLigne);
        }
        return tousLesElements;
    }

    public static void generateTree(int largeur, int hauteur, int nbFeuillesAdiviser, int minDimensionCoupe, int largeurLigne, char variante, String filename) throws IOException {
        // étape 1 : récupère les arguments d'entrée
        tableau = lis(filename);
        //étape 2 : création tree2D ( nom origine ) avec les paramètres de w et de h donné en entrée
        Tree2D racine = new Tree2D(largeur, hauteur);
        // On insère la première feuille dans l'AVL
        AVL avlracine = new AVL(racine.CalculPoids(), racine);

        // (début de la boucle, tant que nbNoeudADIviser n'est pas atteint)
        for (int j = 0; j < nbFeuillesAdiviser; j++) {
            Tree2D noeudACouper = avlracine.chooseFeuille(minDimensionCoupe);
            if (noeudACouper != null) {
                noeudACouper.chooseDivision(variante, j, tableau);

                // étape 3 : calculPoids du noeud, puis créer l'AVL avec ce poids
                // Besoin d'actualisé l'avlracine à chaque appelle de "ajouter()" sinon on perd une partie des informations
                avlracine = avlracine.ajouter(noeudACouper.getD().CalculPoids(), noeudACouper.getD());
                avlracine = avlracine.ajouter(noeudACouper.getG().CalculPoids(), noeudACouper.getG());

            }
        }

        // Le Tree2D est complet on crée l'image
        Image image = new Image(largeur, hauteur);
        racine.toImage(image, largeurLigne);
        try {
            image.save("Michel.png");
        } catch (IOException e) {
            System.err.println("Impossible de générer l'image. " + e.getMessage());
        }
    }


    public static void generateBetterTree(int largeur, int hauteur, int nbFeuillesAdiviser, int minDimensionCoupe, int largeurLigne, char variante, String filename) throws IOException {
        // étape 1 : récupère les arguments d'entrée
        tableau = lis(filename);
        //étape 2 : création tree2DP avec les parametress de w et de h donné en entrée
        Tree2DP racine = new Tree2DP(largeur, hauteur);
        //étape 3 : On crée la Pile et on y ajoute la première feuille
        Tree2Pile pile = new Tree2Pile();
        pile.ajouter(racine);

        // début de la boucle, tant que nbNoeudADIviser n'est pas atteint
        for (int j = 0; j < nbFeuillesAdiviser; j++) {
            Tree2DP noeudACouper = pile.chooseFeuille(minDimensionCoupe);
            if (noeudACouper != null) {
                noeudACouper.chooseDivision(variante, j, tableau);

                // On empile les nouveaux noeuds
                pile.ajouter(noeudACouper.getG());
                pile.ajouter(noeudACouper.getD());
            }
            //Une fois l'arbre fini, on crée l'image.

        }
        Image image = new Image(largeur, hauteur);
        racine.toImage(image, largeurLigne);
        try {
            image.save("Resultat.png");
        } catch (IOException e) {
            System.err.println("Impossible de générer l'image. " + e.getMessage());
        }

    }

    public static void main(String[] args)throws IOException {
            if (args.length != 8) {
                System.err.println("Usage : Erreur : Pas le bon nombre de paramètres");
                return;
            } else {

                int strategie = Integer.parseInt(args[0]);
                int largeur = Integer.parseInt(args[1]);
                int hauteur = Integer.parseInt(args[2]);
                int nbFeuillesAdiviser = Integer.parseInt(args[3]);
                int minDimensionCoupe = Integer.parseInt(args[4]);
                int largeurLigne = Integer.parseInt(args[5]);
                char variante = args[6].charAt(0);
                String filename = args[7];

                if (strategie == 1) {
                    Main.generateTree(largeur, hauteur, nbFeuillesAdiviser, minDimensionCoupe, largeurLigne, variante, filename);
                }
                else if(strategie == 2){
                    Main.generateBetterTree(largeur, hauteur, nbFeuillesAdiviser, minDimensionCoupe, largeurLigne, variante, filename);
                }
                else{
                    System.err.println("Stratégie inconnue.");
                }
            }

    }
}
