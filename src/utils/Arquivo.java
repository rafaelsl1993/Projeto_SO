package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.main;

public class Arquivo {
    public Bloco file[];
    public int blocos;
    public int bytes;
    public String nome;
    
    public Arquivo(){
        
    }
    
    public Arquivo(String nome, int blocos, int bytes){
        this.nome = nome;
        this.blocos = blocos;
        file = new Bloco[this.blocos];
        this.bytes = bytes;
        for(int i = 0; i < blocos; i++){
            file[i] = new Bloco(bytes);
        }

        try {
            readHD();
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void newPath(char titulo[]) throws IOException{
        Config mConfig = Config.getInstance();
        int i,j;
        for(i = 0; i < blocos; i++){
            if(file[i].estado == '0'){		//Bloco Livre

                file[i].local = mConfig.indice;
                file[i].estado = '3'; 	//É pasta

                System.arraycopy(titulo, 0, file[i].conteudo, 0, titulo.length);

                file[i].conteudo[titulo.length] = '\0';
                this.save();
                return;
            }
        }
        this.save();
    }
    
    
    public void write(char titulo[], char conteudo[]){
        Config mConfig = Config.getInstance();
        int length = 0;
        conteudo[conteudo.length - 1] = '\0';
        length = conteudo.length;
        
        if(length == 0){
            return;				//Nada a gravar
        }
        int toWrite = (length / main.arquivo.bytes) + 1; // blocos a ocupar
        
        int i,j;	//i caminha nos blocos	/	j caminha nos bytes

        for(i = 0; i < main.arquivo.blocos; i++){
            if(file[i].estado == '0'){		//Bloco Livre

                file[i].local = Config.getInstance().indice;
                file[i].estado = '1'; 	//É título

                System.arraycopy(titulo, 0, file[i].conteudo, 0, titulo.length);

                file[i].conteudo[titulo.length] = '\0';

                char[] sizeConteudo = String.valueOf(toWrite).toCharArray();
                
                System.arraycopy(sizeConteudo, 0, file[i].conteudo, titulo.length + 1, sizeConteudo.length);
                
                file[i].conteudo[titulo.length + 1 + sizeConteudo.length] = '\0';

                break;
            }
        }

        writeConteudo(conteudo, length, 0, i);			//Recursivo, Procura Bloco vazio e Grava
        try {
            save();
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void write(char titulo[], char conteudo[], int size){
        Config mConfig = Config.getInstance();
        int length = 0;
        conteudo[conteudo.length - 1] = '\0';
        length = size + 1;
        
        int toWrite = (length / main.arquivo.bytes) + 1; // blocos a ocupar
        
        int i,j;	//i caminha nos blocos	/	j caminha nos bytes

        for(i = 0; i < main.arquivo.blocos; i++){
            if(file[i].estado == '0'){		//Bloco Livre

                file[i].local = Config.getInstance().indice;
                file[i].estado = '1'; 	//É título

                System.arraycopy(titulo, 0, file[i].conteudo, 0, titulo.length);

                file[i].conteudo[titulo.length] = '\0';

                char[] sizeConteudo = String.valueOf(toWrite).toCharArray();
                
                System.arraycopy(sizeConteudo, 0, file[i].conteudo, titulo.length + 1, sizeConteudo.length);
                
                file[i].conteudo[titulo.length + 1 + sizeConteudo.length] = '\0';

                break;
            }
        }

        writeConteudo(conteudo, length, 0, i);			//Recursivo, Procura Bloco vazio e Grava
        try {
            save();
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void writeConteudo(char conteudo[], int length, int atual, int i){		//Recursivo, Procura Bloco vazio e Grava
        for(int k = i + 1; k < main.arquivo.blocos; k++){	//k é o bloco atual		/	i é bloco anterior
            if(file[k].estado == '0'){		//Bloco Livre
                file[k].estado = '2'; 		//É conteúdo
                file[i].continua = k;		//Link Bloco anterior com Bloco atual

                int j = 0;
                while(j < main.arquivo.bytes && atual < length){		//Grava conjunto de N Bytes ou até final do conteúdo a gravar
                    file[k].conteudo[j++] = conteudo[atual++];
                }

                if(atual < length){					//Se ainda não gravou tudo, chama recursão
                    writeConteudo(conteudo, length, atual, k);
                }
                else{
                    file[k].continua = 65534;
                    file[k].estado = '4';
                    System.out.println("GRAVOU TUDO");
                }
                return;
            }
        }
    }

    public int found(String titulo, int indice, char tipo){

        int i,j;
        for(i = 0; i < blocos; i++){	//Itera Os blocos

            if(file[i].estado == tipo && file[i].local == indice && file[i].compare(titulo)){

                return i;
            }
        }

        return -1;		//não encontrado

    }


    private void readHD() throws IOException{
        BufferedReader reader;
        Config mConfig = Config.getInstance();
        
        reader = new BufferedReader(new FileReader(mConfig.HDs + "/" + this.nome));
        
        byte estado;
        int local;
        int continua;
        char content[] = new char[bytes];
        
        for(int i = 0; i < blocos; i++){
            estado = (byte)reader.read();
            local = this.file[0].converte((int)reader.read(), (int)reader.read());
            continua = this.file[0].converte((int)reader.read(), (int)reader.read());
            for(int j = 0; j < bytes; j++){
                content[j] = (char)reader.read();
            }
            this.file[i].set(estado, local, continua, content);
        }
        
        reader.close();
    }
    
    public void save() throws IOException{
        Config mConfig = Config.getInstance();
        FileWriter arq = new FileWriter(mConfig.HDs + "/" + this.nome);
        for(int i = 0; i < this.blocos; i++){
            arq.append((char) file[i].estado);
            arq.append((char) file[i].converte('L')[0]);
            arq.append((char) file[i].converte('L')[1]);
            arq.append((char) file[i].converte('C')[0]);
            arq.append((char) file[i].converte('C')[1]);
            
            for(int j = 0; j < this.bytes; j++){
                arq.append((char) file[i].conteudo[j]);
            }
            
        }
        
        arq.close();
         
    }
    
}
