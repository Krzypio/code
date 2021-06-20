import javax.annotation.processing.SupportedSourceVersion;

public class Main {
    public static void main(String[] args) {
        System.out.println("Robotics");
        //exercise1();
        //exercise2();
        //zadanie1();
        //zadanie2();
        //zadanie3();
        //zadanie4();
        egzamin4();
        System.out.println("Robotics end");
    }

    private static void egzamin4() {
        OrientationMatrix orientation = new OrientationMatrix();
        orientation.rotateZ(90);

        System.out.println("orientation:\n" + orientation);

        PositionMatrix posBeg = new PositionMatrix(1, 2, 3);
        System.out.println("posBeg:\n" + posBeg);

        PositionMatrix posEnd = new PositionMatrix(orientation, posBeg);
        System.out.println("posEnd:\n" + posEnd);
    }

    private static void zadanie4() {
        double d1 = 9;
        double l1 = 1;
        double l2 = 1;
        double theta2 = 1;
        double theta3 = 1;
        DenavitHartenbergTable dh = new DenavitHartenbergTable(3);
        dh.addAxis(1, 0, 0, d1, 0);
        dh.addAxis(2, l1, 0, 0, theta2);
        dh.addAxis(3, l2, 0, 0, theta3);

        TransformationMatrix T_01 = dh.get(0).getTransformation();
        TransformationMatrix T_12 = dh.get(1).getTransformation();
        TransformationMatrix T_23 = dh.get(2).getTransformation();

        System.out.println("dh:\n" + dh.toString());
        System.out.println("T_01:\n" + T_01);
        System.out.println("T_12:\n" + T_12);
        System.out.println("T_23:\n" + T_23);

        TransformationMatrix T_03 = new TransformationMatrix(T_01);
        T_03.multiply(T_12);
        T_03.multiply(T_23);
        System.out.println("T_03:\n" + T_03);
    }

    private static void zadanie3() {
        System.out.println("a)---------------");

        TransformationMatrix T_WB_not = new TransformationMatrix(new double[][]{ {0,-1,0,3}, {1,0,0,-3}, {0,0,1,1}, {0,0,0,1} });
        System.out.println("T_WB_not:\n" + T_WB_not);

        TransformationMatrix T_BW = T_WB_not.getInverse();
        System.out.println("T_BW:\n" + T_BW);

        TransformationMatrix T_WG = new TransformationMatrix(new double[][]{ {1,0,0,2}, {0,0,-1,5}, {0,1,0,1}, {0,0,0,1} });
        System.out.println("T_WG:\n" + T_WG);

        TransformationMatrix T_BG = new TransformationMatrix(T_BW);
        T_BG.multiply(T_WG);
        System.out.println("T_BG:\n" + T_BG);
        System.out.println("b)---------------");
        T_BG.eulerAnglesZXZ();
    }

    private static void zadanie2() {
        //Robotyka
        OrientationMatrix orientation = new OrientationMatrix();
        orientation.rotateZ(90);

        System.out.println("orientation:\n" + orientation);

        PositionMatrix posBeg = new PositionMatrix(1, 2, 3);
        System.out.println("posBeg:\n" + posBeg);

        PositionMatrix posEnd = new PositionMatrix(orientation, posBeg);
        System.out.println("posEnd:\n" + posEnd);

    }

    private static void zadanie1() {
        //Robotyka zadanie 1
        PositionMatrix r_P = new PositionMatrix(1, -2, 2);

        PositionMatrix d_BS = new PositionMatrix(2, 5, 0);
        PositionMatrix d_PS = new PositionMatrix(3, -6, 4);

        OrientationMatrix R_BS = new OrientationMatrix(new double[][]{ {0, -1, 0}, {1, 0, 0}, {0, 0, 1} });
        OrientationMatrix R_PS = new OrientationMatrix(new double[][]{ {-1, 0, 0}, {0, 1, 0}, {0, 0, -1} });


        System.out.println("r_P:\n" + r_P);
        System.out.println("d_BS:\n" + d_BS);
        System.out.println("d_PS:\n" + d_PS);
        System.out.println("R_BS:\n" + R_BS);
        System.out.println("R_PS:\n" + R_PS);

        TransformationMatrix T_BS = new TransformationMatrix(R_BS, d_BS);
        TransformationMatrix T_PS = new TransformationMatrix(R_PS, d_PS);
        TransformationMatrix T_SP = T_PS.getInverse();

        System.out.println("T_BS:\n" + T_BS);
        System.out.println("T_PS:\n" + T_PS);
        System.out.println("T_SP:\n" + T_SP);

        TransformationMatrix T_BP = new TransformationMatrix();
        T_BP.multiply(T_BS);
        T_BP.multiply(T_SP);
        System.out.println("T_BP:\n" + T_BP);

        PositionMatrix r_B = new PositionMatrix(T_BP, r_P);
        System.out.println("r_B:\n" + r_B);
    }

    private static void exercise2() {
        System.out.println("EXERCISE2----------------------------------\n");

        TransformationMatrix cTo = new TransformationMatrix(new double[][] { {0, 0, 1, 2}, {1, 0, 0, 2}, {0, 1, 0, 4}, {0, 0, 0, 1} });
        TransformationMatrix gTc = new TransformationMatrix(new double[][] { {0, 0, -1, 3}, {0, -1, 0, 0}, {-1, 0, 0, 5}, {0, 0, 0, 1} });
        TransformationMatrix gTa = new TransformationMatrix(new double[][] { {0, -1, 0, 0}, {1, 0, 0, 0}, {0, 0, 1, 4}, {0, 0, 0, 1} });
        TransformationMatrix aTg = gTa.getInverse();

        TransformationMatrix aTo = new TransformationMatrix();
        aTo.multiply(aTg);
        aTo.multiply(gTc);
        aTo.multiply(cTo);

        System.out.println("cTo:\n" + cTo);
        System.out.println("gTc:\n" + gTc);
        System.out.println("gTa:\n" + gTa);
        System.out.println("aTg:\n" + aTg);
        System.out.println("aTo:\n" + aTo);

        System.out.println("END_EXERCISE2----------------------------\n");

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
    }

}