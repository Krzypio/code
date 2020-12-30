public class ClockToGray3bit {
    private static final int WORD_LENGTH = 8;
    private static long moduleAmount = 0;
    private long moduleNo;


    private int nbkInt = 0;
    private int grayInt= 0;
    private String grayStr = "000";
    public ClockToGray3bit() {
        moduleNo = moduleAmount;
        moduleAmount++;
    }
    public void clkSignal(){
        if (nbkInt < WORD_LENGTH-1) nbkInt++;
        else nbkInt = 0;
        nbkToGray();
    }

    public int getNbkInt() {
        return nbkInt;
    }

    public void setNbkInt(int aNbkInt) {
        if (aNbkInt < WORD_LENGTH)
            this.nbkInt = aNbkInt;
        else this.nbkInt = aNbkInt % this.WORD_LENGTH;
        nbkToGray();
    }

    public int getGrayInt(){
        return grayInt;
    }

    public void setGrayInt(int aGrayInt){
        if (aGrayInt < WORD_LENGTH)
            this.grayInt = aGrayInt;
        else this.grayInt = aGrayInt % this.WORD_LENGTH;
        grayToNbk();
    }

    public void setGrayStr(String aGrayStr){
        this.grayInt = valueStrToInt(aGrayStr);
        setGrayInt(this.grayInt);
    }

    public String getGrayStr(){
        return this.grayStr;
    }

    private void nbkToGray(){
        int grayBuffor;
        switch (this.nbkInt) {
            case 0:
                grayBuffor = 0;
                break;
            case 1:
                grayBuffor = 1;
                break;
            case 2:
                grayBuffor = 3;
                break;
            case 3:
                grayBuffor = 2;
                break;
            case 4:
                grayBuffor = 6;
                break;
            case 5:
                grayBuffor = 7;
                break;
            case 6:
                grayBuffor = 5;
                break;
            case 7:
                grayBuffor = 4;
                break;
            default:
                grayBuffor = 0;
                System.err.println("Gray8_nbkToGray(): nbkInt with undefined GrayInt. Set 0");
        }//switch
        this.grayInt = grayBuffor;
        this.grayStr = valueIntToStr(this.grayInt);
    }

    private void grayToNbk(){
        int nbkBuffor;
        switch (this.grayInt) {
            case 0:
                nbkBuffor = 0;
                break;
            case 1:
                nbkBuffor = 1;
                break;
            case 3:
                nbkBuffor = 2;
                break;
            case 2:
                nbkBuffor = 3;
                break;
            case 6:
                nbkBuffor = 4;
                break;
            case 7:
                nbkBuffor = 5;
                break;
            case 5:
                nbkBuffor = 6;
                break;
            case 4:
                nbkBuffor = 7;
                break;
            default:
                nbkBuffor = 0;
                System.err.println("Gray8_grayToNbk(): grayInt with undefined nbkInt. nbkInt Set 0");
        }//switch
        this.nbkInt = nbkBuffor;
        this.grayStr = valueIntToStr(this.grayInt);
    }

    private String valueIntToStr(int aIntValue) {
        String strValue;
        switch (aIntValue) {
            case 0:
                strValue = "000";
                break;
            case 1:
                strValue = "001";
                break;
            case 2:
                strValue = "010";
                break;
            case 3:
                strValue = "011";
                break;
            case 4:
                strValue = "100";
                break;
            case 5:
                strValue = "101";
                break;
            case 6:
                strValue = "110";
                break;
            case 7:
                strValue = "111";
                break;
            default:
                strValue = "000";
                System.err.println("Gray8_valueIntToStr(): aIntValue with undefined strValue, strValue set \"000\"");
        }//switch
        return strValue;
    }

    private int valueStrToInt(String strValue) {
        int intValue;
        switch (strValue) {
            case "000":
                intValue = 0;
                break;
            case "001":
                intValue = 1;
                break;
            case "010":
                intValue = 2;
                break;
            case "011":
                intValue = 3;
                break;
            case "100":
                intValue = 4;
                break;
            case "101":
                intValue = 5;
                break;
            case "110":
                intValue = 6;
                break;
            case "111":
                intValue = 7;
                break;
            default:
                intValue = 0;
                System.err.println("Gray8_valueStrToInt(): strValue with undefined intValue, intValue set 0");
        }//switch
        return intValue;
    }

    public String toString(){
        return "GRAY8["+moduleNo+"] : NbkInt. " + getNbkInt() + " GrayInt. " + getGrayInt() + " GrayStr. " + getGrayStr();
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
}
