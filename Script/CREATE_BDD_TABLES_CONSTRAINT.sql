CREATE DATABASE TROC_ENCHERES
GO
USE TROC_ENCHERES
 --TABLE CATEGORIES
CREATE TABLE CATEGORIES
(
    no_categorie INTEGER IDENTITY
        CONSTRAINT PK_CATEGORIES
        PRIMARY KEY,
    libelle      VARCHAR(30) NOT NULL
        CONSTRAINT categories_libelle_unique
            UNIQUE
)
GO
--TABLE ENCHERES
CREATE TABLE ENCHERES
(
    no_utilisateur  INTEGER                            NOT NULL
        CONSTRAINT FK_ENCHERES_UTILISATEURS
            REFERENCES UTILISATEURS,
    no_article      INTEGER                            NOT NULL
        CONSTRAINT FK_ENCHERES_ARTICLES
            REFERENCES ARTICLES,
    date_enchere    datetime                           NOT NULL,
    montant_enchere INTEGER                            NOT NULL,
    etat_enchere    VARCHAR(10)                        NOT NULL,
    no_acquereur    INTEGER,
    der_ench        BIT
        CONSTRAINT df_value_derniere_enchere DEFAULT 0 NOT NULL,
    CONSTRAINT PK_utilisateur_article_montant
        PRIMARY KEY (no_utilisateur, no_article, montant_enchere)
)
GO
--TABLE RETRAITS
CREATE TABLE RETRAITS
(
    no_article  INTEGER         NOT NULL
        CONSTRAINT PK_RETRAITS
            PRIMARY KEY
        CONSTRAINT FK_RETRAITS_ARTICLES
            REFERENCES ARTICLES
            ON DELETE CASCADE,
    rue         VARCHAR(30) NOT NULL,
    code_postal VARCHAR(5)  NOT NULL,
    ville       VARCHAR(30) NOT NULL
)
GO
--TABLE UTILISATEURS
CREATE TABLE UTILISATEURS
(
    no_utilisateur INTEGER IDENTITY
        CONSTRAINT PK_UTILISATEUR
        PRIMARY KEY,
    pseudo         VARCHAR(30) NOT NULL,
    nom            VARCHAR(30) NOT NULL,
    prenom         VARCHAR(30) NOT NULL,
    email          VARCHAR(75) NOT NULL,
    telephone      VARCHAR(15),
    rue            VARCHAR(30) NOT NULL,
    code_postal    VARCHAR(5)  NOT NULL,
    ville          VARCHAR(30) NOT NULL,
    mot_de_passe   VARCHAR(30) NOT NULL,
    credit         INT         NOT NULL,
    administrateur BIT         NOT NULL
)
GO
-- TABLE ARTICLES
CREATE TABLE ARTICLES
(
    no_article          INTEGER IDENTITY
        CONSTRAINT PK_ARTICLES
        PRIMARY KEY,
    nom_article         VARCHAR(30)  NOT NULL,
    description         VARCHAR(300) NOT NULL,
    date_debut_encheres DATE         NOT NULL,
    date_fin_encheres   DATE         NOT NULL,
    prix_initial        INTEGER,
    prix_vente          INTEGER,
    etat_article        VARCHAR(15)  NOT NULL,
    photo               image,
    no_utilisateur      INTEGER          NOT NULL
        CONSTRAINT FK_VENTES_UTILISATEURS
            REFERENCES UTILISATEURS
            ON DELETE CASCADE,
    no_categorie        INTEGER          NOT NULL
        CONSTRAINT FK_ARTICLES_CATEGORIES
            REFERENCES CATEGORIES,
    vues                INTEGER
)
GO
CREATE index in_articles_no_utilisateur
    ON ARTICLES (no_utilisateur)
GO
CREATE index in_articles_no_categorie
    ON ARTICLES (no_categorie)
GO
CREATE index in_articles_nom_article
    ON ARTICLES (nom_article)
