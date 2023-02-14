import java.util.ArrayList;
import java.util.List;

class Hirsipuu
{
	private Sanalista sanalista; //Sanalista-olio
	private int arvauksetMaara; //Arvauksien määrä
	private List<String> sanat = new ArrayList<>(); //Lista tekstitiedostossa olevista sanoista
	private String arvattavaSana; //Valittu sana
	private List<Character> arvatut = new ArrayList<>(); //Lista arvatuista kirjaimista
	
	//Hirsipuu-olion konstruktori
	public Hirsipuu (final Sanalista lista, final int arvauksetMaara_par)
	{
		this.sanalista = lista;
		this.arvauksetMaara = arvauksetMaara_par;
		this.sanat = sanalista.annaSanat();
		this.arvattavaSana = sanat.get((int) (Math.random() * sanat.size()));
	}
	
	//Sanalista-olion asetusmetodi
	public void setSanalista (final Sanalista lista)
	{
		this.sanalista = lista;
	}
	
	//Arvauksien määrän asetusmetodi
	public void setArvaukset (final int arvauksetMaara_par)
	{
		this.arvauksetMaara = arvauksetMaara_par;
	}
	
	//Sanojen listan asetusmetodi
	public void setSanat()
	{
		this.sanat = sanalista.annaSanat();
	}
	
	//Arvattavan sanan valintametodi
	public void setSana()
	{
		this.arvattavaSana = sanat.get((int) (Math.random() * sanat.size()));
	}
	
	//Sanalista-olion saantimetodi
	public Sanalista getSanalista()
	{
		return this.sanalista;
	}
	
	//Arvauksien määrän saantimetodi
	public int arvauksiaOnJaljella()
	{
		return this.arvauksetMaara;
	}
	
	//Sanojen listan saantimetodi
	public List<String> getSanat()
	{
		return this.sanat;
	}
	
	//Arvattavan sanan saantimetodi
	public String sana()
	{
		return this.arvattavaSana;
	}
	
	//Tarkistaa, onko arvattu kirjain sanassa ja tarpeen mukaan vähentää arvauksien määrää
	public boolean arvaa (Character merkki)
	{
		boolean onArvattu = arvatut.contains(merkki);
		
		if (onArvattu)
		{
			System.out.println("Kirjainta on jo arvattu!");
			return false;
		}
		else
		{
			arvatut.add(merkki);
		
			if (arvattavaSana.indexOf(merkki) != -1)
			{
				return true;
			}
			else
			{
				arvauksetMaara = arvauksetMaara - 1;
				return false;
			}
		}
	}
	
	//Arvattujen kirjainten listan saantimetodi
	public List<Character> arvaukset()
	{
		return this.arvatut;
	}
	
	//Tarkistaa, onko kaikki kirjaimet arvattu
	public boolean onLoppu()
	{
		int sanaPituus = this.arvattavaSana.length();
		int oikeat = 0;
		
		for (int i=0; i < arvatut.size(); i++)
		{
			for (int j=0; j < arvattavaSana.length(); j++)
			{
				if (arvatut.get(i) == arvattavaSana.charAt(j))
				{
					oikeat++;
				}
			}
		}
		
		if (oikeat == sanaPituus)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}