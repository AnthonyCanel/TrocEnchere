INSERT INTO CATEGORIES VALUES('Vêtements');
INSERT INTO CATEGORIES VALUES('Chaussures');
INSERT INTO CATEGORIES VALUES('Ameublement');
INSERT INTO CATEGORIES VALUES('Electoménager');
INSERT INTO CATEGORIES VALUES('Décoration');
INSERT INTO CATEGORIES VALUES('Bricolage');
INSERT INTO CATEGORIES VALUES('Informatique');
INSERT INTO CATEGORIES VALUES('Consoles&Jeux Vidéo');
INSERT INTO CATEGORIES VALUES('Image&Son');
INSERT INTO CATEGORIES VALUES('Téléphonie');
INSERT INTO CATEGORIES VALUES('DVD&Films');
INSERT INTO CATEGORIES VALUES('CD&Musique');
INSERT INTO CATEGORIES VALUES('Livres');
INSERT INTO CATEGORIES(libelle) VALUES('Autre');

INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES('jojo44', 'Bonnepioche', 'Joseph', 'joseph.bonnepioche2021@campus-eni.fr', '0606060606', 'Rue des mouettes', '44800', 'Saint-Herblain', 'toto', 0, 0)
INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES('NineJea', 'Crobert', 'Jeanine', 'j.crobert@bmail.fr', '0645250412', 'Rue des goélands', '44800', 'Saint-Herblain', 'dodo', 640, 0)

INSERT INTO ARTICLES(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_article, no_utilisateur, no_categorie) VALUES('guitare', 'guitare sèche un peu usé pour jouer des chansons au coin du feu', '2021/07/01', '2021/07/15', 125, 0, 'disponible', 2, 12)
INSERT INTO ARTICLES(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_article, no_utilisateur, no_categorie) VALUES('CyberPunk 2077', 'jeu PC complètement buggé', '2021/06/23', '2021/07/15', 25, 0, 'disponible', 1, 8)
INSERT INTO ARTICLES(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_article, no_utilisateur, no_categorie) VALUES('théière', 'ceci n''est pas une théière', '2021/06/15', '2021/07/04', 25,0,'enchere',2,4)

INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES(1,'2B rue Benjamin Franklin', '44800, 'Saint-Herblain')

INSERT INTO ENCHERES(no_utilisateur, no_article, date_enchere, montant_enchere, etat_enchere) VALUES(1, 3,CONVERT(DATETIME,'18/06/2021 6:30:00 PM',103), 35, 'NonVendu')