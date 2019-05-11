package comandos;

import main.main;
import utils.Config;

public class cd extends Command{

    public cd(String[] parameters){
        super(parameters);
    }

    public cd() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        Config mConfig = Config.getInstance();
        
        if("..".equals(name)){
            int i;
            for(i = mConfig.shellLine.length() - 1; i > 0; i--){
                if(mConfig.shellLine.charAt(i) == '/'){
                    break;
                }
            }
            
            mConfig.shellLine = mConfig.shellLine.substring(0, i) + ">";

        }else if(name.isEmpty()){
            mConfig.indice = 0;
            mConfig.localMaior = 0;
            mConfig.localMenor = 0;
            mConfig.shellLine = (main.arquivo.nome + ":/>");

        }
        else{
            for(int i = 0; i < main.arquivo.blocos; i++){
                if(mConfig.indice == main.arquivo.file[i].local && main.arquivo.file[i].estado == 3 && main.arquivo.file[i].compare(this.name)){
                    mConfig.indice = i;
                    byte[] local = main.arquivo.file[i].converte('L');
                    mConfig.localMaior = local[0];
                    mConfig.localMenor = local[1];

                    int size = mConfig.shellLine.length();
                    String sub = mConfig.shellLine.substring(0,size-1);
                    mConfig.shellLine = (sub + "/" + this.name + ">");
                }
            }
        }
    }
}
