package Model;

public class Agence extends Entity{
    private int id;
    private int nbEmployes;
    private Ville ville;

    public Agence(){
        this(0,0,null);
    }
    public Agence(int id, int nbEmployes,Ville ville){
        this.id = id;
        this.nbEmployes = nbEmployes;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbEmployes() {
        return nbEmployes;
    }

    public void setNbEmployes(int nbEmployes) {
        this.nbEmployes = nbEmployes;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
}
