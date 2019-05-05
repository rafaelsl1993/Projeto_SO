package comandos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class formathd extends Command{
    
    public formathd(String[] parameters){
        super(parameters);
    }

    public formathd() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        try {
            FileReader arq = new FileReader(constants.hdPath + "/" + name);
            arq.close();
            FileWriter file = new FileWriter(constants.hdPath + "/" + name);
            file.flush();
            file.close();
            System.out.println("hd formated\n");
        } catch (IOException ex) {
            System.out.println("hd does not exist\n");
        }
    }
    
}
