package src;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String[]> pablo;

    public static List<String[]> lis(String filename) throws IOException{
        List<String> listeLignes = Files.readAllLines(Paths.get(filename));
        String[] tabLignes = listeLignes.toArray(new String[0]);
        List<String[]> tousLesElements = new ArrayList<>();
        for (String ligne : tabLignes) {
            String[] elementsLigne = ligne.split("\\s+");
            tousLesElements.add(elementsLigne);
        }
        return tousLesElements;
    }

    public static void chooseDivisionA(Tree2D arbre, int ligne) {
        int coX = arbre.getX() + (int)Math.floor(arbre.getW()*Float.parseFloat(pablo.get(ligne)[1]));
        int coY = arbre.getY() + (int)Math.floor(arbre.getH()*Float.parseFloat(pablo.get(ligne)[2]));
        if(arbre.getW() / arbre.getH() > 0.5 || arbre.getH()/ arbre.getW() <= 2){
            arbre.setG(new Tree2D(arbre.getX(), arbre.getY(), coX- arbre.getX(), arbre.getH(), arbre.chooseColor(pablo.get(ligne)[3])));
            arbre.setD(new Tree2D(coX, arbre.getY(),  arbre.getW()-coX+ arbre.getX(), arbre.getH(), arbre.chooseColor(pablo.get(ligne)[4])));
        }
        else {
            arbre.setG(new Tree2D(arbre.getX(), arbre.getY(), arbre.getW(), coY - arbre.getY(), arbre.chooseColor(pablo.get(ligne)[3])));
            arbre.setD(new Tree2D(arbre.getX(), coY, arbre.getW(), arbre.getH()-coY + arbre.getY() ,arbre.chooseColor(pablo.get(ligne)[4])));
        }
    }

  /*
    public static void chooseDivisionB(float prctX, float prctY, Color couleurG, Color couleurD) {
        int coX = (int)(prctX * w);
        int coY = (int)(prctY * h);
        if((w/h)>1){ // Cas chooseDivision sur X
            Tree2D Gauche = new Tree2D(x, y, coX, h, couleurG );
            Tree2D Droit = new Tree2D(x + coX, y,w-coX+x, h, couleurD);
            this.setG(Gauche);
            this.setD(Droit);
        }
        else{
            Tree2D Gauche = new Tree2D(x, y, w, coY, couleurG );
            Tree2D Droit = new Tree2D(x, y +coY,w, h-coY+y, couleurD);
            this.setG(Gauche);
            this.setD(Droit);
        }
    }
    */
    public static void main(String[] args)throws IOException {
        if (args.length != 8) {
            System.err.println("Usage : Erreur : Pas le bon nombre de paramètres");
            return;
        }

            // generateTree()
            // étape 1 : récupère les arguments d'entrée

            int strategie = Integer.parseInt(args[0]);
            int largeur = Integer.parseInt(args[1]);
            int hauteur = Integer.parseInt(args[2]);
            int nbFeuillesAdiviser = Integer.parseInt(args[3]);
            int minDimensionCoupe = Integer.parseInt(args[4]);
            float largeurLigne = Float.parseFloat(args[5]);
            char variante = args[6].charAt(0);
            String filename = args[7];


            pablo = lis(filename);

            //étape 2 : création tree2D ( nom origine ) avec les parametress de w et de h donné en entrée
            Tree2D racine = new Tree2D(largeur, hauteur);
            //étape 2 + : on coupe sur la racine, et on crée l'avl avant la boucle
            AVL avlracine = new AVL(racine.CalculPoids(), racine);
/*
            System.out.println("-avlRacine/Poids/Origine/Couleur-");
            System.out.println(avlracine.getPoids());
            System.out.println(avlracine.getOrigine());
            System.out.println(avlracine.getOrigine().getCouleur());
            System.out.println("------------------------------------");


            String[] ligne1 = tousLesElements.get(0);

            if(variante =='A'){
                System.out.println("hihi je suis passé par là");
                racine.chooseDivisionA(Float.parseFloat(tousLesElements.getFirst()[1]), Float.parseFloat(tousLesElements.get(0)[2]), racine.chooseColor(tousLesElements.get(0)[3]), racine.chooseColor(tousLesElements.get(0)[4]));
            }
            else{
                racine.chooseDivisionB(Float.parseFloat(tousLesElements.getFirst()[1]), Float.parseFloat(tousLesElements.get(0)[2]), racine.chooseColor(tousLesElements.get(0)[3]), racine.chooseColor(tousLesElements.get(0)[4]));
            }
            System.out.println("Poids noeud gauche : " + racine.getG().CalculPoids());
            System.out.println("Poids noeud droite : " + racine.getD().CalculPoids());
            System.out.println("------------------------------------------------------------");

            System.out.println("noeud gauche : " + racine.getG());
            System.out.println("noeud gauche couleur : " + racine.getG().getCouleur());


            System.out.println("------------------------------------------------------------");

            avlracine.ajouter(racine.getG().CalculPoids(), racine.getG()); // Deviens la nouvelle racine
            avlracine.ajouter(racine.getD().CalculPoids(), racine.getD()); // Deviens son fils.

            System.out.println("-avlRacine/Poids/Origine/Couleur Après 1ere coupure-");
            System.out.println(avlracine.getPoids());
            System.out.println(avlracine.getOrigine());
            System.out.println(avlracine.getOrigine().getCouleur());

            if(!(avlracine.getG().estVide())){
                System.out.println("---------------Gauche--------------------");
                System.out.println(avlracine.getG().getPoids());
                System.out.println(avlracine.getG().getOrigine());
                System.out.println(avlracine.getG().getOrigine().getCouleur());
            }
            else{
                System.out.println("Gauche est vide");
            }

            if(!(avlracine.getD().estVide())){
                System.out.println("---------------Droit----------------");
                System.out.println(avlracine.getD().getPoids());
                System.out.println(avlracine.getD().getOrigine());
                System.out.println(avlracine.getD().getOrigine().getCouleur());
            }
            else{
                System.out.println("Droite est vide");
            }
*/



            // (début de la boucle, tant que nbNoeudADIviser n'est pas atteint)
            for (int j =0; j<nbFeuillesAdiviser; j++){
                System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii; " + j);
                System.out.println(avlracine.getOrigine());
                System.out.println(avlracine.getOrigine().getCouleur());
                Tree2D noeudACouper = avlracine.chooseFeuille(minDimensionCoupe);
                if (noeudACouper != null){

                    System.out.println(noeudACouper.getCouleur());
                    if(variante == 'A'){
                        chooseDivisionA(noeudACouper, j);
                    }
                    /*else{
                        chooseDivisionB(noeudACouper,j);
                    }*/
                    // étape 3 : calculPoids du noeud, puis créer l'AVL avec ce poids
                    avlracine.ajouter(noeudACouper.getD().CalculPoids(), noeudACouper.getD());
                    avlracine.ajouter(noeudACouper.getG().CalculPoids(), noeudACouper.getG());

                }
            }
            // Le Tree2D est complet on crée l'image
            Image image = new Image(largeur, hauteur);
            racine.toImage(image);
    }

}
