public class DenavitHartenbergAxis {
    int axis_i;
    double a_i_1;
    double alpha_i_1;
    double d_i;
    double theta_i;

    /**
     *
     * @param axis_i idetification number of axis
     * @param a_i_1 distance from z_i to z_i+1 measured along x_i
     * @param alpha_i_1 [radian] angle from z_i to z_i+1 measured about x_i
     * @param d_i distance from x_i-1 to x_i measured along z_i
     * @param theta_i [radian] angle from x_i-1 to x_i measured about z_i
     */
    public DenavitHartenbergAxis(int axis_i, double a_i_1, double alpha_i_1, double d_i, double theta_i) {
        this.axis_i = axis_i;
        this.a_i_1 = a_i_1;
        this.alpha_i_1 = alpha_i_1;
        this.d_i = d_i;
        this.theta_i = theta_i;
    }

    @Override
    public String toString() {
        return axis_i + "; " +
               a_i_1 + "; " +
               alpha_i_1 + "; " +
                d_i + "; " +
                theta_i;
    }

    public TransformationMatrix getTransformation(){
        TransformationMatrix tm = new TransformationMatrix();
        double r11, r12, r13, r14, r21, r22, r23, r24, r31, r32, r33, r34, r41, r42, r43, r44;
        r11 = Math.cos(theta_i);                        r12 = -Math.sin(theta_i);                       r13 = 0;                    r14 = a_i_1;
        r21 = Math.sin(theta_i)*Math.cos(alpha_i_1);    r22 = Math.cos(theta_i)*Math.cos(alpha_i_1);    r23 = -Math.sin(alpha_i_1); r24 = -Math.sin(alpha_i_1)*d_i;
        r31 = Math.sin(theta_i)*Math.sin(alpha_i_1);    r32 = Math.cos(theta_i)*Math.sin(alpha_i_1);    r33 = Math.cos(alpha_i_1);  r34 = Math.cos(alpha_i_1)*d_i;
        r41 = 0;                                        r42 = 0;                                        r43 = 0;                    r44 = 1;
        double[][] m = new double[4][4];
        m[0][0] = r11;  m[0][1] = r12;  m[0][2] = r13;  m[0][3] = r14;
        m[1][0] = r21;  m[1][1] = r22;  m[1][2] = r23;  m[1][3] = r24;
        m[2][0] = r31;  m[2][1] = r32;  m[2][2] = r33;  m[2][3] = r34;
        m[3][0] = r41;  m[3][1] = r42;  m[3][2] = r43;  m[3][3] = r44;
        tm.set(m);
        return tm;
    }
}
