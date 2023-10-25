import java.util.*;

public class Main {

    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4};
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, numbers);
        List<Integer> result = new ArrayList<>();
        for (Integer elem: list) {
            if(elem > 0 && elem % 2 == 0) {
                result.add(elem);
            }
        }
        result.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        for (Integer elem : result){
            System.out.println(elem);
        }

    }
}
