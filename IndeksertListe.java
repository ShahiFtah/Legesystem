import java.util.Iterator;

class IndeksertListe <E> extends Lenkeliste<E> {
//lager IndeksterListe som en subklasse av Lenkeliste
    
    public void leggTil (int pos, E x) throws UgyldigListeindeks{
        Node ny = new Node(x); 
        if (0 > pos || pos > stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }

        else if (pos == 0) {
            ny.neste = start; //setter inn noden før start
            start = ny;
        }
        
        else {
            Node peker = start;
            for (int i = 0; i<pos-1; i++){
                peker = peker.neste;
            }
            ny.neste = peker.neste; 
            peker.neste = ny;
        }
        stoerrelse++;
    }
//Metoden over legger til elementer i gitt rekkefølge.
//Først begynner vi med å lage Noden, og gå gjennom if/else setningene
//Hvis posisjonen som er gitt ikke er i listen, sender vi beskjed
//ellers går den videre og sjekker om posisjonen er 0, slik at vi lett kan legge den til foran i listen
//Ellers går den videre. Her iterer vi gjennom listen til posisjonen som er gitt ved for-løkke
//som til slutt legger til elementet til riktig posisjon og øker stoerrelse
    
public void sett (int pos, E x) throws UgyldigListeindeks{
        if (pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        Node node = start;
        for (int i = 0; i < pos; i++){
            node = node.neste;
        }
        node.data = x;
    }
// i sett metoden sjekker jeg om posisjonen som er gitt er innenfor mengden
//videre iterer jeg gjennom listen til jeg kommer til posisjonen som er gitt, og endrer elementet med den som er gitt

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
//I hent metoden sjekekr jeg om posisjonen er innenfor mengden
//Hvis det er innenfor mengden, iterer jeg til gitt posisjon og returnerer ved å kalle på data
    public E fjern (int pos) throws UgyldigListeindeks {
        if (pos > 0 && pos < stoerrelse()){
            Node peker = start; 
            for (int i = 0; i<pos-1; i++){
                peker = peker.neste;
            }

            E data;
            data = peker.neste.data;
            peker.neste = peker.neste.neste;
            stoerrelse--;
            return data;
        }           
        throw new UgyldigListeindeks(pos); //skriv på en bedre måte
    }
    //i fjern metoden iterer jeg til gitt posisjon i parameteren
    //videre lager jeg data noden som skal fjernes, også fjerner jeg noden ved å hoppe over den
    //til slutt tar jeg bort én stoerrelse og returnerer data 

    @Override
    public Iterator<E> iterator() {
        return new IndeksertListeIterator();
    }

    private class IndeksertListeIterator implements Iterator<E> {
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