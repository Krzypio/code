import java.util.ArrayList;
//dla 000 jest ' ' a pozniej znaki z word
public class CodecLetterGray {
    private static long moduleAmount = 0;
    private final long moduleNo;

    private int minBitAmount=0;
    private int bitAmount=0;

    private String word = "";
    private  ArrayList<String> arr = new ArrayList<>();

    public CodecLetterGray(String word, int bitAmount) {
        this.moduleNo = this.moduleAmount;
        this.moduleAmount++;

        setWord(word);

        setMinBitAmount();
        if (bitAmount < minBitAmount){
            this.bitAmount = minBitAmount;
            System.err.println("CodecLetterGray[" + moduleNo + "] : bitAmount < minBitAmount. set bitAmount = minBitAmount");
        } else {
            this.bitAmount = bitAmount;
        }//else

        generateGrayarr(this.bitAmount);
    }

    private int log2(int decimalNo)
    {
        int whole = (int) (Math.log(decimalNo) / Math.log(2));
        return whole + 1;
    }

    private void setMinBitAmount(){
        this.minBitAmount = log2(this.word.length());
    }

    public int getMinBitAmount() {
        return minBitAmount;
    }

    public String getWord() {
        return word;
    }

    private void setWord(String word){
        this.word = word;
    }

    public int getBitAmount() {
        return bitAmount;
    }

    public char getLetterChar(int index){
        if (index < 0 || index > word.length()){
            System.err.println("CodecLetterGray[" + moduleNo + "] getLetterChar(int index): index out of range,  return char=\' \'");
            return ' ';
        }//if

        if (index == 0) return ' ';
        return word.charAt(index-1);
    }

    private void generateGrayarr(int n)
    {
        arr.clear();
        // base case
        if (n <= 0)
            return;

        // start with one-bit pattern
        arr.add("0");
        arr.add("1");

        // Every iteration of this loop generates 2*i codes from previously
        // generated i codes.
        int i, j;
        for (i = 2; i < (1<<n); i = i<<1)
        {
            // Enter the prviously generated codes again in arr[] in reverse
            // order. Nor arr[] has double number of codes.
            for (j = i-1 ; j >= 0 ; j--)
                arr.add(arr.get(j));

            // append 0 to the first half
            for (j = 0 ; j < i ; j++)
                arr.set(j, "0" + arr.get(j));

            // append 1 to the second half
            for (j = i ; j < 2*i ; j++)
                arr.set(j, "1" + arr.get(j));
        }
    }

    public String getLetterString(int index){
        if (index < 0 || index > word.length()){
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<getBitAmount(); i++)
                sb.append("0");
            String grayCode = sb.toString();
            System.err.println("CodecLetterGray[" + moduleNo + "] getLetterGray(int index): index out of range,  return string=\""+ grayCode + "\"");
            return grayCode;
        }//if

        return arr.get(index);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("CLG["+moduleNo+"] :\n");
        for (int i=0; i<=6; i++){
            sb.append("\t" + i + ": \'" + getLetterChar(i) + "\'" + getLetterString(i) + "\n");
        }//for
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

    public void show(){
        System.out.println(toString());
    }

    public String generateInputBus(){
        StringBuilder sb = new StringBuilder();
        for (int i=this.word.length(); i>0; i--){
            sb.append(getLetterString(i) + " ");
        }//for
        sb.setLength(sb.length()-1);
        return sb.toString();
    }
}
