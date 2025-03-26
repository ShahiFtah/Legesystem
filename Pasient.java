class Pasient {
    protected String navn;
    protected String fodselsnummer;
    private static int teller;
    protected final int pasientId;
    protected IndeksertListe<Resept> resepter = new IndeksertListe<>(); 
    

    public Pasient(String navn, String fodselsnummer){
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        this.pasientId = teller++;
    }    

    public int hentId(){
        return pasientId;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentfodselsnummer(){
        return fodselsnummer;
    }

    public void leggTilResept(Resept resept){
        resepter.leggTil(resept); //bruk leggTil metodene i stabel eller andre klassene
    }

    public Lenkeliste<Resept> hentResepter(){
        return resepter;
    }

    public String toString() {
        return "Pasient. Navn: " + navn + " , Fødselsnummer: " + fodselsnummer + " , Pasient: " + pasientId + ", Respeter: " + resepter.stoerrelse();
    }



}