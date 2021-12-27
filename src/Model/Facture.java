package Model;

public class Facture extends  Entity{
    private int id;
    private float montant;
    private Client client;

    public Facture(){
        this(0,0,null);
    }
    public Facture(int id, float montant, Client client){

        this.id = id;
        this.montant = montant;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
