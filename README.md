# [Rapport du projet (PDF)](Rapport.pdf)

# Système de Gestion de Crédit Bancaire

## Introduction

Ce projet implémente un système complet de gestion de crédit bancaire. L'application permet d'automatiser et simplifier la gestion des crédits, y compris les demandes, les suivis et les remboursements, pour une institution financière. Le projet utilise une architecture moderne avec Spring Boot pour le backend et Angular pour le frontend.

## Architecture du Système

Le projet est divisé en deux parties principales:

### Backend (Spring Boot)

- REST API pour la gestion des clients, crédits et remboursements
- Authentification sécurisée avec JWT
- Persistance des données avec JPA/Hibernate
- Architecture en couches (Contrôleurs, Services, Repositories)

### Frontend (Angular)

- Interface utilisateur moderne et responsive
- Communication avec le backend via services HTTP
- Authentification et protection des routes
- Formulaires réactifs avec validation
- Design moderne avec Bootstrap 5

## Fonctionnalités Principales

Le système offre les fonctionnalités suivantes:

### Gestion des Clients

- Enregistrement des nouveaux clients
- Consultation de la liste des clients avec recherche
- Modification et suppression des informations clients
- Vue détaillée de chaque client

### Gestion des Crédits

- Création de différents types de crédit (Personnel, Immobilier, Professionnel)
- Suivi de l'état des demandes de crédit
- Acceptation ou rejet des demandes
- Consultation des crédits par client

### Gestion des Remboursements

- Génération automatique des échéanciers de remboursement
- Enregistrement des paiements
- Suivi des échéances impayées
- Calcul des intérêts et pénalités

### Tableau de Bord et Rapports

- Vue d'ensemble des activités
- Statistiques sur les crédits en cours
- Rapports sur les remboursements
- Alertes pour les échéances à venir

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

#### Credit (classe abstraite)

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

### Relations entre Entités

- Un Client peut avoir plusieurs Crédits (relation 1-n)
- Un Crédit appartient à un seul Client (relation n-1)
- Un Crédit peut avoir plusieurs Remboursements (relation 1-n)
- Un Remboursement appartient à un seul Crédit (relation n-1)
- Héritage des différents types de Crédit depuis la classe abstraite Credit

## API REST

L'application expose ses fonctionnalités via une API REST complète:

### Authentification

- `POST /auth/login` - Authentifier un utilisateur

### Clients

- `GET /customers?keyword=search` - Rechercher des clients
- `GET /customers/{id}` - Obtenir les détails d'un client
- `POST /customers` - Créer un nouveau client
- `PUT /customers/{id}` - Mettre à jour un client
- `DELETE /customers/{id}` - Supprimer un client

### Crédits

- `GET /credits` - Lister tous les crédits
- `GET /credits/{id}` - Obtenir les détails d'un crédit
- `GET /customers/{id}/credits` - Obtenir les crédits d'un client
- `POST /credits` - Créer un nouveau crédit
- `PUT /credits/{id}` - Mettre à jour un crédit
- `DELETE /credits/{id}` - Supprimer un crédit

### Remboursements

- `GET /credits/{id}/remboursements` - Obtenir les remboursements d'un crédit
- `POST /remboursements` - Enregistrer un remboursement
- `PUT /remboursements/{id}` - Mettre à jour un remboursement

## Instructions d'Installation

### Prérequis

- Java 17+
- Maven 3.8+
- Node.js 18+
- npm 9+
- MySQL ou PostgreSQL

### Installation du Backend

1. Cloner le dépôt

   ```bash
   git clone https://github.com/your-username/credit-bancaire.git
   cd credit-bancaire
   ```

2. Configurer la base de données dans `application.properties`

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/credit_bancaire
   spring.datasource.username=root
   spring.datasource.password=root
   ```

3. Compiler et exécuter

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. Le serveur démarrera sur http://localhost:8085

### Installation du Frontend

1. Naviguer vers le répertoire frontend

   ```bash
   cd frontend
   ```

2. Installer les dépendances

   ```bash
   npm install
   ```

3. Démarrer le serveur de développement

   ```bash
   ng serve
   ```

4. Accéder à l'application sur http://localhost:4200

![1 - xampp](https://github.com/user-attachments/assets/872e2589-7ebf-4298-84dc-290d3c4f393b)

![2 - phpmyadmin database](https://github.com/user-attachments/assets/9521440f-f6ce-4174-a0c1-e8e2172597f0)

![3 - swaggerui index html](https://github.com/user-attachments/assets/b30e8be1-67ec-46cd-a57a-3920abd721cd)

![image](https://github.com/user-attachments/assets/b0dfda96-7bf8-41f9-9302-6e24f3365eee)

![image](https://github.com/user-attachments/assets/d40c0f4b-1000-4739-97d5-9fdec1814dd7)

![image](https://github.com/user-attachments/assets/9e0feff0-8788-4a38-a0cf-627a022e315e)
