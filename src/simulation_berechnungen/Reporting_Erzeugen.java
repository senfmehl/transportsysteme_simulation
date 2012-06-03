/**
 * 
 */
package simulation_berechnungen;

import simulation_daten.Array_Auftraege_Status;
import simulation_daten.Array_Reporting;

/**
 * @author crossness
 *Auslastung Schiffe (Fahrzeit, Zeit auf See, Zeit im Hafen, sm, Wartezeit)
 */
public class Reporting_Erzeugen {
	
	public static Array_Reporting<Object> report;
	
	
	public static void report_anlegen(){
		 report = new Array_Reporting<Object>() ;
		 
		 //Array_Auftraege_Status.getNumRows();
		 
		 
		 
		 
	}
	
	public static void set_gesamt_zeit(int Schiffs_id, int Vorgang){
		report.set(Schiffs_id, 2, Vorgang)	;
		report.set(Schiffs_id, 6, true)	;
		
		
		
		//report.printDebugData();
		
		
	}
	
	public static void printreport(int aufenthalt_zeit){
		
		int numRows = report.getNumRows();
        int aufenthalts_zeits;
        int zeit_aufsee;
        int fahrzeit;
        for (int i=0; i < numRows; i++) {
            
        	aufenthalts_zeits = (Integer) report.get(i,1);
        	zeit_aufsee = (Integer) get_zeit_auf_see(i);
        	
        	fahrzeit = zeit_aufsee-aufenthalts_zeits;
        	report.set(i, 1, fahrzeit);
	
        	
        }
        
       
		
		
		
		
		System.out.println("*-------------------Report---------------------*");
		
		
	
		
		printDebugData();
		System.out.println("*---------------------------------------------------------*");
		
		
		
		
		
	}
	
	public static void set_fahrzeit(int Schiffs_id, int Aufenthaltszeit){
		Aufenthaltszeit = Aufenthaltszeit + (Integer) report.get(Schiffs_id, 1);
		
		report.set(Schiffs_id, 1, Aufenthaltszeit);
		
	}
	
	
	
	
	
	public static boolean gucke_ob_alle_reports_fertig_sind() {
        int numRows = report.getNumRows();
        int z�hler = 0;
        for (int i=0; i < numRows; i++) {
            
        	if ((Boolean) report.get(i, 6)== true){
        		z�hler = z�hler +1;
        	}
        	
        	
        }
        
        if (z�hler == numRows){
        	return true;
        }else{
        	return false;
        }
	}
	
	
	public static void seemeilen(int Schiffs_id, int seemeilen){
		
		seemeilen = seemeilen + (Integer) report.get(Schiffs_id, 4);
		
		report.set(Schiffs_id, 4, seemeilen);
		//report.printDebugData();	
	}
	
	
		public static Object get_seemeilen(int Schiffs_id){
		
		
		
		return report.get(Schiffs_id, 4 );
		//report.printDebugData();	
	}
	
	
	
	public static void set_wartezeit(int Schiffs_id,int wartezeit){
		
		wartezeit = wartezeit + (Integer) report.get(Schiffs_id, 5);
		
		report.set(Schiffs_id, 5, wartezeit);
		
		
	}
	
	
	public static Object get_wartezeit(int Schiffs_id){
		
		
		
		return report.get(Schiffs_id, 5);
		
		
	}
	
	public static void set_zeit_im_hafen(int Schiffs_id,int zeit_im_hafen){
		
		zeit_im_hafen = zeit_im_hafen + (Integer) report.get(Schiffs_id, 3);
		
		report.set(Schiffs_id, 3, zeit_im_hafen);
		
		
	}
	
	public static void set_zeit_auf_see(int Schiffs_id,int zeit_auf_see){
		
		zeit_auf_see = zeit_auf_see + (Integer) report.get(Schiffs_id, 2);
		
		report.set(Schiffs_id, 2, zeit_auf_see);
		
		
	}
	
	public static Object get_zeit_auf_see(int Schiffs_id){
		
		
		
		return (Integer) report.get(Schiffs_id, 2);

	}
	
	
	public static Object get_zeit_im_hafen(int Schiffs_id){
		
		
		
		return (Integer) report.get(Schiffs_id, 3);

	}
	
	
	
	public static void add_schiffe(int Schiffs_id){
		
		int fahrzeit = 0;
		int zeit_auf_see = 0;
		int zeit_im_hafen = 0;
		int seemeilen = 0; 
		int wartezeit = 0;
		Boolean fertig = false;
		String Schiffs_id_string = "Schiffs ID " + Schiffs_id;
		report.Add(Schiffs_id_string, Schiffs_id)	; //0
		report.Add(fahrzeit, Schiffs_id);//1
		report.Add(zeit_auf_see, Schiffs_id);//2
		report.Add(zeit_im_hafen, Schiffs_id);//3
		report.Add(seemeilen, Schiffs_id);//4
		report.Add(wartezeit, Schiffs_id);//5
		report.Add(fertig, Schiffs_id);//6
		
	}
	
	public static void printDebugData() {
        int numRows = report.getNumRows();
        int numCols = report.getNumCols(0);
        
        
        
        for (int i=0; i < numRows; i++) {
            
        	System.out.println("*---------------"+report.get(i,0)+"--------------------*");
        	System.out.println("");
        	System.out.println("Fahrzeit: " + report.get(i,1)+" Minuten");
        	System.out.println("Zeit auf See: " +report.get(i,2)+" Minuten");
        	System.out.println("Zeit im Hafen: " + report.get(i,3)+" Minuten");
        	System.out.println("Seemeilen: " +	report.get(i,4));
			System.out.println("Wartezeit: " +report.get(i,5)+" Minuten");
			System.out.println("");
			System.out.println("*---------------------------------------------------------*");
        	
            
		
        }
       
    }
	
	
	
	
}
