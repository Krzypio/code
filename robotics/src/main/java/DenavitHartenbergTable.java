import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DenavitHartenbergTable {
    List<DenavitHartenbergAxis> axes;
    public DenavitHartenbergTable(int axisAmount) {
        axes = new ArrayList<>(axisAmount);
    }

    public void addAxis(DenavitHartenbergAxis axis){
        axes.add(axis);
    }

    public void addAxis(int axis_i, double a_i_1, double alpha_i_1, double d_i, double theta_i){
        DenavitHartenbergAxis axis = new DenavitHartenbergAxis(axis_i, a_i_1, alpha_i_1, d_i, theta_i);
        addAxis(axis);
    }

    public DenavitHartenbergAxis get(int index){
        return axes.get(index);
    }

    @Override
    public String toString() {
        String header = "axis_i; a_i_1; alpha_i_1; d_i; theta_i\n";
        StringBuilder sb = new StringBuilder();
        sb.append(header);
        for (DenavitHartenbergAxis axis: axes) {
            sb.append(axis.toString() + "\n");
        }//for
        return sb.toString();
    }
}
