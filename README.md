# Logic Toolbox


## 📚 Sommaire

- [Présentation](#-présentation)
- [Choix d'implémentation](#-choix-dimplémentation)
- [Architecture](#️-architecture)
- [Installation](#️-installation)
- [Documentation API](#-documentation-api)
- [Format des réponses API](#-format-des-réponses-api)
- [Gestion des erreurs API](#-gestion-des-erreurs-api)
- [Collection Insomnia](#-collection-insomnia)
- [Fonctionnalités disponibles](#-fonctionnalités-disponibles)
- [Exécution des tests](#-exécution-des-tests)

---

# 🚀 Présentation

Logic Toolbox est un projet Spring Boot regroupant plusieurs exercices de logique et de manipulation de données.

L'objectif du projet est de démontrer :
- des bonnes pratiques de développement
- une architecture backend propre
- l'utilisation de tests unitaires avec JUnit
- des implémentations réutilisables et maintenables

Le projet expose également les différentes fonctionnalités via une API REST documentée avec Swagger/OpenAPI.

---

# 🧠 Choix d'implémentation

Le choix a été fait de traiter ce sujet avec une approche proche d'une mise en œuvre backend réelle.

Au-delà de la résolution des exercices demandés, le projet intègre :
- une architecture Spring Boot en couches
- une exposition des fonctionnalités via API REST
- une documentation API Swagger/OpenAPI complète et enrichie via annotations
- des réponses API standardisées
- une gestion centralisée des erreurs via Global Exception Handler
- des tests unitaires JUnit
- des tests API/controller Spring Boot
- de la documentation JavaDoc
- une organisation modulaire du code

L'objectif est de proposer une implémentation complète mettant en avant :
- la maintenabilité
- la lisibilité
- la testabilité
- la documentation
- la réutilisabilité des composants
- les bonnes pratiques de développement backend

Le projet a volontairement été conçu comme une mini application exploitable plutôt qu'une simple collection de fonctions isolées.

Cette approche permet également de démontrer les choix techniques et les bonnes pratiques qui seraient appliqués dans un contexte de développement réel.

---

# 🏗️ Architecture

```text
src
├── main
│   └── java/com/fred/logictoolbox
│       ├── controller
│       ├── service
│       │   ├── arrayobject
│       │   ├── objectutils
│       │   └── string
│       └── LogicToolboxApplication
│
└── test
    └── java/com/fred/logictoolbox
        └── service
```

L'application suit une architecture en couches simple basée sur :
- des contrôleurs REST pour l'exposition API
- des services dédiés à chaque catégorie d'exercices
- des tests unitaires pour chaque fonctionnalité

---

# ⚙️ Installation

## Cloner le dépôt

```bash
git clone <repository-url>
cd logic-toolbox
```

## Construire le projet

```bash
mvn clean install
```

## Lancer l'application

```bash
mvn spring-boot:run
```

---

# 🌐 Documentation API

Le projet utilise Swagger/OpenAPI pour documenter automatiquement les endpoints REST.

La documentation Swagger est disponible à l'adresse suivante :

```text
http://localhost:8080/swagger-ui/index.html
```

# 🧪 Collection Insomnia

Une collection Insomnia exportée est disponible afin de faciliter le test des différents endpoints de l'API.

Emplacement :

```text
docs/insomnia/
```

---

# 📦 Format des réponses API

Les endpoints REST utilisent un format de réponse standardisé afin de garantir la cohérence des échanges API.

## Exemple de réponse

```json
{
  "success": true,
  "message": "Longueur calculée avec succès",
  "data": 15
}
```

---

# ❌ Gestion des erreurs API

L'application utilise un Global Exception Handler afin de centraliser la gestion des erreurs et garantir des réponses API cohérentes.

## Exemple de réponse d'erreur

```json
{
  "success": false,
  "message": "Le paramètre 'input' est obligatoire",
  "data": null
}
```

---

# ✅ Fonctionnalités disponibles

## String

### Longueur d'une chaîne sans espaces

Retourne la longueur d'une chaîne après suppression des espaces.

### Endpoint

```http
GET /api/string/length-without-spaces?input=Bonjour le monde !
```

### Exemple de réponse

```json
15
```

---

# 🧪 Exécution des tests

Lancer l'ensemble des tests unitaires :

```bash
mvn test
```

Le projet inclut :
- des tests unitaires métier
- des tests API/controller Spring Boot
- des validations de comportement HTTP et JSON