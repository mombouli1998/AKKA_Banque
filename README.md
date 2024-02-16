# Akka
Le projet vise à développer un système de gestion de comptes bancaires en utilisant Akka, un framework pour la construction d'applications concurrentes et distribuées en Java ou Scala.

Conception de l'architecture du système :

Le système sera basé sur le modèle d'acteurs fourni par Akka, où chaque entité (comme les comptes, les banquiers, les transactions) sera représentée par un acteur.
Les interactions entre les acteurs seront gérées par l'envoi de messages asynchrones, permettant ainsi un traitement concurrent et distribué.
Implémentation des fonctionnalités de gestion des comptes :

Création d'acteurs pour représenter les comptes bancaires et les banquiers.

Implémentation des fonctionnalités permettant aux banquiers de gérer les comptes, telles que l'ouverture de compte, la fermeture de compte, le dépôt, le retrait, le virement, etc.
Définition des messages et des comportements pour chaque type d'acteur afin de gérer ces fonctionnalités de manière cohérente.

Mise en place d'un système de persistance avec un SGBD :

Intégration d'un système de persistance pour sauvegarder l'état des comptes des clients dans une base de données relationnelle.
Utilisation de bibliothèques ou modules Akka pour la persistance des acteurs, qui peuvent être configurés pour fonctionner avec différents SGBD tels que PostgreSQL, MySQL, etc.
Gestion de la cohérence des données entre la mémoire des acteurs et la base de données pour assurer la fiabilité du système.







Pour compiler le code il faut exécuter la commande suivante sur un terminal d’un pc de l’université :

```mvn compile```

Ensuite pour exécuter le code il faut exécuter la commande suivante :

```mvn exec : java -Dexec . mainClass =" sd. akka . App"```

Si vous n’avez pas accès à un pc de la fac il faut sur votre pc se connecter au vpn puis sur un invité de commande taper la commande suivante :

```sftp votreNomDutilisateur@aragon.iem```

Rentrer ensuite votre mot de passe de session.

Puis ce placer a l’emplacement voulue cd (serveur distant) lcd(votre pc) pour ensuite lancer les commandes suivante :

```
put -r nomDuDossierContenantLeProjet
exit
```

puis se connecter en ssh avec la commande suivante :

```ssh votreNomDutilisateur@aragon.iem```

et lancer les 2 première commandes
