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
                Runtime.getRuntime().exec("cls");
        else
                Runtime.getRuntime().exec("clear");
                
                /*for(int i = 0; i<100; i++){
                System.out.println("\n");
                }
                System.out.println(System.getProperty("os.name").toString());*/
        } catch (IOException ex) {
            Logger.getLogger(cls.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
