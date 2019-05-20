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
            if(mConfig.indice == 65534){
                return;
            }
            int i;
            for(i = mConfig.shellLine.length() - 1; i > 0; i--){
                if(mConfig.shellLine.charAt(i) == '/'){
                    break;
                }
            }
            mConfig.indice = main.arquivo.file[mConfig.indice].local;
            if(mConfig.indice == 65534){
                mConfig.shellLine = mConfig.shellLine.substring(0, i) + "/>";
            }else{
                mConfig.shellLine = mConfig.shellLine.substring(0, i) + ">";
            }
        }else if(name.isEmpty()){
            mConfig.indice = 65534;
            mConfig.localMaior = 0;
            mConfig.localMenor = 0;
            mConfig.shellLine = (main.arquivo.nome + ":/>");

        }
        else{
            int i = main.arquivo.found(this.name, mConfig.indice, '3');
            if(i != -1){
                int size = mConfig.shellLine.length();
                String sub = mConfig.shellLine.substring(0,size-1);
                if(mConfig.indice == 65534){
                    mConfig.shellLine = (sub + this.name + ">");
                }else{
                    mConfig.shellLine = (sub + "/" + this.name + ">");
                }
                mConfig.indice = i;
            }
        }
    }
}
