class Blåresepter extends Resept{

    public Blåresepter(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
//lager en kontruktør som tar inn og gjør det mulig for klassen å bruke disse variablene

    @Override
    public String farge(){
        return "Blå";
    }
//Bruker override metode farge som lar underklassene implementere en metode fra superklassen. Returnerer blå

    @Override
    public int prisAaBetale() {
        int hentPris = legemiddel.hentPris();
        int betalingPris = hentPris/4; // 1/4 av prisen er det samme som 25% siden pasienten bare skal betale 25% som er 1/4
        int sum = (int) Math.round(betalingPris);
        return Math.max(sum, 0);
    }
//lager en override metode som regner ut 25% av prisen og returnerer det til brukeren
//Her kaller vi legemiddel hentPris som er en metode i klassen Legemiddel. 
}