package comandos;
import main.main;
import utils.Config;

public class type extends Command{

    public type(String name) {
        this.name = name;
    }

    public type() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        Config mConfig = Config.getInstance();
        
        int index = main.arquivo.found(this.name.getBytes());
        
        if(main.arquivo.file[index].estado == 1 && main.arquivo.file[index].local == mConfig.indice){
            
            for(int i = index; ;){
                if(main.arquivo.file[i].continua != 0){
                    i = main.arquivo.file[i].continua;
                }
                else{
                    for(int j = 0; j < main.arquivo.file[index].getSizeLastLine(); j++){
                        System.out.print(main.arquivo.file[i].conteudo[j]);
                    }
                    return;
                }
                for(int j = 0; j < main.arquivo.bytes; j++){
                    System.out.print(main.arquivo.file[i].conteudo[j]);
                    
                }
            }
        }
        
    }
    
}
