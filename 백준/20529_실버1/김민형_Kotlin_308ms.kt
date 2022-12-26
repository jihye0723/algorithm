import java.lang.Math.abs
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
3
3
ENTJ INTP ESFJ
4
ESFP ESFP ESFP ESFP
5
INFP INFP ESTP ESTJ ISTJ
 */
class Solution {
    /*

     */
    fun solution() {
        val T = readLine()!!.toInt()
        repeat(T) {
            val N = readLine()!!.toInt()
            val input = readLine()!!.split(' ') // [INFP, INFP, ESTP, ESTJ, ISTJ]

            solve(N, input)
        }
    }

    fun solve(N: Int, input: List<String>) {
        if (N > 48) {
            println(0)
            return
        }

        val times = (N * (N-1) * (N-2)) / 6
        val results = IntArray(times) { 0 }
        var first = 0
        var second = 1
        var third = 2
        var index = 0

        while (true) {
            if (third >= N) {
                second++
                third = second + 1
            }
            if (second + 1 == N || second >= N) {
                first++
                second = first + 1
                third = second + 1
            }
            if (first + 2 == N) break

            val one = calc(input[first], input[second])
            val two = calc(input[second], input[third])
            val three = calc(input[third], input[first])

            results[index++] = one + two + three

            third++
        }

        println(results.minOrNull())
    }

    fun calc(s1: String, s2: String): Int {
        var result = 0
        s1.forEachIndexed { index, c ->
            result += if (c == s2[index]) 0 else 1
        }
        return result
    }
}
