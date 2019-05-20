package comandos;
import main.main;
import utils.Config;

public class statushd extends Command{

    public statushd() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void execute() {
        int size = 0;
        for(int i = 0; i < main.arquivo.blocos; i++){
            if(main.arquivo.file[i].estado == '0'){
                size++;
            }
        }
        System.out.println("Tamanho Total = " + main.arquivo.blocos * main.arquivo.bytes + " bytes");
        System.out.println("Tamanho Ocupado = " + ((main.arquivo.blocos - size) * main.arquivo.bytes) + " bytes");
        System.out.println("Tamanho Disponivel = " + (size * main.arquivo.bytes) + " bytes");
    }
    
}
