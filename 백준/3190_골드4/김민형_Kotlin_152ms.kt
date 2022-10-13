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
6
3
3 4
2 5
5 3
3
3 D
15 L
17 D
 */
const val SNAKE = Int.MAX_VALUE

class Solution {
    /*

     */
    val dx = intArrayOf(0, 1, 0, -1)
    val dy = intArrayOf(1, 0, -1, 0)

    fun solution() {
        val N = readLine()!!.toInt()
        val graph = Array(N) { IntArray(N) }
        val K = readLine()!!.toInt()
        repeat(K) {
            val apple = readLine()!!.split(' ').map { it.toInt() }
            val x = if (apple[0] == 0) 0 else apple[0]-1
            val y = if (apple[1] == 0) 0 else apple[1]-1
            graph[x][y] = 1
        }
        val L = readLine()!!.toInt()
        val cmdList = mutableMapOf<Int, String>()
        repeat(L) {
            val input = readLine()!!.split(' ')
            val time = input[0].toInt()
            val cmd = input[1]
            cmdList[time] = cmd
        }
        var dir = 0
        var time = 0
        var x = 0
        var y = 0
        var tailX = 0
        var tailY = 0
        val dirRoute = mutableListOf<Int>()
        dirRoute.add(0)
        var length = 1
        graph[x][y] = SNAKE

        while (true) {
            val nx = x + dx[dir]
            val ny = y + dy[dir]

            time++

            if (nx !in 0 until N || ny !in 0 until N)
                break
            if (graph[nx][ny] == SNAKE)
                break

            if (graph[nx][ny] == 0) {
                graph[tailX][tailY] = 0
                tailX += dx[dirRoute[time-length]]
                tailY += dy[dirRoute[time-length]]
            } else
                length++

            graph[nx][ny] = SNAKE

            if (cmdList.containsKey(time)) {
                when (cmdList[time]) {
                    "D" -> {
                        dir = (dir+1)%4
                    }
                    "L" -> {
                        dir = (dir+3)%4
                    }
                    else -> {

                    }
                }
            }
            dirRoute.add(dir)

            x = nx
            y = ny
        }

        println(time)
    }
}
