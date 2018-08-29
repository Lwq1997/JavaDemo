package main.com.lwq.kedaxunfei;

import java.util.Scanner;

/**
 * @Author: Lwq
 * @Date: 2018/8/29 11:44
 * @Version 1.0
 * @Describe
 */
/*

有n 个人排成了一行队列，每个人都有一个站立的方向：
面向左或面向右。由于这n 个人中每个人都很讨厌其他的人，
所以当两个人面对面站立时，他们会发生争吵，
然后其中一个人就会被踢出队列，谁被踢出队列都是有可能的。

我们用字符L 来表示一个面向左站立的人，用字符R 来表示一个面向右站立的人，
那么这个队列可以用一个字符串描述。比如RLLR 就表示一个四个人的队列，
其中第一个人和第二个人是面对面站立的。他们发生争吵后队列可能会变成LLR，
也可能变成RLR；若变成RLR，则第一个人与第二个人还会发生争吵，队列会进一步变成LR 或者RR。

若在某个时刻同时可能有很多的争吵会发生时，接下来只会发生其中的一个，且任意一个都是有可能发生的。

你想知道经过一系列的争吵后，这个队列最少会剩下多少人？

输入
第一行包含一个有字符L 和R 构成的字符串。
1 ≤字符串长度≤ 105
输出
输出队列中最少会剩下多少人。

样例输入
LRRLRL

样例输出
2

一种可能的变化情况是这样的:
LRRLRL -> LRLRL -> LRRL -> LRL -> LR
 */
/*
觉像是找规律题：因为最左的一个LL、最右的一个R永远不会被消去，所以我们需要做的就是最后一个L的位置减去第一个R的位置就是消去的人数。
 */
public class Demo01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String queue = sc.next();
        int lCount = 0,rCount = queue.length()-1,count = 0;
        for(int i = 0;i < queue.length();i++){
            if(queue.charAt(i)=='R'){
                break;
            }
            lCount++;
        }
        for(int j = queue.length()-1;j>=0;j--){
            if(queue.charAt(j)=='L'){
                break;
            }
            rCount--;
        }
        count = queue.length()-(rCount-lCount);
        if (count > queue.length())
            count = queue.length();
        System.out.println(count);
    }
}
