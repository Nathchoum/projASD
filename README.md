#  Art Abstrait et Arbres
# Projet d’ASD3

Projet réalisé en Java 1.8 par BLONDEAU Nathan et CHUPIN Noa, tous deux étudiants à l'Université de Nantes : Sciences et Techniques, pour le module d'Algorithmiques et Structures de Données 3.

Le projet à pour but de généré à partir de structure vue en cours, des œuvres s'inspirant du peintre Piet Mondrian.

### Stratégie 1 : 
  Notre stratégie 1 se base sur l'implémentation du problème avec :
  
      Un KDtree : ici un Tree2D qui sert à conserver les informations des nœuds comme sa position ( x, y ), sa feuille (w, h ) ses enfants ( G, D), sa couleur et enfin son Axe de découpe et sa coordonnée de découpe si c'est un nœud parent.

      Un AVL : ici AVL qui sert à conserver les valeurs des poids, ainsi que le nœud correspondant, des Tree2D de manière efficace, car nous avons besoin d'effectuer une recherche de maximum sur les poids des feuilles à chaque découpe. Ainsi, comme un AVL est très efficace dans la recherche de maximum. Cela nous permettra d'obtenir une meilleur performance sur le programme dans sa globalité.

      Notre stratégie 1 fonctionne de la manière suivante : 
        1) Nous récupérons les arguments entrée dans le programme, puis nous créons un Tree2D avec comme racine sa première feuille.
        2) Comme nous avons notre premier nœud dans notre Tree2D, il faut calculer son poids, créer un AVL vide, et ajouter ce nœud et son poids dans ce dernier.
        3 ) Une fois la base initialisé, tant qu'on a pas dépasser le nombre de feuilles à diviser, nous allons faire, à tour de rôles les découpes sur les nœuds possédants les poids les plus élever, en suivant les     instructions dans le fichier mis en argument.
        4 ) L'Arbre est alors terminé et nous avons plus qu'à créer l'image en faisant un parcours en largeur de cet Arbre et en dessinant des rectangles avec la classe Image.java

### Stratégie 2 : 
  Notre stratégie 2 se base sur l'implémentation du problème avec :
  
        Un KDtree : ici un Tree2DP qui sert à conserver les informations des nœuds comme sa position ( x, y ), sa feuille (w, h ) ses enfants ( G, D), sa couleur et enfin son Axe de découpe, sa coordonnée de découpe si c'est un nœud parent et le poids de sa feuille !

        Une Pile : Tree2Pile qui est une pile de Tree2DP qui nous sert à ranger les nœuds par poids dans une pile. Sa méthode ajouter(Tree2DP nœud) s'assure que la Pile est trié du plus petit au plus grand ( le min est tout en bas de la pile et son max tout en haut. Ainsi, la récupération du maximum prends un coût constant, mais l'ajout a alors un coût en O(n) avec n le nombre de nœud.   

    Notre stratégie 2 fonctionne de la manière suivante : 
      1) Nous récupérons les arguments entrée dans le programme, puis nous créons un Tree2DP avec comme racine sa première feuille.
      2) Comme nous avons notre premier nœud dans notre Tree2DP, créer un Tree2Pile vide, et on ajoute ce nœud dans ce dernier.
      3 ) Une fois la base initialisé, tant qu'on a pas dépasser le nombre de feuilles à diviser, nous allons faire, à tour de rôles les découpes sur les nœuds possédants les poids les plus élever, en suivant les instructions dans le fichier mis en argument.
      4 ) L'Arbre est alors terminé et nous avons plus qu'à créer l'image en faisant un parcours en largeur de cet Arbre et en dessinant des rectangles avec la classe Image.java.

### Comparaison entre les deux Stratégies.

      La première utilise les structures vue en cours et à une complexité légèrement meilleur comparée à notre stratégie 2 :
      O(nombreLigneFichier + nbFeuillesAdiviser * (log n + 2n) + n) < O(nombreLigneFichier + nbFeuillesAdiviser * (2n)+ n),
      car le ratio entre les méthodes ajouter et chooseFeuille est meilleur du côté de l'AVL. 
      Nous sommes conscient qu'il est possible de mieux optimiser notre Stratégie 1 et dans cas,elle sera nettement plus rapide que la Stratégie 2.


### Conclusion.

    Ce projet nous aura permit de voir dans quel situation un AVL est mit à son avantage et comment nous pourrions les utiliser dans le futur.
    Mais le projet nous a surtout aider à comprendre l'importance des Arbres dans le stockage de données.

 ### Compilation et Exécution du projet.
 
    • La compilation doit se faire, une fois l’archive extraite dans le r´epertoire courant (quel qu’il), avec la commande:
                          javac -source 1.8 -target 1.8 -d BlondeauChupin/bin BlondeauChupin/src/*.java
    • L’exécution doit se faire avec la commande:
                          java -classpath BlondeauChupin/bin Main [args]
    où [args] est la liste d’arguments, telle que précisée dans la Section 2.2.5. Cela signifie que le nom de votre classe contenant le programme principal (main) doit être Main.java.
    

