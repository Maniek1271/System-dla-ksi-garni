public class GraPlanszowa extends Przedmiot{
    protected int ograniczenieWiekowe;
    protected int czasTrwania;
    protected String iloscGraczy;

    public GraPlanszowa() throws MyExceptions{
        UstawGrePlanszowa(0, "Brak", "Brak", "Brak", 0, "1-1-1970", 0, 0, 0, "0");
    }
    public GraPlanszowa(int id, String autor, String tytul, String gatunek, int ograniczenieWiekowe,
				          String dataWydania, int cena, int ilosc, int czasTrwania, String iloscGraczy) throws MyExceptions{
        UstawGrePlanszowa(id, autor, tytul, gatunek, ograniczenieWiekowe, dataWydania, cena, ilosc, czasTrwania, iloscGraczy);

    }
    public void UstawGrePlanszowa(int id, String autor, String tytul, String gatunek, int ograniczenieWiekowe,
				          String dataWydania, int cena, int ilosc, int czasTrwania, String iloscGraczy) throws MyExceptions{
        
        if (ograniczenieWiekowe < 0 || ograniczenieWiekowe > 99)
            throw new GraPlanszowaException();
        if (czasTrwania < 0)
            throw new GraPlanszowaException();

        

        this.id = id;
		this.autor = autor;
		this.tytul = tytul;
		this.gatunek = gatunek;
		this.cena = cena;
		this.ilosc = ilosc;
		this.dataWydania = new CData(dataWydania);
        this.ograniczenieWiekowe = ograniczenieWiekowe;
        this.czasTrwania = czasTrwania;
        SetIloscGraczy(iloscGraczy);
    }

    public int GetOgraniczeniaWiekowe(){ return this.ograniczenieWiekowe; }

    public int GetCzasTrwania(){ return this.czasTrwania; }

    public String GetIloscGraczy() {return this.iloscGraczy; }

    public void SetOgraniczenieWiekowe(int ograniczenieWiekowe) throws GraPlanszowaException{
        if (ograniczenieWiekowe < 0 || ograniczenieWiekowe > 18){
            throw new GraPlanszowaException();
        }
        this.ograniczenieWiekowe = ograniczenieWiekowe;
    }

    public void SetCzasTrwania(int czasTrwania) throws GraPlanszowaException{
        if (czasTrwania < 0){
            throw new GraPlanszowaException();
        }
        this.czasTrwania = czasTrwania;
    }

    public void SetIloscGraczy(String iloscGraczy) throws GraPlanszowaException{
        String[] str = iloscGraczy.split("-");
        if (str.length != 2)
            throw new GraPlanszowaException();
        
        int minWiek, maxWiek = 0;
        try{
            minWiek = Integer.parseInt(str[0]);
            maxWiek = Integer.parseInt(str[1]);
            if (minWiek < 0 || maxWiek > 99)
                throw new GraPlanszowaException();
        } catch(NumberFormatException nfe){
            throw new GraPlanszowaException();
        }
        
        this.iloscGraczy = iloscGraczy;
    }

    public String toString(){
        return this.id + " " + this.autor + " " + this.tytul + " " + this.gatunek + " " + this.ograniczenieWiekowe 
			  + " " + this.czasTrwania + " " + this.iloscGraczy + " " + this.dataWydania + " " + this.cena + " " + this.ilosc;
    }

    public boolean equals(Object o) {
        if (!((o instanceof GraPlanszowa) && this.autor.equals(((GraPlanszowa)o).autor)))
            return false;
        if (!((o instanceof GraPlanszowa) && this.tytul.equals(((GraPlanszowa)o).tytul)))
            return false;
        if (!((o instanceof GraPlanszowa) && this.gatunek.equals(((GraPlanszowa)o).gatunek)))
            return false;
        if (!((o instanceof GraPlanszowa) && this.cena == ((GraPlanszowa)o).cena))
            return false;
        if (!((o instanceof GraPlanszowa) && this.ilosc == ((GraPlanszowa)o).ilosc))
            return false;
        if (!((o instanceof GraPlanszowa) && this.dataWydania.GetDni() == ((GraPlanszowa)o).dataWydania.GetDni()))
            return false;
		if (!((o instanceof GraPlanszowa) && this.ograniczenieWiekowe == ((GraPlanszowa)o).ograniczenieWiekowe))
            return false;
		if (!((o instanceof GraPlanszowa) && this.czasTrwania == ((GraPlanszowa)o).czasTrwania))
            return false;
        if (!((o instanceof GraPlanszowa) && this.iloscGraczy.equals(((GraPlanszowa)o).iloscGraczy)))
            return false;
        
        return true;
    }
}