# Creation evenement - API

## Conception database

### MCD

[MCD](MCD_creation_evenement.PNG)

creation des Tables evenement et viennoiserie, avec primary Key & Delete Cascade 
et insertion des valeurs 

### Migration

Nous n'avons pas modifié la table existante, "users". Celle-ci utilise "username" comme primary key. Ce point est à discuter.
Nous avons créé un script `202405161110-addevent.sql` qui est exécuté automatiquement au démarrage de gradle.

### Comment tester ?

Démarrer gradle : `.\gradlew run`

