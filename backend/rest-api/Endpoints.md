# Documentation API — Equipe & Tournée

---

## 🏗️ Architecture Hexagonale — Spring Boot / JPA Hibernate

### Le principe

L'architecture hexagonale (Ports & Adapters) sépare le code en 3 zones :

```
[ Monde extérieur ] → [ Ports ] → [ Domaine métier ] → [ Ports ] → [ Infrastructure ]
```

- Le **domaine** ne connaît rien du monde extérieur
- Il communique uniquement via des **interfaces (ports)**
- Les **adapters** implémentent ces interfaces

### Structure des dossiers

```
src/main/java/com/exemple/
│
├── domain/                          ← CŒUR MÉTIER (aucune dépendance externe)
│   ├── model/                       ← Entités métier pures
│   ├── port/
│   │   ├── in/                      ← Ports d'entrée (ce que l'app peut faire)
│   │   └── out/                     ← Ports de sortie (ce dont l'app a besoin)
│   └── service/                     ← Logique métier
│
├── application/                     ← ORCHESTRATION
│   └── dto/
│
└── infrastructure/                  ← ADAPTERS (détails techniques)
    ├── adapter/
    │   ├── in/web/                  ← REST Controllers
    │   └── out/persistence/         ← JPA / Hibernate
    └── config/
```

### Règles fondamentales

| Règle | Détail |
|-------|--------|
| Le domaine **ne dépend de rien** | Pas de `@Entity`, pas de Spring dans le domaine |
| Les dépendances vont **vers l'intérieur** | Infrastructure → Domaine, jamais l'inverse |
| On parle toujours **aux interfaces** | Jamais aux implémentations directement |
| Le **mapper** traduit | Entity JPA ↔ Domain model |

---

## 📦 API — Equipe

> ⚠️ La table **Livreur** n'est pas gérée par ce service — uniquement des références.

### Relations (diagramme de classes)

- Une `Equipe` a **1 conducteur** (Livreur)
- Une `Equipe` a **0..2 aides-livreurs** (Livreur)
- Un Livreur ne peut pas être à la fois conducteur et aide-livreur

### CRUD de base

```
POST    /api/equipes              → Créer une équipe
GET     /api/equipes              → Lister toutes les équipes
GET     /api/equipes/{id}         → Obtenir une équipe par ID
PUT     /api/equipes/{id}         → Modifier une équipe
DELETE  /api/equipes/{id}         → Supprimer une équipe
```

### Gestion du conducteur

```
GET    /api/equipes/{id}/conducteur                    → Voir le conducteur
PUT    /api/equipes/{id}/conducteur/{livreurId}        → Assigner un conducteur
DELETE /api/equipes/{id}/conducteur                    → Retirer le conducteur
```

### Gestion des aides-livreurs (max 2)

```
GET    /api/equipes/{id}/aides-livreurs                    → Lister les aides-livreurs
POST   /api/equipes/{id}/aides-livreurs/{livreurId}        → Ajouter un aide-livreur
DELETE /api/equipes/{id}/aides-livreurs/{livreurId}        → Retirer un aide-livreur
```

### Erreurs métier — Equipe

| Situation | Code HTTP | Message |
|-----------|-----------|---------|
| Équipe introuvable | `404` | Team not found |
| Livreur introuvable | `404` | Livreur not found |
| Conducteur déjà assigné | `409` | Conducteur already assigned |
| Plus de 2 aides-livreurs | `409` | Max 2 aides-livreurs atteint |
| Livreur déjà dans l'équipe | `409` | Livreur already in team |
| Livreur est conducteur ailleurs | `409` | Livreur already conductor in another team |
| Supprimer équipe avec membres | `409` | Cannot delete team with members |
| Supprimer équipe avec tournées actives | `409` | Cannot delete team with active tournées |
| `nbHeuresMax` invalide (≤ 0) | `400` | Invalid nbHeuresMax |
| Non authentifié | `401` | Authentication required |
| Non autorisé | `403` | Forbidden |

### Résumé visuel — Equipe

```
/api/equipes
    ├── POST    /                                        → créer
    ├── GET     /                                        → lister
    ├── GET     /{id}                                    → détail
    ├── PUT     /{id}                                    → modifier
    ├── DELETE  /{id}                                    → supprimer
    │
    ├── conducteur/
    │   ├── GET    /{id}/conducteur                      → voir
    │   ├── PUT    /{id}/conducteur/{livreurId}          → assigner
    │   └── DELETE /{id}/conducteur                      → retirer
    │
    └── aides-livreurs/
        ├── GET    /{id}/aides-livreurs                  → lister
        ├── POST   /{id}/aides-livreurs/{livreurId}      → ajouter
        └── DELETE /{id}/aides-livreurs/{livreurId}      → retirer
```

---

## 🚚 API — Tournée

> ⚠️ La table **Véhicule** n'est pas gérée par ce service — uniquement des références.

### Relations (diagramme de classes)

