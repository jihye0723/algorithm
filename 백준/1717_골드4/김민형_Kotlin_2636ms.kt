import java.util.*

fun main() {
    val solution = Solution()

    solution.solution()
}

/**
 *
 */
class Solution {

    fun solution() {
        val (N, M) = readLine()!!.split(' ')
        val parent = Array(N.toInt() + 1) { 0 }

        for (i in parent.indices) {
            parent[i] = i
        }

        repeat(M.toInt()) {
            val input = readLine()!!.split(' ')

            when (input[0].toInt()) {
                0 -> {
                    unionSet(input[1].toInt(), input[2].toInt(), parent)
                }
                1 -> {
                    if (isUnion(input[1].toInt(), input[2].toInt(), parent))
                        println("YES")
                    else
                        println("NO")
                }
            }
        }
    }

    private fun findParent(n: Int, parent: Array<Int>): Int {
        if (parent[n] == n) return n
        parent[n] = findParent(parent[n], parent)
        return parent[n]
    }

    private fun unionSet(a: Int, b: Int, parent: Array<Int>) {
        val x = findParent(a, parent)
        val y = findParent(b, parent)

        if (x > y) parent[x] = y
        else parent[y] = x
    }

    private fun isUnion(a: Int, b: Int, parent: Array<Int>): Boolean {
        val x = findParent(a, parent)
        val y = findParent(b, parent)

        return x == y
    }
}

