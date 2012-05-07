/**
 * 
 */
package jobs;

import daten.Tabelle_Auftrag_Allgemein;
import daten.Tabelle_Auftrag_Schiffsdaten;
import gui_komponenten.Gui_Tabellen;

/**
 * @author patrick
 *
 */
public class Schiffsdaten_Auslesen {

	/**
	 */
	public static void main() {
		// TODO Auto-generated method stub

		
	String aktuelles_verzeichnis = Datei_Auswaehler.main(new String[] {"schiffe.txt", "Schiffsdaten"});		
		
	Object[][] datei_array = Datei_Importierer.main(new String[]{aktuelles_verzeichnis});		
	
	
	String[]spalten_namen = {"Heimathafen","Geschwindigkeit Revier"," Geschwindigkeit", "Marschfahrt"};
	

	Tabelle_Auftrag_Schiffsdaten table_object_Auftrag_Schiffsdaten = new Tabelle_Auftrag_Schiffsdaten();
	table_object_Auftrag_Schiffsdaten.setSpalten_namen(spalten_namen);
	table_object_Auftrag_Schiffsdaten.setDatei_array(datei_array);
	
		
		
		
		
	}

}