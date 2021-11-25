<html>
<head>
<title>源码修改帮助 - MCH</title>
<link rel="stylesheet" href="main.css">
</head>
<body>
以下帮助不再为用户编辑<br>
目标群体为有意加入测试或开发人<br>
以下只提供些许方法实现<br>
若要全面请询问老开发人员或下载源码<br>
<hr>
<br><br>
----------代码----------<br>
<br>
以下为部分代码<br>
<br>
-----<br>
代码如下:<br><br>

StringSelection selection = new StringSelection(MchUI.input_Command.getText());<br>
Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();<br>
clipboard.setContents(selection, null);<br>
<br>
使用ClipBoard打开系统剪贴板<br>
而后将从input获取到的文本加上系统剪切板去<br>
<br>
在完成后,会将input设为空的<br>
还会将复制到的数据写入历史记录<br>
<br>
引发Enter崩溃的代码位置也于此,高频率开关系统剪切板,最后会导致无法打开,引发IllegalStateException(非法状态)<br>
此错误并不足以导致程序或系统崩溃,但偶然发现这个方法用于退出貌似是个不错的选择<br>
所以在引发从错误后,在catch中使用Errors方法并告知无法处理,中断程序的运行并输出崩溃提示<br>
<br>
Error调用:<br>
<br>
try {<br>
    ......<br>
} catch(IllegalStateException e) {<br>
    Errors(e,true);<br>
}<br>
<br>
Errors : 内部方法(MCH用于处理错误所制作的方法)<br>
<br>
e : Exception(传入Exception记录这次崩溃)<br>
true : CannotCannotHandle(设定无法处理,以此停止程序运行,如果不设定,只会记录错误而不会停止程序)<br>
<br>
在0.0.1-*ah版本中将Errors的参数设为了3个,多出的一个为String<br>
这个String用于传递错误位置,例如手动崩溃,传递的是"copy"<br>
这代表问题出在复制时,无法打开剪贴板则被划分在这区间内<br>
<br>
<hr>
<br>
崩溃其实是程序停止了主要运行部分,算是内部崩溃,并非程序崩溃<br>
<br>
停止了以后会降低CPU运作、内存占用<br>
<br>
因为要给予提示,所以不能直接System.exit(*);<br>
而是要给一个窗口留在面前显示<br>
所以只停止不直接退出,也免得不看帮助的用户觉得那是一个大问题<br>
<br>
不过不可能一直停留在那里,在停留10s后会自动使整个程序退出<br>
<hr>
咕咕咕
<hr>
<big><strong><p class="center">页面版本: 1.0（最终版本）</p></big></strong>
<textarea />
</body>
</html>
