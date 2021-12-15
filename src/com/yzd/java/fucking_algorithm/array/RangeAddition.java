package com.yzd.java.fucking_algorithm.array;

/**
 * Created by yzd on 2021/12/8
 */

import java.util.Arrays;

/**
 * 370 加法区间
 *
 * 题目描述:
 *
 * 假设你有一个长度为 n 的数组
 *
 * ，初始情况下所有的数字均为 0，你将会被给出 k 个更新的操作。
 *
 * 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。
 *
 * 请你返回 k 次操作后的数组。
 *
 *
 * 输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * 输出: [-2,0,3,5,3]
 *
 *
 * 初始状态:
 * [0,0,0,0,0]
 *
 * 进行了操作 [1,3,2] 后的状态:
 * [0,2,2,2,0]
 *
 * 进行了操作 [2,4,3] 后的状态:
 * [0,2,5,5,3]
 *
 * 进行了操作 [0,2,-2] 后的状态:
 * [-2,0,3,5,3]
 *
 */
public class RangeAddition {
    // 370
    public int[] getModifiedArray(int length, int[][] updates){
        //nums 初始化为全 0
        int[] nums = new int[length];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] update : updates
        ) {
            int i = update[0];
            int j = update[1];
            int val = update[2];
            df.increment(i, j, val);
        }
        return df.result();
    }

    /**
     * 1109
     *
     * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
     *
     * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
     *
     * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
     * 示例一：
     * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
     * 输出：[10,55,45,25,25]
     * 解释：
     * 航班编号        1   2   3   4   5
     * 预订记录 1 ：   10  10
     * 预订记录 2 ：       20  20
     * 预订记录 3 ：       25  25  25  25
     * 总座位数：      10  55  45  25  25
     * 因此，answer = [10,55,45,25,25]
     * 示例二：
     * 输入：bookings = [[1,2,10],[2,2,15]], n = 2
     * 输出：[10,25]
     * 解释：
     * 航班编号        1   2
     * 预订记录 1 ：   10  10
     * 预订记录 2 ：       15
     * 总座位数：      10  25
     * 因此，answer = [10,25]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int[] copyFlightBookings(int[][] bookings, int n) {
        // nums 初始化为全 0
        int[] nums = new int[n];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] booking : bookings) {
            //转成数组索引要减一
            int i = booking[0] - 1;
            int j = booking[1] - 1;
            int val = booking[2];
            //对区间 nums [i..j] 增加 val
            df.increment(i, j, val);
        }
        //返回最终的结果数组
        return df.result();
    }

    /**
     *
     * 1094
     * 假设你是一位顺风车司机，车上最初有 capacity 个空座位可以用来载客。由于道路的限制，车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向，你可以将其想象为一个向量）。
     *
     * 这儿有一份乘客行程计划表 trips[][]，其中 trips[i] = [num_passengers, start_location, end_location] 包含了第 i 组乘客的行程信息：
     *
     *     必须接送的乘客数量；
     *     乘客的上车地点；
     *     以及乘客的下车地点。
     *
     * 这些给出的地点位置是从你的 初始 出发位置向前行驶到这些地点所需的距离（它们一定在你的行驶方向上）。
     *
     * 请你根据给出的行程计划表和车子的座位数，来判断你的车是否可以顺利完成接送所有乘客的任务（当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false）。
     *
     *
     *
     * 示例 1：
     *
     * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
     * 输出：false
     *
     * 示例 2：
     *
     * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
     * 输出：true
     *
     * 示例 3：
     *
     * 输入：trips = [[2,1,5],[3,5,7]], capacity = 3
     * 输出：true
     *
     * 示例 4：
     *
     * 输入：trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
     * 输出：true
     *
     *
     *
     * 提示：
     *
     *     你可以假设乘客会自觉遵守 “先下后上” 的良好素质
     *     trips.length <= 1000
     *     trips[i].length == 3
     *     1 <= trips[i][0] <= 100
     *     0 <= trips[i][1] < trips[i][2] <= 1000
     *     1 <= capacity <= 100000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/car-pooling
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    boolean carPooling(int [][] trips,int capacity){
        //最多有 1000 个车站
        int[] nums = new int[1001];
        // 构造差分数组
        Difference df = new Difference(nums);

        for (int[] trip : trips) {
            // 乘客数量
            int val = trip[0];
            // 第 trip[1] 站乘客上车
            int i = trip[1];
            // 第 trip[2] 站乘客已经下车
            // 即乘客在车上的区间是[ trip[1] , trip[2] - 1 ]
            int j = trip[2] - 1;
            // 进行区间操作
            df.increment(i, j, val);
        }
            int[] res = df.result();

            // 客车自始至终都不应该超载
            for (int k = 0; k < res.length; k++) {
                if (capacity < res[k]) {
                    return false;
                }
            }

        return true;
    }

    public static void main(String[] args) {
        RangeAddition rangeAddition = new RangeAddition();
//        int length = 5;
//        int [][] updates = new int[][]{{1,3,2},{2,4,3},{0,2,-2}};
//        int[] modifiedArray = rangeAddition.getModifiedArray(5, updates);
//        System.out.println(Arrays.toString(modifiedArray));
//        =================================================
//        int[][] bookings = new int[][]{{1,2,10},{2,3,20},{2,5,25}};
//        int n = 5;
//        int[] flightBookings = rangeAddition.copyFlightBookings(bookings, 5);
//        System.out.println(Arrays.toString(flightBookings));
//        ==================================================
        int[][] trips = new int[][]{{2,1,5},{3,3,7}};
        int capacity = 4;
        boolean b = rangeAddition.carPooling(trips, capacity);
        System.out.println(b);
    }
}