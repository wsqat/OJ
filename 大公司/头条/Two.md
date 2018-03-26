```

//题目描述：
//定义两个字符变量：s和m，再定义两种操作，
//第一种操作：
//m=s；
//s=s+s;
//第二种操作：
//s=s+m;
//假设初始化如下：
//s="a"; m=s;
//求最小的操作步骤数，可以将s拼接到长度等于n；

import java.util.Scanner;

public class Two {
    /*
     * 思路：采用dfs算法遍历每一种拼接到长度等于n的可能，输出最小的步骤数。
     * */
    static int minStep = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(GetMinStep(n));
    }
    public static int GetMinStep(int n) {
        dfs("a", "a", 0, n);
        return minStep;
    }
    public static void dfs(String s, String m, int step, int n) {
        if (s.length()>=n) {
            if (s.length()==n)
                minStep = (step<minStep? step: minStep);
        } else {
            dfs(s+s, s, step+1, n);
            dfs(s+m, m, step+1, n);
        }
    }
}
```