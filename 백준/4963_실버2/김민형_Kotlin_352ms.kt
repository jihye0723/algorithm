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
    val dx = listOf(0, 1, 0, -1, 1, -1, 1, -1)
    val dy = listOf(1, 0, -1, 0, 1, 1, -1, -1)
    fun solution() {
        val result = mutableListOf<Int>()
        while (true) {
            val (w, h) = readLine()!!.split(' ').map { it.toInt() }
            if (w == 0 && h == 0) break
            val map = Array(h) { IntArray(w) {0} }
            repeat(h) {
                map[it] = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
            }
            val visited = Array(h) { BooleanArray(w) {false} }

            var count = 0
            for (i in 0 until h) {
                for (j in 0 until w) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        count++
                        dfs(i, j, map, visited)
                    }
                }
            }
            result.add(count)
        }
        result.forEach { println(it) }
    }

    fun dfs(h: Int, w: Int, map: Array<IntArray>, visited: Array<BooleanArray>) {
        visited[h][w] = true

        for (x in dx) {
            for (y in dy) {
                val nx = w + x
                val ny = h + y

                if (nx !in map[0].indices || ny !in map.indices) continue
                if (visited[ny][nx]) continue

                if (map[ny][nx] == 1) {
                    dfs(ny, nx, map, visited)
                }
            }
        }
    }
}
