Formulaire de création de compte

## Description

Ce formulaire permet aux utilisateurs de créer un compte et pouvoir se connecter à la page du calendrier. Il utilise React pour l'interface utilisateur et CSS pour le style.

## Fonctionnalités

- Formulaire de création de compte avec les champs suivants :
  - Titre
  - Pseudo
  - Prénom
  - Email
  - Mot de passe
- Bouton de soumission pour envoyer le formulaire (S'enregistrer).

## Structure des fichiers

### Fichier `NewAccount.js`

Ce fichier contient le composant React pour le formulaire de création de compte. 

#### Détails du composant `NewAccount`

- **Conteneur principal (`main-container`) :** Utilisé pour centrer le formulaire au milieu de la page.
- **Conteneur de formulaire (`form-container`) :** Contient les éléments du formulaire, stylisés pour une meilleure présentation.
- **Titre (`h2`) :** Affiche "Créer un compte" au-dessus du formulaire.
- **Formulaire HTML :** Contient des champs pour le pseudo, le prénom, l'email et le mot de passe, ainsi qu'un bouton de soumission.

### Fichier `NewAccount.css`

Ce fichier contient les styles pour le composant `NewAccount`.

#### Styles inclus

- **`.main-container` :** Utilise Flexbox pour centrer les éléments horizontalement et verticalement, et occupe toute la hauteur de la fenêtre.
- **`.form-container` :** Ajoute une bordure rouge, un fond blanc, et centre les éléments à l'intérieur avec des coins arrondis.
- **`.form-container h2` :** Ajoute un espace inférieur sous le titre.
- **`.form-container label` :** Centre le texte du label et ajoute un espace inférieur.
- **`.form-container input` :** Stylise les champs du formulaire avec des bordures arrondies et un espacement interne.
- **`.form-container button` :** Stylise le bouton de soumission avec une couleur de fond rouge, des coins arrondis, et un effet au survol pour le rendre   plus sombre.