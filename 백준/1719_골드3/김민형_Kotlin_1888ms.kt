import java.util.*

fun main() {
    val solution = Solution()

    solution.solution()
}

/**
 *
 */

const val INF = Int.MAX_VALUE

class Solution {

    fun solution() {
        val (N, M) = readLine()!!.split(' ')
        val graph = Array(N.toInt() + 1) { arrayListOf<Node>() }

        repeat(M.toInt()) {
            val input = readLine()!!.split(' ')
            graph[input[0].toInt()].add(Node(input[1].toInt(), input[2].toInt()))
            graph[input[1].toInt()].add(Node(input[0].toInt(), input[2].toInt()))
        }

        repeat(N.toInt()) {
            val result = dijkstra(it + 1, N.toInt(), graph).toList()
            for (i in 1..N.toInt()) {
                if (result[i] == INF)
                    print("- ")
                else
                    print("${result[i]} ")
            }
            println()
        }
    }

    private fun dijkstra(start: Int, n: Int, graph: Array<ArrayList<Node>>): IntArray {
        val queue = PriorityQueue<Node>()
        val dist = IntArray(n+1) { INF }
        val visit = BooleanArray(n+1) { false }
        val route = Array(n+1) { mutableListOf<Int>() }
        val result = IntArray(n+1) { INF }

        dist[start] = 0
        queue.offer(Node(start, 0))

        while (queue.isNotEmpty()) {
            val node = queue.poll()

            if (visit[node.end]) continue
            visit[node.end] = true

            for (next in graph[node.end]) {
                if (dist[next.end] > next.cost + dist[node.end]) {
                    dist[next.end] = next.cost + dist[node.end]
                    queue.offer(Node(next.end, dist[next.end]))

                    val temp = mutableListOf<Int>()
                    if (route[node.end].isEmpty())
                        temp.add(node.end)
                    else {
                        for (i in route[node.end].indices) {
                            temp.add(route[node.end][i])
                        }
                        temp.add(node.end)
                    }
                    route[next.end] = temp
                }
            }
        }

        for (i in 1..n) {
            if (route[i].isEmpty())
                continue
            if (route[i].size == 1) {
                result[i] = i
            }
            else
                result[i] = route[i][1]
        }

        return result
    }
}

data class Node(val end: Int, val cost: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return when {
            cost > other.cost -> 1
            cost < other.cost -> -1
            cost == other.cost -> 0
            else -> INF
        }
    }
}

