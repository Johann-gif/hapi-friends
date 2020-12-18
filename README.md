# hAPI friends

## Description du projet

hAPI-friends est un projet d'API réseau social basique effectué dans le cadre du cours Spring de M1 III, Université Catholique de Lille.

## Membres du groupe

Les membres du groupe sont les suivants :
- Johann De Almeida
- Sébastien Guidez
- Gianni Giudice

## Fonctionnalités


Sur hapi-friends, il est possible d'effectuer les actions suivantes :

### Sécurité

- Créer un compte en envoyant à la route **/sign-up** un .json de cette forme : (**POST**)

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

- Créer un compte en envoyant à la route **/sign-up2** les paramètres suivants : (**POST**) => version alternative de sign-up

    - pseudo
    - password
    - surname
    - firstname
    - email
    - number

- Connecter son compte en envoyant à la route **/sign-in** les paramètres suivants : (**POST**)
    - pseudo
    - password

### Utilisateur

- Afficher les utilisateurs existant en base via la route **/users** (**GET**)

- Afficher un utilisateur existant en base en renseignant son id via la route **/users/{id}** (**GET**)

- Supprimer un utilisateur existant en base en renseignant son id via la route **/users/{id}** (**DELETE**)

- Modifier un utilisateur existant en base en renseignant son id via la route **/users/{id}** et envoyant les paramètres suivants : (**PUT**)

  - surname
  - firstname
  - email
  - number

- Rechercher un utilisateur existant en base en renseignant son nom via la route **/users/{name}** (**GET**)
