package main;

import comandos.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import utils.Arquivo;
import utils.Config;

public class main {

    public static Arquivo arquivo = new Arquivo();
    
    
    public static void main(String[] args) {
        Config mConfig = Config.getInstance();
        String TAG = "Main";
        Command mComando;
        Scanner S =new Scanner(System.in);
        String line = new String();
        String splitLine[];
        
        Map<String, Object> m = new HashMap<String, Object>();
        
        m.put("createhd", (Command) new createhd());
        m.put("selecthd", (Command) new selecthd());
        m.put("dirhd", (Command) new dirhd());
        m.put("formathd", (Command) new formathd());
        m.put("typehd", (Command) new typehd());
        m.put("cd", (Command) new cd());
        m.put("cls", (Command) new cls());
        m.put("create", (Command) new create());
        m.put("del", (Command) new del());
        m.put("dir", (Command) new dir());
        m.put("mkdir", (Command) new mkdir());
        m.put("type", (Command) new type());
        
        m.put("statushd", (Command) new statushd());
        m.put("removehd", (Command) new removehd());
        m.put("copyfrom", (Command) new copyfrom());
        m.put("copyto", (Command) new copyto());
        m.put("renamedir", (Command) new renamedir());
        m.put("copy", (Command) new copy());
        m.put("help", (Command) new help());
        m.put("move", (Command) new move());
        m.put("rmdir", (Command) new rmdir());
        m.put("tree", (Command) new tree());
        m.put("rename", (Command) new rename());
        
        m.put("\n", null);
        
        
        File diretorio  = new File(mConfig.HDListPath);
        if(!diretorio.exists()){
            diretorio.mkdir();
        }
        diretorio = new File(mConfig.HDs);
        if(!diretorio.exists()){
            diretorio.mkdir();
        }
        
        while(true){
            try{
                System.out.print(mConfig.shellLine);
                line = S.nextLine();
                splitLine = line.split(" ");
                if(splitLine != null && !splitLine[0].isEmpty()){
                    mComando = (Command) m.get(splitLine[0]);
                    mComando.setValues(splitLine);
                    mComando.execute();
                }
            }catch(Exception c){            
                System.out.println(c);
            }
        }

    }

    public static void novo(String splitLine, int integer, int integer0) {
        arquivo = new Arquivo(splitLine, integer, integer0);
    }
}
