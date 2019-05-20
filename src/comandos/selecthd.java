package comandos;

import main.main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import utils.Arquivo;
import utils.Config;

public class selecthd extends Command{
    private Config mConfig = Config.getInstance();
    
    public selecthd(String[] parameters){
        super(parameters);
    }

    public selecthd() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(mConfig.HDList));
            String[] splitLine;
            String temp;
            temp = reader.readLine();
            do{
                if(temp != null && temp.isEmpty()){
                   throw new IOException();
                }
                splitLine = temp.split(" ");
                if(name.equals(splitLine[0])){
                    main.novo(splitLine[0], Integer.valueOf(splitLine[1]), Integer.valueOf(splitLine[2]));
                    mConfig.indice = 65534;
                    mConfig.localMaior = 0;
                    mConfig.localMenor = 0;
                    mConfig.shellLine = (this.name + ":/>");
                    System.out.println("Selected HD = " + splitLine[0] + " Blocks = " + splitLine[1] + " Bytes = "  + splitLine[2]);
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