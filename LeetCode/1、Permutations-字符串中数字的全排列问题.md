## 题目描述
Given a collection of numbers, return all possible permutations.

For example,
[1,2,3]have the following permutations:
[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2], and[3,2,1].

给定一个无重复数字的序列，返回这些数所能排列出所有序列。

样例输入：
```
[1,2,3]
```

样例输出：

```
[   
    [1,2,3],
    [1,3,2],
    [2,1,3],
    [2,3,1],
    [3,1,2],
    [3,2,1]
]
```

## 解题思路

思路一：置换   

复杂度：时间 O(N^2) 空间 O(N) 递归栈     

置换实际上是给出所有的排列方式，同样是用深度优先搜索，不过为了避免重复选择的情况，我们要保证两点：第一，所有数必须是数组中的，第二，数组中每个数只能用不多于也不少于一次。如果我们要单独写一个函数，来判断下一轮搜索该选择哪一个数就很麻烦了。这里有一个技巧，我们可以只将数两两交换，不过交换时只能跟自己后面的交换。


![](https://www.programcreek.com/wp-content/uploads/2013/02/leetcode-permutations-java-400x303.jpg)

思路二：使用DFS策略

复杂度：时间 O(N) 空间 O(N) 递归栈

我们还可以简单的使用深度优先搜索来解决这题。每一轮搜索选择一个数加入列表中，同时我们还要维护一个全局的布尔数组，来标记哪些元素已经被加入列表了，这样在下一轮搜索中要跳过这些元素。


## AC代码

> 思路一：递归解决

```
import java.util.ArrayList;
public class Solution {

    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    
    public ArrayList<ArrayList<Integer>> permute(int[] nums) {
        int len = nums.length;
        if (len==0||nums==null)  return result;

        // 采用前后元素交换的办法，dfs解题
        exchange(nums, 0, len);
        return result;
    }
    
    public void exchange(int[] nums, int i, int len){
        // 将当前数组加到结果集中
        if (i == len-1){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j=0; j<len; j++){
                list.add(nums[j]);
            }
            result.add(list);
        }
        // 将当前位置的数跟后面的数交换，并搜索解
        for (int j=i; j<len ;j++){
            swap(nums,i,j);
            exchange(nums,i+1,len);
            swap(nums,i,j);
        }
    }
    
    public void swap(int[] nums , int i ,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

> 思路二：使用DFS策略

```
import java.util.ArrayList;
public class Solution {
    
    ArrayList<ArrayList<Integer>> res;
    boolean[] used;
    
    public ArrayList<ArrayList<Integer>> permute(int[] nums) {
        res = new ArrayList<ArrayList<Integer>>();
        used = new boolean[nums.length];
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        helper(nums, tmp);
        return res;
    }
    
    private void helper(int[] nums, ArrayList<Integer> tmp){
        if(tmp.size() == nums.length){
            ArrayList<Integer> list = new ArrayList<Integer>(tmp);
            res.add(list);
        } else {
            for(int idx = 0; idx < nums.length; idx++){
                // 遇到已经加过的元素就跳过
                if(used[idx]){
                    continue;
                }
                // 加入该元素后继续搜索
                used[idx] = true;
                tmp.add(nums[idx]);
                helper(nums, tmp);
                tmp.remove(tmp.size()-1);
                used[idx] = false;
            }
        }
    }
}
```


```
package leetcode;

import java.util.Arrays;

public class QuanPaiLie {
    public static  void main(String[] args){
        int [] array = {1,2,3,1};
        int len = array.length;
        fullSort(array,0,len-1);
//        System.out.println(array.length);
    }

    public static void fullSort(int [] numbers, int start, int end){
        if (start==end){
            System.out.println(Arrays.toString(numbers));

        }
        for (int i = start; i <= end; i++) {

            if (!isSwaped(numbers,start,i)){
                continue;
            }

            swap(numbers,i,start);
            fullSort(numbers,start+1,end);
            swap(numbers,i,start);//恢复数组，使得下一次进入循环时数组的排列一样
        }
    }

    public static void swap(int [] numbers, int i, int j){
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    //是否需要进行交换，有重复的就不进行交换，直接下一次循环
    public static boolean isSwaped(int [] numbers, int start, int end){
        for (int k = start; k < end; k++) {
            if (numbers[k]==numbers[end]){
                return false;
            }
        }
        return true;
    }

}

```