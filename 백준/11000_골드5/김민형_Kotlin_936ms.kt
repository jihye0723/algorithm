import java.lang.Math.abs
import java.util.*
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.system.exitProcess

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

     */
    fun solution() {
        val N = readLine()!!.toInt()
        val classes = mutableListOf<Pair<Int,Int>>()
        repeat(N) {
            val (start, end) = readLine()!!.split(' ').map { it.toInt() }
            classes.add(Pair(start,end))
        }
        classes.sortBy { it.first }
        val room = PriorityQueue<Int>()
        for (i in 0 until N) {
            if (room.isEmpty())
                room.offer(classes[i].second)
            else {
                val endClass = room.peek()
                if (endClass <= classes[i].first) {
                    room.poll()
                    room.offer(classes[i].second)
                } else
                    room.offer(classes[i].second)
            }
        }
        print(room.size)
    }
}
