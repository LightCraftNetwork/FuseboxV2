package mc.lightcraft.java.local.dataStorage;

public class TreeNode {
	private Object value;
	private TreeNode left, right;

	/**
	 * Instantiate a new TreeNode
	 * 
	 * @param initValue
	 *            the value that this node is worth
	 */
	public TreeNode(Object initValue) {
		value = initValue;
		left = null;
		right = null;
	}

	/**
	 * Instantiate a new TreeNode
	 * 
	 * @param initValue
	 *            the value that this node is worth
	 * @param initLeft
	 *            this tree's left node
	 * @param initRight
	 *            this tree's right node
	 */
	public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight) {
		value = initValue;
		left = initLeft;
		right = initRight;
	}

	/**
	 * Get the value of this tree node
	 * 
	 * @return this node's value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Get this tree's left node
	 * 
	 * @return this trees left node or null if there is no node
	 */
	public TreeNode getLeft() {
		return left;
	}

	/**
	 * Get this tree's right node
	 * 
	 * @return this trees right node or null if there is no node
	 */
	public TreeNode getRight() {
		return right;
	}

	/**
	 * Change the value of this node to a new one
	 * 
	 * @param theNewValue
	 *            the value you want this node to have
	 */
	public void setValue(Object theNewValue) {
		value = theNewValue;
	}

	/**
	 * Change this tree's left node to something else
	 * 
	 * @param theNewLeft
	 *            what you want this tree's left node to equal
	 */
	public void setLeft(TreeNode theNewLeft) {
		left = theNewLeft;
	}

	/**
	 * Change this tree's right node to something else
	 * 
	 * @param theNewLeft
	 *            what you want this tree's right node to equal
	 */
	public void setRight(TreeNode theNewRight) {
		right = theNewRight;
	}
}
