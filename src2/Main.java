package src2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* TODO : 1) Changer les types des Args.
          2) passer de la lettre à la couleur
          3) Refaire toImage()
*/


public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage : java Main <chemin_image>");
            return;
        }

        try {
            // generateTree()
            // étape 1 : récupère les arguments d'entrée
            String strategie = args[0];
            String largeur = args[1];
            String hauteur = args[2];
            String nbFeuillesAdiviser = args[3];
            String minDimensionCoupe = args[4];
            String largeurLigne = args[5];
            String variante = args[6];
            String filename = args[7];

            List<String> listeLignes = Files.readAllLines(Paths.get(filename));
            String[] tabLignes = listeLignes.toArray(new String[0]);
            List<String[]> tousLesElements = new ArrayList<>();
            for(String ligne : tabLignes){
                String[] elementsLigne = ligne.split("\\s+");
                tousLesElements.add(elementsLigne);
            }

            //étape 2 : création tree2D ( nom origine ) avec les parametress de w et de h donné en entrée
            Tree2D racine = new Tree2D(largeur, hauteur);
            //étape 2 + : on coupe sur la racine, et on crée l'avl avant la boucle
            AVL avlracine = new AVL();
            avlracine.ajouter(racine.CalculPoids(), racine);

            int nbDivision = 0;
            String[] ligne1 = tousLesElements.get(0);
            if(variante == "A"){
                racine.chooseDivisionA(tousLesElements.get(nbDivision)[1], tousLesElements.get(nbDivision)[2], tousLesElements.get(nbDivision)[3], tousLesElements.get(nbDivision)[4]);
                nbDivision++;
            }
            else{
                racine.chooseDivisionB(tousLesElements.get(nbDivision)[1], tousLesElements.get(nbDivision)[2], tousLesElements.get(nbDivision)[3], tousLesElements.get(nbDivision)[4]);
                nbDivision++;
            }
            avlracine.ajouter(racine.getG().CalculPoids(), racine.getG());
            avlracine.ajouter(racine.getD().CalculPoids(), racine.getD());

            // (début de la boucle, tant que nbNoeudADIviser n'est pas atteint)
            while(nbDivision < nbFeuillesAdiviser){
                Tree2D noeudACouper = avlracine.chooseFeuille();
                if(variante == "A"){
                    noeudACouper.chooseDivisionA(tousLesElements.get(nbDivision)[1], tousLesElements.get(nbDivision)[2], tousLesElements.get(nbDivision)[3], tousLesElements.get(nbDivision)[4]);
                    nbDivision++;
                }
                else{
                    noeudACouper.chooseDivisionB(tousLesElements.get(nbDivision)[1], tousLesElements.get(nbDivision)[2], tousLesElements.get(nbDivision)[3], tousLesElements.get(nbDivision)[4]);
                    nbDivision++;
                }
                // étape 3 : calculPoids du noeud, puis créer l'AVL avec ce poids
                avlracine.ajouter(noeudACouper.getD().CalculPoids(), noeudACouper.getD());
                avlracine.ajouter(noeudACouper.getG().CalculPoids(), noeudACouper.getG());

            }
            // Le Tree2D est complet on crée l'image
            racine.toImage();


        }

        catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
        }
    }
}
