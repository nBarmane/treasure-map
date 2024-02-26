# Carte aux trésors

C'est une application Java qui permet d'importer les entrées du jeu "Carte aux trésors" dans un premier temps, puis lance une simulation du jeu et permet de générer un fichier de sortie qui contient l'état final du jeu.

#### Bibliothèques utilisées :
>- **Lombok :** utilisé pour la gérération des setters/getters...
>- **JUnit :** utilisé pour les tests unitaires

## Pour démarrer le projet

Vous pouvez utiliser l'éditeur de text que vous voulez, il faudra :
- Récupérer les dépendances avec Maven,
- Puis exécuter les tests pour vérifier le bon fonctionnement du programme.

## Effectuer des tests personnalisés ?

C'est assez simple!
Dans la classe Main du projet vous pouvez indiquer le chemin (relatif ou absolu) vers un fichier d'entrée, puis indiquer le chemin pour le fichier de sortie (ou bien laissez le chemin présent sur le projet, le fichier resultat sera dans le dossier "output" dans la racine du projet).
