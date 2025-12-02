import java.util.Random;

/**
 * Rappresenta un atleta che partecipa a una gara simulata.
 * Ogni atleta corre in un thread separato e avanza in modo pseudo-casuale
 * tenendo conto di condizioni come distrazione, scivolate e sospette "bevute".
 *
 * L'atleta aggiorna continuamente i metri percorsi finché non raggiunge
 * la lunghezza totale della gara, quindi notifica il giudice.
 */
public class Atleta implements Runnable {

    /** Riferimento al giudice che supervisiona la gara. */
    Giudice giudice;

    /** Nome dell'atleta. */
    String nome;

    /** Numero identificativo dell'atleta. */
    int numero;

    /** Tempo impiegato dall'atleta (in secondi). */
    double tempo;

    /** Metri percorsi dall'atleta. */
    double metri;

    /** True se l'atleta ha bevuto qualcosa di sospetto (boost di velocità). */
    boolean bevutoSospetto;

    /** True se l'atleta è scivolato (un turno perso). */
    boolean scivolato;

    /** True se l'atleta è distratto (velocità ridotta temporaneamente). */
    boolean distratto;

    /** Indica se l'atleta ha raggiunto il traguardo. */
    boolean arrivato;

    /** Generatore pseudo-casuale usato per gli eventi e l'avanzamento. */
    public final Random rand = new Random();

    /**
     * Crea un nuovo atleta.
     *
     * @param nome    Nome dell'atleta
     * @param numero  Numero identificativo dell'atleta
     * @param giudice Riferimento al giudice che gestisce la gara
     */
    public Atleta(String nome, int numero, Giudice giudice) {
        this.giudice = giudice;
        this.nome = nome;
        this.numero = numero;
        this.metri = 0;
        this.tempo = 0;
        this.arrivato = false;

        // 20% di probabilità che l'atleta abbia bevuto qualcosa di "sospetto"
        this.bevutoSospetto = rand.nextDouble(100) < 20;

        this.scivolato = false;
        this.distratto = false;
    }

    /**
     * Logica principale del thread dell'atleta.
     * L'atleta corre, può scivolare, distrarsi, accelerare se ha bevuto qualcosa
     * e aggiorna i metri percorsi ogni secondo.
     * Al termine della gara avvisa il giudice.
     */
    @Override
    public void run() {
        double LUNGHEZZA_GARA = giudice.getLUNGHEZZAGARA();
        double tempoDistratto = 0;

        while (metri <= LUNGHEZZA_GARA) {

            // Possibile scivolata
            if (!scivolato && rand.nextInt(100) < 10) {
                scivolato = true;
                System.out.println(nome + " è scivolato! Rimane fermo per 1 secondo...");
            }

            // Possibile distrazione
            if (!scivolato && !distratto && rand.nextInt(100) < 15) {
                System.out.println(nome + " si è distratto! Rallenta per 2 secondi...");
                distratto = true;
                tempoDistratto = tempo;
            }

            // Fine della distrazione
            if (distratto && (tempo - tempoDistratto) >= 2) {
                distratto = false;
                System.out.println(nome + " ha ripreso la concentrazione");
            }

            // Avanzamento
            if (!scivolato) {
                if (distratto) {
                    metri += rand.nextDouble(3, 5);
                } else if (bevutoSospetto) {
                    metri += rand.nextDouble(7, 10);
                } else {
                    metri += rand.nextDouble(5, 8);
                }
                System.out.printf("%s - Metri percorsi: %.2f\n", nome, metri);
            } else {
                System.out.printf("%s resta fermo per la scivolata\n", nome);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tempo += 1;

            // Dopo lo stop per scivolata, riparte al ciclo successivo
            if (scivolato) scivolato = false;
        }

        arrivato = true;
        System.out.printf("%s ha terminato la gara!\n", nome);
        giudice.finito(this);
    }
}
