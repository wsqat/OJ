package ThreadPool;

// Executor 执行 Callable 任务的示例代码

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExecutorTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 当将一个 Callable 的对象传递给 ExecutorService 的 submit 方法，则该 call 方法自动在一个线程上执行，并且会返回执行结果 Future 对象。
        // Future并发异步,可以不等待返回结果,需要时可以查看
        ArrayList<Future<String>> resultList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<String> future = executorService.submit(new TestCallable(i));
            resultList.add(future);
        }

        for (Future<String> fs : resultList) {
            try {
                while (fs.isDone())  // 如果没完成则循环等待
                    System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }
    }
}
