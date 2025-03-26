class Spesialist extends Lege implements Godkjenningsfritak {
    protected String kontrollkode;
//lager en instans kontrollkode
//Vi har i tillegg implementert Godkjenningsfritak på metoden hentKontrollkode inne på Godkjenningsfritak.java

    public Spesialist(String navn, String kontrollkode){
        super(navn);
        this.kontrollkode = kontrollkode;
//kontruktøren tar inn parameterene og gir verdi til super() og this.kontrollkode
    }
    
    public String hentKontrollkode() {
        return kontrollkode;
    }
//returnerer kontrollkode

    @Override
    public String toString(){
        return super.toString() + " Kontrollkode: " + kontrollkode;
    }
//override metoden fra superklassen Lege. Kaller på toString med super + legger til variabel kontrollkode.
}