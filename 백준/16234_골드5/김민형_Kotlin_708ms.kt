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
    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(1, 0, -1, 0)
    var sum = 0
    var count = 0
    var result = 0
    var go = 0
    fun solution() {
        val (N, L, R) = readLine()!!.split(' ').map { it.toInt() }
        val map = Array(N) { IntArray(N) {-1} }
        repeat(N) { n ->
            map[n] = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
        }
        while (true) {
            go = 0
            result++
            val visited = Array(N) { BooleanArray(N) {false} }
            for (i in 0 until N) {
                for (j in 0 until N) {
                    val union = mutableListOf<Pair<Int, Int>>()
                    sum = 0
                    count = 0
                    if (!visited[i][j])
                        dfs(i, j, map, visited, union, L, R)
                    if (union.size > 1) {
                        go = 1
                        union.forEach {
                            val (i, j) = it
                            map[i][j] = sum/count
                        }
                    }
                }
            }
            if (go == 0)  {
                result--
                break
            }
        }
        println(result)
    }
    fun dfs(i: Int, j: Int, map: Array<IntArray>, visited: Array<BooleanArray>, union: MutableList<Pair<Int, Int>>, L: Int, R: Int) {
        visited[i][j] = true
        union.add(Pair(i, j))
        sum += map[i][j]
        count++

        for (n in 0 until 4) {
            val nx = i + dx[n]
            val ny = j + dy[n]

            if (nx !in map.indices || ny !in map.indices) continue

            if (visited[nx][ny]) continue

            val visit = abs(map[nx][ny] - map[i][j]) in L..R
            if (visit) {
                dfs(nx, ny, map, visited, union, L, R)
            }
        }
    }
}
