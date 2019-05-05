package utils;

public class Config {
    public int header = 5;
    public int indice = 0;
    public String shellLine = new String(">");
    public String HDList = "hdlist/hdlist.txt";
    public String HDs = "hds";
    public String logFile = "log.txt";
    public byte localMaior = 0;
    public byte localMenor = 0;

    private static Config instance;

    private Config(){

    }

    static public Config getInstance(){
        if(instance == null){
            instance = new Config();
        }
        return instance;
    }

}