

fun main() {
    val solution = Solution()

    solution.solution()
}
/*
2
5 6
0 0 1 0
6
1 2 3 4 5 6
2 1 1 1
 */
class Solution {
    var max = -1234567890
    var min = 1234567890
    fun solution() {
        val N = readLine()!!.toInt()
        val numbers = Array(N+1) {0}
        val ops = Array(4) {0}

        readLine()!!.split(' ').forEachIndexed { index, s ->
            numbers[index] = s.toInt()
        }

        readLine()!!.split(' ').forEachIndexed { index, s ->
            ops[index] = s.toInt()
        }

        for (op in ops.indices) {
            dfs(numbers, op, ops, 0, N-1)
        }

        println(max)
        println(min)
    }

    fun dfs(numbers: Array<Int>, op: Int, ops: Array<Int>, index: Int, depth: Int) {
        if (ops[op] < 1) {
            if (index == depth) {
                max = max.coerceAtLeast(numbers[index])
                min = min.coerceAtMost(numbers[index])
                return
            }
            return
        }

        val tempNumbers = numbers.copyOf()
        val tempOps = ops.copyOf()
        // op is index. ex, 0 = +, 1 = -, 2 = *, 3 = /
        // ops 를 임시 ops 로 만들고, 사용한 op 를 하나씩 줄인 ops 를 계속 인자로 넘겨준다.
        val result = when (op) {
            0 -> {
                tempNumbers[index] + tempNumbers[index+1]
            }
            1 -> {
                tempNumbers[index] - tempNumbers[index+1]
            }
            2 -> {
                tempNumbers[index] * tempNumbers[index+1]
            }
            3 -> {
                tempNumbers[index] / tempNumbers[index+1]
            }
            else -> {0}
        }
        tempOps[op] -= 1
        tempNumbers[index+1] = result

        for (op in ops.indices) {
            dfs(tempNumbers, op, tempOps, index+1, depth)
        }
    }
}
