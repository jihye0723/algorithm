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
    1. 점 위치 저장
    2. x 좌표, y 좌표 별로 분리 후 정렬
    3. 2번 결과 x, y 좌표 완전 탐색
    4. 반복되는 해당 점에서 주어진 점까지의 거리 계산 후 최소값 비교 / 저장
     */
    fun solution() {
        val N = readLine()!!.trim().toInt()
        val input = mutableListOf<Pair<Int, Int>>()
        repeat(N) {
            val tmp = readLine()!!
            val (x, y) = if (tmp.split(' ').last().isBlank()) {
                tmp.split(' ').dropLast(1).map { it.toInt() }
            } else {
                tmp.split(' ').map { it.toInt() }
            }
            input.add(Pair(x, y))
        }
        val xList = input.map { it.first }.sorted()
        val yList = input.map { it.second }.sorted()
        var result = 0L
        input.forEach {
            result += dist(it, Pair(xList[N/2],yList[N/2]))
        }
        print(result)
    }

    fun dist(a: Pair<Int, Int>, b: Pair<Int, Int>): Int {
        return abs(a.first-b.first) + abs(a.second-b.second)
    }
}
