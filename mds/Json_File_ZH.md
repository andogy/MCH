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
                ......
            }
        ],
        "chinese": [
            {
                "@command_example": "这是一个语言示例",
                "@example": "语言示例",
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
            }
        }
    }
}
```

### 主信息
