package utils;

public class Bloco {
    public byte estado;
    public short local;
    public short continua;
    public byte conteudo[];
    
    public Bloco(int size){
        conteudo = new byte[size];
    }
    
    public void set(byte estado, short local, short continua, byte[] conteudo){
        this.estado = estado;
        this.local = local;
        this.continua = continua;
        this.conteudo = conteudo;
    }
    
    public short converte(byte a, byte b){
        return ((short)((a*256) + b));
    }
    
    public byte[] converte(char L){
        byte[] b;
        if(L == 'L'){
            b = new byte[] {((byte)(local/256)), ((byte)(local%256))};

        }
        else{
             b = new byte[] {((byte)(continua/256)), ((byte)(continua%256))};
        }
        return b;
    }
    
    public boolean compare(String compName){
        int i;
        for(i = 0; this.conteudo[i] != '\0'; i++){
            if(this.conteudo[i] != compName.charAt(i)){
                return false;
            }
        }
        
        if(compName.charAt(i) == '\0'){
            return true;
        }
        
        return false;
        
    }
    
    @SuppressWarnings("empty-statement")
    public int getSizeLastLine(){
        int index = 0;
        
        while(conteudo[index++] != '\0');
        
        int sizeLastLine = 0;
        
        for(int i = index; conteudo[i] != '\0'; i++){
            sizeLastLine = (sizeLastLine * 10) + (conteudo[i] - 48);
        }
        
        return sizeLastLine;
    }
    
}