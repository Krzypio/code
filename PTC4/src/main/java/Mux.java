public class Mux {
    private static long moduleAmount = 0;
    private final long moduleNo;
    private int bitsPerLetter;

    private String outLetterGray= "";
    private IndexToGray indexToGray;

    private int inPosIndex = -1;
    private String inPosGray = "";

    private InputBus inputBus;
    private Mux() {
        moduleNo = moduleAmount;
        moduleAmount++;
    }

    public Mux(InputBus inputBus) {
        this();
        this.inputBus = inputBus;
        indexToGray = new IndexToGray(this.inputBus.getLetterAmount(), this.inputBus.getBitsPerLetter());
    }

    public void setInPosGray(String inPosGray){
        this.inPosGray = inPosGray;
        this.inPosIndex = indexToGray.getIndex(inPosGray);
        if (this.inPosIndex<inputBus.getLetterAmount()){
            this.outLetterGray = inputBus.getLetter(inPosIndex);
        } else {
            System.err.println("Mux getOutLetterGray(String inPosGray): inPosIndex("+this.inPosIndex+") >= letterAmount("+inputBus.getLetterAmount()+"). Set " + indexToGray.getGrayCode(0));
            this.outLetterGray = indexToGray.getGrayCode(0);
        }//else
    }

    public String toString(){
        return outLetterGray;
    }

    public void show(){
        System.out.println(toString());
    }

    public String getOutLetterGray() {
        return outLetterGray;
    }

    public int getInPosIndex() {
        return inPosIndex;
    }
}
