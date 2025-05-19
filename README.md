# Système de Gestion de Crédit Bancaire

## Introduction

Ce projet implémente un système complet de gestion de crédit bancaire. L'application permet d'automatiser et simplifier la gestion des crédits, y compris les demandes, les suivis et les remboursements, pour une institution financière. Le projet est développé en utilisant des technologies modernes du monde Java, avec une architecture orientée services.

## Description du Projet

Le projet "Crédit Bancaire" est une application de gestion complète permettant aux institutions financières de gérer efficacement:

- Les demandes de crédit
- Le suivi des remboursements
- La gestion des clients

L'application prend en charge différents types de crédits (personnel, immobilier, professionnel) et offre une interface pour gérer l'ensemble du cycle de vie d'un crédit, de la demande initiale jusqu'au remboursement final.

### Objectifs du Projet

- Gestion des clients et de leurs informations personnelles
- Traitement des demandes de crédit de différentes natures
- Suivi de l'état des crédits (en cours, accepté, rejeté)
- Gestion des échéances de remboursement
- Génération de rapports sur l'état des crédits
- Interface REST pour l'interaction avec d'autres systèmes

## Architecture Technique

### Technologies Utilisées

- Java comme langage de programmation principal
- Spring Boot comme framework d'application
- JPA/Hibernate pour la persistance des données
- Spring Data pour l'accès aux données
- Spring REST pour l'exposition des services web
- Lombok pour réduire le code boilerplate

### Architecture en Couches

L'application suit une architecture en couches classique:

- **Couche Entités**: Représente les objets du domaine et leurs relations
- **Couche Repository**: Gère l'accès aux données
- **Couche Service**: Contient la logique métier
- **Couche Controller**: Expose les API REST
- **Couche DTO**: Objets de transfert de données entre le frontend et le backend

## Modèle de Données

### Entités Principales

#### Client

```java
@Entity
public class Client {
    @Id @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String dateNaissance;
    private String cin;

    @OneToMany(mappedBy = "client")
    private List<Credit> credits;
}
```

#### Credit

```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_credit")
public abstract class Credit {
    @Id @GeneratedValue
    private Long id;
    private LocalDate dateDemande;
    @Enumerated(EnumType.STRING)
    private StatutCredit statut;
    private LocalDate dateAcceptation;
    private double montant;
    private int dureeRemboursement;
    private double tauxInteret;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "credit")
    private List<Remboursement> remboursements;
}
```

#### Types de Crédit

Le système gère trois types de crédits spécifiques:

- **CreditPersonnel**: Pour les besoins personnels des clients
- **CreditImmobilier**: Pour l'achat de biens immobiliers
- **CreditProfessionnel**: Pour les projets professionnels ou d'entreprise

#### Remboursement

```java
@Entity
public class Remboursement {
    @Id @GeneratedValue
    private Long id;
    private LocalDate dateEcheance;
    private LocalDate datePaiement;
    private double montant;
    private boolean paye;

    @Enumerated(EnumType.STRING)
    private TypeRemboursement type;

    @ManyToOne
    private Credit credit;
}
```

### Relations entre Entités

- Un Client peut avoir plusieurs Crédits (relation 1-n)
- Un Crédit appartient à un seul Client (relation n-1)
- Un Crédit peut avoir plusieurs Remboursements (relation 1-n)
- Un Remboursement appartient à un seul Crédit (relation n-1)
- Héritage des différents types de Crédit depuis la classe abstraite Credit

## Services et Fonctionnalités

### Gestion des Clients

Le service `ClientService` offre les fonctionnalités suivantes:

- Listing de tous les clients
- Recherche de clients par ID, nom ou email
- Création, mise à jour et suppression de clients

### Gestion des Crédits

Le service `CreditService` permet:

- Création de différents types de crédits (Personnel, Immobilier, Professionnel)
- Consultation des crédits par ID, par client ou par statut
- Acceptation ou rejet des demandes de crédit
- Modification et suppression des crédits

### Gestion des Remboursements

Le service `RemboursementService` gère:

- Enregistrement des remboursements programmés
- Suivi des paiements effectués
- Consultation des échéanciers par crédit
- Modification et suppression des remboursements

## API REST

L'application expose ses fonctionnalités via une API REST complète:

- `ClientRestController`: Endpoints pour la gestion des clients
- `CreditRestController`: Endpoints pour la gestion des crédits
- `RemboursementRestController`: Endpoints pour la gestion des remboursements

Ces contrôleurs permettent l'intégration avec des applications frontales ou d'autres systèmes.

## Pattern Data Transfer Object (DTO)

Le projet utilise le pattern DTO pour séparer les entités de persistance des objets utilisés pour la communication avec les clients. Par exemple, la classe `ClientDTO` est utilisée pour transférer les données des clients sans exposer directement les entités JPA.

## Perspectives d'Évolution

Les prochaines étapes du projet pourraient inclure:

- Développement d'une interface utilisateur frontend
- Implémentation de calculs financiers avancés
- Intégration avec des systèmes de scoring de crédit
- Mise en place d'un système de notification
- Ajout de fonctionnalités de reporting et d'analyse
