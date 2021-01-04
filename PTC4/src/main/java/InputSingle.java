
//arg "zero" set 000...
public class InputSingle {
    private static long moduleAmount = 0;
    private final long moduleNo;

    private int bitAmount=0;
    private String binary="";
    private String zeroStr;

    public InputSingle(int bitAmount, String binaryStr) {
        moduleNo = moduleAmount;
        moduleAmount++;
        setBitAmount(bitAmount);
        setZeroStr(bitAmount);
        setBinaryStr(binaryStr);
    }

    private int setBitAmount(int bitAmount) {
        if (bitAmount < 0) {
            this.bitAmount = 0;
            System.err.println("InputSingle["+ moduleNo +"] setBitAmount(int bitAmount): bitAmount < 0, set 0");
            return 1;
        }//if
        this.bitAmount = bitAmount;
        return 0;
    }

    private int setZeroStr(int bitAmount) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<bitAmount; i++){
            sb.append("0");
        }//for
        this.zeroStr = sb.toString();
        return 0;
    }

    public int setBinaryStr(String binaryStr) {
        if(binaryStr == "zero"){
            setBinaryStr(zeroStr);
            return 0;
        }

        //contain  chars
        if (binaryStr.length() != bitAmount) {
            System.err.println("InputSingle[" + moduleNo + "]setBinaryStr(String binaryStr): binaryStr.length(\""+binaryStr+"\") != " + bitAmount + ", set \"" + zeroStr + "\"");
            this.binary = zeroStr;
            return 1;
        }//if

        //contain only 1 and 0
        for (int i=0; i<bitAmount; i++){
            char buffer = binaryStr.charAt(i);
            if ( !(buffer == '1' || buffer == '0') ) {
                System.err.println("InputSingle["+moduleNo+"]setBinaryStr(String binaryStr): binaryStr(\""+binaryStr+"\") contain not only \"1\" and \"0\", set \"" + zeroStr + "\"");
                this.binary = zeroStr;
                return 2;
            }//if
        }//for

        this.binary = binaryStr;
        return 0;
    }

    public String toString(){
        return binary;
    }

    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("IS["+moduleNo+"]: \"" + binary + "\"");
        return sb.toString();
    }

    public void show(){
        System.out.println(toString());
    }
}
