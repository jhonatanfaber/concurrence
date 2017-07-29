package concurrencia;
import java.util.Queue;
import java.util.LinkedList;
public class FileNames {
    private boolean flag=false;
    private Queue<String> queue =new LinkedList<>();
    
    public synchronized void  addName(String fileName) {
        if(flag == false){
            queue.add(fileName);
            notifyAll();
        }
    }
    public synchronized String getName() {
        while(queue.isEmpty()){
            if(flag==true){
                return null;
             }   
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        
        
        //extrae y elimina
        return queue.poll();
    }
    public synchronized void noMoreNames() {
        flag=true;
        notifyAll();
    }
}