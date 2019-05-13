
Le programme est structuré de la façon suivante :

Le Main sert de lanceur, il ne connaît pas le jeu qu'il va lanceur mais s'attends
à ce qu'il possède certaines méthode (Classe abstraite Game) pour pouvoir lancer le jeu.
Le jeu hérite de Game possède des composants (implemente GameComponant) tels le menu (Classe Menu) qui
charge une sauvegarde, ou choisi de créer une grille, et le coeur du jeu (GameCore) qui
lit une sauvegarde (SaveLoader), Crée une nouvelle partie (CreateGame) et lance
l'algorithme de parcours en fonction des choix de l'utilisateur.

Le jeu est lancé dans une fenêtre qui à la particularité de n'exister qu'à partir de
son fond d'écran (Background), et la labyrinthe est représenté sous la forme d'une
grille (Grille) qui contient des cases (Cases).

Si des erreurs surviennent ou on veut afficher des messages, les classes popup et
exceptions ont étés crées pour ce faire.