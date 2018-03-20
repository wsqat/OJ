package ThreadPool;

// �̳�Thread��
// �߳�ʵ��
class MyThread extends Thread{

    private String name;

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run(){
        Single s = Single.getSingle();
        System.out.println(s);
        System.out.println("name:"+name+" ���߳�ID:"+Thread.currentThread().getId());
    }
}

//����ģʽ��˫��У����
class Single{
    private Single(){};
    private static Single single = null;

    public static Single getSingle() {
        if (single==null){
            synchronized (Single.class){
                if (single == null){
                    single = new Single();
                }
            }
        }
        return single;
    }
}

//���̲߳������ʵ���ģʽ
//https://www.cnblogs.com/yjd_hycf_space/p/7526608.html
//http://www.importnew.com/21136.html
public class MultiThread{

    public static void main(String[] args){
        System.out.println("���߳�ID:"+Thread.currentThread().getId());
        MyThread thread1 = new MyThread("thread1");
        thread1.start();
        MyThread thread2 = new MyThread("thread2");
        thread2.run();
    }
}