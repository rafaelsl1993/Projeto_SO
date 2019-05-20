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
            
            if(main.arquivo.file[i].estado != '0'){
                System.out.print("     ");
                System.out.print(asc[(main.arquivo.file[i].estado)/16] + "" + asc[(main.arquivo.file[i].estado)%16] + " ");
                System.out.print(asc[((main.arquivo.file[i].converte('L'))[0])/16] + "" + asc[((main.arquivo.file[i].converte('L'))[0])%16] + " ");
                System.out.print(asc[((main.arquivo.file[i].converte('L'))[1])/16] + "" + asc[((main.arquivo.file[i].converte('L'))[1])%16] + " ");
                System.out.print(asc[((main.arquivo.file[i].converte('C'))[0])/16] + "" + asc[((main.arquivo.file[i].converte('C'))[0])%16] + " ");
                System.out.print(asc[((main.arquivo.file[i].converte('C'))[1])/16] + "" + asc[((main.arquivo.file[i].converte('C'))[1])%16] + " ");
                
                for(int j = 0; j < main.arquivo.bytes; j++){
                    System.out.print(asc[(main.arquivo.file[i].conteudo[j]/16)] + "" + asc[(main.arquivo.file[i].conteudo[j]%16)] + " ");
                }
                
                System.out.println("\n" + i);
                
                System.out.print("     ");
                System.out.print(" " + ((char)(main.arquivo.file[i].estado)) +" ");
                
                
                if(main.arquivo.file[i].converte('L')[0] > 32 && main.arquivo.file[i].converte('L')[0] != 127){
                    System.out.print(" " + ((char)main.arquivo.file[i].converte('L')[0]) + " ");
                }else{
                    System.out.print("   ");
                }
                if(main.arquivo.file[i].converte('L')[1] > 32 && main.arquivo.file[i].converte('L')[1] != 127){
                    System.out.print(" " + ((char)main.arquivo.file[i].converte('L')[1]) + " ");
                }else{
                    System.out.print("   ");
                }
                if(main.arquivo.file[i].converte('C')[0] > 32 && main.arquivo.file[i].converte('C')[0] != 127){
                    System.out.print(" " + ((char)main.arquivo.file[i].converte('C')[0]) + " ");
                }else{
                    System.out.print("   ");
                }
                if(main.arquivo.file[i].converte('C')[1] > 32 && main.arquivo.file[i].converte('C')[1] != 127){
                    System.out.print(" " + ((char)main.arquivo.file[i].converte('C')[1]) + " ");
                }else{
                    System.out.print("   ");
                }
               
                
                for(int j = 0; j < main.arquivo.bytes; j++){
                    if(main.arquivo.file[i].conteudo[j] != '\n'){
                        System.out.print(" " + ((char)main.arquivo.file[i].conteudo[j]) + " ");
                    }else{
                        System.out.print("   ");
                    }
                }
                System.out.println('\n');
            }
        }
    }
}
