class Koe<E> extends Lenkeliste<E>{
//Her arver vi Lenkeliste slik at Koe er en subklasse
    @Override
    public void leggTil(E x) {
        super.leggTil(x);
    }
//lager en metode som legger nye elementer. Her kaller jeg på leggTil i Lenkeliste klassen
    @Override
    public E fjern() {
        if (stoerrelse() != 0 || start != null){
            return super.fjern();
        }
        else { 
            throw new UgyldigListeindeks(stoerrelse());
        }    
    }
    //kan også kalle på super.fjern();
//Dette er nesten det samme som fjern metoden i superklassen. Den fjerner første element i listen
}
