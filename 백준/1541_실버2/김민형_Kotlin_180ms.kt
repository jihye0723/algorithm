import java.util.PriorityQueue
import kotlin.math.abs

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
    1. -, + 로 Split
    2. - Index 저장 (1 ~ N-1)
    3. - 위치부터 다음 - 가 나올 때 까지 숫자 더하기 (끝까지)
    4. 결과 값으로 대체 후 계산
     */
    fun solution() {
        val input = readLine()!!
        val numList = input.split('-', '+').toMutableList()
        val minusList = mutableListOf<Int>()
        var i = 1;
        var result = 0;
        input.forEachIndexed { index, c ->
            if (c == '-') {
                minusList.add(i++)
            } else if (c == '+') {
                i++
            }
        }
        if (minusList.isEmpty()) {
            print(numList.sumOf { it.toInt() })
            return
        }
        result += numList.subList(0, minusList[0]).sumOf { it.toInt() }
        for (index in 0 until minusList.size-1) {
            result -= numList.subList(minusList[index], minusList[index+1]).sumOf { it.toInt() }
        }
        result -= numList.subList(minusList.last(), numList.size).sumOf { it.toInt() }
        print(result)
    }
}
