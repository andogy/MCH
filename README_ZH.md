# MCH
> <a href="https://github.com/zhuaidadaya/MCH/blob/main/README.md">English Page</a>

hi,欢迎来到MCH项目<br>
让我们先了解一下MCH吧

## 首先

这个项目是由两个初学者在进行<br>
你也可以加入这个项目<br>

```
如果可以, 我希望你加入!<br>
```

<br>
MCH在Minecraft指令方面提供帮助<br>
它可以帮你使用或学习Minecraft中的命令<br>
<br>
这个仓库是工作仓库, 我们有一个专门用于存储的仓库 <br>
这里 -> <a href="https://github.com/andogy/MCH">主仓库</a>

<hr>

# 如果你是用户

你可以在release里面下载到最新的MCH<br>
如果你不知道什么是release, 你只需要<a href="https://github.com/zhuaidadaya/MCH/releases">点击这里</a><br>

请在使用MCH前阅读用户协议<br>
<a href="https://github.com/andogy/MCH/tree/main/%E4%B8%AD%E6%96%87/%E5%B8%AE%E5%8A%A9/%E7%94%A8%E6%88%B7%E5%8D%8F%E8%AE%AE">
中文页面</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://github.com/andogy/MCH/tree/main/English/Helps/agreement">
english-page</a>

就没了

# 其他

## 开发者

MCH只有两个开发者<br>
(俩菜鸡)<br>
这导致我们的开发效率非常非常低, 以及可能导致我们非常累, 我们希望更多开发者加入我们的项目<br>
虽然很难实现

### 如果你可以加入我们

你需要使用腾讯QQ与我们交流<br>
<b>
你只需要发送请求到我们的QQ群(124319875)然后等待片刻
</b>

## MCH历史

MCH项目发起于2021年4月9日<br>
目前还没有发布第一个正式版本<br>

## 活动

现在进行中的活动 
> 修复"execute"命令<br>
> 位置在"src/project/Community/Command/CommandParsing"第243行<br>
这里有非常多的问题<br>
<b>如果你可以, 我希望你能帮助我们完善这些问题</b>

> 完善"README.md"
> 位置在"README.md"

> 修复其他bug

# 文档

<a href="https://github.com/zhuaidadaya/MCH/blob/main/mds/Json_File_ZH.md"><b>JSON File</b></a>

## 代码
这里有一些MCH的代码<br>
它们展示出来用于学习, 你也可以尝试优化它们<br>
我希望你能克隆这个仓库, 然后使用pull requests来修改代码
(不要pull到存储仓库)

### Restart
```java
local "src/project/Community/Events/reStart.java"

//  Run a new MCH
    Runtime r = Runtime.getRuntime();
    String str = r.exec("cmd.exe /k \"" + getJar.getOldPath() + "\"").toString();
//  Create a check file
    File file = new File(ini.path + "res.cache");
    FileWriter fw = new FileWriter(file);
    fw.write(file.hashCode());
//  close Stream
    fw.close();
    
----------------------------------------------------
local "src/project/Community/Community.java"

//  Delete check file, meaning restart is success
    new File(ini.path + "res.cache").delete();

----------------------------------------------------
local "src/project/Community/Events/reStart.java"

//  get time
    long startRestart = System.currentTimeMillis();

//  always check restart status when not restart success or time out
    do {
//      use reader to check, if no exception then not restart success
        FileReader fr = new FileReader(ini.path + "res.cache");
        fr.close();
        Thread.sleep(1);
    } while (System.currentTimeMillis() - startRestart <= 2000);

//  if time out, delete check file
    boolean restart = new File(ini.path + "res.cache").delete();

//if success delete, then tip restart failed and delay exit MCH
    if (restart) {
        System.out.println("restart failed");

        Errors.tips(Errors.jFrame.getWidth(), Errors.jFrame.getHeight(), "restart failed");

        Thread.sleep(10000);

        exit.Ex();
    } else {
        throw new Exception();
    }

```

### CommandParsing.java

``` java
local "src/project/Community/Command/CommandParsing" at run() 


//if have error, then stop process
while(!Errors.CannotHandle) {
    var input = MchUI.input_Command.getText();
    
    //command text must not null and not wrap line
    if(!input.equals("") & !input.equals("\n")) {
    
        //process command at not switching command, if switching then waiting for
        if (!MchUI.switchTip.isFocusOwner()) {
            CommandParsing.commands();
        }
        
    }
    
}
 
----------------------------------------------------
local "src/project/Community/Command/CommandParsing" at commands()

 
public static void commands() {
     
}
```

### 
