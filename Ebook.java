public class Ebook extends Ksiazka{
    protected float rozmiar;

    public Ebook() throws MyExceptions{
        UstawEbook(0, "Brak", "Brak", "Brak", 0, "1-1-1970", 0, 0, 0);
    }

    public Ebook(int id, String autor, String tytul, String gatunek, int liczbaStron, 
	            String dataWydania, float rozmiar, int cena, int ilosc) throws MyExceptions{
        UstawEbook(id, autor, tytul, gatunek, liczbaStron, dataWydania, rozmiar, cena, ilosc);
    }

    public void UstawEbook(int id, String autor, String tytul, String gatunek, int liczbaStron, 
	String dataWydania, float rozmiar, int cena, int ilosc) throws MyExceptions{
		if (rozmiar < 0)
			throw new EbookException();

		this.id = id;
		this.autor = autor;
		this.tytul = tytul;
		this.gatunek = gatunek;
		this.cena = cena;
		this.ilosc = ilosc;
        this.rozmiar = rozmiar;
		this.liczbaStron = liczbaStron;
		this.dataWydania = new CData(dataWydania);
	}

    public float GetRozmiar() { return this.rozmiar; }

    public void SetRozmiar(float rozmiar) throws EbookException{
        if (rozmiar < 0)
            throw new EbookException();
        this.rozmiar = rozmiar;
    }

    public String toString(){
        return this.id + " " + this.autor + " " + this.tytul + " " + this.gatunek + " " + this.liczbaStron 
			   + " " + this.dataWydania + " " + this.rozmiar + " " + this.cena + " " + this.ilosc;
    }

    public boolean equals(Object o) {
        if (!((o instanceof Ebook) && this.autor.equals(((Ebook)o).autor)))
            return false;
        if (!((o instanceof Ebook) && this.tytul.equals(((Ebook)o).autor)))
            return false;
        if (!((o instanceof Ebook) && this.gatunek.equals(((Ebook)o).autor)))
            return false;
        if (!((o instanceof Ebook) && this.cena == ((Ebook)o).cena))
            return false;
        if (!((o instanceof Ebook) && this.ilosc == ((Ebook)o).ilosc))
            return false;
        if (!((o instanceof Ebook) && this.dataWydania.GetDni() == ((Ebook)o).dataWydania.GetDni()))
            return false;
		if (!((o instanceof Ebook) && this.liczbaStron == ((Ebook)o).liczbaStron))
            return false;
		if (!((o instanceof Ebook) && this.rozmiar == ((Ebook)o).rozmiar))
            return false;
        
        return true;
    }
}