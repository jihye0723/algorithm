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
        val N = readLine()!!.toInt()
        var itemList = mutableListOf<Int>()

        repeat(N) {
            val op = readLine()!!.split(' ')

            itemList = itemList.sortedBy { it }.toMutableList()

            if (op[0].toInt() == 0) {
                if (itemList.isEmpty()) {
                    println(-1)
                } else {
                    println(itemList.last())
                    itemList = itemList.dropLast(1).toMutableList()
                }

            } else {
                for (i in 1 until op.size) {
                    itemList.add(op[i].toInt())
                }
            }
        }
    }
}
