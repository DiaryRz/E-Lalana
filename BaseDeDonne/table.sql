drop database construction;
create database construction;
\c construction;

create table Type(
    idType serial primary key not null,
    nomType varchar(100)
);

create table Qualite(
    idQualite serial primary key not null,
    nomQualite varchar(100),
    longueur double precision,
    largeur double precision,
    niveauQualite int default 1
);

 
create table Unite(
    idUnite serial primary key not null,
    nomUnite varchar(100)
);

create table MaterielTsotra(
    idMateriel serial primary key not null,
    nomMateriel varchar(100)
);

create table MaterielPrix(
    idMaterielPrix serial not null,
    idMateriel int not null,
    prix double precision,
    datePrixMateriel date default now(),
    idUnite int not null,
    foreign key(idMateriel) references MaterielTsotra(idMateriel),
    foreign key (idUnite) references Unite(idUnite) 
);

create table MaterielType(
    idMaterielType serial primary key not null,
    idType int not null,
    idMateriel int not null,
    idQualite int not null,
    QantiteParMettreCarre double precision,
    foreign key (idType) references Type(idType),
    foreign key (idQualite) references Qualite(idQualite),
    foreign key (idMateriel) references MaterielTsotra(idMateriel)
);

create table Route (
    idRoute serial primary key not null,
    idType int not null,
    nomRoute varchar(100),
    identifiant int default 0,
    foreign key (idType) references Type(idType)
);

create table RouteQualite (
    idRouteQualite serial primary key not null,
    idRoute int not null,
    idQualite int not null,
    identifiant int default 0,
    NomRouteQualite varchar(100),
    foreign key (idRoute) references Route(idRoute),
    foreign key (idQualite) references Qualite(idQualite)
);

create table Stock(
    idStock serial primary key not null,
    idMateriel int not null,
    Quantite double precision,
    DateStock date default now(),
    PrixEnStock double precision,
    foreign key (idMateriel) references MaterielTsotra(idMateriel)
);

create table RouteFabrique(
    idRouteFabrique serial primary key not null,
    idRouteQualite int not null,
    DateFabrication date default now(),
    Nombre double precision,
    foreign key (idRouteQualite) references RouteQualite(idRouteQualite)
);

create table Personnel(
    idPersonnel serial primary key not null,
    nomPersonnel varchar(100),
    expMin double precision,
    expMax double precision,
    augmentation double precision
);


create table EmployeTravaillant(
    idEmployeTravaillant serial primary key not null,
    idQualite int not null,
    idPersonnel int not null,
    Salaire double precision,
    Surface double precision,
    Nombre double precision,
    foreign key (idQualite) references Qualite(idQualite),
    foreign key (idPersonnel) references Personnel(idPersonnel)
);


create table PrixVente(
    idPrixVente serial primary key not null,
    idRouteQualite int not null,
    prix double precision,
    foreign key (idRouteQualite) references RouteQualite(idRouteQualite)
);

create table Personne(
    idPersonne serial not null primary key,
    nomPersonne varchar(100),
    idPersonnel int not null,
    dateNaissance date,
    dateEmbauche date default now(),
    salaireBase double precision,
    foreign key(idPersonnel) references Personnel(idPersonnel)
);

create table postePersonne(
    idPostePersonne serial not null primary key,
    idPersonne int not null,
    idPersonnel int not null,
    tauxHoraire double precision,
    foreign key(idPersonne) references Personne(idPersonne)
);

create table historiquePoste(
    idHistoriquePoste serial not null primary key,
    idPersonne int not null,
    idPersonnel int not null,
    tauxHoraire double precision,
    foreign key(idPersonne) references Personne(idPersonne),
    foreign key(idPersonnel) references Personnel(idPersonnel)
);


create table TypeClient(
    idTypeClient serial not null primary key,
    nomTypeClient varchar(100)
);


create table Client(
    idClient serial not null primary key,
    nomClient varchar(100),
    idTypeClient int not null,
    foreign key(idTypeClient) references TypeClient(idTypeClient)
);

create table vente(
    idVente serial not null primary key,
    idRouteQualite int not null,
    idClient int not null,
    DateVente date default now(),
    prix double precision,
    foreign key(idRouteQualite) references routeQualite(idRouteQualite),
    foreign key(idClient) references Client(idClient)
);