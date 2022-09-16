import java.util.*

fun main() {
    val solution = Solution()

    solution.solution()
}

class Solution {
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    fun solution() {
        val (R, C) = readLine()!!.split(' ')
        val graph = Array(R.toInt()) { Array(C.toInt()) { '.' } }
        val visit = Array(R.toInt()) { Array(C.toInt()) { false } }
        var k = 0
        var v = 0

        repeat(R.toInt()) {
            graph[it] = readLine()!!.toCharArray().toTypedArray()
        }

        for (y in 0 until R.toInt()) {
            for (x in 0 until C.toInt()) {
                if (graph[y][x] == 'v' || graph[y][x] == 'k') {
                    val result = bfs(x, y, graph, visit, R.toInt(), C.toInt())

                    if (result.first > result.second) {
                        k += result.first
                    } else {
                        v += result.second
                    }
                }
            }
        }

        println("$k $v")
        }

        private fun bfs(x: Int, y: Int, graph: Array<Array<Char>>, visit: Array<Array<Boolean>>, R: Int, C: Int): Pair<Int, Int> {
            val queue = LinkedList<Pair<Int, Int>>()
            var v = 0
            var k = 0

            queue.push(Pair(x, y))

            while (queue.isNotEmpty()) {
                val now = queue.poll()

                if (visit[now.second][now.first])
                    continue

                if (now.first !in 0 until C || now.second !in 0 until R)
                    continue

                if (graph[now.second][now.first] == '#')
                    continue

                visit[now.second][now.first] = true

                if (graph[now.second][now.first] == 'v')
                    v++

                if (graph[now.second][now.first] == 'k')
                    k++

                for (dir in 0 until 4) {
                    val nx = now.first + dx[dir]
                    val ny = now.second + dy[dir]

                    queue.offer(Pair(nx, ny))
                }
            }

            return Pair(k, v)
        }
}
