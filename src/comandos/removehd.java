package comandos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.main;
import utils.Config;

public class removehd extends Command{

    @Override
    public void execute() {
        Config mConfig = Config.getInstance();
        String temp;
        ArrayList<String> hd = new ArrayList<>();
        try {
            BufferedReader arq = new BufferedReader(new FileReader(mConfig.HDList));
            while(!(temp = arq.readLine()).isEmpty()){
                if(!(temp.split(" ")[0].equals(this.name))){
                    hd.add(temp);
                }
            }
            arq.close();
            File file = new File(mConfig.HDs + "/" + this.name);
            if(file.exists()){
                file.delete();
            }
        
        }catch (Exception e){
    
        }
        
    }
    
}