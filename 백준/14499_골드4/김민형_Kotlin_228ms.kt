
import java.math.BigInteger
import java.util.LinkedList
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
    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, -1, 1)

    fun solution() {
        val input = readLine()!!.split(' ').map { it.toInt() }
        val (N, M) = input.subList(0, 2)
        var (y, x, K) = input.subList(2, 5)
        val map = Array(N) { IntArray(M) }
        repeat(N) {
            map[it] = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
        }
        val cmd = readLine()!!.split(' ').map { it.toInt() }
        val dice = arrayOf(0, 0, 0, 0, 0, 0)

        cmd.forEach { dir ->
            if (x + dx[dir-1] !in 0 until M || y + dy[dir-1] !in 0 until N) return@forEach

            x = x + dx[dir-1]
            y = y + dy[dir-1]

            when (dir) {
                1 -> { // 우
                    val tmp = dice[2]
                    dice[2] = dice[0]
                    dice[0] = dice[3]
                    dice[3] = dice[5]
                    dice[5] = tmp
                }
                2 -> { // 좌
                    val tmp = dice[3]
                    dice[3] = dice[0]
                    dice[0] = dice[2]
                    dice[2] = dice[5]
                    dice[5] = tmp
                }
                3 -> { // 상
                    val tmp = dice[1]
                    dice[1] = dice[0]
                    dice[0] = dice[4]
                    dice[4] = dice[5]
                    dice[5] = tmp
                }
                4 -> { // 하
                    val tmp = dice[4]
                    dice[4] = dice[0]
                    dice[0] = dice[1]
                    dice[1] = dice[5]
                    dice[5] = tmp
                }
            }

            if (map[y][x] == 0) {
                map[y][x] = dice[5]
            } else {
                dice[5] = map[y][x]
                map[y][x] = 0
            }

            println(dice[0])
        }
    }
}
