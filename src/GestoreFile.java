import java.io.*;
public class GestoreFile {
 private final String fileName="classifica.txt";

 public void scriviFile(String testo){
     try(BufferedWriter bw= new BufferedWriter(new FileWriter(fileName))){
         bw.write(testo);
     } catch(IOException e){
         System.err.println(e.getMessage());
     }

 }
 public void leggiFile(){
     File file=new File(fileName);
     if(!file.exists()) {
         System.out.println("filenon esistente");
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
