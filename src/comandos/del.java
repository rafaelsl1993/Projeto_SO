package comandos;
import main.main;
import utils.Config;

public class del extends Command{

    public del(String name) {
        this.name = name;
    }

    public del() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        Config mConfig = Config.getInstance();
        
        for(int i = 0; ; i++){
            if(main.arquivo.file[i].estado == 1 && main.arquivo.file[i].local == mConfig.indice){
                deleteFile(i);
                return;
            }
        }
        
    }
    
    private void deleteFile(int i){
        if(main.arquivo.file[i].continua != 0){
            deleteFile(main.arquivo.file[i].continua);
        }
        main.arquivo.file[i].estado = 0;
    }
    
}
