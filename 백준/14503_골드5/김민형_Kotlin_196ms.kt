
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
    data class Cleaner(
        val x: Int,
        val y: Int,
        val dir: Int
    )

    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(-1, 0, 1, 0)

    var result = 0

    fun solution() {
        val (N, M) = readLine()!!.split(' ').map { it.toInt() }
        val (y, x, dir) = readLine()!!.split(' ').map { it.toInt() }
        val map = Array(N) { IntArray(M) }
        repeat(N) { n ->
            map[n] = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
        }
        val queue = LinkedList<Cleaner>()

        queue.offer(Cleaner(x, y, dir))

        loop@ while (queue.isNotEmpty()) {
            val cleaner = queue.poll()
            val x = cleaner.x
            val y = cleaner.y
            val dir = cleaner.dir

            if (map[y][x] == 0) {
                map[y][x] = Int.MAX_VALUE
                result++
            }
                if ((0 until 4).map { map[y + dy[it]][x + dx[it]] }.any { it == 0 }) {
                    for (next in 3 downTo 0) {
                        val nDir = (dir + next) % 4
                        val nx = x + dx[nDir]
                        val ny = y + dy[nDir]

                        if (map[ny][nx] == 0) {
                            queue.offer(Cleaner(nx, ny, nDir))
                            continue@loop
                        }
                    }
                } else {
                    when (dir) {
                        0 -> {
                            if (map[y+1][x] != 1) {
                                queue.offer(Cleaner(x, y+1, dir))
                                continue
                            } else {
                                break@loop
                            }
                        }
                        1 -> {
                            if (map[y][x - 1] != 1) {
                                queue.offer(Cleaner(x - 1, y, dir))
                                continue
                            } else {
                                break@loop
                            }
                        }
                        2 -> {
                            if (map[y-1][x] != 1) {
                                queue.offer(Cleaner(x, y-1, dir))
                                continue
                            } else {
                                break@loop
                            }
                        }
                        3 -> {
                            if (map[y][x + 1] != 1) {
                                queue.offer(Cleaner(x + 1, y, dir))
                                continue
                            } else {
                                break@loop
                            }
                        }
                    }
                }
        }

        println(result)
    }
}
