

Il s'agit du compte rendu du projet semestriel Polytech Nancy des étudiants BEGIN Florian et BERVILLER Thomas.
## Gestion d'étudiant avec SOAP et base de données
### Le fonctionnement :

#### Grossièrement : 
 1. L'utilisateur arrive sur le site par l'adresse : http://localhost:8080/Projet_GestionEtudiant/etudiant
 2. Le servlet le redirige aussitôt sur la page d'accueil.
 3. L'utilisateur pourra alors choisir ses actions depuis cette page.
 4. Ce dernier choisit donc une actions, remplit le formulaire correspondant et soumet la requête.
 5. Le servlet engage une HttpSession avec l'utilisateur et va traiter sa requête.
 6. En fonction de la requête de l'utilisateur, le servlet engagera divers requête SOAP pour réaliser la demande utilisateur.
 7. Un retour de la commande utilisateur lui ai envoyé via une alerte sur son navigateur et ce dernier peut ensuite passer à l'étape suivante.

#### Dans les détails :

#### Le servlet - Controller.java

Tout d'abord nous avons le Servlet qui est la pierre angulaire de notre projet. C'est ce dernier qui va receptionner les requêtes POST et GET de l'utilisateur et les traiter.

Il peut être découpé en trois morceaux : 

 1. Le constructeur :
		 Tout d'abord le constructeur instancie le "stub" pour obtenir une connexion avec le service web SOAP.
	
2. La méthode doGet() :
		Cette méthode définie ce qui sera envoyé à l'utilisateur lors d'une requête GET, en réalité le contenue de cette méthode sera toujours exécuté car lors d'une méthode POST le Servlet effectuera ensuite une méthode doGet après avoir exécuté un doPost.
		Ainsi, cette méthode envoie au client les différentes listes : Etudiants, Notes, Matières et Utilisateurs. De plus, cette méthode s'occupera aussi de créer une HttpSession vide si c'est un nouvel utilisateur sans session qui est arrivé sur le site car une HttpSession sera nécessaire quand ce dernier voudra se connecter à son compte utilisateur.
	
