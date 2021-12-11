package Model;

public class Agence {
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
}
