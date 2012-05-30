package Test;

import java.awt.Color;
import java.awt.Paint;

import javax.swing.JFrame;

import sim.app.geo.colorworld.ColorWorld;
import sim.app.geo.colorworld.ColorWorldPortrayal;
import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;

import sim.portrayal.FieldPortrayal2D;
import sim.portrayal.SimplePortrayal2D;
import sim.portrayal.geo.GeomPortrayal;
import sim.portrayal.geo.GeomVectorFieldPortrayal;
import sim.portrayal.network.SimpleEdgePortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;
import sim.portrayal.simple.RectanglePortrayal2D;
import sim.portrayal.simple.ShapePortrayal2D;
import sim.portrayal.simple.ValuePortrayal2D;
import sim.util.gui.SimpleColorMap;

public class TestWithGUI extends GUIState{
	
	Display2D display;
	JFrame displayFrame;
	
	
	GeomVectorFieldPortrayal landPortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal agentPortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal ortePortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal c_punktePortrayal = new GeomVectorFieldPortrayal();
	
	public TestWithGUI(SimState state){
		super(state);
	}
	
	public TestWithGUI(){
		super(new Test(System.currentTimeMillis()));
	}
	
	public static String getName(){ return "GeoMason: Test";}
	public Object getSimulationInspectedObject(){ return state;}
	
	public void init (Controller controller){
		super.init(controller);
		
		display = new Display2D(Test.WIDTH, Test.HEIGHT, this);
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
		Test welt = (Test)state;
		
		landPortrayal.setField(welt.see);
		System.out.println(Test.NUM_AGENTS);
		landPortrayal.setPortrayalForAll(new GeomPortrayal(Color.BITMASK,true)); 
		
		
		agentPortrayal.setField(welt.agents);
		agentPortrayal.setPortrayalForAll(new OvalPortrayal2D(Color.RED, 0.05));
		
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
		TestWithGUI testGUI = new TestWithGUI();
		Console console = new Console(testGUI);
		console.setVisible(true);
		
	}
	
	

}