3. La méthode doPost() :
		Cette méthode est la plus important et s'occupera de gérer la demande utilisateur. Si l'utilisateur remplit et exécute un formulaire, il enverra une requête POST et, donc, cette méthode se chargera de gérer la requête, elle identifiera le type d'action effectué par l'utilisateur et agira en conséquence en effectuant l'appel au service SOAP correspondant.
		Pour connaître l'action de l'utilisateur, chaque formulaire envoie une variable "action" qui contient comme valeur le nom de l'action effectué, c'est comme cela que le servlet déduit quelle action à été effectué par l'utilisateur.
		Enfin, le Servlet s'occupe aussi du pré-traitement des requêtes : Par exemple. lors de la création d'un utilisateur, c'est le servlet qui vérifiera si le nom d'utilisateur est déjà utilisé ou non grâce à divers appels SOAP. Il en va de même pour l'ajout de notes, d'étudiants et de matières.
	
	[Controller.java](https://prnt.sc/xrm8ul)

#### Le Listener - ServletContextListener.java

Le Listener accompagne le Servlet et va réagir à des évènements appelés "Event", le Listener, dans son état actuel réagit à deux évents, le contextInitialized et le contextDestroyed.

 1. contextInitialized : Cette évènement se réfère au Servlet qui à finit de s'initialiser, et le Listener, au moment de cet évent va alors réagir en démarrant le Web Service SOAP via la création d'un Thread Publication. Nous reviendrons à ce dernier plus tard dans le Compte Rendu.

 2. contextDestroyed : Cette évènement se réfère au Servlet qui termine son exécution, qui est détruit, lors de l'extinction du serveur. Le Listener réagira à ce moment en détruisant le Thread Publication crée précédemment avec le contextInitialized.

    [ServletContextListener.java](https://prnt.sc/xrmcin)

 #### Le Web Service SOAP - Publication.Java

Le Web Service est crée via un Thread appelé Publication.
[Publication.java](https://prnt.sc/xrmeui)

Le fonctionnement reste le même que pour la Publication d'un Web Service via un méthode main, à la différence que nous créons un Thread à part pour cela. Le Web Service est ainsi crée à partir d'un objet Gestions. Ce Thread est détruit à la fin de l'exécution du Servlet.

#### L'objet Gestions utilisé pour la publication du Web Service - Gestions.java

[Gestions.java](https://prnt.sc/xrmge6)

L'objet Gestions est instancié par le Thread Publication.java et est la pierre angulaire de notre Web Service.

Tout d'abord, c'est dans cet objet que nous stockons nos listes internes contenant tout les étudiants, les notes, les matières et les users. Ces listes sont remplit automatiquement à l'instanciation de l'objet Gestions à partir de la base de données. Nous avons donc les informations qui sont à la fois stockés dans les listes internes du Web Service et dans la base de données MySQL.

L'objets Gestions contiens diverses méthodes :

1. Les méthode New :

   Ces méthodes ont pour but d'ajouter un nouvel élément à l'une des listes, pour ce faire une requête SQL est tout d'abord préparé avec les arguments (Les informations de l'objet à ajouter à la liste), ensuite cette requête est exécuté et si cette dernière est un succès alors le nouvel élément est aussi ajouter à la liste interne du Web Service.

   Il existe une exception pour la création d'un nouvel utilisateur pour lequel le mot de passe est d'abord crypté avec un algorithme PBKDFE avant d'être stocké dans la liste internet et la base de données.

   Ces méthodes renvoies TRUE pour confirmer l'ajout de l'élément et FALSE dans le cas contraire (Une erreur lors de la création de l'élément).

2. Les méthodes Check : 

      Ces méthodes vont vérifier les paramètres pour les méthodes New, par exemple, dans le cas de l'ajout d'un étudiant, ces méthodes vont renvoyer TRUE si le numéro d'étudiant n'est pas déjà utilisé et FALSE dans le cas contraire.

3. Les méthodes Init : 

   Ces méthodes ont pour rôle de remplir les listes interne du Web Service à partir de la base de donnée, par exemple, la méthode initEtudiant va récupérer tout les étudiants de la base de données et les stocker dans la liste interne d'étudiant du Web Service.

4. Les méthodes Supr : 

   Ces méthodes vont supprimer l'élément d'id donné dans la liste interne du Web Service ET dans la base de données.

   Une exception existe pour la suppression de matière où l'argument à donner doit être le nom de la matière et non un ID car les objets de type Matiere ne possèdent pas d'ID.

5. La méthode connexion :

   Cette méthode va prendre en argument un String "password" et un String "username". Tout d'abord, il va utiliser une méthode Check pour vérifier que le username réfère bien à un utilisateur existant, ensuite, dans le cas d'un succès, la méthode de connexion récupèrera dans la liste des utilisateurs le mot de passe crypté de l'utilisateur correspondant au username donné pour ensuite appelé la méthode de validation de mot de passe de l'objet PBKDFEncryption avec en argument le mot de passe donné par l'utilisateur au moment de la connexion (Non crypté) et le mot de passe obtenus dans la liste (Crypté). Cette méthode va ensuite crypter le mot de passe donné et comparer les deux mots de passe crypté, si ces derniers sont identiques, cela signifie que les mots de passe originaux (Non cryptés) sont identiques, et donc la méthode retourne TRUE pour signifier que l'utilisateur peut se connecter, dans le cas contraire la méthode retournera un FALSE.
   Ensuite, dans le cas d'une connexion réussit, le servlet stockera les informations de connexion (Le mot de passe en crypté) dans la HttpSession de l'utilisateur pour que ce dernier puisse rester connecter pendant sa navigation dans le site, et ce, jusqu'au moment où il se déconnecte ou que ladite session expire.

6. Les Getters et Setters :
   
   Enfin, notre Objet Gestions possède aussi les Getters et Setters par défaut de tout objets Java.
#### L'encryption des données : PBKDFEncryption.Java

Tout les mots de passes entrés dans notre système sont directement encrypté via l'algorithme PBKDF et stockés comme tel, AUCUN mot de passe n'est enregistré en clair sur le Web Service ou dans la base de données.

[PBKDFEncryption.Java](https://prnt.sc/xrng2z)

Cette objet contient diverses méthodes qui permettent notamment d'encrypter les mots de passe et de vérifier ces derniers comme expliqué dans le point sur la méthode de connexion de l'Objet Gestions.Java.

####  Les objets : Etudiants, Matiere, Note, Users

Notre Gestionnaire (Gestions.Java) utilise quatre types d'objets :

1. Etudiant :
      *int* ID
      *String* nom
      *String* prenom
      *String* numEtu (Le numéro d'étudiant est un String car il peut commencer par un 0)
2. Matiere :
      *String* nom
      *int* coeff
3. Note :
      *int* idNote
      *String* idEtu
      *String* matiere
      *float* note
4. User :
      *int* ID
      *String* username
      *String* password
      *int* permission (Pas encore utilisé)

Enfin, chacuns de ces objets possèdes les méthodes Getter et Setter par défaut de tout objet Java.

#### Les Artefacts et le .wsdl

[Artefact et .wsdl](https://prnt.sc/xrnssl)

Les Artefact sont nécessaire au fonctionnement de notre projet, ce sont eux qui nous permettent d'instancier le "stub" dans notre Servlet.

Ces derniers sont générés à partir du fichier .wsdl lui même généré à partir de notre Gestions.java.

Tout ces fichiers ont été générés grâce aux commandes wsgen et wsimport disponible avec Java 1.8.

#### Notre page web - Index.jsp

[Index.jsp](https://prnt.sc/xro0g6)

Notre projet ne possède qu'une seule page web car nous avons décidé de rendre cette dernière "responsive".

Pour ce faire, notre page contient plusieurs divisions qui possèdent chacune un ID, ensuite ce [script](https://prnt.sc/xro3uc) permet, lorsque l'utilisateur clique sur un bouton de modifier le style des divisions pour les rendre invisibles sauf pour la divisions concernés par le bouton pressé. Par exemple, si l'utilisateur presse le bouton "Ajouter une étudiant" alors toutes les divisions deviendront invisible sauf celle d'ID "newEtu". Nous avons donc une page web responsive de cette manière.

Enfin, la pagination, l'ordonnancement des listes ainsi que le boutons "Toggle menu" ont été obtenus à l'aide de script que nous avons récupéré dans des templates que nous avons réadapté à notre projet.

### Pour aller plus loin...

Nous avons prévu de continuer d'améliorer ce projet pour augmenter nos connaissances, voici donc une liste NON-exhaustive des modifications que nous avons prévu d'y apporter : 

- [ ] Le système de permission.
  Les objets de type User possède une variable *permission* qui n'est pas encore utilisé. Le but de cette variable sera de ne montrer que les tableaux auquel l'utilisateur à accès. Il en va de même pour les opérations auquel il aura accès.
  0 : Non connecté
  1 : Étudiant
  2 : Professeur
  3 : Administrateur

- [ ] La sauvegarde de base de donnée.
  Initialement, pour notre projet, le Web Service ne devait utilisé que ces listes interne et ne toucher à la base de donnée que pour sauvegarder ses données dedans toutes les 5 minutes, permettant ainsi de ne pas avoir à faire de requêtes SQL dès l'ajout de quelque chose dans les listes. Cependant nous ne sommes par encore parvenus à faire cela, d'où l'utilisation des listes et de la base de donnée en parallèle, la base de données assurant la persistance des données quand le serveur est éteint et les listes pour réduire le "stresse" sur la base de données lors de l'envoie des listes complètes à l'utilisateur à chaque requête.

- [ ]  L'envoie des listes selon les permissions.
  Eviter qu'un utilisateur de niveau de permissions 1 (Etudiant) reçoive la liste des utilisateurs par exemple.
