package lab5;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import javax.swing.JApplet;

public class SpringApplet extends JApplet implements  MouseListener, MouseMotionListener, ActionListener
{
	private SimEngine engine;
	private SimTask task;
	private Timer timer;
	boolean mdrag;  // zmienna przechowujaca stan myszy
	//=pola sluzace do wprowadzania danych symulacji
	private TextField typeM, typeK, typeC, typeG, typeLo;
	//przycisk ustawiajace i resetujace symulacje
	private Button restartButton;
	//etykiety opisujace pola tekstowe
	private Label infoAll, infoM, infoK, infoC, infoG, infoLo;
	
	Button test;
	
	// przeciazenie metody init
	public void init()	
	{
		//ustawienie rozmiaru appletu
		setSize(600, 600);
		
		//zainicjowanie obiektu klasy SimEngine
		double m = 10;
		double k = 2;
		double c = 0.2;
		double g = 10;
		double lo = 150;
		engine = new SimEngine(m, k, c, lo, 300, 300, 0 , 40, 300, 10, 0, g);
		
		//zainicjowanie obiektu klasy SimTask
		task = new SimTask(engine, this, 1);
		
		//zainicjowanie i ustawienie timera
		timer = new Timer();
		timer.scheduleAtFixedRate(task, 1000, 35);
		
		mdrag = false; // zresetowanie stanu myszy
		
		//dodanie Listenerow do myszy
		addMouseListener(this); 
		
		addMouseMotionListener(this);
		
		//zainicjowanie pol do wpisywania tekstu
		typeM = new TextField(m+" ");
		typeK = new TextField(k+" ");
		typeC = new TextField(c+" ");
		typeG = new TextField(g+" ");
		typeLo = new TextField(lo+" ");
		
		//zainicjowanie guzika
		restartButton = new Button  ("  restart  ");
		restartButton.addActionListener(this);

		//zainicjowanie etykiet informujacych
		infoAll = new Label("Set simulation properties:");
		infoM = new Label("M:");
		infoK = new Label("K:");
		infoC = new Label("C:");
		infoG = new Label("G:");
		infoLo = new Label("Lo:");
		
		//dodanie GUI do appletu
		add(restartButton);
		add(typeM);
		add(typeK);
		add(typeC);
		add(typeG);
		add(typeLo);
		add(infoAll);
		add(infoM);
		add(infoK);
		add(infoC);
		add(infoG);
		add(infoLo);
		
	} 
	
	//metoda odpowiadajaca za animowanie symulacji
	public void paint(Graphics g)
	{
		//"czysczenie" appletu
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 10000, 10000);
		
		//ustawienie etykiet na applecie
		infoAll.setBounds(10, 5, 150, 20);
		infoM.setBounds(10,30,20,20);
		infoK.setBounds(10, 55, 20, 20);
		infoC.setBounds(10, 80, 20, 20);
		infoLo.setBounds(10, 105, 20, 20);
		infoG.setBounds(10, 130, 20, 20);
		
		//dodanie pol tekstowych i guzika
		typeM.setBounds(30,30,30,20);
		typeK.setBounds(30, 55, 30, 20);
		typeC.setBounds(30, 80, 30, 20);
		typeLo.setBounds(30, 105, 30, 20);
		typeG.setBounds(30, 130, 30, 20);
		restartButton.setBounds(10, 160, 60, 20);
		
		//rysowanie ukladu wspolrzednych
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(50, 0, 50, 1000);
		g.drawLine(100, 0, 100, 1000);
		g.drawLine(150, 0, 150, 1000);
		g.drawLine(200, 0, 200, 1000);
		g.drawLine(250, 0, 250, 1000);
		g.drawLine(300, 0, 300, 1000);
		g.drawLine(250, 0, 250, 1000);
		g.drawLine(300, 0, 300, 1000);
		g.drawLine(350, 0, 350, 1000);
		g.drawLine(400, 0, 400, 1000);
		g.drawLine(450, 0, 450, 1000);
		g.drawLine(500, 0, 500, 1000);
		g.drawLine(550, 0, 550, 1000);
		
		g.drawLine(0, 50, 1000, 50);
		g.drawLine(0, 100, 1000, 100);
		g.drawLine(0, 150, 1000, 150);
		g.drawLine(0, 200, 1000, 200);
		g.drawLine(0, 250, 1000, 250);
		g.drawLine(0, 300, 1000, 300);
		g.drawLine(0, 350, 1000, 350);
		g.drawLine(0, 400, 1000, 400);
		g.drawLine(0, 450, 1000, 450);
		g.drawLine(0, 500, 1000, 500);
		g.drawLine(0, 550, 1000, 550);
		
