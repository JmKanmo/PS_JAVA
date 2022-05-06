package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class programmers_길찾기게임 {
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        List<Pointer> nodeList = new ArrayList<>();
        BTree bTree = new BTree();

        for (int i = 0; i < nodeinfo.length; i++) {
            nodeList.add(new Pointer(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        Collections.sort(nodeList);

        for (Pointer pointer : nodeList) {
            bTree.insert(pointer);
        }

        List<Integer> preOrderList = new ArrayList<>();
        bTree.preOrder(bTree.head, preOrderList);

        List<Integer> postOrderList = new ArrayList<>();
        bTree.postOrder(bTree.head, postOrderList);

        answer = new int[2][];
        answer[0] = new int[preOrderList.size()];

        for (int i = 0; i < answer[0].length; i++) {
            answer[0][i] = preOrderList.get(i);
        }

        answer[1] = new int[postOrderList.size()];

        for (int i = 0; i < answer[1].length; i++) {
            answer[1][i] = postOrderList.get(i);
        }
        return answer;
    }

    static class Pointer implements Comparable<Pointer> {
        int idx;
        int x;
        int y;

        Pointer() {
        }

        public Pointer(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }


        @Override
        public int compareTo(Pointer o) {
            return Integer.compare(o.y, this.y);
        }

        @Override
        public String toString() {
            return "Pointer{" +
                    "idx=" + idx +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class BTree {
        Node head;

        BTree() {
        }

        void insert(Pointer pointer) {
            Node newNode = new Node(pointer.idx, pointer.x, null, null);

            if (head == null) {
                head = newNode;
                return;
            } else {
                Node ptr = head;

                while (true) {
                    if (ptr.weight > newNode.weight) {
                        // left
                        if (ptr.left == null) {
                            ptr.left = newNode;
                            break;
                        } else {
                            ptr = ptr.left;
                        }
                    } else {
                        if (ptr.right == null) {
                            ptr.right = newNode;
                            break;
                        } else {
                            ptr = ptr.right;
                        }
                    }
                }
            }
        }

        class Node {
            int idx;
            int weight;
            Node left;
            Node right;

            Node() {
            }

            public Node(int idx, int weight, Node left, Node right) {
                this.idx = idx;
                this.weight = weight;
                this.left = left;
                this.right = right;
            }
        }

        void preOrder(Node node, List<Integer> list) {
            if (node == null) {
                return;
            }
            list.add(node.idx);
            preOrder(node.left, list);
            preOrder(node.right, list);
        }

        void postOrder(Node node, List<Integer> list) {
            if (node == null) {
                return;
            }
            postOrder(node.left, list);
            postOrder(node.right, list);
            list.add(node.idx);
        }
    }

    public static void main(String[] args) {
        programmers_길찾기게임 main = new programmers_길찾기게임();
        main.solution(new int[][]{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}});
    }
}
