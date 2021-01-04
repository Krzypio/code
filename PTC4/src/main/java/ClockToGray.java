public class ClockToGray {
    private static long moduleAmount = 0;
    private final long moduleNo;

    private int letterAmount=0;
    private int bitsPerLetter=0;

    private IndexToGray itg;

    private String inputGray;
    private String outputGray;

    private String startInputGray;
    private int startInputIndex;

    private ClockToGray(){
        moduleNo = moduleAmount;
        moduleAmount++;
    }

    private ClockToGray(int letterAmount, int bitsPerLetter){
        this();

        this.letterAmount = letterAmount;
        this.bitsPerLetter = bitsPerLetter;

        this.itg = new IndexToGray(this.letterAmount, this. bitsPerLetter);
    }

    public ClockToGray(int letterAmount, int bitsPerLetter, String startGrayValue) {
        this(letterAmount, bitsPerLetter);

        if (itg.getIndex(startGrayValue)<0){
            System.err.println("ClockToGray (...String startGrayValue): startGrayValue("+startGrayValue+") out of boundary(maxIndex="+itg.getMaxIndex()+"). Set "+itg.getGrayCode(0)+".");
            this.startInputGray = itg.getGrayCode(0);
            this.startInputIndex = 0;
            this.inputGray = this.startInputGray;
        } else {
            this.startInputGray = startGrayValue;
            this.inputGray = this.startInputGray;
            this.startInputIndex = itg.getIndex(this.startInputGray);
        }
        this.outputGray = inputGray;
    }//ClockToGray

    public ClockToGray(int letterAmount, int bitsPerLetter, int startGrayInt) {
        this(letterAmount, bitsPerLetter);

        if (itg.getMaxIndex()<startGrayInt){
            System.err.println("ClockToGray (...int startGrayInt): startGrayInt("+startGrayInt+") out of boundary(maxIndex="+itg.getMaxIndex()+"). Set 0.");
            this.startInputIndex = 0;
            this.startInputGray = itg.getGrayCode(0);
            this.inputGray = this.startInputGray;
        } else {
            this.startInputIndex = startGrayInt;
            this.startInputGray = itg.getGrayCode(this.startInputIndex);
            this.inputGray = this.startInputGray;
        }
        this.outputGray = inputGray;
    }//ClockToGray

    private void calculateOutput(){
        this.inputGray = this.outputGray;
        int index = itg.getIndex(this.inputGray) + 1;
        this.outputGray = itg.getGrayCode(index);
    }

    public String next(){
        calculateOutput();
        return this.outputGray;
    }

    public String toString(){
        return "CTG["+moduleNo+"]: startIndex="+startInputIndex+" startGray="+startInputGray+" in=" + this.inputGray + " out=" + this.outputGray ;
    }

    public void show(){
        System.out.println(toString());
    }

    public String toStringShort(){
        return this.outputGray;
    }
}
