import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val solution = Solution()

    solution.solution()
}

class Solution {

    fun solution() {
        val (N, r, c) = readLine()!!.split(' ')
        val district = IntArray(N.toInt()) {1} // 1, 2, 4
        val sum = IntArray(N.toInt()) { 1 }
        val result = mutableListOf<Int>() // 3, 3, 3
        var nowR = r.toInt()
        var nowC = c.toInt()

        for (time in 1 until N.toInt()) {
            sum[time] = (4.toDouble().pow(time) * 1).toInt()
            district[time] = sqrt(4.toDouble().pow(time) * 1).toInt()
        }

        // 2(4) - 1(2) - 0(1)
        for (i in N.toInt()-1 downTo 0) {
            if (nowR < district[i] && nowC < district[i]) {
                result.add(0)
            } else if (nowR < district[i] && nowC >= district[i]) {
                result.add(1)
                nowC -= district[i]
            } else if (nowR >= district[i] && nowC < district[i]) {
                result.add(2)
                nowR -= district[i]
            } else if (nowR >= district[i] && nowC >= district[i]) {
                result.add(3)
                nowR -= district[i]
                nowC -= district[i]
            }
        }

        var answer = 0
        for (i in 0 until N.toInt()) {
            answer += result[i] * sum[sum.lastIndex-i]
        }

        println(answer)
    }
}
