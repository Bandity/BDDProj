package Model;

public class Modele {

    private int id;
    private String determinations;
    private int puissaceFiscale;

    public Modele(){
        this(0,null,0);
    }
    public Modele(int id, String determinations, int puissaceFiscale){
        this.id = id;
        this.determinations = determinations;
        this.puissaceFiscale = puissaceFiscale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeterminations() {
        return determinations;
    }

    public void setDeterminations(String determinations) {
        this.determinations = determinations;
    }

    public int getPuissaceFiscale() {
        return puissaceFiscale;
    }

    public void setPuissaceFiscale(int puissaceFiscale) {
        this.puissaceFiscale = puissaceFiscale;
    }
}
