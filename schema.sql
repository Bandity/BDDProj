DROP TABLE IF EXISTS  Facture;
DROP TABLE IF EXISTS Contrat;
DROP TABLE IF EXISTS  Vehicule;
DROP TABLE IF EXISTS  Client;
DROP TABLE IF EXISTS  Agence;
DROP TABLE IF EXISTS  Modele;
DROP TABLE IF EXISTS  Categorie;
DROP TABLE IF EXISTS  Types;
DROP TABLE IF EXISTS Marque;
DROP TABLE IF EXISTS Ville;

CREATE TABLE IF NOT EXISTS Ville(
    idVille SERIAL,
    nomVille VARCHAR,
    nombreHabitants INT,
    CONSTRAINT pkVille PRIMARY KEY (idVille)
);

CREATE TABLE IF NOT EXISTS Marque (
    idMarque SERIAL,
    nomMarque VARCHAR,
    CONSTRAINT pkMarque PRIMARY KEY (idMarque)
);

CREATE TABLE IF NOT EXISTS Types(
    idType SERIAL,
    libelleType VARCHAR,
    CONSTRAINT pkTypes PRIMARY KEY (idType)
);

CREATE TABLE IF NOT EXISTS Categorie(
    idCategorie SERIAL,
    libelleCategorie VARCHAR,
    CONSTRAINT pkCategorie PRIMARY KEY (idCategorie)
);

CREATE TABLE IF NOT EXISTS Modele(
    idModele SERIAL,
    determinations VARCHAR,
    puissanceFiscale INT,
    CONSTRAINT pkModele PRIMARY KEY (idModele)
);

CREATE TABLE IF NOT EXISTS Agence(
    idAgence SERIAL,
    nbEmployes INT,
    idVille INT,
    CONSTRAINT pkAgence PRIMARY KEY(idAgence),
    CONSTRAINT agence_ibfk_1 FOREIGN KEY (idVille) REFERENCES Ville (idVille)
);

CREATE TABLE IF NOT EXISTS Client(
    idClient SERIAL,
    nomClient VARCHAR,
    addressClient VARCHAR,
    codePostalClient INT,
    idVille INT,
    CONSTRAINT pkClient PRIMARY KEY(idClient),
    CONSTRAINT client_ibfk_1 FOREIGN KEY (idVille) REFERENCES Ville (idVille)
);

CREATE TABLE IF NOT EXISTS Vehicule (
    immatriculation VARCHAR,
    dateMiseEnCirculation DATE,
    etat VARCHAR,
    nbKilometres NUMERIC (10,2),
    prixParJourDeLocation NUMERIC(10,2),
    idMarque INT,
    idModele INT,
    idCategorie INT,
    idType INT,
    idAgence INT,
    CONSTRAINT pkVehicule PRIMARY KEY (immatriculation),
    CONSTRAINT vehicule_ibfk_1 FOREIGN KEY (idMarque) REFERENCES Marque (idMarque),
    CONSTRAINT vehicule_ibfk_2 FOREIGN KEY (idModele) REFERENCES Modele (idModele),
    CONSTRAINT vehicule_ibfk_3 FOREIGN KEY (idCategorie) REFERENCES Categorie (idCategorie),
    CONSTRAINT vehicule_ibfk_4 FOREIGN KEY (idType) REFERENCES Types (idType),
    CONSTRAINT vehicule_ibfk_5 FOREIGN KEY (idAgence) REFERENCES Agence (idAgence)
);

CREATE TABLE IF NOT EXISTS Contrat(
    idContrat SERIAL,
    dateDeRetrait DATE,
    dateDeRetour DATE,
    kmRetrait NUMERIC(10,2),
    kmRetour NUMERIC(10,2),
    idClient INT,
    immatriculation VARCHAR,
    idAgenceDeRetour INT,
    CONSTRAINT pkContrat PRIMARY KEY (idContrat),
    CONSTRAINT contrat_ibfk_1 FOREIGN KEY (idClient) REFERENCES Client (idClient),
    CONSTRAINT contrat_ibfk_2 FOREIGN KEY (idAgenceDeRetour) REFERENCES Agence (idAgence)
);

CREATE TABLE IF NOT EXISTS Facture (
    idFacture SERIAL,
    montant NUMERIC(10,2),
    idContrat INT,
    CONSTRAINT pkFacture PRIMARY KEY (idFacture),
    CONSTRAINT facture_ibfk_1 FOREIGN KEY (idContrat) REFERENCES Contrat (idContrat)
);

