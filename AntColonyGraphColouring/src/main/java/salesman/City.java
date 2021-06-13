package salesman;

public class City {
    private int id;
    private int x;
    private int y;

    public City(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public double measureDistance(City city){
        int xDelta = Math.abs( this.x - city.getX() );
        int yDelta = Math.abs( this.y = city.getY() );
        return Math.sqrt( Math.pow(xDelta,2) + Math.pow(yDelta,2) );
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return Integer.toString(id);// + " " + Integer.toString(x) + " " + Integer.toString(y);
    }
}
