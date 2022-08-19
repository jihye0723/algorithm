import java.util.LinkedList
import java.util.Stack
import kotlin.math.abs
import kotlin.time.ExperimentalTime
import kotlin.time.TimedValue
import kotlin.time.measureTimedValue

/*
1. 인풋을 그래프로 생성
2. 1번 부터 스택에 넣음
3. 스택에서 하나씩 꺼냄
4. 방문 혹은 루프면 스킵
4. 방문 처리 후 방문 기록에 넣음
5. 본인을 가리키면 루프처리 하고 팀멤버에 넣고 스킵
6. 가리키는 노드가 첫 노드이면 루프처리 하고 팀멤버에 넣고 스킵
4. 가리키는 노드를 스택에 넣음
 */

@OptIn(ExperimentalTime::class)
fun main() {
    val solution = Solution()

    solution.solution()

}
/*
2
7
3 1 3 7 3 4 6
8
2 1 3 4 5 6 7 8
 */
class Solution {
    var result = 0
    fun solution() {
        // 1. 인풋을 그래프로 생성
        val T = readLine()!!.toInt()
        repeat(T) {
            result = 0
            val N = readLine()!!.toInt()
            val graph: Array<Int> = Array(N+1) {0}
            val visit: Array<Boolean> = Array(N+1) {false}
            val loop: Array<Boolean> = Array(N+1) {false}
            readLine()!!.split(' ').forEachIndexed { index, s ->
                graph[index+1] = s.toInt()
            }
            //        2. 1번 부터 스택에 넣음 (노드 수 만큼 반복) 2 <= N <= 100,000
            for (i in 1..N) {
                val visitList = LinkedList<Int>()
                val stack = Stack<Int>()
                stack.push(i)

                while (stack.isNotEmpty()) {
                    //        3. 스택에서 하나씩 꺼냄
                    val node = stack.pop() // node 6 first 4 visit[1][2][4][7][6] t, loop[3][4][7][6] t, visitlist (4)(7)(6), result (3)(4)(7)
                    //        4. 방문 혹은 루프면 스킵
                    if (visit[node] || loop[node]) {
                        break
                    } // visit[6] f loop[6] f
                    // 4. 방문 처리 후 방문 기록에 넣음
                    visit[node] = true // visit[1] t
                    visitList.add(node) // visitlist (9)(10)(4)(7)(6)(4)
                    // 5. 본인을 가리키면 루프처리 하고 팀멤버에 넣고 스킵
                    val next = graph[node] // next 4
                    // temp
                    if (node == next) { // f
                        loop[node] = true // loop[3] t
                        result++ // result (3)
                        break
                    }
                    if (visit[next]) {
                        //  6. 가리키는 노드가 방문 기록에 있으면 걔네를 루프처리 하고 팀멤버에 넣고 스킵
                        val index = visitList.indexOf(next)
                        if (index != -1) {
                            result += abs(index-visitList.lastIndex) + 1
                            break
                        }
                    }

//        4. 가리키는 노드를 스택에 넣음
                    stack.push(next) // stack (2)
                }
                // first 4 visit[1][2] t, loop[3] t, visitlist , stack , result (3)
//                solve(visit, loop, visitList, stack, graph)
            }
            println(N-result)
        }
    }
}
