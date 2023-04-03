
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
    val keyList = listOf('a', 'b', 'c', 'd', 'e', 'f')
    val door = listOf('A', 'B', 'C', 'D', 'E', 'F')
    var result = 0

    data class MinSik(
        val x: Int,
        val y: Int,
        val step: Int,
        val keys: Int
    ) : Comparable<MinSik> {
        override fun compareTo(other: MinSik): Int {
            return when {
                step > other.step -> 1
                step < other.step -> -1
                step == other.step -> 0
                else -> Int.MAX_VALUE
            }
        }
    }

    fun solution() {
        val (N, M) = readLine()!!.split(' ').map { it.toInt() }
        val map = Array(N) { CharArray(M) }
        repeat(N) {
            map[it] = readLine()!!.toCharArray()
        }
        val queue = PriorityQueue<MinSik>()
        val visited = Array(N) { Array(M) { BooleanArray(64) { false } } }

        for (y in map.indices) {
            for (x in map[y].indices) {
                if (map[y][x] == '0')
                    queue.offer(MinSik(x, y, 0, 0))
            }
        }

        while (queue.isNotEmpty()) {
            val minsik = queue.poll()

//            println(minsik)

            if (map[minsik.y][minsik.x] == '1') {
                result = minsik.step
                break
            }

            for (dir in 0 until 4) {
                val nx = minsik.x + dx[dir]
                val ny = minsik.y + dy[dir]

                if (nx !in (0 until M) || ny !in (0 until N)) continue
                if (map[ny][nx] == '#') continue
//                println(visited[ny][nx][minsik.keys])
                if (visited[ny][nx][minsik.keys]) continue

                if (keyList.contains(map[ny][nx])) {
                    val key = 1 shl 5 - keyList.indexOf(map[ny][nx])
//                    println(key)
                    val newKey = minsik.keys or key
//                    println(newKey)
                    visited[ny][nx][newKey] = true
                    queue.offer(MinSik(nx, ny, minsik.step+1, newKey))
                    continue
                }

                if (door.contains(map[ny][nx])) {
                    val doorToKey = map[ny][nx] + 32
//                    println(doorToKey)
                    val key = 1 shl 5 - keyList.indexOf(doorToKey)
//                    println(key)
                    if (minsik.keys and key == key){
                        visited[ny][nx][minsik.keys] = true
                        queue.offer(MinSik(nx, ny, minsik.step+1, minsik.keys))
                    }
                    continue
                }

                visited[ny][nx][minsik.keys] = true
                queue.offer(MinSik(nx, ny, minsik.step+1, minsik.keys))
            }
        }

        if (result == 0)
            println(-1)
        else
            println(result)
    }
}
