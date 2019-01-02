##  全排列I（46 mid） 、II(47 mid)

### 全排列I - 有重复
### 题目
给定一个没有重复数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:

[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]


### 思路

1、以前写过全排列的问题，类似于栈的思想。这里采用递归的思想。

2、思路：采用深度优先搜索的方法来做，设置一个int型的0，1索引数组，来表示这个数是否之前已经出现过。

3、dfs（8ms）比递归（16ms）快一倍。

### 代码
```
class Solution {
public:
    
    //方法一 递归
    vector<vector<int>> permute(vector<int>& nums) {
         vector<vector<int>> res;
         permutationrecusive(res, 0, nums);
         return res;
    }
    
    void permutationrecusive(vector<vector<int>> & res, int k, vector<int> & nums){
        if(k==nums.size()){
            res.push_back(nums);
        }
        
        for(int i=k; i< nums.size(); i++){
            swap(nums[i], nums[k]);
            permutationrecusive(res, k+1, nums);
            swap(nums[k], nums[i]);
        }
        
    }
    
    //方法二 dfs
    vector<vector<int>> permute(vector<int>& nums) {
        vector<vector<int>> res;
        vector<int> temp;
        vector<bool> isVisited(nums.size(), false);
        dfs(res, temp, nums, isVisited);
        return res;
    }
    
    void dfs(vector<vector<int>> & res, vector<int> &temp, vector<int> &nums, vector<bool> &isVisited){
        if(temp.size() == nums.size()){
            res.push_back(temp);
        }
        
        for(int i=0; i<nums.size(); i++){
            if(isVisited[i]) continue;
            temp.push_back(nums[i]);
            isVisited[i]=true;
            dfs(res, temp, nums, isVisited);
            temp.pop_back();
            isVisited[i]=false;
        }
    }
    
};
```



### 全排列II - 无重复

### 题目
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

### 思路
这道题合着46题一起看，与46题相比多了去重。不过，46题中对数组进行了排序，因此去重比较简单。

### 代码
```
class Solution {
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        vector<vector<int>> res;
        vector<int> temp;
        vector<bool> isVisited(nums.size(), false);
        sort(nums.begin(), nums.end());
        dfs(res, temp, nums, isVisited);
        return res;
    }
    
    void dfs(vector<vector<int>> & res, vector<int> &temp, vector<int> &nums, vector<bool> &isVisited){
        if(temp.size() == nums.size()){
            res.push_back(temp);
            return;
        }
        
        for(int i=0; i<nums.size(); i++){
            if(isVisited[i]) continue;
            if(i>0 && nums[i]==nums[i-1] && !isVisited[i-1]) continue;
            temp.push_back(nums[i]);
            isVisited[i]=true;
            dfs(res, temp, nums, isVisited);
            temp.pop_back();
            isVisited[i]=false;
        }
    }
};
```


