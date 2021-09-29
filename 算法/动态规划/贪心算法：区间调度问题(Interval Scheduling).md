贪心算法：跳跃游戏

![图片](https://mmbiz.qpic.cn/sz_mmbiz_png/gibkIz0MVqdHfjCVlvAoZxfsbpI4AQDGcRJiabZib35lvUP5POs7K05dBkwTqQV6Gyr2Dop8ssvl800WCpIE8jjtw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

关键点：求最大距离是否能大于最后一个位置。

```java
bool canJump(vector<int>& nums) {
    int n = nums.size();
    int farthest = 0;
    for (int i = 0; i < n - 1; i++) {
        // 不断计算能跳到的最远距离
        farthest = max(farthest, i + nums[i]);
        // 可能碰到了 0，卡住跳不动了
        if (farthest <= i) return false;
    }
    return farthest >= n - 1;
}
```

跳跃游戏2题目：leetcode45

![图片](https://mmbiz.qpic.cn/sz_mmbiz_png/gibkIz0MVqdHfjCVlvAoZxfsbpI4AQDGcCvjMic8xW7PqzaxCVKpa7eABhgDLAAHrRk4ekqicvGkswsULwVxrXbxw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

贪心算法：每次选择一个能达到最大范围的值，在下面的范围中再进行选择。

```java
    public static int tx(int[] nums,int p){
        int n = nums.length;
        int end = 0,farthest = 0;
        int jumps = 0;
        for (int i=0;i<n-1;i++){
            farthest = Math.max(nums[i]+i,farthest);
            if (end == i){
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }
```

![图片](https://mmbiz.qpic.cn/sz_mmbiz_jpg/gibkIz0MVqdHfjCVlvAoZxfsbpI4AQDGcLwv0HnFpV6nYI3eLicDMGZkXd5ia0qqnZNJrE36uXB3icsOPDSP5tZHGQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)