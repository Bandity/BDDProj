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
    idAgence INT,
    CONSTRAINT pkContrat PRIMARY KEY (idContrat),
    CONSTRAINT contrat_ibfk_1 FOREIGN KEY (idClient) REFERENCES Client (idClient),
    CONSTRAINT contrat_ibfk_2 FOREIGN KEY (idAgence) REFERENCES Agence (idAgence)
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
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('862-53-0578', '2020-06-13', 'semi-neuf', 221039.21, 401.55, 1, 7, 2, 1, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('696-03-2098', '2021-10-30', 'bon', 218332.06, 304.11, 5, 5, 3, 2, 6);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('391-68-8002', '2006-05-23', 'neuf', 220278.53, 738.94, 2, 2, 4, 3, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('397-73-5066', '2010-01-02', 'bon', 9950.54, 728.37, 6, 3, 3, 5, 4);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('151-55-0389', '2010-08-21', 'mauvais', 240213.31, 807.71, 7, 1, 2, 4, 6);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('468-62-2043', '2016-01-17', 'neuf', 82006.88, 445.79, 6, 2, 1, 2, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('760-24-4223', '2018-04-07', 'bon', 123361.25, 610.67, 2, 4, 3, 2, 6);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('798-38-8912', '2007-06-03', 'semi-neuf', 225865.59, 451.72, 6, 6, 4, 4, 7);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('823-03-8512', '2021-12-15', 'semi-neuf', 65546.24, 445.91, 6, 4, 2, 1, 4);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('708-67-7620', '2007-10-19', 'mauvais', 47511.37, 460.36, 7, 7, 1, 1, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('686-21-7528', '2020-04-11', 'semi-neuf', 176676.08, 564.15, 1, 4, 2, 5, 5);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('653-41-2152', '2015-06-24', 'mauvais', 243325.15, 930.65, 4, 6, 4, 3, 5);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('362-45-2859', '2019-07-24', 'neuf', 23385.18, 361.51, 6, 7, 3, 3, 5);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('132-51-0875', '2014-10-04', 'semi-neuf', 111638.21, 588.16, 7, 6, 3, 4, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('167-57-4475', '2011-04-13', 'bon', 11442.8, 699.97, 4, 4, 1, 3, 2);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('635-38-9357', '2006-05-15', 'neuf', 75422.97, 850.98, 2, 7, 3, 2, 3);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('781-58-3517', '2020-06-06', 'neuf', 44741.01, 539.55, 7, 7, 4, 4, 1);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('202-11-2445', '2021-12-05', 'semi-neuf', 14678.75, 803.33, 3, 1, 4, 5, 6);
insert into Vehicule (immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values ('477-53-3079', '2013-09-11', 'bon', 129614.25, 361.49, 4, 1, 2, 3, 1);

insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2018-05-19', '2011-02-28', 153117.51, 167659.08, 3, '132-51-0875', 7);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2015-09-16', '2019-02-23', 128408.97, 242333.82, 2, '798-38-8912', 5);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2020-08-08', '2021-01-13', 238589.57, 135779.82, 4, '653-41-2152', 4);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2014-10-24', '2008-07-10', 244031.52, 117676.82, 3, '760-24-4223', 2);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2008-10-19', '2011-11-20', 239997.44, 139015.09, 1, '862-53-0578', 3);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2007-06-24', '2008-09-04', 165189.73, 43242.73, 6, '781-58-3517', 6);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2021-01-09', '2016-06-06', 92529.69, 91355.43, 4, '686-21-7528', 3);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2015-07-16', '2009-01-22', 87322.76, 51846.79, 7, '760-24-4223', 4);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2006-04-12', '2010-08-29', 102708.45, 155832.78, 2, '653-41-2152', 5);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2021-07-03', '2016-03-29', 41423.09, 242344.63, 6, '696-03-2098', 4);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2011-09-02', '2018-06-26', 16194.34, 23829.55, 7, '397-73-5066', 7);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2006-01-29', '2017-01-19', 146022.33, 50459.09, 6, '202-11-2445', 2);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2008-04-01', '2016-09-01', 35459.93, 131416.69, 4, '477-53-3079', 3);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2007-09-19', '2008-08-28', 53424.6, 155191.0, 6, '823-03-8512', 5);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2014-04-18', '2021-05-27', 155353.94, 83385.39, 4, '167-57-4475', 2);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2008-09-28', '2019-05-09', 201143.58, 15976.35, 6, '477-53-3079', 7);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2009-10-06', '2007-07-11', 47423.83, 169088.15, 7, '798-38-8912', 4);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2019-05-19', '2015-06-23', 244448.84, 304.46, 6, '167-57-4475', 7);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2021-04-08', '2007-08-12', 68667.0, 70909.59, 6, '468-62-2043', 5);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ('2007-02-23', '2006-04-26', 176704.99, 138399.88, 6, '397-73-5066', 3);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ( null, null, 382337, 41583, null, null, null);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ( null, null, 1232337, 41583, null, null, null);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ( null, null, 7337, 41583, null, null, null);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ( null, null,82337, 41583, null, null, null);
insert into Contrat (dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgence) values ( null, null, 337, 41583, null, null, null);

insert into Facture (montant, idContrat) values (65987.72, 18);
insert into Facture (montant, idContrat) values (25908.78, 11);
insert into Facture (montant, idContrat) values (83994.6, 1);
insert into Facture (montant, idContrat) values (85579.46, 2);
insert into Facture (montant, idContrat) values (65030.8, 7);
insert into Facture (montant, idContrat) values (49952.27, 9);
insert into Facture (montant, idContrat) values (84350.18, 12);
insert into Facture (montant, idContrat) values (51800.48, 4);
insert into Facture (montant, idContrat) values (93275.14, 17);
insert into Facture (montant, idContrat) values (85161.26, 16);
insert into Facture (montant, idContrat) values (20642.72, 3);
insert into Facture (montant, idContrat) values (25310.09, 14);
insert into Facture (montant, idContrat) values (33232.11, 19);
insert into Facture (montant, idContrat) values (37551.15, 13);
insert into Facture (montant, idContrat) values (74829.19, 15);
insert into Facture (montant, idContrat) values (28688.04, 11);
insert into Facture (montant, idContrat) values (24864.27, 4);
insert into Facture (montant, idContrat) values (64360.37, 15);
insert into Facture (montant, idContrat) values (77870.98, 14);
insert into Facture (montant, idContrat) values (54388.4, 6);







