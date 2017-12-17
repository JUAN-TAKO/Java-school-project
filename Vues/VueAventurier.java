package Vues;

public class VueAventurier{
    private String nom, role;
    private boolean active;
    public VueAventurier(String n, String r){
        nom = n;
        role = r;
        active = false;
    }

    public void setActive(boolean a) {
        active = a;
    }
}