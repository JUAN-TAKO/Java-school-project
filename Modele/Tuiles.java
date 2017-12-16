package Modele;
public enum TypeTuile{
    PORTE_FER("Porte de fer"),
    PORTE_BRONZE("Porte de bronze"),
    PORTE_ARGENT("Porte d'argent"),
    PORTE_OR("Porte d'or"),
    PORTE_PLATINE("Porte de platine"),
    HELIPORT("Heliport");

    private String nomTuile;
    private TypeTuile(String nom){
        nomTuile = nom;
    }
    public String toString(){
        return nomTuile;
    }
}