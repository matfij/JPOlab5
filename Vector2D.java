package lab5;


public class Vector2D 
{
	public double x;
	public double y;
	
	
	Vector2D()  // konstruktor domyslny
	{
		this.x = 0;
		this.y = 0;
	}
	
	Vector2D(double x, double y)  // konstruktor z parametrami
	{
		this.x = x;
		this.y = y;
	}
	
	
	Vector2D addVectors(Vector2D vec)  // metoda sumujaca wektory i zwracajaca nowy wektor
	{
		Vector2D newVector = new Vector2D(this.x + vec.x, this.y + vec.y);
		
		return newVector;
	}
	
	Vector2D subVectors(Vector2D vec)  // metoda odejmujaca wektory i zwracajaca nowy wektor
	{
		Vector2D newVector = new Vector2D(this.x - vec.x, this.y - vec.y);
		
		return newVector;
	}
	
	Vector2D mulVectors(double multiplier)  // metoda mnozaca wektor przez stala i zwracajaca nowy wektor
	{
		Vector2D newVector = new Vector2D(this.x*multiplier, this.y*multiplier);
		
		return newVector;
	}
	
	double calcLenght()  // metoda zwracajaca dlugosc wektora
	{
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}
	
	Vector2D calcNorm()  // metoda zwracajaca znormalizowany wektor
	{
		double lenght = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
		
		Vector2D newVector = new Vector2D(this.x/lenght, this.y/lenght);
		
		return newVector;
	}
	
	// metoda wyswietlajaca informacje o wektorze
	void showInfo()
	{
		System.out.println("x:" + this.x + " y:" + this.y);
	}	
}
