DROP SCHEMA IF EXISTS pae_project CASCADE;
CREATE SCHEMA pae_project;

CREATE TABLE pae_project.adresses(
    id_adresse serial PRIMARY KEY,
    rue varchar(100),
    numero int,
    boite varchar(10),	
    code_postal int,
    commune varchar(55),
    pays varchar(55)
);



CREATE TABLE pae_project.utilisateurs(
    id_utilisateur serial PRIMARY KEY,
    pseudo varchar(50),
    nom varchar(55),
    mot_de_passe varchar(255),
    prenom varchar(55),
    email varchar(100),
    date_inscription timestamp,
    role varchar(10),
    adresse integer REFERENCES pae_project.adresses(id_adresse)
);


CREATE TABLE pae_project.types_meubles(
    id_type serial PRIMARY KEY,
    nom varchar(100) NOT NULL
);

CREATE TABLE pae_project.visites(
    id_visite serial PRIMARY KEY,
    utilisateur integer references pae_project.utilisateurs(id_utilisateur) NOT NULL,
    plage_horaire varchar(255) NOT NULL,
    date_demande timestamp NOT NULL,
    date_visite timestamp,
    etat_visite varchar(10)  NOT NULL,
    adresse integer references pae_project.adresses(id_adresse),
    explication TEXT
);


CREATE TABLE pae_project.meubles(
    id_meuble serial PRIMARY KEY,
    etat_meuble varchar(10)  NOT NULL,
    type_meuble integer references pae_project.types_meubles(id_type) NOT NULL,
    description TEXT NOT NULL,
    prix_achat decimal,
    prix_vente decimal,
    prix_special decimal,
    photo_preferee integer,
    visite integer references pae_project.visites(id_visite) NOT NULL,
    date_emporte_patron timestamp,
    date_depot timestamp
);


CREATE TABLE pae_project.photos_meubles(
    id_photo serial PRIMARY KEY,
    meuble integer references pae_project.meubles(id_meuble) NOT NULL,
    photo varchar(255) NOT NULL,
    peut_defiler boolean NOT NULL
);

CREATE TABLE pae_project.ventes(
    id_vente serial PRIMARY KEY,
    meuble integer references pae_project.meubles(id_meuble) NOT NULL,
    client integer references pae_project.utilisateurs(id_utilisateur),
    etat_vente varchar(10)  NOT NULL,
    date_vente timestamp NOT NULL,
    date_emporte timestamp,
    date_livraison timestamp
);

CREATE TABLE pae_project.options(
    id_option serial PRIMARY KEY,
    client integer references pae_project.utilisateurs(id_utilisateur) NOT NULL,
    meuble integer references pae_project.meubles(id_meuble) NOT NULL,
    date_fin timestamp NOT NULL,
    date_debut timestamp NOT NULL
);


----------------------------------------------------------------------------------------------------------------------------------------------------------
--demo livrable 4

INSERT INTO pae_project.adresses VALUES 
	(DEFAULT,'Rue de l eglise','11','B1',4987,'Stoumont','Belgique');

INSERT INTO pae_project.adresses VALUES 
	(DEFAULT,'Rue de Renkin','7',null,4800,'Verviers','Belgique');

INSERT INTO pae_project.adresses VALUES 
	(DEFAULT,'Lammerskreuzstrasse','6',null,52159,'Roetgen','Allemagne');

INSERT INTO pae_project.adresses VALUES 
	(DEFAULT,'Sante des artistes','1','bis',4800,'Verviers','Belgique');

INSERT INTO pae_project.adresses VALUES 
	(DEFAULT,'Sante des artistes','18',null,4800,'Verviers','Belgique');


INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'Caro','Line','$2a$10$rH3.56s2nhYhK8a2JmYCVOF2xo1e7R3qnivfP4bmejhhddqyvl2u2','Caroline','caro.line@hotmail.com','2021-03-23','antiquaire',1);

INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'achil','Ile','$2a$10$5bXuYve9SatznJWB3u5pOeGqJ2fe43i9Av4T/3IfVFheIh9ot9rPW','Achile','achi.ile@hotmail.com','2021-03-23','valide',2);

INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'bazz','Ile','$2a$10$Dl3.V0fwHkdQIcdYsHHmOeH5bWkrUFtrRmSz/XyqPaqGFgepIY.yW','Basile','bas.ile@gmail.be','2021-03-23','valide',3);

INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'bert','Satcho','$2a$10$8aTlgRToDKhxgBzjrL.W7e7rXxaqDTF2MiWChzFLlKIu7SsTwqiW6','Albert','bert.satcho@gmail.be','2021-03-22','admin',4);
    -- mdp: Jaune;3

INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'lau','Satcho','$2a$10$BZ65WTisCgTiija8GB5V8Of2sYmicNYT8Ye6IR1d9h9bqzg5FcTNO','Laurent','laurent.satcho@gmail.be','2021-03-22','admin',5);
    -- mdp: Mauve;7




INSERT INTO pae_project.visites VALUES 
	(DEFAULT,2,'lundi de 18h a 22h','2021-03-24','2021-03-29','confirme',2,null);

INSERT INTO pae_project.visites VALUES 
	(DEFAULT,2,'lundi de 18h a 22h','2021-03-25',null,'annule',2,'meuble trop recent');

INSERT INTO pae_project.visites VALUES 
	(DEFAULT,3,'tous les jours de 15h a 18h','2021-03-25','2021-03-29','confirme',3,null);


INSERT INTO pae_project.types_meubles VALUES 
	(DEFAULT,'Bahut');

INSERT INTO pae_project.types_meubles VALUES 
	(DEFAULT,'Bureau');

INSERT INTO pae_project.types_meubles VALUES 
	(DEFAULT,'Table');

INSERT INTO pae_project.types_meubles VALUES 
	(DEFAULT,'Secretaire');

INSERT INTO pae_project.meubles VALUES 
	(DEFAULT,'achete',1,'Bahut profond d une largeur de 112cm et d une hauteur de 147cm',200,200,null,null,1,'2021-03-31',null);

INSERT INTO pae_project.meubles VALUES 
	(DEFAULT,'achete',2,'Largeur du bureau 1m87 cm, deux colonnes de tirois',159,159,null,null,1,'2021-03-31',null);

INSERT INTO pae_project.meubles VALUES 
	(DEFAULT,'annule',3,'Table jardin en bois brut',null,null,null,null,2,null,null);

INSERT INTO pae_project.meubles VALUES 
	(DEFAULT,'achete',3,'Table en chÃªne, pieds en fer forge',140,140,null,null,3,'2021-03-29',null);

INSERT INTO pae_project.meubles VALUES 
	(DEFAULT,'achete',4,'Secretaire en acajou, en marqueterie ',90,90,null,null,1,'2021-03-29',null);

    -- password: test
    -- password demo: mdpusr.2
    --