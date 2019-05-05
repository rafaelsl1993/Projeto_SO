package utils;

public class Bloco {
    public byte estado;
    public short local;
    public short continua;
    public byte conteudo[];
    
    public Bloco(int size){
        conteudo = new byte[size];
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
}