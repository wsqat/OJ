package ThreadPool;

// ʵ��Runnable�ӿ�
// ʵ��Callable�ӿ�
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MultiThreadTest{

    public static void main(String[] args){
//        System.out.println("���߳�ID:"+Thread.currentThread().getId());
//        TestRunnable thread1 = new TestRunnable("thread1");
//        thread1.run();
//        TestRunnable thread2 = new TestRunnable("thread2");
//        thread2.run();

        /*
         * һ������ִ���̵߳ķ�ʽ����ʵ�� Callable �ӿڡ� �����ʵ�� Runnable �ӿڵķ�ʽ�����������з���ֵ�����ҿ����׳��쳣��
         *
         * ����ִ�� Callable ��ʽ����Ҫ FutureTask ʵ�����֧�֣����ڽ�����������  FutureTask ��  Future �ӿڵ�ʵ����
         */

        CallableDemo td = new CallableDemo();

        //1.ִ�� Callable ��ʽ����Ҫ FutureTask ʵ�����֧�֣����ڽ�����������
        FutureTask<Integer> result = new FutureTask<>(td);

        new Thread(result).start();

        //2.�����߳������Ľ��
        try {
            Integer sum = result.get();  //FutureTask ������ ���� ������CountDownLatch�����ã������е��߳�û��ִ�����֮�������ǲ���ִ�е�
            System.out.println(sum);
            System.out.println("------------------------------------");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 100000; i++) {
            sum += i;
        }

        return sum;
    }
}
