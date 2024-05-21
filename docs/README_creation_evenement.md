# Creation evenement - API

## Conception database

### MCD

[MCD](MCD_creation_evenement.PNG)

creation des Tables evenement et viennoiserie, avec primary Key & Delete Cascade 
et insertion des valeurs 


|:exclamation: Correction : Suite à l'harmonisation de la bdd avec une autre issue, ce MCD a été corrigé et n'est plus à jour !|
|---|

### Migration

Nous n'avons pas modifié la table existante, "users". Celle-ci utilise "username" comme primary key. Ce point est à discuter.
Nous avons créé un script `202405161110-addevent.sql` qui est exécuté automatiquement au démarrage de gradle.

### Comment tester ?

Démarrer gradle : `.\gradlew run`

### Sur thunder client ou Postman

Requete POST sur `http://localhost:8080/events`
avec le body : 
```json
{
  "event_date":"2024-05-03", 
  "creator":"shiipou", 
  "pastries": ["croissant", "pain aux raisins"]
}
```
Assurez vous que le username existe dans la table `users`, ainsi que les noms des viennoiseries dans la table `pastry`!
