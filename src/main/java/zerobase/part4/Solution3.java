package zerobase.part4;

public class Solution3 {
    public static void main(String[] args) {

    }

    public int solution(int[] arr) {
        int answer = findPeakElement(arr);
        if (answer == arr.length - 1) return -1;
        return answer;
    }

    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if(mid == 0 || mid == nums.length) {
                return -1;
            }

            if(nums[mid-1] < nums[mid] && nums[mid+1] < nums[mid]) {
                return mid;
            }

            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
