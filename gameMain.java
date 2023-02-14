import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

class gameMain
{
	private static final Scanner lukija = new Scanner (System.in);
	
	public static void main (String [] args)
	{
		String tiedosto; //Luettava tekstitiedosto
		Sanalista lista; //Sanalista-olio
		int maara; //Arvauksien määrä
		Hirsipuu peli; //Hirsipuu-olio
		String arvattavaSana; //Valittu sana
		boolean loppu = false; //Tarkistaa, onko peli suoritettu loppuun
		List<Character> arvatut; //Lista arvatuista kirjaimista
		
		System.out.print("Anna luettava sanalistatiedosto (lisaa myos tiedoston paate esim. '.txt') >");
		tiedosto = lukija.nextLine();
		
		lista = new Sanalista (tiedosto);
		
		do
		{
			System.out.print("Anna arvausten maara >");
			maara = readInt();
			
			if (maara < 1)
			{
				System.out.println("Virheellinen maara!");
			}
		}while(maara < 1);
		
		peli = new Hirsipuu (lista, maara);
		
		arvattavaSana = peli.sana();
		arvatut = peli.arvaukset();
		
		do
		{
			switch (arvattavaKirjain(arvattavaSana, arvatut, peli))
			{
				case 0:
					break;
				case 1:
					loppu = peli.onLoppu();
					break;
			}
			maara = peli.arvauksiaOnJaljella();
		}while (!loppu && maara > 0);
		
		if (maara == 0)
		{
			System.out.println("Arvaukset loppuivat! Havisit pelin!");
		}
		else
		{
			System.out.println("");
			System.out.println("Voitit pelin! Arvattava sana oli: " + arvattavaSana);
		}
		
		System.out.println("Peli paattyy...");
	}
	
	//Lukee käyttäjältä arvattavan kirjaimen ja tarkistaa löytyykö se sanasta ja/tai onko kirjainta arvattu jo aiemmin
	public static int arvattavaKirjain(String sana, List<Character> arvaukset, Hirsipuu peli)
	{
		int maara = peli.arvauksiaOnJaljella();
		System.out.println("Arvauksia jaljella: " + maara);
		System.out.print("Anna arvattava kirjain sanassa ");
		if (!tulostaSana(sana, arvaukset))
		{
			return 1;
		}
		System.out.print(" > ");
		char merkki = lukija.nextLine().charAt(0);
		boolean arvaus = peli.arvaa(merkki);
		arvaukset = peli.arvaukset();
		
		if(!arvaus)
		{
			boolean arvattu = arvaukset.contains(merkki);
			
			if (arvattu)
			{
				return 0;
			}
			else
			{
				System.out.println("Arvattu kirjain ei ole sanassa!");
				return 0;
			}
		}
		else
		{
			System.out.println("Arvattu kirjain on sanassa!");
			return 0;
		}
	}
	
	//Tulostaa sanan sitä mukaa, kun oikeita kirjaimia arvataan
	public static boolean tulostaSana (String sana, List<Character> arvaukset)
	{
		boolean kirjaimiaArvaamatta = false;
		for (int i = 0; i < sana.length(); i++)
		{
			char kirjain = sana.charAt(i);
			boolean arvattu = arvaukset.contains(kirjain);
			
			if (arvattu)
			{
				System.out.print(kirjain);
			}
			else
			{
				System.out.print('*');
				kirjaimiaArvaamatta = true;
			}
		}
		return kirjaimiaArvaamatta;
	}
	
	//Kokonaislukujen lukemismetodi
	public static int readInt()
	{
		boolean ok = false;
		int luku = 0;
		
		do
		{
			try
			{
				luku = lukija.nextInt();
				lukija.nextLine();
				ok = true;
			}
			catch (InputMismatchException ime)
			{
				lukija.nextLine();
				System.out.print("Virhe, yrita uudelleen >");
			}
		}while (!ok);
		
		return luku;
	}
}