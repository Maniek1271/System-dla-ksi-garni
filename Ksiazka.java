public class Ksiazka extends Przedmiot{
	protected int liczbaStron;
	
	public Ksiazka()throws MyExceptions{
		UstawKsiazke(0, "Brak", "Brak", "Brak", 0, "1-1-1970", 0, 0);
    }
	
	public Ksiazka(int id, String autor, String tytul, String gatunek, 
				   int liczbaStron, String dataWydania, int cena, int ilosc) throws MyExceptions{
		UstawKsiazke(id, autor, tytul, gatunek, liczbaStron, dataWydania, cena, ilosc);
    }

	public void UstawKsiazke(int id, String autor, String tytul, String gatunek, int liczbaStron, 
	String dataWydania, int cena, int ilosc) throws MyExceptions{
		if (liczbaStron < 0)
			throw new KsiazkaException();

		this.id = id;
		this.autor = autor;
		this.tytul = tytul;
		this.gatunek = gatunek;
		this.cena = cena;
		this.ilosc = ilosc;
		this.liczbaStron = liczbaStron;
		this.dataWydania = new CData(dataWydania);
	}

	public int GetLiczbaStron(){ return this.liczbaStron; }

	public void SetLiczbaStron(int liczbaStron) throws KsiazkaException{
		if (liczbaStron < 1)
			throw new KsiazkaException();
		
		this.liczbaStron = liczbaStron;	
	}

	public String toString(){
        return this.id + " " + this.autor + " " + this.tytul + " " + this.gatunek + " " + this.liczbaStron 
			   + " " + this.dataWydania + " " + this.cena + " " + this.ilosc;
    }

	public boolean equals(Object o) {
        if (!((o instanceof Ksiazka) && this.autor.equals(((Ksiazka)o).autor))){
			return false;
		}
        if (!((o instanceof Ksiazka) && this.tytul.equals(((Ksiazka)o).tytul))){
			return false;
		}
        if (!((o instanceof Ksiazka) && this.gatunek.equals(((Ksiazka)o).gatunek))){
			return false;
		}
        if (!((o instanceof Ksiazka) && this.cena == ((Ksiazka)o).cena)){
			return false;
		}
        if (!((o instanceof Ksiazka) && this.ilosc == ((Ksiazka)o).ilosc)){
			return false;
		}
        if (!((o instanceof Ksiazka) && this.dataWydania.GetDni() == ((Ksiazka)o).dataWydania.GetDni())){
			return false;
		}
		if (!((o instanceof Ksiazka) && this.liczbaStron == ((Ksiazka)o).liczbaStron)){
			return false;
		}
        
        return true;
    }
}