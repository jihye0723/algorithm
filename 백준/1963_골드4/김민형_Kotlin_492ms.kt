import java.util.*

fun main() {
    val solution = Solution()

    solution.solution()
}

const val INF = 123456789

class Solution {

    fun solution() {
        val T = readLine()!!.toInt()

        val prime = Array(10000) { 1 }

        for (i in 2 until 10000) {
            if (prime[i] == 0) continue

            for (j in 2 * i until 10000 step i) {
                prime[j] = 0
            }
        }

        repeat(T) {
            var visit = Array(10000) { false }
            val (start, end) = readLine()!!.split(' ')
            val queue = PriorityQueue<Node>()
            var result = -1

            queue.offer(Node(start.toInt(), 0))

            while (queue.isNotEmpty()) {
                val node = queue.poll()

                if (prime[node.num] == 0) continue
                if (visit[node.num]) continue
                if (node.num == end.toInt()) {
                    result = node.count
                    break
                }

                visit[node.num] = true

//                println("${node.num} is prime, count = ${node.count}")

                 for (index in 0 until 4) {
                    val now = node.num.toString()[index]

                     for (time in 0 until 10) {
                        if (index == 0 && time == 0) continue
                        if (time == now.toString().toInt()) continue

                        val next = buildString {
                            if (index == 0) append(time) else append(node.num.toString()[0])
                            if (index == 1) append(time) else append(node.num.toString()[1])
                            if (index == 2) append(time) else append(node.num.toString()[2])
                            if (index == 3) append(time) else append(node.num.toString()[3])
                        }

                        queue.offer(Node(next.toInt(), node.count + 1))
                    }
                }
            }
            println(if (result != -1) result else "Impossible")
        }
    }
}

data class Node(
    val num: Int,
    val count: Int
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
