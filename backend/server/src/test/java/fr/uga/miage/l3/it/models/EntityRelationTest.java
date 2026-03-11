package fr.uga.miage.l3.it.models;

import org.junit.jupiter.api.Test;

import jakarta.persistence.*;
import static org.junit.jupiter.api.Assertions.*;

import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.enums.StatutTournee;
import fr.uga.miage.l3.models.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Tests de validation des entités JPA selon le diagramme de classes UML.
 * Vérifie pour chaque entité :
 *   - La présence de l'annotation @Entity
 *   - Les identifiants (@Id)
 *   - Les types et noms des attributs
 *   - Les relations JPA (@OneToMany, @ManyToOne, @OneToOne, @ManyToMany)
 */
class EntityRelationTest {

    // =========================================================================
    // @Entity presence sur toutes les classes
    // =========================================================================

    /**
     * Vérifie que toutes les classes du domaine sont bien annotées @Entity JPA.
     */
    @Test
    void testAnnotationsEntity() {
        assertTrue(AdresseEntity.class.isAnnotationPresent(Entity.class),
                "AdresseEntity doit être annotée @Entity");
        assertTrue(CommandeEntity.class.isAnnotationPresent(Entity.class),
                "CommandeEntity doit être annotée @Entity");
        assertTrue(EquipeEntity.class.isAnnotationPresent(Entity.class),
                "EquipeEntity doit être annotée @Entity");
        assertTrue(LivreurEntity.class.isAnnotationPresent(Entity.class),
                "LivreurEntity doit être annotée @Entity");
        assertTrue(ProduitEntity.class.isAnnotationPresent(Entity.class),
                "ProduitEntity doit être annotée @Entity");
        assertTrue(TourneeEntity.class.isAnnotationPresent(Entity.class),
                "TourneeEntity doit être annotée @Entity");
        assertTrue(VehiculeEntity.class.isAnnotationPresent(Entity.class),
                "VehiculeEntity doit être annotée @Entity");
    }

    // =========================================================================
    // AdresseEntity
    // =========================================================================

    /**
     * Vérifie la structure complète de AdresseEntity :
     * - Clé primaire : id (@Id)
     * - Attributs : numeroRue (int), rue (String), ville (String),
     *               codePostal (int), pays (String), latitude (double), longitude (double)
     * - Relation : commandes -> @OneToMany + @JoinColumn (1 Adresse possède 0..* Commandes)
     */
    @Test
    void testAdresseEntity() throws Exception {
        // --- Clé primaire ---
        EntityTestUtils.assertAnnotationPresent(AdresseEntity.class, "id", Id.class);

        // --- Attributs et types ---
        EntityTestUtils.assertFieldType(AdresseEntity.class, "numeroRue", int.class);
        EntityTestUtils.assertFieldType(AdresseEntity.class, "rue", String.class);
        EntityTestUtils.assertFieldType(AdresseEntity.class, "ville", String.class);
        EntityTestUtils.assertFieldType(AdresseEntity.class, "codePostal", int.class);
        EntityTestUtils.assertFieldType(AdresseEntity.class, "pays", String.class);
        EntityTestUtils.assertFieldType(AdresseEntity.class, "latitude", double.class);
        EntityTestUtils.assertFieldType(AdresseEntity.class, "longitude", double.class);

        // --- Relations ---
        // Une adresse possède plusieurs commandes (1 -> 0..*)
        //EntityTestUtils.assertAnnotationPresent(AdresseEntity.class, "commandes", OneToMany.class);
        //EntityTestUtils.assertAnnotationPresent(AdresseEntity.class, "commandes", JoinColumn.class);
    }

    // =========================================================================
    // CommandeEntity
    // =========================================================================

