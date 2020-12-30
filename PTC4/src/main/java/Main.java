public class Main {
    private static final int PTC_LAB_ID = 31;
    private static final int LETTER_AMOUNT = 5;
    private static final String WORDS_BASE= "ABCEFGHIJLOPS";

    public static void main(String[] args){
        WordAvaliable wb = new WordAvaliable(WORDS_BASE, LETTER_AMOUNT, PTC_LAB_ID);
        wb.show();

        CodecLetterGray clg = new CodecLetterGray(wb.getWordsAvaliable(), 3);
        System.out.println(clg.getWord());
        for (int i=0; i<=6; i++){
            System.out.println(i + ": lc " + clg.getLetterChar(i) + " ls " + clg.getLetterString(i));
        }

    }//main
}
