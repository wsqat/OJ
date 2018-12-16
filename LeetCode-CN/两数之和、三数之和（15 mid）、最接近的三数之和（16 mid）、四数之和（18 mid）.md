## 两数之和、三数之和（15 mid）、最接近的三数之和（16 mid）、四数之和（18 mid）

### 两数之和
题目链接：两数之和

给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。

你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9 
所以返回 [0, 1] 
### 思路
最容易想到的方法是用一个双重循环来枚举数组中两两组合的情况，然后判断和是否为 target ，时间复杂度是 O(n^2)。 
我们还可以先对数组元素从小到大升序排序，然后在一个循环中利用头尾指针扫描排序后的数组，每次扫描比较两个数的和和 target 的值。因为需要得到元素的排序前下标，所以用一个结构体数组来保存数组元素的值和未排序之前元素所在下标，这样的话采用快速排序，时间复杂度为 O(n*logn)，空间复杂度为 O(n)。

### 代码
```
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        struct Node {
            // 保存数组中每个数的值和排序前所在下标
            int index, val;
            bool operator <(Node &n) {
                return val < n.val;
            }
        };

        vector<int> res;
        int size = nums.size();
        Node nodes[size];
        for (int i = 0; i < size; i++) {
            nodes[i].val = nums[i], nodes[i].index = i;
        }
        sort(nodes, nodes + size);
        int start = 0, end = size - 1, t;
        while (start < end) {
            t = nodes[start].val + nodes[end].val;
            // 等于
            if (t == target) {
                res.push_back(nodes[start].index);
                res.push_back(nodes[end].index);
                return res;
            // 小于， start 指针后移
            } else if (t < target) {
                start++;
            // 大于，end 指针前移
            } else {
                end--;
            }
        }
        return res;
    }
};
```


### 三数之和
题目链接：三数之和

给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：

[
  [-1, 0, 1],
  [-1, -1, 2]
]

### 思路

先对数组进行从小到大升序排序，之后用一个循环（循环范围 0~size-3）先确定一个数，之后设立双指针头尾同时扫描数组右边剩下的数，如果找到两个数和为外层循环中以确定的相反数，那么存入解，并且去除 start 和 end 重复。 
对于外层循环，每一次需要去除从下标 i 开始的数字重复，一是为了去除重复答案。二是可以提高运行效率。

### 代码
```
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        int n=nums.size();
        vector<vector<int>> res;
        if(n==0 || nums.empty()) return res;
        sort(nums.begin(), nums.end());
        if(nums.front()>0 || nums.back()<0) return res;

        
        for(int i=0; i<n-2; i++){
            int target = -nums[i];
            int start=i+1, end=n-1;
            while(start<end){
                if(nums[start]+nums[end]==target){
                    res.push_back({nums[i], nums[start], nums[end]});
                    start++;
                    end--;
                    //start去重
                    while(start<end && nums[start]==nums[start-1])
                        start++;
                    //end去重
                    while(start<end && nums[end]==nums[end+1])
                        end--;
                    
                }else if(nums[start]+nums[end]<target){
                    start++;
                }else if(nums[start]+nums[end]>target){
                    end--;
                }
            }
            
            //i去重
            while(i<n-2 && nums[i]==nums[i+1])
                i++;
        }
        
        return res;
    }
};
```


```
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> res;
        vector<int> save;
        sort(nums.begin(), nums.end());
        int size = nums.size();
        int start, end, curSum, t;
        // 外层循环先确定一个数，之后双指针扫描数组右边剩下的数，找到两个和为已确定数的相反数的两个数
        for (int i = 0; i < size - 2; i++) {
            start = i + 1, end = size - 1;
            curSum = 0 - nums[i];
            // 双指针扫描是否存在两个数等于 -nums[i] 
            while (start < end) {
                t = nums[start] + nums[end];
                // 找到一个解
                if (t == curSum) {
                    save.clear();
                    save.push_back(nums[i]);
                    save.push_back(nums[start]);
                    save.push_back(nums[end]);
                    res.push_back(save);
                    start++;
                    end--;
                    // 去 start 重复
                    while (start < end && nums[start] == nums[start-1]) {
                        start++;
                    }
                    // 去 end 重复
                    while (start < end && nums[end] == nums[end+1]) {
                        end--;
                    }
                } else if (t < curSum) {
                    start++;
                } else {
                    end--;
                }
            }
            // 去 i 重复
            while (i < size - 2 && nums[i] == nums[i+1]) {
                i++;
            }
        }
        return res;
    }
};
```

### 最接近的三数之和
题目链接： 最接近的三数之和

给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

### 思路
想法和上题类似，对于每个外层循环确定的数，内层循环双指针扫描数组右边剩下的数，每次得到的 3 个数都拿来更新一次结果

