
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
    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(1, 0, -1, 0)

    var result = 0

    fun solution() {
        val n = readLine()!!.toInt()
        val map = Array(n) { IntArray(n) }
        val visited = Array(n) { BooleanArray(n) }
        val max = Array(n) { IntArray(n) }

        repeat(n) {
            map[it] = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
        }

        for (y in map.indices) {
            for (x in map[y].indices) {
                dfs(n, x, y, map, visited, max)
            }
        }

        println(result + 1)
    }

    private fun dfs(n: Int, x: Int, y: Int, map: Array<IntArray>, visited: Array<BooleanArray>, max: Array<IntArray>) {
        if (visited[y][x]) return
        
        visited[y][x] = true

        for (dir in 0 until 4) {
            val nx = x + dx[dir]
            val ny = y + dy[dir]

            if (nx !in map.indices || ny !in map.indices) continue

            if (map[ny][nx] > map[y][x]) {
                if (!visited[ny][nx]) {
                    dfs(n, nx, ny, map, visited, max)
                }
                val res = max[ny][nx] + 1
                result = result.coerceAtLeast(res)
                max[y][x] = max[y][x].coerceAtLeast(res)
            }
        }
    }
}
