package pd.SingleTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingleReflectAttack {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
    {
        Class<?> classType = Singleton.class;

        Constructor<?> c = classType.getDeclaredConstructor(null);//无参构造函数
        c.setAccessible(true);
        Singleton e1 = (Singleton)c.newInstance();//设置private权限修饰符为可见
        Singleton e2 = Singleton.GetInstance();
        System.out.println(e1==e2);//false
    }
}
