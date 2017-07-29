package concurrencia;

import java.util.HashMap;
import java.util.Map;

public class FileProcessor extends Thread {

    private final FileContents fc;
    private final WordFrequencies wf;

    public FileProcessor(FileContents fc, WordFrequencies wf) {
        this.fc = fc;
        this.wf = wf;
    }

    @Override
    // llamar a fc, wf
    public void run() {
        Map<String,Integer> mapa;
        String contents = fc.getContents();
        String[] palabras;
       // System.out.println("empezar fp");
        Integer count;
        while (contents != null) {
            palabras = contents.split("[^\\wáéíóúÁÉÍÓÚñÑüÜ]+");
            mapa = new  HashMap<>();
           
            for (String palabra : palabras) {
                count = mapa.get(palabra);
                if(count == null){
                    mapa.put(palabra,1);
                }else{
                    count++;
                    mapa.put(palabra,count);
                }
            }
            wf.addFrequencies(mapa);
            contents = fc.getContents(); 
        }
        //System.out.println("terminar fp");
    }
}
