

fun main() {
    val solution = Solution()

    solution.solution()
}
/*
4
1 5 6 7
 */
class Solution {
    fun solution() {
        val N = readLine()!!.toInt()
        val price = Array(N+1) {0}
        val dp = Array(N + 1) {0}

        readLine()!!.split(' ').forEachIndexed { index, s -> price[index+1] = s.toInt() }

        dp[1] = price[1]

        /**
         * 1. dp[i] = for (1..i) i/index * dp[] + i%index * dp[] 의 min
         */
        for (i in 2..N) {
            dp[i] = min(dp, price, i)
        }

        println(dp[N])
    }

    private fun min(dp: Array<Int>, price: Array<Int>, i: Int): Int {
        var min = price[i] // 4 7

        for (index in 1 until i) {
            val n = i / index // 4/3 = 1
            val m = i % index // 5%3 = 2

            min = min.coerceAtMost(n * dp[index] + dp[m])
        }

        return min
    }
}
