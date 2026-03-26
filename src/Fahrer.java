package src;

public class Fahrer {
  
  // start attributes
  private static double geld = 55;
  private String name = "Hephaestus";
  // end attributes
  
  // start methods
  public static double getGeld() {
    return geld;
  }

  public Fahrer(double geld, String name) {
    this.geld = geld;
    this.name = name;
  }

  public static void setGeld(double geldNeu) {
    geld = geldNeu;
  }

  // end methods
} // end of src.Fahrer
