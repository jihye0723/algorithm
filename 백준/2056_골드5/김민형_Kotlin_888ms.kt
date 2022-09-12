import java.util.*

fun main() {
    val solution = Solution()

    solution.solution()
}

/**
 *
 */
class Solution {

    fun solution() {
        val N = readLine()!!.toInt()
        val dp = Array(N+1) { 0 }
        val time = Array(N+1) { 0 }
        val pre = Array(N+1) { mutableListOf<Int>() }
        var result = 0

        repeat(N) {
            val split = readLine()!!.split(' ')
            val temp = mutableListOf<Int>()

            time[it + 1] = split[0].toInt()
            if (split[1].toInt() != 0) {
                for (i in 2 until split.size) {
                    temp.add(split[i].toInt())
                }
                pre[it + 1] = temp
            }
        }

        dp[1] = time[1]
        result = dp[1]

        for (i in 2..N) {
            if (pre[i].size == 1) {
                dp[i] = dp[pre[i][0]] + time[i]
            } else if (pre[i].size > 1) {
                var max = 0
                for (j in pre[i].indices) {
                    if (max < dp[pre[i][j]]) {
                        max = dp[pre[i][j]]
                    }
                }
                dp[i] = max + time[i]
            } else {
                dp[i] = time[i]
            }
            result = result.coerceAtLeast(dp[i])
        }

        println(result)
    }
}
