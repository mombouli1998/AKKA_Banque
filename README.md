# Akka

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
