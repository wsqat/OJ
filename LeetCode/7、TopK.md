## 题目描述

在处理大量数据的时候，有时候往往需要找出Top前几的数据，这时候如果直接对数据进行排序，在处理海量数据的时候往往就是不可行的了，而且在排序最好的时间复杂度为nlogn，当n远大于需要获取到的数据的时候，时间复杂度就显得过高。
使用最小堆或者最大堆可以很好地解决Top大问题或者Top小问题。


## 解题思路

Top大问题解决思路：使用一个固定大小的最小堆，当堆满后，每次添加数据的时候与堆顶元素比较，若小于堆顶元素，则舍弃，若大于堆顶元素，则删除堆顶元素，添加新增元素，对堆进行重新排序。
Top小问题解决思路：使用一个固定大小的最大堆，当堆满后，每次添加数据到时候与堆顶元素进行比较，若大于堆顶元素，则舍弃，若小于堆顶元素，则删除堆顶元素，添加新增元素，对堆进行重新排序。

对于n个数，取Top m个数，时间复杂度为O(nlogm)，这样在n较大情况下，是优于nlogn的时间复杂度的。

比如10000个数据，取前100大的数，那么时间复杂度就是O(10000log100)。
因为在插入数据的时候需要遍历元素时间复杂度达到了O(10000)，然后每次插入过程中进行调整的复杂度为O(log100)，所以总体时间复杂度为O(10000log100)。

## AC代码

### 使用Java类库集合实现

下面就使用PriorityQueue来实现最小堆和最大堆。

Java集合中的PriorityQueue就可以实现最大堆或者最小堆，从名字可以知道该集合是优先队列，数据结构中的优先队列就是使用堆来实现的。

```
// 底层通过一个Object类型数据保存元素
transient Object[] queue;

// 通过Comparator制定比较方法
private final Comparator<? super E> comparator;


// 其中一个构造函数
public PriorityQueue(int initialCapacity,
                     Comparator<? super E> comparator) {
    // Note: This restriction of at least one is not actually needed,
    // but continues for 1.5 compatibility
    if (initialCapacity < 1)
        throw new IllegalArgumentException();
    this.queue = new Object[initialCapacity];
    this.comparator = comparator;
}
```


在构造PriorityQueue的时候需要传入一个size和一个比较函数，制定堆中元素比较规则。
重写compare(o1, o2)方法，最小堆使用o1 - o2，最大堆使用o2 - o1。

```
public class TopK<E extends Comparable> {
    private PriorityQueue<E> queue;
    private int maxSize; //堆的最大容量

    public TopK(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalStateException();
        }
        this.maxSize = maxSize;
        this.queue = new PriorityQueue<>(maxSize, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                // 最大堆用o2 - o1，最小堆用o1 - o2
                return (o1.compareTo(o2));
            }
        });
    }

    public void add(E e) {
        if (queue.size() < maxSize) {
            queue.add(e);
        } else {
            E peek = queue.peek();
            if (e.compareTo(peek) > 0) {
                queue.poll();
                queue.add(e);
            }
        }
    }

    public List<E> sortedList() {
        List<E> list = new ArrayList<>(queue);
        Collections.sort(list);
        return list;
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 1, 6, 2, 7, 3, 8};
        TopK pq = new TopK(4);
        for (int n : array) {
            pq.add(n);
        }
        System.out.println(pq.sortedList());
    }
}
```

### 使用Java实现
通过上述讲述，基本了解最大堆和最小堆情况以及它们与TopK问题的关系，上面是使用集合实现，下面使用Java来实现最小堆，并解决TopK大问题。

限定数据大小。
若堆满，则插入过程中与堆顶元素比较，并做相应操作。
每次删除堆顶元素后堆做一次调整，保证最小堆特性。

