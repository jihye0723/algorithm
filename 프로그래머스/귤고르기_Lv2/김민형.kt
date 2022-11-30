import java.util.*
class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        var answer: Int = 0
        val array = IntArray(10000001) {0}
        val queue = LinkedList<Int>()
        var kCount = k
        tangerine.forEach { t ->
            array[t] = array[t] + 1
        }
        array.sortedByDescending{it}.forEach { 
            n -> if (n > 0) {
                queue.offer(n)
            }
        }
        while (queue.isNotEmpty() && kCount > 0) {
            val now = queue.poll()
            kCount = kCount - now
            answer++
        }
        return answer
    }
}
