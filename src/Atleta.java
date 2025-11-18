import java.util.Random;


public class Atleta implements Runnable {
    int numero;
    String nome;
    double tempo = 0;
    double metri = 0;




    public Atleta(int cNumero, String cNome) {
        this.numero = cNumero;
        this.nome = cNome;
        Giudice.aggiungimi(this);
    }


    public void run() {
        Random metriPercorsi = new Random();


        while(this.metri <= Giudice.getLUNGHEZZAGARA()) {
            this.metri += metriPercorsi.nextDouble((double)10);
            System.out.println(this.nome + " Metri Percorsi: " + this.metri);


            try {
                Thread.currentThread();
                Thread.sleep(1000);
            } catch (InterruptedException var3) {
                System.err.println("Errore sleep");
            }
        }


      Giudice.finito(this);
    }
}

