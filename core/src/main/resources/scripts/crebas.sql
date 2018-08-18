/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  2018/08/18 7:35:57                       */
/*==============================================================*/

drop database food_db;

create database food_db;

use food_db;

/*==============================================================*/
/* Table : adresse                                              */
/*==============================================================*/
create table adresse
(
   id_adresse           int not null auto_increment,
   voirie               varchar(255),
   code_postal          varchar(255),
   ville                varchar(255),
   latitude             varchar(255),
   longitude            varchar(255),
   adr_principale       smallint,
   date_ajout_adr       date,
   date_retrait_adr     date,
   primary key (id_adresse)
);

/*==============================================================*/
/* Table : annonce                                              */
/*==============================================================*/
create table annonce
(
   id_publi             int not null auto_increment,
   id_user              int not null,
   id_motif_retrait     int,
   id_prod_stock        int not null,
   id_adresse           int not null,
   titre                varchar(255),
   description          varchar(255),
   qte_publi            double,
   date_publication     date,
   date_retrait         date,
   primary key (id_publi)
);

/*==============================================================*/
/* Table : categorie                                            */
/*==============================================================*/
create table categorie
(
   id_cat               int not null auto_increment,
   libelle_cat          varchar(255),
   primary key (id_cat)
);

/*==============================================================*/
/* Table : conservation                                         */
/*==============================================================*/
create table conservation
(
   id_conserv           int not null auto_increment,
   type                 varchar(255),
   duree_ext_conserv    int,
   primary key (id_conserv)
);

/*==============================================================*/
/* Table : consommation                                         */
/*==============================================================*/
create table consommation
(
   id_conso             int not null auto_increment,
   id_prod_stock        int not null,
   id_mode              int not null,
   date_conso           date,
   qte_conso            double,
   primary key (id_conso)
);

/*==============================================================*/
/* Table : contact                                              */
/*==============================================================*/
create table contact
(
   id_contact           int not null auto_increment,
   id_user              int not null,
   Id_friend            int not null,
   date_invitation      date,
   date_refus           date,
   date_acceptation     date,
   date_suppression     date,
   primary key (id_contact)
);

/*==============================================================*/
/* Table : evaluation                                           */
/*==============================================================*/
create table evaluation
(
   id_eval              int not null auto_increment,
   id_user              int not null,
   id_reponse           int not null,
   note                 int,
   com_eval             varchar(255),
   auteur_eval          smallint,
   primary key (id_eval)
);

/*==============================================================*/
/* Table : mode_conso                                           */
/*==============================================================*/
create table mode_conso
(
   id_mode              int not null auto_increment,
   libelle_mode         varchar(255),
   primary key (id_mode)
);

/*==============================================================*/
/* Table : motif_annulation                                     */
/*==============================================================*/
create table motif_annulation
(
   id_motif_annul       int not null auto_increment,
   libelle_annul        varchar(255),
   primary key (id_motif_annul)
);

/*==============================================================*/
/* Table : motif_retrait                                        */
/*==============================================================*/
create table motif_retrait
(
   id_motif_retrait     int not null auto_increment,
   libelle_retrait      varchar(255),
   primary key (id_motif_retrait)
);

/*==============================================================*/
/* Table : produit                                              */
/*==============================================================*/
create table produit
(
   id_prod              int not null auto_increment,
   id_sous_cat          int not null,
   libelle_prod         varchar(255),
   code                 varchar(255),
   image                varchar(255),
   primary key (id_prod)
);

/*==============================================================*/
/* Table : reponse                                              */
/*==============================================================*/
create table reponse
(
   id_reponse           int not null auto_increment,
   id_user              int not null,
   id_motif_annul       int,
   id_publi             int not null,
   date_demande         date,
   date_selection       date,
   date_annulation      date,
   date_transaction     date,
   primary key (id_reponse)
);

/*==============================================================*/
/* Table : resider                                              */
/*==============================================================*/
create table resider
(
   id_user              int not null,
   id_adresse           int not null,
   primary key (id_user, id_adresse)
);

