/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  2018/08/10 18:44:18                      */
/*==============================================================*/

drop database food_db;

create database food_db;

use food_db;

/*==============================================================*/
/* Table : Adresse                                              */
/*==============================================================*/
create table Adresse
(
   Id_adresse           int not null AUTO_INCREMENT,
   Voirie               varchar(255),
   Code_postal          varchar(255),
   Ville                varchar(255),
   Latitude             varchar(255),
   Longitude            varchar(255),
   Adr_principale       smallint,
   Date_ajout_adr       date,
   Date_retrait_adr     date,
   primary key (Id_adresse)
);

/*==============================================================*/
/* Table : Annonce                                              */
/*==============================================================*/
create table Annonce
(
   Id_publi             int not null AUTO_INCREMENT,
   Id_user              int not null,
   Id_motif_retrait     int,
   Id_prod_stock        int not null,
   Id_adresse           int not null,
   Titre                varchar(255),
   Description          varchar(255),
   Qte_publi            double,
   Date_publication     date,
   Date_retrait         date,
   primary key (Id_publi)
);

/*==============================================================*/
/* Table : Categorie                                            */
/*==============================================================*/
create table Categorie
(
   Id_cat               int not null AUTO_INCREMENT,
   Libelle_cat          varchar(255),
   primary key (Id_cat)
);

/*==============================================================*/
/* Table : Conservation                                         */
/*==============================================================*/
create table Conservation
(
   Id_conserv           int not null AUTO_INCREMENT,
   Type                 varchar(255),
   Duree_ext_conserv    int,
   primary key (Id_conserv)
);

/*==============================================================*/
/* Table : Consommation                                         */
/*==============================================================*/
create table Consommation
(
   Id_conso             int not null AUTO_INCREMENT,
   Id_prod_stock        int not null,
   Id_mode              int not null,
   Date_conso           date,
   Qte_conso            double,
   primary key (Id_conso)
);

/*==============================================================*/
/* Table : Contact                                              */
/*==============================================================*/
create table Contact
(
   Id_contact           int not null AUTO_INCREMENT,
   Id_user              int not null,
   Id_friend            int not null,
   Date_invitation      date,
   Date_refus           date,
   Date_acceptation     date,
   Date_suppression     date,
   primary key (Id_contact)
);

/*==============================================================*/
/* Table : Evaluation                                           */
/*==============================================================*/
create table Evaluation
(
   Id_eval              int not null AUTO_INCREMENT,
   Id_user              int not null,
   Id_reponse           int not null,
   Note                 int,
   Com_eval             varchar(255),
   Auteur_eval          smallint,
   primary key (Id_eval)
);

/*==============================================================*/
/* Table : Mode_conso                                           */
/*==============================================================*/
create table Mode_conso
(
   Id_mode              int not null AUTO_INCREMENT,
   Libelle_mode         varchar(255),
   primary key (Id_mode)
);

/*==============================================================*/
/* Table : Motif_annulation                                     */
/*==============================================================*/
create table Motif_annulation
(
   Id_motif_annul       int not null AUTO_INCREMENT,
   Libelle_annul        varchar(255),
   primary key (Id_motif_annul)
);

/*==============================================================*/
/* Table : Motif_retrait                                        */
/*==============================================================*/
create table Motif_retrait
(
   Id_motif_retrait     int not null AUTO_INCREMENT,
   Libelle_retrait      varchar(255),
   primary key (Id_motif_retrait)
);

/*==============================================================*/
/* Table : Produit                                              */
/*==============================================================*/
create table Produit
(
   Id_prod              int not null AUTO_INCREMENT,
   Id_sous_cat          int not null,
   Libelle_prod         varchar(255),
   Code                 varchar(255),
   Image                varchar(255),
   primary key (Id_prod)
);

/*==============================================================*/
/* Table : Reponse                                              */
/*==============================================================*/
create table Reponse
(
   Id_reponse           int not null AUTO_INCREMENT,
   Id_user              int not null,
   Id_motif_annul       int,
   Id_publi             int not null,
   Date_demande         date,
   Date_selection       date,
   Date_annulation      date,
   Date_transaction     date,
   primary key (Id_reponse)
);

/*==============================================================*/
/* Table : Resider                                              */
/*==============================================================*/
create table Resider
(
   Id_user              int not null,
   Id_adresse           int not null,
   primary key (Id_user, Id_adresse)
);

