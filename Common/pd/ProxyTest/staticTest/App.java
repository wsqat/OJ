package pd.ProxyTest.staticTest;

/**
 * ������
 */
public class App {
    public static void main(String[] args) {
        //Ŀ�����
        UserDao target = new UserDao();

        //������󣬰�Ŀ����󴫸�������󣬽��������ϵ
        UserDaoProxy proxy = new UserDaoProxy(target);
        //ִ�е��Ǵ���ķ���
        proxy.save();

    }
}