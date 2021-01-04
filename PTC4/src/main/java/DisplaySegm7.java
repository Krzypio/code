import java.lang.reflect.Array;
import java.util.*;

public class DisplaySegm7 {
    private static long moduleAmount = 0;
    private final long moduleNo;

    private String inputString = "000";
    private char outputChar = ' ';
    private int outputInt = 0;

    public DisplaySegm7() {
        moduleNo = moduleAmount;
        moduleAmount++;
    }

    public int setInput(String inputString){
        //contain 7 chars
        if (inputString.length() != 7) {
            setInput("000");
            System.err.println("DisplaySegm7["+moduleNo+"]_setInput(String inputString): inputString.length != 7, set \"000\"");
            return 1;
        }//if
        //contain only 1 and 0
        for (int i=0; i<7; i++){
            char buffer = inputString.charAt(i);
            if ( !(buffer == '1' || buffer == '0') ) {
                setInput("000");
                System.err.println("DisplaySegm7["+moduleNo +"]_setInput(String inputString): inputString contain not only \"1\" and \"0\"");
                return 2;
            }//if
        }//for
        this.inputString = inputString;
        setOther(this.inputString);
        setOutputInt(this.inputString);
        return 0;
    }//setInput

    public int setInput(char aInputChar){
        String enabledChars = " ABCEFGHIJLOPS";
        Set<Character> listEnabledChars = new HashSet<>();
        for (int i=0; i<enabledChars.length(); i++){
            listEnabledChars.add( enabledChars.charAt(i) );
        }
        if (!listEnabledChars.contains(aInputChar)){
            setInput(' ');
            System.err.println("DisplaySegm7["+moduleNo+"]_setInput(char aInputChar): aInputChar not on enabled list. Set \' \'");
            return 1;
        }
        this.outputChar = aInputChar;
        setOther(this.outputChar);
        setOutputInt(this.inputString);
        return 0;
    }//setInput(char)

    private void setOther(String aInputString){
        char aOutputChar;
        switch(aInputString){
            case "0000000":
                aOutputChar = '_';
                break;
            case "1110111":
                aOutputChar = 'A';
                break;
            case "1111100":
                aOutputChar = 'B';
                break;
            case "0111001":
                aOutputChar = 'C';
                break;
            case "1111001":
                aOutputChar = 'E';
                break;
            case "1110001":
                aOutputChar = 'F';
                break;
            case "1101111":
                aOutputChar = 'G';
                break;
            case "1110110":
                aOutputChar = 'H';
                break;
            case "0000110":
                aOutputChar = 'I';
                break;
            case "0001110":
                aOutputChar = 'J';
                break;
            case "0111000":
                aOutputChar = 'L';
                break;
            case "0111111":
                aOutputChar = 'O';
                break;
            case "1110011":
                aOutputChar = 'P';
                break;
            case "1101101":
                aOutputChar = 'S';
                break;
            default:
                aOutputChar = ' ';
        }//switch
        this.outputChar = aOutputChar;
    }//decodeChar

    private void setOther(char inputChar){
        String inputString = "";
        switch(inputChar){
            case ' ':
                inputString = "0000000";
                break;
            case 'A':
                inputString = "1110111";
                break;
            case 'B':
                inputString = "1111100";
                break;
            case 'C':
                inputString = "0111001";
                break;
            case 'E':
                inputString = "1111001";
                break;
            case 'F':
                inputString = "1110001";
                break;
            case 'G':
                inputString = "1101111";
                break;
            case 'H':
                inputString = "1110110";
                break;
            case 'I':
                inputString = "0000110";
                break;
            case 'J':
                inputString = "0001110";
                break;
            case 'L':
                inputString = "0111000";
                break;
            case 'O':
                inputString = "0111111";
                break;
            case 'P':
                inputString = "1110011";
                break;
            case 'S':
                inputString = "1101101";
                break;
            default:
                inputString = "0001000";
                System.err.println("DisplaySegm7["+moduleNo+"]_codeChar(char inputChar): no code for inputChar. Set _");
        }//switch
        this.inputString = inputString;
    }//setInput(char)

    public String getInputString() {
        return inputString;
    }//getInputString

    public char getOutputChar() {
        return outputChar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DS7["+moduleNo+"] = str \"" + getInputString() + "\" : char \'" + getOutputChar() + "\' int " + getOutputInt());
        return sb.toString();
    }//toString

    public String toStringShort(){
        StringBuilder sb = new StringBuilder();
        sb.append("["+moduleNo+"]\'" + getOutputChar() + "\'" + getOutputInt());
        return sb.toString();
    }

    public void show(){
        System.out.println(toString());
    }

    public long getModuleNo(){
        return moduleNo;
    }

    public long getModuleAmount(){
        return moduleAmount;
    }

    private int convertBinaryStrToInt(String binaryString){
        return Integer.parseInt(binaryString, 2);
    }

    private void setOutputInt(String binaryString){
        this.outputInt = convertBinaryStrToInt(this.inputString);
    }

    public int getOutputInt() {
        return outputInt;
    }
}
