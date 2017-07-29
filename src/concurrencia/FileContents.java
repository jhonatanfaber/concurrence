package concurrencia;
import java.util.LinkedList;
import java.util.Queue;

public class FileContents {

    private Queue<String> queue;
    private int registerCount = 0;
    private boolean closed = false;
    private final int maxFiles;
    private final int maxChars;
    private int suma = 0;

    public FileContents(int maxFiles, int maxChars) {
        queue = new LinkedList<>();
        this.maxFiles = maxFiles;
        this.maxChars = maxChars;
    }

    public synchronized void registerWriter() {
        registerCount++;
    }

    public synchronized void unregisterWriter() {
        if (closed == false) {
            registerCount--;
        }
        if (registerCount == 0) {
            closed = true;
        }

    }

    public synchronized void addContents(String contents) {

        while (queue.size() == maxFiles || suma+contents.length() > maxChars) {
            if (queue.size()==0) {
                break;
            }
            try {
                wait();
            } catch (InterruptedException ex) {
                
            }
            //actualizar
        }
        queue.add(contents);
        suma += contents.length();
        notifyAll();
    }

    public synchronized String getContents() {
        while (queue.isEmpty()) {
            if(closed){
               //notifyAll();
                return null;
            }
            try {
                wait();
            } catch (InterruptedException ex) {
                
            }
        }
        
        
        String content= queue.poll();
        suma -= content.length();
        notifyAll();
        return content;
    }
}
