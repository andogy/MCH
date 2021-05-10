# MCH Helps

MCH is a Minecraft Command Helper<br>
<br>
<a href = "http://caibiwangluo.eu5.org/mch/yhxy.php">User agreement</a><br>

## == minimum requirement ==
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

## == basic usage ==
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
use shortcut key can up efficiency of use MCH<br>
MCH have shortcut key as below:<br>
<br>
Ctrl + Esc&nbsp;&nbsp;&nbsp;&nbsp;(exit MCH)<br>
Ctrl + Delete&nbsp;&nbsp;&nbsp;&nbsp;(clear input)<br>
Ctrl + M&nbsp;&nbsp;&nbsp;&nbsp;(display menu)<br>
Ctrl + H&nbsp;&nbsp;&nbsp;&nbsp;(display advanced settings)<br>
Ctrl + G&nbsp;&nbsp;&nbsp;&nbsp;(display Github of MCH page)<br>
<br>
Alt + F1&nbsp;&nbsp;&nbsp;&nbsp;(entry "§" to caret position)<br>
Alt + L&nbsp;&nbsp;&nbsp;&nbsp;(switch language)<br>
<br>
Ctrl + /&nbsp;&nbsp;&nbsp;&nbsp;(entry "/" to first char)<br>
Alt + /&nbsp;&nbsp;&nbsp;&nbsp;(entry "/" to first char)<br>
<br>
Ctrl + R&nbsp;&nbsp;&nbsp;&nbsp;(restart MCH)<br>
Alt + R&nbsp;&nbsp;&nbsp;&nbsp;(restart MCH)<br>
<br>
Ctrl + Alt&nbsp;&nbsp;&nbsp;&nbsp;(switch color)<br>
Alt + C&nbsp;&nbsp;&nbsp;&nbsp;(switch color)<br>

## == menu usage == 
<br>
usage of various parts of menu<br>
<br>

### color status
button color is it status<br>
if the button color is the same as the background,then means it are using this option<br>
if not,it means you can switch after click this button<br>
<br>

### fast load

fast load setting:<br>
this setting reduce frequency of garbage disposal,MCH therefore can load files such as history file more quickly<br>
<br>
this memory used maybe more than 200MB<br>
<br>
but memory used hight just at load that,memory will return to normal at load finished<br>
<br>

### on top
on top setting:<br>
this setting can display MCH in screen top,you can convenient to query command when Minecraft is full screen<br>
<br>

### click exit button
click exit button can do two works:<br>
1.exit<br>
2.smaller MCH<br>
<br>
smaller MCH and on top meanwhile use,maybe very convenient(?)<br>
<br>

### update source
update source setting:<br>

this setting used set how where download update package for MCH,this setting need according your network environment<br>
if you can,we recommended to you to give priority to GitHub sorce

### agreement
click this button will go user agreement page,page change with language

### delete data button
click this button can delete all file at directory of MCH(except settings.ini and file of user add)

### "Helps"
click "Helps" button will go here,if language is english<br>
if language is chinese,then will go <a href = "https://github.com/andogy/MCH/tree/main/%E4%B8%AD%E6%96%87/%E5%B8%AE%E5%8A%A9/%E4%BD%BF%E7%94%A8%E5%B8%AE%E5%8A%A9">chinese help page</a>

### restart MCH button
click this button will try rastart MCh,if no new MCH window display in 1 second,please restart by you self

### Github button
click this button will go Github of MCH,page change with language

### advanced button
click this button will go advanced settings page,there have almost all MCH setting

## == exit ==

<br>
have 5 ways to exit MCH<br>
&nbsp;&nbsp;&nbsp;&nbsp;1.entry ".exit" to input box<br>
&nbsp;&nbsp;&nbsp;&nbsp;2.click exit button to exit<br>
&nbsp;&nbsp;&nbsp;&nbsp;3.keep pressing Enter key<br>
&nbsp;&nbsp;&nbsp;&nbsp;4.used Task Manager off MCH<br>
&nbsp;&nbsp;&nbsp;&nbsp;5.turn off your computer<br>
<br>
<br>
at one case mostly when the exit button is disabled<br>
at two case is normal exit <br>
at three case is illegal exit,this case exit,will recorded in "error.log",too many uses may up a lot of storage<br>
at four case mostly is cannot operation MCH window,can force off MCH<br>
at five case mostlt is cannot operation computer,but at present,this case is no used for MCH causes<br>

## == config ==
<br>
config file save in "C:\.MCH\settings.ini"<br>
unable to customize the config file path temporarily<br>
<br>
<br>
(Waiting for translation)
<br>
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

## == MCH support ==
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

## == 人员 ==
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
(sign name)<br>
<br>
over<br>
<br>

## == open source ==
<br>
MCH is open source,that is,we provide the source code<br>
we save MCH code in <a href = "https://github.com/andogy/MCH">Github</a><br>
you can download it any time<br>
<br>
you can use source code try add new function,fix bug,and even modify it at will<br>
but,Please do not use the modify source code to the release,this is tort,if we know it,consequences need to be borne by yourself,even if we caused it<br>
<br>

## == Expansibility ==
<br>
MCH plan used json to extend<br>
<br>
as for time,it is uncertain,maybe just one version,maybe after several<br>

