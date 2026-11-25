package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* TODO : 3) Refaire toImage()
*/


public class Main {
    public static void main(String[] args) {
        if (args.length != 8) {
            System.err.println("Usage : Erreur : Pas le bon nombre de paramètres");
            return;
        }

        try {
            // generateTree()
            // étape 1 : récupère les arguments d'entrée

            int strategie = Integer.parseInt(args[0]);
            int largeur = Integer.parseInt(args[1]);
            int hauteur = Integer.parseInt(args[2]);
            int nbFeuillesAdiviser = Integer.parseInt(args[3]);
            float minDimensionCoupe = Float.parseFloat(args[4]);
            float largeurLigne = Float.parseFloat(args[5]);
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


            System.out.println("-avlRacine/Poids/Origine/Couleur-");
            System.out.println(avlracine.getPoids());
            System.out.println(avlracine.getOrigine());
            System.out.println(avlracine.getOrigine().getCouleur());
            System.out.println("------------------------------------");


            int nbDivision = 0;
            String[] ligne1 = tousLesElements.get(0);
            if(variante == "A"){
                racine.chooseDivisionA(Float.parseFloat(tousLesElements.get(nbDivision)[1]), Float.parseFloat(tousLesElements.get(nbDivision)[2]), racine.chooseColor(tousLesElements.get(nbDivision)[3]), racine.chooseColor(tousLesElements.get(nbDivision)[4]));
                nbDivision++;
            }
            else{
                racine.chooseDivisionB(Float.parseFloat(tousLesElements.get(nbDivision)[1]), Float.parseFloat(tousLesElements.get(nbDivision)[2]), racine.chooseColor(tousLesElements.get(nbDivision)[3]), racine.chooseColor(tousLesElements.get(nbDivision)[4]));
                nbDivision++;
            }

            avlracine.ajouter(racine.getG().CalculPoids(), racine.getG()); // Deviens la nouvelle racine
            avlracine.ajouter(racine.getD().CalculPoids(), racine.getD()); // Deviens son fils.

            System.out.println("-avlRacine/Poids/Origine/Couleur Après 1ere coupure-");
            System.out.println(avlracine.getPoids());
            System.out.println(avlracine.getOrigine());
            System.out.println(avlracine.getOrigine().getCouleur());

            if(avlracine.getG() != null){
                System.out.println("---------------Gauche--------------------");
                System.out.println(avlracine.getG().getPoids());
                System.out.println(avlracine.getG().getOrigine());
                System.out.println(avlracine.getG().getOrigine().getCouleur());
            }
            else{
                System.out.println("Gauche est vide");
            }

            System.out.println("---------------Droit----------------");
            System.out.println(avlracine.getD().getPoids());
            System.out.println(avlracine.getD().getOrigine());
            System.out.println(avlracine.getD().getOrigine().getCouleur());



            // (début de la boucle, tant que nbNoeudADIviser n'est pas atteint)
            while(nbDivision < nbFeuillesAdiviser){
                System.out.println(avlracine.getOrigine());
                System.out.println(avlracine.getOrigine().getCouleur());
                Tree2D noeudACouper = avlracine.chooseFeuille();
                System.out.println(noeudACouper.getCouleur());
                if(variante == "A"){
                    noeudACouper.chooseDivisionA(Float.parseFloat(tousLesElements.get(nbDivision)[1]), Float.parseFloat(tousLesElements.get(nbDivision)[2]), noeudACouper.chooseColor(tousLesElements.get(nbDivision)[3]), noeudACouper.chooseColor(tousLesElements.get(nbDivision)[4]));
                    nbDivision++;
                }
                else{
                    noeudACouper.chooseDivisionB(Float.parseFloat(tousLesElements.get(nbDivision)[1]), Float.parseFloat(tousLesElements.get(nbDivision)[2]), noeudACouper.chooseColor(tousLesElements.get(nbDivision)[3]), noeudACouper.chooseColor(tousLesElements.get(nbDivision)[4]));
                    nbDivision++;
                }
                // étape 3 : calculPoids du noeud, puis créer l'AVL avec ce poids
                avlracine.ajouter(noeudACouper.getD().CalculPoids(), noeudACouper.getD());
                avlracine.ajouter(noeudACouper.getG().CalculPoids(), noeudACouper.getG());

            }
            // Le Tree2D est complet on crée l'image
            Image image = new Image(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            racine.toImage(image);


        }

        catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
        }
    }
}
