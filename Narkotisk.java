
class Narkotisk extends Legemiddel{
   public final int styrke;
//lager instans styrke

    public Narkotisk(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }
//lager kontruktøren som tar inn variablene i legemiddel med super, i tillegg til å ta inn styrke

    @Override
    public String toString() {
        return super.toString() + ", Styrke: " + styrke;
    }
//kaller toString med super der jeg får ut info om legemiddelet i tillegg så legger jeg til styrke som returnerer alt + styrke

}

