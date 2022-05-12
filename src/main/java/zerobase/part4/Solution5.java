package zerobase.part4;

public class Solution5 {
    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        solution5.solution(
                4, 2, new int[]{2, 2, 1, 3}, new int[]{2, 4, 3, 2});
    }

    public int solution(int N, int M, int[] fry, int[] clean) {
        int answer = Integer.MAX_VALUE;
        int left = 0;
        int right = (100000 * 100);

        while (left <= right) {
            int mid = (left + right) / 2; // 자동 튀김기 가동 시간
            int cnt = 0;

            for (int i = 0; i < fry.length; i++) {
                int fry_time = fry[i];
                int clean_time = clean[i];

                cnt += (mid/(fry_time + clean_time));

                int div = mid%(fry_time + clean_time);

                if(div !=0 && div%fry_time==0) {
                    cnt++;
                }
            }

            if(cnt >= M) {
                right = mid-1;
                answer = Math.min(answer,mid);
            }else{
                left = mid+1;
            }
        }
        return answer;
    }
}
