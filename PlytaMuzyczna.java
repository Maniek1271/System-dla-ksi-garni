public class PlytaMuzyczna extends Przedmiot{
    protected int czasTrwania;

    public PlytaMuzyczna() throws MyExceptions{
        UstawPlyteMuzyczna(0, "Brak", "Brak", "Brak", "1-1-1970", 0, 0, 0);
    }
    public PlytaMuzyczna(int id, String autor, String tytul, String gatunek,
				          String dataWydania, int cena, int ilosc, int czasTrwania) throws MyExceptions{
        UstawPlyteMuzyczna(id, autor, tytul, gatunek, dataWydania, cena, ilosc, czasTrwania);

    }
    public void UstawPlyteMuzyczna(int id, String autor, String tytul, String gatunek,
				          String dataWydania, int cena, int ilosc, int czasTrwania) throws MyExceptions{
        if (czasTrwania < 0)
            throw new PlytaMuzycznaException();

        this.id = id;
		this.autor = autor;
		this.tytul = tytul;
		this.gatunek = gatunek;
		this.cena = cena;
		this.ilosc = ilosc;
		this.dataWydania = new CData(dataWydania);
        this.czasTrwania = czasTrwania;
    }

    public int GetCzasTrwania(){ return this.czasTrwania; }

    public void SetCzasTrwania(int czasTrwania) throws PlytaMuzycznaException{
        if (czasTrwania < 0){
            throw new PlytaMuzycznaException();
        }
        this.czasTrwania = czasTrwania;
    }

    public String toString(){
        return this.id + " " + this.autor + " " + this.tytul + " " + this.gatunek  
			  + " " + this.czasTrwania + " " + this.dataWydania + " " + this.cena + " " + this.ilosc;
    }

    public boolean equals(Object o) {
        if (!((o instanceof PlytaMuzyczna) && this.autor.equals(((PlytaMuzyczna)o).autor)))
            return false;
        if (!((o instanceof PlytaMuzyczna) && this.tytul.equals(((PlytaMuzyczna)o).tytul)))
            return false;
        if (!((o instanceof PlytaMuzyczna) && this.gatunek.equals(((PlytaMuzyczna)o).gatunek)))
            return false;
        if (!((o instanceof PlytaMuzyczna) && this.cena == ((PlytaMuzyczna)o).cena))
            return false;
        if (!((o instanceof PlytaMuzyczna) && this.ilosc == ((PlytaMuzyczna)o).ilosc))
            return false;
        if (!((o instanceof PlytaMuzyczna) && this.dataWydania == ((PlytaMuzyczna)o).dataWydania))
            return false;
		if (!((o instanceof PlytaMuzyczna) && this.czasTrwania == ((PlytaMuzyczna)o).czasTrwania))
            return false;
        
        return true;
    }
}