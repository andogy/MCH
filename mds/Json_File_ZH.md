# Json 文件

在进行学习扩展文件制作之前，请确保自己有一些编程基础以便能够理解以下内容

## 方式

MCH需要解析三个位置的信息才可以正常完成一次完整的解析<br>
1.<a href="">主信息</a><br>
2.<a href="">资源信息</a><br>
3.<a href="">语言信息</a><br>

### 解析结构

下面是一个示例的扩展文件<br>

这看起来可能稍微有那么点长,并且还不好理解<br>
不过没有关系,这只是一个示例,稍后会有详细的解释

```json
{
    "languages": {
        "english": [
            {
                "@command_example": "This is a example command",
                "@example": "language example",
                "@example2": "just these",
                ......
            }
        ],
        "chinese": [
            {
                "@command_example": "这是一个语言示例",
                "@example": "语言示例",
                "@example2": "就这些了",
                ......
            }
        ]
    },
    "resources": {
        "commands": {
            "example": {
                "description": "@command_example",
                "wiki": "*here need a website link",
                "limited": [
                    "java",
                    "op"
                ],
                "usage": [
                    {
                        "tree": [
                            "@example_step1",
                            "@example_step2"
                        ]
                    }
                ]
            }
        },
        "resource": {
            "@example_step1": {
                "description": "@example",
                "wiki": "*here need a website link",
                "wikiTips": "Example"
            },
            "@example_step2": {
                "description": "@example",
                "wiki": "@here need a website link",
                "wikiTips": "Example2"
            }
        }
    }
}
```

## 主信息

主信息,是指文件中的commands区域的信息

```json
{
    "resources": {
        "commands": {
        }
    }
}
```

commands中,以对象列表的形式保存命令

```json
{
    "commands": {
        "commands1": {
        },
        "commands2": {
        },
        "commands3": {
        }
    }
}
```

每个单独的命令中,可以存储8个信息,分别是<br>
1.description<br>
2.wiki<br>
3.wikiTips<br>
4.limited<br>
5.usage<br>
6.$invalid<br>
7.forks<br>
8.$execute<br>

### description

这个用于存储命令的介绍<br>
注意：description是必须要存在于命令中,否则将无法解析此条命令

使用字符串储存一个语言资源名称<br>
例如<br>

```json
{
    "commands1": {
        "description": "@commands1_description"
    }
}
```

在解析description时,会尝试在<a herf="">语言池</a>中寻找与description内容一致的资源<br>

根据不同的语言设定,解析时寻找的位置也不同<br>
因此建议将中文和英文都进行语言完成,否则可能导致切换语言后出现空白介绍<br>
<br>
<br>
在语言中,使用@开头标记命令引用,而没有@则为其他提示<br>
这是一个规范标准,不是硬性规定

### wiki

这个用于存储维基百科的链接

使用一个字符串来存储网址资源<br>
例如

```json
{
    "commands1": {
        "wiki": "https://www.baidu.com"
    }
}
```

> 以上只是一个示例,实际的网址根据情况而变<br>
> 仅允许http以及https形式

在解析wiki时,会直接在浏览器打开内容,如果内容错误会导致无法打开

### wikiTips

这个用于存储维基百科的即将前往的词条名

使用字符串存储一个语言资源名称

```json
{
    "commands1": {
        "wikiTips": "@commands1_wikiTips"
    }
}
```

在解析wikiTips时,与description流程一致的<br>

根据不同的语言设定,解析时寻找的位置也不同<br>
因此建议将中文和英文都进行语言完成<br>
不过并不像description一样,没有语言就会是空白<br>
如果wikiTips没有语言资源,则会把命令当作语言资源进行显示

例如commands1<br>

有了语言应该显示为(示例)以下<br>
```
前往 "命令1" 的wiki页面
``` 
如果没有语言资源,则会显示以下<br>
```
前往 "commands1" 的wiki页面 
```


### limited

这个用于存储使用限制

使用一个数组来存储限制列表<br>
例如

