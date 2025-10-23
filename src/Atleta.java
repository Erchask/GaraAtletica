import java.util.Random;
public class Atleta implements Runnable{
String nome;
int numero;
double tempo=0;
double metri=0;
final double LUNGHEZZAGARA=400;
final Random rand = new Random();


public Atleta(String nome,int numero){
    this.nome=nome;
    this.numero=numero;


}
public void comunica(){
    System.out.println("sono l'atleta "+ nome);
}
@Override
public void run(){
    while(metri<LUNGHEZZAGARA){
        metri+=rand.nextDouble(5, 17);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("metri percorsi:"+metri);
    }
    System.out.println(nome+ "è arrivato");


}

}
