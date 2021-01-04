
//arg "zero" set 000...
public class InputBus {
    private static long moduleAmount = 0;
    private final long moduleNo;

    private int letterAmount=0;
    private int bitsPerLetter=0;
    private String binaryStr="";
    private String rawInput="";
    private String zeroStr="0";

    private InputSingle[] inputLetters;

    private InputBus() {
        moduleNo = moduleAmount;
        moduleAmount++;
    }

    public InputBus(int letterAmount, int bitsPerLetter, String binaryStr) {
        this();

        setBitsPerLetter(bitsPerLetter);
        setZeroStr(letterAmount, bitsPerLetter);
        setLetterAmount(letterAmount);

        //initialize
        inputLetters = new InputSingle[letterAmount];
        for (int i=0; i<letterAmount; i++){
            inputLetters[i] = new InputSingle(bitsPerLetter, "zero");
        }

        setBinaryStr(binaryStr);
    }

    private int setZeroStr(int letterAmount, int bitsPerLetter) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<letterAmount; i++){
            for(int j=0; j<bitsPerLetter; j++){
                sb.append("0");
            }//for i
            sb.append(" ");
        }//for j
        sb.setLength(sb.length()-1);
        this.zeroStr = sb.toString();
        return 0;
    }

    private int setLetterAmount(int letterAmount) {
        if (letterAmount < 0) {
            System.err.println("InputBus["+ moduleNo +"] setLetterAmount(int letterAmount): letterAmount("+letterAmount+") < 0, set 0");
            this.letterAmount = 0;
            return 1;
        }//if
        this.letterAmount = letterAmount;
        return 0;
    }

    private int setBitsPerLetter(int bitsPerLetter) {
        if (bitsPerLetter < 0) {
            this.bitsPerLetter = 0;
            System.err.println("InputSingle["+ moduleNo +"] setBitsPerLetter(int bitsPerLetter): bitsPerLetter < 0, set 0");
            return 1;
        }//if
        this.bitsPerLetter = bitsPerLetter;
        return 0;
    }

    public int setBinaryStr(String binaryStr){
        if("zero" == binaryStr){
            setBinaryStr(zeroStr);
            return 0;
        }

        int error = 0;
        this.rawInput=binaryStr;
        //usun biale znaki
        String remSpaces = binaryStr.replaceAll("\\s+",""); //removes all whitespaces and non-visible characters (e.g., tab, \n).

        //Sprawdz dlugosc
        int inputLength = this.letterAmount*this.bitsPerLetter;
        if (remSpaces.length() != inputLength) {
            System.err.println("InputBus["+moduleNo+"]_setBinaryStr(String binaryStr): remSpaces.length("+remSpaces.length()+") != " + inputLength + ", set " + zeroStr);
            this.binaryStr = zeroStr;
            return 1;
        }//if

        //dla kazdej litery
        for (int i=0; i < letterAmount; i++){
            int letterBegin = i * bitsPerLetter;
            int afterLetterEnd = letterBegin + bitsPerLetter;
            String letterStr = remSpaces.substring(letterBegin, afterLetterEnd);
            error += inputLetters[letterAmount-i-1].setBinaryStr(letterStr);
        }//for letter

        if (error>0){
            System.err.println("InputBus["+moduleNo+"]setBinaryStr((String binaryStr): error>0, chars other than 1 0 ' ', set \'zero\' for bad InputSingle");
            return 2;
        }
        return 0;
    }//setInput

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("IB["+moduleNo+"]: ");
        for(int i=letterAmount; i>0; i--){
            sb.append( inputLetters[i-1].toString());
            sb.append(" ");
        }
        sb.setLength( sb.length()-1 ); //remove last ' '
        return sb.toString();
    }

    public void show(){
        System.out.println(toString());
    }

    public String getLetter(int index){
        if (index >= 0 && index < letterAmount){
            return inputLetters[index].toString();
        }//if

        StringBuilder sb = new StringBuilder();
        for(int j=0; j<bitsPerLetter; j++)
            sb.append("0");

        System.err.println("InputBus["+moduleNo+"] getLetter(int index): index("+index+") is <0 or >=" + letterAmount + ", return " +sb.toString());
        return sb.toString();
    }//getLetter

    public int getLetterAmount() {
        return letterAmount;
    }

    public int getBitsPerLetter() {
        return bitsPerLetter;
    }
}
