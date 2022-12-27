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
4
Baha enter
Askar enter
Baha leave
Artem enter
 */
class Solution {
    /*

     */
    fun solution() {
        val N = readLine()!!.toInt()
        val list = mutableMapOf<String, Boolean>()
        repeat(N) {
            val (who, move) = readLine()!!.split(' ')
            when (move) {
                "leave" -> {
                    list.remove(who)
                }
                else -> {
                    list[who] = true
                }
            }
        }
        list.keys.sortedByDescending {it}.forEach { println(it) }
    }
}
