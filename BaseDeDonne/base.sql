drop database construction;
create database construction;
\c construction;

create table Type(
    idType serial primary key not null,
    nomType varchar(100)
);

insert into Type(nomType) values('goudron');
insert into Type(nomType) values('paver');

create table Qualite(
    idQualite serial primary key not null,
    nomQualite varchar(100),
    longueur double precision,
    largeur double precision,
    niveauQualite int default 1
);

insert into Qualite(nomQualite) values('petit' , 100 , 20);
insert into Qualite(nomQualite) values('moyen' , 200 , 40 );
 
create table Unite(
    idUnite serial primary key not null,
    nomUnite varchar(100)
);

insert into unite (nomUnite) values ('kg');
insert into unite (nomUnite) values ('l');
insert into unite (nomUnite) values ('m');

create table MaterielTsotra(
    idMateriel serial primary key not null,
    nomMateriel varchar(100)
);

insert into MaterielTsotra(nomMateriel) values('goudron');
insert into MaterielTsotra(nomMateriel) values('gravillon');
insert into MaterielTsotra(nomMateriel) values('sable');

create table MaterielPrix(
    idMaterielPrix serial not null,
    idMateriel int not null,
    prix double precision,
    datePrixMateriel date default now(),
    idUnite int not null,
    foreign key(idMateriel) references MaterielTsotra(idMateriel),
    foreign key (idUnite) references Unite(idUnite) 
);

insert into MaterielPrix(idMateriel,prix,idUnite) values(1,100,1);
insert into MaterielPrix(idMateriel,prix,idUnite) values(2,90,2);
insert into MaterielPrix(idMateriel,prix,idUnite) values(1,120,1);

