# TextWord-AI - Frontend Angular

Ce projet frontend a été généré avec [Angular CLI](https://github.com/angular/angular-cli) version 16.2.16.  
Il constitue l'interface utilisateur web de l'application **TextWord-AI**, permettant à l'utilisateur de saisir du texte, de le faire améliorer par l'intelligence artificielle, puis de générer un document Word (.docx).

---

## Serveur de développement

Pour lancer un serveur de développement local :  

```bash
ng serve
```

Puis naviguez vers [http://localhost:4200/](http://localhost:4200/) dans votre navigateur.  
L'application se rechargera automatiquement à chaque modification du code source.

---

## Génération de code

Pour générer de nouveaux éléments Angular comme un composant, un service, une directive, etc., utilisez :  

```bash
ng generate component nom-du-composant
```

ou  

```bash
ng generate directive|pipe|service|class|guard|interface|enum|module nom
```

---

## Compilation

Pour construire le projet et générer les fichiers prêts pour la production :  

```bash
ng build
```

Les fichiers compilés seront placés dans le dossier `dist/`.

---

## Tests unitaires

Pour exécuter les tests unitaires avec [Karma](https://karma-runner.github.io) :  

```bash
ng test
```

---

## Tests end-to-end (E2E)

Pour exécuter les tests de bout en bout, il est nécessaire d'ajouter un framework de tests E2E (comme Cypress ou Protractor) avant d'utiliser :  

```bash
ng e2e
```

---

## Fonctionnalités principales du frontend

- **Saisie de texte utilisateur** avec compteur de caractères  
- **Amélioration du texte** via un service d'IA (ex. Cohere API)  
- **Génération et téléchargement** automatique d'un document Word au format `.docx`  
- Interface simple, responsive et conviviale

---

## Pour aller plus loin

Pour plus d'aide sur les commandes Angular CLI, utilisez :  

```bash
ng help
```

ou consultez la documentation officielle :  
[Angular CLI Overview and Command Reference](https://angular.io/cli)

---

*Ce frontend fait partie intégrante du projet TextWord-AI, dont le backend est réalisé en Spring Boot.*
