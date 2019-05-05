import comandos.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import utils.Config;

public class main {

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
        
        m.put("\n", null);
        
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
                System.out.println("Command not found");
            }
        }

    }
}
