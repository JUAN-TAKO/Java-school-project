package Utils;

public enum TypeTuile{
    PORTE_FER("Porte de fer", "src/Images/tuiles/LaPorteDeFer", null),
    PORTE_CUIVRE("Porte de cuivre", "src/Images/tuiles/LaPorteDeCuivre", null),
    PORTE_BRONZE("Porte de bronze", "src/Images/tuiles/LaPorteDeBronze", null),
    PORTE_ARGENT("Porte d'argent", "src/Images/tuiles/LaPortedArgent", null),
    PORTE_OR("Porte d'or", "src/Images/tuiles/LaPorteDOr", null),
    HELIPORT("Heliport", "src/Images/tuiles/Heliport", null),
    
    PALAIS_CORAIL("Palais de corail", "src/Images/tuiles/LePalaisDeCorail", Tresor.CALICE),
    PALAIS_MAREES("Palais des marées", "src/Images/tuiles/LePalaisDesMarees", Tresor.CALICE),
    TEMPLE_SOLEIL("Temple du soleil", "src/Images/tuiles/LeTempleDuSoleil", Tresor.PIERRE),
    TEMPLE_LUNE("Temple de la lune", "src/Images/tuiles/LeTempleDeLaLune", Tresor.PIERRE),
    JARDIN_HURLEMENTS("Jardin des hurlements", "src/Images/tuiles/LeJardinDesHurlements", Tresor.ZEPHYR),
    JARDIN_MURMURES("Jardin des murmures", "src/Images/tuiles/LeJardinDesMurmures", Tresor.ZEPHYR),
    CAVERNE_OMBRES("Caverne des ombres", "src/Images/tuiles/LaCaverneDesOmbres", Tresor.CRISTAL),
    CAVERNE_BRASIER("Caverne du brasier", "src/Images/tuiles/LaCaverneDuBrasier", Tresor.CRISTAL),
    
    VAL_CREPUSCULE("Val du crépuscule", "src/Images/tuiles/LeValDuCrepuscule", null),
    MARAIS_BRUMEUX("Marais brumeux ", "src/Images/tuiles/LeMaraisBrumeux", null),
    TOUR_GUET("Tour du guet", "src/Images/tuiles/LaTourDeGuet", null),
    PONT_ABIMES("Pont des abîmes", "src/Images/tuiles/LePontDesAbimes", null),
    DUNES_ILLUSION("Dunes de l'illusion", "src/Images/tuiles/LesDunesDeLIllusion", null),
    FALAISES_OUBLI("Les falaises de l'oubli", "src/Images/tuiles/LesFalaisesDeLOubli", null),
    ROCHER_FANTOME("Le rocher fantôme", "src/Images/tuiles/LeRocherFantome", null),
    FORET_POURPRE("La forêt pourpre", "src/Images/tuiles/LaForetPourpre", null),
    LAGON_PERDU("Le lagon perdu", "src/Images/tuiles/LeLagonPerdu", null),
    OBSERVATOIRE("L'observatoire", "src/Images/tuiles/Observatoire", null);
    
    private final String nomTuile;
    private final String imagePath;
    private final Tresor tresor;
    
    private TypeTuile(String nom, String imPath, Tresor t){
        nomTuile = nom;
        tresor = t;
        imagePath = imPath;
    }
    
    @Override
    public String toString(){
        return nomTuile;
    }
    
    public Tresor getTresor(){
        return tresor;
    }
    
    public String getImagePath(){
        return imagePath + ".png";
    }
    
    public String getImagePathInonde(){
        return (imagePath + "_Inonde.png");
    }
}