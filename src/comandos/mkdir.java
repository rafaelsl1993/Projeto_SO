package comandos;
import main.main;

public class mkdir extends Command{

    public mkdir(String name) {
        this.name = name;
    }

    public mkdir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        main.arquivo.newPath(this.name.getBytes());
    }
    
}
