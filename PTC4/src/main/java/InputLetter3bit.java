public class InputLetter3bit {
    private static final int INPUT_LENGTH = 3;
    private static long moduleAmount = 0;
    private long moduleNo;



    String inputString = "000";

    public InputLetter3bit() {
        moduleNo = moduleAmount;
        moduleAmount++;
    }

    public InputLetter3bit(String aInputString){
        this();
        setInput(aInputString);
    }

    public int setInput(String aInputString){
        //contain 3 chars
        if (aInputString.length() != INPUT_LENGTH) {
            setInput("000");
            System.err.println("InputLetter3bit["+moduleNo+"]_setInput(String aInputString): aInputString.length != " + INPUT_LENGTH + ", set \"000\"");
            return 1;
        }//if
        //contain only 1 and 0
        for (int i=0; i<INPUT_LENGTH; i++){
            char buffer = aInputString.charAt(i);
            if ( !(buffer == '1' || buffer == '0') ) {
                setInput("000");
                System.err.println("InputLetter3bit["+moduleNo +"]_setInput(String aInputString): aInputString contain not only \"1\" and \"0\"");
                return 2;
            }//if
        }//for
        this.inputString = aInputString;
        return 0;
    }//setInput

    public static long getModuleAmount() {
        return moduleAmount;
    }

    public long getModuleNo() {
        return moduleNo;
    }

    public String getInputString() {
        return inputString;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IL3b["+moduleNo+"]: \"" + inputString + "\"");
        return sb.toString();
    }//toString

    public void show(){
        System.out.println(toString());
    }

    public String toStringShort(){
        StringBuilder sb = new StringBuilder();
        sb.append("["+moduleNo+"]"+ inputString);
        return sb.toString();
    }
}
