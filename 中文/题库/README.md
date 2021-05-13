### 题目：证明AB逆=B逆A逆
1：
```
∵((AB)^-1)(AB) = 1
∴(((AB)^-1)A)B = 1
∴((AB)^-1)A = B^-1
∴(AB)^-1 = (B^-1)(A^-1)
```

2：
```
∵(AB)[B^(-1)A^(-1)]=A[(B^(-1))B]A^(-1)=(A^(-1))A=E
∴[B^(-1)A^(-1)](AB)=B^(-1)[A^(-1)*A]B=(B^(-1))B=E
∴(AB)^(-1)=B^(-1)A^(-1)
```

### 题目：以下极限值等于e的是？

A. lim[x→0](1+1/x)↑x<br>
B. lim[x→0](1+x)↑x<br>
C. lim[x→∞](1+1/x)↑x<br>
D. lim[x→∞](1+x)↑1/x

```
C
```

### 题目：当x→0时，以下各无穷小量中与x^2等价的是？

A. (xsin^2)(x)<br>
B. (xcos^2)(x)<br>
C. xsinx<br>
D. xcosx
```
C
lim[x→0] (x↑2)/xsinx = lim[x→0] x/sinx = 1
所以xsinx与x^2等价
```
### 题目：下列函数中，在x=0处不可导的是？
A. y=3√(x^5)
B. y=5√(x^3)
C. y=sinx
D. y=x^2
```
B
在点x=0处有
lim[x→0] f(x)-f(0)/x = lim[x→0] (x↑3/5)/x = lim[x→0] 1/x↑2/5 = +∞
即导数为无穷大
即B选项在x=0处不可导
```

### 问题：函数f(x)=㏑((x↑2)+2x+2)的单调递减区间是？
A. (-∞,-1)<br>
B. (-1,0)<br>
C. (0,1)<br>
D. (1,+∞)
```
A
因为f′(x) = 2x+2/(x↑2)+2x+2 = 2x+2/((x+1)↑2)+1
当f′(x) < 0时(即x < -l)，函数单调递减，即函数的单调递减区间是(-∞,1)
```

### 问题：执行/execute @e ~ ~ ~ execute @e ~ ~ ~ ... summon pig时(...省略若干条execute)为什么会造成卡顿

```
答：
因为麻将没有为嵌套execute进行优化
在将多条execute载入命令缓冲区时，缓冲区内存溢出，减慢载入速度，进而影响游戏刻加载速度
甚至可以将游戏刻拉长到1tick/分钟(正常游戏刻为20tick/秒)
```

### 问题：1+1=?

```
2
```

### 问题：不定积分∫((tan^2)(x)/1+x^2)dx=?   (非正常问题)

```
答(不保证正确):
∫((tan^2)(x)/1+x^2)dx=
Σ(∞,n=1)Σ(m,k=1) (((4^(n-k))((4^k)-1)((4^n)-(4^k))) / (2n-1)(2k)!(2n-2k)!)B(n)B(n-k)(2)F(1){1,2n-1/2;2n+1/2;-x^2}(x^2n-1)+C
```

> <img src="https://latex.codecogs.com/svg.image?\bg_white&space;\inline&space;\sum_{n=1}^{\infty}\sum_{k=1}^{n}&space;\frac{4^{n-k}\left&space;(&space;4^{k}-1&space;\right&space;)\left&space;(&space;4^{n}&space;-&space;4^{k}&space;\right&space;)}{\left&space;(&space;2n&space;-&space;1&space;\right&space;)\left&space;(&space;2k&space;\right&space;)!&space;\left&space;(&space;2n&space;-&space;2k&space;\right&space;)!}&space;B_{n}B_{n-k}&space;{_{2}\textrm{F}_{1}}\left&space;(&space;1,\frac{2n-1}{2};\frac{2n&plus;1}{2};-x^{2}&space;\right&space;)x^{2n-1}&plus;C" title="\bg_white \inline \sum_{n=1}^{\infty}\sum_{k=1}^{n} \frac{4^{n-k}\left ( 4^{k}-1 \right )\left ( 4^{n} - 4^{k} \right )}{\left ( 2n - 1 \right )\left ( 2k \right )! \left ( 2n - 2k \right )!} B_{n}B_{n-k} {_{2}\textrm{F}_{1}}\left ( 1,\frac{2n-1}{2};\frac{2n+1}{2};-x^{2} \right )x^{2n-1}+C" /><br>
> 生成的图片只有黑色,建议白色背景查看

### 问题: 0和-∞谁是无穷小?

```
答: 0
常识不需要解释
```

### 问题:
