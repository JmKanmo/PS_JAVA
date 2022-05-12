package zerobase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Util {
    public static void main(String[] args) {

    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (target == arr[mid]) {
                return mid;
            } else if (target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

class BinarySearchTree {
    //node class that defines BST node
    class Node {
        int key;
        Node left, right;

        public Node(int data) {
            key = data;
            left = right = null;
        }
    }

    // BST root node
    Node root;

    // Constructor for BST =>initial empty tree
    BinarySearchTree() {
        root = null;
    }

    //delete a node from BST
    void deleteKey(int key) {
        root = delete_Recursive(root, key);
    }

    //recursive delete function
    Node delete_Recursive(Node root, int key) {
        //tree is empty
        if (root == null) return root;

        //traverse the tree
        if (key < root.key)     //traverse left subtree
            root.left = delete_Recursive(root.left, key);
        else if (key > root.key)  //traverse right subtree
            root.right = delete_Recursive(root.right, key);
        else {
            // node contains only one
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node has two children;
            //get inorder successor (min value in the right subtree)
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = delete_Recursive(root.right, root.key);
        }
        return root;
    }

    int minValue(Node root) {
        //initially minval = root
        int minval = root.key;
        //find minval
        while (root.left != null) {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }

    // insert a node in BST
    void insert(int key) {
        root = insert_Recursive(root, key);
    }

    //recursive insert function
    Node insert_Recursive(Node root, int key) {
        //tree is empty
        if (root == null) {
            root = new Node(key);
            return root;
        }
        //traverse the tree
        if (key < root.key)     //insert in the left subtree
            root.left = insert_Recursive(root.left, key);
        else if (key > root.key)    //insert in the right subtree
            root.right = insert_Recursive(root.right, key);
        // return pointer
        return root;
    }

    // method for inorder traversal of BST
    void inorder() {
        inorder_Recursive(root);
    }

    // recursively traverse the BST
    void inorder_Recursive(Node root) {
        if (root != null) {
            inorder_Recursive(root.left);
            System.out.print(root.key + " ");
            inorder_Recursive(root.right);
        }
    }

    boolean search(int key) {
        root = search_Recursive(root, key);
        if (root != null)
            return true;
        else
            return false;
    }

    //recursive insert function
    Node search_Recursive(Node root, int key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.key == key)
            return root;
        // val is greater than root's key
        if (root.key > key)
            return search_Recursive(root.left, key);
        // val is less than root's key
        return search_Recursive(root.right, key);
    }

    //PostOrder Traversal - Left:Right:rootNode (LRn)
    void postOrder(Node node) {
        if (node == null)
            return;

        // first traverse left subtree recursively
        postOrder(node.left);

        // then traverse right subtree recursively
        postOrder(node.right);

        // now process root node
        System.out.print(node.key + " ");
    }

    // InOrder Traversal - Left:rootNode:Right (LnR)
    void inOrder(Node node) {
        if (node == null)
            return;
        //first traverse left subtree recursively
        inOrder(node.left);

        //then go for root node
        System.out.print(node.key + " ");

        //next traverse right subtree recursively
        inOrder(node.right);
    }

    //PreOrder Traversal - rootNode:Left:Right (nLR)
    void preOrder(Node node) {
        if (node == null)
            return;

        //first print root node first
        System.out.print(node.key + " ");
        // then traverse left subtree recursively
        preOrder(node.left);
        // next traverse right subtree recursively
        preOrder(node.right);
    }

    // Wrappers for recursive functions
    void postOrder_traversal() {
        postOrder(root);
    }

    void inOrder_traversal() {
        inOrder(root);
    }

    void preOrder_traversal() {
        preOrder(root);
    }
}

class BTree_List {
    Node head;

    BTree_List(char[] arr) {
        Node[] nodes = new Node[arr.length];

        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(null, null, arr[i], null);
        }

        for (int i = 0; i < arr.length; i++) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if (left < nodes.length) {
                nodes[i].left = nodes[left];
                nodes[left].parent = nodes[i];
            }

            if (right < nodes.length) {
                nodes[i].right = nodes[right];
                nodes[right].parent = nodes[i];
            }
        }
        this.head = nodes[0];
    }

    void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    void levelOrder(Node node) {
        Queue<Node> q = new LinkedList<>();

        q.add(node);

        while (q.isEmpty() != true) {
            Node cur = q.poll();

            System.out.println(cur.data);

            if (cur.left != null) {
                q.add(cur.left);
            }

            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    Node searchNode(char data) {
        Queue<Node> q = new LinkedList<>();
        q.add(this.head);

        while (q.isEmpty() != true) {
            Node cur = q.poll();

            if (cur.data == data) {
                return cur;
            }

            if (cur.left != null) {
                q.add(cur.left);
            }

            if (cur.right != null) {

                q.add(cur.right);
            }
        }
        return null;
    }

    // 특정 데이터를 가진 하나의 노드의 깊이를 알아낸다.
    // 순회를 통해서도 가능한 부분이다. ㅋㅋㅋ 차라리 순회가 더 쉽겟다.
    int getNodeDepth(char data) {
        Node e = searchNode(data);
        Node ptr = e;

        int cnt = 0;

        while (ptr.parent != null) {
            ptr = ptr.parent;
            cnt++;
        }
        return cnt;
    }

    // 트리 높이 구하는 공식

    /**
     * 트리를 순회한다. 이때, 각 트리를 순회할 때마다 매개변수로 cnt값을 넣어준다. 그리고
     * cnt 값을 재귀 호출될 때마다 +1 한다. 이때, 가장 높은 cnt 값을 구하면 그것이 높이가 된다.
     **/

    static class Node {
        Node left;
        Node right;
        char data;
        Node parent;

        Node() {
        }

        public Node(Node left, Node right, char data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }

        public Node(Node left, Node right, char data, Node parent) {
            this.left = left;
            this.right = right;
            this.data = data;
            this.parent = parent;
        }
    }
}

class BTree_Array {
    char[] arr; // 자료형은 뭐가되든 달라질 수 있음.

    public BTree_Array(char[] arr) {
        this.arr = arr;
    }

    void preOrder(int idx) {
        if (idx >= arr.length) {
            return;
        }

        System.out.println("data: " + arr[idx]);

        preOrder(idx * 2 + 1); // left
        preOrder(idx * 2 + 2); // right
    }

    void inOrder(int idx) {
        if (idx >= arr.length) {
            return;
        }

        inOrder(idx * 2 + 1);
        System.out.println("data: " + arr[idx]);
        inOrder(idx * 2 + 2);
    }

    void postOrder(int idx) {
        if (idx >= arr.length) {
            return;
        }

        postOrder(idx * 2 + 1);
        postOrder(idx * 2 + 2);
        System.out.println("data: " + arr[idx]);
    }

    // 배열 기반 트리는 이거는 무시
    void levelOrder(int idx) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

class _Trie {
    Node root;

    _Trie() {
        root = new Node();
    }

    void insert(String str) {
        Node cur = root;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            cur.map.putIfAbsent(ch, new Node());
            cur = cur.map.get(ch);

            if (i == str.length() - 1) {
                cur.isTerminal = true;
                break;
            }
        }
    }

    // 완전한 단어를 찾는 search 메소드
    boolean search(String str) {
        Node cur = root;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (cur.map.containsKey(ch)) {
                cur = cur.map.get(ch);
            } else {
                return false;
            }

            if (i == str.length() - 1) {
                if (!cur.isTerminal) {
                    return false;
                }
            }
        }
        return true;
    }

    // startsWith 메소드로 판별,대체 가능
    boolean partialSearch(String str) {
        Node cur = root;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (cur.map.get(ch) == null) {
                return false;
            }
            cur = cur.map.get(ch);
        }
        return true;
    }

    // true : 삭제 성공, false : 삭제 실패
    public boolean delete(String str) {
        return delete(root, str, 0);
    }

    public boolean delete(Node node, String str, int idx) {
        char ch = str.charAt(idx);

        if (!node.map.containsKey(ch)) {
            return false;
        }

        Node cur = node.map.get(ch);
        idx++;

        if (idx == str.length()) {
            if (!cur.isTerminal) {
                return false;
            }

            cur.isTerminal = false;

            if (cur.map.isEmpty()) {
                node.map.remove(ch);
            }
        } else {
            if (!this.delete(cur, str, idx)) {
                return false;
            }

            if (!cur.isTerminal && cur.map.isEmpty()) {
                node.map.remove(ch);
            }
        }
        return true;
    }

    static class Node {
        HashMap<Character, Node> map;
        boolean isTerminal;

        Node() {
            map = new HashMap<>();
            isTerminal = false;
        }
    }
}
