class Solution {
    fun solution(n: Int, results: Array<IntArray>): Int {
        var answer = 0
        val map = Array(n) { IntArray(n) {Int.MAX_VALUE} }
        results.forEach { // [4, 3]
            val A = it[0] - 1
            val B = it[1] - 1
            map[A][B] = 1
            map[B][A] = -1
        }
        for (K in 0 until n) {
            for (I in 0 until n) {
                for (J in 0 until n) {
                    if (map[I][K] == 1 && map[K][J] == 1) map[I][J] = 1
                    else if (map[I][K] == -1 && map[K][J] == -1) map[I][J] = -1
                }
            }
        }
        map.forEach { m ->
            if (m.count { it != Int.MAX_VALUE } == n-1) answer++
        }
        return answer
    }
}
