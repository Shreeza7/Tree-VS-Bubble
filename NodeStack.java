package TreeVsBubble;

public class NodeStack {
    private TreeNode[] nodeArray;
    private int stackTop;
    private static final int DEFAULT_CAPACITY = 10;

    public NodeStack() {
        nodeArray = new TreeNode[DEFAULT_CAPACITY];
        stackTop = -1;
    }

    public boolean isStackEmpty() {
        return stackTop == -1;
    }

    public void pushNode(TreeNode node) {
        if (stackTop == nodeArray.length - 1) {
            expandStack();
        }
        nodeArray[++stackTop] = node;
    }

    public TreeNode popNode() {
        if (isStackEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return nodeArray[stackTop--];
    }

    private void expandStack() {
        int newCapacity = nodeArray.length * 2;
        TreeNode[] newArray = new TreeNode[newCapacity];
        System.arraycopy(nodeArray, 0, newArray, 0, nodeArray.length);
        nodeArray = newArray;
    }
}

