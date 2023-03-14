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
        val N = readLine()!!.toInt()
        val input = mutableListOf<Pair<Int, Int>>()
        repeat(N) {
            val (x, y) = readLine()!!.split(' ').map { it.toInt() }
            input.add(Pair(x, y))
        }
        val xList = input.map { it.first }.toSortedSet().toList()
        val yList = input.map { it.second }.toSortedSet().toList()
        val queue = PriorityQueue<Int>()
        val result = Array(N) { Int.MAX_VALUE }
        for (x in xList) {
            for (y in yList) {
                for (p in input) {
                    queue.offer(dist(Pair(x, y), p))
                }
                var index = 1
                var sum = queue.poll()
                while (!queue.isEmpty()) {
                    sum += queue.poll()
                    result[index] = result[index++].coerceAtMost(sum)
                }
            }
        }
        result.forEach { if (it == Int.MAX_VALUE) print("0 ") else print("$it ") }
    }

    fun dist(a: Pair<Int, Int>, b: Pair<Int, Int>): Int {
        return abs(a.first-b.first) + abs(a.second-b.second)
    }
}
