import java.io.*;
import java.util.*;

public class Giudice extends Thread {
    ArrayList<Atleta> Atleti = new ArrayList<>();
    ArrayList<Atleta> Podio = new ArrayList<>();
    ArrayList<Thread> threadAtleti = new ArrayList<>();
    private double LUNGHEZZAGARA;  // La lunghezza della gara
    GestoreFile gf = new GestoreFile();
    Scanner scanner = new Scanner(System.in); // Scanner per leggere la risposta dell'utente

    public Giudice(double lunghezzaGara) {
        this.LUNGHEZZAGARA = lunghezzaGara;
    }

    public double getLUNGHEZZAGARA() {
        return LUNGHEZZAGARA;
    }

    // Metodo per aggiungere un atleta alla gara
    public void aggiungimi(Atleta a) {
        Atleti.add(a);
    }

    public void inizio() {
        for (int i = 3; i > 0; --i) {
            System.out.println("Inizio in:" + i); // Countdown
            try {
                sleep(1000); // Pausa di 1 secondo
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Mostra se atleta è doppato
        for(Atleta a : Atleti)
            if(a.bevutoSospetto) System.out.println(a.nome + " ha bevuto qualcosa di sospetto!");
        System.out.println("===============");  // Per separare l'output

        // Avvia il thread per ogni atleta
        for (Atleta a : Atleti) {
            threadAtleti.add(new Thread(a)); // Crea un nuovo thread per ogni atleta
            threadAtleti.get(threadAtleti.size() - 1).start(); // Avvia il thread
        }

        for (Atleta a : Atleti) {
            try {
                threadAtleti.get(threadAtleti.size() - 1).join(); // Avvia il thread
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Metodo che viene chiamato quando un atleta ha finito
    public synchronized void finito(Atleta a) {
        Podio.add(a); // Aggiungi l'atleta al podio
        if (Podio.size() == Atleti.size()) // Se tutti gli atleti hanno finito
            fineGara(); // Termina la gara
    }

    // Metodo per terminare la gara e mostrare i risultati
    public void fineGara() {
        StringBuilder sb = new StringBuilder();
        sb.append("gara terminata\n");
        for (int i = 0; i < Podio.size(); i++) {
            sb.append((i + 1) + "° in classifica: ").append(Podio.get(i).nome).append("\n");
        }
        System.out.println(sb.toString()); // Stampa la classifica

        // Chiedi all'utente se vuole salvare il podio
        System.out.print("Vuoi salvare il podio nel file? (sì/no): ");
        String risposta = scanner.nextLine();
        
        if (risposta.equalsIgnoreCase("si")) {
            gf.scriviFile(sb.toString()); // Scrivi il podio nel file
            System.out.println("Podio salvato nel file.");
        } else {
            System.out.println("Podio non salvato.");
        }
    }
}
