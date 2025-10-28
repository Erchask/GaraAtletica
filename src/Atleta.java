import java.util.Random;


public class Atleta implements Runnable {
    int numero;
    String nome;
    double tempo = (double)0.0F;
    double metri = (double)0.0F;
    final double LUNGHEZZAGARA = (double)50.0F;


    public Atleta(int pNumero, String pNome) {
        this.numero = pNumero;
        this.nome = pNome;
    }


    public void run() {
        Random metriPercorsi = new Random();


        while(this.metri <= (double)50.0F) {
            this.metri += metriPercorsi.nextDouble((double)10.0F);
            System.out.println(this.nome + " Metri Percorsi: " + this.metri);


            try {
                Thread.currentThread();
                Thread.sleep(1000L);
            } catch (InterruptedException var3) {
                System.err.println("Errore sleep");
            }
        }


        System.out.println(this.nome + " è al Traguardo");
    }
}

