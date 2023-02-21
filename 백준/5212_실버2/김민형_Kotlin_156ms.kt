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
5 3
...
.X.
.X.
.X.
...
 */
class Solution {
    /*

     */
    val dx = listOf(0,1,0,-1)
    val dy = listOf(1,0,-1,0)

    fun solution() {
        val (R, C) = readLine()!!.split(' ').map { it.toInt() }
        val map = Array(R) { Array(C) {'.'} }
        repeat(R) {
            map[it] = readLine()!!.toList().toTypedArray()
        }
        val newMap = map.map { it.copyOf() }.toTypedArray()

        repeat(R) { r ->
            repeat(C) { c ->
                when (map[r][c]) {
                    '.' -> {
                        //
                    }
                    'X' -> {
                        search(r, c, map, newMap, R, C)
                    }
                    else -> {
                        //
                    }
                }
            }
        }

        var minR = R
        var minC = C
        var maxR = 0
        var maxC = 0
        for (r in 0 until R) {
            for (c in 0 until C) {
                if (newMap[r][c] == 'X') {
                    minR = minR.coerceAtMost(r)
                    minC = minC.coerceAtMost(c)
                    maxR = maxR.coerceAtLeast(r)
                    maxC = maxC.coerceAtLeast(c)
                }
            }
        }

        for (i in minR..maxR) {
            for (j in minC..maxC) {
                print(newMap[i][j])
            }
            println()
        }
    }

    fun search(r: Int, c: Int, map: Array<Array<Char>>, newMap: Array<Array<Char>>, R: Int, C: Int) {
        var count = 0

        for (dir in 0 until 4) {
            val nx = dx[dir] + r;
            val ny = dy[dir] + c;

            if (nx !in 0 until R) {
                count++
                continue
            }
            if (ny !in 0 until C) {
                count++
                continue
            }

            when (map[nx][ny]) {
                '.' -> {
                    count++
                }
                'X' -> {
                    //
                }
                else -> {
                    count++
                }
            }
        }

        if (count > 2) {
            newMap[r][c] = '.'
            return
        }
    }
}
