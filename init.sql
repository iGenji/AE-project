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

INSERT INTO pae_project.adresses VALUES 
	(DEFAULT,'Rue des Minières','45',"Ter",4800,'Verviers','Belgique');

INSERT INTO pae_project.adresses VALUES 
	(DEFAULT,'Rue Victor Bouillenne','9',"4C",4800,'Verviers','Belgique');


INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'Caro','Line','$2a$10$Zr5JSdPxzwRbzoq7QeiI5uifjh.iQBiXcLg.gsdDiYg2a7OuTeZpi','Caroline','caro.line@hotmail.com','2021-03-23','antiquaire',1);

INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'achil','Ile','$2a$10$B1WFCG66F9UQsXQq7ASqL./ZNf9nYbP5b7NQB2yeamGnyz8ZTC4VK','Achile','achi.ile@hotmail.com','2021-03-23','valide',2);

INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'bazz','Ile','$2a$10$WZDFMG8jc8rlIH9cXA1WCO2RpXKbwyffoW9Vt0eSYw/EP/SjK3xdC','Basile','bas.ile@gmail.be','2021-03-23','valide',3);

INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'bert','Satcho','$2a$10$Uf3k5TF61gAPln7N00dBfeBujGnRt0Lb/NYXKYsn9LSVuxF2ucyFC','Albert','bert.satcho@gmail.be','2021-03-22','admin',4);
    -- mdp: Jaune;10

INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'lau','Satcho','$2a$10$BZ65WTisCgTiija8GB5V8Of2sYmicNYT8Ye6IR1d9h9bqzg5FcTNO','Laurent','laurent.satcho@gmail.be','2021-03-22','admin',5);
    -- mdp: Mauve;7

INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'Theo','Ile','$2a$10$kCZkUtSMbjfmoyDAimtete6oBokLFwHyRQMwsayk9ChrkTqTeqd4G','Théophile','theo.phile@proximus.be','2021-03-30','valide',2);
    -- mdp: rouge;E6.

INSERT INTO pae_project.utilisateurs VALUES 
	(DEFAULT,'charline','Line','$2a$10$4MUGJeHO5hQigkgayKXVCuAOAjJVsgcbcIEQK2Vy2bEIk/8Rd3VC','Charles','charline@proximus.be','2021-04-22','valide',6);
    -- mdp: mdpusr.4

-- arreté ici, demande de visites demo finale
INSERT INTO pae_project.visites VALUES 
	(DEFAULT,2,'lundi de 18h a 22h','2021-03-24','2021-03-29','confirme',2,null);

INSERT INTO pae_project.visites VALUES 
	(DEFAULT,2,'lundi de 18h a 22h','2021-03-25',null,'annule',2,'meuble trop recent');

INSERT INTO pae_project.visites VALUES 
	(DEFAULT,3,'tous les jours de 15h a 18h','2021-03-25','2021-03-29','confirme',3,null);

INSERT INTO pae_project.visites VALUES 
	(DEFAULT,3,'tous les jours de 15h a 18h','2021-03-25','2021-03-29','confirme',3,null);

INSERT INTO pae_project.visites VALUES 
	(DEFAULT,6,'tous les matins de 9h à 13h','2021-04-21',null,'introduit',7,null);

INSERT INTO pae_project.visites VALUES 
	(DEFAULT,1,'tous les jours de 16h à 19h','2021-04-22','2021-04-26','confirme',1,null);


INSERT INTO pae_project.types_meubles VALUES --1
	(DEFAULT,'Bahut');

INSERT INTO pae_project.types_meubles VALUES --2
	(DEFAULT,'Bureau');

INSERT INTO pae_project.types_meubles VALUES --3
	(DEFAULT,'Table');

INSERT INTO pae_project.types_meubles VALUES --4
	(DEFAULT,'Secretaire');

INSERT INTO pae_project.types_meubles VALUES --5
	(DEFAULT,'Armoire');

INSERT INTO pae_project.types_meubles VALUES --6
	(DEFAULT,'Bibliothèque');

INSERT INTO pae_project.types_meubles VALUES --7
	(DEFAULT,'Bonnetière');

INSERT INTO pae_project.types_meubles VALUES --8
	(DEFAULT,'Buffet');

INSERT INTO pae_project.types_meubles VALUES --9
	(DEFAULT,'Chaise');

INSERT INTO pae_project.types_meubles VALUES --10
	(DEFAULT,'Chiffonnier');

INSERT INTO pae_project.types_meubles VALUES --11
	(DEFAULT,'Coffre');

INSERT INTO pae_project.types_meubles VALUES --12
	(DEFAULT,'Coiffeuse');

INSERT INTO pae_project.types_meubles VALUES --13
	(DEFAULT,'Commode');

INSERT INTO pae_project.types_meubles VALUES --14
	(DEFAULT,'Confident / Indiscret');

INSERT INTO pae_project.types_meubles VALUES --15
	(DEFAULT,'Console');

INSERT INTO pae_project.types_meubles VALUES --16
	(DEFAULT,'Dresse');

INSERT INTO pae_project.types_meubles VALUES --17
	(DEFAULT,'Fauteuil');

INSERT INTO pae_project.types_meubles VALUES --18
	(DEFAULT,'Guéridon');

INSERT INTO pae_project.types_meubles VALUES --19
	(DEFAULT,'Lingère');

INSERT INTO pae_project.types_meubles VALUES --20
	(DEFAULT,'Lit');

INSERT INTO pae_project.types_meubles VALUES --21
	(DEFAULT,'Lit');

