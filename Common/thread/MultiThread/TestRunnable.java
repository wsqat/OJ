package ThreadPool;

public class TestRunnable implements Runnable{
    private String name;

    public TestRunnable(String name){
        this.name = name;
    }

    @Override
    public void run(){
        System.out.println("name:"+name+" ×ÓÏß³ÌID:"+Thread.currentThread().getId());
    }
}
