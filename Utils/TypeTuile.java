package Utils;

public enum TypeTuile{
    PORTE_FER("Porte de fer", null),
    PORTE_CUIVRE("Porte de cuivre", null),
    PORTE_BRONZE("Porte de bronze", null),
    PORTE_ARGENT("Porte d'argent", null),
    PORTE_OR("Porte d'or", null),
    HELIPORT("Heliport", null),
    
    PALAIS_CORAIL("Palais de corail", Tresor.CALICE),
    PALAIS_MAREES("Palais des marées", Tresor.CALICE),
    TEMPLE_SOLEIL("Temple du soleil", Tresor.PIERRE),
    TEMPLE_LUNE("Temple de la lune", Tresor.PIERRE),
    JARDIN_HURLEMENTS("Jardin des hurlements", Tresor.ZEPHYR),
    JARDIN_MURMURES("Jardin des murmures", Tresor.ZEPHYR),
    CAVERNE_OMBRES("Caverne des ombres", Tresor.CRISTAL),
    CAVERNE_BRASIER("Caverne du brasier", Tresor.CRISTAL),
    
    VAL_CREPUSCULE("Val du crépuscule", null),
    MARAIS_BRUMEUX("Marais brumeux ", null),
    TOUR_GUET("Tour de guet", null),
    PONT_ABIMES("Pont des abîmes", null),
    DUNES_ILLUSION("Dunes de l'illusion", null),
    FALAISES_OUBLI("Les falaises de l'oubli", null),
    ROCHER_FANTOME("Le rocher fantôme", null),
    FORET_POURPRE("La forêt pourpre", null),
    LAGON_PERDU("Le lagon perdu", null),
    OBSERVATOIRE("L'observatoire", null);
    
    private String nomTuile;
    private Tresor tresor;
    private TypeTuile(String nom, Tresor t){
        nomTuile = nom;
        tresor = t;
    }
    public String toString(){
        return nomTuile;
    }
    public Tresor getTresor(){
        return tresor;
    }
}