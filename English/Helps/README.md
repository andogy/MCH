# MCH帮助

MCH is a Minecraft Command Helper

<a href = "http://caibiwangluo.eu5.org/mch/yhxy.php">User agreement</a>

## ----------minimum requirement----------
<br>
before used MCH,please check your computer are the following requirements met<br>
&nbsp;&nbsp;&nbsp;&nbsp;*your computer is normal computer<br>
&nbsp;&nbsp;&nbsp;&nbsp;-has 2cores 1GHz or higer CPU<br>
&nbsp;&nbsp;&nbsp;&nbsp;-has 360x460 or higer Display screen<br>
&nbsp;&nbsp;&nbsp;&nbsp;*has Java8u201 or Java of higer version<br>
&nbsp;&nbsp;&nbsp;&nbsp;*has keyboard and mouse<br>
&nbsp;&nbsp;&nbsp;&nbsp;-has 50M or higer storage<br>
&nbsp;&nbsp;&nbsp;&nbsp;-has 1GB or higer RAM<br>
<br>
When your computer meets at least all the asterisk marks,you can a jar from<a href = "https://raw.githubusercontent.com/andogy/MCH/main/Public/MCH.jar">Github</a> or <a href = "http://caibiwangluo.eu5.org/mch/MCH.jar">CbNet</a><br>
double click it to used the program<br>
<br>
at first use,you need do some cofig set,like language or color ETC.<br>
need not to set again when you restart after completion<br>
<br>

## ----------basic usage----------
<br>
### find commands
in MCH,have a input box and menu button in below<br>
After complete command in the input box,entry space to display command tip,entry Enter can copy command<br>
if do not know specific command,can entry usage of that command<br>
such as "gamemode"<br>
this command is used set game mode,can entry "g" or "mode" ETC.<br>
<br>
if wanna get all commands,can entry "/" in input box,or entry Tab key when input box is empty<br>
<br>
when input box is empty,default operation is read your history file,it is commands of you write in MCH<br>
if you do not wanna see that,you can off it in menu<br>

### completion command
when input box not is empty<br>
entry UP/Down(↑/↓) key or Tab key can select command to completion,entry ohther key then to take effect,entry Backspace(回车) to cancel change<br>
at completion,command of will completion to input box will display as orange(display in up tips,not input box)<br>

### shortcut key
熟练使用快捷键使得MCH的使用效率有所增加<br>
MCH有以下快捷键:<br>
<br>
Ctrl + Esc&nbsp;&nbsp;&nbsp;&nbsp;(退出MCH)<br>
Ctrl + Delete&nbsp;&nbsp;&nbsp;&nbsp;(清空输入)<br>
Ctrl + M&nbsp;&nbsp;&nbsp;&nbsp;(打开菜单)<br>
Ctrl + H&nbsp;&nbsp;&nbsp;&nbsp;(打开高级设置)<br>
Ctrl + G&nbsp;&nbsp;&nbsp;&nbsp;(打开MCH的Github页面)<br>
Ctrl + Alt&nbsp;&nbsp;&nbsp;&nbsp;(切换颜色)<br>
<br>
Alt + F1&nbsp;&nbsp;&nbsp;&nbsp;(entry "§" in caret position)<br>
Alt + C&nbsp;&nbsp;&nbsp;&nbsp;(switch color)<br>
Alt + L&nbsp;&nbsp;&nbsp;&nbsp;(switch language)<br>
<br>
Ctrl + /&nbsp;&nbsp;&nbsp;&nbsp;(entry "/" in first char)<br>
Alt + /&nbsp;&nbsp;&nbsp;&nbsp;(entry "/" in first char)<br>
<br>
Ctrl + R&nbsp;&nbsp;&nbsp;&nbsp;(restart MCH)<br>
Alt + R&nbsp;&nbsp;&nbsp;&nbsp;(restart MCH)<br>

## ----------menu usage----------
<br>
usage of various parts of menu<br>
<br>

### color status
button color is it status<br>
if the button color is the same as the background,then means it are using this option<br>
if not,it means you can switch after click this button<br>
<br>

### fast load

