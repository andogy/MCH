# MCH

hi welcome MCH project<br>
let we got some about MCH
> this project is suspended<br>
> because developers need go to exam<br>
> will resume development in August<br>
>
> before that, will have some small action<br>
> but may cannot be updating

## first

this project is make by a few beginner<br>
and you can join to this project<br>

```
if you can, i hope you join!<br>
```

<br>
MCH usage to help command of Minecraft<br>
it can help you used or study command of Minecraft<br>
<br>
here is work repository, we have a repository only used to storage <br>
it here -> <a href="https://github.com/andogy/MCH">main repository</a>

<hr>

# if you are user

you can download newly MCH at "release"<br>
if you don't know what is "release", then <a href="https://github.com/zhuaidadaya/MCH/releases">click here</a><br>

please read user agreement before used MCH<br>
<a href="https://github.com/andogy/MCH/tree/main/%E4%B8%AD%E6%96%87/%E5%B8%AE%E5%8A%A9/%E7%94%A8%E6%88%B7%E5%8D%8F%E8%AE%AE">
中文页面</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://github.com/andogy/MCH/tree/main/English/Helps/agreement">
english-page</a>

only these

# other

## developers

> MCH develop just by two developer<br>
> (two beginner)<br>
> this cause inefficiency extraordinary, and we can get two tired developer  
> so, we wanna more developers can join to ours develop<br>
> but we know this is almost impossible to achieve

### if you can join to we

> you need use Tencent qq communicate with us<br>
> we communicate in chinese<br>
> if you cannot speak chinese, don't worry, we also can speak english<br>

### join

> you just need send request to we qq(124319875) and waiting for

## MCH history

> MCH project start with April 9, 2021<br>
> at present we have not a first official release version<br>

## actions

now actions
> fix bug of "execute" command<br>
> local "src/project/Community/Command/CommandParsing" at lines 243<br>
> Here have lots problems<br>
> <b>if you can, i hope you can help we do that</b>

> completing "README.md"
> local "README.md"

> fix other bug

# Code

here have some code about MCH<br>
they show here used to study, and you can try to optimize they<br>
i hope you can fork work repository and use pull requests to changed code
(do not pull to storage repository)

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