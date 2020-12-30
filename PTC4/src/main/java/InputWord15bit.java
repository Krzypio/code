import java.util.logging.Level;

public class InputWord15bit {
    private static final int WORD_LENGTH = 5;
    private static final int LETTER_LENGTH = 3;
    private static final int INPUT_LENGTH = WORD_LENGTH*LETTER_LENGTH;

    private static long moduleAmount = 0;
    private long moduleNo;

    private InputLetter3bit[] inputLetters;
    private String rawInput = "";

    public InputWord15bit() {
        moduleNo = moduleAmount;
        moduleAmount++;

        inputLetters = new InputLetter3bit[WORD_LENGTH];
        for (int i=0; i<WORD_LENGTH; i++){
            inputLetters[i] = new InputLetter3bit();
        }
    }

    public int setInput(String aInputString){
        int error = 0;
        rawInput=aInputString;
        //usun biale znaki
        String remSpaces = aInputString.replaceAll("\\s+",""); //removes all whitespaces and non-visible characters (e.g., tab, \n).

        //Sprawdz dlugosc
        //contain 15 chars
        if (remSpaces.length() != INPUT_LENGTH) {
            setInput("000 000 000 000 000");
            System.err.println("InputWord15bit["+moduleNo+"]_setInput(String aInputString): remSpaces.length != " + INPUT_LENGTH + ", set \"000\"");
            return 1;
        }//if

        //dla kazdej litery
        for (int letter=0; letter < WORD_LENGTH; letter++){
            int letterBegin = letter * LETTER_LENGTH;
            int afterLetterEnd = letterBegin + LETTER_LENGTH;
            String letterStr = remSpaces.substring(letterBegin, afterLetterEnd);
            error += inputLetters[WORD_LENGTH-letter-1].setInput(letterStr);
            inputLetters[WORD_LENGTH-letter-1].show();
        }//for letter

        if (error>0){
            System.err.println("InputWord15bit["+moduleNo+"]_setInput(String aInputString): error>0, chars other than 1 0 ' ' in input, set \"000\" for this letter");
            return 2;
        }

        return 0;
    }//setInput


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("IW15b["+moduleNo+"]: ");
        for(int i=WORD_LENGTH; i>0; i--){
            sb.append( inputLetters[i-1].toStringShort());
            sb.append(" ");
        }
        sb.setLength( sb.length()-1 ); //remove last ' '
        return sb.toString();
    }

    public String toStringShort(){
        StringBuilder sb = new StringBuilder();
        sb.append("["+moduleNo+"]");
        for(int i=WORD_LENGTH; i>0; i--){
            String letterShort = inputLetters[i-1].toStringShort();
            sb.append(letterShort.substring(3));
            sb.append(" ");
        }
        sb.setLength( sb.length()-1 ); //remove last ' '
        return sb.toString();
    }

    public void show(){
        System.out.println("Raw input: " + rawInput);
        System.out.println(toString());
        System.out.println(toStringShort());
    }
}