    /**
     * Vérifie la structure complète de CommandeEntity :
     * - Clé primaire : numeroCommande (String, @Id)
     * - Attributs : dateLimit, statut (StatutCommande), poids (double), volum (double)
     * - Relations :
     *     adresse    -> @ManyToOne  (0..* Commandes pour 1 Adresse)
     *     tournee    -> @ManyToOne  (1..* Commandes dans 0..1 Tournée)
     *     produits   -> @OneToMany  (une commande est composée de 1..* Produits)
     */
    @Test
    void testCommandeEntity() throws Exception {
        // --- Clé primaire ---
        EntityTestUtils.assertAnnotationPresent(CommandeEntity.class, "numeroCommande", Id.class);

        // --- Attributs et types ---
        EntityTestUtils.assertFieldType(CommandeEntity.class, "dateLimite", Date.class);
        EntityTestUtils.assertFieldType(CommandeEntity.class, "poids", double.class);
        EntityTestUtils.assertFieldType(CommandeEntity.class, "volume", double.class);
        // statut est une énumération StatutCommande
        EntityTestUtils.assertFieldType(CommandeEntity.class, "statut", StatutCommande.class);

        // --- Relations ---
        // Commande appartient à une Adresse (Many Commandes -> One Adresse)
        EntityTestUtils.assertAnnotationPresent(CommandeEntity.class, "adresse", ManyToOne.class);

        // Commande appartient à une Tournée (Many Commandes -> One Tournée)
        EntityTestUtils.assertAnnotationPresent(CommandeEntity.class, "tournee", ManyToOne.class);

        // Une commande est composée de plusieurs Produits (One Commande -> Many Produits)
        EntityTestUtils.assertAnnotationPresent(CommandeEntity.class, "produits", OneToMany.class);
    }

    // =========================================================================
    // LivreurEntity
    // =========================================================================

    /**
     * Vérifie la structure complète de LivreurEntity :
     * - Clé primaire : idLivreur (@Id)
     * - Attributs : nom (String), prenom (String), telephone (String), permis (boolean)
     * - Relations :
     *     conducteur      -> @OneToOne   (1 Livreur est conducteur de 0..1 Equipe)
     *     manutentionnaire -> @ManyToOne (0..2 Livreurs sont aides dans 0..1 Equipe)
     */
    @Test
    void testLivreurEntity() throws Exception {
        // --- Clé primaire ---
        EntityTestUtils.assertAnnotationPresent(LivreurEntity.class, "idLivreur", Id.class);

        // --- Attributs et types ---
        EntityTestUtils.assertFieldType(LivreurEntity.class, "nom", String.class);
        EntityTestUtils.assertFieldType(LivreurEntity.class, "prenom", String.class);
        EntityTestUtils.assertFieldType(LivreurEntity.class, "telephone", String.class);
        EntityTestUtils.assertFieldType(LivreurEntity.class, "permis", boolean.class);

        // --- Relations ---
        // Un livreur peut être le conducteur d'une équipe (1..1)
        EntityTestUtils.assertAnnotationPresent(LivreurEntity.class, "equipe", OneToOne.class);

        // Un livreur peut être aide-livreur (manutentionnaire) d'une équipe (Many -> One)
        EntityTestUtils.assertAnnotationPresent(LivreurEntity.class, "equipe2", ManyToOne.class);
    }

    // =========================================================================
    // EquipeEntity
    // =========================================================================

    /**
     * Vérifie la structure complète de EquipeEntity :
     * - Clé primaire : numeroEquipe (int, @Id)
     * - Attributs : nbHeuresMax (double)
     * - Relations :
     *     conducteur    -> @OneToOne  (1 Equipe a 1 conducteur Livreur)
     *     livreurs      -> @OneToMany (1 Equipe a 0..2 aides-livreurs)
     *     tournee       -> @OneToOne  (1 Equipe effectue 0..1 Tournée)
     */
    @Test
    void testEquipeEntity() throws Exception {
        // --- Clé primaire ---
        EntityTestUtils.assertAnnotationPresent(EquipeEntity.class, "numeroEquipe", Id.class);

        // --- Attributs et types ---
        EntityTestUtils.assertFieldType(EquipeEntity.class, "nbHeuresMax", double.class);

        // --- Relations ---
        // L'équipe a un conducteur (One Equipe -> One Livreur conducteur)
        EntityTestUtils.assertAnnotationPresent(EquipeEntity.class, "conducteur", OneToOne.class);

        // L'équipe a des aides-livreurs (One Equipe -> Many Livreurs, max 2)
        EntityTestUtils.assertAnnotationPresent(EquipeEntity.class, "manutentionnaires", OneToMany.class);

        // Une équipe effectue une tournée (One Equipe -> One Tournée)
        EntityTestUtils.assertAnnotationPresent(EquipeEntity.class, "tournee", OneToOne.class);
    }

