package Model;

public class Facture extends  Entity{
    private int id;
    private float montant;
    private Contrat contrat;

    public Facture(){
        this(0,0,null);
    }
    public Facture(int id, float montant, Contrat contrat){

        this.id = id;
        this.montant = montant;
        this.contrat = contrat;
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

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}
