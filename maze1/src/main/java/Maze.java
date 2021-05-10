import java.util.List;
import java.util.Queue;

public class Maze {
    private static int tab[][]; //tab[y][x]
    private static int size;
    private static int gin, gout; //, tab[n-1][gout]

    //możliwe ruchy
    private static int[] movesX= {1, -1, 0, 0};
    private static int[] movesY= {0, 0, 1, -1};

    private static int mark = 0; //do śledzenia wstecz

    //klasa punktu
    private static class Point{
        private int y;
        private int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    //mozliwe ruchy
    private static Point[] moves = {new Point(0,1),
            new Point(0,-1), new Point(1,0),
            new Point(-1,0)};
    //poczatek i koniec
    private static Point pointIn, pointOut;

    public static void main(String[] args) {
        System.out.println("Maze");
        tab = new int[][]{
                {1, 0, 0, 0, 1, 0, 1},
                {0, 1, 1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 1, 1, 0, 1},
                {0, 1, 0, 0, 1, 1, 1},
                {0, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 0, 1, 0, 1}
        };//tab
        gin = 2;
        gout = 5;

        pointIn = new Point(0, gin);
        pointOut = new Point (size-1, gout);

        size = tab.length;
        System.out.println(tabToString()+"\n");
    }//main

    static int how_far(){
        //czy początek i koniec nie są przypadkiem ścianą
        if(1 == tab[pointIn.y][pointIn.x] || 1 == tab[pointOut.y][pointOut.x])
            return -1;
        //oznaczamy odwiedzoe pole ujemna wartoscia
        tab[0][gin] = --mark;
    }

    public static String tabToString() {
        StringBuilder sb = new StringBuilder();
        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                sb.append(tab[y][x]);   //wyswietlanie x i y odwrotnie by wyglądało tak samo jak input
            }//x
            sb.append('\n');
        }//y
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

    //Czy w tabicy istnieje takie miejsce
    static boolean isValid(int x, int y){
        return x>=0 && x<size && y>=0 && y<size;
    }
}
