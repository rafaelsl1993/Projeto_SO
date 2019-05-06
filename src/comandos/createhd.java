package comandos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Config;

public class createhd extends Command{
    
    private Config mConfig = Config.getInstance();
    
    public createhd(String[] parameters){
        super(parameters);
    }

    public createhd() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute(){
        try {
            FileReader arq = new FileReader(mConfig.HDs + "/" + name);
            System.out.println("hd already exists");
            arq.close();
        } catch (IOException ex) {
            try {
                FileWriter arq = new FileWriter(mConfig.HDList, true);
                arq.append(name + " " + mBlock + " " + mByte +"\n");
                arq.close();
                arq = new FileWriter(mConfig.HDs + "/" + this.name);
                for(int i = 0; i < mBlock; i++){
                    for(int j = 0; j < mByte + mConfig.header; j++){
                        arq.append('0');
                    }
                }
                arq.close();
            } catch (IOException ex1) {
                Logger.getLogger(createhd.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

}