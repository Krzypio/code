public class Main {
    private static final int PTC_LAB_ID = 31;
    private static final int LETTER_AMOUNT = 5;
    private static final int BITS_PER_LETTER = 3;
    private static final int DISPLAY_AMOUNT = 8;
    private static final String WORDS_BASE= "ABCEFGHIJLOPS";

    public static void main(String[] args){
        //INICJALIZACJA
        //index to gray
        IndexToGray indexToGray = new IndexToGray(LETTER_AMOUNT, BITS_PER_LETTER);

        //input bus
        InputBus ib = new InputBus(LETTER_AMOUNT, BITS_PER_LETTER, indexToGray.getInputBus(1));

        //clock to gray
        ClockToGray[] ctg = new ClockToGray[DISPLAY_AMOUNT];
        for (int i=0; i<DISPLAY_AMOUNT; i++){
            ctg[i] = new ClockToGray(LETTER_AMOUNT, BITS_PER_LETTER, indexToGray.getGrayCode(i));
        }//for

        //mux
        Mux[] mux = new Mux[DISPLAY_AMOUNT];
        for (int i=0; i<DISPLAY_AMOUNT; i++){
            mux[i] = new Mux(ib);
        }//for

        //word available
        WordAvailable wb = new WordAvailable(WORDS_BASE, LETTER_AMOUNT, PTC_LAB_ID);

        //gray to display
        GrayToDisplay[] gtd = new GrayToDisplay[DISPLAY_AMOUNT];
        for (int i=0; i<DISPLAY_AMOUNT; i++){
            gtd[i] = new GrayToDisplay(LETTER_AMOUNT, BITS_PER_LETTER, wb);
        }

        //display
        DisplaySegm7[] ds = new DisplaySegm7[DISPLAY_AMOUNT];
        for (int i=0; i<DISPLAY_AMOUNT; i++){
            ds[i] = new DisplaySegm7();
        }

        for(int clk=0; clk<10; clk++){
            //SET
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<DISPLAY_AMOUNT; i++){
                String a = ctg[i].toStringShort();
                mux[i].setInPosGray(a);
                String b = mux[i].getOutLetterGray();
                gtd[i].setInputGray(b);
                char c = gtd[i].getOutputLetter();
                sb.append(c);

                ds[i].setInput(c);
                sb.append(" " + ds[i].toStringShort() + "; ");
                //clock
                ctg[i].next();
            }//for i
            sb.setLength(sb.length()-2);
            System.out.println(clk + ": " + sb.toString());
        }//for clk

    }//main
}
