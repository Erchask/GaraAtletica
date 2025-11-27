import java.util.ArrayList;
import java.io.*;
public class Giudice extends Thread{

     ArrayList<Atleta> Atleti=new ArrayList<>();
     ArrayList<Atleta> Podio=new ArrayList<>();
     ArrayList<Thread> threadAtleti=new ArrayList<>();
    private  final double LUNGHEZZAGARA = 50;
    GestoreFile gf= new GestoreFile();


    public Giudice(){

    }
    public  double getLUNGHEZZAGARA(){
        return LUNGHEZZAGARA;
    }
    public  void aggiungimi (Atleta a){
        Atleti.add(a);
    }
    public  void inizio() {
        for (int i = 3; i > 0; --i) {
            System.out.println("Inizio in:" + i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        for(Atleta a:Atleti){
            threadAtleti.add(new Thread(a));
            threadAtleti.getLast().start();
        }
    }
    public synchronized void finito(Atleta a){
        Podio.add(a);
        if(Podio.size()==Atleti.size())
            fineGara();
    }
    public void fineGara(){
        StringBuilder sb = new StringBuilder();
        sb.append("gara terminata\n");
        sb.append("primo in classifica: ").append(Podio.get(0).nome).append("\n");
        sb.append("secondo in classifica: ").append(Podio.get(1).nome);
        sb.append("secondo in classifica ").append(Podio.get(1).nome);
        System.out.println(sb.toString());
        gf.scriviFile(sb.toString());

    }

}