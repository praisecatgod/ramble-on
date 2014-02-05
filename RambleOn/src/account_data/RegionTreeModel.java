package account_data;

import java.util.Vector;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class RegionTreeModel implements TreeModel {

    private TreeNode rootNode;
    private Vector<TreeModelListener> listeners =
            new Vector<TreeModelListener>();

    public RegionTreeModel(TreeNode initRootNode) {
        rootNode = initRootNode;
    }

    @Override
    public Object getRoot() {
        return rootNode;
    }

    @Override
    public Object getChild(Object parent, int index) {
        TreeNode parentNode = (TreeNode) parent;
        return parentNode.getChildAt(index);
    }

    @Override
    public int getChildCount(Object parent) {
        TreeNode parentNode = (TreeNode) parent;
        return parentNode.getChildCount();
    }

    @Override
    public boolean isLeaf(Object node) {
        TreeNode treeNode = (TreeNode) node;
        return treeNode.isLeaf();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        TreeNode parentNode = (TreeNode) parent;
        TreeNode childNode = (TreeNode) child;
        return parentNode.getIndex(childNode);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }
}
