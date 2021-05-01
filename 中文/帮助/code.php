
********************

以下不再帮助不再为用户编辑
目标群体为有意加入测试或开发人员
以下只提供些许方法实现
若要全面请询问老开发人员或下载源码

********************


----------代码----------

以下为部分代码

-----
复制文本代码如下

代码如下:

StringSelection selection = new StringSelection(MchUI.input_Command.getText());
Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboard.setContents(selection, null);

使用ClipBoard打开系统剪贴板
而后将从input获取到的文本加上系统剪切板去

在完成后,会将input设为空的
还会将复制到的数据写入历史记录

引发Enter崩溃的代码位置也于此,高频率开关系统剪切板,最后会导致无法打开,引发IllegalStateException(非法状态)
此错误并不足以导致程序或系统崩溃,但偶然发现这个方法用于退出貌似是个不错的选择
所以在引发从错误后,在catch中使用Errors方法并告知无法处理,中断程序的运行并输出崩溃提示

Error调用:

try {
    ......
} catch(IllegalStateException e) {
    Errors(e,true);
}

Errors : 内部方法(MCH用于处理错误所制作的方法)

e : Exception(传入Exception记录这次崩溃)
true : CannotCannotHandle(设定无法处理,以此停止程序运行,如果不设定,只会记录错误而不会停止程序)

在0.0.1-*ah版本中将Errors的参数设为了3个,多出的一个为String
这个String用于传递错误位置,例如手动崩溃,传递的是"copy"
这代表问题出在复制时,无法打开剪贴板则被划分在这区间内

-

崩溃其实是程序停止了主要运行部分,算是内部崩溃,并非程序崩溃

停止了以后会降低CPU运作、内存占用

因为要给予提示,所以不能直接System.exit(*);
而是要给一个窗口留在面前显示
所以只停止不直接退出,也免得不看帮助的用户觉得那是一个大问题

不过不可能一直停留在那里,在停留10s后会自动使整个程序退出

-----


