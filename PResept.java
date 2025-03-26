class PResept extends Hviteresepter {
    public static final int rabatt = 108;
//lager en instans rabatt på 108 kr

    public PResept( Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
//kontruktøren tar inn relevant data fra Hviteresepter som igjen er hentet fra Resepter ved bruk av super()
    }

    @Override
    public String farge() {
        return "hvit";
    }
//Returnerer farge hvit for hvilke type resept det er

    @Override
    public int prisAaBetale() {
        int pris = legemiddel.hentPris() - rabatt;
        return Math.max(pris, 0);
    }
//Henter prisen fra legemiddel ved å kalle på hentPris fra klassen Legemiddel og trekker fra 108kr fra prisen
//og returnerer dette

}