GO
--CREATE VIEWS
CREATE VIEW V_ARTICLES_CATEGORIES_UTILISATEURS
AS SELECT A.no_article, A.nom_article, A.description, A.date_debut_encheres, A.date_fin_encheres, A.prix_initial, A.prix_vente, A.etat_article, A.photo, A.vues,
          C.no_categorie, C.libelle, U.no_utilisateur, U.pseudo, U.nom, U.prenom, U.email, U.telephone, U.rue, U.code_postal, U.ville, U.mot_de_passe, U.credit, U.administrateur
   FROM ARTICLES  AS A INNER JOIN CATEGORIES AS C ON A.no_categorie = C.no_categorie
                       INNER JOIN UTILISATEURS AS U ON A.no_utilisateur = U.no_utilisateur
GO

CREATE  VIEW V_ARTICLES_CATEGORIES_UTILISATEURS_ENCHERES
    AS SELECT Articles.no_article AS arts_no_articles,
            Articles.nom_article AS arts_nom_article,
            Articles.description AS arts_description,
            Articles.date_debut_encheres AS arts_date_debut_encheres,
            Articles.date_fin_encheres AS arts_date_fin_encheres,
            Articles.prix_initial AS arts_prix_initial,
            Articles.prix_vente AS arts_prix_vente,
            Articles.etat_article AS arts_etat_article,
            Articles.photo AS arts_photo,
            articles.no_utilisateur AS arts_no_utilisateur,
            articles.no_categorie AS arts_no_categorie,

            CATEGORIES.no_categorie AS cats_no_categorie,
            CATEGORIES.libelle AS cats_libelle,

            utilisateurs.no_utilisateur AS utils_no_utilisateur,
            utilisateurs.pseudo AS utils_pseudo,
            utilisateurs.nom AS utils_nom,
            utilisateurs.prenom AS utils_prenom,
            utilisateurs.email AS utils_email,
            utilisateurs.telephone AS utils_telephone,
            utilisateurs.rue AS utils_rue,
            utilisateurs.code_postal AS utils_code_postal,
            utilisateurs.ville AS utils_ville,
            utilisateurs.mot_de_passe AS utils_mot_de_passe,
            utilisateurs.credit AS utils_credit,
            utilisateurs.administrateur utils_administrateur,

            encheres.no_utilisateur AS encs_no_utilisateur,
            encheres.no_article AS encs_no_article,
            encheres.date_enchere AS encs_date_enchere,
            encheres.montant_enchere AS encs_montant_enchere,
            encheres.etat_enchere AS encs_etat_enchere,
            encheres.no_acquereur AS encs_no_acquereur,
            encheres.der_ench AS encs_derniere_enchere
        FROM
            ARTICLES LEFT OUTER JOIN  CATEGORIES ON ARTICLES.no_categorie = CATEGORIES.no_categorie
                     LEFT OUTER JOIN ENCHERES ON ARTICLES.no_article = ENCHERES.no_article
                     LEFT OUTER JOIN  UTILISATEURS ON ARTICLES.no_utilisateur = UTILISATEURS.no_utilisateur
GO

CREATE VIEW V_RETRAITS_ARTICLES_UTILISATEURS
AS SELECT A.no_article, A.nom_article, A.description, A.date_debut_encheres, A.date_fin_encheres, A.prix_initial, A.prix_vente, A.etat_article, A.photo, A.vues,
          R.rue AS rue_Retrait, R.code_postal AS codePostal_Retrait, R.ville AS ville_Retrait, U.no_utilisateur, U.pseudo, U.nom, U.prenom, U.email, U.telephone, U.rue AS rueUtilisateur,
          U.code_postal AS codePostalUtilisateur, U.ville AS villeUtilisateur, U.mot_de_passe, U.credit FROM RETRAITS AS R INNER JOIN ARTICLES AS A ON R.no_article = A.no_article
                                                                                                                           INNER JOIN UTILISATEURS AS U ON A.no_utilisateur = U.no_utilisateur
GO

