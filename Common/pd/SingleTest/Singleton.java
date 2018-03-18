package pd.SingleTest;
//设计模式包
//单例模式
//饿汉模式
public class Singleton {
    private Singleton(){}

    private static final Singleton single = new Singleton();

    public static Singleton GetInstance(){
        return single ;
    }
}


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

//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

//线程安全，双重校验锁
//public class Singleton {
//    //关键点0：构造函数是私有的
//    private Singleton(){}
//    //关键点1：声明单例对象是静态的
//    private volatile static  Singleton single = null;
//    //通过静态犯法类构造对象
//    public static Singleton GetInstance(){
//        //关键点2：判断单例对象是否已经被构造
//        if (single==null){
//            //关键点3：加线程锁 lock.lock(obj);
//            synchronized(Singleton.class) {
//                //关键点4：二次判断单例是否已经被构造
//                if (single == null){
//                    single = new Singleton();
//                }
//            }
//        }
//        return single;
//    }
//}