/*==============================================================*/
/* Table : Sous_categorie                                       */
/*==============================================================*/
create table Sous_categorie
(
   Id_sous_cat          int not null AUTO_INCREMENT,
   Id_cat               int not null,
   Libelle_sous_cat     varchar(255),
   Duree_ext_scat       int,
   primary key (Id_sous_cat)
);

/*==============================================================*/
/* Table : Stock                                                */
/*==============================================================*/
create table Stock
(
   Id_prod_stock        int not null AUTO_INCREMENT,
   Id_unite             int not null,
   Id_conserv           int not null,
   Id_user              int not null,
   Id_prod              int not null,
   Date_peremption      date,
   Duree_ext_stock      int,
   Prix                 double,
   Qte_initiale         double,
   Date_ajout           date,
   primary key (Id_prod_stock)
);

/*==============================================================*/
/* Table : Unite                                                */
/*==============================================================*/
create table Unite
(
   Id_unite             int not null AUTO_INCREMENT,
   Libelle_unite        varchar(255),
   primary key (Id_unite)
);

/*==============================================================*/
/* Table : Utilisateur                                          */
/*==============================================================*/
create table Utilisateur
(
   Id_user              int not null AUTO_INCREMENT,
   Date_naissance       date,
   Sexe                 smallint,
   Nom                  varchar(255),
   Prenom               varchar(255),
   Login                varchar(255),
   Password             varchar(255),
   Telephone            varchar(255),
   Mail                 varchar(255),
   Portrait             varchar(255),
   Date_inscription     date,
   Date_desinscription  date,
   primary key (Id_user)
);

alter table Annonce add constraint FK_Donner foreign key (Id_motif_retrait)
      references Motif_retrait (Id_motif_retrait) on delete restrict on update restrict;

alter table Annonce add constraint FK_Partager foreign key (Id_prod_stock)
      references Stock (Id_prod_stock) on delete restrict on update restrict;

alter table Annonce add constraint FK_Publier foreign key (Id_user)
      references Utilisateur (Id_user) on delete restrict on update restrict;

alter table Annonce add constraint FK_Selectionner foreign key (Id_adresse)
      references Adresse (Id_adresse) on delete restrict on update restrict;

alter table Consommation add constraint FK_Consommer foreign key (Id_prod_stock)
      references Stock (Id_prod_stock) on delete restrict on update restrict;

alter table Consommation add constraint FK_Preciser foreign key (Id_mode)
      references Mode_conso (Id_mode) on delete restrict on update restrict;

alter table Contact add constraint FK_Inviter foreign key (Id_friend)
      references Utilisateur (Id_user) on delete restrict on update restrict;

alter table Contact add constraint FK_Socialiser foreign key (Id_user)
      references Utilisateur (Id_user) on delete restrict on update restrict;

alter table Evaluation add constraint FK_Evaluer foreign key (Id_user)
      references Utilisateur (Id_user) on delete restrict on update restrict;

alter table Evaluation add constraint FK_Noter foreign key (Id_reponse)
      references Reponse (Id_reponse) on delete restrict on update restrict;

alter table Produit add constraint FK_Classer foreign key (Id_sous_cat)
      references Sous_categorie (Id_sous_cat) on delete restrict on update restrict;

alter table Reponse add constraint FK_Contenir foreign key (Id_publi)
      references Annonce (Id_publi) on delete restrict on update restrict;

alter table Reponse add constraint FK_Justifier foreign key (Id_motif_annul)
      references Motif_annulation (Id_motif_annul) on delete restrict on update restrict;

alter table Reponse add constraint FK_Repondre foreign key (Id_user)
      references Utilisateur (Id_user) on delete restrict on update restrict;

alter table Resider add constraint FK_Resider foreign key (Id_user)
      references Utilisateur (Id_user) on delete restrict on update restrict;

alter table Resider add constraint FK_Resider2 foreign key (Id_adresse)
      references Adresse (Id_adresse) on delete restrict on update restrict;

alter table Sous_categorie add constraint FK_Categoriser foreign key (Id_cat)
      references Categorie (Id_cat) on delete restrict on update restrict;

alter table Stock add constraint FK_Ajouter foreign key (Id_user)
      references Utilisateur (Id_user) on delete restrict on update restrict;

alter table Stock add constraint FK_Conserver foreign key (Id_conserv)
      references Conservation (Id_conserv) on delete restrict on update restrict;

alter table Stock add constraint FK_Mesurer foreign key (Id_unite)
      references Unite (Id_unite) on delete restrict on update restrict;

alter table Stock add constraint FK_Referencer foreign key (Id_prod)
      references Produit (Id_prod) on delete restrict on update restrict;

