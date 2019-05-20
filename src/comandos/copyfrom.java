package comandos;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.main;
import utils.Bloco;
import utils.Config;


public class copyfrom extends Command{
    String orig;
    String dest;
    
    @Override
    public void setValues(String[] parameters){
        this.orig = parameters[1];
        this.dest = parameters[2];
    }

    @Override
    public void execute() {
        char temp[] = new char[1000000];
        int tempB;
        int count = 0;
        try {
            FileReader file = new FileReader(this.orig);
            
            
            tempB = file.read();
            do{
                temp[count++] = (char)(tempB);
                tempB = file.read();
            }while(tempB != -1);
            
            
            
            main.arquivo.write(dest.toCharArray(), temp, count);
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(copyfrom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(copyfrom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}