		//podstawowy oscylator
		g.setColor(Color.BLACK);
		g.drawLine((int)engine.getRo().x-100, (int)engine.getRo().y, (int)engine.getRo().x+100, (int)engine.getRo().y);
		//g.drawLine((int)engine.getRo().x, (int)engine.getRo().y, (int)engine.getRm().x, (int)engine.getRm().y);
		g.setColor(Color.BLACK);
		g.fillOval((int)engine.getRm().x-10, (int)engine.getRm().y-10, 20, 20);
		
		//sprezyna
		g.drawLine((int)engine.getRo().x, (int)engine.getRo().y, (int)(engine.getRo().x+50+(0.1*(engine.getRm().x-300))),  (int)(engine.getRo().y+0.1*engine.getRm().y));
		g.drawLine((int)(engine.getRo().x+50+(0.1*(engine.getRm().x-300))), (int)(engine.getRo().y+0.1*engine.getRm().y),  (int)(engine.getRo().x-50+(0.2*(engine.getRm().x-300))), (int)(engine.getRo().y+0.2*engine.getRm().y));
		g.drawLine((int)(engine.getRo().x-50+(0.2*(engine.getRm().x-300))), (int)(engine.getRo().y+0.2*engine.getRm().y), (int)(engine.getRo().x+50+(0.3*(engine.getRm().x-300))), (int)(engine.getRo().y+0.3*engine.getRm().y));
		g.drawLine((int)(engine.getRo().x+50+(0.3*(engine.getRm().x-300))), (int)(engine.getRo().y+0.3*engine.getRm().y), (int)(engine.getRo().x-50+(0.4*(engine.getRm().x-300))), (int)(engine.getRo().y+0.4*engine.getRm().y));
		g.drawLine((int)(engine.getRo().x-50+(0.4*(engine.getRm().x-300))), (int)(engine.getRo().y+0.4*engine.getRm().y), (int)(engine.getRo().x+50+(0.5*(engine.getRm().x-300))), (int)(engine.getRo().y+0.5*engine.getRm().y));
		g.drawLine((int)(engine.getRo().x+50+(0.5*(engine.getRm().x-300))), (int)(engine.getRo().y+0.5*engine.getRm().y), (int)(engine.getRo().x-50+(0.6*(engine.getRm().x-300))), (int)(engine.getRo().y+0.6*engine.getRm().y));
		g.drawLine((int)(engine.getRo().x-50+(0.6*(engine.getRm().x-300))), (int)(engine.getRo().y+0.6*engine.getRm().y),  (int)(engine.getRo().x+50+(0.7*(engine.getRm().x-300))), (int)(engine.getRo().y+0.7*engine.getRm().y));
		g.drawLine((int)(engine.getRo().x+50+(0.7*(engine.getRm().x-300))), (int)(engine.getRo().y+0.7*engine.getRm().y), (int)(engine.getRo().x-50+(0.8*(engine.getRm().x-300))), (int)(engine.getRo().y+0.8*engine.getRm().y));
		g.drawLine((int)(engine.getRo().x-50+(0.8*(engine.getRm().x-300))), (int)(engine.getRo().y+0.8*engine.getRm().y), (int)(engine.getRo().x+50+(0.9*(engine.getRm().x-300))), (int)(engine.getRo().y+0.9*engine.getRm().y));
	    g.drawLine((int)(engine.getRo().x+50+(0.9*(engine.getRm().x-300))), (int)(engine.getRo().y+0.9*engine.getRm().y), (int)engine.getRm().x, (int)engine.getRm().y); 

		
		//utwierdzenie
		g.drawLine((int)engine.getRo().x-100, (int)engine.getRo().y, (int)engine.getRo().x-75, (int)engine.getRo().y-20);
		g.drawLine((int)engine.getRo().x-75,  (int)engine.getRo().y, (int)engine.getRo().x-50, (int)engine.getRo().y-20);
		g.drawLine((int)engine.getRo().x-50,  (int)engine.getRo().y, (int)engine.getRo().x-25, (int)engine.getRo().y-20);
		g.drawLine((int)engine.getRo().x-25,  (int)engine.getRo().y, (int)engine.getRo().x,    (int)engine.getRo().y-20);
		g.drawLine((int)engine.getRo().x,     (int)engine.getRo().y, (int)engine.getRo().x+25, (int)engine.getRo().y-20);
		g.drawLine((int)engine.getRo().x+25,  (int)engine.getRo().y, (int)engine.getRo().x+50, (int)engine.getRo().y-20);
		g.drawLine((int)engine.getRo().x+50,  (int)engine.getRo().y, (int)engine.getRo().x+75, (int)engine.getRo().y-20);
		g.drawLine((int)engine.getRo().x+75,  (int)engine.getRo().y, (int)engine.getRo().x+100,(int)engine.getRo().y-20);
		g.drawLine((int)engine.getRo().x+100, (int)engine.getRo().y, (int)engine.getRo().x+125,(int)engine.getRo().y-20);
		
