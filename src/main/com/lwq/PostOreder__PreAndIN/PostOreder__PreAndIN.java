package main.com.lwq.PostOreder__PreAndIN;

import java.sql.SQLOutput;

/**
 * @Author: Lwq
 * @Date: 2018/8/29 11:13
 * @Version 1.0
 * @Describe 根据前序遍历和中序遍历求出后序遍历
 */
public class PostOreder__PreAndIN {
    public static void main(String[] args) {
        BinaryTree2  biTree = new BinaryTree2();
        int[] pre={1,2,4,8,9,5,10,3,6,7};
        int[] in={8,4,9,2,10,5,1,6,3,7};
        biTree.initTreee(pre,in);
        System.out.println("二叉树的后序遍历");
        biTree.postOrder();
    }
}
