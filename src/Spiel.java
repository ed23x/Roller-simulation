package src;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Spiel {
    public static void main(String[] args) {
        // Erstelle einen neuen Scanner, um Benutzereingaben von der Konsole zu lesen
        Scanner scanner = new Scanner(System.in);
        boolean spielen = true; // spiel läuft
        // Erstelle einen DecimalFormat, der alle Zahlen auf 2 Nachkommastellen rundet
        DecimalFormat df = new DecimalFormat("#.##");


        /*
        Solange das Spiel läuft:
            frage ab, was gemacht werden soll
            speichere die Eingabe in befehl
         */
        while (spielen) {
            // Zeige dem Benutzer das Menü mit allen verfügbaren Befehlen an
            System.out.println("\nWas möchten Sie machen? (t tanken / f fahren / g geld bekommen / q beenden), deutsche schreibweise von kommazahlen verwenden");
            // Lese die Eingabe des Benutzers als Text ein und speichere sie in befehl
            String befehl = scanner.nextLine();


            /*
            Wenn die Eingabe t ist:
                berechne, wie viel man maximal tanken kann
                    (Tankgröße minus aktueller Tankinhalt)
                berechne, für wie viel Liter das Geld reicht
                    (aktuelles Geld geteilt durch Preis pro Liter)
                gebe beide Werte aus, damit der Benutzer weiß wie viel er tanken kann
                frage, wie viel getankt werden soll und lese die Eingabe ein
             */
            if (befehl.equals("t")) {
                // Berechne die maximal tankbaren Liter: Tankgröße - aktueller Inhalt
                double maxTanken = Roller.getTankgroesse() - Roller.getTankinhalt();
                // Berechne wie viele Liter man sich leisten kann: Geld / Preis pro Liter
                double kannKaufen = Fahrer.getGeld() / Roller.getPreisProLiter();
                // Zeige dem Benutzer den aktuellen Tankstand, das Maximum und das Geldlimit an
                // Alle Werte werden mit df.format() auf 2 Nachkommastellen gerundet ausgegeben
                System.out.println("Tanken aktuell: " + df.format(Roller.getTankinhalt()) + "L \nmax: " + df.format(maxTanken) + "L \nGeld reicht für: " + df.format(kannKaufen) + "L");
                // Lese die gewünschte Literzahl als Dezimalzahl ein
                double zuTanken = scanner.nextDouble();
                // Leere den Zeilenpuffer, damit die nächste nextLine()-Eingabe funktioniert
                scanner.nextLine();

                /*
                Wenn man mehr tanken will als in den Tank passt:
                    gib eine Fehlermeldung mit dem Maximum aus
                Sonst wenn man mehr tanken will als man sich leisten kann:
                    gib eine Fehlermeldung mit dem Geldlimit aus
                Sonst wenn die eingegebene Zahl 0 oder negativ ist:
                    gib eine Fehlermeldung aus, da nur positive Werte erlaubt sind
                Sonst (die Eingabe ist gültig):
                    addiere die getankten Liter zum aktuellen Tankinhalt
                    ziehe die Kosten (Liter * Preis pro Liter) vom Geld ab
                    stelle sicher, dass Tankinhalt und Geld nicht unter 0 fallen
                    gib den neuen Tankstand und den neuen Kontostand aus
                 */
                if (zuTanken > maxTanken) {
                    // Die gewünschte Menge passt nicht in den Tank
                    System.out.println("Passt nicht in den Tank! Maximal: " + df.format(maxTanken) + "L");
                } else if (zuTanken > kannKaufen) {
                    // Der Benutzer hat nicht genug Geld für die gewünschte Menge
                    System.out.println("Nicht genug Geld! Maximal: " + df.format(kannKaufen) + "L");
                } else if (zuTanken <= 0) {
                    // Die eingegebene Menge ist ungültig, da sie nicht positiv ist
                    System.out.println("Bitte eine positive Zahl eingeben.");
                } else {
                    // Berechne den neuen Tankinhalt und stelle sicher, dass er nicht unter 0 fällt
                    double neuerTank = Math.max(0, Roller.getTankinhalt() + zuTanken);
                    // Berechne das neue Geld und stelle sicher, dass es nicht unter 0 fällt
                    double neuesGeld = Math.max(0, Fahrer.getGeld() - zuTanken * Roller.getPreisProLiter());
                    // Setze den neuen Tankinhalt
                    Roller.setTankinhalt(neuerTank);
                    // Setze das neue Geld
                    Fahrer.setGeld(neuesGeld);
                    // Zeige den aktualisierten Tankstand und Kontostand gerundet an
                    System.out.println("Neuer Tankstand: " + df.format(Roller.getTankinhalt()) + "L  \nGeld: " + df.format(Fahrer.getGeld()) + "€");
                }

            /*
            Sonst wenn die Eingabe f ist:
                berechne die maximal fahrbare Strecke
                    (aktueller Tankinhalt mal 5, da der Roller 5km pro Liter fährt)
                gib den aktuellen Kilometerstand und die maximale Strecke aus
                frage, wie viele Kilometer gefahren werden sollen und lese die Eingabe ein
                wenn die Eingabe negativ oder 0 ist, gib eine Fehlermeldung aus
                wenn die Eingabe größer als die maximale Strecke ist, gib eine Fehlermeldung aus
                addiere die gefahrenen Kilometer zum Kilometerstand
                ziehe den Verbrauch (Kilometer geteilt durch 5) vom Tankinhalt ab
                stelle sicher, dass der Tankinhalt nicht unter 0 fällt
                gib den neuen Kilometerstand und den neuen Tankinhalt aus
             */
            } else if (befehl.equals("f")) {
                // Berechne die maximal fahrbare Strecke: Tankinhalt × 5 km/L
                double maxFahren = Roller.getTankinhalt() * 5;
                // Zeige dem Benutzer den aktuellen Kilometerstand und das Maximum gerundet an
                System.out.println("Fahren — aktuell: " + df.format(Roller.getKilometerstand()) + "km \nMax: " + df.format(maxFahren) + "km");
                // Lese die gewünschte Fahrstrecke als Dezimalzahl ein
                double zuFahren = scanner.nextDouble();
                // Leere den Zeilenpuffer, damit die nächste nextLine()-Eingabe funktioniert
                scanner.nextLine();

                /*
                Wenn die eingegebene Strecke negativ oder 0 ist:
                    gib eine Fehlermeldung aus
                Sonst wenn die eingegebene Strecke größer als die maximale Strecke ist:
                    gib eine Fehlermeldung mit dem Maximum aus
                Sonst (die Eingabe ist gültig):
                    addiere die Kilometer zum Kilometerstand
                    ziehe den Verbrauch vom Tankinhalt ab
                    stelle sicher, dass der Tankinhalt nicht unter 0 fällt
                    gib den neuen Stand aus
                 */
                if (zuFahren <= 0) {
                    // Die eingegebene Strecke ist ungültig, da sie nicht positiv ist
                    System.out.println("Bitte eine positive Zahl eingeben.");
                } else if (zuFahren > maxFahren) {
                    // Die eingegebene Strecke übersteigt die mögliche Reichweite
                    System.out.println("Nicht genug Sprit! Maximal: " + df.format(maxFahren) + "km");
                } else {
                    // Addiere die gefahrenen Kilometer zum aktuellen Kilometerstand
                    Roller.setKilometerstand(Roller.getKilometerstand() + zuFahren);
                    // Berechne den neuen Tankinhalt und stelle sicher, dass er nicht unter 0 fällt
                    double neuerTank = Math.max(0, Roller.getTankinhalt() - zuFahren / 5);
                    // Setze den neuen Tankinhalt
                    Roller.setTankinhalt(neuerTank);
                    // Zeige den aktualisierten Kilometerstand und Tankinhalt gerundet an
                    System.out.println("Neuer Kilometerstand: " + df.format(Roller.getKilometerstand()) + "km \nTank: " + df.format(Roller.getTankinhalt()) + "L");
                }

            /*
            Sonst wenn die Eingabe g ist:
                addiere 100 Euro zum aktuellen Geldkonto des Fahrers
                gib das neue Guthaben aus
             */
            } else if (befehl.equals("g")) {
                // Füge dem Geldkonto des Fahrers 100 Euro hinzu
                Fahrer.setGeld(Fahrer.getGeld() + 100);
                // Zeige dem Benutzer das neue Guthaben gerundet an
                System.out.println("100€ bekommen. Geld: " + df.format(Fahrer.getGeld()) + "€");

            /*
            Sonst wenn die Eingabe q ist:
                setze spielen auf false, damit die while-Schleife endet
                gib eine Abschiedsmeldung aus
             */
            } else if (befehl.equals("q")) {
                // Beende die Spielschleife, indem spielen auf false gesetzt wird
                spielen = false;
                // Gib eine Abschiedsmeldung an den Benutzer aus
                System.out.println("tschuuß!");

            /*
            Sonst (die Eingabe passt zu keinem bekannten Befehl):
                gib eine Fehlermeldung aus
             */
            } else {
                // Die Eingabe war kein gültiger Befehl, informiere den Benutzer
                System.out.println("Ungültige Eingabe.");
            }
        }

        // Schließe den Scanner, um Ressourcen freizugeben, da er nicht mehr benötigt wird
        scanner.close();
    }
}