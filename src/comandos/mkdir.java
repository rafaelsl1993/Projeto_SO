package comandos;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.main;

public class mkdir extends Command{

    public mkdir(String name) {
        this.name = name;
    }

    public mkdir() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        try {
            main.arquivo.newPath(this.name.toCharArray());
        } catch (IOException ex) {
            Logger.getLogger(mkdir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
