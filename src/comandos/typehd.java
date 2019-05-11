package comandos;

import utils.Config;
import main.main;

public class typehd extends Command {

    public typehd(String[] parameters){
        super(parameters);
    }

    public typehd() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        char[] asc =  new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        Config mConfig = Config.getInstance();
        
        
        for(int i = 0; i < main.arquivo.blocos; i++){
            if(main.arquivo.file[i].estado != 0){
                System.out.print("     ");
                for(int j = 0; j < main.arquivo.bytes + mConfig.header; j++){
                    System.out.print(asc[(main.arquivo.file[i].conteudo[j]/16)] + "" + asc[(main.arquivo.file[i].conteudo[j]%16)] + " ");
                }
                
                System.out.println("\n" + i);
                
                System.out.print("     ");
                for(int j = 0; j < main.arquivo.bytes + mConfig.header; j++){
                    System.out.print(" " + main.arquivo.file[i].conteudo[j] + " ");
                }
            }
        }
    }
}
