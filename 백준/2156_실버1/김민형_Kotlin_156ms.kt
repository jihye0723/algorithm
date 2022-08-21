

fun main() {
    val solution = Solution()

    solution.solution()
}
/*
6
6
10
13
9
8
1
 */
class Solution {
    fun solution() {
        val N = readLine()!!.toInt()
        val juice = Array(N+1) {0}
        val dp = Array(N + 1) {0}

        repeat(N) { index ->
            juice[index+1] = readLine()!!.toInt()
        }

        dp[1] = juice[1]
        if (N > 1) {
            dp[2] = dp[1] + juice[2]
        }
        if (N > 2) {
            dp[3] = maxOf(juice[3] + juice[2], maxOf(dp[2], juice[3] + dp[1]))

            for (index in 4..N) {
                dp[index] = maxOf(
                    juice[index] + juice[index - 1] + dp[index - 3],
                    maxOf(dp[index - 1], juice[index] + dp[index - 2])
                )
            }
        }

        println(dp[N])
    }
}
