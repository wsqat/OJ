package pd.ProxyTest.dynamicTest;

/**
 * ������
 */
public class App {
    public static void main(String[] args) {
        // Ŀ�����
        IUserDao target = new UserDao();
        // ��ԭʼ������ class pd.ProxyTest.staticTest.UserDao��
        System.out.println(target.getClass());

        // ��Ŀ����󣬴����������
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   �ڴ��ж�̬���ɵĴ������
        System.out.println(proxy.getClass());

        // ִ�з���   ���������
        proxy.save();
    }
}