package comandos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class dirhd extends Command{

    public dirhd(String[] parameters){
        super(parameters);
    }

    public dirhd() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(constants.hdListFile));
            String[] content;
            String temp;
            temp = reader.readLine();
            do{
               if(temp != null && temp.isEmpty()){
                   throw new IOException();
               }
               content = temp.split(" ");
               System.out.println(content[0]);
               temp = reader.readLine();
            }while(temp != null && !temp.isEmpty());

            reader.close();
            
        } catch (IOException ex) {
                System.out.println("hd does not exist\n");
            }
        
    }
    
}
