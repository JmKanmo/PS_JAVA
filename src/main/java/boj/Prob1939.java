package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Prob 1939, 중량 제한
 * 이분탐색,그래프 탐색(DFS,BFS), MST(최소비용신장트리)
 */
public class Prob1939 {
    static int N, M;
    static int A, B;
    /**
     * Map<Integer,List<Graph>>로 할 경우, 메모리초과가 뜬다.
     * 따라서 이와같은 방법을 통해 인접리스트를 구성하도록 한다.
     * 두 방법의 메모리 차이가 극심하다.
     */
    static List<Graph>[] GP = new ArrayList[10001];
    static boolean[] visited = new boolean[10001];
    static int answer;

    /**
     * 크루스칼 알고리즘
     */
    static int[] parent = new int[10001];
    static List<Krus_GP> sorted_gp = new LinkedList<>();

    /**
     * Scanner로 하게될 경우, 시간이 비대히 늘어난다. 따라서, BufferedReader를 사용하도록 한다.
     */
    static void init_1() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String[] strArr = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(strArr[0]);
            M = Integer.parseInt(strArr[1]);

            for (int i = 0; i < M; i++) {
                int a, b, c;
                strArr = bufferedReader.readLine().split(" ");
                a = Integer.parseInt(strArr[0]);
                b = Integer.parseInt(strArr[1]);
                c = Integer.parseInt(strArr[2]);
                // 양방향
                if (GP[a] == null) {
                    GP[a] = new ArrayList<>();
                }
                GP[a].add(new Graph(b, c));

                if (GP[b] == null) {
                    GP[b] = new ArrayList<>();
                }
                GP[b].add(new Graph(a, c));
            }

            strArr = bufferedReader.readLine().split(" ");
            A = Integer.parseInt(strArr[0]);
            B = Integer.parseInt(strArr[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void init_2() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String[] strArr = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(strArr[0]);
            M = Integer.parseInt(strArr[1]);

            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                strArr = bufferedReader.readLine().split(" ");
                int a = Integer.parseInt(strArr[0]);
                int b = Integer.parseInt(strArr[1]);
                int c = Integer.parseInt(strArr[2]);
                sorted_gp.add(new Krus_GP(a, b, c));
            }

            strArr = bufferedReader.readLine().split(" ");
            A = Integer.parseInt(strArr[0]);
            B = Integer.parseInt(strArr[1]);

            Collections.sort(sorted_gp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * dfs로 탐색할 경우에, visited관련해서 해당 문제에서는 공장이 있는 두 섬을 연결하는 경로는 항상 존재하는 데이터만 입력으로 주어진다.
     * 라는 조건에 의해, visited를 단순히 true로만 탐색해도 제한시간안에 통과, 정답으로 인정된다.
     * 하지만, dfs도중 false로 변경(즉, 한번 탐색한 경로는 다시 false로 지정, 이후에 다시 또 탐색
     * 을 하게 될 경우, 탐색시간이 증가함에 따라 시간초과가 발생하게 된다. (가령,정답이라 해도, 시간초과로 인해 틀림)
     */
    static boolean dfs(int src, int dest, int weight) {
        visited[src] = true;

        if (src == dest) {
            return true;
        }

        List<Graph> gp = GP[src];

        for (Graph graph : gp) {
            if (visited[graph.dest]) {
                continue;
            }

            if (weight > graph.value) {
                continue;
            }
            boolean ret = dfs(graph.dest, dest, weight);

            if (ret) {
                return true;
            }
        }
        return false;
    }

    /**
     * bfs탐색으로 공장 연결 여부 확인. (dfs보다 간단)
     */
    static boolean bfs(int src, int dest, int weight) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        visited[src] = true;

        while (queue.isEmpty() != true) {
            int curIdx = queue.poll();

            if (curIdx == dest) {
                return true;
            }

            List<Graph> gpList = GP[curIdx];

            for (Graph gp : gpList) {
                int nextIdx = gp.dest;
                int nextValue = gp.value;

                if (visited[nextIdx] == true) {
                    continue;
                }

                if (weight > nextValue) {
                    continue;
                }

                visited[nextIdx] = true;
                queue.add(nextIdx);
            }
        }
        return false;
    }

    // 그래프 탐색 (BFS or DFS)
    static void solve_1() {
        // 이분 탐색
        int left = 0, right = 1000000000;

        while (left <= right) {
            int middle = (left + right) / 2;

            boolean ret = bfs(A, B, middle);

            // middle(weight)로 dfs 시도
            for (int i = 0; i < 10001; i++) {
                visited[i] = false;
            }

            if (ret) {
                left = middle + 1;
                answer = middle;
            } else {
                right = middle - 1;
            }
        }
    }

    static int getParent(int idx) {
        if (idx == parent[idx]) {
            return idx;
        } else {
            return getParent(parent[idx]);
        }
    }

    // 크루스칼
    static void solve_2() {
        for (Krus_GP krus_gp : sorted_gp) {
            int src = getParent(krus_gp.src);
            int dest = getParent(krus_gp.dest);
            int value = krus_gp.value;

            if (src != dest) {
                parent[src] = dest;
            }

            if (getParent(A) == getParent(B)) {
                answer = value;
                break;
            }
        }
    }

    public static void main(String[] args) {
        init_2();
        solve_2();
        System.out.println(answer);
    }

    static class Graph {
        int dest;
        int value;

        Graph() {
        }

        Graph(int dest, int value) {
            this.dest = dest;
            this.value = value;
        }
    }
}


// 크루스칼 알고리즘 사용 그래프 (정렬용)
class Krus_GP implements Comparable<Krus_GP> {
    int src;
    int dest;
    int value;

    Krus_GP() {
    }

    Krus_GP(int src, int dest, int value) {
        this.src = src;
        this.dest = dest;
        this.value = value;
    }

    @Override
    public int compareTo(Krus_GP o) {
        if (value < o.value) {
            return 1;
        } else if (value == o.value) {
            return 0;
        } else {
            return -1;
        }
    }
}
