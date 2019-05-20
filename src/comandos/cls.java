package comandos;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class cls extends Command{

    public cls(String[] parameters){
        super(parameters);
    }

    public cls() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        try{
        if( System.getProperty( "os.name" ).startsWith( "Window" ) )
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        else
                Runtime.getRuntime().exec("clear");
        } catch (IOException ex) {
            Logger.getLogger(cls.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(cls.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}