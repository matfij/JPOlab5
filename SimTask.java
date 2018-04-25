package lab5;

import java.util.TimerTask;

public class SimTask extends TimerTask 
{	
	private SimEngine engine;
	private SpringApplet applet;
	private int interval;
	
	//konstruktor z parametrami
	public SimTask(SimEngine engine, SpringApplet applet, int interval)
	{
		this.engine = engine;
		this.applet = applet;
		this.interval = interval;
	}
	
	// przeciazona metoda run metoda run
	public void run() 
	{
		engine.calcSim(interval);  // obliczenie kolejnego kroku symulacji
		applet.repaint();  // odswierzenie powierzchni appletu
	}

}
