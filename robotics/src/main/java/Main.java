public class Main {
    public static void main(String[] args) {
        System.out.println("Robotics");
        OrientationMatrix ArB = new OrientationMatrix();
        ArB.setBetaDegreesY(30);
        ArB.setAlphaDegreesX(60);
        ArB.setGammaDegreesZ(30);
        System.out.println(ArB);
    }
}
