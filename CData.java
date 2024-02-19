public class CData 
{
    // Pola
    protected int dni;

    protected static final String[] nazwyDni = {    
        "Sobota", "Niedziela", 
        "Poniedziałek", "Wtorek", "Środa",
        "Czwartek", "Piątek"
    };
    // Metody
    public CData() throws DataException{  
        ustawDate(1,1,1970);
    }
    
    public CData(int dzien, int miesiac, int rok) throws DataException{  
        ustawDate(dzien, miesiac, rok);            
    }
    
    public CData(String s_data) throws DataException{ 
        String[] st = s_data.split("-");
        if (st.length != 3)
            throw new DataException();

            int dzien, miesiac = 1;
            int rok = 1970;

        try{
            dzien = Integer.parseInt(st[0]);
            miesiac = Integer.parseInt(st[1]);
            rok = Integer.parseInt(st[2]);
        } catch(NumberFormatException nfe){
            throw new DataException();
        }

        ustawDate(dzien, miesiac, rok);    
    }

    public int GetDni(){ return this.dni; }
	
    public int getDzien(){
        int lata = this.dni / 360;
        int pozostaleDni = this.dni % 360;
        int r = 1970 + lata;

        int miesiace = pozostaleDni / 30;
        int d = pozostaleDni % 30 + 1;
        int m = miesiace + 1;
        
        return d;
    }
    
    public int getMiesiac(){
        int lata = this.dni / 360;
        int pozostaleDni = this.dni % 360;
        int r = 1970 + lata;

        int miesiace = pozostaleDni / 30;
        int d = pozostaleDni % 30 + 1;
        int m = miesiace + 1;
        
        return m;
    }

    public int getRok(){
        int lata = this.dni / 360;
        int pozostaleDni = this.dni % 360;
        int r = 1970 + lata;

        int miesiace = pozostaleDni / 30;
        int d = pozostaleDni % 30 + 1;
        int m = miesiace + 1;
        
        return r;
    }
    
    public void setDzien(int dzien) throws DataException{
        int lata = this.dni / 360;
        int pozostaleDni = this.dni % 360;
        int r = 1970 + lata;

        int miesiace = pozostaleDni / 30;
        int d = pozostaleDni % 30 + 1;
        int m = miesiace + 1;

        //System.out.println(dzien + "::" + d + " " + m + " " + r);

        ustawDate(dzien, m, r);       
    }
    
    public void setMiesiac(int miesiac) throws DataException{  
        int lata = this.dni / 360;
        int pozostaleDni = this.dni % 360;
        int r = 1970 + lata;

        int miesiace = pozostaleDni / 30;
        int d = pozostaleDni % 30 + 1;
        int m = miesiace + 1;

        //System.out.println(miesiac + "::" + d + " " + m + " " + r);

        ustawDate(d, miesiac, r);
    }

    public void setRok(int rok) throws DataException{
        int lata = this.dni / 360;
        int pozostaleDni = this.dni % 360;
        int r = 1970 + lata;

        int miesiace = pozostaleDni / 30;
        int d = pozostaleDni % 30 + 1;
        int m = miesiace + 1;

        //System.out.println(rok + "::" + d + " " + m + " " + r);

        ustawDate(d, m, rok);
    }
    
    private void ustawDate(int dzien, int miesiac, int rok) throws DataException{  
        // 709231 dni dla 1.1.1970
        if ((dzien < 1 || dzien > 30) || (miesiac < 1 || miesiac > 12) || rok < 1970)
            throw new DataException();
        int lata = rok - 1970;
        int liczbaDniZLat = lata * 360;
        int liczbaDniZMiesiecy = (miesiac - 1)* 30 + (dzien - 1);
        this.dni = liczbaDniZLat + liczbaDniZMiesiecy;
    }
    
    public void do_przyszlosci(int dni){
        System.out.println(this.dni + dni);
    }
    public void w_przeszlosc(int dni){
        System.out.println(this.dni - dni);
    }
    public void jutro(){
        System.out.println(this.dni + 1);
    }
    public void wczoraj(){
        System.out.println(this.dni - 1);
    }
    public void zaTydzen(){
        System.out.println(this.dni + 7);
    }
    public void tydzenTemu(){
        System.out.println(this.dni - 7);
    }
    
    public boolean porownajDaty(CData x){
        int wynik;
        int iloscDni1 = this.dni + 709231;

        int r1 = iloscDni1 / 360;
        int m1 = (iloscDni1 - r1*360) / 30;
        int d1 = iloscDni1 - r1*360 - m1*30;

        int iloscDni2 = (x.getDzien() + x.getMiesiac()*30 + x.getRok()*360) + 709231;
        int r2 = iloscDni2 / 360;
        int m2 = (iloscDni2 - r2*360) / 30;
        int d2 = iloscDni2 - r2*360 - m2*30;

        wynik = iloscDni1 - iloscDni2;

        if(wynik < 0)
            return true;
        else
            return false;
    }
    public int roznicaDat(CData x){
        int wynik;
        int iloscDni1 = this.dni + 709231;
        
        int iloscDni2 = (x.getDzien() + x.getMiesiac()*30 + x.getRok()*360) + 709231;

        wynik = iloscDni1 - iloscDni2;
        wynik = Math.abs(wynik);

        return wynik;
    }
    public String toString(){
        int lata = this.dni / 360;
        int pozostaleDni = this.dni % 360;
        int r = 1970 + lata;

        int miesiace = pozostaleDni / 30;
        int d = pozostaleDni % 30 + 1;
        int m = miesiace + 1;

        return d + "-" + m + "-" + r;
    }
    
    public boolean equals(Object o){
        if (!((o instanceof CData) && this.dni == ((CData)o).dni))
            return false;
        
        return true;
    }

    public int compareTo(Object o) {
        int a = ((CData)o).dni;
        return (this.dni-a < 0) ? -1 : ((this.dni-a == 0) ? 0 : 1);
    }

    public String dzienTygodnia(){
        int dzien_tyg = (this.dni + 709231);
        dzien_tyg = dzien_tyg % 7;
        return nazwyDni[dzien_tyg];
    }
    

    // Metody statyczne
    public static boolean porownajDaty(CData x1, CData x2){
        int iloscDni1 = x1.getDzien() + x1.getMiesiac()*30 + x1.getRok()*360 + 709231;
        int iloscDni2 = x2.getDzien() + x2.getMiesiac()*30 + x2.getRok()*360 + 709231;

        int wynik = iloscDni1 - iloscDni2;
        if(wynik < 0)
            return true;
        else
            return false;
    }

    public static int roznicaDat(CData x1, CData x2){
        int iloscDni1 = x1.getDzien() + x1.getMiesiac()*30 + x1.getRok()*360 + 709231;
        int iloscDni2 = x2.getDzien() + x2.getMiesiac()*30 + x2.getRok()*360 + 709231;

        return Math.abs(iloscDni1 - iloscDni2);
    }
    
    public static int[] dataWielkanocy(int rok){
        int a = rok % 19;
        int b = (int) Math.floor(rok/100);
        int c = rok % 100;
        int d = (int) Math.floor(b/4);
        int e = b % 4;
        int f = (int) Math.floor((b+8)/25);
        int g = (int) Math.floor((b - f + 1)/3);
        int h = (19*a+b-d-g+15)%30;
        int i = (int) Math.floor(c/4);
        int k = c % 4;
        int l = (32+2*e+2*i-h-k)%7;
        int m = (int) Math.floor((a+11*h+22*l)/451);
        int p = (h+l-7*m+114)%31;
        
        int dzienWielkanocy = p+1;
        int miesiacWielkanocy = (int) Math.floor((h+l-7*m+114)/31);
        int[] wielkanoc = {dzienWielkanocy, miesiacWielkanocy, rok};

        return wielkanoc;
    }
    public static String dzienTygodnia(CData x){
        int iloscDni = x.getDzien() + x.getMiesiac()*30 + x.getRok()*360;
        
        System.out.println("Dni z iloscdni: " + iloscDni);
        int dzien_tyg = iloscDni % 7;
        return nazwyDni[dzien_tyg];
    }
}