
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Start {
    public static void main(String[] args) {
        int segmentHeight[] = { 2,2,3,4,3,3,2,2,1,1,2,5 };
        Solution s = new Solution();
        System.out.println(s.solution(segmentHeight));
        //Create array with repeats
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(1);
        //get sizes of original and distinct arrays
        int arrSize = arr.size();
        int distSize = arr.stream()
                .distinct()
                .collect(Collectors.toList())
                .size();
        //compare if sizes are equals or not
        if (arrSize != distSize) {
            System.out.println("Zawiera powtórzenia");
        } else {
            System.out.println("Nie zawiera powtórzeń");
        }
    }
}
