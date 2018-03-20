package ThreadPool;

import java.util.concurrent.Callable;

public class TestCallable implements Callable<String> {

    private int id;
    public TestCallable(int id)
    {
        this.id = id;
    }

    /*
     * ����ᴫ�ݸ�ExecutorService��submit����
     * ���ݺ�÷����Զ���һ���߳���ִ��
     * **/

    @Override
    public String call() throws Exception{
        System.out.println("call�����ѱ�����!!!   " + Thread.currentThread().getName());

        StringBuilder sb = new StringBuilder("call()�������Զ�����,���񷵻ؽ��:");
        sb.append(id);
        sb.append("   ");
        sb.append(Thread.currentThread().getName());

        return sb.toString();
    }
}