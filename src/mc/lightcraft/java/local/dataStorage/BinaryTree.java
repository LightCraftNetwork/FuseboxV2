package mc.lightcraft.java.local.dataStorage;

public abstract class BinaryTree
{
	private TreeNode root;
	
	/**
	 * Instantiate a new BinaryTree
	 */
	public BinaryTree()
	{
		root = null;
	}
	
	/**
	 * Returns the root of this tree as a TreeNode
	 * @return this tree's root
	 */
	public TreeNode getRoot()
	{
		return root;
	}
	
	/**
	 * Set this trees root to a chosen value
	 * @param theNewNode what you want the new value to be
	 */
	public void setRoot(TreeNode theNewNode)
	{
		root = theNewNode;
	}
	
	/**
	 * Returns if this tree has any data
	 * @return is this tree free of data
	 */
	public boolean isEmpty()
	{
		return root == null;
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Insert a comparable object into the tree
	 * @param item the object you want inserted
	 */
	public abstract void insert(Comparable item);
	
	@SuppressWarnings("rawtypes")
	/**
	 * Location a comparable object within this tree
	 * @param key the object you are looking for
	 * @return the object if available, otherwise null
	 */
	public abstract TreeNode find(Comparable key);
}
