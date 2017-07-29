package concurrencia;

public class FileReader extends Thread {

    private final FileNames fn;
    private final FileContents fc;

    public FileReader(FileNames fn, FileContents fc) {
        this.fn = fn;
        this.fc = fc;
    }

    @Override
    public void run() {
        fc.registerWriter();
        //System.out.println("empezar fr");
        String name = fn.getName();
        while (name != null) {
            fc.addContents(Tools.getContents(name));
            name = fn.getName();
        }
        fc.unregisterWriter();
        //System.out.println("terminar fr");
    }
}
