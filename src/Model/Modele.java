package Model;

public class Modele extends  Entity{

    private int id;
    private String determinations;
    private int puissanceFiscale;

    public Modele(){
        this(0,null,0);
    }
    public Modele(int id, String determinations, int puissanceFiscale){
        this.id = id;
        this.determinations = determinations;
        this.puissanceFiscale = puissanceFiscale;
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

    public int getPuissanceFiscale() {
        return puissanceFiscale;
    }

    public void setPuissacenFiscale(int puissanceFiscale) {
        this.puissanceFiscale = puissanceFiscale;
    }
}
