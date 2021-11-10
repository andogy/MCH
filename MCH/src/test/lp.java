import java.math.BigDecimal;

public class lp {
    public static void main(String[] args) {
        f(33,BigDecimal.valueOf(33));
    }

    public static void test(int v) {
        if (v > 2) {
            System.out.println(v);
            test(--v);
        }
    }

    public static void f(int d, BigDecimal v) {
        if (v.toString().equals(String.valueOf(d))) {
            v = BigDecimal.valueOf(d);
        }

        if (d <= 0)
            return;

        d--;

        v = v.multiply(BigDecimal.valueOf(d));
        f(d,v);
        System.out.println(d + ":" + v);
    }
}
