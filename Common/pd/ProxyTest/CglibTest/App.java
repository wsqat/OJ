package pd.ProxyTest.CglibTest;

import org.junit.Test;

/**
 * ������
 */
public class App {

    @Test
    public void test(){
        //Ŀ�����
        UserDao target = new UserDao();

        //�������
        UserDao proxy = (UserDao)new ProxyFactory(target).getProxyInstance();

        //ִ�д������ķ���
        proxy.save();
    }
}
