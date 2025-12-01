import java.io.*;
/**
 * Classe per la gestione della scrittura e lettura della classifica su file.
 */
public class GestoreFile {

    /** Nome del file di classifica */
    private final String fileName="classifica.txt";

    
    public void scriviFile(String testo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(testo);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Legge e stampa il contenuto del file di classifica.
     */
    public void leggiFile(){
        File file=new File(fileName);
        if(!file.exists()) {
            System.out.println("file non esistente");
            return;
        }
        try(BufferedReader br=new BufferedReader((new FileReader(fileName)))){
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
