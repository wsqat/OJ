package pd.SingleTest;
//���ģʽ��
//����ģʽ
//����ģʽ
public class Singleton {
    private Singleton(){}

    private static final Singleton single = new Singleton();

    public static Singleton GetInstance(){
        return single ;
    }
}


//����ģʽ���̲߳���ȫ
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

//�̰߳�ȫ��˫��У����
//public class Singleton {
//    //�ؼ���0�����캯����˽�е�
//    private Singleton(){}
//    //�ؼ���1���������������Ǿ�̬��
//    private volatile static  Singleton single = null;
//    //ͨ����̬�����๹�����
//    public static Singleton GetInstance(){
//        //�ؼ���2���жϵ��������Ƿ��Ѿ�������
//        if (single==null){
//            //�ؼ���3�����߳��� lock.lock(obj);
//            synchronized(Singleton.class) {
//                //�ؼ���4�������жϵ����Ƿ��Ѿ�������
//                if (single == null){
//                    single = new Singleton();
//                }
//            }
//        }
//        return single;
//    }
//}