CREATE VIEW  [dbo].[V_UTIL_ENCHERES_ARTICLES_CATEGORIES_LEFT_RETRAITS]
AS SELECT U.no_utilisateur, U.pseudo, U.nom, U.prenom, U.email, U.telephone, U.rue AS rueUtilisateur,
          U.code_postal AS codePostalUtilisateur, U.ville AS villeUtilisateur, U.mot_de_passe, U.credit, U.administrateur, E.no_utilisateur AS utilisateurEnchere,
          E.date_enchere, E.montant_enchere, E.etat_enchere,E.no_acquereur,
          A.no_article, A.nom_article, A.description, A.date_debut_encheres, A.date_fin_encheres, A.prix_initial, A.prix_vente, A.etat_article, A.photo, A.vues,
          R.rue AS rueRetrait, R.code_postal AS codePostalRetrait, R.ville AS villeRetrait,
          C.no_categorie, C.libelle
   FROM UTILISATEURS AS U INNER JOIN ARTICLES AS A ON U.no_utilisateur = A.no_utilisateur
                          INNER JOIN ENCHERES AS E ON E.no_article = A.no_article
                          LEFT JOIN RETRAITS AS R ON A.no_article = R.no_article
                          INNER JOIN CATEGORIES AS C ON A.no_categorie = C.no_categorie
GO

CREATE VIEW V_UTILISATEURS_ARTICLES_RETRAITS_CATEGORIES
AS SELECT U.no_utilisateur, U.pseudo, U.nom, U.prenom, U.email, U.telephone, U.rue AS rueUtilisateur,
          U.code_postal AS codePostalUtilisateur, U.ville AS villeUtilisateur, U.mot_de_passe, U.credit, U.administrateur,
          A.no_article, A.nom_article, A.description, A.date_debut_encheres, A.date_fin_encheres, A.prix_initial, A.prix_vente, A.etat_article, A.photo, A.vues,
          R.rue AS rueRetrait, R.code_postal AS codePostalRetrait, R.ville AS villeRetrait,
          C.no_categorie, C.libelle
   FROM UTILISATEURS AS U INNER JOIN ARTICLES AS A ON U.no_utilisateur = A.no_utilisateur
                          INNER JOIN RETRAITS AS R ON A.no_article = R.no_article
                          INNER JOIN CATEGORIES AS C ON A.no_categorie = C.no_categorie
GO

CREATE VIEW [dbo].[V_UTILISATEURS_ENCHERES_ARTICLES]
AS SELECT U.no_utilisateur, U.pseudo, U.nom, U.prenom, U.email, U.telephone, U.rue, U.code_postal, U.ville, U.mot_de_passe, U.credit, U.administrateur,
          E.date_enchere, E.etat_enchere, E.montant_enchere, E.der_ench,
          A.no_article, A.nom_article, A.description, A.date_debut_encheres, A.date_fin_encheres, A.prix_initial, A.prix_vente, A.etat_article, A.photo, A.vues
   FROM UTILISATEURS AS U INNER JOIN ENCHERES AS E ON U.no_utilisateur = E.no_utilisateur
                          INNER JOIN ARTICLES AS A ON E.no_article = A.no_article
GO

CREATE VIEW V_UTILISATEURS_ENCHERES_ARTICLES_RETRAITS_CATEGORIES
AS SELECT U.no_utilisateur, U.pseudo, U.nom, U.prenom, U.email, U.telephone, U.rue AS rueUtilisateur,
          U.code_postal AS codePostalUtilisateur, U.ville AS villeUtilisateur, U.mot_de_passe, U.credit, U.administrateur,
          E.date_enchere, E.montant_enchere, E.etat_enchere,E.no_acquereur,
          A.no_article, A.nom_article, A.description, A.date_debut_encheres, A.date_fin_encheres, A.prix_initial, A.prix_vente, A.etat_article, A.photo, A.vues,
          R.rue AS rueRetrait, R.code_postal AS codePostalRetrait, R.ville AS villeRetrait,
          C.no_categorie, C.libelle
   FROM UTILISATEURS AS U INNER JOIN ENCHERES AS E ON U.no_utilisateur = E.no_utilisateur INNER JOIN ARTICLES AS A ON U.no_utilisateur = A.no_utilisateur
                          INNER JOIN RETRAITS AS R ON A.no_article = R.no_article
                          INNER JOIN CATEGORIES AS C ON A.no_categorie = C.no_categorie
GO