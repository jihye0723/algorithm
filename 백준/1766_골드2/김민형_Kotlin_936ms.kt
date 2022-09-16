import java.util.*

fun main() {
    val solution = Solution()

    solution.solution()
}

class Solution {

    fun solution() {
        val (N, M) = readLine()!!.split(' ')
        val degree = Array(N.toInt()+1) { 0 }
        val graph = Array(N.toInt()+1) { mutableListOf<Int>() }
        val queue = PriorityQueue<Node>()

        repeat(M.toInt()) {
            val input = readLine()!!.split(' ')

            graph[input[0].toInt()].add(input[1].toInt())
            degree[input[1].toInt()]++
        }

        for (i in 1..N.toInt()) {
            if (degree[i] == 0)
                queue.offer(Node(i, graph[i]))
        }

        while (queue.isNotEmpty()) {
            val node = queue.poll()

            print("${node.n} ")

            for (i in node.next.indices) {
                if (--degree[node.next[i]] == 0) {
                    queue.offer(Node(node.next[i], graph[node.next[i]]))
                }
            }
        }
    }
}

data class Node(val n: Int, val next: MutableList<Int>) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return when {
            n > other.n -> 1
            n < other.n -> -1
            else -> 123456789
        }
    }
}
