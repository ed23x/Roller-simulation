package src;

public class Roller {

  // start attributes
  private static double tankgröße = 8.4;
  private static double kilometerstand = 2749;
  private static double tankinhalt = 5;
  private static double preisProLiter = 1.99;
  // end attributes
  
  public Roller(double tankgröße, double kilometerstand, double tankinhalt) {
    this.tankgröße = tankgröße;
    this.kilometerstand = kilometerstand;
    this.tankinhalt = tankinhalt;
  }

  // start methods
  public static double getTankgroesse() {
    return tankgröße;
  }

  public static double getKilometerstand() {
    return kilometerstand;
  }

  public static void setKilometerstand(double kilometerstandNeu) {
    kilometerstand = kilometerstandNeu;
  }

  public static double getTankinhalt() {
    return tankinhalt;
  }

  public static void setTankinhalt(double tankinhaltNeu) {
    tankinhalt = tankinhaltNeu;
  }

  public static void setPreisProLiter(double preisProLiter) {
    Roller.preisProLiter = preisProLiter;
  }

  public static double getPreisProLiter(){return preisProLiter;}

  // end methods
} // end of src.Roller
