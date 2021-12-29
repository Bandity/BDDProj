package Model;

import java.util.Date;

public class Contrat extends  Entity{
    private int id;
    private Date dateDeRetrait;
    private Date dateDeRetour;
    private float kmRetrait;
    private float kmRetour;
    private Client client;
    private Vehicule vehicule;
    private Agence agence;

    public Contrat(){
        this(0,null,null,0,0,null,null,null);
    }
    public Contrat(int id, Date dateDeRetrait, Date dateDeRetour,float kmRetrait,float kmRetour, Client client,Vehicule vehicule, Agence agence){

        this.id = id;
        this.dateDeRetrait = dateDeRetrait;
        this.dateDeRetour = dateDeRetour;
        this.kmRetrait = kmRetrait;
        this.kmRetour = kmRetour;
        this.client = client;
        this.vehicule = vehicule;
        this.agence = agence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public float getKmRetrait() {
        return kmRetrait;
    }

    public void setKmRetrait(float kmRetrait) {
        this.kmRetrait = kmRetrait;
    }

    public float getKmRetour() {
        return kmRetour;
    }

    public void setKmRetour(float kmRetour) {
        this.kmRetour = kmRetour;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Date getDateDeRetrait() {
        return dateDeRetrait;
    }

    public void setDateDeRetrait(java.sql.Date dateDeRetrait) {
        this.dateDeRetrait = dateDeRetrait;
    }

    public Date getDateDeRetour() {
        return dateDeRetour;
    }

    public void setDateDeRetour(java.sql.Date dateDeRetour) {
        this.dateDeRetour = dateDeRetour;
    }

}
