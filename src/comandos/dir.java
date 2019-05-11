package comandos;

import java.util.Arrays;
import utils.Config;
import main.main;

public class dir extends Command{

    public dir() {
    }

    @Override
    public void execute() {
        Config mConfig = Config.getInstance();
        
        for(int i = 0; i < main.arquivo.blocos; i++){
            
            if(main.arquivo.file[i].estado != 0 && main.arquivo.file[i].estado != 2 && main.arquivo.file[i].local == mConfig.indice){
                System.out.println(Arrays.toString(main.arquivo.file[i].conteudo));
            }
            
        }

    }
    
}
