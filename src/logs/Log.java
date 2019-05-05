package logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import utils.Config;

public class Log {
    private static Log instancia;
    private BufferedWriter write;
    Config mConfig;
    
    private Log(){
        mConfig = Config.getInstance();
    }

    public static Log getInstance(){
        if(instancia == null){
            instancia = new Log();
        }
        return instancia;
    }
    
    public void logger(String className, String message) throws IOException{
        write = new BufferedWriter(new FileWriter(mConfig.logFile));
        write.append(className + ": " + message);
        write.close();
    }
    
}
