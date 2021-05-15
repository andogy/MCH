# 历史版本
本处存放着MCH从开发第一日至最新版本源码的所有源码，供于学习与了解本项目。

最上面的版本是最新的

## 版本ID算法
版本ID是由版本特性、分隔、版本号构成的<br>
<br>
版本ID的前两位表示此版本的特性<br>
11为ah(Alpha)<br>
12为beta<br>
13为正式发布版<br>
14则是Debug版本<br>
<br>
第3-5位是由MCH的伪随机算法生成的三位数字<br>
<br>
第6-7位永远是01,这是分隔伪随机数字和版本号的两位数字<br>
<br>
剩下的即为版本号<br>
如0.0.1.1,在版本ID里即为0011,再如0.0.1.22,在版本ID里即为00122<br>

## 版本更新内容

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-36.zip">MCH_ah_0-0-1-36(115030100136)</a>
```
修改了启动器的位置,从 菜单>启动器 中移动到了 菜单>功能>启动器
以后几乎所有功能都会在那里进入

(2021.5.15)
```


### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-35.zip">MCH_ah_0-0-1-35(145020100135)</a>
```
修复了115020100134版本无限更新的bug

(2021.5.14)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-34.zip">MCH_ah_0-0-1-34(115020100134)</a>
```
微调了设置页面的布局和位置
修复了更新的一些小bug

(2021.5.14)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-33.zip">MCH_ah_0-0-1-33(145010100133)</a>
```
CbNet更新源停止更新支持,现在只能从Github更新
国内如果有新版本后无法更新,请等待数分钟后重试或在qq群下载更新

(2021.5.13)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-32.zip">MCH_ah_0-0-1-32(115010100132)</a>
```
新增反馈按钮,点击后跳转到反馈邮箱

(2021.5.13)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-31.zip">MCH_ah_0-0-1-31(115090100131)</a>
```
新增自动更新功能,在有更新的时候会自动进行更新操作

(2021.5.12)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-30.zip">MCH_ah-0-0-1-30(114990100130)</a>
```
现在除了settings.ini文件,其他都可以不保存了
可以在 菜单>进阶设置 里寻找到这些设置不保存的文件
修改了MCH文件占用的计算方法,现在更加精确了
/
这两天都在研究websocket,所以一直没有更新
现在websocekt被放到后面的版本了,更新速度会很快恢复
/

(2021.5.11)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-29.zip">MCH_ah_0-0-1-29(111470100129)</a>
```
新增启动器功能,此功能可以快捷启动或关闭基岩版MC
Java版启动器正在计划中
现在错误日志可以不保存了,在 菜单>进阶设置>保存错误 里进行设置

(2021.5.9)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-28.zip">MCH_ah_0-0-1-28(111550100128)</a>
```
新增彩字代码(§)颜色展示,在输入框将会有彩字效果
这个功能目前处于测试性,可能bug会有很多(但现在没有发现)

(2021.5.8)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-27.zip">MCH_ah_0-0-1-27(111460100127)</a>
```
修复部分补全的bug
新增高亮功能,目前只在补全命令时有使用
修复了其他bug

(2021.5.8)<br>
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-26.zip">MCH_ah_0-0-1-26(111540100126)</a>
```
再次修复缓存残留问题
新增保留缓存功能,在 菜单>进阶设置>保留缓存 进行设置
新增一个快捷键:
    Ctrl + H    打开进阶设置
    
(2021.5.7)
```
### <a href = "hhttps://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-25.zip">MCH_ah_0-0-1-25(111540100125)</a>
```
新增上下键补全命令,以及上下键填入历史
    使用方法:
    1.在输入时按上下或Tab键将命令补全到输入中,按下其他键生效,按下Backspace(退格)键取消
    2.补全完成后按空格键可以生成原命令外加一个空格,如果不按空格,则只有源命令
        如:
        在补全"gamemode"时,按下空格键,输入框将出现"gamemode ",不然则出现"gamemode"
