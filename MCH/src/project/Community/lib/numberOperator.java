package project.Community.lib;

import java.math.BigDecimal;

public class numberOperator {
    public static int intValueOf(String str) {
        int result;
        if (str.length() > 10) {
            badValue(str, BigDecimal.TEN, true);
        }

        result = 0;
        String subStr;
        int length = str.length();
        int i = 0;

        while (true) {
            int multiple = 1;

            for (int counts = length - 1; counts > 0; counts--) {
                multiple *= 10;
            }

            int value = 0;
            try {
                subStr = String.valueOf(str.charAt(i));
                switch (subStr) {
                    case "0" -> value = 0;
                    case "1" -> value = 1;
                    case "2" -> value = 2;
                    case "3" -> value = 3;
                    case "4" -> value = 4;
                    case "5" -> value = 5;
                    case "6" -> value = 6;
                    case "7" -> value = 7;
                    case "8" -> value = 8;
                    case "9" -> value = 9;
                    default -> badValue(str, BigDecimal.valueOf(i), false);
                }
                i++;
                result = toInt(value, multiple, result);
                length--;
            } catch (StringIndexOutOfBoundsException e) {
                break;
            }
        }
        return result;
    }

    public static Long longValueOf(String str) {
        Long result;
        str = str.toLowerCase();
        if (!str.contains("l")) {
            if (str.length() > 19) {
                badValue(str, BigDecimal.valueOf(19), true);
            }
        } else {
            if (str.length() > 20) {
                badValue(str, BigDecimal.valueOf(19), true);
            }
        }

        result = 0L;
        String subStr;
        int length = str.length();
        int i = 0;

        while (true) {
            long multiple = 1L;

            for (int counts = length - 1; counts > 0; counts--) {
                multiple *= 10;
            }

            long value = 0L;
            try {
                subStr = String.valueOf(str.charAt(i));
                switch (subStr) {
                    case "0" -> value = 0L;
                    case "1" -> value = 1L;
                    case "2" -> value = 2L;
                    case "3" -> value = 3L;
                    case "4" -> value = 4L;
                    case "5" -> value = 5L;
                    case "6" -> value = 6L;
                    case "7" -> value = 7L;
                    case "8" -> value = 8L;
                    case "9" -> value = 9L;
                    case "l" -> {
                        if (i + 1 != str.length()) {
                            badValue(str, BigDecimal.valueOf(i), false);
                        }
                    }
                    default -> badValue(str, BigDecimal.valueOf(i), false);
                }
                i++;
                result = toLong(value, multiple, result);
                length--;
            } catch (StringIndexOutOfBoundsException e) {
                break;
            }
        }
        return result;
    }

    private static int toInt(int value, int multiple, int result) {
        return result = result + value * multiple;
    }

    private static Long toLong(Long value, Long multiple, Long result) {
        return result = result + value * multiple;
    }

    private static void badValue(String value, BigDecimal badPoint, boolean toLength) {
        badPoint = badPoint.add(BigDecimal.ONE);
        if (!toLength) {
            throw new NumberFormatException(value.substring(0, intValueOf(badPoint.toString())) + " \033[37;1m<-bad here(" + value.charAt(intValueOf(badPoint.toString()) - 1) + ")\033[0m " + value.substring(intValueOf(badPoint.toString())));
        } else {
            throw new NumberFormatException(value.substring(0, intValueOf(badPoint.toString()) - 1) + " \033[37;1mall is bad->\033[0m" + value.substring(intValueOf(badPoint.toString()) - 1));
        }
    }
}
