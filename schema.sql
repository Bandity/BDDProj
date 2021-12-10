CREATE DATABASE IF NOT EXISTS `postSql`
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
    puissanceFiscale VARCHAR,
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
    CONSTRAINT pkMarque PRIMARY KEY (immatriculation),
    CONSTRAINT marque_ibfk_1 FOREIGN KEY (idMarque) REFERENCES Marque (idMarque),
    CONSTRAINT marque_ibfk_2 FOREIGN KEY (idModele) REFERENCES Modele (idModele),
    CONSTRAINT marque_ibfk_3 FOREIGN KEY (idCategorie) REFERENCES Categorie (idCategorie),
    CONSTRAINT marque_ibf_4 FOREIGN KEY (idType) REFERENCES Types (idType),
    CONSTRAINT marque_ibf_5 FOREIGN KEY (idAgence) REFERENCES Agence (idAgence)
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
    CONSTRAINT pkFacture PRIMARY KEY (idFactature),
    CONSTRAINT facture_ibfk_1 FOREIGN KEY (idContrat) REFERENCES Contrat (idContrat)
);