/*==============================================================*/
/* Table : sous_categorie                                       */
/*==============================================================*/
create table sous_categorie
(
   id_sous_cat          int not null auto_increment,
   id_cat               int not null,
   libelle_sous_cat     varchar(255),
   duree_ext_scat       int,
   primary key (id_sous_cat)
);

/*==============================================================*/
/* Table : stock                                                */
/*==============================================================*/
create table stock
(
   id_prod_stock        int not null auto_increment,
   id_unite             int not null,
   id_conserv           int not null,
   id_user              int not null,
   id_prod              int not null,
   date_peremption      date,
   duree_ext_stock      int,
   prix                 double,
   qte_initiale         double,
   date_ajout           date,
   primary key (id_prod_stock)
);

/*==============================================================*/
/* Table : unite                                                */
/*==============================================================*/
create table unite
(
   id_unite             int not null auto_increment,
   libelle_unite        varchar(255),
   primary key (id_unite)
);

/*==============================================================*/
/* Table : utilisateur                                          */
/*==============================================================*/
create table utilisateur
(
   id_user              int not null auto_increment,
   date_naissance       date,
   sexe                 smallint,
   nom                  varchar(255),
   prenom               varchar(255),
   login                varchar(255),
   password             varchar(255),
   telephone            varchar(255),
   mail                 varchar(255),
   portrait             varchar(255),
   date_inscription     date,
   date_desinscription  date,
   bat_param_p          int,
   bat_param_e          int,
   primary key (id_user)
);

alter table annonce add constraint FK_Donner foreign key (id_motif_retrait)
      references motif_retrait (id_motif_retrait) on delete restrict on update restrict;

alter table annonce add constraint FK_partager foreign key (id_prod_stock)
      references stock (id_prod_stock) on delete restrict on update restrict;

alter table annonce add constraint FK_publier foreign key (id_user)
      references utilisateur (id_user) on delete restrict on update restrict;

alter table annonce add constraint FK_selectionner foreign key (id_adresse)
      references adresse (id_adresse) on delete restrict on update restrict;

alter table consommation add constraint FK_consommer foreign key (id_prod_stock)
      references stock (id_prod_stock) on delete restrict on update restrict;

alter table consommation add constraint FK_preciser foreign key (id_mode)
      references mode_conso (id_mode) on delete restrict on update restrict;

alter table contact add constraint FK_inviter foreign key (Id_friend)
      references utilisateur (id_user) on delete restrict on update restrict;

alter table contact add constraint FK_socialiser foreign key (id_user)
      references utilisateur (id_user) on delete restrict on update restrict;

alter table evaluation add constraint FK_evaluer foreign key (id_user)
      references utilisateur (id_user) on delete restrict on update restrict;

alter table evaluation add constraint FK_noter foreign key (id_reponse)
      references reponse (id_reponse) on delete restrict on update restrict;

alter table produit add constraint FK_classer foreign key (id_sous_cat)
      references sous_categorie (id_sous_cat) on delete restrict on update restrict;

alter table reponse add constraint FK_contenir foreign key (id_publi)
      references annonce (id_publi) on delete restrict on update restrict;

alter table reponse add constraint FK_justifier foreign key (id_motif_annul)
      references motif_annulation (id_motif_annul) on delete restrict on update restrict;

alter table reponse add constraint FK_repondre foreign key (id_user)
      references utilisateur (id_user) on delete restrict on update restrict;

alter table resider add constraint FK_resider foreign key (id_user)
      references utilisateur (id_user) on delete restrict on update restrict;

alter table resider add constraint FK_resider2 foreign key (id_adresse)
      references adresse (id_adresse) on delete restrict on update restrict;

alter table sous_categorie add constraint FK_categoriser foreign key (id_cat)
      references categorie (id_cat) on delete restrict on update restrict;

alter table stock add constraint FK_ajouter foreign key (id_user)
      references utilisateur (id_user) on delete restrict on update restrict;

alter table stock add constraint FK_conserver foreign key (id_conserv)
      references conservation (id_conserv) on delete restrict on update restrict;

alter table stock add constraint FK_mesurer foreign key (id_unite)
      references unite (id_unite) on delete restrict on update restrict;

alter table stock add constraint FK_referencer foreign key (id_prod)
      references produit (id_prod) on delete restrict on update restrict;

