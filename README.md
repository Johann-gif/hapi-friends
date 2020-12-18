# hAPI friends

## Description du projet

hAPI-friends est un projet d'API réseau social basique effectué dans le cadre du cours Spring de M1 III, Université Catholique de Lille.

## Membres du groupe

Les membres du groupe sont les suivants :
- Johann De Almeida
- Sébastien Guidez
- Gianni Giudice

## Fonctionnalités

### Sécurité

Sur hapi-friends, il est possible d'effectuer les actions suivantes :

- Créer un compte en envoyant à la route **/security/sign-up** un .json de cette forme : (**POST**)

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

### Utilisateur

Sur hapi-friends, il est possible d'effectuer les actions suivantes :

- Afficher les utilisateurs existant en base via la route **/users** (**GET**)

- Afficher un utilisateur existant en base en renseignant son id via la route **/users/{id}** (**GET**)

- Modifier un utilisateur existant en base en renseignant son id via la route **/users/{id}** et envoyant les paramètres suivants : (**PUT**)

  - nom
  - prenom
  - email
  - numero

- Supprimer un utilisateur existant en base en renseignant son id via la route **/users/{id}** (**DELETE**)

- Rechercher un utilisateur existant en base en renseignant son nom via la route **/users/{name}** (**GET**)
