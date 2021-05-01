# previousVersions
in Here,Saved all Previous Version of MCH<br>
Provide Learn and Reference for this project

## updateLogs
```
MCH_ah_0-0-1-18
a small update,Change command process to only process the content before the cursor
ver parameter stored the Community,before that stored in the language

(2021.4.30)

-

MCH_ah_0-0-1-17
add tips function,Disply at input be null

(2021.4.30)

-

MCH_ah_0-0-1-16
New a color eggs,hint:entry at the input
the errors add a new parameter: er,this parameter is Error type,at present just OutOfMemory use this parameter
    Cause OutOfMemoryExeption:Entry 3.4 million characters at the Input and key Enter
        Do not try without protection,This operation may crash yor Computer 

(2021.4.29)

-

MCH_ah_0-0-1-15
move the UI form the resources to the Community
add a Button for delete MCH data,delete list:
    run.log
    errors.log
    history.txt
Optimize history read again,Memory used reduced to less than 150M

(2021.4.27)

-

MCH_ah_0-0-1-14
add Helps class
historyRead add a Judge file length function
    it stored file length After read file,Next read need Judge file length than stored consistent or not
        if consistent,maybe file not Revised,not refresh,Reduce CPU and Memory ETC use
        if consistent not,maybe user copied a command or Edited at external,This is time to refresh
MenuUI new OnTop function

(2021.4.23)

-

MCH_ah_0-0-1-13
This Version updated command parsing part

(2021.4.22)

-

MCH_ah_0-0=1-12
Display MCH version in MenuUI
errors of Error class add two parameter,they is cannotHandle and exceptionSource
    cannotHandle is boolean type,Used to set Whether errors can be ignored
    exceptionSource is String type,Used to set error source,will write in errors.log
Delete Gc Thread
Optimize history read,Memory used Significantly reduced

(2021.4.22)

-

MCH_ah_0-0-1-11
add LoadAssembly class,this class will write state output in the run.log
add Errors class,this class will process errors and exit at When necessary

(2021.4.20)

-

MCH_ah_0-0-1-10
add history record function,this function will record copied commands and display history at input is null
add fast load function,this function just use for history,Enable will Reduce memory recycles,so can to speed up the loading speed,But Memory used will super hight

(2021.4.19)


-

MCH_ah_0-0-1-9
add exit button events switch,now user can decision click exit button will exit or smaller
add commandLibrary class,this is a Dictionary
Process part set to stream,not process in a class
Events add switchExButtonWillExit function,used switch exit button will do what things
config file is no longer expressed as "Color = Whita",now it is "Color@White
add random problem button,But this function update maybe after many version,Because it is not the main part

(2021.4.17)

-

MCH_ah_0-0-1-8
Events新增switch系列方法:
    -switchColor    切换颜色
    -switchLanguage    切换语言
新增Gc线程,用于回收垃圾,后来发现其实没什么用,过几个版本就被删了
ini新增创建引导,如若没有配置文件则引导用户设置基本配置
新增iniHas线程,用于判断配置文件是否存在,不存在就将菜单的UI关闭
CaUI更名为MchUI
Parsing中大部分解析都被注释了,因为准备换处理方式
新增语言切换

(2021.4.17)

-

MCH_ah_0-0-7
删除HelpUI
颜色切换按钮完成
目录ca更名为MCH
MenuUI新增文件存储路径显示和颜色切换(未完成)
EXI类更名为exit
新增Times类,用于格式化时间,给状态提示使用
新增状态提示,即时间加事件输出
新增ini类,用于配置文件加载,因为要加载颜色、语言等,配置文件存储在C:\MCH\settings.ini


(2021.4.14)

-

MCH_ah_0-0-1-6
将退出事件监听更名为EXIT_Button.java
新增EXI类,用于输入.exit退出时提示
新增HelpUI,这个当时可能是想做使用帮助,没几个版本就删除了,因为改用了web

(2021.4.11)

-

MCH_ah_0-0-1-5
将复制方式改为按下回车复制
将按下退出按钮事件改为缩小窗口
HelpUI更名为MenuUI
将颜色和语言的暂存放在Community.java
colors新增黑色

(2021.4.10)

-

MCH_ah_0-0-1-4
在Parsing中将大写字母转为小写
language新增英文

(2021.4.10)

-

MCH_ah_0-0-1-3
新增language线程,用于设定UI语言
新增Parsing线程,用于判断输入以及解析输入
在Parsing进行简单的命令判断测试
删除复制按钮
新增HelpUI,其实这是设置并非帮助


(2021.4.10)

-

MCH_ah_0-0-1-2
UI新增命令输入和复制按钮
新增colors线程,用于设定UI颜色
新增Events方法集
Events新增Copy方法,用于复制输入框文本
入口方法更名为Community.java

(2021.4.10)

-

MCH_ah_0-0-1-1
增加了一个基本窗口(无内容)
增加按下退出按钮的事件监听

(2021.4.9)

MCH_ah_0-0-1
创建了一个空项目

(2021.4.9)
```
