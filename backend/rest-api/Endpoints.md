# EquipeEndpoints
## CRUD de base

**POST** /api/equipes → Créer une équipe <br>
**GET** /api/equipes → Lister toutes les équipes <br>
**GET** /api/equipes/{id} → Obtenir une équipe par ID <br>
**PUT** /api/equipes/{id} → Modifier une équipe <br>
**DELETE** /api/equipes/{id} → Supprimer une équipe <br> 

##  Gestion du conducteur
**PUT**    /api/equipes/{id}/conducteur/{livreurId}     → Assigner un conducteur <br>
**DELETE** /api/equipes/{id}/conducteur                 → Retirer le conducteur <br>
**GET**    /api/equipes/{id}/conducteur                 → Voir le conducteur <br> 

##  Gestion des aides-livreurs (0..2)
**POST**   /api/equipes/{id}/aides-livreurs/{livreurId}    → Ajouter un aide-livreur <br>
**DELETE** /api/equipes/{id}/aides-livreurs/{livreurId}    → Retirer un aide-livreur <br>
**GET**    /api/equipes/{id}/aides-livreurs                → Lister les aides-livreurs <br>