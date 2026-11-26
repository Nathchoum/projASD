###  Art Abstrait et Arbres
###  Projet d’ASD3

Projet réalisé en Java 1.8 par BLONDEAU Nathan et CHUPIN Noa, tous deux étudiants à l'Université de Nantes : Sciences et Techniques, pour le module d'Algorithmiques et Structures de Données 3.

Le projet à pour but de généré à partir de structure vue en cours, des oeuvres s'inspirant du peintre Piet Mondrian.

# Stratégie 1 : 
  Notre stratégie 1 se base sur l'implémentation du problème avec :
    Un KDtree : ici un Tree2D qui sert à conserver les informations des noeuds comme sa position ( x, y ), sa feuille (w, h ) ses enfants ( G, D), sa couleur et enfin son Axe de découpe et sa coordonnée de découpe si c'est un noeud parent, 

    Un AVL : ici AVL qui sert à conserver les valeurs des poids, ainsi que le noeud correspondant, des Tree2D de manière efficace, car nous avons besoin d'effectuer une recherche de maximum sur les poids des feuilles à chaque découpe. Ainsi, comme un AVL est très efficace dans la recherche de maximum. Cela nous permettra d'obtenir une meilleur performance sur le programme dans sa globalité.

    Notre stratégie 1 fonctionne de la manière suivante : 
      1) Nous récupérons les arguments entrée dans le programme, puis nous créeons un Tree2D avec comme racine sa première feuille.
      2) Comme nous avons notre premier noeud dans notre Tree2D, il faut calculer son poids, créer un AVL vide, et ajouter ce noeud et son poids dans ce dernier.
      3 ) Une fois la base initialisé, tant qu'on a pas dépasser le nombre de feuilles à diviser, nous allons faire, à tour de rôles les découpes sur les noeuds possèdants les poids les plus élever, en suivant les instructions dans le fichier mis en argument.
      4 ) L'Arbre est alors terminé et nous avons plus qu'à créer l'image en faisant un parcours en largeur de cet Arbre et en dessinant des rectangles avec la classe Image.java
