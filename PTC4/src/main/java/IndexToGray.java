import java.util.ArrayList;

public class IndexToGray {
    private static long moduleAmount = 0;
    private final long moduleNo;

    private int letterAmount=0;
    private int bitsPerLetter=0;
    private int maxDifferentNumber=0;

    private int posNo=0;

    private ArrayList<String> grayArr = new ArrayList<>();

    private IndexToGray() {
        moduleNo = moduleAmount;
        moduleAmount++;
    }

    public IndexToGray(int letterAmount, int bitsPerLetter) {
        this();
        verifyAndSet(letterAmount, bitsPerLetter);
        generateGrayArr(this.bitsPerLetter);
    }

    private void verifyAndSet(int letterAmount, int bitsPerLetter){
        //bits<0
        if (bitsPerLetter < 0) {
            this.bitsPerLetter = 0;
            System.err.println("IndexToGray[" + moduleNo + "] verifyAndSet : bitsPerLetter < 0, set 0");
        } else {
            this.bitsPerLetter = bitsPerLetter;
        }//else

        setMaxDifferentNumber(calculateMaxNumber(bitsPerLetter));
        if (letterAmount < 0) {
            this.letterAmount = 0;
            System.err.println("IndexToGray[" + moduleNo + "] : letterAmount < 0, set 0");
        } else {
            if (letterAmount>maxDifferentNumber){
                System.err.println("IndexToGray[" + moduleNo + "] : letterAmount("+letterAmount+")>maxDifferentNumber("+this.maxDifferentNumber+"), set letterAmount=" + maxDifferentNumber);
                this.letterAmount = maxDifferentNumber;
            } else {
                this.letterAmount = letterAmount;
            }//if (letterAmount>maxDifferentNumber) else
        }//if (letterAmount < 0) else
    }

    private void generateGrayArr(int bitsPerLetter)
    {
        grayArr.clear();
        if (bitsPerLetter <= 0) return;

        grayArr.add("0");
        grayArr.add("1");

        int i, j;
        for (i = 2; i < (1<<bitsPerLetter); i = i<<1)
        {
            for (j = i-1 ; j >= 0 ; j--)
                grayArr.add(grayArr.get(j));
            for (j = 0 ; j < i ; j++)
                grayArr.set(j, "0" + grayArr.get(j));
            for (j = i ; j < 2*i ; j++)
                grayArr.set(j, "1" + grayArr.get(j));
        }//for i
    }//generateGrayArr

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("CTG["+moduleNo+"] : ");
        for (int i=0; i<maxDifferentNumber; i++){
            sb.append("["+i+"]" + grayArr.get(i) + " ");
        }
        /*sb.append("\n\tAva : ");
        for (int i=0; i<letterAmount && i<maxDifferentNumber; i++){
            sb.append("["+i+"]" + grayArr.get(i) + " ");
        }*/
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

    public void show(){
        System.out.println(toString());
    }

    private int calculateMaxNumber(int bitsPerLetter){
        int value = (int) Math.pow(2, bitsPerLetter);
        return value;
    }

    private void setMaxDifferentNumber(int value){
        this.maxDifferentNumber = value;
    }

    public String getGrayCode(int index){
        int posNo;
        if (index<0)
            posNo = maxDifferentNumber + index%maxDifferentNumber;
        else
            posNo = index%maxDifferentNumber;
        return grayArr.get(posNo);
    }
    
    public int getIndex(String grayCode){
        if (grayArr.contains(grayCode))
            return grayArr.indexOf(grayCode);
        else {
            System.err.println("IndexToGray[" + moduleNo + "] getIndex(String grayCode): grayArr[] do not contain "+ grayCode +", return index -1");
            return -1;
        }
    }

    public String getInputBus(int startPos){
        startPos = startPos%maxDifferentNumber;
        //pomija 0
        StringBuilder sb = new StringBuilder();
        for (int i=this.letterAmount-1; i>=0; i--){

            sb.append(getGrayCode(startPos+i) + " ");
        }//for
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

    public int getMaxIndex() {
        return maxDifferentNumber-1;
    }
}
