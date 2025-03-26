class Hviteresepter extends Resept {

    public Hviteresepter(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
//lager en konstruktør som refererer til variablene i superklassen
    @Override
    public String farge() {
        return "hvit";
    }
//lager en override metode som returner fargen hvit

    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }
//lager en override metode som returnerer prisen til legemiddelet ved å kalle på hentPris på klassen Legemiddel
}