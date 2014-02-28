import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import de.unifr.acp.annot.Grant;

public class TreeNode {
    private static final double R_GRAV = 9.81;
    double mass;
    double force;
    TreeNode left;
    TreeNode right;
    TreeNode next = null;
    TreeNode parent = null;

    TreeNode(double mass, TreeNode left, TreeNode right) {
        this.mass = mass;
        this.left = left;
        this.right = right;
    }

    public void print() {
        System.out.println("mass=" + mass + ", force=" + force);
        if (left != null)
            left.print();
        if (right != null)
            right.print();
    }

    @Grant("this.(left|right)*.force, this.(left|right)*.mass.@")
    public void computeForces() {
        /* reads mass writes force */
        this.force = this.mass * R_GRAV;

        /*
         * reads left.(left|right)*.mass, right.(left|right) writes
         * left.(left|right)*.force
         */
        if (left != null)
            left.computeForces();

        /*
         * reads right.(left|right)*.mass, right.(left|right) writes
         * right.(left|right)*.force
         */
        if (right != null)
            right.computeForces();
    }

    @Grant("this.(left|right)*.force, this.(left|right)*.mass.@")
    public void computeForcesDelegator() {
        computeForcesUnannotated();
    }
    
    public void computeForcesIterative() {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        
        stack.push(this);
        
        boolean doneLeft = false;
        boolean doneRight = false;
        while (!stack.isEmpty()) {
            TreeNode current = stack.peek();
            
            /* reads mass writes force */
            current.force = current.mass * R_GRAV;
            
            if (current.left != null && !doneLeft) {
                stack.push(current.left);
                doneLeft = false;
                doneRight = false;
            } else if (current.right != null && !doneRight) {
                stack.push(current.right);
                doneLeft = false;
                doneRight = false;
            } else if (!stack.isEmpty()) {
                TreeNode popped = stack.pop();
                if (!stack.isEmpty() && stack.peek().right == popped) {
                    doneLeft = true;
                    doneRight = true;
                } else if (!stack.isEmpty() && stack.peek().left == popped) {
                    doneLeft = true;
                    doneRight = false;
                } else {
                    doneLeft = false;
                    doneRight = false;
                }
            }
        }
    }

    public void computeForcesUnannotated() {
        /* reads mass writes force */
        this.force = this.mass * R_GRAV;

        /*
         * reads left.(left|right)*.mass, right.(left|right) writes
         * left.(left|right)*.force
         */
        if (left != null)
            left.computeForcesUnannotated();

        /*
         * reads right.(left|right)*.mass, right.(left|right) writes
         * right.(left|right)*.force
         */
        if (right != null)
            right.computeForcesUnannotated();
    }
    
    public void setParentNodes(TreeNode parent) {
        this.parent = parent;

        if (left != null)
            left.setParentNodes(this);

        if (right != null)
            right.setParentNodes(this);
    }
    
    public void computeForcesLoopUnannotated() {
        TreeNode current = this;
        TreeNode last = null;
        boolean down = true;
        while (true) {
            /* reads mass writes force */
            if (down)
                current.force = current.mass * R_GRAV;
            TreeNode currentLeft = current.left;
            if (down && currentLeft != null) {
                down = true;
                //current.left.parent = current;
                last = current;
                current = currentLeft;
                continue;
            }
            TreeNode currentRight = current.right;
            if (currentRight != null
                    && (down || (!down && last != currentRight))) {
                down = true;
                //current.right.parent = current;
                last = current;
                current = currentRight;
                continue;
            }
            TreeNode currentParent = current.parent;
            if (currentParent != null) {
                last = current;
                current = currentParent;
                down = false;
            } else {
                break;
            }
        }
    }
    
    @Grant("this.force, this.mass.@")
    public void computeForce() {
        /* reads mass writes force */
        this.force = this.mass * R_GRAV;
    }
    
    public void computeForceUnannotated() {
        /* reads mass writes force */
        this.force = this.mass * R_GRAV;
    }
    
    public static void main(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Missing command line arguments.");
        }
        
        int nodeCount = Integer.parseInt(args[0]);
        TreeNode root = null;
        if (args[1].equals("balanced")) {
             root = genBalancedTree(nodeCount);
        } else if (args[1].equals("degenerate")) {
            root = genDegenerateTree(nodeCount);          
        }
        
        if (args[2].equals("annotated")) {
            for (int j = 0; j < 500; j++)
                root.computeForces();
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                root.computeForces();
            }
            long end = System.currentTimeMillis();
            printTime(end-start);
        } else if (args[2].equals("delegator")) {
            for (int j = 0; j < 500; j++)
                root.computeForcesDelegator();
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                root.computeForcesDelegator();
            }
            long end = System.currentTimeMillis();
            printTime(end-start);
        } else if (args[2].equals("unannotated")) {
            for (int j = 0; j < 500; j++)
                root.computeForcesUnannotated();
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                root.computeForcesUnannotated();
            }
            long end = System.currentTimeMillis();
            printTime(end-start);
        } else if (args[2].equals("loop-unannotated")) {
            root.setParentNodes(null);
                for (int j = 0; j < 500; j++)
                root.computeForcesLoopUnannotated();
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                root.computeForcesLoopUnannotated();
            }
            long end = System.currentTimeMillis();
            printTime(end-start);
        } else if (args[2].equals("flat")) {
            for (int j = 0; j < 500; j++)
                root.computeForce();
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                root.computeForce();
            }
            long end = System.currentTimeMillis();
            printTime(end-start);
        } else if (args[2].equals("flat-unannotated")) {
            for (int j = 0; j < 500; j++)
                root.computeForceUnannotated();
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                root.computeForceUnannotated();
            }
            long end = System.currentTimeMillis();
            printTime(end-start);
        }
    }
    
    private static void printTime(long timeMillis) {
        System.out.format("Time: %8.3f\n", (((float)timeMillis)/1000));
    }

    public static TreeNode genBalancedTree(int n) {
        if (n == 0) {
            return null;
        } else {
            return new TreeNode((double) n, genBalancedTree(n / 2),
                    genBalancedTree((n - 1) / 2));
        }
    }
    
    public static TreeNode genDegenerateTree(int n) {
        if (n == 0) {
            return null;
        } else {
            return new TreeNode((double) n, null, genDegenerateTree(n - 1));
        }
    }
}
