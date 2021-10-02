# Extra for MCH using java

extra need are jar file<br>
it should be followed certain specifications
(name or structure ETC.)

a extra example:

```java
package Mex;

public class Declared {
    public void onLoad() {
        System.out.println("Hello World!");
    }
}
```


compiled code to jar and move it to extra folder<br>
will run code of ```onLoad()``` on loading<br>

```public void onLoad()``` in code are indispensable<br>
if lost then cannot load this extra

> ```onLoad()``` like ```main()``` in normally program<br>
> so it cannot load when that method lost
> if you do not <a href="https://github.com/zhuaidadaya/MCH/blob/main/mds/means.md#def">def</a> entrance, program do not know where are you star with

> "Declare" are not must be that name<br>
> that just a <a href="https://github.com/zhuaidadaya/MCH/blob/main/mds/means.md#std">std</a> name
> you need declare in extra.json<br>
> if did not have extra.json, cannot use other name
<hr>
MCH will create new thread for load extra<br>
so do not worry to load be slow<br>
but extra will cannot use when load not completed<br>

> so, we can ```while(true)``` in code(don't

we did safe thread in extra modules<br>
so please do not do danger things in extra

<hr>

# Dev package

use dev package to change UI or process logics of MCH

but not in here, we need start with some basically

### regis for extra

use ```r()``` let MCH know your extra registered

```java
package Mex;

import com.github.zhuaidadaya.MCH.Events.UPD.getJar;

public class Declared {
    public void onLoad() {
        //codes in here
    }

    public String r() {
        return new getJar().getOldPath(this.getClass());
    }
}
```

以上这段代码将返回扩展包被加载时的绝对路径<br>
MCH会将其显示在扩展列表中

> r() 是一个检查方法<br>
> 方法并非强制性,但是建议将其加上 <br>
> 如果不加上,MCH将会把此扩展认为非正常扩展

<hr>

### 输出信息

在有需要输出文本或其他时,尽量避免使用 ```System.out``` 来输出

应换用MCH提供的out

```java
package Mex;

import com.github.zhuaidadaya.MCH.exOut;

public class Declared() {
    exOut out = new exOut();

    public void onLoad() {
        //此处应当使用扩展的名称来作为exID
        //非强制性,看得懂是什么的的都行              
        out.setExID("MchExtraName");

        out.println("xxx now is loading");
    }

    public String r() {
        return new getJar().getOldPath(this.getClass());
    }
}
```

也可以

```java
package Mex;

import com.github.zhuaidadaya.MCH.exOut;

public class Declared() {
    //在创建之初直接设定exID    
    exOut out = new exOut().setExID("MchExtraName");

    public void onLoad() {
        out.println("xxx now is loading");
    }

    public String r() {
        return new getJar().getOldPath(this.getClass());
    }
}
```

<hr>

> 此处开始的示例将不再有 ```r()``` 方法<br>
> 但并不代表我们建议学习此行为

<hr>

### 保存日志

用以下代码在日志中保存 ```this is a log``` 文本

```java
package Mex;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.lib.Log;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Declared() {
    public void onLoad() {
        //将日志存储到MCH的默认位置,有需要可以自行更改        
        File logSaveTo = new File(Config.runLogsPath + "latest.log");

        //如果是false则写入前删除文件内容
        //不建议设为false
        //因为会把MCH自身以及其他插件生成的日志清除        
        boolean append = true;

        //编码,要求为UTF-8        
        Charset charset = StandardCharsets.UTF_8;

        //这是日志,内容随意        
        String log = "this is a log";

        //是否为warning
        //设为true将会以红色字体输出在控制台
        //(部分控制台可能不支持)        
        boolean warn = false;

        //将参数给到方法        
        Log.writeLog(logSaveTo, append, charset, log, warn);
    }
}

```
去掉注释以后的样子
```java
package Mex;

        import com.github.zhuaidadaya.MCH.Command.Config;
        import com.github.zhuaidadaya.MCH.lib.Log;

        import java.io.File;
        import java.nio.charset.Charset;
        import java.nio.charset.StandardCharsets;

public class Declared() {
    public void onLoad() {
        File logSaveTo = new File(Config.runLogsPath + "latest.log");
        boolean append = true;
        Charset charset = StandardCharsets.UTF_8;
        String log = "this is a log";
        boolean warn = false;

        Log.writeLog(logSaveTo, append, charset, log, warn);
    }
}
```

如果觉得行数有点多,也可以这样

```java
package Mex;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.lib.Log;

import java.nio.charset.StandardCharsets;

public class Declared() {
    public void onLoad() {
        //直接在给参数时临时建立参数        
        Log.writeLog(Config.runLogsPath + "latest.log", true, StandardCharsets.UTF_8, "this is a log", false);
    }
}
```

如果还不够短...

```java
package Mex;

import com.github.zhuaidadaya.MCH.lib.Log;

public class Declared() {
    public void onLoad() {
        //使用默认参数保存    
        Log.writeLog("this is a log");
    }
}
```

保存日志前建议进行检查

```java
package Mex;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.lib.Log;

public class Declared() {
    public void onLoad() {
        //检查是否保存日志        
        if(Community.saveRunLog)
            Log.writeLog(Config.runLogsPath + "latest.log", true, StandardCharsets.UTF_8, "this is a log", false);
    }
}
```

或者使用LoadAssembly类检查

```java
package Mex;

import com.github.zhuaidadaya.MCH.Events.LoadAssembly;

public class Declared() {
    public void onLoad() {
        //使用 加载组件 功能加载一个日志,此方法会帮你检查是否启用了日志写入
        LoadAssembly.loadAssembly("this is a log");
    }
}
```

> ``` Log.writeLog(Object) ``` 或 ``` Log.writeErr(Object) ``` <br>
> 会进行自动检查,也就是说如果使用默认日志参数,则不需要手动检查
>
> 除了默认以外的日志方法都不支持自动检查

<hr>

### 保存错误

```java
package Mex;

import com.github.zhuaidadaya.MCH.Events.Errors;

public class Declared() {
    public void onLoad() {
        try {
            throw new IllegalStateException();
        } catch (Exception e) {
            Errors.errors(null, e, false, "extra loading", "a extra are testing Errors class", 600, 460, true);
        }
    }
}
```

``` Errors.errors() ``` 可以有八个参数

```
Error/Exception  -必须在首位,可以只有一个,也可以有两个
CannotHandle     -在Err/Exce后面,true时会尝试重启MCH
exceptionSource  -错误源,如果没有会被记录为Unknow
message          -错误时的提示信息
w                -错误窗口的宽度
h                -错误窗口的高度
show             -发生错误时是否显示错误窗口
```

以上只有列出七行,因为Error/Exception可以同时传递,算作两个

Errors类是一个保存错误以方便反馈的类<br>
当然你也可以选择使用Log类和JFrame自己完成

在无关紧要的地方不建议保存错误,否则可能意外增大错误日志

> Errors.class

```java
package com.github.zhuaidadaya.MCH.Events;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.UI.*;
import com.github.zhuaidadaya.MCH.lib.Log;
import com.github.zhuaidadaya.MCH.lib.Resources;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Errors extends Throwable {
    public static JFrame jFrame = new JFrame();
    public static JTextArea jTextArea = new JTextArea();

    public static boolean CannotHandle = false;

    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Dimension screenSize = toolkit.getScreenSize();
    public static int width = screenSize.width;
    public static int height = screenSize.height;

    public static void errors(Exception exception, boolean cannotHandle, String message) {
        errors(exception, cannotHandle, "Unknow", message);
    }

    public static void errors(Error error, boolean cannotHandle, String message) {
        errors(error, cannotHandle, "Unknow", message);
    }

    public static void errors(Error error, boolean cannotHandle, String exceptionSource, String message) {
        errors(error, null, cannotHandle, exceptionSource, message);
    }

    public static void errors(Exception exception, boolean cannotHandle, String exceptionSource, String message) {
        errors(null, exception, cannotHandle, exceptionSource, message);
    }

    public static void errors(Error error, Exception exception, boolean cannotHandle, String exceptionSource, String message) {
        errors(error, exception, cannotHandle, exceptionSource, message, 644, 466, true);
    }

    //    Errors主体函数
    public static void errors(Error error, Exception exception, boolean cannotHandle, String exceptionSource, String message, int w, int h, boolean show) {
        //......
    }
}
```

<hr>

### 配置文件

MCH在写入以及读取配置文件时会对其加密<br>
使用每行的首位字符对后面所有字符进行char的偏移
这是随机的

需要使用MCH提供的HashMap来保存配置文件<br>
用以下代码来存储配置文件
```java
package Mex;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Community;

public class Declared() {
    public void onLoad() {
        Community.extraConf.put("config-name", "config-value");
        Config.WriteIni();
    }
}
```

以下来读取配置文件

```java
package Mex;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Community;

public class Declared() {
    public void onLoad() {
        String conf = Community.extraConf.get("config-name");
    }
}
```

