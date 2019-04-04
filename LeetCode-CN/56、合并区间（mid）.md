[博客园](http://www.cnblogs.com/grandyang/p/4370601.html)

### 56、合并区间
给出一个区间的集合，请合并所有重叠的区间。
```
示例 1:

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
```


```
/**
 * Definition for an interval.
 * struct Interval {
 *     int start;
 *     int end;
 *     Interval() : start(0), end(0) {}
 *     Interval(int s, int e) : start(s), end(e) {}
 * };
 */
class Solution {
public:
    static bool cmp(Interval a, Interval b){
        return a.start< b.start;
    }

    vector<Interval> merge(vector<Interval>& intervals) {
        sort(intervals.begin(), intervals.end(), cmp);
        if(intervals.size()==1) return intervals;
        int i=0;
        vector<Interval> res;
        while(i<intervals.size()){
            int start = intervals[i].start, end = intervals[i].end;
            int j=i+1;

            while(j<intervals.size() && intervals[j].start <= end ){
                if(end <= intervals[j].end)
                    end = intervals[j].end;
                j++;
            }

            struct Interval merge = Interval(start,end);
            res.push_back(merge);
            i=j;
        }

        return res;
    }
};
```
