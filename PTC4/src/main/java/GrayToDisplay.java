public class GrayToDisplay {
    private static long moduleAmount = 0;
    private final long moduleNo;

    private IndexToGray indexToGray;
    private WordAvailable wordAvailable;

    private String inputGray = "";
    private char outputLetter = '_';

    private int letterAmount;
    private int bitsPerLetter;

    public GrayToDisplay(int letterAmount, int bitsPerLetter, WordAvailable wordAvailable) {
        moduleNo = moduleAmount;
        moduleAmount++;

        this.wordAvailable = wordAvailable;

        this.letterAmount = letterAmount;
        this.bitsPerLetter = bitsPerLetter;

        this.indexToGray = new IndexToGray(this.letterAmount, this. bitsPerLetter);
    }

    public String getInputGray() {
        return inputGray;
    }

    public void setInputGray(String inputGray) {
        this.inputGray = inputGray;
        calculateOutputLetter();
    }

    public String getNext(){
        int index = indexToGray.getIndex(inputGray) + 1;
        return indexToGray.getGrayCode(index);
    }

    private void calculateOutputLetter() {
        //gdy kod jest 0 zwroc puste
        if (this.inputGray == indexToGray.getGrayCode(0)){
            this.outputLetter = '_';
            return;
        };

        int index = indexToGray.getIndex(this.inputGray) - 1;   //-1 becouse WordAvaliable doesnt contain '_'

        if (index < wordAvailable.getLetterAmount())
            this.outputLetter = wordAvailable.getAvaliableLetter(index);
        else
        {
            System.err.println("GrayToDisplay[" + moduleNo + "] calculateOutputLetter(): index["+index+"] < wordAvaliable.getLetterAmount(), set char \'_\' and input "+ indexToGray.getGrayCode(0));
            this.outputLetter = '_';
            this.inputGray = indexToGray.getGrayCode(0);
        }//else
    }

    public char getOutputLetter() {
        return outputLetter;
    }

    public void show(){
        System.out.println(outputLetter + " " + inputGray);
    }
}
