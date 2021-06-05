public class Start {
    public static void main(String[] args) throws Exception {
        String input =
                "1000101" +
                        "0110101" +
                        "0100001" +
                        "0101101" +
                        "0100111" +
                        "0010001" +
                        "0100101";

        Maze maze = new Maze(input, 2, 5);
        System.out.println(maze);

        System.out.println(convert("BAcAb"));
    }

    static int ABS(char ch) {
        int ascii = (int) ch;
        int A = (int) 'A';
        int a = (int) 'a';
        if (Character.isUpperCase(ch))
            return ascii - A;
        else
            return ascii - a;
    }//ABS()

    static int convert(String input) {
        double numerator = 0;
        double denominator = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (!Character.isAlphabetic(ch))
                return -1;
            if (Character.isUpperCase(ch))
                numerator += ABS(ch);
            else
                denominator += ABS(ch);
        }//for
        if (0 == denominator)
            return -1;
        return (int) Math.round(numerator/denominator);
    }//convert()
}

