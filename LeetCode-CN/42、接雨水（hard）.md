
[博客园](http://www.cnblogs.com/grandyang/p/4402392.html)
### 题目描述
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

![image](http://upload-images.jianshu.io/upload_images/688387-8c977b4c8ec38812.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
```
示例:

输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
```

### 解题思路

这道收集雨水的题跟之前的那道 Largest Rectangle in Histogram 直方图中最大的矩形 有些类似，但是又不太一样，我们先来看一种方法，这种方法是基于动态规划Dynamic Programming的，我们维护一个一维的dp数组，这个DP算法需要遍历两遍数组，第一遍遍历dp[i]中存入i位置左边的最大值，然后开始第二遍遍历数组，第二次遍历时找右边最大值，然后和左边最大值比较取其中的较小值，然后跟当前值A[i]相比，如果大于当前值，则将差值存入结果，代码如下：

### 代码


```
class Solution {
public:
    int trap(vector<int>& height) {
        int n = height.size(), res=0;
        vector<int> dp(n, 0);
        int mx=0;
        for(int i=0;i<n;i++){
            dp[i] = mx;
            mx = max(height[i],mx);
        }
        mx=0;

        for(int i=n-1; i>=0;i--){
            dp[i] = min(dp[i], mx);
            mx =  max(height[i], mx);
            if(dp[i]>height[i]) res+= dp[i]-height[i];
        }
        return res;
    }
};
```