INSERT INTO Ville ( nomVille, nombreHabitants)VALUES ('Mulhouse',100000);
INSERT INTO Ville ( nomVille, nombreHabitants)VALUES ('Belfort',42500);
INSERT INTO Ville ( nomVille, nombreHabitants)VALUES ('Paris',500401);
INSERT INTO Ville ( nomVille, nombreHabitants)VALUES ('Toulouse',300000);
INSERT INTO Ville ( nomVille, nombreHabitants)VALUES ('Lyon',350000);
INSERT INTO Ville ( nomVille, nombreHabitants)VALUES ('Nice',350000);
INSERT INTO Ville ( nomVille, nombreHabitants)VALUES ('Marseille',40000);

INSERT INTO Marque ( nomMarque) VALUES ('Lamborghini');
INSERT INTO Marque ( nomMarque) VALUES ('Peugeot');
INSERT INTO Marque ( nomMarque) VALUES ('Tesla');
INSERT INTO Marque ( nomMarque) VALUES ('Audi');
INSERT INTO Marque ( nomMarque) VALUES ('Nissan');
INSERT INTO Marque ( nomMarque) VALUES ('BMW');
INSERT INTO Marque ( nomMarque) VALUES ('Toyota');

INSERT INTO Types( libelleType) values ('Hybrid');
INSERT INTO Types( libelleType) values ('Diesel');
INSERT INTO Types( libelleType) values ('Electric');
INSERT INTO Types( libelleType) values ('Ethanol');
INSERT INTO Types( libelleType) values ('Gasoline');


INSERT INTO Categorie ( libelleCategorie) values ('Sport');
INSERT INTO Categorie ( libelleCategorie) values ('Casual');
INSERT INTO Categorie ( libelleCategorie) values ('Coupe');
INSERT INTO Categorie ( libelleCategorie) values ('Convertible');
INSERT INTO Categorie ( libelleCategorie) values ('SUV');

INSERT INTO Modele ( determinations,puissanceFiscale) values ('Sián FKP 37 ',15);
INSERT INTO Modele ( determinations,puissanceFiscale) values ('3008 Hybrid',2);
INSERT INTO Modele ( determinations,puissanceFiscale) values ('Model X',11);
INSERT INTO Modele ( determinations,puissanceFiscale) values ('Audi R8',14);
INSERT INTO Modele ( determinations,puissanceFiscale) values ('GT-R 35',13);
INSERT INTO Modele ( determinations,puissanceFiscale) values ('M4 Competition',14);
INSERT INTO Modele ( determinations,puissanceFiscale) values ('Supra MK4',10);

INSERT INTO Agence (nbEmployes, idVille) VALUES (34,1);
INSERT INTO Agence (nbEmployes, idVille) VALUES (123,2);
INSERT INTO Agence (nbEmployes, idVille) VALUES (75,3);
INSERT INTO Agence (nbEmployes, idVille) VALUES (523,4);
INSERT INTO Agence (nbEmployes, idVille) VALUES (44,5);
INSERT INTO Agence (nbEmployes, idVille) VALUES (347,6);
INSERT INTO Agence (nbEmployes, idVille) VALUES (4,7);

insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Karly Casiroli', '65 Manley Alley', 7152, 4);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Kandace McClurg', '114 Upham Alley', 95499, 4);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Tessy Mapowder', '85516 Eastwood Drive', 14929, 4);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Andriette Cranston', '94699 American Crossing', 41956, 2);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Alyda Gilroy', '3676 South Point', 6240, 6);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Gracie Cawkill', '31004 Arrowood Avenue', 36852, 1);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Suzanne Hatfield', '34 Springs Alley', 2586, 5);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Martynne Ledgley', '61 Petterle Parkway', 32344, 7);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Marcelia O''Mahoney', '4 Vera Crossing', 43484, 4);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Vernor Beecroft', '51 Duke Lane', 43841, 5);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Leola O''Kane', '48 Lerdahl Avenue', 38213, 1);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Judith Bulley', '7852 Manitowish Hill', 11937, 6);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Donnie Tredger', '03821 Burning Wood Trail', 79975, 3);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Wynne Siveyer', '76140 Bellgrove Drive', 42614, 5);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Nissie Bengle', '08 Valley Edge Pass', 79403, 4);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Seward Kuzma', '7950 Northport Center', 8261, 4);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Jerrie Overpool', '5 Village Green Place', 13913, 2);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Lawry Curcher', '94974 Holy Cross Road', 49122, 1);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Maurice Beddow', '87 3rd Crossing', 26226, 7);
insert into Client (nomClient, addressClient, codePostalClient, idVille) values ('Kellia Presswell', '70175 Myrtle Crossing', 37890, 4);

insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('617-02-3662', '2017-02-09', 'bon', 180380.17, 482.18, 5, 7, 1, 2, 4);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('862-53-0578', '2020-06-13', 'semi-neuf', 2200.21, 401.55, 1, 7, 2, 1, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('696-03-2098', '2021-10-30', 'bon', 5183.06, 304.11, 5, 5, 3, 2, 6);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('391-68-8002', '2006-05-23', 'neuf', 3, 738.94, 2, 2, 4, 3, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('397-73-5066', '2010-01-02', 'mauvais', 199950.54, 728.37, 6, 3, 3, 5, 4);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('151-55-0389', '2010-08-21', 'mauvais', 240213.31, 807.71, 7, 1, 2, 4, 6);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('468-62-2043', '2016-01-17', 'neuf', 0, 445.79, 6, 2, 1, 2, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('760-24-4223', '2018-04-07', 'bon', 6361.25, 610.67, 2, 4, 3, 2, 6);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('798-38-8912', '2007-06-03', 'semi-neuf', 65.59, 451.72, 6, 6, 4, 4, 7);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('823-03-8512', '2021-12-15', 'semi-neuf', 46.24, 445.91, 6, 4, 2, 1, 4);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('708-67-7620', '2007-10-19', 'mauvais', 47511.37, 460.36, 7, 7, 1, 1, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('686-21-7528', '2020-04-11', 'semi-neuf', 1776.08, 564.15, 1, 4, 2, 5, 5);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('653-41-2152', '2015-06-24', 'mauvais', 243325.15, 930.65, 4, 6, 4, 3, 5);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('362-45-2859', '2019-07-24', 'neuf', 0, 361.51, 6, 7, 3, 3, 5);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('132-51-0875', '2014-10-04', 'semi-neuf', 111638.21, 588.16, 7, 6, 3, 4, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('167-57-4475', '2011-04-13', 'mauvais', 7442.8, 699.97, 4, 4, 1, 3, 2);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('635-38-9357', '2006-05-15', 'neuf', 0, 850.98, 2, 7, 3, 2, 3);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('781-58-3517', '2020-06-06', 'neuf', 0, 539.55, 7, 7, 4, 4, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('202-11-2445', '2021-12-05', 'semi-neuf', 322.75, 803.33, 3, 1, 4, 5, 6);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('477-53-3079', '2013-09-11', 'mauvais', 129614.25, 361.49, 4, 1, 2, 3, 1);

insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2018-12-19', '2019-02-28', 153117.51, 167659.08, 3, '132-51-0875', 7);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2015-09-16', '2019-02-23', 128408.97, 242333.82, 2, '798-38-8912', 5);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2020-12-08', '2021-01-13',243325.15, 263325.15, 4, '653-41-2152', 4);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2019-10-24', '2020-07-10', 6361.59, 117676.82, 3, '760-24-4223', 2);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2021-10-19', '2021-11-20', 2200.21, 3015.09, 1, '862-53-0578', 3);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2021-11-19', '2021-12-20', 3015.09, 3615.09, 1, '862-53-0578', 3);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2020-06-24', '2021-09-04', 0, 13242.73, 6, '781-58-3517', 6);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2020-05-09', '2020-06-06', 1776.08, 2455.43, 4, '686-21-7528', 3);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2021-05-09', '2021-06-06', 2455.43, 3142.43, 4, '686-21-7528', 3);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2020-07-16', '2020-09-22', 117676.82, 121846.79, 7, '760-24-4223', 4);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2021-01-14', '2021-08-29',263325.15, 268500.12, 2, '653-41-2152', 5);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2021-10-31', '2021-12-29', 5183.06, 7541.63, 6, '696-03-2098', 4);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2011-09-02', '2015-06-26', 199950.54, 238029.55, 7, '397-73-5066', 7);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2021-12-14', '2022-01-06', 568.75, 50459.09, 6, '202-11-2445', 2);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2013-12-01', '2016-09-01',  129614.25, 141416.69, 4, '477-53-3079', 3);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2007-09-19', '2008-08-28', 53424.6, 155191.0, 6, '823-03-8512', 5);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2012-04-18', '2021-05-27', 7442.8, 83385.39, 4, '167-57-4475', 2);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2016-09-28', '2019-05-09',  141416.69, 15976.35, 6, '477-53-3079', 7);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2008-10-06', '2009-07-11', 65.59, 3988.15, 7, '798-38-8912', 4);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2021-12-19', '2022-02-22', 83385.39, 9400.46, 6, '167-57-4475', 7);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2017-04-08', '2017-08-12', 0, 4523.59, 6, '468-62-2043', 5);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceDeRetour) values ('2016-02-23', '2016-04-26', 238029.55, 239399.88, 6, '397-73-5066', 3);

