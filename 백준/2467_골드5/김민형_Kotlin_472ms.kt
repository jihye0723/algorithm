import java.math.BigInteger
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
    1. 블록 완전 탐색
    2. 값이 존재하면 방향으로 이동
    3. 모든 값이 이동을 마치면 방향의 끝부터 같은 숫자 합치기
    4. 5번 반복
     */
    val dx = listOf(1, 0, 1)
    val dy = listOf(0, 1, 1)
    fun solution() {
        val N = readLine()!!.toInt()
        val arr = readLine()!!.split(' ').map { it.toInt() }
        var min = Int.MAX_VALUE
        var result = Pair(0, 0)

        var left = 0
        var right = N-1
        while (left < right) {
            val sub = arr[left] + arr[right]
            if (abs(sub) <= min) {
                min = abs(sub)
                result = Pair(arr[left], arr[right])
            }
            if (sub > 0) right--
            else left++
        }

        println("${result.first} ${result.second}")
    }
}
