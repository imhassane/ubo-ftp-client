# ubo-ftp-client
Un client ftp fait pour un projet universitaire

### Objectif
L'objectif du projet est la mise en place d'un serveur ftp qui accepte 7 commandes
. user --user
. pass --password
. passe --password
. get --file-name
. stor --file-name
. ls
. cd

### Fonctionnement
Au début le client doit être connecté pour éffectuer une action, et pour la connexion il faut utiliser la commande user --username.
###### user --username
Elle vérifie dans le repertoire "passwords" qu'un dossier nommé --username existe bien, par exemple la commande "user personne" va vérifier
que le dossier personne existe bien, si c'est le cas on envoie une message au client qui va ensuite enregistrer le nom d'utilisateur
entré par l'utilisateur.

###### pass --password
Elle vérifie que le mot de passe entré est bien égal à "abcd".

###### passe --password
Elle vérifie que le mot de passe entré est le meme que celui dans le fichier passwords/nom_d_utilisateur_/pwd.txt, si c'est le cas
elle renvoie un code permettant à l'utilisateur de se connecter.

###### ls
Cette commande recoit en paramètres le chemin relatif pour lequel il faut lister les fichiers. Chaque client a une instance de ce
chemin qui lui est propre.

###### cd
Cette commande recoit en paramètre un chemin puis utilise la méthode normalize de java pour retourner un chemin.

###### stor --file-name
La commande stor recoit en paramètre un nom de fichier puis lit le contenu du fichier qu'elle transforme ensuite en un tableau
de byte qu'elle envoie au serveur. Le serveur créé un fichier dans son repertoire courant recu en paramètre du meme nom que file-name
puis copie le contenu du tableau de byte.

###### get --file-name
Le serveur vérifie que le fichier file-name existe bien, si c'est le cas on lit le fichier sous forme d'un tableau de byte
qu'on envoie au client qui quant à lui lira le tableau de bit et crééra un fichier dans son repertoire courant contenant les
données du tableau de byte.


### Serveur et client

##### Serveur
Le serveur écoute sur le port 2121 et attends de recevoir les clients.
Chaque fois qu'un client est accepté, il créé un thread contenant le socket recu, des flux d'entrée et sortie pour la lecture
et l'écriture de données.

##### Client
Le client se connecte au serveur puis commence à executer ses commandes si il est accepté.

#### Exécution
Le serveur: Main.java
Le client en ligne de commandes: ClientLauncher.java
Le client avec Swing: ClientWindow.java












