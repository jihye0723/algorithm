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
10101111
01111101
11001110
00000010
2
3 -1
1 1
 */
class Solution {
    /*

     */
    fun solution() {
        val queue1 = LinkedList<Int>()
        val queue2_1 = LinkedList<Int>()
        val queue2_2 = LinkedList<Int>()
        val queue3_1 = LinkedList<Int>()
        val queue3_2 = LinkedList<Int>()
        val queue4 = LinkedList<Int>()

        val gear1 = readLine()!!.map { it.toString().toInt() }
        val gear2 = readLine()!!.map { it.toString().toInt() }
        val gear3 = readLine()!!.map { it.toString().toInt() }
        val gear4 = readLine()!!.map { it.toString().toInt() }

        for (i in 2..7) {
            queue1.offer(gear1[i])
        }
        queue1.offer(gear1[0])
        queue1.offer(gear1[1])

        queue2_1.offer(gear2[6])
        queue2_1.offer(gear2[7])
        queue2_1.offer(gear2[0])
        queue2_1.offer(gear2[1])

        queue2_2.offer(gear2[2])
        queue2_2.offer(gear2[3])
        queue2_2.offer(gear2[4])
        queue2_2.offer(gear2[5])

        queue3_1.offer(gear3[6])
        queue3_1.offer(gear3[7])
        queue3_1.offer(gear3[0])
        queue3_1.offer(gear3[1])

        queue3_2.offer(gear3[2])
        queue3_2.offer(gear3[3])
        queue3_2.offer(gear3[4])
        queue3_2.offer(gear3[5])

        queue4.offer(gear4[6])
        queue4.offer(gear4[7])
        for (i in 0..5) {
            queue4.offer(gear4[i])
        }

        val K = readLine()!!.toInt()
        repeat(K) {
            val input = readLine()!!.split(' ').map { it.toInt() }
            val gear = input[0]
            val right = input[1] == 1
            when (gear) {
                1 -> {
                    if (right) {
                        if (queue1.peek() != queue2_1.peek()) {
                            if (queue2_2.peek() != queue3_1.peek()) {
                                if (queue3_2.peek() != queue4.peek()) {
                                    q4Left(queue4)
                                }
                                q3Right(queue3_1, queue3_2)
                            }
                            q2Left(queue2_1, queue2_2)
                        }
                        q1Right(queue1)
                    } else {
                        if (queue1.peek() != queue2_1.peek()) {
                            if (queue2_2.peek() != queue3_1.peek()) {
                                if (queue3_2.peek() != queue4.peek()) {
                                    q4Right(queue4)
                                }
                                q3Left(queue3_1, queue3_2)
                            }
                            q2Right(queue2_1, queue2_2)
                        }
                        q1Left(queue1)
                    }
                }
                2 -> {
                    if (right) {
                        if (queue2_1.peek() != queue1.peek()) {
                            q1Left(queue1)
                        }
                        if (queue2_2.peek() != queue3_1.peek()) {
                            if (queue3_2.peek() != queue4.peek()) {
                                q4Right(queue4)
                            }
                            q3Left(queue3_1, queue3_2)
                        }
                        q2Right(queue2_1, queue2_2)
                    } else {
                        if (queue2_1.peek() != queue1.peek()) {
                            q1Right(queue1)
                        }
                        if (queue2_2.peek() != queue3_1.peek()) {
                            if (queue3_2.peek() != queue4.peek()) {
                                q4Left(queue4)
                            }
                            q3Right(queue3_1, queue3_2)
                        }
                        q2Left(queue2_1, queue2_2)
                    }
                }
                3 -> {
                    if (right) {
                        if (queue3_2.peek() != queue4.peek()) {
                            q4Left(queue4)
                        }
                        if (queue3_1.peek() != queue2_2.peek()) {
                            if (queue2_1.peek() != queue1.peek()) {
                                q1Right(queue1)
                            }
                            q2Left(queue2_1, queue2_2)
                        }
                        q3Right(queue3_1, queue3_2)
                    } else {
                        if (queue3_2.peek() != queue4.peek()) {
                            q4Right(queue4)
                        }
                        if (queue3_1.peek() != queue2_2.peek()) {
                            if (queue2_1.peek() != queue1.peek()) {
                                q1Left(queue1)
                            }
                            q2Right(queue2_1, queue2_2)
                        }
                        q3Left(queue3_1, queue3_2)
                    }
                }
                4 -> {
                    if (right) {
                        if (queue4.peek() != queue3_2.peek()) {
                            if (queue3_1.peek() != queue2_2.peek()) {
                                if (queue2_1.peek() != queue1.peek()) {
                                    q1Left(queue1)
                                }
                                q2Right(queue2_1, queue2_2)
                            }
                            q3Left(queue3_1, queue3_2)
                        }
                        q4Right(queue4)
                    } else {
                        if (queue4.peek() != queue3_2.peek()) {
                            if (queue3_1.peek() != queue2_2.peek()) {
                                if (queue2_1.peek() != queue1.peek()) {
                                    q1Right(queue1)
                                }
                                q2Left(queue2_1, queue2_2)
                            }
                            q3Right(queue3_1, queue3_2)
                        }
                        q4Left(queue4)
                    }
                }
                else -> return
            }
        }
        println(queue1[6]+queue2_1[2]*2+queue3_1[2]*4+queue4[2]*8)
    }

    private fun q1Right(queue1: LinkedList<Int>) {
        val pop = queue1.pollLast()
        queue1.offerFirst(pop)
    }
    private fun q1Left(queue1: LinkedList<Int>) {
        val pop = queue1.poll()
        queue1.offer(pop)
    }
    private fun q2Right(queue2_1: LinkedList<Int>, queue2_2: LinkedList<Int>) {
        val pop1 = queue2_2.pollLast()
        queue2_1.offerFirst(pop1)
        val pop2 = queue2_1.pollLast()
        queue2_2.offerFirst(pop2)
    }
    private fun q2Left(queue2_1: LinkedList<Int>, queue2_2: LinkedList<Int>) {
        val pop1 = queue2_1.pollFirst()
        queue2_2.offerLast(pop1)
        val pop2 = queue2_2.pollFirst()
        queue2_1.offerLast(pop2)
    }
    private fun q3Right(queue3_1: LinkedList<Int>, queue3_2: LinkedList<Int>) {
        val pop1 = queue3_2.pollLast()
        queue3_1.offerFirst(pop1)
        val pop2 = queue3_1.pollLast()
        queue3_2.offerFirst(pop2)
    }
    private fun q3Left(queue3_1: LinkedList<Int>, queue3_2: LinkedList<Int>) {
        val pop1 = queue3_1.pollFirst()
        queue3_2.offerLast(pop1)
        val pop2 = queue3_2.pollFirst()
        queue3_1.offerLast(pop2)
    }
    private fun q4Right(queue4: LinkedList<Int>) {
        val pop = queue4.pollLast()
        queue4.offerFirst(pop)
    }
    private fun q4Left(queue4: LinkedList<Int>) {
        val pop = queue4.poll()
        queue4.offer(pop)
    }
}
