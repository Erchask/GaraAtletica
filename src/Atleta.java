import java.util.Random;


public class Atleta implements Runnable {
    int numero;
    String nome;
    double tempo = 0;
    double metri = 0;
    Giudice g;




    public Atleta(int cNumero, String cNome,Giudice g) {
        this.numero = cNumero;
        this.nome = cNome;
        this.g=g;
    }


    public void run() {
        Random metriPercorsi = new Random();


        while(this.metri <= g.getLUNGHEZZAGARA()) {
            this.metri += metriPercorsi.nextDouble((double)10);
            System.out.printf(this.nome + " Metri Percorsi: %.2f\n",this.metri);


            try {
                Thread.currentThread();
                Thread.sleep(1000);
            } catch (InterruptedException var3) {
                System.err.println("Errore sleep");
            }
        }


        g.finito(this);
    }
}
