# Création de chaque sous formulaires (composant réutilisable)#12

L'objet de cette branche est de créer la page affichant les formulaires pour, au choix, créer un événement ou un sondage.

## Installation

1. Rendez-vous dans le dossier react-app : `cd react-app`
2. Installez les dépendances : `npm i`
3. Lancez le front : `npm run start`

## Route

La route utilisée pour notre fonctionnalité est `http://localhost:3000/formulaire`
"/formulaire" pour afficher les formulaires -> composant "PageFormulaires"

## Fonctionnement

L'appel à la route dans App.js envoie une page `PageFormulaire.js` qui a son propre css. 

La page `PageFormulaire.js` appelle le composant `Formulaire.js` dans le dossier `Components`. 
(utilisation props pour le titre du composant)

## Affichage automatique des propositions 

Dans la maquette, lorsque l'on choisissait une viennoiserie, l'affichage du menu déroulant reprenait le CSS du reste du composant.
Nous avons utilisé une `datalist` pour gérer ce menu.

Ce sont les navigateurs qui gèrent l'affichage des datalist. Il est possible de désactiver cette gestion et de la réécrire en javascript, de façon à pouvoir en personnaliser l'affichage. 

Cela implique quelques dizaines de lignes de code en plus, source de bugs et chronophage. Cela ne sert en rien l'objectif de notre projet .

Nous avons donc choisi de laisser l'affichage géré par les navigateurs pour l'instant. Cela pourra être modifié par la suite. 

