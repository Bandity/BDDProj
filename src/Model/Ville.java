package Model;

public class Ville extends  Entity{
    private int id;
    private String nom;
    private int nombreHabitants;

    public Ville(){
        this(0,null, 0);
    }
    public Ville(int id, String nom, int nombreHabitants){
        this.id = id;
        this.nom =nom;
        this.nombreHabitants = nombreHabitants;
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

    public int getNombreHabitants() {
        return nombreHabitants;
    }

    public void setNombreHabitants(int nombreHabitants) {
        this.nombreHabitants = nombreHabitants;
    }
}
