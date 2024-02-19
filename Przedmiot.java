public class Przedmiot{
    protected int id;
    protected String autor;
    protected String tytul;
    protected String gatunek;
    protected int cena;
    protected int ilosc;
    protected CData dataWydania;

    public Przedmiot() throws MyExceptions{
        UstawPrzedmiot(0, "Brak", "Brak", "Brak", "1-1-1970", 0, 0);
    }

    public Przedmiot(int id, String autor, String tytul, String gatunek, 
	            String dataWydania, int cena, int ilosc) throws MyExceptions{
        UstawPrzedmiot(id, autor, tytul, gatunek, dataWydania, cena, ilosc);
    }

    public void UstawPrzedmiot(int id, String autor, String tytul, String gatunek, 
	                        String dataWydania, int cena, int ilosc) throws MyExceptions{
		if (cena < 0 || ilosc < 0)
			throw new PrzedmiotException();

		this.id = id;
		this.autor = autor;
		this.tytul = tytul;
		this.gatunek = gatunek;
		this.cena = cena;
		this.ilosc = ilosc;
		this.dataWydania = new CData(dataWydania);
	}

    public int GetId(){ return this.id; }

    public String GetAutor(){ return this.autor; }
    
    public String GetTytul(){ return this.tytul; }

    public String GetGatunek(){ return this.gatunek; }
    
    public int GetCena() { return this.cena; }

    public int GetIlosc() { return this.ilosc; }

    public CData GetDataWydania(){ return this.dataWydania; }


	
    public void SetID(int id) throws PrzedmiotException{ 
        if (id < 1)
            throw new PrzedmiotException();
        this.id = id; 
    }

    public void SetAutor(String autor){ this.autor = autor; }

    public void SetTytul(String tytul){ this.tytul = tytul; }

    public void SetGatunek(String gatunek){ this.gatunek = gatunek; }

    public void SetCena(int cena) throws PrzedmiotException{
        if (cena < 0)
            throw new PrzedmiotException();
        this.cena = cena; 
    }

    public void SetIlosc(int ilosc) throws PrzedmiotException{ 
        if (ilosc < 0)
            throw new PrzedmiotException();
        this.ilosc = ilosc; 
    }

    public void SetDataWydania(CData dataWydania){ this.dataWydania = dataWydania; }

    public String toString(){
        return this.autor + " " + this.tytul + " " + this.gatunek + " " + this.dataWydania
         + " " + this.cena + " " + this.ilosc;
    }

    public boolean equals(Object o) {
        if (!((o instanceof Przedmiot) && this.autor.equals(((Przedmiot)o).autor)))
            return false;
        if (!((o instanceof Przedmiot) && this.tytul.equals(((Przedmiot)o).tytul)))
            return false;
        if (!((o instanceof Przedmiot) && this.gatunek.equals(((Przedmiot)o).gatunek)))
            return false;
        if (!((o instanceof Przedmiot) && this.cena == ((Przedmiot)o).cena))
            return false;
        if (!((o instanceof Przedmiot) && this.ilosc == ((Przedmiot)o).ilosc))
            return false;
        if (!((o instanceof Przedmiot) && this.dataWydania.GetDni() == ((Przedmiot)o).dataWydania.GetDni()))
            return false;
        
        return true;
    }

    public int compareTo(Object o) {
        int a = ((Przedmiot)o).id;
        return (this.id-a < 0) ? -1 : ((this.id-a == 0) ? 0 : 1);
    }
}