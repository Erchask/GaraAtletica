import java.util.Scanner;

public class GaraAtletica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numeroAtleti = 0;
        while (numeroAtleti < 3 ) {
            System.out.print("Quanti atleti vuoi registrare? (minimo 3): ");
            numeroAtleti = scanner.nextInt();
        }
       
        int lunghezzaGara = 0;
        while (lunghezzaGara != 50 && lunghezzaGara != 100 && lunghezzaGara != 200) {
            System.out.print("Scegli la lunghezza della gara (50, 100, 200): ");
            lunghezzaGara = scanner.nextInt();
        }

        
        Giudice g = new Giudice(lunghezzaGara);

        scanner.nextLine(); 
        for (int i = 1; i <= numeroAtleti; i++) {
            System.out.print("Inserisci il nome dell'atleta " + i + ": ");
            String nomeAtleta = scanner.nextLine();
            Atleta atleta = new Atleta(nomeAtleta, i, g); 
            g.aggiungimi(atleta); 
        }

        g.inizio(); 
    }
}
