# previousVersions
in Here,Saved all Previous Version of MCH<br>
Provide Learn and Reference for this project
Top Version is Newly Version

## algorithm of version ID
version ID is composed of version type and separate and version<br>
<br>
first two digits of ID represent the version type of this version<br>
"11" is ah(Alpha)<br>
"12" is beta<br>
"13" is Official release version<br>
"14" is Debug version<br>
<br>
3-5 digits is random number by MCH unique algorithm<br>
<br>
6-7 digits always is "01",this is two digit number that separates a random number from a version number<br>
<br>
rest number is version<br>
like 0.0.1.1,in version ID it should be done "0011",or like 0.0.1.22,in version ID it should be done "00122"<br>


## updateLogs

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-29.zip">MCH_ah_0-0-1-29(111470100129)</a>
```
add launcher function,this function can start and off Minecraft Bedrock Edition
we have a plan make a launcher of java edition
now error log can is save not,this setting is at Menu>advance>save error

(2021.5.9)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-28.zip">MCH_ah_0-0-1-28(111550100128)</a>
```
add color display of color code(§),input box will have colors
this function is testing,maybe have lot of bug(but bugs cannot find in my test)

(2021.5.8)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-27.zip">MCH_ah_0-0-1-27(111460100127)</a>
```
fixed some bug of command completion
add higt light,used in command completion
fixed other bug

(2021.5.8)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-26.zip">MCH_ah_0-0-1-26(111540100126)</a>
```
fixed problem of cache again
add save cache function,set in Menu>Advanced>save cache
add a shortcut key:
    Ctrl + H    open advanced settings

(2021.5.7)
```
### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-25.zip">MCH_ah_0-0-1-25(111540100125)</a>
```
add completion with UP/DOWN key and Tab,this function can use in history
    usage:
    1.on input,Entry UP/DOWN(↑/↓) or Tab key can completion the command,Entry other key to take effect,Entry Backspace(退格) to cancel
    2.after completion,Entry space to generate source command and a space,if not Entry space,there is only completion source command
        such as:
        try completion "gamemode",if Entry space,input box will have "gamemode ",if not then have "gamemode"
this version major UPD this function,have some minor structural change in other parts,have not abundant UPD
completion function have lots bug,but we fix will fast

(2021.5.7)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-24.zip">MCH_ah_0-0-1-24(111440100124)</a>
```
fixed problem of UPD cache residue
internal execution process is slightly adjusted
add reStart shortcut key:
    Ctrl + R
    Alt + R

(2021.5.6)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-23.zip">MCH_ah_0-0-1-23(111520100123)</a>
```
add some Shortcut key:
    Ctrl + Esc    exit MCH
    Ctrl + Delete    clear input(Available when entry )
    Ctrl + M    open the Menu UI(Available when not entry)
    Ctrl + U    check UPD
    Ctrl + G    go to Github of MCH
    Alt + C     switch color
    Alt + L     switch language
    Alt + Ctrl  switch color too
add a key coloregg,Press the adjacent key of two at the same time to find

(2021.5.5)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-22.zip">MCH_ah_0-0-1-22(111430100122) </a>
```
fixed problem of Github source cannot get UPD,now connect will not be rejecte ,but in china maybe will out time
fixed version judge problem with last version

(2021.5.5)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-21.zip">MCH_ah_0-0-1-21(112440100121)</a>
```
now can use UPD function
add switch source button,can switch UPD use Github or CbNet be download
solved problem as "installing" of version 111970100120
used lots Thread.sleep,Simulate processing time
add countTime class,used judge connect out time
has a auto check UPD as MCH start

(2021.5.4)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-20.zip">MCH_ah_0-0-1-20(111970100120)</a>
```
add UPD(update) function,but web file cannot be ready,so this version cannot use yet this function
add restart button
add check UPD button,and check return
add "getJar" class,used get own path(full path)
add "URLs" thread,used process url request,for example check update ETC.

(2021.5.3)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-19.zip">MCH_ah_0-0-1-19(112660100119)</a>
```
after this version,will use version ID(112660100119),but not delete version(MCH_ah_0-0-1-19)
add 3 buttons:
    -Helps    helps document
    -agreement    user agreement
    -gayhub    MCH Github
Changed some order of internal methods,now bug less(but lots too)

(2021.5.2)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-18.zip">MCH_ah_0-0-1-18</a>
```
a small update,Change command process to only process the content before the cursor
ver parameter stored the Community,before that stored in the language

(2021.4.30)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-17.zip">MCH_ah_0-0-1-17</a>
```
add tips function,Disply at input be null

