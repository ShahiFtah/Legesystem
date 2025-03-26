class MilResept extends Hviteresepter {

    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, 3);
    }
//lager en kontruktør som tar ibruk variablene ved bruk av super()

    @Override
    public String farge() {
        return "hvit";
    }
//lager en override metode som returnerer fargen til hvit

    @Override
    public int prisAaBetale() {
        return 0;
    }
//lager en override metode som returnerer pris som er 0kr, pga. vernepliktige i tjeneste
}