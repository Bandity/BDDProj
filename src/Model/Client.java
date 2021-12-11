package Model;

public class Client {
    private int id;
    private String nom;
    private String address;
    private int codePostal;
    private Ville ville;

    public Client(){
        this(0,null,null,0,null);
    }
    public Client(int id, String nom, String address, int codePostal,Ville ville){
        this.id = id;
        this.nom = nom;
        this.address = address;
        this.codePostal = codePostal;
        this.ville = ville;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
}
