public class Main {
    public static void main(String[] args) {
        Human krzysztof = new Human();
        for(int i=0; i<30; i++)
            krzysztof.aging();
        System.out.println("Wiek Krzysztofa to " + krzysztof.getAge() + " lat.");
    }
}
        /*System.out.println("Robotics");

        OrientationMatrix aRb = new OrientationMatrix();
        aRb.rotateY(30);
        aRb.rotateX(60);
        aRb.rotateZ(30);

        PositionMatrix aPBorg = new PositionMatrix(10, -5, 4);

        TransformationMatrix aTb = new TransformationMatrix(aRb, aPBorg);

        PositionMatrix bP = new PositionMatrix(6, -4, 1);
        ;

        PositionMatrix aP = new PositionMatrix(aTb, bP);

        System.out.println("aRb: \n" + aRb);
        System.out.println("aPBorg: \n" + aPBorg);
        System.out.println("aTb: \n" + aTb);
        System.out.println("bP: \n" + bP);
        System.out.println("aP: \n" + aP);
    }*/