package com.meme.algs;

import static com.meme.algs.RedBlackTree.Color.BLACK;
import static com.meme.algs.RedBlackTree.Color.RED;

public class RedBlackTree {

    enum Color {
        RED, BLACK;
    }

    private static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;
        Color color = RED;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        boolean isLeftChild() {
            return this.parent != null && this.parent.left == this;
        }

        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            return parent.isLeftChild() ? parent.parent.right : parent.parent.left;
        }

        Node sibling() {
            if (parent == null) {
                return null;
            }
            return isLeftChild() ? parent.right : parent.left;
        }

        static boolean isRed(Node node) {
            return node != null && node.color == RED;
        }

        static boolean isBlack(Node node) {
            return node == null || node.color == BLACK;
        }
    }

    private Node root;

    private void rightRotate(Node node) {

        Node nodeParent = node.parent;
        Node left = node.left;
        Node leftRight = left.right;

        node.left = leftRight;
        if (leftRight != null) {
            leftRight.parent = node;
        }

        left.right = node;
        node.parent = left;

        left.parent = nodeParent;
        if (nodeParent == null) {
            root = left;
        } else if (nodeParent.left == node) {
            nodeParent.left = left;
        } else {
            nodeParent.right = left;
        }
    }

    private void leftRotate(Node node) {

        Node nodeParent = node.parent;
        Node right = node.right;
        Node rightLeft = right.left;

        node.right = rightLeft;
        if (rightLeft != null) {
            rightLeft.parent = node;
        }

        right.left = node;
        node.parent = right;

        right.parent = nodeParent;
        if (nodeParent == null) {
            root = right;
        } else if (nodeParent.left == node) {
            nodeParent.left = right;
        } else {
            nodeParent.right = right;
        }
    }

    public void put(int key, Object value) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                p.value = value;
                return;
            }
        }
        Node newNode = new Node(key, value);
        if (parent == null) {
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
            newNode.parent = parent;
        } else {
            parent.right = newNode;
            newNode.parent = parent;
        }
        fixRedRed(newNode);
    }

    void fixRedRed(Node node) {
        if (node == root) {
            node.color = BLACK;
            return;
        }
        if (Node.isBlack(node.parent)) {
            return;
        }

        Node parent = node.parent;
        Node uncle = node.uncle();
        Node grandparent = parent.parent;
        if (Node.isRed(uncle)) {
            parent.color = BLACK;
            uncle.color = BLACK;
            grandparent.color = RED;
            fixRedRed(grandparent);
        }

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                parent.color = BLACK;
            } else {
                leftRotate(parent);
                node.color = BLACK;
            }
            grandparent.color = RED;
            rightRotate(grandparent);
        } else {
            if (node.isLeftChild()) {
                rightRotate(parent);
                node.color = BLACK;
            } else {
                parent.color = BLACK;
            }
            grandparent.color = RED;
            leftRotate(grandparent);
        }
    }

    public void remove(int key) {
        Node deleted = find(key);
        if (deleted != null) {
            doRemove(deleted);
        }
    }

    private void fixDoubleBlack(Node node) {
        if (node == root) {
            return;
        }
        Node parent = node.parent;
        Node sibling = node.sibling();
        if (Node.isRed(sibling)) {
            if (node.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = RED;
            sibling.color = BLACK;
            fixDoubleBlack(node);
            return;
        }
        if (sibling != null) {
            if (Node.isBlack(sibling.left) && Node.isBlack(sibling.right)) {
                sibling.color = RED;
                if (Node.isRed(parent)) {
                    parent.color = BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
            } else {
                if (sibling.isLeftChild() && Node.isRed(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = BLACK;
                    sibling.color = parent.color;
                } else if (sibling.isLeftChild() && Node.isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                } else if (!sibling.isLeftChild() && Node.isRed(sibling.right)) {
                    leftRotate(parent);
                    sibling.right.color = BLACK;
                    sibling.color = parent.color;
                } else if (!sibling.isLeftChild() && Node.isRed(sibling.left) ) {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                parent.color = BLACK;
            }
        } else {
            fixDoubleBlack(parent);
        }

    }

    private void doRemove(Node deleted) {
        Node replace = findReplace(deleted);
        Node deletedParent = deleted.parent;
        if (replace == null) {

            if (deleted == root) {
                root = null;
            } else {
                if (Node.isBlack(deleted)) {
                    fixDoubleBlack(deleted);
                }
                if (deleted.isLeftChild()) {
                    deletedParent.left = null;
                } else {
                    deletedParent.right = null;
                }
                deleted.parent = null;
            }
            return;
        }
        if (deleted.left == null || deleted.right == null) {

            if (deleted == root) {
                root.key = replace.key;
                root.value = replace.value;
                root.left = root.right = null;
            } else {
                if (deleted.isLeftChild()) {
                    deletedParent.left = replace;
                } else {
                    deletedParent.right = replace;
                }
                replace.parent = deletedParent;
                deleted.parent = deleted.left = deleted.right = null;
                if (Node.isBlack(deleted) && Node.isBlack(replace)) {
                    fixDoubleBlack(replace);
                } else {
                    replace.color = BLACK;
                }
            }

            return;
        }
        int tk = deleted.key;
        deleted.key = replace.key;
        replace.key = tk;
        Object tv = deleted.value;
        deleted.value = replace.value;
        replace.value = tv;
        doRemove(replace);
    }

    Node find(int key) {
        Node p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    Node findReplace(Node deleted) {
        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }

        Node s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }

}
