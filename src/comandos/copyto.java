package comandos;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.main;
import utils.Config;


public class copyto extends Command{
    String orig;
    String dest;
    
    @Override
    public void setValues(String[] parameters){
        this.orig = parameters[1];
        this.dest = parameters[2];
    }

    @Override
    public void execute() {
        BufferedWriter file;
        try {
            file = new BufferedWriter(new FileWriter(dest));
            
            int indice = main.arquivo.found(orig, Config.getInstance().indice, '1');
            indice = main.arquivo.file[indice].continua;
            while(main.arquivo.file[indice].estado != '4'){
                for(int i = 0; i < main.arquivo.bytes; i++){
                    file.append(main.arquivo.file[indice].conteudo[i]);
                }
                indice = main.arquivo.file[indice].continua;
            }
            System.out.println("DIFERENTE");
            for(int i = 0; main.arquivo.file[indice].conteudo[i] != '\0'; i++){
                file.append(main.arquivo.file[indice].conteudo[i]);
            }
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(copyto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