(2021.4.30)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-16.zip">MCH_ah_0-0-1-16</a>
```
New a color eggs,hint:entry at the input
the errors add a new parameter: er,this parameter is Error type,at present just OutOfMemory use this parameter
    Cause OutOfMemoryExeption:Entry 3.4 million characters at the Input and key Enter
        Do not try without protection,This operation may crash yor Computer 

(2021.4.29)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-15.zip">MCH_ah_0-0-1-15</a>
```
move the UI form the resources to the Community
add a Button for delete MCH data,delete list:
    run.log
    errors.log
    history.txt
Optimize history read again,Memory used reduced to less than 150M

(2021.4.27)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-14.zip">MCH_ah_0-0-1-14</a>
```
add Helps class
historyRead add a Judge file length function
    it stored file length After read file,Next read need Judge file length than stored consistent or not
        if consistent,maybe file not Revised,not refresh,Reduce CPU and Memory ETC use
        if consistent not,maybe user copied a command or Edited at external,This is time to refresh
MenuUI new OnTop function

(2021.4.23)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-13.zip">MCH_ah_0-0-1-13</a>
```
This Version updated command parsing part

(2021.4.22)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-12.zip">MCH_ah_0-0=1-12</a>
```
Display MCH version in MenuUI
errors of Error class add two parameter,they is cannotHandle and exceptionSource
    cannotHandle is boolean type,Used to set Whether errors can be ignored
    exceptionSource is String type,Used to set error source,will write in errors.log
Delete Gc Thread
Optimize history read,Memory used Significantly reduced

(2021.4.22)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-11.zip">MCH_ah_0-0-1-11</a>
```
add LoadAssembly class,this class will write state output in the run.log
add Errors class,this class will process errors and exit at When necessary

(2021.4.20)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-10.zip">MCH_ah_0-0-1-10</a>
```
add history record function,this function will record copied commands and display history at input is null
add fast load function,this function just use for history,Enable will Reduce memory recycles,so can to speed up the loading speed,But Memory used will super hight

(2021.4.19)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-9.zip">MCH_ah_0-0-1-9</a>
```
add exit button events switch,now user can decision click exit button will exit or smaller
add commandLibrary class,this is a Dictionary
Process part set to stream,not process in a class
Events add switchExButtonWillExit function,used switch exit button will do what things
config file is no longer expressed as "Color = Whita",now it is "Color@White
add random problem button,But this function update maybe after many version,Because it is not the main part

(2021.4.17)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-8.zip">MCH_ah_0-0-1-8</a>
```
Events add switch Method set:
    -switchColor    switch UI color
    -switchLanguage    switch UI language
add Gc thread,used Reclaim memory,Later it was found that it was useless.it was delete after a few versions
ini add create guide,if config file have not then guide user go set basic settings
add iniHas thread,used judge config file have or not,if have not then close MenuUI
"CaUI" rename to "MchUI"
Most of the contents in Parsing are annotated,Because will to change the precess manner
add language switch

(2021.4.17)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-7.zip">MCH_ah_0-0-7</a>
```
delete HelpUI
color switch button finished
dir "ca" rename to "MCH"
MenuUI add file stored path display and color switch(not finished)
EXI claa rename to exit
add Times class,used format time for state print
add state print function,it is print time add doing things
add ini class,used config file load,because need load color and language ETC,config file stored at C:\MCH\settings.ini


(2021.4.14)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-6.zip">MCH_ah_0-0-1-6</a>
```
exit event class rename to EXIT_Button.java
add EXI class,Used to enter ".exit" to prompt
add HelpUI,at that time was probably intended used for help,it was deleted after a few versions,because the website was used

(2021.4.11)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-5.zip">MCH_ah_0-0-1-5</a>
```
Change the copy mode to key enter to copy
Change the event of click exit button to smaller the window
HelpUI rename to MenuUI
Store Color and Language in Community. Java
colors add black

(2021.4.10)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-4.zip">MCH_ah_0-0-1-4</a>
```
Convert capital letters to lowercase at Parsing
language add english

(2021.4.10)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-3.zip">MCH_ah_0-0-1-3</a>
```
add language thread,used set UI language
add Parsing thread,used parsing the input texts
add easy command and test at Parsing
deleta copy button
add HelpUI,But this is Menu,not is Helps


(2021.4.10)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-2.zip">MCH_ah_0-0-1-2</a>
```
UI add command input area add copy button
add colors thread,used set UI color
add Events Method set
Events add copy function,used copy input text
main class rename to Community.java

(2021.4.10)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1-1.zip">MCH_ah_0-0-1-1</a>
```
add a basic frame
add click exit button event

(2021.4.9)
```

### <a href = "https://github.com/andogy/MCH/raw/main/%E4%B8%AD%E6%96%87/%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC/%E6%BA%90%E7%A0%81/MCH_ah-0-0-1.zip">MCH_ah_0-0-1</a>
```
created a Empty project

(2021.4.9)
```
