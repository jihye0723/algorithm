

fun main() {
    val solution = Solution()

    solution.solution()
}
/*
2 162
100 80042112
 */
class Solution {
    fun solution() {
        var (A, B) = readLine()!!.split(' ')
        var result = 1

        while (B != "0" && B != A) {
            if (B.toInt() % 2 == 0) {
                B = (B.toInt() / 2).toString()
                result++
            } else if (B.last() == '1') {
                if (B.length == 1) {
                    B = "0"
                    result = -1
                    continue
                }
                B = B.dropLast(1)
                result++
            } else {
                result = -1
                break
            }
        }
        println(result)
    }
}
