package utils;

public class Config {
    public int header = 5;
    public int indice = 65534;
    public String shellLine = new String(">");
    public String HDListPath = "hdlist\\";
    public String HDList = HDListPath + "hdlist.txt";
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