这个版本主要更新此功能,其他部分有一些结构上的小改,没有可察觉的大变
补全的bug很多,不过会很快修复的

(2021.5.7)
```
### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-24.zip">MCH_ah_0-0-1-24(111440100124)</a>
```
修复了版本更新残留缓存的问题
将内部执行流程进行了稍微的调整
新增重启快捷键:
    Ctrl + R
    Alt + R

(2021.5.6)
```

-

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-23.zip">MCH_ah_0-0-1-23(111520100123)</a>
```
新增一些快捷键:
    Ctrl + Esc    退出程序
    Ctrl + Delete    清空输入(正在输入时可用)
    Ctrl + M    打开菜单页面(不输入时可用)
    Ctrl + U    检查更新
    Ctrl + G    前往MCH的Github页面
    Alt + C     切换主题颜色
    Alt + L     切换语言
    Alt + Ctrl  也是切换主题颜色
新增一个按键彩蛋,同时按下两个特点相邻的按钮即可找到

(2021.5.5)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-22.zip">MCH_ah_0-0-1-22(111430100122) </a>
```
修复了github源无法获取更新的问题,现在不会被拒绝连接,但国内可能时常会超时
修复了之前版本更新的版本判断问题

(2021.5.5)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-21.zip">MCH_ah_0-0-1-21(112440100121)</a>
```
更新功能现在可以使用了
新增选择源按钮,可以选择更新时使用Github或菜比网络的下载源
解决了上个版本在"安装"时出现的问题
使用了许多线程休眠,模拟处理时间
新增countTime类,这个类用于判断连接是否超时
在MCH启动时会自动检查一次更新

(2021.5.4)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-20.zip">MCH_ah_0-0-1-20(111970100120)</a>
```
新增了更新功能,但是由于网络文件并未就绪,这个版本还不能使用这个功能
新增了重启按钮
新增了检查更新按钮,以及检查结果提示
新增getJar类,这个类用于获取自己的路径(绝对路径)
新增URLs线程,这个线程用于处理更新等url请求

(2021.5.3)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-19.zip">MCH_ah_0-0-1-19(112660100119)</a>
```
从此版本往后开始使用版本ID(112660100119),但不删除版本号(MCH_ah_0-0-1-19)
新增三个按钮:
    -Helps    帮助文档
    -agreement    用户协议
    -gayhub    Github的
修改了一些内部方法的顺序,现在bug更少了(但还是很多)

(2021.5.2)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-18.zip">MCH_ah_0-0-1-18</a>
```
小更新,将命令处理的整条处理改为了处理光标之前的命令
将ver参数存储在了Community,之前是在language

(2021.4.30)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-17.zip">MCH_ah_0-0-1-17</a>
```
新增了tips,在输入框为空时会随机显示

(2021.4.30)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-16.zip">MCH_ah_0-0-1-16</a>
```
这个版本新增了颜色设置的彩蛋,提示:在输入框输入彩蛋的英文
errors新增一个参数: er,这个参数是Error类型,目前可以使用的只有内存溢出
    引起内存溢出方法:在输入栏输入340万以上字符后按下回车
        在非保障情况下请不要轻易尝试,此行为可能导致电脑卡死


(2021.4.29)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-15.zip">MCH_ah_0-0-1-15</a>
```
将UI从resources移动到Community里
新增删除数据按钮,此按钮会删除:
    run.log
    errors.log
    history.txt
将历史读取再优化,现在内存占用不超过150M

(2021.4.27)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-14.zip">MCH_ah_0-0-1-14</a>
```
新增Helps类
历史记录读取新增文件长度缓存的判断
    即读取过后存储文件长度,下次读取前判断长度是否一致
        如果一致,则文件没有被修改,不进行刷新,减少CPU和内存使用率,也降低了硬盘使用
        如果不一致,则可能用户回车了一段命令或外部人为修改,这时才需要进行刷新
MenuUI新增OnTop(应用置顶)选项

(2021.4.23)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-13.zip">MCH_ah_0-0-1-13</a>
```
此版本主要更新命令解析部分

