# Killer

[Maud Van Dorssen](https://github.com/modvd), [Martin Ars](https://github.com/Decator), [Alexis Claveau](https://github.com/Faenya)

Ce projet rentre dans le cadre de notre cours de "Middleware" au sein de notre formation de Master 2 ALMA à l'Université de Nantes

Le Jeu du Killer est un Projet Java utilisant l'interface de programmation RMI

## Structure du projet

Ce projet est séparé en trois parties : 
* Serveur
* Service
* Client

La partie serveur permet de gérer la partie fonctionelle du jeu du Killer. Il permet par exemple d'ajouter des joueurs, de lancer les dés, de passer le tour, etc.

La partie service offre trois interfaces qui sont utilisées par le client et le serveur.

La partie client gére l'affichage visuel du jeu et les interactions entre l'utilisateur et le jeu.

Les instances d'objet sont distants étant donné que nous utilisons l'interface de programmation RMI. De plus, nous avons implémenté le pattern Observer/Observable pour maintenir à jour les clients selon l'avancement de la partie. La partie client et la partie serveur utilisent les interfaces de la partie service grâce à l'ajout de dépendances Maven.

## Lancer le jeu

Pour lancer le jeu, il faut d'abord lancer Server.jar puis lancer quatre fois Client.jar. La partie se lancera lorsque 4 clients auront rejoint la partie.

## Règles du jeu

Le but du jeu du Killer consiste à être le dernier des 4 joueurs en vie.
Chaque tour vous aurez 6 dés. Vous devrez faire un score d'au moins 30 ou de moins de 12.
Pour cela, vous devrez prendre au moins un dé à chaque lancer.
Lorsque vous n'avez plus de dés :
  - Si votre score est supérieur à 12 ou inférieur à 30 alors vos points de vie diminueront de la différence.
  - Sinon, vos points de vie augmenterons de la différence. De plus, vous pourrez attaquer un adversaire.
Vous attaquerez d'autant de points que la différence.
Pour ce faire, vous lancerez les dés jusqu'à ce qu'il n'y ait plus de dés avec le montant de l'attaque.

Plus d'information [ici](https://fr.wikipedia.org/wiki/Killer_(jeu_de_d%C3%A9s)).