<Waiting For Translation>

快速加载选项:<br>
此选项减少了程序处理垃圾的频率,因此可以更快加载历史记录等文件<br>
但是因为减少了垃圾处理,内存可能变得很高,在极限情况下可以达到8GB内存占用<br>
(极限情况:270000行文本,每行600个龘字)<br>
<br>
不过,一般限制下,内存很少超出200MB<br>
(一般限制:800行文本,每行512个龘字)<br>
<br>
并且内存占用仅在读取时,完成后内存都会恢复到正常水平<br>
<br>

### 置于顶层
置于顶层选项:<br>
此选项可以将MCH放在显示器最上方,当鼠标焦点在MC时也可很方便查询指令<br>
<br>

### 按下退出按钮
按下退出按钮可以执行两个操作:<br>
一个是直接退出<br>
另一个是缩小MCH<br>
<br>
缩小MCH和置于顶层一起使用时,在需要很多指令的地图制作就很方便(吧?)<br>
<br>

### 更新源
更新源选项:<br>
此选项用于设置更新MCH时从哪里获取更新包,看情况选择即可

### 用户协议按钮
按下这个按钮后会跳转到用户协议页面,在语言不同而时会跳转到不同的页面

### 删除数据按钮
按下删除数据按钮可以删除MCH路径下除了配置文件以外的所有文件

### 帮助按钮
按下帮助按钮会跳转到此页面,如果语言是中文的话
如果语言选择了英文,则会跳转到<a href = "https://github.com/andogy/MCH/tree/main/English/Helps">英文帮助页面</a>

### 重启MCH按钮
按下这个按钮后会尝试重启MCh,如果在一秒内没有新的MCH窗口出现,请手动重启

### Github按钮
按下这个按钮后会跳转到Github页面,此页面也是根据语言不同而变化的

### 进阶设置
按下这个按钮会进入进阶设置,里面有者比较全面的MCH设置

## ----------退出----------
<br>
退出MCH的方式目前有五种<br>
&nbsp;&nbsp;&nbsp;&nbsp;1.在输入栏输入.exit进行退出<br>
&nbsp;&nbsp;&nbsp;&nbsp;2.使用右上角的X按钮退出<br>
&nbsp;&nbsp;&nbsp;&nbsp;3.长按Enter键退出<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.任务管理器结束任务<br>
&nbsp;&nbsp;&nbsp;&nbsp;5.关闭电脑<br>
<br>
第一种情况适用于将退出按钮禁用时使用<br>
第二种情况为一般情况<br>
第三种情况为非法退出,此方法的退出会被error.log收录,使用次数过多可能会占用很大存储空间<br>
第四种情况为无法操作窗口时常用操作,可以强行停止程序<br>
第五种情况一般在电脑无法操作时使用,目前还没有因为MCH导致此方法使用的案例<br>

## ----------配置文件----------
<br>
配置文件一般存储于C:\.MCH\settings.ini中<br>
暂不考虑允许自定义配置路径<br>
<br>
配置文件中记录的配置是按行来存储的<br>
每行存储一个配置<br>
<br>
配置文件都以引用形式来存储<br>
例如Color@White或者Language@Chinese<br>
前面一般是设置目标,后面是选项<br>
<br>
配置文件不建议手动修改,如果非要的话,看以下<br>
<br>
配置文件目前有几种目标<br>
Color用于设定颜色<br>
Language用于设定语言<br>
Button用于设定按钮事件<br>
Load用于设定加载方式<br>
Display用于设定显示<br>
Update用于设定更新时的下载源<br>
<br>
-Color有两个可用选项<br>
&nbsp;&nbsp;&nbsp;&nbsp;-Black(黑色)<br>
&nbsp;&nbsp;&nbsp;&nbsp;-White(白色)<br>
<br>
-Language有两个可用选项<br>
&nbsp;&nbsp;&nbsp;&nbsp;-Chinese(中文)<br>
&nbsp;&nbsp;&nbsp;&nbsp;-English(英文)<br>
<br>
-Button有一个可用子项<br>
&nbsp;&nbsp;&nbsp;&nbsp;-Ex(Exit Button)<br>
&nbsp;&nbsp;&nbsp;&nbsp;[程序的默认关闭按钮]<br>
-Ex有两个可用选项<br>
&nbsp;&nbsp;&nbsp;&nbsp;-Exit(退出)<br>
&nbsp;&nbsp;&nbsp;&nbsp;-Smaller(变小)<br>
此处的选项设置不再为@,而是使用点(.)<br>
例如Button@Ex.Exit为Ex按钮执行退出程序的行为<br>
又或者Button@Ex.Smaller则为Ex按钮执行缩小窗口的行为<br>
<br>
-Load有两个可用选项<br>
&nbsp;&nbsp;&nbsp;&nbsp;-Fast(快速)<br>
&nbsp;&nbsp;&nbsp;&nbsp;-Safe(慢速)<br>
<br>
-Display有两个可用选项<br>
&nbsp;&nbsp;&nbsp;&nbsp;-OnTop(置顶)<br>
&nbsp;&nbsp;&nbsp;&nbsp;-Default(默认)<br>
    <br>
