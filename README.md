TP1_SOAP de Begin Florian, I2S

# Soap Server

## Démarrer le serveur sous WINDOWS
Exécuter le fichier startServerWINDOWS.bat (Un simple double clique suffit).

## Démarrer le serveur sous UNIX
Exécuter le fichier startServerUNIX.sh (Un simple double clique suffit).

## Démarrer le serveur sous OS-X
Exécuter le fichier startServerOSX.sh (Un simple double clique suffit).

### FAQ 
## Mon fichier startServerOSX.sh ne s'éxécute pas quand je double clique dessus !
Dans le cas de cette erreur, à l'aide du terminal, rendez-vous dans le répertoire contenant le fichier startServerOSX.sh et effectuez la commande suivante :
`chmod +x startServerOSX.sh`
Après cela, votre fichier .sh devrait être éxécutable à l'aide d'un double clique.

# Client Java 

## Démarrer le client sous WINDOWS
Exécuter le fichier startJavaClientWINDOWS.bat (Un simple double clique suffit).

## Démarrer le client sous UNIX
Exécuter le fichier startJavaClientUNIX.sh (Un simple double clique suffit).

## Démarrer le client sous OS-X
Exécuter le fichier startJavaClientOSX.sh (Un simple double clique suffit).

### FAQ 
## Mon fichier startClientOSX.sh ne s'éxécute pas quand je double clique dessus !
Dans le cas de cette erreur, à l'aide du terminal, rendez-vous dans le répertoire contenant le fichier startClientOSX.sh et effectuez la commande suivante :
`chmod +x startClientOSX.sh`
Après cela, votre fichier .sh devrait être éxécutable à l'aide d'un double clique.

# Client PHP

Pour le client PHP, vous devez créer un nouvel alias de votre serveur PHP (Apache, etc...) pointant directement sur le répertoire dans lequel vous décidez de stocker le fichier GestionEtudiant.php.


# Dépendence du projet
Ce projet n'est pas compatible avec les versions de **Java 1.6 (JAVA SE 6)**.

# Todolist
- [ ] Création d'un éxécutable .jar pour le serveur et le client Java.
- [ ] Ajout de la possibilité d'utiliser une base de données plutôt que le fichier etudiant.etu pour le stockage des données.
