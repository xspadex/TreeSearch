import java.util.*;

class Node {
    int val;
    Node left;
    Node right;
    Node(int x) { val = x; }
}


public class TreeSearch{

    //基础遍历
    public List<Integer> dfsByStackX(Node node){
        List<Integer> res = new ArrayList<>();
        Stack<Node> stk = new Stack<>();

        if(node==null) return res;
        stk.push(node);

        while(!stk.isEmpty()){
            Node cur = stk.pop();
            if(cur==null) continue;
            res.add(cur.val);
            stk.push(cur.right);
            stk.push(cur.left);
        }

        return res;
    }

    public List<Integer> dfsByStackH(Node node){
        Deque<Integer> res = new LinkedList<>();
        Stack<Node> stk = new Stack<>();

        if(node==null) return (List<Integer>) res;
        stk.push(node);

        while(!stk.isEmpty()){
            Node cur = stk.pop();
            res.addFirst(cur.val);
            if(cur.left!=null) stk.push(cur.left);
            if(cur.right!=null) stk.push(cur.right);
        }

        return (List<Integer>) res;

    }

    public List<Integer> dfsByStackZ(Node node){
        Deque<Integer> res = new LinkedList<>();
        Stack<Node> stk = new Stack<>();
        Stack<Boolean> tag = new Stack<>();

        if(node==null) return (List<Integer>) res;
        stk.push(node);
        tag.push(true);

        while(!stk.isEmpty()){
            Node cur = stk.pop();
            Boolean left = tag.pop();
            if(left) res.addFirst(cur.val);
            else res.addLast(cur.val);
            if(cur.right!=null){
                stk.push(cur.right);
                tag.push(false);
            }
            if(cur.left!=null){
                stk.push(cur.left);
                tag.push(true);
            }
        }

        return (List<Integer>) res;

    }

    public List<Integer> bfsByQueue(Node node){
        Queue<Node> layer = new LinkedList<>();
        List<Integer> res = new ArrayList<>();

        layer.add(node);

        while(!layer.isEmpty()){
            Node cur = layer.poll();
            res.add(cur.val);
            if(cur.left!=null) layer.add(cur.left);
            if(cur.right!=null) layer.add(cur.right);
        }

        return res;
    }

    public List<Integer> dfsByRecurX(Node node){
        List<Integer> res = new ArrayList<>();

        if(node==null) return res;

        dfsXHelper(node,res);

        return res;
    }

    public void dfsXHelper(Node node, List<Integer> res){
        if(node==null) return;

        res.add(node.val);

        dfsXHelper(node.left,res);
        dfsXHelper(node.right,res);
    }

    public List<Integer> dfsByRecurH(Node node){
        List<Integer> res = new ArrayList<>();

        if(node==null) return res;

        dfsHHelper(node,res);

        return res;
    }

    public void dfsHHelper(Node node, List<Integer> res){
        if(node==null) return;

        dfsHHelper(node.left,res);

        dfsHHelper(node.right,res);

        res.add(node.val);
    }

    public List<Integer> dfsByRecurZ(Node node){
        List<Integer> res = new ArrayList<>();

        if(node==null) return res;

        dfsZHelper(node,res);

        return res;
    }

    public void dfsZHelper(Node node, List<Integer> res){
        if(node==null) return;

        dfsZHelper(node.left,res);

        res.add(node.val);

        dfsZHelper(node.right,res);
    }

    //判断
    public int depthOfTree(Node node){
        if(node==null) return 0;
        return Math.max(depthOfTree(node.left),depthOfTree(node.right))+1;
    }

    public Boolean isSymmetricTree(Node node){
        return node==null?true:isSymmetricTreeHelper(node.left,node.right);
    }

    public Boolean isSymmetricTreeHelper(Node left,Node right){
        if(left==null && right==null) return true;
        if(left==null || right==null) return false;
        return left.val==right.val
                && isSymmetricTreeHelper(left.left,right.right)
                && isSymmetricTreeHelper(left.right,right.left);
    }


    public static void main(String[] args) {
        TreeSearch solution = new TreeSearch();

        Node tree = new Node(0);
        tree.left=new Node(1);
        tree.right=new Node(2);
        tree.left.left=new Node(3);

        System.out.println("先序递归----------");
        System.out.println(solution.dfsByRecurX(tree));
        System.out.println("后序递归----------");
        System.out.println(solution.dfsByRecurH(tree));
        System.out.println("中序递归----------");
        System.out.println(solution.dfsByRecurZ(tree));
        System.out.println("先序栈----------");
        System.out.println(solution.dfsByStackX(tree));
        System.out.println("后序栈----------");
        System.out.println(solution.dfsByStackH(tree));
        System.out.println("中序栈----------");
        System.out.println(solution.dfsByStackZ(tree));
        System.out.println("广度优先队列----------");
        System.out.println(solution.bfsByQueue(tree));
        System.out.println("树的高度----------");
        System.out.println(solution.depthOfTree(tree));
        System.out.println("是否为对称树----------");
        System.out.println(solution.isSymmetricTree(tree));


    }
}
