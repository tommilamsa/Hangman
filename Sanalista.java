import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

class Sanalista
{
	private List<String> sanalista = new ArrayList<>(); //Tekstitiedostossa olevien sanojen lista
	private File tiedosto; //Luettava tekstitiedosto
	
	//Sanalista-olion konstruktori
	public Sanalista (final String tiedosto_nimi)
	{
		tiedosto = new File(tiedosto_nimi);
	}
	
	//Lukee halutun tekstitiedoston ja lisää tiedostossa olevat sanat listaan
	public List<String> annaSanat()
	{
		try
		{
			Scanner lukija = new Scanner(tiedosto);
		
			while (lukija.hasNextLine())
			{
				String sana = lukija.nextLine();
				sana.toLowerCase();
				sanalista.add(sana);
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Virhe! Tiedostoa ei loytynyt!");
		}
		
		return sanalista;
	}
}