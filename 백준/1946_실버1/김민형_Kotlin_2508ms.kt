


fun main() {
    val solution = Solution()

    solution.solution()
}
/*
2
5
3 2
1 4
4 1
2 3
5 5
7
3 6
7 3
4 2
1 4
5 7
2 5
6 1
 */
class Solution {
    fun solution() {
        val T = readLine()!!.toInt()

        repeat(T) {
            var result = 1
            val N = readLine()!!.toInt()
            val applicantList = mutableListOf<Pair<Int, Int>>()
            repeat(N) {
                val (a, b) = readLine()!!.split(' ')
                applicantList.add(Pair(a.toInt(), b.toInt()))
            }
            applicantList.sortBy { it.first }
            /**
             * 1. 서류점수로 정렬한다.
             * 2. 2등부터 N등까지 반복한다.
             * 3. 면접점수를 읽어서 바로 앞 등수 면접점수랑 비교한다.
             * 4. 내가 더 높다면 그 전 앞 등수 ~ 1등까지 비교한다.
             * 5. 내가 내 모든 앞 사람 중 면접점수가 가장 높다면 OK
             * 6. 한 번이라도 내가 더 낮은 등수가 나오면 NO
             */
            var grade = applicantList[0].second

            for (i in 1 until N) {
                val myGrade = applicantList[i].second

                if (myGrade < grade) {
                    grade = myGrade
                    result++
                }
            }
            println(result)
        }
    }
}
