class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = intArrayOf()
        
        // [1]
        // [1]
        // [1,2,3,4]
        val cases = divisor(yellow)
        
        for (case in cases) {
            if (
                (case+2)*((yellow/case)+2)-yellow == brown
            ) {
                answer = intArrayOf((yellow/case+2), case+2)
                break
            }
        }
        
        return answer
    }
    
    fun divisor(yellow: Int): List<Int> {
        val result = mutableListOf<Int>()
        for (i in 1..Math.sqrt(yellow.toDouble()).toInt()) {
            if (yellow % i == 0)
                result.add(i)
        }
        return result
    }
}
