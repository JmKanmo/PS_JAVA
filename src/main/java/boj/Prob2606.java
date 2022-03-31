package boj;

import java.util.*;

/**
 * 2606. 바이러스
 * 그래프,BFS
 */
public class Prob2606 {
    private static int N;
    private static int linkedN;
    private static Scanner scanner = new Scanner(System.in);
    private static Map<Integer, List<Integer>> GP = new HashMap<>();
    private static boolean[] visited = new boolean[101];

    public static void input() {
        N = scanner.nextInt();
        linkedN = scanner.nextInt();

        scanner.nextLine();

        for (int i = 0; i < linkedN; i++) {
            Integer n = null, m = null;
            String str = scanner.nextLine();
            String[] parsed = str.split(" ");

            for (int j = 0; j < parsed.length; j++) {
                if (j == 0) {
                    n = Integer.parseInt(parsed[j]);
                } else {
                    m = Integer.parseInt(parsed[j]);
                }
            }

            if (GP.get(n) == null)
                GP.put(n, new ArrayList<>());

            GP.get(n).add(m);

            if(GP.get(m) == null)
                GP.put(m,new ArrayList<>());

            GP.get(m).add(n);
        }
    }

    public static int bfs() {
        int sols = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(new Integer(1));
        visited[1] = true;

        while (q.isEmpty() != true) {
            Integer n = q.poll();

            if (n != 1) {
                sols++;
            }

            List<Integer> values = GP.get(n);

            if(values == null) continue;

            for(Iterator it = values.iterator(); it.hasNext();) {
                Integer value = (Integer) it.next();

                if (visited[value] != true) {
                    visited[value] = true;
                    q.add(value);
                }
            }
        }
        return sols;
    }

    public static void main(String[] args) {
        input();
        int n = bfs();
        System.out.println(n);
    }
}