		//wektory sily sprezystosci
		Vector2D L = engine.getRm().subVectors(engine.getRo()).calcNorm().mulVectors(engine.getLo());
		Vector2D stretch = engine.getRm().subVectors(engine.getRo()).subVectors(L);
		Vector2D Fk = (stretch.mulVectors(-engine.getK())).mulVectors(0.5);
		g.setColor(Color.RED);
		g.drawLine((int)engine.getRm().x, (int)engine.getRm().y, (int)Fk.x+(int)engine.getRm().x, (int)(Fk.y+engine.getRm().y));
		g.drawString("Fk", (int)Fk.x+(int)engine.getRm().x+5, (int)Fk.y+(int)engine.getRm().y+1);
		
		//wektory sily oporu
		Vector2D Fc = engine.getVm().mulVectors(-engine.getC()*10);
		g.setColor(Color.BLUE);
		g.drawLine((int)engine.getRm().x, (int)engine.getRm().y, (int)Fc.x+(int)engine.getRm().x, (int)Fc.y+(int)engine.getRm().y);
		g.drawString("Fc", (int)Fc.x+(int)engine.getRm().x+5, (int)Fc.y+(int)engine.getRm().y+1);
		
		//wektor predkosci
		Vector2D V = engine.getVm().mulVectors(10);
		g.setColor(Color.GREEN);
		g.drawLine((int)engine.getRm().x, (int)engine.getRm().y, (int)V.x+(int)engine.getRm().x, (int)V.y+(int)engine.getRm().y);
		g.drawString("Vm", (int)V.x+(int)engine.getRm().x+6, (int)V.y+(int)engine.getRm().y+1);
		
		//wektor sily grawitacji
		Vector2D G = engine.getG().mulVectors(engine.getM()/2);
		g.setColor(Color.MAGENTA);
		g.drawLine((int)engine.getRm().x, (int)engine.getRm().y, (int)engine.getRm().x, (int)G.y+(int)engine.getRm().y);
		g.drawString("G", (int)engine.getRm().x+5, (int)G.y+(int)engine.getRm().y);
	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(mdrag == true)
		{
			//odczytanie pozycji kursora myszy
			int tempY = e.getY();
			int tempX = e.getX();
			//ustawienie punktu masy w pozycji kursora
			engine.setRm(new Vector2D(tempX, tempY));
			repaint();
			e.consume();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//odczytanie polozenia myszy
		int tempX = e.getX();
		int tempY = e.getY(); 
		
		//sprawdzenie czy klik nastapil w miejscu punktu
		if((tempX > engine.getRm().x-20 && tempX < engine.getRm().x+20) && (tempY < engine.getRm().y+20 && tempY > engine.getRm().y-20))
		{
			timer.cancel();
			engine.resetSim();  // zresetowanie symulacji
			mdrag = true; 
		}
		e.consume();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//sprawdzenie stanu myszy
		if(mdrag == true)
		{
		  	task = new SimTask(engine,this, 1);
            timer = new Timer();
            timer.scheduleAtFixedRate(task, 0, 35);
			mdrag = false;
		}
		e.consume();		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == restartButton)
		{
			//zatrzymanie timera i zresetowania symulacji
			timer.cancel();
			engine.resetSim();
			
			//zczytanie nowych parametrow symulacji
			double newM = Double.parseDouble(typeM.getText());
			double newK = Double.parseDouble(typeK.getText());
			double newC = Double.parseDouble(typeC.getText());
			double newG = Double.parseDouble(typeG.getText());
			double newLo = Double.parseDouble(typeLo.getText());
			
			//ustawienie nowych parametrow symulacji
			engine.setM(newM);
			engine.setK(newK);
			engine.setC(newC);
			engine.setG(new Vector2D(0, (int)newG));
			engine.setLo(newLo);
			
			repaint();
		}
		
	}
}
