package comandos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.main;

public class formathd extends Command{
    
    public formathd(String[] parameters){
        super(parameters);
    }

    public formathd() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        
        for(int i = 0; i < main.arquivo.blocos; i++){
            main.arquivo.file[i].estado = 0;
        }
        
        try {
            main.arquivo.save();
            System.out.println("\"" + this.name + "\"" + " formated");
        } catch (IOException ex) {
            Logger.getLogger(formathd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
