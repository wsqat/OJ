package ThreadPool;

import java.util.concurrent.Callable;

public class TestCallable implements Callable<String> {

    private int id;
    public TestCallable(int id)
    {
        this.id = id;
    }

    /*
     * 任务会传递给ExecutorService的submit方法
     * 传递后该方法自动在一个线程上执行
     * **/

    @Override
    public String call() throws Exception{
        System.out.println("call方法已被调用!!!   " + Thread.currentThread().getName());

        StringBuilder sb = new StringBuilder("call()方法被自动调用,任务返回结果:");
        sb.append(id);
        sb.append("   ");
        sb.append(Thread.currentThread().getName());

        return sb.toString();
    }
}