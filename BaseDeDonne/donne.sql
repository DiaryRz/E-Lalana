-- Type
insert into Type(nomType) values('goudron');
insert into Type(nomType) values('paver');

-- Qualite
insert into Qualite(nomQualite , longueur ,largeur) values('petit' , 100 , 20);
insert into Qualite(nomQualite , longueur ,largeur) values('moyen' , 200 , 40 );

-- Unite
insert into unite (nomUnite) values ('kg');
insert into unite (nomUnite) values ('l');
insert into unite (nomUnite) values ('m');

-- MaterielTsotra
insert into MaterielTsotra(nomMateriel) values('goudron');
insert into MaterielTsotra(nomMateriel) values('gravillon');
insert into MaterielTsotra(nomMateriel) values('sable');

-- MaterielPrix
insert into MaterielPrix(idMateriel,prix,idUnite) values(1,100,1);
insert into MaterielPrix(idMateriel,prix,idUnite) values(2,90,2);
insert into MaterielPrix(idMateriel,prix,idUnite) values(1,120,1);

-- Inserer Stock
-- Insert into Stock(idMateriel,Quantite,DateStock,PrixEnStock) values()


-- Type de personne(personnel)
INSERT INTO Personnel (nomPersonnel,expMin,expMax,augmentation) VALUES
    ('Ouvrier' ,0,3, 1),
    ('Senior' ,3,6, 2),
    ('Expert' ,6,100, 3);

-- Employer travaillant sur une route
INSERT INTO EmployeTravaillant (idQualite, idPersonnel, Salaire, Surface, Nombre) VALUES
    (1, 1, 1000, 100, 2),  
    (2, 2, 2000, 200, 3), 
    (2, 1, 1000, 300, 5);

-- Personne
INSERT into personne(nompersonne,idpersonnel,datenaissance,salairebase) values('RabeKoto',1,'2003-02-01',100);
INSERT into personne(nompersonne,idpersonnel,datenaissance,salairebase) values('Rabe',1,'2002-02-01',120);
INSERT into personne(nompersonne,idpersonnel,datenaissance,salairebase) values('Koto',1,'2000-02-01',50);