create view Materiel as (
    select MaterielTsotra.idMateriel , nomMateriel , prix , idUnite ,datePrixMateriel
    from MaterielTsotra 
    join MaterielPrix 
    on MaterielTsotra.idMateriel = MaterielPrix.idMateriel
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

INSERT INTO Personnel (nomPersonnel,expMin,expMax,augmentation) VALUES
    ('Ouvrier' ,0,3, 1),
    ('Senior' ,3,6, 2),
    ('Expert' ,6,100, 3);

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
INSERT INTO EmployeTravaillant (idQualite, idPersonnel, Salaire, Surface, Nombre) VALUES
    (1, 1, 1000, 100, 2),  
    (2, 2, 2000, 200, 3), 
    (2, 1, 1000, 300, 5);


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























create View V_MaterielType as(
    select MaterielType.idmaterieltype , Type.idtype , Type.nomType , MaterielType.qantiteparmettrecarre , Materiel.idMateriel , Materiel.nomMateriel ,
    Materiel.prix , Materiel.idUnite , Materiel.datePrixMateriel ,idQualite
    from MaterielType
    join Type
    on MaterielType.idType = Type.idType
    join Materiel
    on Materiel.idMateriel = MaterielType.idMateriel
);

create View MaterielTypeValide as (
    SELECT idType, NomType, idmateriel, Nommateriel, datePrixMateriel, qantiteparmettrecarre 
    FROM 
    (SELECT idType,NomType,idmateriel,Nommateriel,datePrixMateriel,        
        qantiteparmettrecarre,ROW_NUMBER() OVER (PARTITION BY idType, NomType, idmateriel, Nommateriel, datePrixMateriel ORDER BY idMaterielType DESC) 
        AS row_num    
        FROM V_MaterielType
    ) 
    AS subquery WHERE row_num = 1
);

create view V_RouteQualiteMateriel as(
    select MaterielType.idmaterieltype , Type.idtype , Type.nomType , MaterielType.qantiteparmettrecarre , Materiel.idMateriel , Materiel.nomMateriel ,
        Materiel.prix , Materiel.idUnite , Materiel.datePrixMateriel ,RouteQualite.idQualite ,qualite.nomQualite , route.idRoute , route.nomRoute , qualite.longueur,qualite.largeur
        from MaterielType
        join Type
        on MaterielType.idType = Type.idType
        join Materiel
        on Materiel.idMateriel = MaterielType.idMateriel
        join Route on route.idType = Type.idType
        join RouteQualite
        on MaterielType.idQualite = RouteQualite.idQualite
        join Qualite
        on Qualite.idQualite = MaterielType.idQualite
);

-- 1m² -> 120
-- long * largeur -> ?

create view PrixEntreDeux as (
    select idroute ,nomroute, sum(prix * longueur*largeur*qantiteparmettrecarre) as prix ,idQualite,nomQualite 
    from V_RouteQualiteMateriel 
    group by nomroute, idroute,idQualite,nomQualite
);

create view PrixMateriauxParRouteQualite as(
    select  RouteQualite.idroute,nomroute,prix,RouteQualite.idqualite,nomqualite,idroutequalite,identifiant,nomroutequalite
    from PrixEntreDeux 
    join RouteQualite
    on RouteQualite.idQualite = PrixEntreDeux.idQualite
    WHERE RouteQualite.idRoute = PrixEntreDeux.idRoute
);

create view ResteStock as(
    select idMateriel , sum(quantite) as quantite 
    from stock 
    group by idMateriel
);

create view V_ResteStock as(
    select Materiel.idMateriel,quantite,nomMateriel
    from ResteStock 
    join Materiel
    on Materiel.idMateriel = ResteStock.idMateriel
);

-- Quantite pour chauque routequalite/materiel
create view  QuantiteNecessairePourChaqueRoute as(
    select Materiel.idMateriel , Materiel.nomMateriel , RouteQualite.idRouteQualite , route.nomRoute , sum(qantiteparmettrecarre *longueur*largeur) as QuantiteNecessaire  
        from MaterielType
        join Type
        on MaterielType.idType = Type.idType
        join Materiel
        on Materiel.idMateriel = MaterielType.idMateriel
        join Route on route.idType = Type.idType
        join RouteQualite
        on MaterielType.idQualite = RouteQualite.idQualite
        join Qualite
        on Qualite.idQualite = MaterielType.idQualite
        group by  Materiel.idMateriel , Materiel.nomMateriel , RouteQualite.idRouteQualite , route.nomRoute
);

-- Prix materiel / routeQualite / materiel
create view  PrixParRouteParMateriel as(
    select Materiel.idMateriel , Materiel.nomMateriel , RouteQualite.idRouteQualite , route.nomRoute , sum(qantiteparmettrecarre *longueur*largeur*prix) as Prix  
        from MaterielType
        join Type
        on MaterielType.idType = Type.idType
        join Materiel
        on Materiel.idMateriel = MaterielType.idMateriel
        join Route on route.idType = Type.idType
        join RouteQualite
        on MaterielType.idQualite = RouteQualite.idQualite
        join Qualite
        on Qualite.idQualite = MaterielType.idQualite
        group by  Materiel.idMateriel , Materiel.nomMateriel , RouteQualite.idRouteQualite , route.nomRoute
);

-- Prix materiel pour chaque routeQualite
create view PrixMaterielParRouteQualite as(
    select idRouteQualite , sum(prix) as Prix
    from PrixParRouteParMateriel
    group by  idRouteQualite
);


create view EmployeTravaillantRoute as(
    select  idroutequalite,idroute,identifiant,nomroutequalite,idemployetravaillant,
    routeQualite.idqualite,idpersonnel,salaire,surface,nombre,longueur,largeur
    from routeQualite
    join employeTravaillant
    on employeTravaillant.idqualite = routeQualite.idqualite
    join qualite
    on qualite.idQualite = routeQualite.idqualite
);

-- 1h -> surface*nombre
-- ? -> longueur*largeur

create view SalaireParPersonnel as(
    select idroutequalite,idqualite,idpersonnel,salaire, sum(longueur*largeur)/sum(surface*nombre) as heureNecessaire , salaire*(sum(longueur*largeur)/sum(surface*nombre)) as SalaireParPersonnel
    from EmployeTravaillantRoute
    group by idroutequalite,idqualite,idpersonnel,salaire
);

create view SalaireParRouteQualite as (
    select idroutequalite,idqualite,sum(SalaireParPersonnel) as SalaireToutPersonnel
    from SalaireParPersonnel
    group by idroutequalite,idqualite
);

create view Anciennete as(
    select idPersonne ,nomPersonne, idPersonnel , dateNaissance ,dateEmbauche ,salaireBase , EXTRACT(YEAR FROM AGE(now(), DateEmbauche))::DOUBLE PRECISION AS Anciennete
    from Personne
);

create view V_PostePersonne as(
    select idpostepersonne,anciennete.idpersonne, nomPersonne ,postePersonne.idpersonnel,personnel.nomPersonnel,postePersonne.tauxhoraire,datenaissance,dateembauche,salairebase,anciennete 
    from postePersonne
    join anciennete
    on anciennete.idPersonne = postePersonne.idPersonne
    join personnel 
    on personnel.idPersonnel = postePersonne.idPersonnel
);


create table TypeClient (
    idTypeClient serial not null primary key,
    nomTypeClient varchar(100)
);

create table client (
    idClient serial not null primary key,
    nomClient varchar(100),
    idTypeClient int not null,
    foreign key(idTypeClient) references TypeClient(idTypeClient)
);

create table vente(
    idVente serial not null primary key,
    idRouteQualite int not null,
    idClient int not null,
    dateVente date default now(),
    prix double precision,
    foreign key(idRouteQualite) references routeQualite(idRouteQualite),
    foreign key(idClient) references client(idClient)
);
 
create view V_Vente as(
    select vente.idVente , routeQualite.idRouteQualite , routeQualite.NomRouteQualite , client.idClient , nomClient
    ,TypeClient.idTypeClient , nomTypeClient ,dateVente , prix
    from vente
    join client
    on client.idClient = vente.idClient
    join TypeClient
    on client.idTypeClient = vente.idClient
    join routeQualite
    on routeQualite.idRouteQualite = vente.idRouteQualite 
);

create view V_RouteFabrique as (
    select  idroutequalite, sum(nombre) as nombre
    from routeFabrique
    group by idRouteQualite
);

create view JoinRouteFabrique as(
    select RouteFabrique.idroutequalite, NomRouteQualite ,nombre
    from routeFabrique
    join routeQualite
    on routeFabrique.idRouteQualite = routeQualite.idRouteQualite
);

create view V_JoinRouteFabrique as(
    select RouteFabrique.idroutequalite, NomRouteQualite ,sum(nombre) as nombre
    from routeFabrique
    join routeQualite
    on routeFabrique.idRouteQualite = routeQualite.idRouteQualite
    group by RouteFabrique.idroutequalite,NomRouteQualite
);


