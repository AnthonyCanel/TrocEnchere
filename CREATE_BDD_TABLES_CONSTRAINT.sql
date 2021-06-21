CREATE DATABASE TROC_ENCHERES

GO;
USE TROC_ENCHERES

CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORIES ADD CONSTRAINT PK_CATEGORIES PRIMARY KEY (no_categorie)

CREATE TABLE ENCHERES (
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     DATETIME NOT NULL,
    montant_enchere  INTEGER NOT NULL,
	etat_enchere     VARCHAR(10) NOT NULL,
	no_vendeur       INTEGER NULL

)

ALTER TABLE ENCHERES ADD CONSTRAINT PK_ENCHERES PRIMARY KEY (no_utilisateur, no_article)

CREATE TABLE RETRAITS (
	no_article       INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(5) NOT NULL,
    ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD CONSTRAINT PK_RETRAITS PRIMARY KEY  (no_article)

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(75) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(5) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   BIT NOT NULL
)

ALTER TABLE UTILISATEURS ADD CONSTRAINT PK_UTILISATEUR PRIMARY KEY (no_utilisateur)


CREATE TABLE ARTICLES (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           DATE NOT NULL,
    date_fin_encheres             DATE NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
	etat_article                  VARCHAR(15) NOT NULL,
	photo						  IMAGE NULL,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL
)

ALTER TABLE ARTICLES ADD CONSTRAINT PK_ARTICLES PRIMARY KEY (no_article)

ALTER TABLE ENCHERES
    ADD CONSTRAINT FK_ENCHERES_UTILISATEURS FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE CASCADE 
    ON UPDATE no action 

ALTER TABLE ENCHERES
    ADD CONSTRAINT FK_ENCHERES_ARTICLES FOREIGN KEY ( no_article ) REFERENCES ARTICLES ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE RETRAITS
    ADD CONSTRAINT FK_RETRAITS_ARTICLES FOREIGN KEY ( no_article ) REFERENCES ARTICLES ( no_article )
ON DELETE CASCADE
    ON UPDATE no action 

ALTER TABLE ARTICLES
    ADD CONSTRAINT FK_ARTICLES_CATEGORIES FOREIGN KEY ( no_categorie ) REFERENCES CATEGORIES ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES
    ADD CONSTRAINT FK_VENTES_UTILISATEURS FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE CASCADE
    ON UPDATE no action 
