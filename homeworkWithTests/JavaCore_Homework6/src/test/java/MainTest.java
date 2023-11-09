import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class MainTest {



    public static List<int[][]> teams = new ArrayList<>();
    public static List<int[]> results = new ArrayList<>();
    @BeforeAll
    static void beforeAll(){
        teams.add(new int[][]{
                {45, 31, 24, 22, 20, 17, 14, 13, 12, 10},
                {31, 18, 15, 12, 10, 8, 6, 4, 2, 1},
                {51, 30, 10, 9, 8, 7, 6, 5, 2, 1}
        });
        teams.add(new int[][]{
                {75, 64, 24, 23, 20, 17, 15, 13, 12, 10},
                {31, 18, 15, 12, 10, 8, 6, 4, 2, 1},
                {81, 73, 46, 43, 36, 17, 9, 8, 4, 1}
        });
        teams.add(new int[][]{
                {45, 31, 24, 21, 18, 16, 9, 7, 8, 3},
                {61, 18, 14, 11, 10, 8, 6, 4, 2, 1},
                {51, 30, 28, 22, 18, 17, 10, 5, 5, 1}
        });

        results.add(new int[]{51, 45, 31, 31, 30, 24, 22, 20, 18, 17});
        results.add(new int[]{81, 75, 73, 64, 46, 43, 36, 31, 24, 23});
        results.add(new int[]{61, 51, 45, 31, 30, 28, 24, 22, 21, 18});
    }


    @Test

    void mergeAll() {
        for(int i = 0; i < teams.size(); i++) {
            int[] actual = Main.mergeAll(teams.get(i));
            Assertions.assertArrayEquals(actual, results.get(i));
        }
    }




}



