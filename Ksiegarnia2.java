import java.io.*;
import java.util.*;
public class Ksiegarnia2{
    static ArrayList<Ksiazka> ksiazkiMagazyn = new ArrayList<Ksiazka>();
    static ArrayList<Ebook> ebookiMagazyn = new ArrayList<>();
    static ArrayList<PlytaMuzyczna> plytyMuzyczneMagazyn = new ArrayList<>();
    static ArrayList<Film> filmyMagazyn = new ArrayList<>();
    static ArrayList<GraPlanszowa> gryPlanszoweMagazyn = new ArrayList<>();

    static int liczbaKsiazek = ksiazkiMagazyn.size();
    static int liczbaEbookow = ebookiMagazyn.size();
    static int liczbaPlytMuzycznych = plytyMuzyczneMagazyn.size();
    static int liczbaFilmow = filmyMagazyn.size();
    static int liczbaGierPlanszowych = gryPlanszoweMagazyn.size();

    static boolean running;

    public static void main(String[] args){
        running = true;
        DodajTowarZPlikow();

        while(running){
            MenuGlowne();
        } 

        ZapiszStanMagazynuDoPlikow();
            
    }

    

    public static void MenuGlowne(){
        System.out.println("1. Zarzadzaj ksiazkami");
        System.out.println("2. Zarzadzaj plytami muzycznymi");
        System.out.println("3. Zarzadzaj filmami");
        System.out.println("4. Zarzadzaj ebookami");
        System.out.println("5. Zarzadzaj grami planszowymi");
        System.out.println("6. Zapisz stan magazynu do plikow");
        System.out.println("7. Wyswietl stan calego magazynu");
        System.out.println("8. Wyjdz z programu");

        Scanner scanner = new Scanner(System.in);
        int wybor = PodajPoprawnaLiczbe();

        switch(wybor){
            case 1:
                WyswietlMenuZarzadzaniaKsiazkami();
                break;
            case 2:
                WyswietlMenuZarzadzaniaPlytamiMuzycznymi();
                break;
            case 3:
                WyswietlMenuZarzadzaniaFilmami();
                break;
            case 4:
                WyswietlMenuZarzadzaniaEbookami();
                break;
            case 5:
                WyswietlMenuZarzadzaniaGramiPlanszowymi();
                break;
            case 6:
                ZapiszStanMagazynuDoPlikow();
                break;
            case 7:
                WyswietlStanMagazynu();
                break;
            case 8:
                running = false;
                break;
            default:
		System.out.println("Bledna opcja");
                break;
        }
    }

    /*
        ///////////////////

        Start Metod Ksiazki

        ///////////////////
    */


    public static void WyswietlMenuZarzadzaniaKsiazkami(){
        System.out.println("1. Dodaj ksiazke");
        System.out.println("2. Wyswietl ksiazki");
        System.out.println("3. Modyfikuj ksiazke");
        System.out.println("4. Usun ksiazke");
        System.out.println("5. Zapisz ksiazki do pliku");
        System.out.println("6. Sprzedaj ksiazke");
        System.out.println("7. Wyszukaj ksiazke");
        System.out.println("Wprowadz inna liczbe aby anulowac");

        Scanner scanner = new Scanner(System.in);
        int wybor = PodajPoprawnaLiczbe();

        switch(wybor){
            case 1:
                DodajKsiazke();
                break;
            case 2:
                WyswietlKsiazki();
                break;
            case 3:
                ModyfikujKsiazke();
                break;
            case 4:
                UsunKsiazke();
                break;
            case 5:
                ZapiszKsiazkiDoPliku();
                break;
            case 6:
                SprzedajKsiazke();
                break;
            case 7:
                WyszukajKsiazke();
		break;
            default:
		System.out.println("Bledna opcja");
                return;
                
        }
    }

    public static void DodajKsiazke(){
        Scanner scanner = new Scanner(System.in);

		System.out.println("Podaj autora: ");
		String autor = scanner.nextLine();

		System.out.println("Podaj tytul: ");
		String tytul = scanner.nextLine();

		System.out.println("Podaj gatunek: ");
		String gatunek = scanner.nextLine();

		System.out.println("Podaj date wydania w formacie dd-mm-rrrr: ");
		String dataWydania = PodajPoprawnaDate(false);

        System.out.println("Podaj liczbe stron: ");
        int liczbaStron = PodajPoprawnaLiczbe();

        System.out.println("Podaj cene: ");
		int cena = PodajPoprawnaLiczbe();

        System.out.println("Podaj ilosc ksiazek w dostawie: ");
		int ilosc = PodajPoprawnaLiczbe();

        boolean czyToNowaKsiazka = true;
        try{
            liczbaKsiazek += 1;
            Ksiazka nowaKsiazka = new Ksiazka(liczbaKsiazek, autor, tytul, gatunek, liczbaStron, dataWydania, cena, ilosc);
            for (Ksiazka i : ksiazkiMagazyn){
                if (i.equals(nowaKsiazka)){
                    i.SetIlosc(i.GetIlosc() + nowaKsiazka.GetIlosc());
                    System.out.println("\nTaka ksiazka juz istnieje, zostanie zmodyfikowana ilosc ksiazek.\n");
                    czyToNowaKsiazka = false;
                }
            }
            
            if (czyToNowaKsiazka == true){
                ksiazkiMagazyn.add(nowaKsiazka);
            }
        }
        catch(KsiazkaException ke){
            System.out.println("");
            System.out.println("Blad!");
            System.out.println("Liczba stron musi byc wieksza niz 0.");
            System.out.println("");
            liczbaKsiazek -= 1;
        }
        catch(PrzedmiotException pe){
            System.out.println("");
            System.out.println("Blad!");
            System.out.println("Cena i ilosc musza byc wieksze od 0.");
            System.out.println("");
            liczbaKsiazek -= 1;
        }
        catch(MyExceptions me){
            System.out.println("");
            System.out.println("Wystapil blad.");
            System.out.println("");
            liczbaKsiazek -= 1;
        }
    }

    public static void WyswietlKsiazki(){
        System.out.println("Ksiazki w magazynie:");
        System.out.print("|Id ");
		System.out.print("|Autor                         ");
		System.out.print("|Tytul                                             ");
		System.out.print("|Gatunek        ");
		System.out.print("|Liczba stron");
		System.out.print("|Data wydania   ");
        System.out.print("|Cena   ");
        System.out.print("|Ilosc  |" + "\n");

        for (Ksiazka i : ksiazkiMagazyn){
			String kolumnaID = String.format("%1$-3s", i.GetId());
			String kolumnaAutor = String.format("%1$-30s", i.GetAutor());
			String kolumnaTytul = String.format("%1$-50s", i.GetTytul());
			String kolumnaGatunek = String.format("%1$-15s", i.GetGatunek());
			String kolumnaStrony = String.format("%1$-12s", i.GetLiczbaStron());
			String kolumnaData = String.format("%1$-15s", i.GetDataWydania());
            String kolumnaCena = String.format("%1$-7s", i.GetCena());
            String kolumnaIlosc = String.format("%1$-6s", i.GetIlosc());

			System.out.println("|" + kolumnaID + "|" +  kolumnaAutor + "|" +  kolumnaTytul + "|" +  kolumnaGatunek + "|" +
                                     kolumnaStrony + "|" + kolumnaData + "|" + kolumnaCena + "|" + kolumnaIlosc + " |");
		}
        System.out.println("");
    }

    public static void ModyfikujKsiazke(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id ksiazki ktora chcesz zmodyfikowac");
        int idDoWyszukania = PodajPoprawnaLiczbe();

        int czyZnalezionoKsiazke = 0;
        for (Ksiazka i : ksiazkiMagazyn){
            if(i.GetId() == idDoWyszukania){
                System.out.println("Znaleziono ksiazke: ");
				System.out.println(i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " +
                                   i.GetLiczbaStron() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc() + "\n");
                
                
                System.out.println("Podaj nowe dane ksiazki.");
                System.out.println("Podaj 0 aby zachowac stara dana.");
                

                System.out.println("Podaj autora: ");
                String autor = scanner.nextLine();

                System.out.println("Podaj tytul: ");
                String tytul = scanner.nextLine();

                System.out.println("Podaj gatunek: ");
                String gatunek = scanner.nextLine();

                System.out.println("Podaj date wydania w formacie dd-mm-rrrr: ");
                String dataWydania = PodajPoprawnaDate(true);

                System.out.println("Podaj liczbe stron: ");
                int liczbaStron = PodajPoprawnaLiczbe();

                System.out.println("Podaj cene: ");
                int cena = PodajPoprawnaLiczbe();

                System.out.println("Podaj ilosc ksiazek w dostawie: ");
                int ilosc = PodajPoprawnaLiczbe();

                try{
                    if (!autor.equals("0"))
                        i.SetAutor(autor);
                    if (!tytul.equals("0"))
                        i.SetTytul(tytul);
                    if (!gatunek.equals("0"))
                        i.SetGatunek(gatunek);
                    if (liczbaStron != 0)
                        i.SetLiczbaStron(liczbaStron);
                    if (!dataWydania.equals("0"))
                        i.SetDataWydania(new CData(dataWydania));	
                    if (cena != 0)
                        i.SetCena(cena);
                    if (ilosc != 0)
                        i.SetIlosc(ilosc);
                }
                catch(KsiazkaException ke){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna liczba stron.");
                    System.out.println("Liczba Stron musi byc wieksza niz 0");
                    System.out.println("");
                }
                catch(DataException de){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna data. Data musi byc w formacie dd-mm-rrrr");
                    System.out.println("Data musi byc wieksza od 1-1-1970r.");
                    System.out.println("");
                }
                catch(PrzedmiotException pe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna cena lub ilosc ksiazek.");
                    System.out.println("Cena i ilosc musza byc wieksze od 0");
                    System.out.println("");
                }
            }
            else
                czyZnalezionoKsiazke += 1;
        }
        if(czyZnalezionoKsiazke == liczbaKsiazek)
			System.out.println("Nie znaleziono ksiazki o podanym id.");	
    }

