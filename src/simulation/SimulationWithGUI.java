package simulation;

import java.awt.Color;

import javax.swing.JFrame;

import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.geo.GeomPortrayal;
import sim.portrayal.geo.GeomVectorFieldPortrayal;
import sim.portrayal.simple.OvalPortrayal2D;
import simulation.Simulation;
import simulation.SimulationWithGUI;

public class SimulationWithGUI extends GUIState{
	
	Display2D display;
	JFrame displayFrame;
	
	
	GeomVectorFieldPortrayal landPortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal agentPortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal ortePortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal c_punktePortrayal = new GeomVectorFieldPortrayal();
	
	public SimulationWithGUI(SimState state){
		super(state);
	}
	
	public SimulationWithGUI(){
		super(new Simulation(System.currentTimeMillis()));
	}
	
	public static String getName(){ return "GeoMason: Simulation";}
	public Object getSimulationInspectedObject(){ return state;}
	
	public void init (Controller controller){
		super.init(controller);
		
		display = new Display2D(Simulation.WIDTH, Simulation.HEIGHT, this);
		display.attach(landPortrayal, "Landschaft");
		display.attach(agentPortrayal, "Agenten");
		display.attach(ortePortrayal, "Orte");
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
		
		landPortrayal.setField(welt.see);
		System.out.println(Simulation.NUM_AGENTS);
		landPortrayal.setPortrayalForAll(new GeomPortrayal(Color.BITMASK,true)); 
		
		
		agentPortrayal.setField(welt.agents);
		agentPortrayal.setPortrayalForAll(new OvalPortrayal2D(Color.RED, 4.0));
		
		ortePortrayal.setField(welt.orte);
        ortePortrayal.setPortrayalForAll(new GeomPortrayal(Color.BLUE,true));
		
        c_punktePortrayal.setField(welt.c_punkte);
        c_punktePortrayal.setPortrayalForAll(new GeomPortrayal(Color.GREEN,true));
        //c_punktePortrayal.
        
		display.reset();
		display.setBackground(Color.WHITE);
		display.repaint();
	}
	
	public static void main (String[] args){
		SimulationWithGUI SimulationGUI = new SimulationWithGUI();
		Console console = new Console(SimulationGUI);
		console.setVisible(true);
		
	}
	
	

}

