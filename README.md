# hAPI friends

## Description du projet

hAPI-friends est un projet d'API réseau social basique effectué dans le cadre du cours Spring de M1 III, Université Catholique de Lille.

## Membres du groupe

Les membres du groupes sont les suivants :
- Johann De Almeida
- Sébastien Guidez
- Gianni Giudice

## Fonctionnalités

### Utilisateur

Sur hapi-friends, il est possible d'effectuer les actions suivantes :

- Afficher les utilisateurs existant en base via la route **/users** (**GET**)

- Afficher un utilisateur existant en base en renseignant son id via la route **/users/{id}** (**GET**)

- Créer un compte en envoyant à la route **/users/sign-up** un .json de cette forme : (**POST**)

- Modifier un utilisateur existant en base en renseignant son id via la route **/users/{id}** et envoyant les paramètres suivants : (**PUT**)

  - nom
  - prenom
  - email
  - numero

- Supprimer un utilisateur existant en base en renseignant son id via la route **/users/{id}** (**DELETE**)

```json
{
    "id": 4,
    "pseudo": "test",
    "password": "test",
    "surname": "Test",
    "firstname": "Test",
    "email": "test.test@lacatholille.fr",
    "mob_number": null,
    "friends": [
    ]
}
```

- Rechercher un utilisateur existant en base en renseignant son nom via la route **/users/{name}** (**GET**)
