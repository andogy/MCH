import java.util.Random;

public class mch_version_code {
    public static String getVersionCode() {
        String type = "test";
        String firstCode = "10";
        String secondCode = String.valueOf(new Random().nextInt(100,999));
        String splitCode = "01";
        String versionID = "0.0.1.56";

        switch(type) {
            case "alpha" -> firstCode = "10";
            case "beta" -> firstCode = "11";
            case "debug" -> firstCode = "12";
            case "test" -> firstCode = "13";
            case "pre" -> firstCode = "14";
            case "release" -> firstCode = "15";
        }


        String verCode = firstCode + secondCode + splitCode + versionID.replace(".","");

        return verCode;
    }

    public static void main(String[] args) {
        System.out.println(getVersionCode());
    }
}
