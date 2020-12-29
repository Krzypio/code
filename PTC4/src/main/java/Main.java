public class Main {
    public static void main(String[] args){

        SevenSegmentDisplay ssd = new SevenSegmentDisplay();
        ssd.setInput('i');
        System.out.println(ssd.toString());
        ssd.setInput('A');
        System.out.println(ssd.toString());
        ssd.setInput('i');
        System.out.println(ssd.toString());
        ssd.setInput("0000000");
        System.out.println(ssd.toString());
        ssd.setInput("1111100");
        System.out.println(ssd.toString());
        ssd.setInput("1110111");
        System.out.println(ssd.toString());
    }//main
}
