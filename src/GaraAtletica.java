public class GaraAtletica {
    public static void main(String[] args) {
        Giudice g= new Giudice();
        Atleta a1 = new Atleta(1, "Francesco",g);
        Atleta a2 = new Atleta(2, "Federico",g);
        Atleta a3 = new Atleta(3, "PixReDeiPix",g);

        g.aggiungimi(a1);
        g.aggiungimi(a2);
        g.aggiungimi(a3);

        g.inizio();







    }

}