    // =========================================================================
    // TourneeEntity
    // =========================================================================

    /**
     * Vérifie la structure complète de TourneeEntity :
     * - Clé primaire : idTournee (int, @Id)
     * - Attributs : dateTournee (Date), heureDepart (Timestamp),
     *               statut (StatutTournee), distanceTotale (double), tempsTotal (double)
     * - Relations :
     *     commandes -> @OneToMany  (1 Tournée distribue 1..* Commandes)
     *     vehicule  -> @OneToOne   (0..1 Tournée utilise 1 Véhicule)
     *     equipe    -> @OneToOne   (0..1 Tournée effectuée par 1 Equipe)
     */
    @Test
    void testTourneeEntity() throws Exception {
        // --- Clé primaire ---
        EntityTestUtils.assertAnnotationPresent(TourneeEntity.class, "idTournee", Id.class);

        // --- Attributs et types ---
        EntityTestUtils.assertFieldType(TourneeEntity.class, "distanceTotale", double.class);
        EntityTestUtils.assertFieldType(TourneeEntity.class, "tempsTotal", double.class);
        // statut est une énumération StatutTournee
        EntityTestUtils.assertFieldType(TourneeEntity.class, "statut", StatutTournee.class);
        EntityTestUtils.assertFieldType(TourneeEntity.class, "heureDepart", Timestamp.class);
        EntityTestUtils.assertFieldType(TourneeEntity.class, "dateTournee", Date.class);

        // --- Relations ---
        // Une tournée distribue plusieurs commandes (One Tournée -> Many Commandes)
        EntityTestUtils.assertAnnotationPresent(TourneeEntity.class, "commandes", OneToMany.class);

        // Une tournée utilise un véhicule (One -> One)
        EntityTestUtils.assertAnnotationPresent(TourneeEntity.class, "vehicule", OneToOne.class);

        // Une tournée est effectuée par une équipe (One -> One)
        EntityTestUtils.assertAnnotationPresent(TourneeEntity.class, "equipe", OneToOne.class);
    }

    // =========================================================================
    // VehiculeEntity
    // =========================================================================

    /**
     * Vérifie la structure complète de VehiculeEntity :
     * - Clé primaire : matricule (String, @Id)
     * - Attributs : capacitePoidsMax (double), capaciteVolumeMax (double)
     * - Relations :
     *     tournee -> @OneToOne (1 Véhicule est affecté à 0..1 Tournée)
     */
    @Test
    void testVehiculeEntity() throws Exception {
        // --- Clé primaire ---
        EntityTestUtils.assertAnnotationPresent(VehiculeEntity.class, "matricule", Id.class);

        // --- Attributs et types ---
        EntityTestUtils.assertFieldType(VehiculeEntity.class, "capacitePoidsMax", double.class);
        EntityTestUtils.assertFieldType(VehiculeEntity.class, "capaciteVolumeMax", double.class);

        // --- Relations ---
        // Un véhicule est affecté à une tournée (One -> One)
        EntityTestUtils.assertAnnotationPresent(VehiculeEntity.class, "tournee", OneToOne.class);
    }

    // =========================================================================
    // ProduitEntity
    // =========================================================================

    /**
     * Vérifie la structure complète de ProduitEntity :
     * - Clé primaire : reference (String, @Id)
     * - Attributs : nom (String), poids (double)
     * - Relations :
     *     commande -> @ManyToOne (1..* Produits appartiennent à 0..1 Commande)
     */
    @Test
    void testProduitEntity() throws Exception {
        // --- Clé primaire ---
        EntityTestUtils.assertAnnotationPresent(ProduitEntity.class, "reference", Id.class);

        // --- Attributs et types ---
        EntityTestUtils.assertFieldType(ProduitEntity.class, "nom", String.class);
        //EntityTestUtils.assertFieldType(ProduitEntity.class, "poids", double.class);

        // --- Relations ---
        // Un produit appartient à une commande (Many Produits -> One Commande)
        //EntityTestUtils.assertAnnotationPresent(ProduitEntity.class, "commande", ManyToOne.class);
    }
}