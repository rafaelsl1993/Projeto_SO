package comandos;

import java.util.ArrayList;
import java.util.Scanner;
import main.main;

public class create extends Command{

    public create(String name) {
        this.name = name;
    }

    public create() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        try {
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                
                int flag = 0;
                ArrayList<String> temp = new ArrayList<>();
                Scanner S = new Scanner(System.in);
                try{
                    while(temp.add(S.nextLine())){
                        temp.add("\n");
                    }
                    
                }catch(Exception e){
                    String toCopy = new String();
                    
                    for (String temp1 : temp) {
                        toCopy = toCopy.concat(temp1);
                    }
                    main.arquivo.write(this.name.toCharArray(), toCopy.toCharArray());
                    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                }
        }catch(Exception E){
                System.out.println(E);
        }
    }
    
}
