import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Tree{
		char root;
		Tree lc;
		Tree rc;
		
//		public Tree(char root, Tree lc, Tree rc) {
//			this.root = root;
//			this.lc = lc;
//			this.rc = rc;
//		}
		
		public Tree(char root) {
			this.root = root;
			this.lc = null;
			this.rc = null;
		}
	}
	static Tree dfs(int start, int end, Queue preorderq, String inorder) {
		// end node
		if(start == end-1) {
			preorderq.remove();
			Tree leaf = new Tree(inorder.charAt(start));
			return leaf;
		}
		
		if(start == end) {
			return null;
		}
		
		// start~end 범위에서 root 찾기
		char root = (char) preorderq.remove();
		int rootIdx = -1;
		for(int i=start; i<end; i++) {
			if(inorder.charAt(i) == root) {
				rootIdx = i;
				break;
			}
		}
		
		
		Tree tree = new Tree(root);
		tree.lc = dfs(start, rootIdx, preorderq, inorder);
		tree.rc = dfs(rootIdx+1, end, preorderq, inorder);
		
		return tree;
	}
	public static void printTree(Tree tree) {
		if(tree == null) return;
		// 왼
		printTree(tree.lc);
		// 오
		printTree(tree.rc);
		// root
		System.out.print(tree.root);
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			while(true) {
				String inputStr = br.readLine();
//				System.out.println("inputStr : "+inputStr);
				if(inputStr.split(" ").length == 0)break;
				
				String[] input = inputStr.split(" ");
				String preorder = input[0];
				String inorder = input[1];
				int n = preorder.length();
				
				Queue<Character> preorderQ = new LinkedList<>();
				for(int i=0; i<n; i++) {
					preorderQ.add(preorder.charAt(i));
				}
				
				Tree tree = dfs(0, n, preorderQ, inorder);
				
				printTree(tree);
				System.out.println();
			}
		} catch(Exception e) {
			
		}
	}
}
