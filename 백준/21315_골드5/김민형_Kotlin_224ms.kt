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
        val N = readLine()!!.toInt() // 5
        val answer = readLine()!!.split(' ').map { it.toInt() } // 1 3 5 4 2

        val kList = mutableListOf<Int>() // [2, 1]
        for (k in log(N.toDouble(), 2.0).toInt() downTo 1) {
            if (2.0.pow(k.toDouble()) < N)
                kList.add(k)
        }

        for (k1 in kList) {
            for (k2 in kList) {
                var initial = (1..N).toList()
                initial = shuffle(k1, initial)
                initial = shuffle(k2, initial)
                if (initial == answer) {
                    println("$k1 $k2")
                    return
                }
            }
        }
    }

    fun shuffle(k: Int, initial: List<Int>): List<Int> {
        if (k < 0) return initial

        val size = Math.pow(2.0, k.toDouble()).toInt() // 8
        val tmp = initial.subList(initial.size-size, initial.size) // [2, 3, 4, 5]
        val left = initial.subList(0, initial.size-size) // [1]

        return shuffle(k-1, tmp) + left
    }
}
