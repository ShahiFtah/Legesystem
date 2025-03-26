abstract class Resept {
    private static int teller = 0;
    protected final int id;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;
//skriver opp instansene som oppgaven har sagt

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        this.id = teller++;
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
//Lager en kontruktør, slik at når brukeren kaller på resept, med info om legemiddel, lege, pasientid og reit, blir 
//dette lagret i hver sitt variabel. 
    }

    public int hentId() {
        return id;
    }
//returnerer id

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }
//returnerer legemiddel fra Legemiddel objekt

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public Pasient hentPasientId() {
        return pasient;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0){
            reit--;
            return true;
        }
        return false;
    }
//Lager en if-setning. hvis reit er større enn 0, tar vi bort en reit og returnerer true ellers false

    abstract public String farge();

    abstract public int prisAaBetale();
//lager to abstrakte metoder slik at subklassene kan bruke det

    @Override
    public String toString() {
        return "Id : " + id + ", Legemiddel: " + legemiddel.navn + ", Lege: " + utskrivendeLege.hentNavn() + ", pasientID: " + pasient + ", Reit: " + reit;
    }
//Lager en toString som returnerer info fra alle variablene.

}