(2021.4.22)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-12.zip">MCH_ah_0-0=1-12</a>
```
MenuUI新增版本显示
Errors的errors新增两个参数,分别是cannotHandle和exceptionSource
    cannotHandle为boolean类型,用于设定是否能够不管
    exceptionSource为String类型,用于判断错误来源,主要用途时写进errors.log
删除Gc线程
优化了历史记录读取,现在内存的占用大幅度降低

(2021.4.22)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-11.zip">MCH_ah_0-0-1-11</a>
```
新增LoadAssembly类,这个类用于将状态输出的输出存储在run.log
新增Errors类,这个类用于处理错误,在需要时会退出程序

(2021.4.20)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-10.zip">MCH_ah_0-0-1-10</a>
```
新增历史记录功能,这个功能将会在输入框为空时显示曾经回车过的命令
新增快速加载功能,此功能目前只用于历史记录,开启后会减少内存回收次数,以此加快加载速度,但是内存占用极高

(2021.4.19)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-9.zip">MCH_ah_0-0-1-9</a>
```
新增退出按钮事件切换,现在可以让用户决定按钮是退出还是缩小
新增commandLibrary类,字典作用
将处理方式改为流式,不再在一个方法中进行
Events新增switchExButtonWillExit方法,用于切换退出按钮事件
配置文件不再使用Color = White之类的表达方法,现在使用Color@White
新增随机问题按钮,这个功能在很多个版本以后才会更新,因为不是主要部分

(2021.4.17)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-8.zip">MCH_ah_0-0-1-8</a>
```
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
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-7.zip">MCH_ah_0-0-7</a>
```
删除HelpUI
颜色切换按钮完成
目录ca更名为MCH
MenuUI新增文件存储路径显示和颜色切换(未完成)
EXI类更名为exit
新增Times类,用于格式化时间,给状态提示使用
新增状态提示,即时间加事件输出
新增ini类,用于配置文件加载,因为要加载颜色、语言等,配置文件存储在C:\MCH\settings.ini


(2021.4.14)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-6.zip">MCH_ah_0-0-1-6</a>
```
将退出事件监听更名为EXIT_Button.java
新增EXI类,用于输入.exit退出时提示
新增HelpUI,这个当时可能是想做使用帮助,没几个版本就删除了,因为改用了web

(2021.4.11)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-5.zip">MCH_ah_0-0-1-5</a>
```
将复制方式改为按下回车复制
将按下退出按钮事件改为缩小窗口
HelpUI更名为MenuUI
将颜色和语言的暂存放在Community.java
colors新增黑色

(2021.4.10)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-4.zip">MCH_ah_0-0-1-4</a>
```
在Parsing中将大写字母转为小写
language新增英文

(2021.4.10)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-3.zip">MCH_ah_0-0-1-3</a>
```
新增language线程,用于设定UI语言
新增Parsing线程,用于判断输入以及解析输入
在Parsing进行简单的命令判断测试
删除复制按钮
新增HelpUI,其实这是设置并非帮助


(2021.4.10)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-2.zip">MCH_ah_0-0-1-2</a>
```
UI新增命令输入和复制按钮
新增colors线程,用于设定UI颜色
新增Events方法集
Events新增Copy方法,用于复制输入框文本
入口方法更名为Community.java

(2021.4.10)
```

### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1-1.zip">MCH_ah_0-0-1-1</a>
```
增加了一个基本窗口(无内容)
增加按下退出按钮的事件监听

(2021.4.9)
```
### <a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/code/SourceCodes/MCH_ah_0-0-1.zip">MCH_ah_0-0-1</a>
```
创建了一个空项目

(2021.4.9)
```
