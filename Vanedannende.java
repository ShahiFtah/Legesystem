
class Vanedannende extends Legemiddel{
    public final int styrke;
//Lager en instans styrke

    public Vanedannende(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }
//Sriver en kontruktær med super, som tar inn og gjør det brukbart å bruke variablene i Legemiddel + legger til styrke

    @Override
    public String toString() {
        return super.toString() + ", Styrke: " + styrke;
    }
//Lager en toString metode som returnerer Legemiddel-info og styrke på vanedannende legemiddelet

}