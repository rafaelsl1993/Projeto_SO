package utils;

public class Bloco {
    public byte estado;
    public int local;
    public int continua;
    public char conteudo[];
    private int size;
    
    public Bloco(int size){
        this.size = size;
        conteudo = new char[size];
    }
    
    public void set(byte estado, int local, int continua, char[] conteudo){
        this.estado = estado;
        this.local = local;
        this.continua = continua;
        System.arraycopy(conteudo, 0, this.conteudo, 0, size);
    }
    
    public int converte(int a, int b){
        return ((int)((a*256) + b));
    }
    
    public int[] converte(char L){
        int[] b;
        if(L == 'L'){
            b = new int[] {((int)(local/256)), ((int)(local%256))};
        }
        else{
             b = new int[] {((int)(continua/256)), ((int)(continua%256))};
        }
        return b;
    }
    
    public boolean compare(String compName){
        return (compName.equals((new String(this.conteudo)).split("\0")[0]));
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