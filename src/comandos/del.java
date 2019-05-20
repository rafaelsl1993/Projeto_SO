package comandos;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.main;
import utils.Config;

public class del extends Command{

    public del(String name) {
        this.name = name;
    }

    public del() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        Config mConfig = Config.getInstance();
        
        int indice = main.arquivo.found(this.name, mConfig.indice, '1');
        if(indice != -1){
            deleteFile(indice);
        }else{
            indice = main.arquivo.found(this.name, mConfig.indice, '3');
            if(indice != -1){
                deleteAll(indice);
            }
        }
        
        try {
            main.arquivo.save();
        } catch (IOException ex) {
            Logger.getLogger(del.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void deleteFile(int i){
        if(main.arquivo.file[i].estado != '4'){
            deleteFile(main.arquivo.file[i].continua);
        }
        main.arquivo.file[i].estado = '0';
    }
    
    private void deleteAll(int i){
        for(int j = 0; j < main.arquivo.blocos; j++){
            if(main.arquivo.file[j].estado == '1' && main.arquivo.file[j].local == i){
                deleteFile(j);
            }else if(main.arquivo.file[j].estado == '3' && main.arquivo.file[j].local == i){
                deleteAll(j);
            }
        }
        main.arquivo.file[i].estado = '0';
    }

}
