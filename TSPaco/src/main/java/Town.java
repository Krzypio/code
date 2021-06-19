public class Town {
    private String name;
    private int x;
    private int y;

    public Town(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate Euclidean distance between our and destinationTown
     * @param destinationTown
     * @return distance to destinationTown
     */
    public double measureDistance(Town destinationTown){
        int xDelta = this.x - destinationTown.getX();
        int yDelta = this.y = destinationTown.getY();
        return Math.sqrt( Math.pow(xDelta,2) + Math.pow(yDelta,2) );
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
