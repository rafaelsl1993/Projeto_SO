package comandos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import utils.Config;
import main.main;

public class dir extends Command{

    public dir() {
    }

    @Override
    public void execute() {
        Config mConfig = Config.getInstance();
        ArrayList<String> folder = new ArrayList<>();
        ArrayList<String> file = new ArrayList<>();
        for(int i = 0; i < main.arquivo.blocos; i++){
            if(main.arquivo.file[i].estado == '3' && main.arquivo.file[i].local == mConfig.indice){
                folder.add((new String(main.arquivo.file[i].conteudo)).split("\0")[0]);
            }else if(main.arquivo.file[i].estado == '1' && main.arquivo.file[i].local == mConfig.indice){
                file.add((new String(main.arquivo.file[i].conteudo)).split("\0")[0]);
            }

        }
        
        System.out.println("    Unidade selecionada´é: " + main.arquivo.nome);
        System.out.println("    Pasta: " + mConfig.shellLine);
        for (String folderAt : folder) {
            System.out.println("<DIR>   " + folderAt);
        }
        for (String fileAt : file) {
            System.out.println("        " + fileAt);
        }
        System.out.println(file.size() + " arquivo(s)");
        System.out.println(folder.size() + " pasta(s)");
        

    }
    
}
