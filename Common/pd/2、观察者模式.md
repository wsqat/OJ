## 观察者模式

实现观察者模式非常简单，
- 创建被观察者类，它继承自java.util.Observable类；
- 创建观察者类，它实现java.util.Observer接口；
 
## 实现步骤

### 对于被观察者

- 观察者要实现该方法，void addObserver(Observer o)；addObserver()方法把观察者对象添加到观察者对象列表中

- 当被观察者中的事件发生变化时，执行
- 1、setChanged();           setChange()方法用来设置一个内部标志位注明数据发生了变化；
- 2、notifyObservers();
notifyObservers()方法会去调用观察者对象列表中所有的Observer的update()方法，通知它们数据发生了变化。

- 3、只有在setChange()被调用后，notifyObservers()才会去调用update()。
 


###  对于观察者类

- 1、在构造方法中把自己注册加入观察者
- 2、实现Observer接口的唯一方法update，
void update(Observable o, Object arg)：形参Object arg，对应一个由notifyObservers(Object arg);传递来的参数，当执行的是notifyObservers();时，arg为null。



## 实现代码

> 被观察者

```
import java.util.Observable;

public class ServerManager extends Observable{
    private int data = 0;

    public int getData(){
        return data;
    }

    public void setData(int i){
        if (this.data != i){
            this.data = i;
            setChanged();
        }
        //只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。
        notifyObservers();

    }
}

```

> 观察者

AObserver

```
import java.util.Observable;
import java.util.Observer;

public class AObserver implements Observer{

    public AObserver(ServerManager sm){
        super();
        sm.addObserver(this);//注册加入观察者
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("AObserver receive:Data has changed to "+((ServerManager) o).getData());
    }
}

```


BObserver

```
import java.util.Observable;
import java.util.Observer;

public class BObserver implements Observer{
    public BObserver(ServerManager sm){
        super();
        sm.addObserver(this);//注册加入观察者
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("AObserver receive:Data has changed to "+((ServerManager) o).getData());
    }
}

```




> 测试代码

```
public class TestDemo {
    public static void main(String[] args){
        ServerManager sm = new ServerManager();
        AObserver a = new AObserver(sm);
        BObserver b = new BObserver(sm);
        sm.setData(5);
        sm.deleteObserver(a);//注销观察者，以后被观察者有数据变化就不再通知这个已注销的观察者
        sm.setData(0);
    }
}
```


> 输出结果

```
AObserver receive:Data has changed to 5
AObserver receive:Data has changed to 5
AObserver receive:Data has changed to 0
```





[java自带的观察者模式](https://www.cnblogs.com/gongjian/p/6104766.html)