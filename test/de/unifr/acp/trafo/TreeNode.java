package de.unifr.acp.trafo;

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
        }
    }

    private static TreeNode genBalancedTree(int n) {
        if (n == 0) {
            return null;
        } else {
            return new TreeNode((double) n, genBalancedTree(n / 2),
                    genBalancedTree((n - 1) / 2));
        }
    }
    
    private static TreeNode genDegenerateTree(int n) {
        if (n == 0) {
            return null;
        } else {
            return new TreeNode((double) n, null, genDegenerateTree(n - 1));
        }
    }
}
