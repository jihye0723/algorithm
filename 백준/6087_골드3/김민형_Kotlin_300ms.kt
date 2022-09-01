import java.util.*

fun main() {
    val solution = Solution()

    solution.solution()
}
/*
7 8
.......
......C
......*
*****.*
....*..
....*..
.C..*..
.......
 */
/**
 * 완전 탐색
 * C를 찾아서 C 위치에서 탐색 시작
 * 나머지 C 를 찾을 때 까지 완전 탐색을 한다.
 * 탐색 시, 진행 방향과 현재까지 방향 전환을 한 횟수를 전달한다.
 * 진행 방향이 바뀐다면 방향 전환 횟수를 1 증가
 * C 에 도달했을 때 방향 전환 횟수를 비교해 최솟값을 저장
 */
const val INF = 123456789
class Solution {
    var dx = intArrayOf(0, 1, 0, -1)
    var dy = intArrayOf(1, 0, -1, 0)
    var result = 123456789
    fun solution() {
        val input = readLine()!!.split(' ')
        val W = input[0].toInt()
        val H = input[1].toInt()
        val graph = Array(H) { Array(W) { '.' } }
        var visit = Array(H) { Array(W) { false } }
        val queue = PriorityQueue<Node>()
        var start = Pair(0, 0)
        repeat(H) {
            graph[it] = readLine()!!.toCharArray().toTypedArray()
        }

        loop@ for (y in 0 until H) {
            for (x in 0 until W) {
                if (graph[y][x] == 'C') {
                    start = Pair(x, y)
                    break@loop
                }
            }
        }

        queue.offer(Node(start.first, start.second, -1, -1))

        while (queue.isNotEmpty()) {
            val now = queue.poll()

            if (now.x !in 0 until W || now.y !in 0 until H) continue
            if (graph[now.y][now.x] == '*') continue
            if (visit[now.y][now.x]) continue
            if (graph[now.y][now.x] == 'C' && now.count != -1) {
                result = result.coerceAtMost(now.count)
                continue
            }

            visit[now.y][now.x] = true

            for (dir in 0 until 4) {
                val nx = now.x + dx[dir]
                val ny = now.y + dy[dir]

                val count = if (now.dir != dir) now.count + 1 else now.count

                queue.offer(Node(nx, ny, count, dir))
            }
        }

        dx = intArrayOf(1, 0, -1, 0)
        dy = intArrayOf(0, -1, 0, 1)
        visit = Array(H) { Array(W) { false } }

        queue.offer(Node(start.first, start.second, -1, -1))

        while (queue.isNotEmpty()) {
            val now = queue.poll()

            if (now.x !in 0 until W || now.y !in 0 until H) continue
            if (graph[now.y][now.x] == '*') continue
            if (visit[now.y][now.x]) continue
            if (graph[now.y][now.x] == 'C' && now.count != -1) {
                result = result.coerceAtMost(now.count)
                continue
            }

            visit[now.y][now.x] = true

            for (dir in 0 until 4) {
                val nx = now.x + dx[dir]
                val ny = now.y + dy[dir]

                val count = if (now.dir != dir) now.count + 1 else now.count

                queue.offer(Node(nx, ny, count, dir))
            }
        }

        dx = intArrayOf(0, -1, 0, 1)
        dy = intArrayOf(1, 0, 1, 0)
        visit = Array(H) { Array(W) { false } }

        queue.offer(Node(start.first, start.second, -1, -1))

        while (queue.isNotEmpty()) {
            val now = queue.poll()

            if (now.x !in 0 until W || now.y !in 0 until H) continue
            if (graph[now.y][now.x] == '*') continue
            if (visit[now.y][now.x]) continue
            if (graph[now.y][now.x] == 'C' && now.count != -1) {
                result = result.coerceAtMost(now.count)
                continue
            }

            visit[now.y][now.x] = true

            for (dir in 0 until 4) {
                val nx = now.x + dx[dir]
                val ny = now.y + dy[dir]

                val count = if (now.dir != dir) now.count + 1 else now.count

                queue.offer(Node(nx, ny, count, dir))
            }
        }

        dx = intArrayOf(-1, 0, 1, 0)
        dy = intArrayOf(0, 1, 0, -1)
        visit = Array(H) { Array(W) { false } }

        queue.offer(Node(start.first, start.second, -1, -1))

        while (queue.isNotEmpty()) {
            val now = queue.poll()

            if (now.x !in 0 until W || now.y !in 0 until H) continue
            if (graph[now.y][now.x] == '*') continue
            if (visit[now.y][now.x]) continue
            if (graph[now.y][now.x] == 'C' && now.count != -1) {
                result = result.coerceAtMost(now.count)
                continue
            }

            visit[now.y][now.x] = true

            for (dir in 0 until 4) {
                val nx = now.x + dx[dir]
                val ny = now.y + dy[dir]

                val count = if (now.dir != dir) now.count + 1 else now.count

                queue.offer(Node(nx, ny, count, dir))
            }
        }

        println(result)
    }
}

data class Node(
    val x: Int,
    val y: Int,
    val count: Int,
    val dir: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return when {
            count > other.count -> 1
            count < other.count -> -1
            count == other.count -> 0
            else -> INF
        }
    }
}

/*
println("[${now.y}, ${now.x}] : ${now.count} ${when(now.dir) {
                0 -> "아래"
                1 -> "오른쪽"
                2 -> "위"
                3 -> "왼쪽"
                else -> ""
            }
            }")
 */
