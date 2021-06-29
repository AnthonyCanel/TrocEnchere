ALTER TABLE ENCHERES
    DROP CONSTRAINT PK_ENCHERES;

ALTER TABLE ENCHERES
    ADD CONSTRAINT PK_utilisateur_article_montant PRIMARY KEY (no_utilisateur,no_article,montant_enchere);

ALTER TABLE ENCHERES
    ADD der_ench BIT NOT NULL CONSTRAINT df_value_derniere_enchere default 0;


