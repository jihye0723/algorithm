import java.util.*

fun main() {
    val solution = Solution()

    solution.solution()
}
/*
(()[[]])([]) -> 22 6
[][]((]) -> ((
 */
/**
 * 스택을 생성한다.
 * 여는 괄호가 나오면 스택에 추가한다.
 * 닫는 괄호가 나오면 스택에서 하나를 뺀 뒤 짝이 맞는지 체크한다.
 * 스택이 비어있을 떄 까지 성공하면 올바른 괄호열, 아니라면 0 출력한다.
 * 닫는 괄호가 나오면 여는 괄호가 나올 때 까지 스택에서 뺀다. 여는 괄호가 나오면 해당 치환 값을 스택에 추가한다.
 * 이 때, 중간에 숫자가 나오면 큐에 저장한다.
 * 치환 값에 큐안의 값도 같이 계산한다.
 */
class Solution {

    fun solution() {
        val input = readLine()!!
        val stack = Stack<String>()
        val queue = LinkedList<String>()
        var answer = 0
        var weired = false

        loop@ for (i in input.indices) {
            if (input[0] == ')' || input[0] == ']') break@loop

            when (input[i]) {
                '(' -> {
                    stack.push(input[i].toString())
                }
                '[' -> {
                    stack.push(input[i].toString())
                }
                ')' -> {
                    while (stack.isNotEmpty()) {
                        val next = stack.pop()

                        if (next == "(") {
                            var temp = 0

                            while (queue.isNotEmpty()) {
                                temp += queue.poll().toInt()
                            }

                            stack.push(if (temp == 0) "2" else (temp * 2).toString())
                            break
                        } else if (next == "[") {
                            break@loop
                        } else if (next == "]") {
                            break@loop
                        } else if (next == ")") {
                            break@loop
                        } else {
                            try {
                                next.toInt()
                            } catch (e: Exception) {
                                weired = true
                                break@loop
                            }

                            queue.push(next)
                        }
                    }
                }
                ']' -> {
                    while (stack.isNotEmpty()) {
                        val next = stack.pop()

                        if (next == "[") {
                            var temp = 0

                            while (queue.isNotEmpty()) {
                                temp += queue.poll().toInt()
                            }

                            stack.push(if (temp == 0) "3" else (temp * 3).toString())
                            break
                        } else if (next == "(") {
                            break@loop
                        } else if (next == "]") {
                            break@loop
                        } else if (next == ")") {
                            break@loop
                        } else {
                            try {
                                next.toInt()
                            } catch (e: Exception) {
                                weired = true
                                break@loop
                            }

                            queue.push(next)
                        }
                    }
                }
                else -> {
                    weired = true
                    break@loop
                }
            }
        }

        if (weired) {
            println(0)
        } else {
            val result = mutableListOf<String>()
            while (stack.isNotEmpty()) {
                result.add(stack.pop())
            }

            var size = result.size
            var digit = size - result.count { it == "(" || it == "[" || it == ")" || it == "]" }

            if (size == digit) {
                for (i in result.indices) {
                    answer += result[i].toInt()
                }
            }

            println(answer)
        }
    }
}

// )[]
// ()3[]
// {{}()
