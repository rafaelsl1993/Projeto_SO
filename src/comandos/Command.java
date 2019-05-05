package comandos;

public abstract class Command {
    protected String name;
    protected int mBlock;
    protected int mByte;

    abstract public void execute();

    public void setValues(String[] parameters){
        int size = parameters.length;
        if(size>3){
            this.mByte = Integer.parseInt(parameters[3]);   
        }
        if(size>2){
            this.mBlock = Integer.parseInt(parameters[2]);
        }
        if(size>1){
           this.name = parameters[1];
        }
    }

    public Command(String[] parameters) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Command() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
