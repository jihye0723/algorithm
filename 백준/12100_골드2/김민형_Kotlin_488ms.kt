
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
    val dirs = listOf("▼", "▶", "▲" ,"◀")
    var result = 0
    fun solution() {
        val N = readLine()!!.toInt()
        val map = Array(N) { IntArray(N) {0} }
        repeat(N) {
            map[it] = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
        }

        map.forEach {
            it.forEach { result = result.coerceAtLeast(it) }
        }

        for (dir in 0..3) {
            round(map, dir, 1, N)
        }

        println(result)
    }

    fun round(map: Array<IntArray>, dir: Int, depth: Int, N: Int) {
        if (depth > 5) return
        /*
        2 2 2
        4 4 4
        8 8 8
         */
        when (dir) {
            0 -> {
                val newMap = Array(N) { IntArray(N) {0} }
                map.forEachIndexed { index, ints -> newMap[index] = ints.copyOf() }

//                newMap.forEach { println("${dirs[dir]} $depth 번째 ${it.toList()} 전") }
//                println()

                // Down
                val visited = Array(N) { BooleanArray(N) {false} }
//                visited.forEach { println(it.toList()) }

                for (x in 0 until N) {
                    for (y in N-1 downTo 0) {
                        move(x, y, newMap, dir, visited)
                    }
                }

//                newMap.forEach { println("${dirs[dir]} $depth 번째 ${it.toList()} 후") }
//                visited.forEach { println(it.toList()) }
//                println()

                for (i in 0..3) {
                    round(newMap, i, depth+1, N)
                }
            }
            1 -> {
                val newMap = Array(N) { IntArray(N) {0} }
                map.forEachIndexed { index, ints -> newMap[index] = ints.copyOf() }

//                newMap.forEach { println("${dirs[dir]} $depth 번째 ${it.toList()} 전") }
//                println()

                // Right
                val visited = Array(N) { BooleanArray(N) {false} }
                for (y in 0 until N) {
                    for (x in N-1 downTo 0) {
                        move(x, y, newMap, dir, visited)
                    }
                }

//                newMap.forEach { println("${dirs[dir]} $depth 번째 ${it.toList()} 후") }
//                println()

                for (i in 0..3) {
                    round(newMap, i, depth+1, N)
                }
            }
            2 -> {
                val newMap = Array(N) { IntArray(N) {0} }
                map.forEachIndexed { index, ints -> newMap[index] = ints.copyOf() }

//                newMap.forEach { println("${dirs[dir]} $depth 번째 ${it.toList()} 전") }
//                println()

                // Up
                val visited = Array(N) { BooleanArray(N) {false} }
                for (x in 0 until N) {
                    for (y in 0 until N) {
                        move(x, y, newMap, dir, visited)
                    }
                }

//                newMap.forEach { println("${dirs[dir]} $depth 번째 ${it.toList()} 후") }
//                println()

                for (i in 0..3) {
                    round(newMap, i, depth+1, N)
                }
            }
            3 -> {
                val newMap = Array(N) { IntArray(N) {0} }
                map.forEachIndexed { index, ints -> newMap[index] = ints.copyOf() }

//                newMap.forEach { println("${dirs[dir]} $depth 번째 ${it.toList()} 전") }
//                println()

                // Left
                val visited = Array(N) { BooleanArray(N) {false} }
                for (y in 0 until N) {
                    for (x in 0 until N) {
                        move(x, y, newMap, dir, visited)
                    }
                }

//                newMap.forEach { println("${dirs[dir]} $depth 번째 ${it.toList()} 후") }
//                println()

                for (i in 0..3) {
                    round(newMap, i, depth+1, N)
                }
            }
            else -> Unit
        }
    }

    fun move(x: Int, y: Int, map: Array<IntArray>, dir: Int, visited: Array<BooleanArray>) {
        if (map[y][x] == 0) return

        val nx = x + dx[dir]
        val ny = y + dy[dir]

        if (nx !in map.indices || ny !in map.indices) return

        if (map[ny][nx] == map[y][x] && !visited[ny][nx] && !visited[y][x]) {
            map[ny][nx] *= 2
            result = result.coerceAtLeast(map[ny][nx])
            visited[ny][nx] = true
            map[y][x] = 0
        } else if (map[ny][nx] == 0) {
            map[ny][nx] = map[y][x]
            map[y][x] = 0
        }

        move(nx, ny, map, dir, visited)
    }
}
