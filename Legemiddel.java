
abstract class Legemiddel { //tok bort abstrakt for jeg kunne ikke lage en objekt av Legemiddel
    private static int teller = 0;
    public final String navn;
    private int pris;
    public final double virkestoff;
    public final int id;
//I oppgaven sto det at instansene skal være public final unntatt pris.

    public Legemiddel(String navn, int pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.id = teller++;
    }
//Lager konstruktøren som tar inn parametere og gir instansverdiene en verdi
    //Ny:
    public int hentId() {
        return id;
    }
    //Ny:
    public String hentNavn(){
        return navn;
    }

    public int hentPris(){
        return this.pris;
    }
//returnerer pris
    //ny:
    public double hentVirkestoff(){
        return virkestoff;
    }

    public void settNyPris(int pris){
        this.pris = pris;
    }
//returnerer ny pris fra parameteren i metoden

    @Override
    public String toString() {
        return " Navn: " + navn + ", Id: " + id + ", Pris: " + pris + ", Virkestoff: " + virkestoff;
    }
//lager en toSting metode som returnerer info  om legemiddel


}




