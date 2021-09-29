动态规划:0-1背包问题变体（leetcode416分割等和子集)

![图片](https://mmbiz.qpic.cn/sz_mmbiz_png/gibkIz0MVqdH4BiajD8HeXJSPSVviccPkjLAJxmP9Kto2Uqwjtv0AOYx9brfPaeSicxYPpjFzKarQygiaKGHYyV7F4w/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

思路：数组求和sum,得到sum/2，问题变成给一个可装载重量为sum/2的背包和N个物品，每个物品的重量为nums[i]。是否存在一种装法，能够恰好将背包装满？

dp数组：`dp[i][j] = x`表示，对于前`i`个物品，当前背包的容量为`j`时，若`x`为`true`，则说明可以恰好将背包装满，若`x`为`false`，则说明不能恰好将背包装满



```java
    public static boolean canPartition(int[] nums){
        int sum=0;
        for (int num:nums) sum+=num;
        sum = sum/2;
        if(sum%2 !=0) return false;
        int n = nums.length;
        //dp数组：`dp[i][j] = x`表示，对于前`i`个物品，当前背包的容量为`j`时，
        // 若`x`为`true`，则说明可以恰好将背包装满
        // 若`x`为`false`，则说明不能恰好将背包装满
        boolean[][] dp = new boolean[n][sum+1];
        for (int i=0;i<=n;i++)
            dp[i][0] = true;
        for (int i=1;i<=n;i++){
            for (int j=1;j<=sum;j++){
                if (j-nums[i-1]<0)
                    dp[i][j] = dp[i-1][j];
                else {
                    dp[i][j] = dp[i-1][j] | dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][sum];
    }
```

