> 输入

```
3 3 2
1
3
6
```
> 输出

8

## 代码

BFS

> c
```
#include <bits/stdc++.h>
using namespace std;
const int N = 1e5+1000;
typedef pair<int, int> pii;
bool vis[N];
int a[N];
int main()
{
    int n,k,h;
    scanf("%d%d%d",&n,&k,&h);
    for(int i=0;i<n;++i)
    {
        int t;
        scanf("%d",&t);
        a[t]=1;
    }
    queue<pii> q;
    q.push({0,0});
    int ans = 0;
    while(!q.empty())
    {
        pii p = q.front(); q.pop();
        if(p.second>k) break;
        ans = max(ans, p.first);
        for(int i=1; i<=h; ++i)
        {
            if(a[p.first + i]&&!vis[p.first+2*i])
            {
                vis[p.first+2*i]=true;
                q.push(make_pair(p.first+2*i, p.second+1));
            }
            if(p.first-2*i>0&&a[p.first-i]&&!vis[p.first-2*i])
            {
                vis[p.first-2*i]=true;
                q.push(make_pair(p.first-2*i, p.second+1));
            }
        }
    }
    printf("%d\n", ans);
    return 0;
}
 
```



> java

```
import java.util.*;
class Pair {
    int first;
    int second;
    Pair(int f,int s) {
        first = f;
        second = s;
    }
}

public class Five {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] numbers = sc.nextLine().split(" ");
        int N = Integer.valueOf(numbers[0]);
        int K = Integer.valueOf(numbers[1]);
        int H = Integer.valueOf(numbers[2]);
        if (N<1 || K<1|| H<1 || N>100000|| K>100000|| H>100){
            return;
        }
        Pair node = new Pair(0,0);
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(node);
        int ans = 0;

        int [] a = new int[1000];
        boolean [] vis = new boolean[1000];
        for(int i=0;i<N;i++){
            int input = (int)sc.nextInt();
//            if (input>=1&&input<=100000){
                a[i] = input;
//                vis[i] = false;
//            }
        }

        while (!queue.isEmpty()){
            node = queue.peek();// 查看首个元素
            System.out.println("node first: "+(node.first)+" second: "+(node.second));

            queue.poll();    // poll方法移除队列首个元素并返回，若队列为空，返回null
            if (node.second>K){
                break;
            }
            ans = Math.max(ans,node.first);

            for (int i = 1; i <= H ; ++i) {
                if ((a[node.first+i]!=0) && (!vis[node.first+2*i])){
                    vis[node.first+2*i] = true;
                    queue.offer(new Pair(node.first+2*i,node.second+1));        // offer方法向队列中添加元素，返回布尔值
                    System.out.println("1first: "+(node.first+2*i)+" second: "+(node.second+1));
                }

                if ((node.first-2*i>0) && (a[node.first-i]>0) && (!vis[node.first-2*i])){
                    vis[node.first-2*i] = true;
                    queue.offer(new Pair(node.first-2*i,node.second+1));
                    System.out.println("2first: "+(node.first-2*i)+" second: "+(node.second+1));
                }
            }

        }

        System.out.println(ans);
    }
}

```
