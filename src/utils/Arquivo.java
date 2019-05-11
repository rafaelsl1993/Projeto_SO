package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arquivo {
    public Bloco file[];
    public int blocos;
    public int bytes;
    public String nome;
    
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
    
    public void newPath(byte titulo[]){
        Config mConfig = Config.getInstance();
        int i,j;
        for(i = 0; i < blocos; i++){
            if(file[i].estado == 0){		//Bloco Livre

                file[i].local = (short)mConfig.indice;
                file[i].estado = 3; 	//É pasta

                for(j = 0; titulo[j] != '\0'; j++){
                    file[i].conteudo[j] = titulo[j];

                }
                file[i].conteudo[j] = titulo[j];
            }
        }
    }
    
    
    public void write(byte titulo[], byte conteudo[]){
        Config mConfig = Config.getInstance();
        int length = 0;
        for(int i = 0; conteudo[i] != '\0'; i++){
            length++;
        }
        if(length == 0){
            return;				//Nada a gravar
        }else{
            length++;			//Incrementa o \0 para gravação
        }

        int toWrite = (length / bytes) + 1; // blocos a ocupar

        int i,j;	//i caminha nos blocos	/	j caminha nos bytes

        for(i = 0; i < blocos; i++){
            if(file[i].estado == 0){		//Bloco Livre

                file[i].local = file[i].converte(mConfig.localMaior, mConfig.localMenor);
                file[i].estado = 1; 	//É título

                for(j = 0; titulo[j] != '\0'; j++){
                    file[i].conteudo[j] = titulo[j];

                }
                file[i].conteudo[j] = titulo[j];
                j++;
                byte[] sizeConteudo = Integer.toString(toWrite).getBytes();
                
                for(int k = 0; k < sizeConteudo.length; k++){
                    file[i].conteudo[j++] = sizeConteudo[k];
                }
                file[i].conteudo[j] = '\0';
                
            }
        }
        writeConteudo(conteudo, length, 0, i);			//Recursivo, Procura Bloco vazio e Grava

    }
    
    
    private void writeConteudo(byte conteudo[], int length, int atual, int i){		//Recursivo, Procura Bloco vazio e Grava
        for(int k = i + 1; k < blocos; k++){	//k é o bloco atual		/	i é bloco anterior
            if(file[k].estado == 0){		//Bloco Livre
                file[k].estado = 2; 		//É conteúdo
                file[i].continua = (short)k;		//Link Bloco anterior com Bloco atual

                int j = 0;
                while(j < bytes && atual < length){		//Grava conjunto de N Bytes ou até final do conteúdo a gravar
                    file[k].conteudo[j++] = conteudo[atual++];
                }

                if(atual < length){					//Se ainda não gravou tudo, chama recursão
                    writeConteudo(conteudo, length, atual, k);
                }
                else{
                    file[k].continua = 0;
                }
                return;
            }
        }
    }

    public int found(byte titulo[]){
        int i,j;

        for(i = 0; i < blocos; i++){	//Itera Os blocos
            if(file[i].estado == 1 || file[i].estado == 3){

                for(j = 0; file[i].conteudo[j] != '\0'; j++){	//Compara o Titulo/Pasta e retorna seu índice
                    if(titulo[j] != file[i].conteudo[j]){
                        break;
                    }
                }
                if(titulo[j] == file[i].conteudo[j]){
                    return i;
                }

            }
        }

        return -1;		//não encontrado

    }


    private void readHD() throws IOException{
        BufferedReader reader;
        Config mConfig = Config.getInstance();
        
        reader = new BufferedReader(new FileReader(mConfig.HDs + "/" + this.nome));
        
        byte estado;
        short local;
        short continua;
        byte content[] = new byte[bytes];
        
        for(int i = 0; i < blocos; i++){
            estado = (byte)reader.read();
            local = (short)reader.read();
            continua = (short)reader.read();
            for(int j = 0; j < bytes; j++){
                content[j] = (byte)reader.read();
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
            arq.append((char) file[i].local);
            arq.append((char) file[i].continua);
            
            for(int j = 0; j < this.bytes; j++){
                arq.append((char) file[i].conteudo[j]);
            }
            
        }
        
        arq.close();
         
    }
    
}
