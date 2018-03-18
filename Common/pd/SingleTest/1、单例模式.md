# 单例模式

> 单例模式：单例模式定义与作用就是保证整个程序中某个实例有且只有一个。


- 饿汉模式：已加载类就创建对象。 饿汉模式中，初始化变量的时候最好加上final关键字，这样更为严谨。


- 懒汉模式：用的时候再去加载。
懒汉模式中，会通过 判 null，然后 new 出一个实例，也就是懒汉模式会延迟加载出实例对象。



## 饿汉模式

```
public class Singleton {
    private Singleton(){}
    
    private static final Singleton single = new Singleton();
    
    public static Singleton GetInstance(){
        return single ;
    }
}
```


## 懒汉模式，线程不安全
```
//懒汉模式，线程不安全
public class Singleton {
    private Singleton(){}
    private static Singleton single = null;
    public static Singleton GetInstance(){
        if (single==null){
            single = new Singleton();
        }
        return single;
    }
}
```

## 懒汉模式，线程安全
```
package pd;
//设计模式包
//单例模式
//懒汉模式，线程不安全
//public class Singleton {
//    private Singleton(){}
//    private static Singleton single = null;
//    public static Singleton GetInstance(){
//        if (single==null){
//            single = new Singleton();
//        }
//        return single;
//    }
//}

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//线程安全，双重校验锁
public class Singleton {
    //关键点0：构造函数是私有的
    private Singleton(){}
    //关键点1：声明单例对象是静态的
    private volatile static Singleton single = null;
    //通过静态犯法类构造对象
    public static Singleton GetInstance(){
        //关键点2：判断单例对象是否已经被构造
        if (single==null){
            //关键点3：加线程锁 lock.lock(obj);
            synchronized(Singleton.class) {
                //关键点4：二次判断单例是否已经被构造
                if (single == null){
                    single = new Singleton();
                }
            }
        }
        return single;
    }
}
```


这样我们的安全隐患就被解决了，但是同样带来了一个问题。那就是每次都要判断锁，程序的执行效率就会比较低。所以我们就应该尽量减少判断锁的次数，以提高运行效率。加上双重判断，也就是最开始的代码。
推荐使用饿汉模式，简单，安全。


## 破坏单例模式
破解final的单例类，让其生成多个实例对象？通过反射

```
package pd;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingleReflectAttack {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
    {
        Class<?> classType = Singleton.class;

        Constructor<?> c = classType.getDeclaredConstructor(null);//无参构造函数 
        c.setAccessible(true);// 设置private权限修饰符为可见  
        Singleton e1 = (Singleton)c.newInstance();
        Singleton e2 = Singleton.GetInstance();
        System.out.println(e1==e2);//false
    }
}

```

> 输出：false，故反射破坏了单例模式