```json
{
    "commands1": {
        "limited": [
            "limited1",
            "limited2"
        ]
    }
}
```

<hr>

limited在命令中,一般用于储存版本差异以及命令使用权限<br>
例如java则代表仅Java版可用,bedrock同理<br>
以下是列表<br>

```
java   表示仅Java版可用此命令
bedrock   表示仅基岩版可用此命令
edu   表示仅教育版可用此命令
bds   表示仅bedrock server控制台可使用此命令
wsserver   表示仅websocket server可使用此命令
normal   表示该命令通用
op   表示需要管理员权限才可以使用此命令
player   表示谁都可以使用此命令
```

<hr>

在<a href="">forks</a>中也可以使用limited<br>
仅用于表示此命令分支的使用版本<br>
于是只有以下两种可用参数

```
java   表示仅Java版可用
bedrock   表示仅基岩版可用 
```

<hr>

limited还可以在<a href="">resource</a>中使用<br>
不过目前仅可解析@Number的参数,且必须是一个不大于long限制的整数数字<br>
示例

```json
{
    "commands1_step1": {
        "@Number": {
            "limited": 10000
        }
    }
}
```

### usage

这个用于存储命令使用方式

使用一个分支列表存储一个<a href="">resource</a>列表 示例

```json
{
    "commands1": {
        "usage": [
            {
                "tree": [
                    "commands1_step1",
                    "commands1_step2"
                ]
            },
            {
                "tree": [
                    "commands1_step1",
                    "commands1_step2"
                ]
            }
        ]
    }
}
```

tree中的每一个资源,表示一个空格以后即将要前往的<a href="">resource</a><br>
例如"commands1_step1"<br>

```json
{
    "resource": {
        "commands1_step1": {
            "description": "it will be show when command have first space"
        }
    }
}
```

解析就是按照tree里面提供的resource一步一步往下走

<hr>

当输入的用法于第一个tree不符合时,将会前往第二个tree寻找是否可用<br>
如果所有的tree都无法匹配用法,那么会提示无法找到用法

以及,可以使用<a href="">forks</a>和<a href="">resource</a>中的<a href="">toResource</a>进行资源或分支指定

### $invalid

这个用于储存命令的失效状态

存储一个布尔指说明命令有没有失效 例如

```json
{
    "commands1": {
        "$invalid": true
    }
}
```

像上面这样写即可把命令标记为失效<br>
在设置关闭失效命令显示时就不会再看到这条命令的任何提示了

你也可以将true设置为false表示其未失效<br>
不过这和不写没有区别,虽然可以不过这是多余的操作

<hr>

<b>此参数不限于命令,在resource中也可以使用</b>

### forks

这个用于标记每个分支的版本限制

使用一个数组存储一个对象列表 例如

```json
{
    "commands1": {
        "forks": [
            {
                "fork": "java"
            },
            {
                "fork": "bedrock"
            }
        ],
        "usage": [
            {
                "tree": [
                    "java_commands1_usage1",
                    "java_commands1_usage2"
                ]
            },
            {
                "tree": [
                    "bedrock_commands1_usage1",
                    "bedrock_commands1_usage2"
                ]
            }
        ]
    }
}
```

以下为主要部分

```json
{
    "forks": [
        {
            "fork": "java"
        },
        {
            "fork": "bedrock"
        }
    ]
}
```

forks和tree一样,都是通过数量来解析<br>
第一个fork对应第一个tree<br>
第二个同理<br>

此参数用于在选定版本显示命令时使用<br>
如设置中的"优先显示"或者未来即将添加的"筛选器"都会解析这个参数

### $execute

这个用于存储是否是execute的独有分支显示<br>
例如detect,在基岩版中是execute后和命令混在一起显示的<br>
为了在没有execute时也显示detect,需要有一个东西标记,不让它出来

存储一个布尔表示是否是execute的独有分支显示

这个和$invalid类似,甚至可以说一模一样,就不过多示例了

##