public class Solution {
    public int solution(int[] A){
        int segmentsAmount = A.length;
        int hillValleyCounter = 0;

        int prevHeight = -1;
        int actualHeight = -1;
        int nextHeight = -1;

        int direction = 0; //1 is up, -1 is down

        for (int i=0; i<segmentsAmount; i++){
            //Establish values
            //prev
            if(i-1<0)
                prevHeight = -1;
            else
                prevHeight = A[i-1];
            //actual
            actualHeight = A[i];
            //next
            if(i+1>=segmentsAmount)
                nextHeight = -1;
            else
                nextHeight = A[i+1];

            //last field
            if (nextHeight == -1 && actualHeight != prevHeight){
                hillValleyCounter++;
                return hillValleyCounter;
            }

            int change = nextHeight-actualHeight;

            if (change>0 && direction != 1){
                hillValleyCounter++;
                direction = 1;
            } else if (change<0 && direction != -1){
                hillValleyCounter++;
                direction = -1;
            }
        }//for
        return hillValleyCounter;
    }//solution
}
