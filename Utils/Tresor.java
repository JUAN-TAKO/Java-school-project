package Utils;

public enum Tresor {
    CRISTAL("src/Images/tresors/cristal"),
    PIERRE("src/Images/tresors/pierre"),
    CALICE("src/Images/tresors/calice"),
    ZEPHYR("src/Images/tresors/zephyr");
    
    private final String imagePath;
    
    private Tresor(String path){
        imagePath = path;
    }
    
    public String getTresor(){
        return imagePath + ".png";
    }
    
    public String getTresorGris(){
        return (imagePath + "_gris.png");
    }
}
