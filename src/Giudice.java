import java.io.*;
import java.util.*;

/**
 * Classe Giudice che gestisce la gara.
 * Estende Thread per coordinare i thread degli Atleti.
 */
public class Giudice extends Thread {

    ArrayList<Atleta> Atleti = new ArrayList<>();
    ArrayList<Atleta> Podio = new ArrayList<>();
    ArrayList<Thread> threadAtleti = new ArrayList<>();
    private double LUNGHEZZAGARA;

    /** Gestore file per salvare la classifica */
    GestoreFile gf = new GestoreFile();
    Scanner scanner = new Scanner(System.in);

    public Giudice(double lunghezzaGara) {
        this.LUNGHEZZAGARA = lunghezzaGara;
    }

    public double getLUNGHEZZAGARA() { return LUNGHEZZAGARA; }
    public void aggiungimi(Atleta a) { Atleti.add(a); }

    public void inizio() {
        // countdown
        for (int i = 3; i > 0; --i) {
            System.out.println("Inizio in:" + i);
            try { sleep(1000); } catch (InterruptedException e) { throw new RuntimeException(e); }
        }

        // mostra se bevuto sospetto
        for(Atleta a : Atleti)
            if(a.bevutoSospetto) System.out.println(a.nome + " ha bevuto qualcosa di sospetto...");
        System.out.println("===============");

        for (Atleta a : Atleti) {
            threadAtleti.add(new Thread(a));
            threadAtleti.get(threadAtleti.size() - 1).start();
        }

        for (Atleta a : Atleti) {
            try { threadAtleti.get(threadAtleti.size() - 1).join(); } 
            catch (InterruptedException e) { throw new RuntimeException(e); }
        }
    }

    public synchronized void finito(Atleta a) {
        Podio.add(a);
        if (Podio.size() == Atleti.size()) fineGara();
    }

    /** Termina la gara, stampa il podio e chiede se salvarlo */
    public void fineGara() {
        StringBuilder sb = new StringBuilder();
        sb.append("gara terminata\n");
        for (int i = 0; i < Podio.size(); i++) {
            sb.append((i + 1) + "° in classifica: ").append(Podio.get(i).nome).append("\n");
        }
        System.out.println(sb.toString());

        System.out.print("Vuoi salvare il podio nel file? (sì/no): ");
        String risposta = scanner.nextLine();
        
        if (risposta.equalsIgnoreCase("si")) {
            gf.scriviFile(sb.toString());
            System.out.println("Podio salvato nel file.");
        } else {
            System.out.println("Podio non salvato.");
        }
    }
}
