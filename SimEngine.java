package lab5;


// klasa reprezentujaca silnik symulujacy
public class SimEngine 
{
	private double m;  // masa
	private double k;  // wspolczynnik sprezystosci
	private double c;  // wspolczynnik tlumienia
	private double lo;  // dlugosc swobodna sprezyny
	private Vector2D rm;  // polozenie masy
	private Vector2D vm;  // predskosc masy
	private Vector2D ro;  // polozenie punktu zaczepienia
	private Vector2D g;  // przyspieszenie grawitacyjne
	
	//konstruktor pozwalajacy zainicjowac wszystkie parametry
	public SimEngine(double m, double k, double c, double lo, double xm, double ym, double vxm, double vym, double xo, double yo, double gx, double gy)
	{
		this.m = m;
		this.k = k;
		this.c = c;
		this.lo = lo;
		this.rm = new Vector2D(xm, ym);
		this.vm = new Vector2D(vxm,vym);
		this.ro = new Vector2D(xo, yo);
		this.g = new Vector2D(gx, gy);
	}
	
	
	//metoda obliczajaca przebieg symulacji
	void calcSim(int interval)
	{
		
		Vector2D L = this.rm.subVectors(this.ro).calcNorm().mulVectors(lo);  // calkowita dlugosc sprezyny
		
		Vector2D stretch = this.rm.subVectors(this.ro).subVectors(L);  // wydluzenie sprezyny
		
		Vector2D Fw = stretch.mulVectors(-k).addVectors(this.vm.mulVectors(-c)).addVectors((this.g).mulVectors(this.m));  // sila wypdakowa
		
		Vector2D a = Fw.mulVectors(1/this.m);  // przyspieszenie
		
		this.vm = vm.addVectors(a.mulVectors(interval));  // nowa predkosc
		
		this.rm = rm.addVectors(vm.mulVectors(interval).addVectors(a.mulVectors(Math.pow(interval, 2)/2)));  // przemieszczenie
		
	}
	
	//metoda resetujaca symulacje
	void resetSim()
	{
		this.rm = new Vector2D(300,lo);
		this.vm = vm.mulVectors(0);
	}
	

	//gettery i settery
	public double getM() {
		return m;		
	}


	public void setM(double m) {
		this.m = m;
	}


	public double getK() {
		return k;
	}


	public void setK(double k) {
		this.k = k;
	}


	public double getC() {
		return c;
	}


	public void setC(double c) {
		this.c = c;
	}


	public double getLo() {
		return lo;
	}


	public void setLo(double lo) {
		this.lo = lo;
	}


	public Vector2D getRm() {
		return rm;
	}


	public void setRm(Vector2D rm) {
		this.rm = rm;
	}


	public Vector2D getVm() {
		return vm;
	}


	public void setVm(Vector2D vm) {
		this.vm = vm;
	}


	public Vector2D getRo() {
		return ro;
	}


	public void setRo(Vector2D ro) {
		this.ro = ro;
	}
	
	public Vector2D getG() {
		return g;
	}


	public void setG(Vector2D g) {
		this.g = g;
	}






}
