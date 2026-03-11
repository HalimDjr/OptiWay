# EquipeEndpoints
## CRUD de base

**POST** /api/equipes → Créer une équipe
**GET** /api/equipes → Lister toutes les équipes
**GET** /api/equipes/{id} → Obtenir une équipe par ID
**PUT** /api/equipes/{id} → Modifier une équipe
**DELETE** /api/equipes/{id} → Supprimer une équipe

##  Gestion du conducteur
**PUT**    /api/equipes/{id}/conducteur/{livreurId}     → Assigner un conducteur
**DELETE** /api/equipes/{id}/conducteur                 → Retirer le conducteur
**GET**    /api/equipes/{id}/conducteur                 → Voir le conducteur

##  Gestion des aides-livreurs (0..2)
**POST**   /api/equipes/{id}/aides-livreurs/{livreurId}    → Ajouter un aide-livreur
**DELETE** /api/equipes/{id}/aides-livreurs/{livreurId}    → Retirer un aide-livreur
**GET**    /api/equipes/{id}/aides-livreurs                → Lister les aides-livreurs