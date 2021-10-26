public class az {

}

class ReorderedPowerOf2 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        long start = System.currentTimeMillis();
        System.out.println(reorderedPowerOf2(679213508));
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    public static boolean reorderedPowerOf2(int n) {
        return reorderedPowerOf2_Upset(n) || isPoweredOf2(n);
    }

    public static boolean reorderedPowerOf2_Upset(int n) {
        return switchPowerOf(String.valueOf(n).toCharArray(), 0);
    }

    public static boolean switchPowerOf(char[] str, int i) {
        boolean ret = false;
        if(i >= str.length)
            return false;
        if(i == str.length - 1) {
            if(! String.valueOf(str[0]).equals("0"))
                ret = isPoweredOf2(Integer.parseInt(String.valueOf(str)));
        } else {
            for(int j = i; j < str.length; j++) {
                char temp = str[j];
                str[j] = str[i];
                str[i] = temp;

                ret = switchPowerOf(str, i + 1);
                if(ret)
                    break;

                temp = str[j];
                str[j] = str[i];
                str[i] = temp;
            }
        }

        return ret;
    }

    public static boolean isPoweredOf2(int n) {
        return (n & n - 1) == 0;
    }
}