insert into Facture (montant, idContrat) values (6987.72, 20);
insert into Facture (montant, idContrat) values (7770.98, 2);
insert into Facture (montant, idContrat) values (5180.48, 13);
insert into Facture (montant, idContrat) values (8394.6, 18);
insert into Facture (montant, idContrat) values (8579.46, 17);
insert into Facture (montant, idContrat) values (6030.8, 16);
insert into Facture (montant, idContrat) values (8350.18, 14);
insert into Facture (montant, idContrat) values (3751.15, 7);
insert into Facture (montant, idContrat) values (2508.78, 19);
insert into Facture (montant, idContrat) values (8561.26, 11);
insert into Facture (montant, idContrat) values (2042.72, 10);
insert into Facture (montant, idContrat) values (2464.27, 4);
insert into Facture (montant, idContrat) values (2510.09, 9);
insert into Facture (montant, idContrat) values (7429.19, 6);
insert into Facture (montant, idContrat) values (3232.11, 8);
insert into Facture (montant, idContrat) values (2688.04, 5);
insert into Facture (montant, idContrat) values (6360.37, 3);
insert into Facture (montant, idContrat) values (9275.14, 12);
insert into Facture (montant, idContrat) values (5388.4, 1);
insert into Facture (montant, idContrat) values (4952.27, 15);
insert into Facture (montant, idContrat) values (4952.27, 21);
insert into Facture (montant, idContrat) values (1352.27, 22);

SELECT  a.idAgence, a.idVille, a.nbEmployes, sum(f.montant) as ChiffreAffairs FROM Contrat as c
INNER JOIN Agence a on a.idAgence = c.idAgenceDeRetour
INNER JOIN Facture f on c.idContrat = f.idContrat
WHERE EXTRACT(MONTH FROM c.datederetrait) = 05 AND a.idAgence =3
GROUP BY  a.idAgence
ORDER BY ChiffreAffairs DESC ;

SELECT DISTINCT count(*) as NombreVehicules, m.nomMarque FROM Vehicule as v
INNER JOIN Marque m on v.idMarque = m.idMarque
GROUP BY m.nomMarque
ORDER BY NombreVehicules DESC ;

SELECT c.libelleCategorie, sum(f.montant) as Chiffre_Affaires FROM Facture as f
INNER JOIN Contrat cont on cont.idContrat = f.idContrat
INNER JOIN Vehicule v on v.immatriculation = cont.immatriculation
INNER JOIN Categorie c on v.idCategorie = c.idCategorie
GROUP BY c.libelleCategorie
ORDER BY Chiffre_Affaires DESC;

SELECT t.libelleType, SUM(f.montant) as Chiffre_Affaires FROM Facture as f
INNER JOIN Contrat cont on cont.idContrat = f.idContrat
INNER JOIN Vehicule v on v.immatriculation = cont.immatriculation
INNER JOIN Types t on v.idType = t.idType
GROUP BY t.libelleType
ORDER BY Chiffre_Affaires DESC;


SELECT  a.idAgence, c.dateDeRetrait,c.dateDeRetour, SUM(f.montant) as Chiffre_Affaires FROM Facture as f
INNER JOIN contrat c on f.idContrat = c.idcontrat
INNER JOIN Agence a on a.idAgence = c.idAgenceDeRetour
WHERE c.dateDeRetrait BETWEEN '2021-01-01' and '2021-12-31'
GROUP BY  a.idAgence, c.dateDeRetrait,c.dateDeRetour
ORDER BY Chiffre_Affaires DESC ;

SELECT v.immatriculation, c.dateDeRetrait FROM Contrat as c
INNER JOIN Vehicule v on c.immatriculation = v.immatriculation
ORDER BY c.dateDeRetour DESC LIMIT 1;

SELECT a.idAgence, c.dateDeRetrait , c.dateDeRetour FROM Facture as f
INNER JOIN contrat c on f.idContrat = c.idcontrat
INNER JOIN Agence a on c.idAgenceDeRetour = a.idAgence
ORDER BY c.dateDeRetrait DESC;

SELECT DISTINCT COUNT(v) as Nombre_De_Vehicules , a.idAgence FROM Agence as a
INNER JOIN Vehicule v on a.idAgence = v.idAgence
WHERE v.dateMiseEnCirculation < '2020-01-10' AND v.nbKilometres > 150000.00
GROUP BY a.idAgence
ORDER BY   Nombre_De_Vehicules DESC ;

SELECT c.nomClient, count(idContrat) as Nombre_Contrats
FROM Contrat cont
INNER JOIN Client c on c.idClient = cont.idClient
INNER JOIN Agence a on a.idAgence = cont.idAgenceDeRetour
WHERE a.idAgence = 3 AND cont.dateDeRetrait BETWEEN '2021-01-01' and '2021-12-31'
GROUP BY c.idclient
ORDER BY Nombre_Contrats desc;

SELECT  c.dateDeRetrait ,c.dateDeRetour FROM Contrat c
INNER JOIN Vehicule v on c.immatriculation = v.immatriculation
INNER JOIN Client on c.idClient = client.idclient
WHERE client.idClient =1 AND c.dateDeRetrait = '2021-11-19' AND ( c.idAgenceDeRetour != v.idAgence)
ORDER BY  c.dateDeRetrait DESC ;
