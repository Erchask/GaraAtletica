import java.util.Random;

public class Atleta implements Runnable {
    Giudice giudice;  

    String nome;
    int numero;
    double tempo;
    double metri;
    boolean bevutoSospetto;
    boolean scivolato;
    boolean distratto;
    boolean arrivato;
    public final Random rand = new Random();


    public Atleta(String nome, int numero, Giudice giudice) {
        this.giudice = giudice;
        this.nome = nome;
        this.numero = numero;
        this.metri = 0;
        this.tempo = 0;
        this.arrivato = false;
        this.bevutoSospetto = rand.nextDouble(100) < 20;
        this.scivolato = false;
        this.distratto = false;
    }

    @Override
    public void run() {
        double LUNGHEZZA_GARA = giudice.getLUNGHEZZAGARA();
        double tempoDistratto = 0;

        while (metri <= LUNGHEZZA_GARA) {

            // Possibile scivolata
            if (!scivolato && rand.nextInt(100) < 25) {
                scivolato = true;
                System.out.println(nome + " è scivolato! Rimane fermo per 1 secondo...");
            }

            // Possibile distrazione
            if (!scivolato && !distratto && rand.nextInt(100) < 25) {
                System.out.println(nome + " si è distratto! Rallenta per 2 secondi...");
                distratto = true;
                tempoDistratto = tempo;
            }

            // Fine distrazione dopo 2 secondi
            if (distratto && (tempo - tempoDistratto) >= 2) {
                distratto = false;
                System.out.println(nome + " ha ripreso la concentrazione");
            }

            // Movimento
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

            // La scivolata dura un solo ciclo (1 secondo)
            if (scivolato) scivolato = false;
        }

        arrivato = true;
        System.out.printf("%s ha terminato la gara!\n", nome);
        giudice.finito(this);
    }
}
