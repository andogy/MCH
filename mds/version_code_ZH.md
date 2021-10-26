# 版本ID

MCH在0.0.1.56起换用了新的ID算法

版本ID分为前后部分<br>
1~7位属于前部分

## 前部分
前两位用10~15的数字分别表示
```
alpha版本     ->   10
beta版本      ->   11
debug版本     ->   12
test版本      ->   13
pre版本       ->   14
release版本   ->   15 
```

alpha为最初内部测试版本<br>
beta比alpha晚但是性质一致<br>
debug版本为急性bug修复<br>
test版本则为新内容预览版本<br>
pre是预发布版本<br>
release是正式版

第3~5位是由Java的随机类提供的100~999的随机数

第6~7位永远是01<br>
这两位用于分隔前后部分

## 后部分
后部分由版本名称组成<br>
例如0.0.1.56版本的后部分即为00156

## 参考
一般使用一段代码生成版版本ID

<details>
<summary>
mch_version_code.class
</summary>

```java
public class mch_version_code {
    public static String getVersionCode() {
        String type = "test";
        String firstCode = "10";
        String secondCode = String.valueOf(new Random().nextInt(100, 999));
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


        String verCode = firstCode + secondCode + splitCode + versionID.replace(".", "");

        return verCode;
    }
}
```

</details>

<hr>