- Une `Tournée` est effectuée par **1 Equipe**
- Une `Tournée` distribue **1..* commandes**
- Une `Tournée` est affectée à **0..1 Véhicule**
- Une `Tournée` a un **statut** (StatutTournée)

### CRUD de base

```
POST    /api/tournees             → Créer une tournée
GET     /api/tournees             → Lister toutes les tournées
GET     /api/tournees/{id}        → Obtenir une tournée par ID
PUT     /api/tournees/{id}        → Modifier une tournée
DELETE  /api/tournees/{id}        → Supprimer une tournée
```

### Gestion du statut

```
PATCH  /api/tournees/{id}/statut          → Changer le statut
GET    /api/tournees?statut=EN_COURS      → Filtrer par statut
```

### Gestion de l'équipe

```
GET    /api/tournees/{id}/equipe                  → Voir l'équipe assignée
PUT    /api/tournees/{id}/equipe/{equipeId}        → Assigner une équipe
DELETE /api/tournees/{id}/equipe                  → Retirer l'équipe
```

### Gestion du véhicule (référence seulement)

```
GET    /api/tournees/{id}/vehicule                            → Voir le véhicule
PUT    /api/tournees/{id}/vehicule/{immatriculation}          → Affecter un véhicule
DELETE /api/tournees/{id}/vehicule                            → Retirer le véhicule
```

### Gestion des commandes

```
GET    /api/tournees/{id}/commandes                      → Lister les commandes
POST   /api/tournees/{id}/commandes/{commandeId}         → Ajouter une commande
DELETE /api/tournees/{id}/commandes/{commandeId}         → Retirer une commande
```

### Filtres utiles

```
GET  /api/tournees?date=2025-01-01          → Par date
GET  /api/tournees?equipeId={id}            → Par équipe
GET  /api/tournees?statut=TERMINEE          → Par statut
GET  /api/equipes/{id}/tournees             → Tournées d'une équipe
```

### Erreurs métier — Tournée

| Situation | Code HTTP | Message |
|-----------|-----------|---------|
| Tournée introuvable | `404` | Tournée not found |
| Équipe introuvable | `404` | Equipe not found |
| Véhicule introuvable (autre service) | `404` | Véhicule not found |
| Aucune commande (min 1 requise) | `409` | At least 1 commande required |
| Véhicule déjà affecté ailleurs | `409` | Véhicule already assigned |
| Équipe déjà occupée ce jour-là | `409` | Equipe already has a tournée this day |
| Modifier une tournée terminée | `409` | Cannot modify a finished tournée |
| Supprimer une tournée en cours | `409` | Cannot delete an ongoing tournée |
| `heureDepart` dans le passé | `400` | Invalid heureDepart |
| Transition de statut invalide | `400` | Invalid status transition |
| Capacité véhicule dépassée | `409` | Vehicle capacity exceeded |
| Non authentifié | `401` | Authentication required |
| Non autorisé | `403` | Forbidden |

### Résumé visuel — Tournée

```
/api/tournees
    ├── POST    /                                              → créer
    ├── GET     /                                             → lister (+ filtres)
    ├── GET     /{id}                                         → détail
    ├── PUT     /{id}                                         → modifier
    ├── DELETE  /{id}                                         → supprimer
    │
    ├── statut/
    │   └── PATCH  /{id}/statut                              → changer statut
    │
    ├── equipe/
    │   ├── GET    /{id}/equipe                              → voir
    │   ├── PUT    /{id}/equipe/{equipeId}                   → assigner
    │   └── DELETE /{id}/equipe                              → retirer
    │
    ├── vehicule/
    │   ├── GET    /{id}/vehicule                            → voir
    │   ├── PUT    /{id}/vehicule/{immatriculation}          → affecter
    │   └── DELETE /{id}/vehicule                            → retirer
    │
    └── commandes/
        ├── GET    /{id}/commandes                           → lister
        ├── POST   /{id}/commandes/{commandeId}              → ajouter
        └── DELETE /{id}/commandes/{commandeId}              → retirer
```

---

## 🔄 Flux d'une requête (Architecture Hexagonale)

```
HTTP Request
    ↓
Controller              (adapter in)
    ↓
UseCase                 (port in - interface)
    ↓
Service                 (domaine - logique métier)
    ↓
Repository              (port out - interface)
    ↓
RepositoryAdapter       (adapter out)
    ↓
JpaRepository           (Spring Data JPA)
    ↓
Base de données
```

---

## 📋 Codes HTTP — Référence rapide

| Code | Signification | Quand l'utiliser |
|------|---------------|-----------------|
| `200` | OK | Requête réussie (GET, PUT) |
| `201` | Created | Ressource créée (POST) |
| `204` | No Content | Suppression réussie (DELETE) |
| `400` | Bad Request | Données invalides |
| `401` | Unauthorized | Non authentifié |
| `403` | Forbidden | Non autorisé |
| `404` | Not Found | Ressource introuvable |
| `409` | Conflict | Violation de règle métier |
| `500` | Internal Server Error | Erreur technique |