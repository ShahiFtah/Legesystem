import java.util.Iterator;

class Stabel<E> extends Lenkeliste<E> {
//lager en klasse som arver Lenkeliste

    @Override
    public void leggTil(E x){
        Node ny = new Node(x);
        if (start == null){
            start = ny;
            slutt = start;
        }
        else {
            ny.neste = start;
            start = ny;
        }
        stoerrelse++;
    }
    //I denne metoden gjør jeg nesten det samme som metoden i Lenkeliste
    //Her henter den parameteren og lager en Node.
    //hvis start er null betyr det at listen er som, så da legger vi den til foran som også er slutten
    //Ellers går den videre til else, som legger den til Noden først i listen ved at den får plassen start, og vi øker stoerrelse med 1

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
