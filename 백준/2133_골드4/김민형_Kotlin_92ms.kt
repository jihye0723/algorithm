import java.util.*

fun main() {
    val solution = Solution()

    solution.solution()
}

class Solution {

    fun solution() {
        val N = readLine()!!.toInt()

        if (N % 2 != 0) {
            println(0)
        } else {
            val dp = Array(N+1) { 0 }
    
            dp[2] = 3
    
            if (N > 2) {
                for (i in 4..N step 2) {
                    var temp = 0
                    for (j in 4..i-2 step 2) {
                        temp += dp[i-j] * 2
                    }
                    dp[i] = dp[2] * dp[i-2] + temp + 2
                }
            }
    
            println(dp[N])
        }
    }
}
