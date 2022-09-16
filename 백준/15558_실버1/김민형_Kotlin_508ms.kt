import java.util.*

fun main() {
    val solution = Solution()

    solution.solution()
}

class Solution {
    var result = 0

    fun solution() {
        val (N, K) = readLine()!!.split(' ')
        val queue = LinkedList<Game>()
        val visit = Array(2) { BooleanArray(N.toInt()) { false } }

        val stage = Array(2) { mutableListOf<Char>() }
        stage[0] = readLine()!!.toMutableList()
        stage[1] = readLine()!!.toMutableList()

        queue.offer(Game(0, 0, -1))

        while (queue.isNotEmpty()) {
            val game = queue.poll()

            if (game.current + 1 >= N.toInt() || game.current + K.toInt() >= N.toInt()) {
                result = 1
                break
            }

            if (stage[game.side][game.current + 1] == '1' && game.death <= game.current && !visit[game.side][game.current + 1]) {
                visit[game.side][game.current + 1] = true
                queue.offer(Game(game.current+1, game.side, game.death+1))
            }

            if (game.current - 1 > 0 && stage[game.side][game.current - 1] == '1' && game.death + 1 < game.current - 1 && !visit[game.side][game.current - 1]) {
                visit[game.side][game.current - 1] = true
                queue.offer(Game(game.current-1, game.side, game.death+1))
            }

            when (game.side) {
                0 -> {
                    val next = game.current + K.toInt()

                    if (stage[1][game.current + K.toInt()] == '1' && game.death + 1 <= game.current + K.toInt() && !visit[1][game.current + K.toInt()]) {
                        visit[1][game.current + K.toInt()] = true
                        queue.offer(Game(next, 1, game.death+1))
                    }
                }
                1 -> {
                    val next = game.current + K.toInt()

                    if (stage[0][game.current + K.toInt()] == '1' && game.death + 1 <= game.current + K.toInt() && !visit[0][game.current + K.toInt()]) {
                        visit[0][game.current + K.toInt()] = true
                        queue.offer(Game(next, 0, game.death+1))
                    }
                }
            }
        }

        println(result)
    }
}

data class Game(
    val current: Int,
    val side: Int,
    val death: Int
)
