import java.util.Iterator;
abstract class Lenkeliste <E> implements Liste<E> {
    protected Node start = null;
    protected Node slutt = null;
    protected int stoerrelse = 0;
//Skriver de ulike variablene som trengs for å utføre klassen
    protected class Node {
        E data;
        Node neste = null;
        Node(E data){
            this.data = data;
        }
    }

 //sjekk om du trenger å legge til egen klasse for iterator interface med metodene
    class LenkelisteIterator implements Iterator<E>{
        private Node tilstede = start;
//kanskje @override?
        public boolean hasNext(){
            if(tilstede !=null){
                return true;
            }
            else {
                return false;
            }
        }
//kanskje override?
        public E next() {
            E data = tilstede.data;
            tilstede = tilstede.neste;
            return data;
        }
    }
//iterator metode som returnerer ny objekt
    public Iterator<E> iterator(){
        return new LenkelisteIterator();
    }




//lager en kontruktør til Node, slik at når man kaller noden
//lagrer vi data og Node neste i Nodene som blir kalt opp. 
    @Override
    public int stoerrelse() {
        return stoerrelse;
    }
//lager en metode som returnerer stoerrelse
    @Override
    public void leggTil(E x) {
        Node ny = new Node(x);
        if (start == null){
            start = ny;
            slutt = ny;
        }
        else {
            Node slutt = start;
            while (slutt.neste !=null){
                slutt = slutt.neste;
            }

            slutt.neste = ny;
        }
        
        slutt = ny;
        stoerrelse++;
    }
//lager en metode leggTil. her vil metoden kjøre gjennom en if-setning
//hvis start er null(er tom) legger vi den til som start og slutt, siden det er den eneste Noden tilstede
//hvis ikke gpr den videre og iterer gjennom Noden fra start til slutt, helt til den finner en plass med null
//som tilslutt legger til Noden inn i riktig plass, som er helt bakerst, og øker stoerrelse med 1 
    @Override
    public E hent() throws UgyldigListeindeks {
        if (start == null){ //kan skrive uten denne if metoden, med eller uten ugyldigListeindeks
            throw new UgyldigListeindeks(0);
        }
        else {
            return start.data;
        }
    }
//hent metoden returnerer dataen til den første noden. Jeg la til en if setning her
//man trenger engentlig ikke if setningen, men jeg la til for sikkerhet dersom man bruker hent med tom lenkeliste
//Det vil fungere uten if-setningen. Der var ikke spesifisert hvordan man skulle gjøre det
    @Override
    public E fjern() throws UgyldigListeindeks{
        if (start == null){ //kan skrive uten denne if metoden, med eller uten ugyldigListeindeks
            throw new UgyldigListeindeks(0);
        }
        else { 
            E info = start.data;
            start = start.neste; //sjekk om dette er riktig
            stoerrelse -=1;
            return info;
        }
    }
//Her fjør jeg det samme. jeg sjekker om det finnes en Node ved å se om start er tom eller ikke
//hvis den ikke har en node, får den feilmelding ellers går den videre henter ut startverdiern og endrer start til start.neste og tar bort 1 i stoerrelsen

    /*@Override
    public String toString() {
        
        Node node = start;
        String svar = "";
        while (node != null){
            svar += node.data;
            node = node.neste;
        }
        return svar;
    }*/
//Var litt usikker på hvilke metode jeg skulle buke til toString() så jeg beholdt begge metodene men bruker bare én
    @Override
    public String toString() {
        Node node = start;
        String svar = "[ ";
        while (node != null){
            svar += node.data + " ";
            node = node.neste;
        }
        String slutt = "]";
        String ferdig = "Elementene i Nodene er " + svar + slutt + " i denne rekkefølgen";
        return ferdig;
    }
//Her lager jeg en type "liste" hvor jeg iterer gjennom Nodene og henter data og legger til i strengen.
}
   

