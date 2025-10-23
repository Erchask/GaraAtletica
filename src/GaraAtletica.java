
public class GaraAtletica {
    public static void main(String[] args) {
        System.out.println("caratterisitche Main"+Thread.currentThread().getName()+"-"+Thread.currentThread().getPriority());
      for (int i=3;i<=0;i--){
           System.out.println("Inizio in"+i);
       }
        Atleta A1=new Atleta("francesco",3);
       Thread TA1=new Thread(A1);

       A1.run();


    }
}