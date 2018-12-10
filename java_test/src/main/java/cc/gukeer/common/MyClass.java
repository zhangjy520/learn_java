package cc.gukeer.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MyClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClass tree = new MyClass();
		int[] datas = new int[]{1,2,3,4,5,6,7,8,9};
		List<Node> nodelist = new LinkedList<Node>();
		tree.creatBinaryTree(datas, nodelist);
		Node root = nodelist.get(0);
		System.out.println("递归先序遍历：");
		tree.preOrderTraversal(root);
		System.out.println();
		System.out.println("非递归先序遍历：");
		tree.preOrderTraversalbyLoop(root);
		System.out.println();
		System.out.println("递归中序遍历：");
		tree.inOrderTraversal(root);
		System.out.println();
		System.out.println("非递归中序遍历：");
		tree.inOrderTraversalbyLoop(root);
		System.out.println();
		System.out.println("递归后序遍历：");
		tree.postOrderTraversal(root);
		System.out.println();
		System.out.println("非递归后序遍历：");
		tree.postOrderTraversalbyLoop(root);
		System.out.println();
		System.out.println("广度优先遍历：");
		tree.bfs(root);
		System.out.println();
		System.out.println("深度优先遍历：");
		List<List<Integer>> rst = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		tree.dfs(root,rst,list);
		System.out.println(rst);
	}
	/**
	 * 
	 * @param datas 实现二叉树各节点值的数组
	 * @param nodelist 二叉树list
	 */
	private void creatBinaryTree(int[] datas,List<Node> nodelist){
		//将数组变成node节点
		for(int nodeindex=0;nodeindex<datas.length;nodeindex++){
			Node node = new Node(datas[nodeindex]);
			nodelist.add(node);
		}
		//给所有父节点设定子节点
		for(int index=0;index<nodelist.size()/2-1;index++){
			//编号为n的节点他的左子节点编号为2*n 右子节点编号为2*n+1 但是因为list从0开始编号，所以还要+1
			//这里父节点有1（2,3）,2（4,5）,3（6,7）,4（8,9） 但是最后一个父节点有可能没有右子节点 需要单独处理
			nodelist.get(index).setLeft(nodelist.get(index*2+1)); 
			nodelist.get(index).setRight(nodelist.get(index*2+2));
		}
		//单独处理最后一个父节点  因为它有可能没有右子节点
		int index = nodelist.size()/2-1;
		nodelist.get(index).setLeft(nodelist.get(index*2+1)); //先设置左子节点
		if(nodelist.size() % 2 == 1){ //如果有奇数个节点，最后一个父节点才有右子节点
			nodelist.get(index).setRight(nodelist.get(index*2+2));
		}
	}
	/**
	 * 遍历当前节点的值
	 * @param nodelist
	 * @param node
	 */
	public void checkCurrentNode(Node node){
		System.out.print(node.getVar()+" ");
	}
	/**
	 * 先序遍历二叉树
	 * @param root 二叉树根节点
	 */
	public void preOrderTraversal(Node node){
		if (node == null)  //很重要，必须加上 当遇到叶子节点用来停止向下遍历
            return;  
		checkCurrentNode(node);
		preOrderTraversal(node.getLeft());
		preOrderTraversal(node.getRight());
	}
	/**
	 * 中序遍历二叉树
	 * @param root 根节点
	 */
	public void inOrderTraversal(Node node){
		if (node == null)  //很重要，必须加上
            return;  
		inOrderTraversal(node.getLeft());
		checkCurrentNode(node);
		inOrderTraversal(node.getRight());
	}
	/**
	 * 后序遍历二叉树
	 * @param root 根节点
	 */
	public void postOrderTraversal(Node node){
		if (node == null)  //很重要，必须加上
            return;  
		postOrderTraversal(node.getLeft());
		postOrderTraversal(node.getRight());
		checkCurrentNode(node);
	}
	
	/**
	 * 非递归前序遍历
	 * @param node
	 */
	public void preOrderTraversalbyLoop(Node node){
		Stack<Node> stack = new Stack();
		Node p = node;
		while(p!=null || !stack.isEmpty()){
			while(p!=null){ //当p不为空时，就读取p的值，并不断更新p为其左子节点，即不断读取左子节点
				checkCurrentNode(p);
				stack.push(p); //将p入栈
				p = p.getLeft();
			}
			if(!stack.isEmpty()){
				p = stack.pop();
				p = p.getRight();
			}
		}
	}
	/**
	 * 非递归中序遍历
	 * @param node
	 */
	public void inOrderTraversalbyLoop(Node node){
		Stack<Node> stack = new Stack();
		Node p = node;
		while(p!=null || !stack.isEmpty()){
			while(p!=null){
				stack.push(p);
				p = p.getLeft();
			}
			if(!stack.isEmpty()){	
				p = stack.pop();
				checkCurrentNode(p);
				p = p.getRight();
			}
		}
	}
	/**
	 * 非递归后序遍历
	 * @param node
	 */
	public void postOrderTraversalbyLoop(Node node){
		Stack<Node> stack = new Stack<>();
		Node p = node,prev = node;
		while(p!=null || !stack.isEmpty()){
			while(p!=null){
				stack.push(p);
				p = p.getLeft();
			}
			if(!stack.isEmpty()){
				Node temp = stack.peek().getRight();
				if(temp == null||temp == prev){
					p = stack.pop();
					checkCurrentNode(p);
					prev = p;
					p = null;
				}else{
					p = temp;
				}	
			}
		}
	}
	
	/**
	 * 广度优先遍历（从上到下遍历二叉树）
	 * @param root
	 */
	public void bfs(Node root){
		  if(root == null) return;
		  LinkedList<Node> queue = new LinkedList<Node>();
		  queue.offer(root); //首先将根节点存入队列
		  //当队列里有值时，每次取出队首的node打印，打印之后判断node是否有子节点，若有，则将子节点加入队列
		  while(queue.size() > 0){ 
			Node node = queue.peek();
		    queue.poll(); //取出队首元素并打印
		    System.out.print(node.var+" ");
		    if(node.left != null){ //如果有左子节点，则将其存入队列
		      queue.offer(node.left);
		    }
		    if(node.right != null){ //如果有右子节点，则将其存入队列
		      queue.offer(node.right);
		    }
		  }
	}
	/**
	 * 深度优先遍历
	 * @param node
	 * @param rst
	 * @param list
	 */
	public void dfs(Node node,List<List<Integer>> rst,List<Integer> list){
		if(node == null) return;
		if(node.left == null && node.right == null){
			list.add(node.var);
			/* 这里将list存入rst中时，不能直接将list存入，而是通过新建一个list来实现，
			 * 因为如果直接用list的话，后面remove的时候也会将其最后一个存的节点删掉*/
			rst.add(new ArrayList<>(list));
			list.remove(list.size()-1);
		}
		list.add(node.var);
		dfs(node.left,rst,list);
		dfs(node.right,rst,list);
		list.remove(list.size()-1);
	}
	/**
	 * 节点类
	 * var 节点值
	 * left 节点左子节点
	 * right 右子节点
	 */ 
	class Node{
		int var;
		Node left;
		Node right;
		public Node(int var){
			this.var = var;
			this.left = null;
			this.right = null;
		}
		public void setLeft(Node left) {
			this.left = left;
		}
		public void setRight(Node right) {
			this.right = right;
		}
		public int getVar() {
			return var;
		}
		public void setVar(int var) {
			this.var = var;
		}
		public Node getLeft() {
			return left;
		}
		public Node getRight() {
			return right;
		}
		
	}

}
