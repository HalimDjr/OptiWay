# 🚚 OptiWay

> Application web d'optimisation des tournées de livraison — génération, comparaison et gestion des tournées à partir des commandes, équipes et véhicules disponibles.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)

---

## 📖 Description

**OptiWay** est une application web qui permet aux entreprises de livraison d'optimiser leurs tournées de façon intelligente.  
À partir des **commandes**, **équipes** et **camions** disponibles, l'application génère automatiquement plusieurs solutions de tournées optimisées, les compare et permet à l'utilisateur de sélectionner et personnaliser la solution la plus adaptée.

---

## ✨ Fonctionnalités

### 🧠 Optimisation des Tournées
- Génération automatique de **plusieurs solutions** de tournées optimisées
- Algorithmes d'optimisation basés sur les contraintes (capacité, distance, temps)
- Prise en compte des **fenêtres horaires** et des **capacités de chargement**

### 📊 Comparaison & Sélection
- Affichage côte à côte des **solutions proposées**
- Indicateurs de performance : distance totale, temps estimé, coût
- **Sélection** de la solution la plus adaptée

### ✏️ Modification & Personnalisation
- Modification manuelle d'une tournée sélectionnée
- Réaffectation des commandes entre équipes
- Validation et confirmation de la tournée finale

---

## 🛠️ Stack Technique

| Couche | Technologie | Rôle |
|--------|-------------|------|
| **Frontend** | Angular | Interface utilisateur, visualisation des tournées |
| **Backend** | Spring Boot | API REST, logique métier, optimisation |
| **ORM** | JPA / Hibernate | Mapping objet-relationnel |
| **Base de données** | PostgreSQL | Stockage des données |

---

## 🚀 Lancer le projet

### Prérequis

- [Java 17+](https://adoptium.net/)
- [Node.js 18+](https://nodejs.org/)
- [PostgreSQL 14+](https://www.postgresql.org/)
- [Maven](https://maven.apache.org/)

---

### ⚙️ Configuration de la base de données

Crée une base de données PostgreSQL :

```sql
CREATE DATABASE optiway;
```

Configure le fichier `backend/src/main/resources/application.properties` :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/optiway
spring.datasource.username=ton_username
spring.datasource.password=ton_password
spring.jpa.hibernate.ddl-auto=update
```

---

### 🔧 Lancer le Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

L'API sera disponible sur : `http://localhost:8080`

---

### 🎨 Lancer le Frontend

```bash
cd frontend
npm install
ng serve
```

L'application sera disponible sur : `http://localhost:4200`

---

## 🚧 Statut

Ce projet est actuellement **en cours de développement**.  
Certaines fonctionnalités peuvent être incomplètes ou sujettes à modification.

---

## 📄 Licence

Ce projet est développé dans un cadre académique.  
Toute réutilisation est soumise à l'accord des contributeurs.
