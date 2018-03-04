## 题目描述
定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。

## 解题思路

本题借助辅助栈，以及一个变量用来记录最小元素，当把元素压入栈时，比较元素与最小元素间的关系，若大于最小元素，则将最小元素压入辅助栈，此元素压入数据栈，若元素小于最小元素，则将此元素压入数据栈和辅助栈。代码如下

## AC代码

```

import java.util.Stack;

public class Solution {

    Stack<Integer> st1 = new Stack<Integer>();//数据栈
    Stack<Integer> st2 = new Stack<Integer>();//辅助栈
    Integer temp = null;
    public void push(int node) {
        if (temp!=null){
            if (node<=temp){
                temp = node;
                st1.push(node);
                st2.push(node);
            }else{
                st1.push(node);
                st2.push(temp);
            }
        }else{
            temp = node;
            st1.push(node);
            st2.push(node);
        }
    }
    
    public void pop() {
        if (!st1.empty()&&!st2.empty()){
            int m = st1.pop();
            st2.pop();
            temp = m;
        }
    }
    
    public int top() {
        int top = st1.pop();
        st1.push(top);
        return top;
    }
    
    public int min() {
        int min = st2.pop();
        st2.push(min);
        return min;
    }
}
```