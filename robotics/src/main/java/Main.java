public class Main {
    public static void main(String[] args) {
        System.out.println("Robotics");
        exercise1();
        //exercise2();
        System.out.println("Robotics end");

    }

    private static void exercise2() {

    }

    public static void exercise1(){
        System.out.print("EXERCISE1----------------------------------\n");
        OrientationMatrix aRb = new OrientationMatrix();
        aRb.rotateY(30);
        aRb.rotateX(60);
        aRb.rotateZ(30);

        PositionMatrix aPBorg = new PositionMatrix(10, -5, 4);

        TransformationMatrix aTb = new TransformationMatrix(aRb, aPBorg);

        PositionMatrix bP = new PositionMatrix(6, -4, 1);

        PositionMatrix aP = new PositionMatrix(aTb, bP);

        System.out.println("aRb: \n" + aRb);
        System.out.println("aPBorg: \n" + aPBorg);
        System.out.println("aTb: \n" + aTb);
        System.out.println("bP: \n" + bP);
        System.out.println("aP: \n" + aP);
        System.out.print("END_EXERCISE1----------------------------\n");

        System.out.println("aTb: \n" + aTb);
        System.out.println("bTa: \n" + aTb.getInverse());
        System.out.println("aTb: \n" + aTb.getInverse().getInverse());
    }

}