public class WordAvailable {
    private static long moduleAmount = 0;
    private final long moduleNo;

    //to include
    private String wordsBase = "";
    private int letterAmount = 0;
    private int ptcLabId = 0;

    //to calculate
    private int firstLetterNo = 0;
    private String wordsAvaliable = "";

    private WordAvailable() {
        moduleNo = moduleAmount;
        moduleAmount++;
    }//WordBase

    public WordAvailable(String wordBase, int letterAmount, int ptcLabId){
        this();
        setWorldBase(wordBase);
        setLetterAmount(letterAmount);
        setPtcLabId(ptcLabId);
        calculateWordsAvaliable();
    }

    public int setWorldBase(String wordBase) {
        if(wordBase.length() == 0) {
            this.wordsBase = "";
            System.err.println("WordBase[" + moduleNo + "] setWorldBase(String wordBase): wordsBaseStr.length() < 0, set wordBase=\"\"");
            return 1;
        }

        this.wordsBase = wordBase;
        return 0;
    }//setWorldBase

    public int setPtcLabId(int ptcLabId) {
        if (ptcLabId<0) {
            this.ptcLabId = 0;
            calculateFirstLetterNo();
            System.err.println("WordBase[" + moduleNo + "] setPtcLabId(int ptcLabId): ptcLabId < 0, set ptcLabId=0");
            return 1;
        }//this

        this.ptcLabId = ptcLabId;
        calculateFirstLetterNo();
        return 0;
    }//setPtcLabId

    public int setLetterAmount(int letterAmount) {
        if (letterAmount<0) {
            this.letterAmount = 0;
            System.err.println("WordBase[" + moduleNo + "] setLetterAmount(int letterAmount): letterAmount < 0, set letterAmount=0");
            return 1;
        }//this

        this.letterAmount = letterAmount;
        return 0;
    }//letPtcLabId

    private void calculateFirstLetterNo() {
        setFirstLetterNo(1 + ptcLabId%13);
        calculateWordsAvaliable();
    }

    private void calculateWordsAvaliable(){
        StringBuilder sb = new StringBuilder();

        int start = (firstLetterNo-1)%this.wordsBase.length();
        for(int i=0; i<this.letterAmount; i++) {
            int positionNo = (start+i)%this.wordsBase.length();
            sb.append(this.wordsBase.charAt(positionNo));
        }
        this.wordsAvaliable = sb.toString();
    }

    private int setFirstLetterNo(int firstLetterNo){
        if (firstLetterNo<0) {
            this.firstLetterNo = 0;
            System.err.println("WordBase[" + moduleNo + "] setFirstLetterNo(int firstLetterNo): firstLetterNo < 0, set firstLetterNo=0");
            return 1;
        }//this

        this.firstLetterNo = firstLetterNo;
        return 0;
    }

    public int setWordsAvaliable(String wordsAvaliable) {
        if(wordsAvaliable.length() == 0) {
            this.wordsBase = "";
            System.err.println("WordBase[" + moduleNo + "] setWordsAvaliable(String wordsAvaliable): wordsAvaliable.length() < 0, set wordsAvaliable=\"\"");
            return 1;
        }

        this.wordsAvaliable = wordsAvaliable;
        return 0;
    }//setWorldBase

    //GETTERS
    public long getModuleAmount() {
        return moduleAmount;
    }

    public long getModuleNo() {
        return moduleNo;
    }

    public String getWordsBase() {
        return wordsBase;
    }

    public int getLetterAmount() {
        return letterAmount;
    }

    public int getPtcLabId() {
        return ptcLabId;
    }

    public int getFirstLetterNo() {
        return firstLetterNo;
    }

    public String getWordsAvaliable() {
        return wordsAvaliable;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("WA["+moduleNo+"]: ");
        sb.append("wordsBase=" +getWordsBase() + ", letterAmount=" + getLetterAmount() + ", " + "ptcLabId="+ getPtcLabId() + ", firstLetterNo=" + getFirstLetterNo());
        sb.append("\n\t wordsAvaliable=" + getWordsAvaliable());
        return sb.toString();
    }

    public String toStringShort(){
        return "["+moduleNo+"]" + getWordsAvaliable();
    }

    public void show(){
        System.out.println(toString());
    }

    public char getAvaliableLetter(int index){
        if (index < 0  || index >= wordsAvaliable.length()){
            System.err.println("WordBase[" + moduleNo + "] getAvaliableLetter(int index): index("+index+") <0 or >=wordsAvaliable.length("+wordsAvaliable.length()+") return value ' '");
            return '_';
        }

        return wordsAvaliable.charAt(index);
    }
}
