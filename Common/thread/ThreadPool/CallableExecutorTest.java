package ThreadPool;

// Executor ִ�� Callable �����ʾ������

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExecutorTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // ����һ�� Callable �Ķ��󴫵ݸ� ExecutorService �� submit ��������� call �����Զ���һ���߳���ִ�У����һ᷵��ִ�н�� Future ����
        // Future�����첽,���Բ��ȴ����ؽ��,��Ҫʱ���Բ鿴
        ArrayList<Future<String>> resultList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<String> future = executorService.submit(new TestCallable(i));
            resultList.add(future);
        }

        for (Future<String> fs : resultList) {
            try {
                while (fs.isDone())  // ���û�����ѭ���ȴ�
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
