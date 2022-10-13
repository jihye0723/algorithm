import java.util.*
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.system.exitProcess

fun main() {
    val solution = Solution()

    solution.solution()
}
/*
7
3 10
5 20
1 10
1 20
2 15
4 40
2 200
 */
class Solution {
    /*

     */
    fun solution() {
        val N = readLine()!!.toInt()
        val consultList = mutableMapOf<Int, List<Int>>()
        val payList = IntArray(N+1)
        val dp = IntArray(N+1)
        repeat(N) { index ->
            val input = readLine()!!.split(' ').map { it.toInt() }
            val endDay = index + input[0]
            payList[index+1] = input[1]
            val tmp = consultList.getOrDefault(endDay, mutableListOf())
            consultList[endDay] = tmp.toMutableList().plus(index+1)
        }

        for (i in 1..N) {
            dp[i] = dp[i-1]
            if (consultList.containsKey(i)) {
                consultList[i]!!.forEach {
                    dp[i] = dp[i].coerceAtLeast(dp[it-1] + payList[it])
                }
            }
        }

        println(dp[N])
    }

}