INSERT INTO pae_project.types_meubles VALUES --22
	(DEFAULT,'Penderie');

INSERT INTO pae_project.types_meubles VALUES --23
	(DEFAULT,'Tabouret');

INSERT INTO pae_project.types_meubles VALUES --24
	(DEFAULT,'Vaisselier');

INSERT INTO pae_project.types_meubles VALUES --25
	(DEFAULT,'Valet muet');






INSERT INTO pae_project.photos_meubles VALUES --1
    (DEFAULT,1,'Bahut_2.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --2
    (DEFAULT,2,'Bureau_1.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --3
    (DEFAULT,2,'Bureau_1-Visible-Préférée.jpg',true);

INSERT INTO pae_project.photos_meubles VALUES --4
    (DEFAULT,3,'table-jardin-recente.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --5
    (DEFAULT,4,'Table.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --6
    (DEFAULT,5,'Secretaire.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --7
    (DEFAULT,6,'Lit_LitBaldaquin.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --8
    (DEFAULT,7,'Bureau_2.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --9
    (DEFAULT,8,'Bureau-3_ImageClient.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --10
    (DEFAULT,8,'Bureau-3-Visible.jpg',true);

INSERT INTO pae_project.photos_meubles VALUES --11
    (DEFAULT,8,'Bureau-3-Visible-Préférée.jpg',true);

INSERT INTO pae_project.photos_meubles VALUES --12
    (DEFAULT,9,'Bureau-8.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --13
    (DEFAULT,9,'Bureau-8-Visible-Préférée.jpg',true);

INSERT INTO pae_project.photos_meubles VALUES --14
    (DEFAULT,10,'Coiffeuse_1_Details.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --15
    (DEFAULT,10,'Coiffeuse_1-Visible-Préférée.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --16
    (DEFAULT,11,'Coiffeuse_2.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --17
    (DEFAULT,11,'Coiffeuse_2-Visible-Préférée.jpg',false);





INSERT INTO pae_project.meubles VALUES --1
	(DEFAULT,'depose',1,'Bahut profond d une largeur de 112cm et d une hauteur de 147cm',200,null,null,null,1,'2021-03-30',null);

INSERT INTO pae_project.meubles VALUES --2
	(DEFAULT,'achete',2,'Large bureau 1m87 cm, deux colonnes de tirois',159,299,null,3,1,'2021-03-30',null);

INSERT INTO pae_project.meubles VALUES --3
	(DEFAULT,'annule',3,'Table jardin en bois brut',null,null,null,null,2,null,null);

INSERT INTO pae_project.meubles VALUES --4
	(DEFAULT,'retire',3,'Table en chaine, pieds en fer forge',140,459,null,null,3,'2021-03-29',null);

INSERT INTO pae_project.meubles VALUES --5
	(DEFAULT,'depose',4,'Secretaire en acajou, en marqueterie ',90,null,null,null,3,'2021-03-29',null);

INSERT INTO pae_project.meubles VALUES --6
	(DEFAULT,'propose',20,'Lit a baldaquin en acajou',null,null,null,null,4,null,null);

INSERT INTO pae_project.meubles VALUES --7
	(DEFAULT,'restauration',2,'Bureau en bois cire ',220,null,null,null,5,'2021-04-27',null);

INSERT INTO pae_project.meubles VALUES --8
	(DEFAULT,'vente',2,'Bureau en chaine massif, sous-main integre ',325,378,null,11,5,'2021-04-27',null);

INSERT INTO pae_project.meubles VALUES --9
	(DEFAULT,'vente',2,'Magnifique bureau en acajou ',180,239,null,null,5,'2021-04-27',null);

INSERT INTO pae_project.meubles VALUES --10
	(DEFAULT,'vente',12,'Splendide coiffeuse aux reliefs travailles ',150,199,null,15,5,'2021-04-27',null);

INSERT INTO pae_project.meubles VALUES --11
	(DEFAULT,'vente',12,'Coiffeuse marqueterie',145,199,null,17,5,'2021-04-27',null);



INSERT INTO pae_project.photos_meubles VALUES --1
    (DEFAULT,1,'Bahut_2.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --2
    (DEFAULT,2,'Bureau_1.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --3
    (DEFAULT,2,'Bureau_1-Visible-Préférée.jpg',true);

INSERT INTO pae_project.photos_meubles VALUES --4
    (DEFAULT,3,'table-jardin-recente.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --5
    (DEFAULT,4,'Table.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --6
    (DEFAULT,5,'Secretaire.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --7
    (DEFAULT,6,'Lit_LitBaldaquin.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --8
    (DEFAULT,7,'Bureau_2.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --9
    (DEFAULT,8,'Bureau-3_ImageClient.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --10
    (DEFAULT,8,'Bureau-3-Visible.jpg',true);

INSERT INTO pae_project.photos_meubles VALUES --11
    (DEFAULT,8,'Bureau-3-Visible-Préférée.jpg',true);

INSERT INTO pae_project.photos_meubles VALUES --12
    (DEFAULT,9,'Bureau-8.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --13
    (DEFAULT,9,'Bureau-8-Visible-Préférée.jpg',true);

INSERT INTO pae_project.photos_meubles VALUES --14
    (DEFAULT,10,'Coiffeuse_1_Details.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --15
    (DEFAULT,10,'Coiffeuse_1-Visible-Préférée.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --16
    (DEFAULT,11,'Coiffeuse_2.jpg',false);

INSERT INTO pae_project.photos_meubles VALUES --17
    (DEFAULT,11,'Coiffeuse_2-Visible-Préférée.jpg',false);

    -- password: test
    -- password demo: mdpusr.2
    --