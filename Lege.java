public class Lege implements Comparable<Lege> {
    protected String navn;
    protected IndeksertListe<Resept> utskrevneResepter;

    public Lege(String navn) {
        this.navn = navn;
        this.utskrevneResepter = new IndeksertListe<>();
    }

    public String hentNavn() {
        return navn;
    }

    public IndeksertListe<Resept> hentUtResepter() {
        return utskrevneResepter;
    }

    @Override
    public int compareTo(Lege annenLege) {
        return navn.compareTo(annenLege.hentNavn());
    }

    // Metode for å legge til en resept i listen over utskrevne resepter
    public void leggTilResept(Resept resept) {
        utskrevneResepter.leggTil(resept);
    }

    @Override
    public String toString(){
        return "Navn: " + navn; 
    }

    public Hviteresepter skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        Hviteresepter resept = new Hviteresepter(legemiddel, this, pasient, reit);
        leggTilResept(resept);
        return resept;
    }

    public MilResept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
        throw new UlovligUtskrift(this, legemiddel);
        }
        MilResept resept = new MilResept(legemiddel, this, pasient);
        leggTilResept(resept);
        return resept;
        }
    
        public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
            if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
                throw new UlovligUtskrift(this, legemiddel);
            }
            PResept resept = new PResept(legemiddel, this, pasient, reit);
            leggTilResept(resept);
            return resept;
        }
        
        public Blåresepter skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
            if (!(this instanceof Spesialist)) {
                throw new UlovligUtskrift(this, legemiddel);
            }
            Blåresepter resept = new Blåresepter(legemiddel, this, pasient, reit);
            leggTilResept(resept);
            return resept;
        }
    }

        
