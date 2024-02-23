package com.meme.algs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BSTTree1 {
    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    BSTNode root;

    public Object get(int key) {
        BSTNode curr = root;

        while (curr != null) {
            if (key == curr.key) {
                return curr.value;
            } else if (key < curr.key) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        return null;
    }

    public Object min() {

        return min(root);
    }

    public Object min(BSTNode node) {

        if (node != null) {
            BSTNode curr = node;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr.value;
        } else {
            return null;
        }
    }

    public Object max() {

        return max(root);
    }

    public Object max(BSTNode node) {

        if (node != null) {
            BSTNode curr = node;
            while (curr.right != null) {
                curr = curr.right;
            }
            return curr.value;
        } else {
            return null;
        }
    }

    public void put(int key, Object value) {

        if (root == null) {
            root = new BSTNode(key, value);
        }
        BSTNode parent = null;
        BSTNode curr = root;

        while (curr != null) {
            if (key < curr.key) {
                parent = curr;
                curr = curr.left;
            } else if (key > curr.key) {
                parent = curr;
                curr = curr.right;
            } else {
                curr.value = value;
                return;
            }
        }
        if (key < parent.key) {
            parent.left = new BSTNode(key, value);
        } else {
            parent.right = new BSTNode(key, value);
        }
    }

    public Object predecessor(int key) {

        BSTNode p = root;
        BSTNode predecessor = null;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                predecessor = p;
                p = p.right;
            } else {
                if (p.left != null) {
                    return max(p.left);
                } else {
                    if (predecessor != null) {
                        return predecessor.value;
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public Object successor(int key) {
        if (root == null) {
            return null;
        }
        BSTNode p = root;
        BSTNode successor = null;
        while (p != null) {
            if (key < p.key) {
                successor = p;
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                if (p.right != null) {
                    return min(p.right);
                } else {
                    if (successor != null) {
                        return successor.value;
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public Object delete(int key) {
        BSTNode parent = null;
        BSTNode p = root;
        while (p != null) {
            if (key < p.key) {
                parent = p;
                p = p.left;
            } else if (key > p.key) {
                parent = p;
                p = p.right;
            } else {
                break;
            }
        }

        if (p == null) {
            return null;
        }
        if (p.left == null) {
            shift(parent, p, p.right);
        } else if (p.right == null) {
            shift(parent, p, p.left);
        } else {
            BSTNode sParent = p;
            BSTNode s = p.right;
            while (s.left != null) {
                sParent = s;
                s = s.left;
            }
            if (sParent != p) {
                shift(parent, p, s);
                s.right = p.right;
            }
            shift(parent, p, s);
            s.left = p.left;
        }

        return p;
    }

    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {
            root = child;
        } else if (parent.left == deleted) {
            parent.left = child;
        } else if (parent.right == deleted) {
            parent.right = child;
        }
    }

    public List<Object> less(int key) {
        ArrayList<Object> result = new ArrayList<>();

        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key < key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.right;
            }
        }

        return result;
    }

    public List<Object> greater(int key) {

        ArrayList<Object> result = new ArrayList<>();

        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.right;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.value);
                }
                p = pop.left;
            }
        }

        return result;
    }

    public List<Object> between(int key1, int key2) {

        ArrayList<Object> result = new ArrayList<>();

        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key >= key1 && pop.key <= key2) {
                    result.add(pop.value);
                } else if (pop.key > key2) {
                    break;
                }
                p = pop.right;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        BSTNode n1 = new BSTNode(1, 1);
        BSTNode n3 = new BSTNode(3, 3);
        BSTNode n2 = new BSTNode(2, 2, n1, n3);
        BSTNode n5 = new BSTNode(5, 5);
        BSTNode n7 = new BSTNode(7, 7);
        BSTNode n6 = new BSTNode(6, 6, n5, n7);
        BSTNode n4 = new BSTNode(4, 4, n2, n6);

        BSTTree1 tree = new BSTTree1();
        tree.root = n4;

        System.out.println(tree.less(5));
        System.out.println(tree.greater(5));
        System.out.println(tree.between(3, 5));
    }

}
