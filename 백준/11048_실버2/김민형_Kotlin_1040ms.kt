
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
        val (N, M) = readLine()!!.split(' ').map { it.toInt() }
        val map = Array(N) { IntArray(M) {0} }
        val dp = Array(N) { IntArray(M) {0} }
        repeat(N) {
            map[it] = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
        }

        dp[0][0] = map[0][0]

        for (i in 1 until M) {
            dp[0][i] = dp[0][i-1] + map[0][i]
        }

        for (i in 1 until N) {
            dp[i][0] = dp[i-1][0] + map[i][0]
        }

        for (i in 1 until N) {
            for (j in 1 until M) {
                dp[i][j] = maxOf(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + map[i][j]
            }
        }

        println(dp[N-1][M-1])
    }
}
