import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Maze{

    private static int ROW;
    private static int COL;
    private static String input;
    private int sideSize;
    private static Point source, destination;

    private int[][] mat;

    public Maze(String input, int ginX, int goutX) throws Exception {
        checkAndSetValues(input, ginX, goutX);
        fillTable();
       this.ROW = this.sideSize;
        this.COL = this.sideSize;
        source = new Point(ginX, 0);
        destination = new Point(goutX, sideSize-1);
        int dist = BFS(mat, source, destination);
        if (dist != -1)
            System.out.println("Shortest Path is " + dist);
        else
            System.out.println("Shortest Path doesn't exist");
    }

    private void fillTable() {
        for(int i=0; i<input.length(); i++){
            int y = i/sideSize;
            int x = i%sideSize;
            if (this.input.charAt(i) == '0')
                mat[y][x] = 0;
            else
                mat[y][x] = 1;
        }//for i
    }

    private void checkAndSetValues(String input, int ginX, int goutX) throws Exception {
        //delete white spaces
        String inputWithoutSpaces = input.replaceAll("\\s+", "");
        //delete other than 0 and 1
        String inputWithoutWrong = inputWithoutSpaces.replaceAll("[^01]", "");
        int inputWithoutSpacesLength = inputWithoutSpaces.length();
        int inputWithoutWrongLength = inputWithoutWrong.length();
        //check if input values are ok
        if (inputWithoutSpacesLength != inputWithoutWrongLength)
            throw new Exception("Maze:input contains not only 0 and 1");
        //calculate size from input
        int estimatedSize = (int)Math.sqrt(inputWithoutWrongLength);
        //check if estimatedSize is correct
        if (estimatedSize*estimatedSize != inputWithoutWrongLength)
            throw new Exception("Maze: input is not n*n size");
        if (estimatedSize<1)
            throw new Exception("Maze: input size i less than 1");
        //check gin and gout
        if (ginX<0 || ginX>=estimatedSize) throw new Exception("Maze: gin must be between 0 and sideSize-1");
        if (goutX<0 || goutX>=estimatedSize) throw new Exception("Maze: gout must be between 0 and sideSize-1");

        //set values
        this.input = inputWithoutWrong;
        this.sideSize = estimatedSize;
        this.mat = new int [sideSize][sideSize];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int y=0; y<sideSize; y++){
            for(int x=0; x<sideSize; x++){
                int value = mat[y][x];
                if (value<=0)
                    sb.append(0);
                else if (value==1)
                    sb.append(1);
                else if (value==2)
                    sb.append('X');
            }//for x
            sb.append("\n");
        }//for y
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

    //Dijkstra BFS

    //To store matrix cell coordinates
    private static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }//constructor
    }//Point class

    //A Data Structure for queue in BFS
    static class QueueNode{
        Point pt;   //The coordinates of a cell
        int dist;   //cell's distance of from the source
        public QueueNode(Point pt, int dist){
            this.pt = pt;
            this.dist = dist;
        }//constructor
    }//QueueNode class


    //check whether given cell (row, col) is a valid cell or not
    static boolean isValid(int row, int col){
        //return true if row number and column number is in range
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }//isValid

    //These arrays are used to get row and column numbers of 4 neughbours of a given cell
    static int rowNum[] = {-1, 0, 0, 1};
    static int colNum[] = {0, -1, 1, 0};

    //function to find the shortest path between a given source cell to a destination cell
    static int BFS(int mat[][], Point src, Point dest){
        //check source and destination cell of the matrix have value 0
        if (mat[src.y][src.x] != 0 || mat[dest.y][dest.x] != 0)
            return -1;

        //Create a queue for BFS
        Queue<QueueNode> q = new LinkedList<>();

        //Distance of source cell is 0
        QueueNode s = new QueueNode(src, 0);
        q.add(s); //Enqueue source cell
        //mark starting cell as -1
        mat[src.y][src.x]=-1;
        
        //Do a BFS starting from source cell
        while(!q.isEmpty()){
            QueueNode curr = q.peek();
            Point pt = curr.pt;

            //if we have reached the destination cell, we are done
            if(pt.x == dest.x && pt.y == dest.y){
                int traceValue;
                while(pt.x != source.x || pt.y != source.y){
                    traceValue = mat[pt.y][pt.x];
                    mat[pt.y][pt.x] = 2;    //mark path by 2
                    //search for traceValue+1
                    for(int i = 0; i<4; i++) {
                        int row = pt.y + rowNum[i];
                        int col = pt.x + colNum[i];
                        if(isValid(row, col) && mat[row][col] == traceValue+1){
                            pt.y = row;
                            pt.x = col;
                            traceValue++;
                            break;
                        }

                    }//for
                }
                mat[pt.y][pt.x] = 2;    //mark path by 2
                return curr.dist;
            }

            //Otherwise dequeue the front cell in the queue and enqueue its adjacent cells
            q.remove();

            for(int i = 0; i<4; i++){
                int row = pt.y+rowNum[i];
                int col = pt.x+colNum[i];

                //if adjacent cell is valid, has path and not visited yet (0 value), enqueue it.
                if(isValid(row, col) && 0 == mat[row][col]){
                    //mark cell as visited and enqueue it
                    mat[row][col] = -(curr.dist+1+1);
                    QueueNode adjCell = new QueueNode(new Point(col, row), curr.dist+1);
                    q.add(adjCell);
                }//if
            }//for
        }//while
        //Return -1 if destination cannot be reached
        return -1;
    }//BFS
}
