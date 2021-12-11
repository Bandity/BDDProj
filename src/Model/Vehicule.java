package Model;

import java.util.Date;

public class Vehicule {
    private String immatriculation;
    private Date miseEnCirculation;
    private String etat;
    private float nbKilometres;
    private float prixParJourDeLocation;
    private Marque marque;
    private Modele modele;
    private Categorie categorie;
    private Types type;
    private Agence agence;

    public Vehicule(){
        this(null, null, null, 0,0,null,null,null,null,null);
    }

    public Vehicule(String immatriculation, Date miseEnCirculation, String etat, float nbKilometres,float prixParJourDeLocation,Marque marque,Modele modele,Categorie categorie,Types Type,Agence agence){

        this.immatriculation = immatriculation;
        this.miseEnCirculation = miseEnCirculation;
        this.etat = etat;
        this.nbKilometres = nbKilometres;
        this.prixParJourDeLocation = prixParJourDeLocation;
        this.marque = marque;
        this.modele = modele;
        this.categorie = categorie;
        type = Type;
        this.agence = agence;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Date getMiseEnCirculation() {
        return miseEnCirculation;
    }

    public void setMiseEnCirculation(Date miseEnCirculation) {
        this.miseEnCirculation = miseEnCirculation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public float getNbKilometres() {
        return nbKilometres;
    }

    public void setNbKilometres(float nbKilometres) {
        this.nbKilometres = nbKilometres;
    }

    public float getPrixParJourDeLocation() {
        return prixParJourDeLocation;
    }

    public void setPrixParJourDeLocation(float prixParJourDeLocation) {
        this.prixParJourDeLocation = prixParJourDeLocation;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
}