    public static void UsunKsiazke(){
        ArrayList<Ksiazka> ksiazkiPoUsunieciu = new ArrayList<Ksiazka>();
        System.out.println("Podaj id do usuniecia: ");
        int idDoUsuniecia = PodajPoprawnaLiczbe();
        int czyUsunietoKsiazke = liczbaKsiazek;

        for (Ksiazka i : ksiazkiMagazyn){
			if (i.GetId() != idDoUsuniecia){	
				ksiazkiPoUsunieciu.add(i);
				czyUsunietoKsiazke -= 1;
			}
			else if (i.GetId() == idDoUsuniecia){
				System.out.println("Znaleziono ksiazke: ");
				System.out.println(i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " +
                                   i.GetLiczbaStron() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc() + "\n");
            }
		}

        if ((czyUsunietoKsiazke != 1) && (idDoUsuniecia != 0))
			System.out.println("Nie znaleziono ksiazki o podanym id.");
        else{
            System.out.println("Usunieto ksiazke.");
            ksiazkiMagazyn.clear();
		    ksiazkiMagazyn = ksiazkiPoUsunieciu;
            liczbaKsiazek -= 1;
        }
    }

    public static void ZapiszKsiazkiDoPliku(){
		File booksFile = new File("KsiazkiMagazyn.csv");
		String line = "";
        try{
            PrintWriter writer = new PrintWriter(booksFile.getName(), "UTF-8");

            for (Ksiazka i : ksiazkiMagazyn){
				line = i.GetAutor() + "," + i.GetTytul() + "," + i.GetGatunek() + "," +
                                   i.GetLiczbaStron() + "," + i.GetDataWydania() + "," + i.GetCena() + "," + i.GetIlosc();
				writer.println(line);
			}
            writer.close();
			System.out.println("Zapisano ksiazki do pliku.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}

    public static void SprzedajKsiazke(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id ksiazki do sprzedania");
        int idDoWyszukania = PodajPoprawnaLiczbe();

        int czyZnalezionoKsiazke = 0;
        for (Ksiazka i : ksiazkiMagazyn){
            if(i.GetId() == idDoWyszukania){
                System.out.println("Znaleziono ksiazke: ");
				System.out.println(i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " +
                                   i.GetLiczbaStron() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc() + "\n");
                
                System.out.println("Ile ksiazek sprzedac?");
                int ileSprzedac = PodajPoprawnaLiczbe();

                if (ileSprzedac < 0){
                    System.out.println("Ilosc ksiazek do sprzedania nie moze byc mniejsza od 0.");
                    return;
                }


                try{
                    i.SetIlosc(i.GetIlosc() - ileSprzedac);
                }
                catch(PrzedmiotException pe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Nie ma tylu egzemplarzy!");
                    System.out.println("");
                }
            }
            else
                czyZnalezionoKsiazke += 1;
        }
        if(czyZnalezionoKsiazke == liczbaKsiazek)
			System.out.println("Nie znaleziono ksiazki o podanym id.");	
    }

    public static void WyszukajKsiazke(){
        System.out.println("Wprowadz co ma zostac wyszukane:    ");
		System.out.println("1. Autor");
		System.out.println("2. Tytul");
		System.out.println("3. Gatunek");
		System.out.println("Wprowadz dowolna liczbe aby anulowac");
        int coZnalezc = PodajPoprawnaLiczbe();

        if (coZnalezc < 0 || coZnalezc > 3){
            System.out.println("Podales niepoprawna opcje.");
            return;
        }

        System.out.println("Podaj fraze do wyszukania: ");
        Scanner scanner = new Scanner(System.in);
		String fraza = scanner.nextLine();
		fraza = fraza.toLowerCase();

        for (Ksiazka i : ksiazkiMagazyn){
			if ((coZnalezc == 1) && (i.GetAutor().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetLiczbaStron() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
			if ((coZnalezc == 2) && (i.GetTytul().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetLiczbaStron() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
			if ((coZnalezc == 3) && (i.GetGatunek().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetLiczbaStron() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
		}
    }

    public static void DodajKsiazkiZPliku() throws KsiegarniaException{
        String line = "";  
		String splitBy = ",";  
	
		try{
			BufferedReader br = new BufferedReader(new FileReader("KsiazkiMagazyn.csv"));  
			while ((line = br.readLine()) != null)   
			{  
				liczbaKsiazek += 1;
				String[] ksiazka = line.split(splitBy);  
				Ksiazka nowaKsiazka = new Ksiazka(liczbaKsiazek, ksiazka[0], ksiazka[1], ksiazka[2], Integer.parseInt(ksiazka[3]), ksiazka[4], Integer.parseInt(ksiazka[5]), Integer.parseInt(ksiazka[6]));
				ksiazkiMagazyn.add(nowaKsiazka);
			}  
		}
		catch(FileNotFoundException fnfe){
            System.out.println("");
            System.out.println("Blad!");
			System.out.println("Nie znaleziono pliku z danymi ksiazek!");
            System.out.println("");
		}
        catch(NumberFormatException nfe){
            throw new KsiegarniaException();
        }
        catch(ArrayIndexOutOfBoundsException aie){
            throw new KsiegarniaException();
        }
		catch(IOException ioe){
			ioe.printStackTrace();  
		}
        catch(KsiazkaException ke){
            System.out.println("");
            System.out.println("Blad w pliku z ksiazkami!");
            System.out.println("Blad przy wczytaniu liczby stron!");
            System.out.println("");
        }
        catch(PrzedmiotException pe){
            System.out.println("");
            System.out.println("Blad w pliku z ksiazkami!");
            System.out.println("Blad przy wczytaniu ceny lub ilosci!");
            System.out.println("");
        }
        catch(MyExceptions me){
            System.out.println("");
            System.out.println("Wystapil blad w pliku z ksiazkami!");
            System.out.println("");
        }
    }


    /*
        /////////////////////

        Koniec Metod Ksiazki

        /////////////////////
    */

   /*
        ///////////////////

        Start Metod Ebooki

        ///////////////////
    */

    public static void WyswietlMenuZarzadzaniaEbookami(){
        System.out.println("1. Dodaj ebook");
        System.out.println("2. Wyswietl ebooki");
        System.out.println("3. Modyfikuj ebook");
        System.out.println("4. Usun ebook");
        System.out.println("5. Zapisz ebooki do pliku");
        System.out.println("6. Sprzedaj ebook");
        System.out.println("7. Wyszukaj ebook");
        System.out.println("Wprowadz inna liczbe aby anulowac");

        Scanner scanner = new Scanner(System.in);
        int wybor = PodajPoprawnaLiczbe();

        switch(wybor){
            case 1:
                DodajEbook();
                break;
            case 2:
                WyswietlEbooki();
                break;
            case 3:
                ModyfikujEbook();
                break;
            case 4:
                UsunEbook();
                break;
            case 5:
                ZapiszEbookiDoPliku();
                break;
            case 6:
                SprzedajEbook();
                break;
            case 7:
                WyszukajEbook();
		break;
            default:
		System.out.println("Bledna opcja");
                return;
                
        }
    }


    public static void DodajEbook(){
        Scanner scanner = new Scanner(System.in);

		System.out.println("Podaj autora: ");
		String autor = scanner.nextLine();

		System.out.println("Podaj tytul: ");
		String tytul = scanner.nextLine();

		System.out.println("Podaj gatunek: ");
		String gatunek = scanner.nextLine();

		System.out.println("Podaj date wydania w formacie dd-mm-rrrr: ");
		String dataWydania = PodajPoprawnaDate(false);

        System.out.println("Podaj liczbe stron: ");
        int liczbaStron = PodajPoprawnaLiczbe();

        System.out.println("Podaj rozmiar pliku: ");
        float rozmiar = PodajPoprawnaLiczbeFloat();

        System.out.println("Podaj cene: ");
		int cena = PodajPoprawnaLiczbe();

        System.out.println("Podaj ilosc ksiazek w dostawie: ");
		int ilosc = PodajPoprawnaLiczbe();

        boolean czyToNowyEbook = true;
        try{
            liczbaEbookow += 1;
            Ebook nowyEbook = new Ebook(liczbaEbookow, autor, tytul, gatunek, liczbaStron, dataWydania, rozmiar, cena, ilosc);
            for (Ebook i : ebookiMagazyn){
                if (i.equals(nowyEbook)){
                    i.SetIlosc(i.GetIlosc() + nowyEbook.GetIlosc());
                    System.out.println("\nTaki ebook juz istnieje, zostanie zmodyfikowana ilosc ksiazek.\n");
                    czyToNowyEbook = false;
                }
            }
            
            if (czyToNowyEbook == true){
                ebookiMagazyn.add(nowyEbook);
            }
        }
        catch(EbookException ee){
            System.out.println("");
            System.out.println("Blad!");
            System.out.println("Liczba stron musi byc wieksza od 0, rozmiar musi byc wiekszy od 0 i musi byc przecinek np. 4,5");
            System.out.println("");
            liczbaEbookow -= 1;
        }
        catch(PrzedmiotException pe){
            System.out.println("");
            System.out.println("Blad!");
            System.out.println("Cena musi byc wiekszy niz 0, ilosc musi byc wieksza lub rowna 0");
            System.out.println("");
            liczbaEbookow -= 1;
        }
        catch(MyExceptions me){
            System.out.println("");
            System.out.println("Wystapil blad.");
            System.out.println("");
            liczbaEbookow -= 1;
        }
    }

    public static void WyswietlEbooki(){
        System.out.println("Ebooki w magazynie:");
        System.out.print("|Id ");
		System.out.print("|Autor                         ");
		System.out.print("|Tytul                                             ");
		System.out.print("|Gatunek        ");
		System.out.print("|Liczba stron");
        System.out.print("|Rozmiar");
		System.out.print("|Data wydania   ");
        System.out.print("|Cena   ");
        System.out.print("|Ilosc  |" + "\n");

        for (Ebook i : ebookiMagazyn){
			String kolumnaID = String.format("%1$-3s", i.GetId());
			String kolumnaAutor = String.format("%1$-30s", i.GetAutor());
			String kolumnaTytul = String.format("%1$-50s", i.GetTytul());
			String kolumnaGatunek = String.format("%1$-15s", i.GetGatunek());
			String kolumnaStrony = String.format("%1$-12s", i.GetLiczbaStron());
            String kolumnaRozmiar = String.format("%1$-7s", i.GetRozmiar());
			String kolumnaData = String.format("%1$-15s", i.GetDataWydania());
            String kolumnaCena = String.format("%1$-7s", i.GetCena());
            String kolumnaIlosc = String.format("%1$-6s", i.GetIlosc());

			System.out.println("|" + kolumnaID + "|" +  kolumnaAutor + "|" +  kolumnaTytul + "|" +  kolumnaGatunek + "|" +
                                     kolumnaStrony + "|" + kolumnaRozmiar + "|" + kolumnaData + "|" + kolumnaCena + "|" + kolumnaIlosc + " |");
		}
        System.out.println("");
    }

    public static void ModyfikujEbook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id ebooku ktora chcesz zmodyfikowac");
        int idDoWyszukania = PodajPoprawnaLiczbe();

        int czyZnalezionoEbook = 0;
        for (Ebook i : ebookiMagazyn){
            if(i.GetId() == idDoWyszukania){
                System.out.println("Znaleziono ebook: ");
				System.out.println(i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " +
                                   i.GetLiczbaStron() + " " + i.GetRozmiar() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc() + "\n");
                
                
                System.out.println("Podaj nowe dane ebooka.");
                System.out.println("Podaj 0 aby zachowac stara dana.");
                

                System.out.println("Podaj autora: ");
                String autor = scanner.nextLine();

                System.out.println("Podaj tytul: ");
                String tytul = scanner.nextLine();

                System.out.println("Podaj gatunek: ");
                String gatunek = scanner.nextLine();
            
                System.out.println("Podaj date wydania w formacie dd-mm-rrrr: ");
                String dataWydania = PodajPoprawnaDate(true);

                System.out.println("Podaj liczbe stron: ");
                int liczbaStron = PodajPoprawnaLiczbe();

                System.out.println("Podaj rozmiar pliku: ");
                float rozmiar = PodajPoprawnaLiczbeFloat();

                System.out.println("Podaj cene: ");
                int cena = PodajPoprawnaLiczbe();

                System.out.println("Podaj ilosc ksiazek w dostawie: ");
                int ilosc = PodajPoprawnaLiczbe();

                try{
                    if (!autor.equals("0"))
                        i.SetAutor(autor);
                    if (!tytul.equals("0"))
                        i.SetTytul(tytul);
                    if (!gatunek.equals("0"))
                        i.SetGatunek(gatunek);
                    if (liczbaStron != 0)
                        i.SetLiczbaStron(liczbaStron);
                    if (rozmiar != 0.0f)
                        i.SetRozmiar(rozmiar);
                    if (!dataWydania.equals("0"))
                        i.SetDataWydania(new CData(dataWydania));	
                    if (cena != 0)
                        i.SetCena(cena);
                    if (ilosc != 0)
                        i.SetIlosc(ilosc);
                }
                catch(EbookException ee){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna liczba stron lub rozmiar.");
                    System.out.println("Liczba stron i rozmiar musza byc wieksze od 0.");
                    System.out.println("");
                }
                catch(DataException de){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna data. Data musi byc w formacie dd-mm-rrrr");
                    System.out.println("Data musi byc wieksza od 1-1-1970r.");
                    System.out.println("");
                }
                catch(PrzedmiotException pe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna cena lub ilosc.");
                    System.out.println("Cena i ilosc musza byc wieksze od 0.");
                    System.out.println("");
                }
            }
            else
                czyZnalezionoEbook += 1;
        }
        if(czyZnalezionoEbook == liczbaEbookow)
			System.out.println("Nie znaleziono ebooka o podanym id.");
    }

    public static void UsunEbook(){
        ArrayList<Ebook> ebookiPoUsunieciu = new ArrayList<Ebook>();
        System.out.println("Podaj id do usuniecia: ");
        int idDoUsuniecia = PodajPoprawnaLiczbe();
        int czyUsunietoEbook = liczbaEbookow;

        for (Ebook i : ebookiMagazyn){
			if (i.GetId() != idDoUsuniecia){	
				ebookiPoUsunieciu.add(i);
				czyUsunietoEbook -= 1;
			}
			else if (i.GetId() == idDoUsuniecia){
				System.out.println("Znaleziono ebook: ");
				System.out.println(i.toString() + "\n");
            }
		}

        if ((czyUsunietoEbook != 1) && (idDoUsuniecia != 0))
			System.out.println("Nie znaleziono ebooku o podanym id.");
        else{
            System.out.println("Usunieto ebook.");
            ebookiMagazyn.clear();
		    ebookiMagazyn = ebookiPoUsunieciu;
            liczbaEbookow -= 1;
        }
    }

    public static void ZapiszEbookiDoPliku(){
        File ebooksFile = new File("EbookiMagazyn.csv");
		String line = "";
        try{
            PrintWriter writer = new PrintWriter(ebooksFile.getName(), "UTF-8");

            for (Ebook i : ebookiMagazyn){
				line = i.GetAutor() + "," + i.GetTytul() + "," + i.GetGatunek() + "," +
                                   i.GetLiczbaStron() + "," + i.GetDataWydania() + "," + i.GetRozmiar() + "," + i.GetCena() + "," + i.GetIlosc();
				writer.println(line);
			}
            writer.close();
			System.out.println("Zapisano ebooki do pliku.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void SprzedajEbook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id ebooka do sprzedania");
        int idDoWyszukania = PodajPoprawnaLiczbe();

        int czyZnalezionoEbook = 0;
        for (Ebook i : ebookiMagazyn){
            if(i.GetId() == idDoWyszukania){
                System.out.println("Znaleziono ebook: ");
				System.out.println(i.toString() + "\n");
                System.out.println("Ile ebookow sprzedac?");
                int ileSprzedac = PodajPoprawnaLiczbe();

                if (ileSprzedac < 0){
                    System.out.println("Ilosc ebookow do sprzedania nie moze byc mniejsza od 0.");
                    return;
                }


                try{
                    i.SetIlosc(i.GetIlosc() - ileSprzedac);
                }
                catch(PrzedmiotException pe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Nie ma tylu egzemplarzy!");
                    System.out.println("");
                }
            }
            else
                czyZnalezionoEbook += 1;
        }
        if(czyZnalezionoEbook == liczbaEbookow)
			System.out.println("Nie znaleziono ebooka o podanym id.");	
    }

    public static void WyszukajEbook(){
        System.out.println("Wprowadz co ma zostac wyszukane:    ");
		System.out.println("1. Autor");
		System.out.println("2. Tytul");
		System.out.println("3. Gatunek");
		System.out.println("Wprowadz dowolna liczbe aby anulowac");
        int coZnalezc = PodajPoprawnaLiczbe();

        if (coZnalezc < 0 || coZnalezc > 3){
            System.out.println("Podales niepoprawna opcje.");
            return;
        }

        System.out.println("Podaj fraze do wyszukania: ");
        Scanner scanner = new Scanner(System.in);
		String fraza = scanner.nextLine();
		fraza = fraza.toLowerCase();

        for (Ebook i : ebookiMagazyn){
			if ((coZnalezc == 1) && (i.GetAutor().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetLiczbaStron() + " " + i.GetRozmiar() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
			if ((coZnalezc == 2) && (i.GetTytul().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetLiczbaStron() + " " + i.GetRozmiar() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
			if ((coZnalezc == 3) && (i.GetGatunek().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetLiczbaStron() + " " + i.GetRozmiar() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
		}
    }

    public static void DodajEbookiZPliku() throws KsiegarniaException{
        String line = "";  
		String splitBy = ",";  
	
		try{
			BufferedReader br = new BufferedReader(new FileReader("EbookiMagazyn.csv"));  
			while ((line = br.readLine()) != null)   
			{  
				liczbaEbookow += 1;
				String[] ebook = line.split(splitBy);  
				Ebook nowyEbook = new Ebook(liczbaEbookow, ebook[0], ebook[1], ebook[2], Integer.parseInt(ebook[3]), ebook[4], Float.parseFloat(ebook[5]),Integer.parseInt(ebook[6]), Integer.parseInt(ebook[7]));
				ebookiMagazyn.add(nowyEbook);
			}  
		}
		catch(FileNotFoundException fnfe){
            System.out.println("");
            System.out.println("Blad!");
			System.out.println("Nie znaleziono pliku z danymi ebookow!");
            System.out.println("");
		}
        catch(NumberFormatException nfe){
            throw new KsiegarniaException();
        }
        catch(ArrayIndexOutOfBoundsException aie){
            throw new KsiegarniaException();
        }
		catch(IOException ioe){
			ioe.printStackTrace();  
		}
        catch(EbookException ee){
            System.out.println("");
            System.out.println("Blad w pliku z ebookami!");
            System.out.println("Blad przy wczytaniu liczby stron lub rozmiaru pliku!");
            System.out.println("");
        }
        catch(PrzedmiotException pe){
            System.out.println("");
            System.out.println("Blad w pliku z ebookami!");
            System.out.println("Blad przy wczytaniu ceny lub ilosci!");
            System.out.println("");
        }
        catch(MyExceptions me){
            System.out.println("");
            System.out.println("Wystapil blad w pliku z ebookami!");
            System.out.println("");
        }
    }

   /*
        ///////////////////

        Koniec Metod Ebooki

        ///////////////////
    */


   /*
        //////////////////////////

        Start Metod Plyty Muzyczne

        //////////////////////////
    */

    public static void WyswietlMenuZarzadzaniaPlytamiMuzycznymi(){
        System.out.println("1. Dodaj plyte muzyczna");
        System.out.println("2. Wyswietl plyty muzyczne");
        System.out.println("3. Modyfikuj plyte muzyczna");
        System.out.println("4. Usun plyte muzyczna");
        System.out.println("5. Zapisz plyty do pliku");
        System.out.println("6. Sprzedaj plyte muzyczna");
        System.out.println("7. Wyszukaj plyte muzyczna");
        System.out.println("Wprowadz inna liczbe aby anulowac");

        Scanner scanner = new Scanner(System.in);
        int wybor = PodajPoprawnaLiczbe();

        switch(wybor){
            case 1:
                DodajPlyteMuzyczna();
                break;
            case 2:
                WyswietlPlytyMuzyczne();
                break;
            case 3:
                ModyfikujPlyteMuzyczna();
                break;
            case 4:
                UsunPlyteMuzyczna();
                break;
            case 5:
                ZapiszPlytyMuzyczneDoPliku();
                break;
            case 6:
                SprzedajPlyteMuzyczna();
                break;
            case 7:
                WyszukajPlyteMuzyczna();
		break;
            default:
		System.out.println("Bledna opcja");
                return;
                
        }
    }

    public static void DodajPlyteMuzyczna(){
         Scanner scanner = new Scanner(System.in);

		System.out.println("Podaj autora: ");
		String autor = scanner.nextLine();

		System.out.println("Podaj tytul: ");
		String tytul = scanner.nextLine();

		System.out.println("Podaj gatunek: ");
		String gatunek = scanner.nextLine();

		System.out.println("Podaj date wydania w formacie dd-mm-rrrr: ");
		String dataWydania = PodajPoprawnaDate(false);

        System.out.println("Podaj czas trwania: ");
        int czasTrwania = PodajPoprawnaLiczbe();

        System.out.println("Podaj cene: ");
		int cena = PodajPoprawnaLiczbe();

        System.out.println("Podaj ilosc filmow w dostawie: ");
		int ilosc = PodajPoprawnaLiczbe();

        boolean czyToNowaPlyta = true;
        try{
            liczbaPlytMuzycznych += 1;
            PlytaMuzyczna nowaPlyta = new PlytaMuzyczna(liczbaPlytMuzycznych, autor, tytul, gatunek, dataWydania, cena, ilosc, czasTrwania);
            for (PlytaMuzyczna i : plytyMuzyczneMagazyn){
                if (i.equals(nowaPlyta)){
                    i.SetIlosc(i.GetIlosc() + nowaPlyta.GetIlosc());
                    System.out.println("\nTaka plyta muzyczna juz istnieje, zostanie zmodyfikowana ilosc ksiazek.\n");
                    czyToNowaPlyta = false;
                }
            }
            
            if (czyToNowaPlyta == true){
                plytyMuzyczneMagazyn.add(nowaPlyta);
            }
        }
        catch(PlytaMuzycznaException pme){
            System.out.println("");
            System.out.println("Blad!");
            System.out.println("Czas trwania musi byc wiekszy niz 0.");
            System.out.println("");
            liczbaPlytMuzycznych -= 1;
        }
        catch(PrzedmiotException pe){
            System.out.println("");
            System.out.println("Blad!");
            System.out.println("Cena i ilosc musza byc wieksze od 0.");
            System.out.println("");
            liczbaPlytMuzycznych -= 1;

        }
        catch(MyExceptions me){
            System.out.println("");
            System.out.println("Wystapil blad.");
            System.out.println("");
            liczbaPlytMuzycznych -= 1;
        }
    }
    public static void WyswietlPlytyMuzyczne(){
        System.out.println("Plyty muzyczne w magazynie:");
        System.out.print("|Id ");
		System.out.print("|Autor                         ");
		System.out.print("|Tytul                                             ");
		System.out.print("|Gatunek        ");
		System.out.print("|Czas trwania");
		System.out.print("|Data wydania   ");
        System.out.print("|Cena   ");
        System.out.print("|Ilosc  |" + "\n");

        for (PlytaMuzyczna i : plytyMuzyczneMagazyn){
			String kolumnaID = String.format("%1$-3s", i.GetId());
			String kolumnaAutor = String.format("%1$-30s", i.GetAutor());
			String kolumnaTytul = String.format("%1$-50s", i.GetTytul());
			String kolumnaGatunek = String.format("%1$-15s", i.GetGatunek());
			String kolumnaCzasTrwania = String.format("%1$-12s", i.GetCzasTrwania());
			String kolumnaData = String.format("%1$-15s", i.GetDataWydania());
            String kolumnaCena = String.format("%1$-7s", i.GetCena());
            String kolumnaIlosc = String.format("%1$-6s", i.GetIlosc());

			System.out.println("|" + kolumnaID + "|" +  kolumnaAutor + "|" +  kolumnaTytul + "|" +  kolumnaGatunek + "|" +
                                     kolumnaCzasTrwania + "|" + kolumnaData + "|" + kolumnaCena + "|" + kolumnaIlosc + " |");
		}
        System.out.println("");
    }
    public static void ModyfikujPlyteMuzyczna(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id plyty muzycznej ktora chcesz zmodyfikowac");
        int idDoWyszukania = PodajPoprawnaLiczbe();

        int czyZnalezionoPlyte = 0;
        for (PlytaMuzyczna i : plytyMuzyczneMagazyn){
            if(i.GetId() == idDoWyszukania){
                System.out.println("Znaleziono plyte muzyczna: ");
				System.out.println(i.toString() + "\n");
                
                
                System.out.println("Podaj nowe dane plyty muzycznej.");
                System.out.println("Podaj 0 aby zachowac stara dana.");
                

                System.out.println("Podaj autora: ");
                String autor = scanner.nextLine();

                System.out.println("Podaj tytul: ");
                String tytul = scanner.nextLine();

                System.out.println("Podaj gatunek: ");
                String gatunek = scanner.nextLine();

                System.out.println("Podaj date wydania w formacie dd-mm-rrrr: ");
                String dataWydania = PodajPoprawnaDate(true);

                System.out.println("Podaj czas trwania: ");
                int czasTrwania = PodajPoprawnaLiczbe();

                System.out.println("Podaj cene: ");
                int cena = PodajPoprawnaLiczbe();

                System.out.println("Podaj ilosc ksiazek w dostawie: ");
                int ilosc = PodajPoprawnaLiczbe();

                try{
                    if (!autor.equals("0"))
                        i.SetAutor(autor);
                    if (!tytul.equals("0"))
                        i.SetTytul(tytul);
                    if (!gatunek.equals("0"))
                        i.SetGatunek(gatunek);
                    if (czasTrwania != 0)
                        i.SetCzasTrwania(czasTrwania);
                    if (!dataWydania.equals("0"))
                        i.SetDataWydania(new CData(dataWydania));	
                    if (cena != 0)
                        i.SetCena(cena);
                    if (ilosc != 0)
                        i.SetIlosc(ilosc);
                }
                catch(PlytaMuzycznaException pme){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawny czas trwania.");
                    System.out.println("Czas trwania wiekszy niz 0");
                    System.out.println("");
                }
                catch(DataException de){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna data. Data musi byc w formacie dd-mm-rrrr");
                    System.out.println("Data musi byc wieksza od 1-1-1970r.");
                    System.out.println("");
                }
                catch(PrzedmiotException pe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna cena lub ilosc plyt muzycznych.");
                    System.out.println("Cena i ilosc musza byc wieksze od 0");
                    System.out.println("");
                }
            }
            else
                czyZnalezionoPlyte += 1;
        }
        if(czyZnalezionoPlyte == liczbaPlytMuzycznych)
			System.out.println("Nie znaleziono ksiazki o podanym id.");	
    }

    public static void UsunPlyteMuzyczna(){
        ArrayList<PlytaMuzyczna> plytyPoUsunieciu = new ArrayList<PlytaMuzyczna>();
        System.out.println("Podaj id do usuniecia: ");
        int idDoUsuniecia = PodajPoprawnaLiczbe();
        int czyUsunietoPlyte = liczbaPlytMuzycznych;

        for (PlytaMuzyczna i : plytyMuzyczneMagazyn){
			if (i.GetId() != idDoUsuniecia){	
				plytyPoUsunieciu.add(i);
				czyUsunietoPlyte -= 1;
			}
			else if (i.GetId() == idDoUsuniecia){
				System.out.println("Znaleziono plyte: ");
				System.out.println(i.toString() + "\n");
            }
		}

        if ((czyUsunietoPlyte != 1) && (idDoUsuniecia != 0))
			System.out.println("Nie znaleziono plyty muzycznej o podanym id.");
        else{
            System.out.println("Usunieto plyte muzyczna.");
            plytyMuzyczneMagazyn.clear();
		    plytyMuzyczneMagazyn = plytyPoUsunieciu;
            liczbaPlytMuzycznych -= 1;
        }
    }
    public static void ZapiszPlytyMuzyczneDoPliku(){
        
		File musicFile = new File("PlytyMuzyczneMagazyn.csv");
		String line = "";
        try{
            PrintWriter writer = new PrintWriter(musicFile.getName(), "UTF-8");
                                   
            for (PlytaMuzyczna i : plytyMuzyczneMagazyn){
				line = i.GetAutor() + "," + i.GetTytul() + "," + i.GetGatunek() + "," + i.GetDataWydania()
                                   + "," + i.GetCena() + "," + i.GetIlosc() + "," +  i.GetCzasTrwania();
				writer.println(line);
			}
            writer.close();
			System.out.println("Zapisano plyty muzyczne do pliku.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void SprzedajPlyteMuzyczna(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id plyty muzycznej do sprzedania");
        int idDoWyszukania = PodajPoprawnaLiczbe();

        int czyZnalezionoPlyte = 0;
        for (PlytaMuzyczna i : plytyMuzyczneMagazyn){
            if(i.GetId() == idDoWyszukania){
                System.out.println("Znaleziono plyte muzyczna: ");
				System.out.println(i.toString() + "\n");
                
                System.out.println("Ile plyt muzycznych sprzedac?");
                int ileSprzedac = PodajPoprawnaLiczbe();

                if (ileSprzedac < 0){
                    System.out.println("Ilosc do sprzedania nie moze byc mniejsza od 0.");
                    return;
                }


                try{
                    i.SetIlosc(i.GetIlosc() - ileSprzedac);
                }
                catch(PrzedmiotException pe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Nie ma tylu egzemplarzy!");
                    System.out.println("");
                }
            }
            else
                czyZnalezionoPlyte += 1;
        }
        if(czyZnalezionoPlyte == liczbaPlytMuzycznych)
			System.out.println("Nie znaleziono plyty muzycznej o podanym id.");	
    }
    public static void WyszukajPlyteMuzyczna(){
        System.out.println("Wprowadz co ma zostac wyszukane:    ");
		System.out.println("1. Autor");
		System.out.println("2. Tytul");
		System.out.println("3. Gatunek");
		System.out.println("Wprowadz dowolna liczbe aby anulowac");
        int coZnalezc = PodajPoprawnaLiczbe();

        if (coZnalezc < 0 || coZnalezc > 3){
            System.out.println("Podales niepoprawna opcje.");
            return;
        }

        System.out.println("Podaj fraze do wyszukania: ");
        Scanner scanner = new Scanner(System.in);
		String fraza = scanner.nextLine();
		fraza = fraza.toLowerCase();

        for (PlytaMuzyczna i : plytyMuzyczneMagazyn){
			if ((coZnalezc == 1) && (i.GetAutor().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetCzasTrwania() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
			if ((coZnalezc == 2) && (i.GetTytul().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetCzasTrwania() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
			if ((coZnalezc == 3) && (i.GetGatunek().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetCzasTrwania() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
		}
    }

    public static void DodajPlytyMuzyczneZPliku() throws KsiegarniaException{
        String line = "";  
		String splitBy = ",";  
	
		try{
			BufferedReader br = new BufferedReader(new FileReader("PlytyMuzyczneMagazyn.csv"));  
			while ((line = br.readLine()) != null)   
			{  
				liczbaPlytMuzycznych += 1;
				String[] plyta = line.split(splitBy);  
				PlytaMuzyczna nowaPlyta = new PlytaMuzyczna(liczbaPlytMuzycznych, plyta[0], plyta[1], plyta[2], plyta[3], Integer.parseInt(plyta[4]), Integer.parseInt(plyta[5]), Integer.parseInt(plyta[6]));
				plytyMuzyczneMagazyn.add(nowaPlyta);
			}  
		}
		catch(FileNotFoundException fnfe){
            System.out.println("");
            System.out.println("Blad!");
			System.out.println("Nie znaleziono pliku z danymi plyt muzycznych!");
            System.out.println("");
		}
        catch(NumberFormatException nfe){
            throw new KsiegarniaException();
        }
        catch(ArrayIndexOutOfBoundsException aie){
            throw new KsiegarniaException();
        }
		catch(IOException ioe){
			ioe.printStackTrace();  
		}
        catch(PlytaMuzycznaException pme){
            System.out.println("");
            System.out.println("Blad w pliku z plytami muzycznymi!");
            System.out.println("Blad przy wczytaniu czasu trwania!");
            System.out.println("");
        }
        catch(PrzedmiotException pe){
            System.out.println("");
            System.out.println("Blad w pliku z plytami muzycznymi!");
            System.out.println("Blad przy wczytaniu ceny lub ilosci!");
            System.out.println("");
        }
        catch(MyExceptions me){
            System.out.println("");
            System.out.println("Wystapil blad w pliku z plytami muzycznymi!");
            System.out.println("");
        }
    }



   /*
        ///////////////////////////

        Koniec Metod Plyty Muzyczne

        ///////////////////////////
    */


   /*
        /////////////////

        Start Metod Filmy

        /////////////////
    */

   public static void WyswietlMenuZarzadzaniaFilmami(){
        System.out.println("1. Dodaj film");
        System.out.println("2. Wyswietl filmy");
        System.out.println("3. Modyfikuj film");
        System.out.println("4. Usun film");
        System.out.println("5. Zapisz filmy do pliku");
        System.out.println("6. Sprzedaj film");
        System.out.println("7. Wyszukaj film");
        System.out.println("Wprowadz inna liczbe aby anulowac");

        Scanner scanner = new Scanner(System.in);
        int wybor = PodajPoprawnaLiczbe();

        switch(wybor){
            case 1:
                DodajFilm();
                break;
            case 2:
                WyswietlFilmy();
                break;
            case 3:
                ModyfikujFilm();
                break;
            case 4:
                UsunFilm();
                break;
            case 5:
                ZapiszFilmyDoPliku();
                break;
            case 6:
                SprzedajFilm();
                break;
            case 7:
                WyszukajFilm();
		break;
            default:
		System.out.println("Bledna opcja");
                return;
                
        }
    }

    public static void DodajFilm(){
        Scanner scanner = new Scanner(System.in);

		System.out.println("Podaj autora: ");
		String autor = scanner.nextLine();

		System.out.println("Podaj tytul: ");
		String tytul = scanner.nextLine();

		System.out.println("Podaj gatunek: ");
		String gatunek = scanner.nextLine();

		System.out.println("Podaj date wydania w formacie dd-mm-rrrr: ");
		String dataWydania = PodajPoprawnaDate(false);

        System.out.println("Podaj ograniczenie wiekowe: ");
        int ograniczenieWiekowe = PodajPoprawnaLiczbe();

        System.out.println("Podaj czas trwania: ");
        int czasTrwania = PodajPoprawnaLiczbe();

        System.out.println("Podaj cene: ");
		int cena = PodajPoprawnaLiczbe();

        System.out.println("Podaj ilosc filmow w dostawie: ");
		int ilosc = PodajPoprawnaLiczbe();

        boolean czyToNowyFilm = true;
        try{
            liczbaFilmow += 1;
            Film nowyFilm = new Film(liczbaFilmow, autor, tytul, gatunek, ograniczenieWiekowe, dataWydania, cena, ilosc, czasTrwania);
            for (Film i : filmyMagazyn){
                if (i.equals(nowyFilm)){
                    i.SetIlosc(i.GetIlosc() + nowyFilm.GetIlosc());
                    System.out.println("\nTaki film juz istnieje, zostanie zmodyfikowana ilosc ksiazek.\n");
                    czyToNowyFilm = false;
                }
            }
            
            if (czyToNowyFilm == true){
                filmyMagazyn.add(nowyFilm);
            }
        }
        catch(FilmException fe){
            System.out.println("");
            System.out.println("Nieprawidlowy czas trwania lub ograniczenie wiekowe");
            System.out.println("Czas trwania musi byc wiekszy od 0, ograniczenie wiekowe musi byc z przedzialu od 0 do 18.");
            System.out.println("");
            liczbaFilmow -= 1;
        }
        catch(PrzedmiotException pe){
            System.out.println("");
            System.out.println("Blad!");
            System.out.println("Cena i ilosc musza byc wieksze od 0.");
            System.out.println("");
            liczbaFilmow -= 1;
        }
        catch(MyExceptions me){
            System.out.println("");
            System.out.println("Wystapil blad.");
            System.out.println("");
            liczbaFilmow -= 1;
        }
    }

    public static void WyswietlFilmy(){
        System.out.println("Filmy w magazynie:");
        System.out.print("|Id ");
		System.out.print("|Autor                         ");
		System.out.print("|Tytul                                             ");
		System.out.print("|Gatunek        ");
		System.out.print("|Ograniczenie wiekowe");
        System.out.print("|Czas trwania");
		System.out.print("|Data wydania   ");
        System.out.print("|Cena   ");
        System.out.print("|Ilosc  |" + "\n");

        for (Film i : filmyMagazyn){
			String kolumnaID = String.format("%1$-3s", i.GetId());
			String kolumnaAutor = String.format("%1$-30s", i.GetAutor());
			String kolumnaTytul = String.format("%1$-50s", i.GetTytul());
			String kolumnaGatunek = String.format("%1$-15s", i.GetGatunek());
			String kolumnaOgraniczenie = String.format("%1$-20s", i.GetOgraniczeniaWiekowe());
            String kolumnaCzasTrwania = String.format("%1$-12s", i.GetCzasTrwania());
			String kolumnaData = String.format("%1$-15s", i.GetDataWydania());
            String kolumnaCena = String.format("%1$-7s", i.GetCena());
            String kolumnaIlosc = String.format("%1$-6s", i.GetIlosc());

			System.out.println("|" + kolumnaID + "|" +  kolumnaAutor + "|" +  kolumnaTytul + "|" +  kolumnaGatunek + "|" +
                                     kolumnaOgraniczenie + "|" + kolumnaCzasTrwania + "|" + kolumnaData + "|" + kolumnaCena + "|" + kolumnaIlosc + " |");
		}
        System.out.println("");
    }

    public static void ModyfikujFilm(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id filmu ktora chcesz zmodyfikowac");
        int idDoWyszukania = PodajPoprawnaLiczbe();

        int czyZnalezionoFilm = 0;
        for (Film i : filmyMagazyn){
            if(i.GetId() == idDoWyszukania){
                System.out.println("Znaleziono film: ");
				System.out.println(i.toString() + "\n");
                
                
                System.out.println("Podaj nowe dane filmu.");
                System.out.println("Podaj 0 aby zachowac stara dana.");
                

                System.out.println("Podaj autora: ");
                String autor = scanner.nextLine();

                System.out.println("Podaj tytul: ");
                String tytul = scanner.nextLine();

                System.out.println("Podaj gatunek: ");
                String gatunek = scanner.nextLine();

                System.out.println("Podaj date wydania w formacie dd-mm-rrrr: ");
                String dataWydania = PodajPoprawnaDate(true);

                System.out.println("Podaj ograniczenie wiekowe: ");
                int ograniczenieWiekowe = PodajPoprawnaLiczbe();

                System.out.println("Podaj czas trwania: ");
                int czasTrwania = PodajPoprawnaLiczbe();

                System.out.println("Podaj cene: ");
                int cena = PodajPoprawnaLiczbe();

                System.out.println("Podaj ilosc filmow w dostawie: ");
                int ilosc = PodajPoprawnaLiczbe();

                try{
                    if (!autor.equals("0"))
                        i.SetAutor(autor);
                    if (!tytul.equals("0"))
                        i.SetTytul(tytul);
                    if (!gatunek.equals("0"))
                        i.SetGatunek(gatunek);
                    if (ograniczenieWiekowe != 0)
                        i.SetOgraniczenieWiekowe(ograniczenieWiekowe);
                    if (czasTrwania != 0)
                        i.SetCzasTrwania(czasTrwania);
                    if (!dataWydania.equals("0"))
                        i.SetDataWydania(new CData(dataWydania));	
                    if (cena != 0)
                        i.SetCena(cena);
                    if (ilosc != 0)
                        i.SetIlosc(ilosc);
                }
                catch(FilmException fe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawny czas trwania lub ograniczenie wiekowe.");
                    System.out.println("Czas trwania musi byc wiekszy od 0, ograniczenie wiekowe musi byc w przedziale od 0 do 18.");
                    System.out.println("");
                }
                catch(DataException de){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna data. Data musi byc w formacie dd-mm-rrrr");
                    System.out.println("Data musi byc wieksza od 1-1-1970r.");
                    System.out.println("");
                }
                catch(PrzedmiotException pe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna cena lub ilosc ksiazek.");
                    System.out.println("Cena i ilosc musza byc wieksze od 0");
                    System.out.println("");
                }
            }
            else
                czyZnalezionoFilm += 1;
        }
        if(czyZnalezionoFilm == liczbaFilmow)
			System.out.println("Nie znaleziono ksiazki o podanym id.");
        else{

        }	
    }

    public static void UsunFilm(){
        ArrayList<Film> filmyPoUsunieciu = new ArrayList<Film>();
        System.out.println("Podaj id do usuniecia: ");
        int idDoUsuniecia = PodajPoprawnaLiczbe();
        int czyUsunietoFilm = liczbaFilmow;

        for (Film i : filmyMagazyn){
			if (i.GetId() != idDoUsuniecia){	
				filmyPoUsunieciu.add(i);
				czyUsunietoFilm -= 1;
			}
			else if (i.GetId() == idDoUsuniecia){
				System.out.println("Znaleziono film: ");
				System.out.println(i.toString() + "\n");
            }
		}

        if ((czyUsunietoFilm != 1) && (idDoUsuniecia != 0))
			System.out.println("Nie znaleziono filmu o podanym id.");
        else{
            System.out.println("Usunieto film.");
            filmyMagazyn.clear();
		    filmyMagazyn = filmyPoUsunieciu;
            liczbaFilmow -= 1;
        }
    }

    public static void ZapiszFilmyDoPliku(){
		File filmsFile = new File("FilmyMagazyn.csv");
		String line = "";
        try{
            PrintWriter writer = new PrintWriter(filmsFile.getName(), "UTF-8");

            for (Film i : filmyMagazyn){
				line = i.GetAutor() + "," + i.GetTytul() + "," + i.GetGatunek() + "," +
                                   i.GetOgraniczeniaWiekowe() + "," + i.GetDataWydania() + "," + i.GetCena() + "," + i.GetIlosc()  + "," + i.GetCzasTrwania();
				writer.println(line);
			}
            writer.close();
			System.out.println("Zapisano filmy do pliku.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}

    public static void SprzedajFilm(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id filmu do sprzedania");
        int idDoWyszukania = PodajPoprawnaLiczbe();

        int czyZnalezionoFilm = 0;
        for (Film i : filmyMagazyn){
            if(i.GetId() == idDoWyszukania){
                System.out.println("Znaleziono film: ");
				System.out.println(i.toString() + "\n");
                
                System.out.println("Ile filmow sprzedac?");
                int ileSprzedac = PodajPoprawnaLiczbe();

                if (ileSprzedac < 0){
                    System.out.println("Ilosc do sprzedania nie moze byc mniejsza od 0.");
                    return;
                }


                try{
                    i.SetIlosc(i.GetIlosc() - ileSprzedac);
                }
                catch(PrzedmiotException pe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Nie ma tylu egzemplarzy!");
                    System.out.println("");
                }
            }
            else
                czyZnalezionoFilm += 1;
        }
        if(czyZnalezionoFilm == liczbaFilmow)
			System.out.println("Nie znaleziono filmu o podanym id.");	
    }

    public static void WyszukajFilm(){
        System.out.println("Wprowadz co ma zostac wyszukane:    ");
		System.out.println("1. Autor");
		System.out.println("2. Tytul");
		System.out.println("3. Gatunek");
		System.out.println("Wprowadz dowolna liczbe aby anulowac");
        int coZnalezc = PodajPoprawnaLiczbe();

        if (coZnalezc < 0 || coZnalezc > 3){
            System.out.println("Podales niepoprawna opcje.");
            return;
        }

        System.out.println("Podaj fraze do wyszukania: ");
        Scanner scanner = new Scanner(System.in);
		String fraza = scanner.nextLine();
		fraza = fraza.toLowerCase();

        for (Film i : filmyMagazyn){
			if ((coZnalezc == 1) && (i.GetAutor().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetOgraniczeniaWiekowe() + " " + i.GetCzasTrwania() + " " +  i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
			if ((coZnalezc == 2) && (i.GetTytul().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetOgraniczeniaWiekowe() + " " + i.GetCzasTrwania() + " " +  i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
			if ((coZnalezc == 3) && (i.GetGatunek().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetOgraniczeniaWiekowe() + " " + i.GetCzasTrwania() + " " +  i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
		}
    }

    public static void DodajFilmyZPliku() throws KsiegarniaException{
        String line = "";  
		String splitBy = ",";  
	
		try{
			BufferedReader br = new BufferedReader(new FileReader("FilmyMagazyn.csv"));  
			while ((line = br.readLine()) != null)   
			{  
				liczbaFilmow += 1;
				String[] film = line.split(splitBy);  
				Film nowyFilm = new Film(liczbaFilmow, film[0], film[1], film[2], Integer.parseInt(film[3]), film[4], Integer.parseInt(film[5]), Integer.parseInt(film[6]), Integer.parseInt(film[7]));
				filmyMagazyn.add(nowyFilm);
			}  
		}
		catch(FileNotFoundException fnfe){
            System.out.println("");
            System.out.println("Blad!");
			System.out.println("Nie znaleziono pliku z danymi filmow!");
            System.out.println("");
		}
        catch(NumberFormatException nfe){
            throw new KsiegarniaException();
        }
        catch(ArrayIndexOutOfBoundsException aie){
            throw new KsiegarniaException();
        }
		catch(IOException ioe){
			ioe.printStackTrace();  
		}
        catch(FilmException ke){
            System.out.println("");
            System.out.println("Blad w pliku z filmami!");
            System.out.println("Blad przy wczytaniu czasu trwania lub ograniczenia wiekowego!");
            System.out.println("");
        }
        catch(PrzedmiotException pe){
            System.out.println("");
            System.out.println("Blad w pliku z filmami!");
            System.out.println("Blad przy wczytaniu ceny lub ilosci!");
            System.out.println("");
        }
        catch(MyExceptions me){
            System.out.println("");
            System.out.println("Wystapil blad w pliku z filmami!");
            System.out.println("");
        }
    }

   /*
        //////////////////

        Koniec Metod Filmy

        //////////////////
    */



   /*
        /////////////////////////

        Start Metod Gry Planszowe

        /////////////////////////
    */

   public static void WyswietlMenuZarzadzaniaGramiPlanszowymi(){
        System.out.println("1. Dodaj gre planszowa");
        System.out.println("2. Wyswietl gry planszowe");
        System.out.println("3. Modyfikuj gry planszowe");
        System.out.println("4. Usun gre planszowa");
        System.out.println("5. Zapisz gry do pliku");
        System.out.println("6. Sprzedaj gre planszowa");
        System.out.println("7. Wyszukaj gre planszowa");
        System.out.println("Wprowadz inna liczbe aby anulowac");

        Scanner scanner = new Scanner(System.in);
        int wybor = PodajPoprawnaLiczbe();

        switch(wybor){
            case 1:
                DodajGrePlanszowa();
                break;
            case 2:
                WyswietlGryPlanszowe();
                break;
            case 3:
                ModyfikujGrePlanszowa();
                break;
            case 4:
                UsunGrePlanszowa();
                break;
            case 5:
                ZapiszGryPlanszoweDoPliku();
                break;
            case 6:
                SprzedajGrePlanszowa();
                break;
            case 7:
                WyszukajGrePlanszowa();
		break;
            default:
		System.out.println("Bledna opcja");
                return;
                
        }
    }

    public static void DodajGrePlanszowa(){
        Scanner scanner = new Scanner(System.in);

		System.out.println("Podaj autora: ");
		String autor = scanner.nextLine();

		System.out.println("Podaj tytul: ");
		String tytul = scanner.nextLine();

		System.out.println("Podaj gatunek: ");
		String gatunek = scanner.nextLine();

        System.out.println("Podaj ilosc graczy: ");
		String iloscGraczy = scanner.nextLine();

		System.out.println("Podaj date wydania w formacie dd-mm-rrrr: ");
		String dataWydania = PodajPoprawnaDate(false);

        System.out.println("Podaj ograniczenie wiekowe: ");
        int ograniczenieWiekowe = PodajPoprawnaLiczbe();

        System.out.println("Podaj czas trwania rozgrywki: ");
        int czasTrwania = PodajPoprawnaLiczbe();

        System.out.println("Podaj cene: ");
		int cena = PodajPoprawnaLiczbe();

        System.out.println("Podaj ilosc gier w dostawie: ");
		int ilosc = PodajPoprawnaLiczbe();

        boolean czyToNowaGra = true;
        try{
            liczbaGierPlanszowych += 1;
            GraPlanszowa nowaGra = new GraPlanszowa(liczbaGierPlanszowych, autor, tytul, gatunek, ograniczenieWiekowe, dataWydania, cena, ilosc, czasTrwania, iloscGraczy);
            for (GraPlanszowa i : gryPlanszoweMagazyn){
                if (i.equals(nowaGra)){
                    i.SetIlosc(i.GetIlosc() + nowaGra.GetIlosc());
                    System.out.println("\nTaka gra planszowa juz istnieje, zostanie zmodyfikowana ilosc gier.\n");
                    czyToNowaGra = false;
                }
            }
            
            if (czyToNowaGra == true){
                gryPlanszoweMagazyn.add(nowaGra);
            }
        }
        catch(GraPlanszowaException gpe){
            System.out.println("");
            System.out.println("Blad!");
            System.out.println("Czas trwania musi byc wiekszy niz 0, ograniczenie wiekowe musi byc od 0 do 99, ilosc graczy musi byc w formacie min-max");
            System.out.println("");
            liczbaGierPlanszowych -= 1;
        }
        catch(PrzedmiotException pe){
            System.out.println("");
            System.out.println("Blad!");
            System.out.println("Cena musi byc wiekszy niz 0, ilosc musi byc wieksza lub rowna 0");
            System.out.println("");
            liczbaGierPlanszowych -= 1;
        }
        catch(MyExceptions me){
            System.out.println("");
            System.out.println("Wystapil blad.");
            System.out.println("");
            liczbaEbookow -= 1;
        }
    }

     public static void WyswietlGryPlanszowe(){
        System.out.println("Gry planszowe w magazynie:");
        System.out.print("|Id ");
		System.out.print("|Autor                         ");
		System.out.print("|Tytul                                             ");
		System.out.print("|Gatunek        ");
        System.out.print("|Ilosc graczy");
		System.out.print("|Ograniczenie wiekowe");
        System.out.print("|Czas trwania");
		System.out.print("|Data wydania   ");
        System.out.print("|Cena   ");
        System.out.print("|Ilosc  |" + "\n");

        for (GraPlanszowa i : gryPlanszoweMagazyn){
			String kolumnaID = String.format("%1$-3s", i.GetId());
			String kolumnaAutor = String.format("%1$-30s", i.GetAutor());
			String kolumnaTytul = String.format("%1$-50s", i.GetTytul());
			String kolumnaGatunek = String.format("%1$-15s", i.GetGatunek());
            String kolumnaGracze = String.format("%1$-12s", i.GetIloscGraczy());
			String kolumnaOgraniczenie = String.format("%1$-20s", i.GetOgraniczeniaWiekowe());
            String kolumnaCzasTrwania = String.format("%1$-12s", i.GetCzasTrwania());
			String kolumnaData = String.format("%1$-15s", i.GetDataWydania());
            String kolumnaCena = String.format("%1$-7s", i.GetCena());
            String kolumnaIlosc = String.format("%1$-6s", i.GetIlosc());

			System.out.println("|" + kolumnaID + "|" +  kolumnaAutor + "|" +  kolumnaTytul + "|" +  kolumnaGatunek + "|" + kolumnaGracze + "|" +
                                     kolumnaOgraniczenie + "|" + kolumnaCzasTrwania + "|" + kolumnaData + "|" + kolumnaCena + "|" + kolumnaIlosc + " |");
		}
        System.out.println("");
    }

    public static void ModyfikujGrePlanszowa(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id gry planszowej ktora chcesz zmodyfikowac");
        int idDoWyszukania = PodajPoprawnaLiczbe();

        int czyZnalezionoGre = 0;
        for (GraPlanszowa i : gryPlanszoweMagazyn){
            if(i.GetId() == idDoWyszukania){
                System.out.println("Znaleziono gre planszowa: ");
				System.out.println(i.toString() + "\n");
                
                
                System.out.println("Podaj nowe dane gry planszowej.");
                System.out.println("Podaj 0 aby zachowac stara dana.");
                

                System.out.println("Podaj autora: ");
                String autor = scanner.nextLine();

                System.out.println("Podaj tytul: ");
                String tytul = scanner.nextLine();

                System.out.println("Podaj gatunek: ");
                String gatunek = scanner.nextLine();

                System.out.println("Podaj ilosc graczy: ");
                String iloscGraczy = scanner.nextLine();
            
                System.out.println("Podaj date wydania w formacie dd-mm-rrrr: ");
                String dataWydania = PodajPoprawnaDate(true);

                System.out.println("Podaj ograniczenie wiekowe: ");
                int ograniczenieWiekowe = PodajPoprawnaLiczbe();

                System.out.println("Podaj czas trwania: ");
                int czasTrwania = PodajPoprawnaLiczbe();

                System.out.println("Podaj cene: ");
                int cena = PodajPoprawnaLiczbe();

                System.out.println("Podaj ilosc ksiazek w dostawie: ");
                int ilosc = PodajPoprawnaLiczbe();

                try{
                    if (!autor.equals("0"))
                        i.SetAutor(autor);
                    if (!tytul.equals("0"))
                        i.SetTytul(tytul);
                    if (!gatunek.equals("0"))
                        i.SetGatunek(gatunek);
                    if (ograniczenieWiekowe != 0)
                        i.SetOgraniczenieWiekowe(ograniczenieWiekowe);
                    if (czasTrwania != 0)
                        i.SetCzasTrwania(czasTrwania);
                    if (!iloscGraczy.equals("0"))
                        i.SetTytul(iloscGraczy);
                    if (!dataWydania.equals("0"))
                        i.SetDataWydania(new CData(dataWydania));	
                    if (cena != 0)
                        i.SetCena(cena);
                    if (ilosc != 0)
                        i.SetIlosc(ilosc);
                }
                catch(GraPlanszowaException gpe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawne ograniczenie wiekowe, ilosc graczy lub czas trwania.");
                    System.out.println("Czas trwania musi byc wiekszy niz 0, ograniczenie wiekowe musi byc z przedzialu od 0 do 18, ilosc graczy w formacie min-max.");
                    System.out.println("");
                }
                catch(DataException de){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna data. Data musi byc w formacie dd-mm-rrrr");
                    System.out.println("Data musi byc wieksza od 1-1-1970r.");
                    System.out.println("");
                }
                catch(PrzedmiotException pe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Niepoprawna cena lub ilosc.");
                    System.out.println("Cena i ilosc musza byc wieksze od 0.");
                    System.out.println("");
                }
            }
            else
                czyZnalezionoGre += 1;
        }
        if(czyZnalezionoGre == liczbaGierPlanszowych)
			System.out.println("Nie znaleziono gry planszowej o podanym id.");
    }

    public static void UsunGrePlanszowa(){
        ArrayList<GraPlanszowa> gryPoUsunieciu = new ArrayList<GraPlanszowa>();
        System.out.println("Podaj id do usuniecia: ");
        int idDoUsuniecia = PodajPoprawnaLiczbe();
        int czyUsunietoGre = liczbaGierPlanszowych;

        for (GraPlanszowa i : gryPlanszoweMagazyn){
			if (i.GetId() != idDoUsuniecia){	
				gryPoUsunieciu.add(i);
				czyUsunietoGre -= 1;
			}
			else if (i.GetId() == idDoUsuniecia){
				System.out.println("Znaleziono gre planszowa: ");
				System.out.println(i.toString() + "\n");
            }
		}

        if ((czyUsunietoGre != 1) && (idDoUsuniecia != 0))
			System.out.println("Nie znaleziono gry planszowej o podanym id.");
        else{
            System.out.println("Usunieto gre planszowa.");
            gryPlanszoweMagazyn.clear();
		    gryPlanszoweMagazyn = gryPoUsunieciu;
            liczbaGierPlanszowych -= 1;
        }
    }

    public static void ZapiszGryPlanszoweDoPliku(){
        File gamesFile = new File("GryPlanszoweMagazyn.csv");
		String line = "";
        try{
            PrintWriter writer = new PrintWriter(gamesFile.getName(), "UTF-8");

            for (GraPlanszowa i : gryPlanszoweMagazyn){
				line = i.GetAutor() + "," + i.GetTytul() + "," + i.GetGatunek() + "," +
                                   i.GetOgraniczeniaWiekowe() + "," + i.GetDataWydania() + "," + i.GetCena() + "," + i.GetIlosc()  + "," + i.GetCzasTrwania() + "," + i.GetIloscGraczy();
				writer.println(line);
			}
            writer.close();
			System.out.println("Zapisano gry planszowe do pliku.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void SprzedajGrePlanszowa(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id gry planszowej do sprzedania");
        int idDoWyszukania = PodajPoprawnaLiczbe();

        int czyZnalezionoGre = 0;
        for (GraPlanszowa i : gryPlanszoweMagazyn){
            if(i.GetId() == idDoWyszukania){
                System.out.println("Znaleziono gre planszowa: ");
				System.out.println(i.toString() + "\n");
                System.out.println("Ile egzemplarzy sprzedac?");
                int ileSprzedac = PodajPoprawnaLiczbe();

                if (ileSprzedac < 0){
                    System.out.println("Ilosc do sprzedania nie moze byc mniejsza od 0.");
                    return;
                }


                try{
                    i.SetIlosc(i.GetIlosc() - ileSprzedac);
                }
                catch(PrzedmiotException pe){
                    System.out.println("");
                    System.out.println("Blad!");
                    System.out.println("Nie ma tylu egzemplarzy!");
                    System.out.println("");
                }
            }
            else
                czyZnalezionoGre += 1;
        }
        if(czyZnalezionoGre == liczbaGierPlanszowych)
			System.out.println("Nie znaleziono gry planszowej o podanym id.");	
    }

    public static void WyszukajGrePlanszowa(){
        System.out.println("Wprowadz co ma zostac wyszukane:    ");
		System.out.println("1. Autor");
		System.out.println("2. Tytul");
		System.out.println("3. Gatunek");
		System.out.println("Wprowadz dowolna liczbe aby anulowac");
        int coZnalezc = PodajPoprawnaLiczbe();

        if (coZnalezc < 0 || coZnalezc > 3){
            System.out.println("Podales niepoprawna opcje.");
            return;
        }

        System.out.println("Podaj fraze do wyszukania: ");
        Scanner scanner = new Scanner(System.in);
		String fraza = scanner.nextLine();
		fraza = fraza.toLowerCase();

        for (GraPlanszowa i : gryPlanszoweMagazyn){
			if ((coZnalezc == 1) && (i.GetAutor().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetOgraniczeniaWiekowe() + " " + i.GetCzasTrwania() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
			if ((coZnalezc == 2) && (i.GetTytul().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetOgraniczeniaWiekowe() + " " + i.GetCzasTrwania() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
			if ((coZnalezc == 3) && (i.GetGatunek().toLowerCase().contains(fraza)))
				System.out.println("\n" + i.GetId() + " " + i.GetAutor() + " " + i.GetTytul() + " " + i.GetGatunek() + " " + i.GetOgraniczeniaWiekowe() + " " + i.GetCzasTrwania() + " " + i.GetDataWydania() + " " + i.GetCena() + " " + i.GetIlosc());
		}
    }

    public static void DodajGryPlanszoweZPliku() throws KsiegarniaException{
        String line = "";  
		String splitBy = ",";  
	
		try{
			BufferedReader br = new BufferedReader(new FileReader("GryPlanszoweMagazyn.csv"));  
			while ((line = br.readLine()) != null)   
			{  
				liczbaGierPlanszowych += 1;
				String[] gra = line.split(splitBy);  
				GraPlanszowa nowaGra = new GraPlanszowa(liczbaGierPlanszowych, gra[0], gra[1], gra[2], Integer.parseInt(gra[3]), gra[4], Integer.parseInt(gra[5]), Integer.parseInt(gra[6]), Integer.parseInt(gra[7]), gra[8]);
				gryPlanszoweMagazyn.add(nowaGra);
			}  
		}
		catch(FileNotFoundException fnfe){
            System.out.println("");
            System.out.println("Blad!");
			System.out.println("Nie znaleziono pliku z danymi gier planszowych!");
            System.out.println("");
		}
        catch(NumberFormatException nfe){
            throw new KsiegarniaException();
        }
        catch(ArrayIndexOutOfBoundsException aie){
            throw new KsiegarniaException();
        }
		catch(IOException ioe){
			ioe.printStackTrace();  
		}
        catch(GraPlanszowaException gpe){
            System.out.println("");
            System.out.println("Blad w grach planszowych!");
            System.out.println("Blad przy wczytaniu ograniczenia wiekowego, czasu trwania lub liczby graczy!");
            System.out.println("");
        }
        catch(PrzedmiotException pe){
            System.out.println("");
            System.out.println("Blad w grach planszowych!");
            System.out.println("Blad przy wczytaniu ceny lub ilosci!");
            System.out.println("");
        }
        catch(MyExceptions me){
            System.out.println("");
            System.out.println("Wystapil blad w grach planszowych!!");
            System.out.println("");
        }
    }


   /*
        /////////////////////////

        Koniec Metod Gry Planszowe

        /////////////////////////
    */


   /*
        ////////////////////

        Start Metod Zbiorowe

        ////////////////////
    */

    public static void DodajTowarZPlikow(){
        try{
            DodajKsiazkiZPliku();
            DodajEbookiZPliku();
            DodajFilmyZPliku();
            DodajGryPlanszoweZPliku();
            DodajPlytyMuzyczneZPliku();	
        }catch(KsiegarniaException ke){
            System.out.println("");
            System.out.println("Blad przy wczytaniu plikow!");
            System.out.println("");
        }
        
	}

    public static void ZapiszStanMagazynuDoPlikow(){
        ZapiszKsiazkiDoPliku();
        ZapiszEbookiDoPliku();
        ZapiszFilmyDoPliku();
        ZapiszGryPlanszoweDoPliku();
        ZapiszPlytyMuzyczneDoPliku();
    }

    public static void WyswietlStanMagazynu(){
        WyswietlKsiazki();
        WyswietlEbooki();
        WyswietlFilmy();
        WyswietlGryPlanszowe();
        WyswietlPlytyMuzyczne();
    }

    /*
        //////////////////////

        Koniec Metod Zbiorowe

        //////////////////////
    */

    

    /*
        /////////////////////////////////////////////

        Start Metod Sprawdzajacych Poprawnosc Inputow

        /////////////////////////////////////////////
    */


    public static int PodajPoprawnaLiczbe(){
        Scanner scanner = new Scanner(System.in);
        boolean czyPoprawnyInt;
        int dummy = 0;

        do{
            czyPoprawnyInt = true;

            try{
                dummy = scanner.nextInt();
            }
            catch(InputMismatchException ime){
                System.out.println("Nie podales liczby. Wpisz ponownie liczbe.");
                scanner.nextLine();
                czyPoprawnyInt = false;
            }
        }while(czyPoprawnyInt == false);

        return dummy;
    }
    
    public static String PodajPoprawnaDate(boolean czyModyfikacja){
        Scanner scanner = new Scanner(System.in);
        boolean czyPoprawnaData;
        String dummy = "";

        do{
            czyPoprawnaData = true;

            try{
                dummy = scanner.nextLine();
                if (czyModyfikacja == true && dummy.equals("0")){
                    return dummy;
                }
                CData temp = new CData(dummy);
            }
            catch(DataException de){
                System.out.println("");
                System.out.println("Blad!");
                System.out.println("Data musi byc w formacie dd-mm-rrrr! Data musi byc wieksza niz 1-1-1970r.");
                System.out.println("Podaj date ponownie.");
                System.out.println("");
                czyPoprawnaData = false;
            }
        }while(czyPoprawnaData == false);

        return dummy;
    }

    public static float PodajPoprawnaLiczbeFloat(){
        Scanner scanner = new Scanner(System.in);
        boolean czyPoprawnyFloat;
        float dummy = 0.0f;

        do{
            czyPoprawnyFloat = true;

            try{
                dummy = scanner.nextFloat();
            }
            catch(InputMismatchException ime){
                System.out.println("Nie podales liczby. Wpisz ponownie liczbe.");
                scanner.nextLine();
                czyPoprawnyFloat = false;
            }
        }while(czyPoprawnyFloat == false);

        return dummy;
    }

    /*
        ///////////////////////////////////////////////

        Koniec Metod Sprawdzajacych Poprawnosc Inputow

        ///////////////////////////////////////////////
    */
}