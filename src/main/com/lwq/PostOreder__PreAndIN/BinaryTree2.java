package main.com.lwq.PostOreder__PreAndIN;

/**
 * @Author: Lwq
 * @Date: 2018/8/29 11:15
 * @Version 1.0
 * @Describe
 */
public class BinaryTree2 {
    private Node root;
    public BinaryTree2(){
        root = null;
    }
    //后序遍历的递归实现
    public void postOrder() {
        this.postOrder(this.root);
    }

    private void postOrder(Node root) {
        if(root!=null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data+" ");
        }
    }


    public void initTreee(int[] pre, int[] in) {
        this.root = this.initTreee(pre,0,pre.length-1,in,0,in.length-1);
    }

    /*
    a)确定树的根节点。树根是当前树中所有元素在先序遍历中最先出现的元素，即先序遍历的第一个结点就是二叉树的根。

    b)求解树的子树。找到根在中序遍历的位置，位置左边是二叉树的左孩子，位置右边是二叉树的右子树，如果根节点左边或右边为空，那么该方向子树为空；如果根节点左边和右边都为空，那么根节点已经为叶子节点。

    c)对二叉树的左、右孩子分别进行步骤a)、b），直到求出二叉树结构为止。
     */
    private Node initTreee(int[] pre, int start1, int end1, int[] in, int start2, int end2) {
        if(start1>end1 ||start2>end2){
            return null;
        }
        //树的根节点
        int rootData = pre[start1];
        Node head = new Node(rootData);
        //找到根节点在中序遍历中的位置
        int rootIndex = findIndexInArray(in,rootData,start2,end2);
        //左子树的长度
        int offSet = rootIndex-start2-1;
        //构建左子树
        Node left = initTreee(pre,start1+1,start1+1+offSet,in,start2,rootIndex-1);
        //构建右子树
        Node right = initTreee(pre,start1+1+offSet+1,end1,in,rootIndex+1,end2);
        head.left=left;
        head.right=right;
        return head;
    }

    private int findIndexInArray(int[] in, int rootData, int start2, int end2) {
        for(int i = start2;i<=end2;i++){
            if(in[i]==rootData){
                return i;
            }
        }
        return -1;
    }
}
