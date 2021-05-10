public class Start {
    public static void main(String[] args) throws Exception {

        Maze maze = new Maze("1000101"+
                "0110101"+
              "  0100001"+
               " 0101101"+
              "  0100111"+
            "    0010001"+
                "0100101", 2, 5);

        System.out.println(maze);
    }
}
