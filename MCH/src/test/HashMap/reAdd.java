package test.HashMap;

import java.util.HashMap;

public class reAdd {
    public static void main(String[] args) {
        HashMap test = new HashMap<>();
        test.put("00",1);
        test.put("00",2);
        test.put("00",3);

        System.out.println(test);
    }
}
