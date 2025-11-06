import java.util.ArrayList;
public class Giudice extends Thread{
   static ArrayList<Atleta> Atleti=new ArrayList<>();
    static ArrayList<Atleta> Podio=new ArrayList<>();
   static ArrayList<Thread> threadAtleti=new ArrayList<>();

   public Giudice(){

   }
   public static void aggiungimi (Atleta a){
       Atleti.add(a);
   }
    public static void inizio() {
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
    public static void finito(Atleta a){
       Podio.add(a);
       if(Podio.size()==Atleti.size())
           Giudice.fineGara();
    }
    public static void fineGara(){
       System.out.println("gara terminata");
       System.out.println("primo in classifica "+ Podio.get(0).nome);
       System.out.println("secondo in classifica "+ Podio.get(1).nome);
       System.out.println("terzo in classifica "+ Podio.get(2).nome);
    }

}
