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
    fun solution() {
        val T = readLine()!!.toInt()
        val P = LongArray(101) {0}
        P[1] = 1
        P[2] = 1
        P[3] = 1
        P[4] = 2
        P[5] = 2
        for (n in 6..100) {
            P[n] = P[n-1] + P[n-5]
        }
        repeat(T) {
            val N = readLine()!!.toInt()
            println(P[N])
        }
    }
}
