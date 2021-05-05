# previousVersions
in Here,Saved all Previous Version of MCH<br>
Provide Learn and Reference for this project
Top Version is Newly Version

## updateLogs
```
MCH_ah_0-0-1-22(111430100122)
fixed problem of Github source cannot get UPD,now connect will not be rejecte ,but in china maybe will out time
fixed version judge problem with last version

(2021.5.5)

-

MCH_ah_0-0-1-21(112440100121)
now can use UPD function
add switch source button,can switch UPD use Github or CbNet be download
solved problem as "installing" of version 111970100120
used lots Thread.sleep,Simulate processing time
add countTime class,used judge connect out time
has a auto check UPD as MCH start

(2021.5.4)

-

MCH_ah_0-0-1-20(111970100120)
add UPD(update) function,but web file cannot be ready,so this version cannot use yet this function
add restart button
add check UPD button,and check return
add "getJar" class,used get own path(full path)
add "URLs" thread,used process url request,for example check update ETC.

(2021.5.3)

-

MCH_ah_0-0-1-19(112660100119)
after this version,will use version ID(112660100119),but not delete version(MCH_ah_0-0-1-19)
add 3 buttons:
    -Helps    helps document
    -agreement    user agreement
    -gayhub    MCH Github
Changed some order of internal methods,now bug less(but lots too)

(2021.5.2)

-

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

-

MCH_ah_0-0-7
delete HelpUI
color switch button finished
dir "ca" rename to "MCH"
MenuUI add file stored path display and color switch(not finished)
EXI claa rename to exit
add Times class,used format time for state print
add state print function,it is print time add doing things
add ini class,used config file load,because need load color and language ETC,config file stored at C:\MCH\settings.ini


(2021.4.14)

-

MCH_ah_0-0-1-6
exit event class rename to EXIT_Button.java
add EXI class,Used to enter ".exit" to prompt
add HelpUI,at that time was probably intended used for help,it was deleted after a few versions,because the website was used

(2021.4.11)

-

MCH_ah_0-0-1-5
Change the copy mode to key enter to copy
Change the event of click exit button to smaller the window
HelpUI rename to MenuUI
Store Color and Language in Community. Java
colors add black

(2021.4.10)

-

MCH_ah_0-0-1-4
Convert capital letters to lowercase at Parsing
language add english

(2021.4.10)

-

MCH_ah_0-0-1-3
add language thread,used set UI language
add Parsing thread,used parsing the input texts
add easy command and test at Parsing
deleta copy button
add HelpUI,But this is Menu,not is Helps


(2021.4.10)

-

MCH_ah_0-0-1-2
UI add command input area add copy button
add colors thread,used set UI color
add Events Method set
Events add copy function,used copy input text
main class rename to Community.java

(2021.4.10)

-

MCH_ah_0-0-1-1
add a basic frame
add click exit button event

(2021.4.9)

MCH_ah_0-0-1
created a Empty project

(2021.4.9)
```