```
package leetcode;

public class TopK {
    int[] items;
    int currentSize = 0;

    // 初始化为size + 1，从下标1开始保存元素。
    public TopK(int size) {
        items = new int[size + 1];
    }

    // 插入元素
    public void insert(int x) {
        if (currentSize == items.length - 1) {
            if (compare(x, items[1]) < 0) {
                return;
            } else if (compare(x, items[1]) > 0) {
                deleteMin();
            }
        }

        int hole = ++currentSize;
        for (items[0] = x; compare(x, items[hole / 2]) < 0; hole /= 2) {
            items[hole] = items[hole / 2];
        }
        items[hole] = x;
    }

    // 删除最小堆中最小元素
    public int deleteMin() {
        int min = items[1];
        items[1] = items[currentSize--];
        percolateDown(1);
        return min;
    }

    // 下滤
    public void percolateDown(int hole) {
        int child;
        int temp = items[1];

        for (; hole * 2 <= currentSize; hole = child) {
            child = 2 * hole;
            if (child != currentSize && compare(items[child + 1], items[child]) == -1) {
                child++;
            }
            if (compare(items[child], temp) < 0) {
                items[hole] = items[child];
            } else {
                break;
            }
        }
        items[hole] = temp;
    }

    // 制定比较规则
    public static int compare(int a, int b) {
        if (a < b) {
            return -1;
        } else if (a > b) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        TopK topK = new TopK(10);
        for (int i = 1; i <= 100; i++) {
            topK.insert(i);
        }
        for (int j = 1; j <= topK.currentSize; j++) {
            System.out.print(topK.items[j] + " ");
        }
        System.out.println();
    }
}
```


### 思路
先取出前100个数，维护一个100个数的最小堆，遍历一遍剩余的元素，在此过程中维护堆就可以了。具体步骤如下： step1：取前m个元素（例如m=100），建立一个小顶堆。保持一个小顶堆得性质的步骤，运行时间为O（lgm);建立一个小顶堆运行时间为m*O（lgm）=O(m lgm); step2:顺序读取后续元素，直到结束。每次读取一个元素，如果该元素比堆顶元素小，直接丢弃 如果大于堆顶元素，则用该元素替换堆顶元素，然后保持最小堆性质。最坏情况是每次都需要替换掉堆顶的最小元素，因此需要维护堆的代价为(N-m)*O(lgm); 最后这个堆中的元素就是前最大的10W个。时间复杂度为O(N lgm）。

```
public class Max100 {


    // p 的左右子节点已经为小根堆，调节 p 节点也为小根堆
    public static void adjust(long a[],int n,int p){

        long t=a[p];
        for(int j=2*p+1;j<n;j=2*p+1){
            if(j+1<n&&a[j+1]<a[j])
                j++;
            if(a[j]>t)
                break;
            a[p]=a[j];
            p=j;
        }
        a[p]=t;
    }


    public static void k100()throws Exception{

        Scanner scanner=new Scanner(new FileInputStream("input.txt"));
        long a[]=new long[100];
        for(int i=0;i<100;i++){
            a[i]=scanner.nextLong();
        }//先读取100个数据

        for(int i=100/2-1;i>=0;i--)//将前100个数据建立一个 大小为100 的小根堆
            adjust(a,100,i);

        while (scanner.hasNextLong()){//读取接下来的数据
            long k=scanner.nextLong();
            if(k<a[0])//如果比现在最小的数据还小，直接忽略
                continue;
            a[0]=k;//直接替换掉最小的数据
            adjust(a,100,0);//因为引入了大的数据，需要重新调整为一个小根堆
        }

        //最后的堆中保留的就是 前 100 大的数

        //将一个小根堆进行排序,用堆排序思想
        for(int i=100-1;i>0;i--){
            long t=a[0];
            a[0]=a[i];
            a[i]=t;
            adjust(a,i,0);
        }

        for(int i=0;i<100;i++)
            System.out.println(a[i]);

        scanner.close();

    }



    public static void main(String[]args)throws Exception{
        //randomData();
        long start=System.currentTimeMillis();
        k100();
        long end=System.currentTimeMillis();
        System.out.println(end-start);//测试的电脑大概是1200 ms
    }

    public static void randomData()throws Exception{//随机100万数据

        File file=new File("input.txt");
        if(!file.exists())
            file.createNewFile();

        PrintStream printStream=new PrintStream(new FileOutputStream(file));

        Random random=new Random(System.currentTimeMillis());

        for(int i=0;i<1000000;i++){
            long k=Math.abs(random.nextLong());
            printStream.println(k);
        }
        printStream.close();
    }




}
```