-Update有两个可用选项<br>
&nbsp;&nbsp;&nbsp;&nbsp;-Github<br>
&nbsp;&nbsp;&nbsp;&nbsp;-CbNet<br>
 <br>
-Cache有两个可用选项<br>
&nbsp;&nbsp;&nbsp;&nbsp;-Save(保存)<br>
&nbsp;&nbsp;&nbsp;&nbsp;-Delete(删除)<br>
<br>

## ----------帮助----------
<br>
在使用时出现无法解决的问题,可以进入MCH反馈群反馈或者直接联系开发者<br>
反馈群群号1026701078<br>
<br>
可以直接联系开发者:<br>
QQ:<br>
1501917367<br>
2293332045<br>
<br>
Wechat:<br>
zhuaidadaya<br>
<br>
电话联系暂不提供<br>
<br>
平常时间建议联系1501917367或zhuaidadaya,另一人不一定能及时给予回复<br>
节假日随意<br>
<br>
如果只是想提供问题和想法,不想交流的话,可以将内容编辑好发送到以下邮箱<br>
1501917367@qq.com<br>
3477124880@qq.com<br>
zhuaidadaya@163.com<br>
<br>
在收到邮件后会在一星期内对问题和建议进行讨论并给予回复<br>
<br>

## ----------人员----------
<br>
从此往下的内容不再为一般用户需要的帮助<br>
<br>
MCH的开发人员至今只有2名开发人员,一名测试人员<br>
目前有邀请测试人员的想法,不太打算再增加开发人员,但并不全部拒绝,具体视情况而定<br>
<br>
事先声明:<br>
加入我们作为测试人员不能获得任何广义上的回报(如工资等)<br>
加入是以友情帮助名义进行,可以获得MCH最新程序以及源码等,除此之外别无好处(貌似这也不算多好的好处)<br>
总之可以当成是交个朋友互帮互助<br>
<br>
要加入,请编辑一封邮件发送到1501917367@qq.com<br>
不提供邮件模板参照,大致内容如下:<br>
<br>
(希望加入的部分,例如开发或者测试,亦或运营)<br>
<br>
(擅长部分,使用体验测试、bug测试等方面描述)<br>
<br>
(经验部分,曾测试过什么程序之类的,可以不写)<br>
<br>
(署名)<br>
<br>
以上<br>
<br>

## ----------开源----------
<br>
MCH是一个开源程序,也就是提供源代码<br>
我们将MCH的源码托管在<a href = "https://github.com/andogy/MCH">Github</a><br>
你可以随时访问下载<br>
<br>
你可以使用源码尝试增加功能、修复bug,甚至将其改为完全于原来不同,这随意<br>
但是,请不要使用源码进行修改发布,这属于侵权行为,如若我们发现,可能会造成一些后果,此后果需要你自己承担,即使是由我们引起的<br>
<br>

## ----------可扩展性----------
<br>
MCH未来有打算开放使用json的扩展<br>
<br>
至于未来是多久,这就不好说了,或许一个版本,或许几个版本<br>

