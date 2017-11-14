package coding.test.leetcode;

public class MaxPathSum {
    public int maxPathSum(TreeNode root) {
    	if(root==null)
    		return 0;
    	int max_val = Integer.MIN_VALUE;
    	int max_left = Math.max(0, maxPathSum(root.left));
    	int max_right = Math.max(0, maxPathSum(root.right));
    	return Math.max(max_val, max_left + max_right + root.val);
    }
	public static void main(String[] args){
		
	}
}
