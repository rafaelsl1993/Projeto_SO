package comandos;
import main.main;
import utils.Config;


public class renamedir extends Command{
    String orig;
    String dest;
    
    @Override
    public void setValues(String[] parameters){
        this.orig = parameters[1];
        this.dest = parameters[2];
    }

    @Override
    public void execute() {
        
    }
    
}
