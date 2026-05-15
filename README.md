# Logic Toolbox

## 📚 Sommaire

- [Présentation](#-présentation)
- [Architecture](#️-architecture)
- [Installation](#️-installation)
- [Documentation API](#-documentation-api)
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