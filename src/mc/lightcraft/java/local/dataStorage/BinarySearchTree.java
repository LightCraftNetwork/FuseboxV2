package mc.lightcraft.java.local.dataStorage;

public class BinarySearchTree extends BinaryTree
{
	
	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	/**
	 * Insert a comparable object into this tree. It will be sorted accordingly.
	 * @param object
	 */
	public void insert(Comparable item)
	{
		if (getRoot() == null) setRoot(new TreeNode(item));
		else
		{
			TreeNode p = null, q = getRoot();
			while (q != null)
			{
				p = q;
				if (item.compareTo(p.getValue()) < 0) q = p.getLeft();
				else q = p.getRight();
			}
			if (item.compareTo(p.getValue()) < 0) p.setLeft(new TreeNode(item));
			else p.setRight(new TreeNode(item));
		}
	}
	
	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	/**
	 * Locate a object within this tree. Uses comparability. If no object is found, this function will return null. This function returns the object's node.
	 * @param object
	 * @return
	 */
	public TreeNode find(Comparable key)
	{
		TreeNode p = getRoot();
		while (p != null && key.compareTo(p.getValue()) != 0)
		{
			if (key.compareTo(p.getValue()) < 0) p = p.getLeft();
			else p = p.getRight();
		}
		return p;
	}
}
