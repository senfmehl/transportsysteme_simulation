package simulation;


import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

import org.apache.log4j.PropertyConfigurator;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.geo.GeomPortrayal;
import sim.portrayal.geo.GeomVectorFieldPortrayal;
import sim.portrayal.simple.OvalPortrayal2D;
import simulation_mason_klassen.Console_Simulation;


public class SimulationWithGUI extends GUIState{
	
	
	
	
	
	
	Display2D display;
	JFrame displayFrame;
	
	
	GeomVectorFieldPortrayal landPortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal agentPortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal ortePortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal a_punktePortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal b_punktePortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal c_punktePortrayal = new GeomVectorFieldPortrayal();
	static Console_Simulation console;
	public SimulationWithGUI(SimState state){
		super(state);
	}
	
	
	
	
	
	public SimulationWithGUI(){
		super(new Simulation(System.currentTimeMillis()));
	}
	
	public static String getName(){ return "Transportsysteme Windpark Simulation";}
	public Object getSimulationInspectedObject(){ return state;}
	
	public void init (Controller controller){
		super.init(controller);
		
		display = new Display2D(Simulation.WIDTH, Simulation.HEIGHT, this);
		display.attach(landPortrayal, "Landschaft");
		display.attach(agentPortrayal, "Agenten");
		display.attach(ortePortrayal, "Orte");
		display.attach(a_punktePortrayal, "A_Punkte");
		display.attach(b_punktePortrayal, "B_Punkte");
		display.attach(c_punktePortrayal, "C_Punkte");
	
		
		displayFrame = display.createFrame();
		controller.registerFrame(displayFrame);
		displayFrame.setVisible(true);
		
	}
	
	public void quit(){
		super.quit();
		
		if (displayFrame != null) displayFrame.dispose();
		displayFrame = null;
		display = null;
	}
	
	public void start(){
		super.start();
		setupPortrayals();
		
	}
	
	private void setupPortrayals(){
		Simulation welt = (Simulation)state;
		agentPortrayal.setField(welt.agents);
		agentPortrayal.setPortrayalForAll(new OvalPortrayal2D(Color.RED, 0.5,true));
		
		landPortrayal.setField(welt.see);
		System.out.println(Simulation.NUM_AGENTS);
		landPortrayal.setPortrayalForAll(new GeomPortrayal(Color.GRAY,true)); 
		
		ortePortrayal.setField(welt.orte);
        ortePortrayal.setPortrayalForAll(new GeomPortrayal(Color.OPAQUE,true));
        
		
		
		
        
        a_punktePortrayal.setField(welt.a_punkte);
        a_punktePortrayal.setPortrayalForAll(new GeomPortrayal(Color.BLACK,true));
        
        b_punktePortrayal.setField(welt.b_punkte);
        b_punktePortrayal.setPortrayalForAll(new GeomPortrayal(Color.BLACK,true));
		
        c_punktePortrayal.setField(welt.c_punkte);
        c_punktePortrayal.setPortrayalForAll(new GeomPortrayal(Color.BLACK,true));
        //c_punktePortrayal.
        
        
     
        
        
		display.reset();
		display.setBackground(Color.WHITE);
		display.repaint();
	}
	
	public static void main (String[] args){
		PropertyConfigurator.configureAndWatch( "log4j-3.properties", 60*1000 );
		//new konsole("Konsole"); // Konsole auslesen und anzeigen (alles ab hier)
	//	new AWTConsole();
		SimulationWithGUI SimulationGUI = new SimulationWithGUI();
		
		console = new Console_Simulation(SimulationGUI);
		console.setTitle("Console_Simulation");
	//	console.setWhenShouldPauseTime(600);
		System.out.println("Steps per second " +console.getStepsPerSecond());
		
		// Gr��e Console berechnen
		Rectangle test = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
		int h�he = test.height/3;
		int weite = test.width/3;
		
		console.setSize(weite, h�he);
		//console.setLocation(0,0);
		
		console.setVisible(true);
		
	}
	
	

	

}

