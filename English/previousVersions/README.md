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
New tips function,Disply at input be null

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
new a Button for delete MCH data,delete list:
    run.log
    errors.log
    history.txt
optimize history read again,Memory used reduced to less than 150M

(2021.4.27)

-

MCH_ah_0-0-1-14
new Helps class
历史记录读取新增文件长度缓存的判断
    即读取过后存储文件长度,下次读取前判断长度是否一致
        如果一致,则文件没有被修改,不进行刷新,减少CPU和内存使用率,也降低了硬盘使用
        如果不一致,则可能用户回车了一段命令或外部人为修改,这时才需要进行刷新
MenuUI new OnTop function

(2021.4.23)

-

MCH_ah_0-0-1-13
此版本主要更新命令解析部分

(2021.4.22)

-

MCH_ah_0-0=1-12
MenuUI新增版本显示
Errors的errors新增两个参数,分别是cannotHandle和exceptionSource
    cannotHandle为boolean类型,用于设定是否能够不管
    exceptionSource为String类型,用于判断错误来源,主要用途时写进errors.log
删除Gc线程
优化了历史记录读取,现在内存的占用大幅度降低

(2021.4.22)

-

MCH_ah_0-0-1-11
新增LoadAssembly类,这个类用于将状态输出的输出存储在run.log
新增Errors类,这个类用于处理错误,在需要时会退出程序

(2021.4.20)

-

MCH_ah_0-0-1-10
新增历史记录功能,这个功能将会在输入框为空时显示曾经回车过的命令
新增快速加载功能,此功能目前只用于历史记录,开启后会减少内存回收次数,以此加快加载速度,但是内存占用极高

(2021.4.19)


-

MCH_ah_0-0-1-9
新增退出按钮事件切换,现在可以让用户决定按钮是退出还是缩小
新增commandLibrary类,字典作用
将处理方式改为流式,不再在一个方法中进行
Events新增switchExButtonWillExit方法,用于切换退出按钮事件
配置文件不再使用Color = White之类的表达方法,现在使用Color@White
新增随机问题按钮,这个功能在很多个版本以后才会更新,因为不是主要部分

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