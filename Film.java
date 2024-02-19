public class Film extends Przedmiot{
    protected int ograniczenieWiekowe;
    protected int czasTrwania;

    public Film() throws MyExceptions{
        UstawFilm(0, "Brak", "Brak", "Brak", 0, "1-1-1970", 0, 0, 0);
    }
    public Film(int id, String autor, String tytul, String gatunek, int ograniczenieWiekowe,
				          String dataWydania, int cena, int ilosc, int czasTrwania) throws MyExceptions{
        UstawFilm(id, autor, tytul, gatunek, ograniczenieWiekowe, dataWydania, cena, ilosc, czasTrwania);

    }
    public void UstawFilm(int id, String autor, String tytul, String gatunek, int ograniczenieWiekowe,
				          String dataWydania, int cena, int ilosc, int czasTrwania) throws MyExceptions{
        
        if (ograniczenieWiekowe < 0 || ograniczenieWiekowe > 18)
            throw new FilmException();
        if (czasTrwania < 0)
            throw new FilmException();

        this.id = id;
		this.autor = autor;
		this.tytul = tytul;
		this.gatunek = gatunek;
		this.cena = cena;
		this.ilosc = ilosc;
		this.dataWydania = new CData(dataWydania);
        this.ograniczenieWiekowe = ograniczenieWiekowe;
        this.czasTrwania = czasTrwania;
    }

    public int GetOgraniczeniaWiekowe(){ return this.ograniczenieWiekowe; }

    public int GetCzasTrwania(){ return this.czasTrwania; }

    public void SetOgraniczenieWiekowe(int ograniczenieWiekowe) throws FilmException{
        if (ograniczenieWiekowe < 0 || ograniczenieWiekowe > 18){
            throw new FilmException();
        }
        this.ograniczenieWiekowe = ograniczenieWiekowe;
    }

    public void SetCzasTrwania(int czasTrwania) throws FilmException{
        if (czasTrwania < 0){
            throw new FilmException();
        }
        this.czasTrwania = czasTrwania;
    }

    public String toString(){
        return this.id + " " + this.autor + " " + this.tytul + " " + this.gatunek + " " + this.ograniczenieWiekowe 
			  + " " + this.czasTrwania + " " + this.dataWydania + " " + this.cena + " " + this.ilosc;
    }

    public boolean equals(Object o) {
        if (!((o instanceof Film) && this.autor.equals(((Film)o).autor)))
            return false;
        if (!((o instanceof Film) && this.tytul.equals(((GraPlanszowa)o).tytul)))
            return false;
        if (!((o instanceof Film) && this.gatunek.equals(((GraPlanszowa)o).gatunek)))
            return false;
        if (!((o instanceof Film) && this.cena == ((Film)o).cena))
            return false;
        if (!((o instanceof Film) && this.ilosc == ((Film)o).ilosc))
            return false;
        if (!((o instanceof Film) && this.dataWydania.GetDni() == ((Film)o).dataWydania.GetDni()))
            return false;
		if (!((o instanceof Film) && this.ograniczenieWiekowe == ((Film)o).ograniczenieWiekowe))
            return false;
		if (!((o instanceof Film) && this.czasTrwania == ((Film)o).czasTrwania))
            return false;
        
        return true;
    }
}