package comandos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class selecthd extends Command{

    public selecthd(String[] parameters){
        super(parameters);
    }

    public selecthd() {
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
               if(name.equals(content[0])){
                    ProjetoSO01.content = content;
                    System.out.println("Selected HD = " + content[0] + " Blocks = " + content[1] + " Bytes = "  + content[2]);
                    reader.close();
                    return;
                }
                temp = reader.readLine();
            }while(temp != null && !temp.isEmpty());
            
            System.out.println("hd does not exist\n");
            reader.close();
            
        } catch (IOException ex) {
                System.out.println("hd does not exist\n");
            }
        }
    }
