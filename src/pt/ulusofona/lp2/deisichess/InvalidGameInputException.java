package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {
    private int line;
    private String msg;
    private int numDados;
    private int numExpectedDados;
    public int getLineWithError(){return this.line;}
    String getExpectedString(int numDados,int numExpectedDados){
        return "(Esperava: "+numExpectedDados+" ; Obtive: "+numDados+")";
    }
    public String getProblemDescription(){
        return msg + getExpectedString(this.numDados,this.numExpectedDados);
    }

    public InvalidGameInputException(int line, String msg) {
        this.line=line;
        this.msg=msg;
    }
    public InvalidGameInputException(int line, String msg,int numDados,int numExpectedDados) {
        this.line=line;
        this.msg=msg;
        this.numDados=numDados;
        this.numExpectedDados=numExpectedDados;
    }

}
