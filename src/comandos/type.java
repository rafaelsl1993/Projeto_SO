package comandos;
import main.main;
import utils.Config;

public class type extends Command{

    public type(String name) {
        this.name = name;
    }

    public type() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        Config mConfig = Config.getInstance();
        int index = main.arquivo.found(this.name, mConfig.indice, '1');
        int i = main.arquivo.file[index].continua;
        while(main.arquivo.file[i].estado != '4'){
            System.out.print(new String(main.arquivo.file[i].conteudo));
            i = main.arquivo.file[i].continua;
        }
        System.out.println((new String(main.arquivo.file[i].conteudo)).split("\0")[0]);
    }
    
}
