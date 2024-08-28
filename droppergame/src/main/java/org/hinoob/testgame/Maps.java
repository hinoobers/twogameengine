package org.hinoob.testgame;

import org.hinoob.tge.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maps {

    public static List<Environment> environments = new ArrayList<>();
    public static void load() {
        for(int i = 0; i < 500; i++) { // infinite maps pretty much
            int rows = new Random().nextInt(5);
            while(rows == 0)
                rows = new Random().nextInt(5);

            Environment map1 = new Environment();
            map1.addRenderer(new GoalObject(500, 500, 32, 32));
            for(int z = 0; z < rows; z++) {
                map1.addRenderer(new ObstacleRow(500, 400-(z*100), 800, 15, map1));
            }

            environments.add(map1);
        }
    }
}
