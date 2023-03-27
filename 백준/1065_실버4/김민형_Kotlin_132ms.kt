import java.util.PriorityQueue
import kotlin.math.abs

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
    1. -, + 로 Split
    2. - Index 저장 (1 ~ N-1)
    3. - 위치부터 다음 - 가 나올 때 까지 숫자 더하기 (끝까지)
    4. 결과 값으로 대체 후 계산
     */
    fun solution() {
        val N = readLine()!!.toInt()
        var result = 99

        if (N in 1..99) {
            println(N)
            return
        }
        if (N in 99..110) {
            println(99)
            return
        }

        for (n in 111..N) {
            val str = n.toString() // 123
            val size = str.length
            val arr = Array(size) {""} // [1, 2, 3]
            for (i in 0 until size) {
                arr[i] = str[i].toString()
            }
            val sub = mutableListOf<Int>()
            for (i in 0 until size-1) {
                val first = arr[i].toInt()
                val second = arr[i+1].toInt()
                sub.add(first - second)
            }
            val first = sub.first()
            sub.removeAll(listOf(first))
            if (sub.isEmpty()) {
                result++
            }
        }

        println(result)
    }
}
