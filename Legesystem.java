import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Legesystem {

    protected static IndeksertListe<Pasient> listePasient = new IndeksertListe<>(); 
    protected static IndeksertListe<Legemiddel> listeLegemiddel = new IndeksertListe<>();
    protected static Prioritetskoe<Lege> listeLege = new Prioritetskoe<>();
    protected static IndeksertListe<Resept> listeResept = new IndeksertListe<>();

    private static void LesFraFil(String filnavn) throws FileNotFoundException { 

        try {
            File fil = new File(filnavn);
            Scanner scanner = new Scanner(fil);
            
            while (scanner.hasNextLine()){
                String linje = scanner.nextLine();
                String[] data = linje.split(",");
                //Under - samler pasientene
                if (data.length == 2 && data[1].length() == 11 ){
                    Pasient pasient = new Pasient(data[0], data[1]);
                    listePasient.leggTil(pasient);
                }
                //Under - samler leger/spesialist
                else if (data.length == 2) {
                    if (data[1] == "0"){
                        Lege lege = new Lege(data[0]);
                        listeLege.leggTil(lege);
                    }
                    else {
                        Lege lege = new Spesialist(data[0], data[1]);
                        listeLege.leggTil(lege);
                    }
                }
                //Under- samler Legemiddler
                else if(data.length == 4 || data.length == 5){
                
                    if (data[1].equals("narkotisk")){
                        Legemiddel narkotisk = new Narkotisk (data[0], Integer.parseInt(data[2]), Double.parseDouble(data[3]), Integer.parseInt(data[4]));
                        listeLegemiddel.leggTil(narkotisk);
                    }

                    else if (data[1].equals("vanedannende")){
                        Legemiddel vanedannende = new Vanedannende (data[0], Integer.parseInt(data[2]), Double.parseDouble(data[3]), Integer.parseInt(data[4]));
                        listeLegemiddel.leggTil(vanedannende);
                    }

                    else if (data[1].equals("vanlig")){
                        Legemiddel vanlig = new Vanlig (data[0], Integer.parseInt(data[2]), Double.parseDouble(data[3]));
                        listeLegemiddel.leggTil(vanlig);
                    }
                
                    else {
                        if (data.length == 4 || data.length == 5){
                            Legemiddel legemiddel = null;
                            Lege lege = null;
                            Pasient pasient = null;

                            for (Legemiddel m : listeLegemiddel) {
                                if (m.id == Integer.parseInt(data[0])){
                                    legemiddel = m;
                                    break;
                                }
                            }

                            for (Lege m : listeLege) {
                                if (m.hentNavn().equals(data[1])){
                                    lege = m;
                                    break;
                                }
                            }

                            for (Pasient m : listePasient) {
                                if (m.hentId() == Integer.parseInt(data[2])){
                                    pasient = m;
                                    break;
                                }
                            }

                            if(data[3].equals("blaa")){
                                Resept blaaresept = new Blåresepter(legemiddel, lege, pasient, Integer.parseInt(data[4]));
                                listeResept.leggTil(blaaresept);
                                pasient.leggTilResept(blaaresept); 
                            }

                            else if (data[3].equals("hvit")){
                                Resept hvitresept = new Hviteresepter(legemiddel, lege, pasient, Integer.parseInt(data[4]));
                                listeResept.leggTil(hvitresept);
                                pasient.leggTilResept(hvitresept); 
                            }

                            else if (data[3].equals("militaer")){
                                Resept milresept = new MilResept(legemiddel, lege, pasient); //har egen reit på 3
                                listeResept.leggTil(milresept);
                                pasient.leggTilResept(milresept); 
                            }

                            else if (data[3].equals("p")){
                                Resept presept = new PResept(legemiddel, lege, pasient, Integer.parseInt(data[4]));
                                listeResept.leggTil(presept);
                                pasient.leggTilResept(presept); 
                            }
                        }
                    }  
                }
            }
            scanner.close(); //lukker scanner
        }catch (FileNotFoundException e) {System.out.println("Ugyldig fil -> Filen finnes ikke");}
    }       

    public static void main(String[] args) throws FileNotFoundException {

        try{ 
            LesFraFil("legedata.txt"); //SKriv plasseringen på filen
        } catch (FileNotFoundException e){
            System.out.println("Kunne ikke finne fil");
        }
        System.out.println("Velkommen til legesystemet!");

        Scanner sc = new Scanner(System.in);
        String E = "start";
        while (!E.equals("q")){
            System.out.println(" \nVelg et alternativ: ");
            
            System.out.println("1: Skrive ut en fullstendig oversikt over pasienter, leger, legemidler og resepter");
            System.out.println("2: Oprette og legge til nye elementer i systemet");
            System.out.println("3: Bruke en gitt resept fra listen til en pasient");
            System.out.println("4: Skrive ut forskjellige former for statistikk");
            System.out.println("5: Skrive alle data til fil");
        
            System.out.println("q: Avslutt");
            System.out.println("");
            System.out.println("Ditt valg: ");

            E = sc.next();
            if (E.equals("1")) {
                System.out.println ("Skriver ut valgt \n ");
                skrivUtOversikt();
            }
            else if (E.equals("2")){
                System.out.println("Opprette og legg til nye elementer valgt \n");
                leggTilNyeElementer();
            }
            else if (E.equals("3")){
                System.out.println("Bruk en gitt resept valgt \n");
                brukResept();
            }
            else if (E.equals("4")){
                System.out.println("Skriv ut statistikk valgt \n" );
                skrivUtStatistikk();
            }
            else if(E.equals("5")){
                System.out.println("Skriv ut data til fil valgt \n");
                dataTilFil();
            }
        }
        System.out.println("Systemet er avsluttet");
    }
//Skriver ut all info NR1:
    private static void skrivUtOversikt(){
        //skriv pasienter
        System.out.println("Pasienter: ");
        skrivPasient();  
        System.out.println("");
        //Skriv legemidler 
        System.out.println("Legemidler: ");
        skrivLegemidler();
        System.out.println("");
        //Skriv ut Leger
        System.out.println("Leger: ");
        skrivLeger();
        System.out.println("");
        //Skriv ut resepter
        System.out.println("Resepter: ");
        System.out.println("");
        for (Resept t : listeResept){
            String string;
            if (t instanceof Hviteresepter){
                string = "hvit";
            }
            else if (t instanceof Blåresepter){
                string = "blå";
            }
            else if (t instanceof MilResept){
                string = "militærresept";
            }
            else if (t instanceof PResept){
                string = "p-resept";
            }
            else {
                string = "ukjent resept";
            }
            System.out.println("Resept ID: " + t.hentId() + ", Legemiddel: " + t.hentLegemiddel().hentId() + ", Lege: " + t.hentLege().hentNavn() + ", Pasient: " + t.hentPasientId().hentId() + ", Reit: " + t.hentReit() + " " + string);
        }    
    } 
    
//Skriver ut pasient
    private static void skrivPasient(){
        System.out.println("");
        int tall = 0;
        for (Pasient m : listePasient){
            System.out.println(tall + ": " + m.toString()); //enklere 
            //System.out.println(m.hentId() + ", " + m.hentNavn() + ", " + m.hentfodselsnummer());  
            tall++;
        }System.out.println();
    }
//Skriver ut legemidler
    private static void skrivLegemidler() {
        System.out.println("");
        for (Legemiddel o : listeLegemiddel){
            if (o instanceof Vanlig){
                System.out.println(o.toString() + ", dette er vanlig");
                System.out.println(" ");
            } 
            else if (o instanceof Narkotisk){
                Narkotisk n = (Narkotisk)o;
                System.out.println(n.toString() + ", dette er narkotisk");
                System.out.println(" ");
            }
            else if (o instanceof Vanedannende){
                Vanedannende n = (Vanedannende)o;
                System.out.println(n.toString() + ", dette er vanedannende");
                System.out.println(" ");
            }
        } 
    }
//Skriver ut lege
    private static void skrivLeger(){
        System.out.println("");
        for (Lege u : listeLege){
            if (u instanceof Spesialist){
                System.out.println(u.toString());
            }
            else if( u instanceof Lege){
                System.out.println(u.toString());
            }
        }
    }





//Opprette og legg til nye elementer valgt NR.2
    private static void leggTilNyeElementer(){
        System.out.println("Her kan du legge til elementer!");
        System.out.println("");

        Scanner valg = new Scanner(System.in);

        String Brukerverdi = "start";

        while (!Brukerverdi.equals("q")){ 
            System.out.println("Velg hva du vil legge til: ");
            System.out.println("1: Pasient");
            System.out.println("2: Lege");
            System.out.println("3: Legemiddel");
            System.out.println("4: Resept");
            System.out.println("q : Tilbake");
            
            System.out.println("Skriv inn ditt valg:");
            Brukerverdi = valg.nextLine();//kan bruke next() eller nextInt()
            
            if (Brukerverdi.equals("1")){
                System.out.println("Legger til ny pasient");
                System.out.println("");
                System.out.println("Skriv inn navn: ");
                    String navn = valg.nextLine();

                    System.out.println("Skriv inn foedselsnummer: ");
                    String foedselsnummer = valg.nextLine();

                    Pasient nyPasient = new Pasient(navn, foedselsnummer);
                    listePasient.leggTil(nyPasient);
                    System.out.println("Du har lagt til pasienten: " + nyPasient);
                    System.out.println("");
            }
            else if (Brukerverdi.equals("2")){
                System.out.println("Legger til ny Lege");
                System.out.println();
                System.out.println("Skriv inn navn: ");
                String navn = valg.nextLine();

                System.out.println("Er legen en spesialist? (j/n)");
                String spesialistSvar = valg.nextLine();

                if(spesialistSvar.equalsIgnoreCase("j")){
                    System.out.println("Skriv inn kontroll Id: ");
                    String kontrollId = valg.nextLine();
                    Lege nyLege = new Spesialist(navn, kontrollId);
                    listeLege.leggTil(nyLege);
                    System.out.println("Du har lagt til spesialisten: " + nyLege.hentNavn());
                }
                else{
                    Lege nyLege = new Lege(navn);
                    listeLege.leggTil(nyLege);
                    System.out.println("Du har lagt til Legen " + nyLege.hentNavn());
                }
                System.out.println("");
            }

            else if (Brukerverdi.equals("3")){
                // Opprett legemiddel
                System.out.println("Skriv inn navn paa legemiddel: ");
                String navn = valg.nextLine();

                System.out.println("Skriv in type (vanlig, vanedannende, narkotisk)");
                String type = valg.nextLine();

                System.out.println("Skriv inn prisen: ");
                int pris = valg.nextInt();

                System.out.println("Skriv inn antall milligram virkestoff: ");
                double virkestoff = valg.nextDouble();

                Legemiddel nyttLegemiddel = null;

                if(type.equalsIgnoreCase("narkotisk")){
                    System.out.println("Skriv inn narkotisk styrke: ");
                    int narkotiskStyrke = valg.nextInt();
                    nyttLegemiddel = new Narkotisk(navn, pris, virkestoff, narkotiskStyrke);
                }

                else if(type.equalsIgnoreCase("vanedannende")){
                    System.out.println("Skriv inn vanedannende styrke: ");
                    int vanedannendeStyrke = valg.nextInt();
                    nyttLegemiddel = new Vanedannende(navn, pris, virkestoff, vanedannendeStyrke);   
                }

                else if(type.equalsIgnoreCase("vanlig")){
                    nyttLegemiddel = new Vanlig(navn, pris, virkestoff);
                }

                else{
                    System.out.println("Ugyldig valg!");
                }

                listeLegemiddel.leggTil(nyttLegemiddel);
                System.out.println("Du har lagt til legemiddelet: " + nyttLegemiddel);
                System.out.println("");
            }

            else if (Brukerverdi.equals("4")){
                //Opprett resept
                System.out.println("Hvilken pasient skal resepten være til? (Skriv heltall)");
                int teller = 0;
                for (Pasient p : listePasient){ //eller kan du kalle på metoden skrivUtPasient()
                    System.out.println(teller + ": " + p);
                    teller++;
                }
                int pasientValg = valg.nextInt();
                valg.nextLine();
                Pasient valgPasient = listePasient.hent(pasientValg); //-1

                System.out.println("Hvilken lege skal skrive ut resepten? (Skriv heltall)");
                int tall = 0;
                for(Lege l : listeLege){ //her kan du også bruke hentLege metoden
                    System.out.println(tall + ": " + l);
                    tall++;
                }
                int legeValg = valg.nextInt();
                valg.nextLine(); 
                Lege valgtLege = listeLege.hent(legeValg ); // -1

                System.out.println("Hvilket legemiddel skal resepten vaere paa?");
                int tall1 = 0;
                for (Legemiddel lm : listeLegemiddel){//her kan jeg også bruke hentLegemiddelmetoden
                    System.out.println(tall1 + ": " + lm);
                    tall1++;
                }
                int legemiddelValg = valg.nextInt();
                valg.nextLine();
                Legemiddel valgtLegemiddel = listeLegemiddel.hent(legemiddelValg ); //-1

                System.out.println("Hvor mange ganger skal resepten brukes? (Reit)");
                int reit = valg.nextInt();
                valg.nextLine();

                // Sjekker hva slags resept det skal vaere
                try {
                System.out.println("Hva slags resept skal det vaere? (blaa, p, hvit, mil)");
                String reseptValg = valg.nextLine();
                if(reseptValg.equals("blaa")){
                    Resept nyResept = valgtLege.skrivBlaaResept(valgtLegemiddel, valgPasient, reit);
                    listeResept.leggTil(nyResept);
                    System.out.println("Du har lagt til resepten: " + nyResept);
                }
                else if(reseptValg.equals("p")){
                    Resept nyResept = valgtLege.skrivPResept(valgtLegemiddel, valgPasient, reit);
                    listeResept.leggTil(nyResept);
                    System.out.println("Du har lagt til resepten: " + nyResept);
                }
                else if(reseptValg.equals("hvit")){
                    Resept nyResept = valgtLege.skrivHvitResept(valgtLegemiddel, valgPasient, reit);
                    listeResept.leggTil(nyResept);
                    System.out.println("Du har lagt til resepten: " + nyResept);
                }
                else if(reseptValg.equals("mil")){
                    Resept nyResept = valgtLege.skrivMilResept(valgtLegemiddel, valgPasient);
                    listeResept.leggTil(nyResept);
                    System.out.println("Du har lagt til resepten: " + nyResept);
                }
                else{
                    System.err.println("Du skrev inn noe feil!");
                }

                } catch(Exception e){ 
                    System.out.println("Ugyldig");
                }
            }
        }
    }

//Bruk en gitt resept valgt NR.3
    private static void brukResept(){
        Scanner sc = new Scanner(System.in);
        String brukersvar;
        int intTall;

        System.out.println("Bruk av resept er tilgjengelig");
        System.out.println("Hvilken pasient vil du se resepter for?");
        skrivPasient();

        System.out.println("Skriv id-nr her: ");
        brukersvar = sc.next();

        try {
            intTall = Integer.valueOf(brukersvar);
        } catch (Exception e) {
            System.out.println(brukersvar + " er ikke gyldig id");
            return;
        }

        Pasient valgtpasient = null; 
        for (Pasient pasient : listePasient) {
            if (pasient.hentId() == intTall){
                System.out.println("Valgt pasient: " + pasient.hentNavn() + " ( "+ pasient.hentfodselsnummer() + " )" );
                valgtpasient = pasient; 
            }   
        }//sjekk om du trenger if-setningen under her:
        if (valgtpasient == null){
            System.out.println("Fant ikke pasienten med id " + intTall);
            return;
        }

        System.out.println(" ");

        System.out.println("Hvilken resept vil du bruke: ");

        int teller = 0;//
        IndeksertListe<Resept> h = new IndeksertListe<>(); 
        for (Resept resept : listeResept) {
            if (resept.hentPasientId().hentId() == intTall){
                System.out.println(teller + ": " + resept.hentLegemiddel().hentNavn() + "  (" + resept.hentReit() + " reit )"); 
                h.leggTil(teller, resept);//
                teller++;//
            } //skriver ut legemiddelet og reit og legger det i en liste
            
        }
        
//Sjekker om brukeren som velger resepten har gyldig nr
        System.out.println("\nVelg resept: ");
        brukersvar = sc.next();
        try {
            intTall = Integer.valueOf(brukersvar);
        } catch (Exception e) {
            System.out.println("Ugyldig valg -> " + brukersvar);
            return;
        }
        Resept resept;//
        try { //her lager vi en ny resept objekt, som henter ut gitt plassering på hvilke legemiddel
            resept = h.hent(intTall);
        } catch (Exception e)  {
            System.out.println("Det finnes ikke valgt: " + intTall);
            return;
        }
        if (resept.bruk()){
            System.out.println("Resept er brukt du har " + resept.hentReit() + " reit igjen på " + resept.hentLegemiddel().hentNavn());
        } else {
            System.out.println("Kunne ikke bruke resept på" + resept.hentLegemiddel().hentNavn() + " ( Ingen gjenværende reit)");
        }
    }


    private static void skrivUtStatistikk(){
        System.out.println("\nHvilken statistikk vil du se?");
                System.out.println("1: Total antall utskrevende resepter paa vanedannende legemidler");
                System.out.println("2: Totalt antall utskrevende resepter paa narkotiske legemidler");
                System.out.println("3: Statistikk om mulig misbruk av narkotika");

                Scanner scanner = new Scanner(System.in);
                String valg4 = scanner.nextLine();

                // Antall resepter Vanedannende
                if (valg4.equals("1")){
                    int teller = 0;
                    for(Resept r : listeResept){
                        if(r.hentLegemiddel() instanceof Vanedannende){
                            teller++;   
                        }
                    }
                    System.out.println("Totalt antall utskrevende resepter pa vanedannende legemiddel: " + teller);
                }

                // Antall resepter narkotisk
                else if(valg4.equals("2")){
                    int teller = 0;
                    for(Resept r : listeResept){
                        if(r.hentLegemiddel() instanceof Narkotisk){
                            teller++;
                        }
                    }
                    System.out.println("Totalt antall utskrevende resepter paa Narkotisk legemiddel " + teller);

                }
                else if(valg4.equals("3")){
                    // Statiskk om mulig misbruk av narkotika
                    System.out.println("Navnene paa alle leger som har skrevet minst en resept paa narkotiske legemiddler og antall slike resepter per lege:");
                    IndeksertListe<Lege> legerMedNarkotiskeResepter = new IndeksertListe<>();
                    for(Resept r : listeResept){
                        if(r.hentLegemiddel() instanceof Narkotisk){
                            Lege reseptLege = r.hentLege();
                            boolean legenFinnes = false;
                            for(int i = 0; i < legerMedNarkotiskeResepter.stoerrelse(); i++){
                                if(legerMedNarkotiskeResepter.hent(i).equals(reseptLege)){
                                    legenFinnes = true;
                                    break;
                                }
                            }
                            if(!legenFinnes){
                                legerMedNarkotiskeResepter.leggTil(reseptLege);
                            }
                            reseptLege.leggTilResept(r);
                        }
                    }
                    for(Lege l : legerMedNarkotiskeResepter){
                        System.out.println(l.hentNavn() + " har " +l.hentUtResepter().stoerrelse() + " resepter med narkotisk legemiddel.");
                    }
                    // Pasienter
                    System.out.println("\nNavnene paa alle pasienter som har minst en gyldig resept paa narkotiske legemiddler og antallet per pasient: ");
                    IndeksertListe<Pasient> pasienterMedNarkotiskeResepter = new IndeksertListe<>();
                    for(Resept r : listeResept){
                        if(r.hentLegemiddel() instanceof Narkotisk){
                            Pasient reseptPasient = r.hentPasientId();
                            boolean pasientFinnes = false;
                            for(int i = 0; i < pasienterMedNarkotiskeResepter.stoerrelse(); i++){
                                if(pasienterMedNarkotiskeResepter.hent(i).equals(reseptPasient)){
                                    pasientFinnes = true;
                                    break;
                                }
                            }
                            if(!pasientFinnes){
                                pasienterMedNarkotiskeResepter.leggTil(reseptPasient);
                            }
                            reseptPasient.leggTilResept(r);
                        }
                    }
                    for(Pasient p : pasienterMedNarkotiskeResepter){
                        System.out.println(p.hentNavn() + " har " + p.hentResepter().stoerrelse() + " resepter med narkotisk legemiddel.");
                    }

                }
                else{
                    System.out.println("Ugyldig input!");
                }
    }
    
    private static void dataTilFil() throws FileNotFoundException {
        PrintWriter skriver = new PrintWriter("NY_legedata.txt");
       
       //Skriver inn pasienter
       skriver.println("# Pasienter (navn, fnr)");
        for (Pasient pasient : listePasient){
            String navn = pasient.hentNavn();
            String foedselsnr = pasient.hentfodselsnummer();
            String utskrift = navn + ", " + foedselsnr;
            skriver.println(utskrift);
        }
        skriver.println();
       
        //Skriver inn alle legemidler i dokumentet
        skriver.println("# Legemidler (navn, type, pris, virkestoff [, styrke])");
        for (Legemiddel legemiddel : listeLegemiddel){
            String navn = legemiddel.hentNavn();
            String type = legemiddel.getClass().getSimpleName();
            int pris = legemiddel.hentPris();
            double virkestoff = legemiddel.hentVirkestoff();
            String utskrift = navn + ", " + type + ", " + pris + ", " + virkestoff;  

            if (legemiddel instanceof Narkotisk){
                Narkotisk narkotisk = (Narkotisk)legemiddel;
                int styrke = narkotisk.styrke;
                utskrift += ", " + styrke;
            }
            else if (legemiddel instanceof Vanedannende){
                Vanedannende vanedannende = (Vanedannende)legemiddel;
                int styrke = vanedannende.styrke;
                utskrift += ", " + styrke;
            }
            else if (legemiddel instanceof Vanlig){
                //har ingen styrke, så den kjører videre
            }

            skriver.println(utskrift);
        }
        skriver.println();

        //Skriver inn leger
        skriver.println("# Leger (navn, kontrollnr / 0 hvis ingen kontrollnr)");
        for (Lege lege : listeLege){
            String navn = lege.hentNavn();
            String utskrift = navn;
            if (lege instanceof Spesialist){
                Spesialist spesialist = (Spesialist)lege;
                String kontrollnr = spesialist.hentKontrollkode();
                utskrift += ", " + kontrollnr;
            }
            skriver.println(utskrift);
        }
        skriver.println();

        //Skriver inn resepter
        skriver.println("# Resepter (legemiddelNummer, legeNavn, pasientId, type, [reit])");
        for (Resept resept : listeResept){
            int LegemiddelID = resept.hentLegemiddel().hentId();
            String legenavn = resept.hentLege().hentNavn();
            int pasientID = resept.hentPasientId().hentId();
            int reit = resept.hentReit();
            String type = "";

            if (resept instanceof MilResept){
                type = "militaer";
            }
            else if (resept instanceof Blåresepter){
                type = "blaa";
            }
            else if (resept instanceof Hviteresepter){
                type = "hvit";
            }
            else if (resept instanceof PResept){
                type = "p";
            }
            
            String utskrift = LegemiddelID + ", " + legenavn + ", " + pasientID + ", " + type + ", " + reit;
            skriver.println(utskrift);
        }
            skriver.println();
            skriver.close();
            System.out.println("Du har naa skrevet alt data til en fil!");
   }
}