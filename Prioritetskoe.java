import java.util.Iterator;

class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E>{
//Her er Prioritetskoe en subklasse av Lenkeliste.
//Vi har også brukt extends som hentes fra java biblioteket som angir at noe er sammenlignbart
    @Override
    public void leggTil(E x) {
        Node node = new Node(x);
        if (start == null || stoerrelse() == 0 || x.compareTo(start.data) <=0) {
        node.neste = start;
        start = node;
        stoerrelse++;
            //super.leggTil(x); kunne brukt denne
        }
        else {
        Node begynnelse = start;
        while (begynnelse.neste != null && x.compareTo(begynnelse.neste.data) > 0){
            begynnelse = begynnelse.neste;
        }
        node.neste = begynnelse.neste;
        begynnelse.neste = node;
        stoerrelse++;
        }
    }
    //i metoden leggTil legger jeg til elementene i sortert rekkefølge, fra minst til størst
    //Her går metoden gjennom if setninger. den første sjekekr om det ikke finnes noe i listen. 
    //hvis det ikke finnes noe legger den til elementet først i listen og øker stoerrelse. her kunne jeg egentlig kalt super.leggTil
    //Videre bruker jeg en while løkke slik at den kan gå gjennom hele listen fra start til slutt, og se om elementet som er gitt er større enn element-dataen listen peker er på -
    //mens den går gjennom elementene. Videre legger vi elementen i riktig plass og flytter resten nedover, og øker stoerrelse med 1

    @Override
    public E hent(){
        //if(stoerrelse() == 0 || start == null){
        //    return null;
        //}
        //else {
        //    return start.data;
        //} 
        //kunne skrevet det over, men tenkte det var lettere å kalle på metoden
        return super.hent();
    }   
    //her skrev jeg først opp hele metoden for å hente ut det minste elementet
    //men så kom jeg på at det minste elementet faktisk er det første elementet, så da 
    //kan jeg bare kalle på super.hent() siden den tar ut der første elementet som også er det minste i dette tilfellet    
    
    @Override
    public E fjern(){
        return super.fjern();
    }
//Siden metoden hent() og metoden fjern() gjør akkurat det samme som metodene Lenkeliste
//tenkte jeg at jeg bare kunne kalle dem opp, siden de henter og sletter det "første" elementet i listen, 
//som også egentlig er den minste elementen. 
    
//PS jeg legger til en metode som gjør det mulig for lege å hente ut objekt i gitt posisjon
public E hent (int pos) throws UgyldigListeindeks {
    if (pos < 0 || pos >= stoerrelse()){
        throw new UgyldigListeindeks(pos);
    }

    Node peker = start;
    for (int i = 0; i < pos; i++){
        peker = peker.neste;
    }
    return peker.data;
}

@Override
public Iterator<E> iterator() {
    return new PrioritetskoeIterator();
}

private class PrioritetskoeIterator implements Iterator<E> {
    private Node current = start;

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        E data = current.data;
        current = current.neste;
        return data;
    }
}

}   