import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    private static int[][] tab;   //[x][y]
    private static int size;
    private static int possibleMoveX[] = {-1, 0, 0, 1};
    private static int possibleMoveY[] = {0, -1, 1, 0};
    private static Point in, out;


    private static class Point{
        private int x;
        private int y;
        public Point (int x, int y){
            this.x = x;
            this.y = y;
        }//constructor
    }//Point class

    private static class QueueNode{
        private Point pt;
        private int dist;
        public QueueNode(Point pt, int dist){
            this.pt = pt;
            this.dist = dist;
        }//constructor
    }//QueueNode class

    private static boolean isValid(int x, int y){
        return (x >= 0) && (x < size) && (y >= 0) && (y < size);
    }//isValid()

    public Maze(String input, int gin, int gout) throws Exception {
        String truncInput = checkPrepareInput(input, gin, gout);
        fillTab(truncInput);
        in = new Point(gin, 0);
        out = new Point(gout, size-1);

        int dist = howFarBfs();
        if (dist != -1)
            System.out.println("Shortest Path is " + dist);
        else
            System.out.println("Shortest Path doesn't exist");
    }//constructor

    private String checkPrepareInput(String input, int gin, int gout) throws Exception {
        //delete white spaces
        String inputWithoutSpaces = input.replaceAll("\\s+", "");
        //delete other than 0 and 1
        String inputWithoutWrong = inputWithoutSpaces.replaceAll("[^01]", "");
        int inputWithoutSpacesLength = inputWithoutSpaces.length();
        int inputWithoutWrongLength = inputWithoutWrong.length();
        //check if input values are ok
        if (inputWithoutSpacesLength != inputWithoutWrongLength)
            throw new Exception("Maze: checkTrunctInput: input contains not only 0 and 1");
        //calculate size from input
        int estimatedSize = (int)Math.sqrt(inputWithoutWrongLength);
        //check if estimatedSize is correct
        if (estimatedSize*estimatedSize != inputWithoutWrongLength)
            throw new Exception("Maze: checkTrunctInput: input is not n*n size");
        if (estimatedSize<1)
            throw new Exception("Maze: checkTrunctInput: input size is less than 1");
        //check gin and gout
        if (gin<0 || gin>=estimatedSize) throw new Exception("Maze: gin must be between 0 and sideSize-1");
        if (gout<0 || gout>=estimatedSize) throw new Exception("Maze: gout must be between 0 and sideSize-1");

        //set values
        this.size = estimatedSize;
        this.tab = new int [size][size];
        return inputWithoutWrong;
    }//checkPrepareInput

    private void fillTab(String input) {
        for(int i=0; i<input.length(); i++){
            int y = i/size;
            int x = i%size;
            if (input.charAt(i) == '0')
                tab[x][y] = 0;
            else
                tab[x][y] = 1;
        }//for i
    }//fillTab()

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int x=0; x<size; x++){
            for(int y=0; y<size; y++){
                int value = tab[y][x];
                if (value<=0)
                    sb.append(0);
                else if (value==1)
                    sb.append(1);
                else if (value==2)
                    sb.append('X');
                //sb.append(tab[x][y]);
            }//for x
            sb.append("\n");
        }//for y
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

    public static int howFarBfs(){
        //in and out points are passable
        if (tab[in.x][in.y] != 0 || tab[out.x][out.y] != 0)
            return -1;

        //Create a queue
        Queue<QueueNode> q = new LinkedList<>();

        //Distance of source cell is 0
        QueueNode s = new QueueNode(in, 0);
        q.add(s); //Enqueue source cell

        //mark starting cell as -1 for backtracking
        tab[in.x][in.y] = -1;

        //starting from source cell
        while(!q.isEmpty()){
            QueueNode curr = q.peek();//Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
            Point pt = curr.pt;

            //if we have reached the destination cell, backtrack the route
            if(pt.x == out.x && pt.y == out.y){
                int traceValue;
                while(pt.x != in.x || pt.y != in.y){
                    traceValue = tab[pt.x][pt.y];
                    tab[pt.x][pt.y] = 2;    //mark path by 2
                    //search for traceValue+1 (backtracking)
                    for(int i = 0; i<4; i++) {
                        int x = pt.x + possibleMoveX[i];
                        int y = pt.y + possibleMoveY[i];
                        if(isValid(x, y) && tab[x][y] == traceValue+1){
                            pt.x = x;
                            pt.y = y;
                            traceValue++;
                            break;
                        }//if
                    }//for
                }
                tab[pt.x][pt.y] = 2;    //mark path by 2
                return curr.dist;
            }
            //If its not the end, dequeue front cell and enqueue its adjacent cells
            q.remove();

            for(int i = 0; i<4; i++){
                int x = pt.x+possibleMoveX[i];
                int y = pt.y+possibleMoveY[i];

                //if adjacent cell is valid, has path and not visited yet (0 value), enqueue it.
                if(isValid(x, y) && 0 == tab[x][y]){
                    //mark cell as visited and enqueue it
                    tab[x][y] = -(curr.dist+1+1);   //mark the route by negative values
                    QueueNode adjCell = new QueueNode(new Point(x, y), curr.dist+1);
                    q.add(adjCell);
                }//if
            }//for
        }//while
        //Return -1 if there is no way
        return -1;
    }//BFS
}//end