### 代码
```
class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        int n=nums.size();
        if(n==0 || nums.empty()) return 0;
        sort(nums.begin(), nums.end());
        // if(nums.front()>0 || nums.back()<0) return res;
        int min=INT_MAX,sum=nums[0];
        for(int i=0; i<n-2; i++){
            int start=i+1, end=n-1;
            while(start<end){
                int tmp =nums[start]+nums[end]+nums[i];
                if(tmp==target){
                    sum = tmp;
                    min=0;
                    start++;
                    end--;
                }else if(tmp<target){
                    if (target - tmp < min){
                        min = target - tmp;
                        sum = tmp;    
                    }
                    start++;
                }else if(tmp>target){
                    if (tmp-target< min){
                        min = tmp-target;
                        sum = tmp;    
                    }
                    end--;
                }
            }
            
            //i去重
            while(i<n-2 && nums[i]==nums[i+1])
                i++;
        }
        
        return sum;
    }  
};
```

```
class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int size = nums.size();
        int start, end, res = nums[0], minn = 999999999, t;
        // 外层循环先确定一个数，之后双指针扫描数组右边剩下的数，对于每次找到的两个数，
        // 通过这 3 个数的和来更新结果
        for (int i = 0; i < size - 2; i++) {
            start = i + 1, end = size - 1;
            // 双指针扫描是否存在两个数等于 -nums[i] 
            while (start < end) {
                t = nums[start] + nums[end] + nums[i];
                // 当前 3 个数和等于 target
                if (t == target) {
                    res = t;
                    minn = 0;
                    start++;
                    end--;
                // 当前 3 个数和小于 target
                } else if (t < target) {
                    if (target - t < minn) {
                        minn = target - t;
                        res = t;
                    }
                    start++;
                // 当前 3 个数和大于于 target
                } else {
                    if (t - target < minn) {
                        minn = t - target;
                        res = t;
                    }
                    end--;
                }
            }
            // 去 i 重复，提高运行效率
            while (i < size - 2 && nums[i] == nums[i+1]) {
                i++;
            }
        }
        return res;
    }
};
```



### 四数之和
题目链接：四数之和

给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

注意：

答案中不可以包含重复的四元组。

示例：

给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：

[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

### 思路
首先，将数组从小到大排序，之后先使用双层循环范围为（0 ~ size-3）的循环确定较小的两个数，剩下的两个数通过设定两个指针头尾扫描右边循环没有遍历倒的数的，在找到一个解之后，因为数组中的数字可能有重复，需要去重，同样的对于外面的双层循环中，在每一次循环末尾也需要判断去重


### 代码
```
class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        int n=nums.size();
        vector<vector<int>> res;
        if(n==0 || nums.empty()) return res;
        sort(nums.begin(), nums.end());
        if(nums.front()>0 || nums.back()<0) return res;

        
        for(int i=0; i<n-3; i++){
            for(int j=i+1; j<n-2; j++){
                int start=j+1, end=n-1;
                while(start<end){
                    int t = nums[i]+nums[j]+nums[start]+nums[end];
                    
                    if(t==target){
                        res.push_back({nums[i], nums[j], nums[start], nums[end]});
                        start++;
                        end--;
                        //start去重
                        while(start<end && nums[start]==nums[start-1])
                            start++;
                        //end去重
                        while(start<end && nums[end]==nums[end+1])
                            end--;

                    }else if(t<target){
                        start++;
                    }else if(t>target){
                        end--;
                    }
                }

                //j去重
                while(j<n-2 && nums[j]==nums[j+1])
                    j++;
            }
            //i去重
            while(i<n-3 && nums[i]==nums[i+1])
                i++;
        }
        
        return res;
    }
};
```

```
class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        vector<vector<int>> res;
        // 从小到大对数组进行排序
        sort(nums.begin(), nums.end());
        int size = nums.size();
        int curSum;
        int start, end;
        vector<int> save;
        // 留有最少两个最右边的数用于双指针扫描
        for (int i = 0; i < size - 3; i++) {
            for (int j = i + 1; j < size - 2; j++) {
                curSum = nums[i] + nums[j];
                start = j + 1, end = size - 1;
                // 双指针扫描
                while (start < end) {
                    // 找到一个解
                    if (nums[start] + nums[end] == target - curSum) {
                        save.clear();
                        save.push_back(nums[i]);
                        save.push_back(nums[j]);
                        save.push_back(nums[start]);
                        save.push_back(nums[end]);
                        res.push_back(save);
                        start++;
                        end--;
                        // 去 start 重复
                        while (start < end && nums[start] == nums[start-1]) {
                            start++;
                        }
                        // 去 end 重复
                        while (start < end && nums[end] == nums[end+1]) {
                            end--;
                        }
                    // 四数之和大于 target ，end 指针减一
                    } else if (nums[start] + nums[end] > target - curSum) {
                        end--;
                    // 四数之和小于 target ，start 指针加一
                    } else {
                        start++;
                    }
                }
                // 去 j 重复
                while (j < size - 2 && nums[j] == nums[j+1]) {
                    j++;
                }
            }
            // 去 i 重复
            while (i < size - 3 && nums[i] == nums[i+1]) {
                i++;
            }
        }
        return res;
    }
};
```