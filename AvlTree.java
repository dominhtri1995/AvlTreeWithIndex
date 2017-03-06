/**
 * Implements an AVL tree.
 */
package hackathon;

public class AvlTree<AnyType> {

    /**
     * The tree root.
     */
    private AvlNode<AnyType> root;

    public AvlTree() {
        root = null;
    }

    public int insert(double index, AnyType x) {
        //Check index out of bound
        if (root == null) {
            if (index != 0) {
                return -1;
            }
        }
        if (root != null && (index > root.leftSubTreeNum + numRightSubTree(root.right) + 2 || index < 0)) {
            return -1;
        }
        // Insert
        root = insert(index, x, root);
        return 1;
    }

    public int remove(double index) {
        if (root == null) {
            return -1;
        }
        if (root != null && (index > root.leftSubTreeNum + numRightSubTree(root.right) || index < 0)) {
            return -1;
        }

        root = remove(index, root);

        return 1;
    }

    public AnyType findAndReplace(double index, AnyType newValue) {
        //Check index out of bound
        if (root == null) {
            if (index != 0) {
                return null;
            }
        }
        if (root != null && (index > root.leftSubTreeNum + numRightSubTree(root.right) || index < 0)) {
            return null;
        }

        return findAndReplace(index, newValue, root);
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    public AnyType get(int index) {
        if (root == null) {
            return null;
        }
        if (root != null && (index > root.leftSubTreeNum + numRightSubTree(root.right) || index < 0)) {
            return null;
        }

        return find(index, root);
    }

    private static final int ALLOWED_IMBALANCE = 1;

    // Assume t is either balanced or within one of being balanced
    /**
     * Internal method to insert into a subtree.
     */
    private AnyType find(int index, AvlNode<AnyType> t) {
        if (t == null) {
            return null;
        }
        if (index == t.leftSubTreeNum) {
            return t.element;
        } else if (index < t.leftSubTreeNum) {
            return find(index, t.left);
        } else {
            return find(index - t.leftSubTreeNum - 1, t.right);
        }
    }

    private AvlNode<AnyType> insert(double index, AnyType x, AvlNode<AnyType> t) {
        if (t == null) {
            System.out.println("New root :" + index + " " + x);
            return new AvlNode<>(index, x, null, null);
        }

        if (((int)index - (int)t.index ==0 &&(int)t.index -t.index !=0)|| (0<index - t.index&& index - t.index <1 &&(int)t.index -t.index !=0)) { // 
//            System.out.println("in here index :"+index +"   tindex: "+t.index);
            t.leftSubTreeNum++;
            t.left = insert(t.index - 0.0001, x, t.left);
        }
        else if (index < t.index) {
            t.leftSubTreeNum++;
            t.left = insert(index, x, t.left);
        } else if (index > t.index) {
//            System.out.println(" larger ne");
            t.right = insert(index, x, t.right);
        } 
        else //Duplicate
        {
            System.out.println("Duplicate :" + t.index);
            t.leftSubTreeNum++;
            t.left = insert(index - 0.0001, x, t.left);
        }
        return balance(t);
    }

    /**
     * Internal method to remove from a subtree.
     */
    private AvlNode<AnyType> remove(double index, AvlNode<AnyType> t) {
        if (t == null) {
            return t;   // Item not found; do nothing
        }

        if (index < t.leftSubTreeNum) {
            t.leftSubTreeNum--;
            t.left = remove(index, t.left);
        } else if (index > t.leftSubTreeNum) {
            t.right = remove(index - t.leftSubTreeNum - 1, t.right);
        } else if (t.left != null && t.right != null) // Two children
        {
            AvlNode<AnyType> min = findMinForRemove(t.right);
            t.index = min.index;
            t.element = min.element;
            t.right = remove(0, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }

        return balance(t);
    }

    private AvlNode<AnyType> findMinForRemove(AvlNode<AnyType> t) {
        if (t == null) {
            return t;
        }

        while (t.left != null) {
            t.leftSubTreeNum--;
            t = t.left;
        }
        return t;
    }

    private AvlNode<AnyType> findMax(AvlNode<AnyType> t) {
        if (t == null) {
            return t;
        }

        while (t.right != null) {
            t = t.right;
        }
        return t;
    }

    private AnyType findAndReplace(double index, AnyType newValue, AvlNode<AnyType> t) {
        if (index == t.leftSubTreeNum) {
            t.element = newValue;
            return t.element;
        } else if (index < t.leftSubTreeNum) {
            return findAndReplace(index, newValue, t.left);
        } else {
            return findAndReplace(index - t.leftSubTreeNum - 1, newValue, t.right);
        }
    }

    private void printTree(AvlNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.index + "  leftsub:" + t.leftSubTreeNum + "  element:" + t.element);
            printTree(t.right);
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height(AvlNode<AnyType> t) {
        return t == null ? -1 : t.height;
    }

    //Return the number of node in the right subtree
    private int numRightSubTree(AvlNode<AnyType> t) {
        int sum = 0;
        if (t == null) {
            return 0;
        }
        while (t != null) {
            sum = sum + t.leftSubTreeNum + 1;
            t = t.right;
        }
        return sum;
    }

    /**
     * Rotate binary tree node with left child. For AVL trees, this is a single
     * rotation for case 1. Update heights, then return new root.
     */
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1 = k2.left;
        int numRight = numRightSubTree(k1.right);
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;

        k2.leftSubTreeNum = numRight;
        return k1;
    }

    /**
     * Rotate binary tree node with right child. For AVL trees, this is a single
     * rotation for case 4. Update heights, then return new root.
     */
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k1) {
        AvlNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        k2.leftSubTreeNum += (k1.leftSubTreeNum + 1);
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child with its right child;
     * then node k3 with new left child. For AVL trees, this is a double
     * rotation for case 2. Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child with its left child;
     * then node k1 with new right child. For AVL trees, this is a double
     * rotation for case 3. Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    private AvlNode<AnyType> balance(AvlNode<AnyType> t) {
        if (t == null) {
            return t;
        }

        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            if (height(t.left.left) >= height(t.left.right)) {
                t = rotateWithLeftChild(t);
            } else {
                t = doubleWithLeftChild(t);
            }
        } else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
            if (height(t.right.right) >= height(t.right.left)) {
                t = rotateWithRightChild(t);
            } else {
                t = doubleWithRightChild(t);
            }
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    public void checkBalance() {
        checkBalance(root);
    }

    private int checkBalance(AvlNode<AnyType> t) {
        if (t == null) {
            return -1;
        }

        if (t != null) {
            int hl = checkBalance(t.left);
            int hr = checkBalance(t.right);
            if (Math.abs(height(t.left) - height(t.right)) > 1
                    || height(t.left) != hl || height(t.right) != hr) {
                System.out.println("OOPS!!");
            }
        }

        return height(t);
    }

    private static class AvlNode<AnyType> {
        // Constructors

        AvlNode(AnyType theElement) {
            this(0, theElement, null, null);
        }

        AvlNode(double in, AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
            index = in;
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
            leftSubTreeNum = 0;
        }

        AnyType element; // The data in the node
        double index;    //index 
        int leftSubTreeNum;  // number of node in its left subtree
        AvlNode<AnyType> left;         // Left child
        AvlNode<AnyType> right;        // Right child
        int height;       // Height
    }

}
