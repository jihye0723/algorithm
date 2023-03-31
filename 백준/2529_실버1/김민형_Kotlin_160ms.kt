
import java.math.BigInteger
import java.util.PriorityQueue
import kotlin.math.*

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
    1. 블록 완전 탐색
    2. 값이 존재하면 방향으로 이동
    3. 모든 값이 이동을 마치면 방향의 끝부터 같은 숫자 합치기
    4. 5번 반복
     */
    fun solution() {
        val k = readLine()!!.toInt() // 9
        val arr = readLine()!!.split(' ') // > < < < > > > < <

        val max = (9 downTo 0).toMutableList()
        val min = (0..9).toMutableList()

        val maxRes = IntArray(k+1) {0}
        val minRes = IntArray(k+1) {0}

        searchMax(arr, maxRes, max)
        searchMin(arr, minRes, min)

        maxRes.forEach { print(it) }
        println()
        minRes.forEach { print(it) }
    }

    fun searchMax(arr: List<String>, maxRes: IntArray, max: MutableList<Int>) {
        var now = 0
        var search = 0
        for (s in arr) {
            if (s == ">") {
                maxRes[search] = max.first()
                max.removeFirst()

                if (now < search) {
                    for (i in search-1 downTo now) {
                        maxRes[i] = max.first()
                        max.removeFirst()
                    }
                }

                now = search + 1
                search = now
            } else if (s == "<") {
                search++
            }
        }

        maxRes[search] = max.first()
        max.removeFirst()

        if (now < search) {
            for (i in search-1 downTo now) {
                maxRes[i] = max.first()
                max.removeFirst()
            }
        }
    }

    fun searchMin(arr: List<String>, minRes: IntArray, min: MutableList<Int>) {
        var now = 0
        var search = 0
        for (s in arr) {
            if (s == "<") {
                minRes[search] = min.first()
                min.removeFirst()

                if (now < search) {
                    for (i in search-1 downTo now) {
                        minRes[i] = min.first()
                        min.removeFirst()
                    }
                }

                now = search + 1
                search = now
            } else if (s == ">") {
                search++
            }
        }

        minRes[search] = min.first()
        min.removeFirst()

        if (now < search) {
            for (i in search-1 downTo now) {
                minRes[i] = min.first()
                min.removeFirst()
            }
        }
    }
}
