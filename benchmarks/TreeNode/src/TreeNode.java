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
            root.computeForces();
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                root.computeForces();
            }
            System.out.println("Time: "+(System.currentTimeMillis()-start));
        } else if (args[2].equals("delegator")) {
            root.computeForcesDelegator();
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                root.computeForcesDelegator();
            }
            System.out.println("Time: "+(System.currentTimeMillis()-start));
        } else if (args[2].equals("unannotated")) {
            root.computeForcesUnannotated();
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                root.computeForcesUnannotated();
            }
            System.out.println("Time: "+(System.currentTimeMillis()-start));
        } else if (args[2].equals("flat")) {
            root.computeForce();
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                root.computeForce();
            }
            System.out.println("Time: "+(System.currentTimeMillis()-start));
        } else if (args[2].equals("flat-unannotated")) {
            root.computeForceUnannotated();
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                root.computeForceUnannotated();
            }
            System.out.println("Time: "+(System.currentTimeMillis()-start));
        }
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
