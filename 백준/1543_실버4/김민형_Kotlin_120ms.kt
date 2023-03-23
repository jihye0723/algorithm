
import java.util.PriorityQueue
import kotlin.math.*

fun main() {
    val solution = Solution()

    solution.solution()
}
/*
3
1 3
2 4
3 5
 */
class Solution {
    /*
    1. N 에서 K 범위를 특정한다.
    2. 가능한 K 에 대해 셔플한다.
    3. 결과를 답과 비교한다.
     */
    fun solution() {
        var input = readLine()!!
        val word = readLine()!!
        var result = 0

        while (input.contains(word)) {
            val index = input.indexOf(word) + word.length
            input = input.substring(index)
            result++
        }

        println(result)
    }

    fun shuffle(k: Int, initial: List<Int>): List<Int> {
        if (k < 0) return initial

        val size = Math.pow(2.0, k.toDouble()).toInt() // 8
        val tmp = initial.subList(initial.size-size, initial.size) // [2, 3, 4, 5]
        val left = initial.subList(0, initial.size-size) // [1]

        return shuffle(k-1, tmp) + left
    }
}
