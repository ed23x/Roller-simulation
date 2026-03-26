package src;

import java.util.Scanner;

public class Spiel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean spielen = true; // spiel läuft

        while (spielen) {
            // menü anzeigen
            System.out.println("\nWas möchten Sie machen? (t tanken / f fahren / g geld bekommen / q beenden)");
            String befehl = scanner.nextLine();

            if (befehl.equals("t")) {
                // berechne maximal mögliche liter
                double maxTanken = Roller.getTankgroesse() - Roller.getTankinhalt();
                double kannKaufen = Fahrer.getGeld() / Roller.getPreisProLiter();
                System.out.println("Tanken aktuell: " + Roller.getTankinhalt() + "L / max: " + maxTanken + "L / Geld reicht für: " + kannKaufen + "L");
                double zuTanken = scanner.nextDouble(); // eingabe lesen
                scanner.nextLine();
                // eingabe prüfen
                if (zuTanken > maxTanken) {
                    System.out.println("Passt nicht in den Tank! Maximal: " + maxTanken + "L");
                } else if (zuTanken > kannKaufen) {
                    System.out.println("Nicht genug Geld! Maximal: " + kannKaufen + "L");
                } else if (zuTanken <= 0) {
                    System.out.println("Bitte eine positive Zahl eingeben.");
                } else {
                    // tanken und geld abziehen
                    Roller.setTankinhalt(Roller.getTankinhalt() + zuTanken);
                    Fahrer.setGeld(Fahrer.getGeld() - zuTanken * Roller.getPreisProLiter());
                    System.out.println("Neuer Tankstand: " + Roller.getTankinhalt() + "L | Geld: " + Fahrer.getGeld() + "€");
                }

            } else if (befehl.equals("f")) {
                // max km berechnen (1 liter = 5 km)
                double maxFahren = Roller.getTankinhalt() * 5;
                System.out.println("Fahren — aktuell: " + Roller.getKilometerstand() + "km | Max: " + maxFahren + "km");
                double zuFahren = scanner.nextDouble();
                scanner.nextLine();
                // km und tank aktualisieren
                Roller.setKilometerstand(Roller.getKilometerstand() + zuFahren);
                Roller.setTankinhalt(Roller.getTankinhalt() - zuFahren / 5);
                System.out.println("Neuer Kilometerstand: " + Roller.getKilometerstand() + "km | Tank: " + Roller.getTankinhalt() + "L");

            } else if (befehl.equals("g")) {
                // geld addieren
                Fahrer.setGeld(Fahrer.getGeld() + 100);
                System.out.println("100€ bekommen. Geld: " + Fahrer.getGeld() + "€");

            } else if (befehl.equals("q")) {
                // spiel beenden
                spielen = false;
                System.out.println("tschuuß!");

            } else {
                System.out.println("Ungültige Eingabe.");
            }
        }

        scanner.close(); // scanner schliessen
    }
}
