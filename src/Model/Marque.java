package Model;

public class Marque extends  Entity{
    private int id;
    private String nom;

    public Marque(){
        this(0,null);
    }

    public Marque(int id, String nom){
        this.id=id;
        this.nom=nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
