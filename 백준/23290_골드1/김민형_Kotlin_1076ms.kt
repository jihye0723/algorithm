
import java.math.BigInteger
import java.util.LinkedList
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
    data class Fish(
        val x: Int,
        val y: Int,
        val dir: Int
    )

    val dx = listOf(0, -1, -1, 0, 1, 1, 1, 0, -1)
    val dy = listOf(0, 0, -1, -1, -1, 0, 1, 1, 1)
    val sdx = listOf(0, -1, 0, 1)
    val sdy = listOf(-1, 0, 1, 0)

    var result = 0

    fun solution() {
        val (M, S) = readLine()!!.split(' ').map { it.toInt() }
        val map = Array(5) { IntArray(5) }
        var fishMap = Array(5) { Array<MutableList<Fish>>(5) { mutableListOf() } }
        repeat(M) {
            val (y, x, dir) = readLine()!!.split(' ').map { it.toInt() }
            fishMap[y][x].add(Fish(x, y, dir))
        }
        var (sy, sx) = readLine()!!.split(' ').map { it.toInt() }

        repeat(S) {
            result = 0
//            for (i in 1..4) {
//                for (j in 1..4) {
//                    if(fishMap[i][j].isNotEmpty())
//                        print("[${fishMap[i][j].size}]")
//                    else
//                        print("[ ]")
//                }
//                println()
//            }
//            println()
//            map.forEach { println(it.toList()) }
//            println("$sx $sy")

            val newFishMap = Array(5) { Array<MutableList<Fish>>(5) { mutableListOf() } }
            for (y in 1..4) {
                for (x in 1..4) {
                    if (fishMap[y][x].isNotEmpty()) {
                        loop@ for (fish in fishMap[y][x]) {
                            val nx = fish.x + dx[fish.dir]
                            val ny = fish.y + dy[fish.dir]

                            if (nx in 1..4 && ny in 1..4 && map[ny][nx] == 0 && !(nx == sx && ny == sy)) {
//                                println("한 방에 간 물고기 $nx $ny ${fish.dir}")
                                newFishMap[ny][nx].add(Fish(nx, ny, fish.dir))
                                continue
                            } else {
                                for (next in 7 downTo 1) {
                                    var nextDir = (fish.dir + next) % 8
                                    if (nextDir == 0) nextDir = 8
                                    val npx = x + dx[nextDir]
                                    val npy = y + dy[nextDir]

                                    if ((npx in 1..4 && npy in 1..4) && map[npy][npx] == 0 && !(npx == sx && npy == sy)) {
//                                        println("꺽어서 간 물고기 $npx $npy ${nextDir}")
                                        newFishMap[npy][npx].add(Fish(npx, npy, nextDir))
                                        continue@loop
                                    }
                                }
                            }
                            newFishMap[y][x].add(Fish(x, y, fish.dir))
                        }
                    }
                }
            }

//            for (i in 1..4) {
//                for (j in 1..4) {
//                    if(newFishMap[i][j].isNotEmpty())
//                        print("[${newFishMap[i][j].size}]")
//                    else
//                        print("[ ]")
//                }
//                println()
//            }
//            println()

            val nextSharkRoute = findSharkRoute(newFishMap, sx, sy)

//            println("$sx $sy")

            if (nextSharkRoute.isNotEmpty()) {
                for (i in 0 until 3) {
                    val nsx = sx + sdx[nextSharkRoute[i]]
                    val nsy = sy + sdy[nextSharkRoute[i]]

                    if (newFishMap[nsy][nsx].isNotEmpty()) {
                        newFishMap[nsy][nsx].clear()
                        map[nsy][nsx] = 3
                    }

                    sx = nsx
                    sy = nsy
                }
            }

//            println("$sx $sy")

//            map.forEach { println(it.toList()) }
//            println()

            for (y in 1..4) {
                for (x in 1..4) {
                    if (map[y][x] > 0) {
                        map[y][x] -= 1
                    }
                }
            }

//            map.forEach { println(it.toList()) }
//            println()

            for (y in 1..4) {
                for (x in 1..4) {
                    if (fishMap[y][x].isNotEmpty()) {
                        newFishMap[y][x].addAll(fishMap[y][x])
                    }
                }
            }

            result += newFishMap.sumOf { it.sumOf { it.size } }

            fishMap = newFishMap

//            for (i in 1..4) {
//                for (j in 1..4) {
//                    if(newFishMap[i][j].isNotEmpty())
//                        print("[${newFishMap[i][j].size}]")
//                    else
//                        print("[ ]")
//                }
//                println()
//            }
//            println("Finish")
        }

        println(result)
    }

    private fun findSharkRoute(newFishMap: Array<Array<MutableList<Fish>>>, sx: Int, sy: Int): List<Int> {
        var results = Triple<Int, Int, Int>(-1, -1, -1)
        var max = -1

        for (fst in 0 until 4) {
            var fisrtEatCnt = 0
            val fx = sx + sdx[fst]
            val fy = sy + sdy[fst]

            if (fx !in 1..4 || fy !in 1..4) continue

            if (newFishMap[fy][fx].isNotEmpty()) {
                fisrtEatCnt += newFishMap[fy][fx].size
            }
            for (snd in 0 until 4) {
                var secondEatCnt = 0
                val snx = fx + sdx[snd]
                val sny = fy + sdy[snd]

                if (snx !in 1..4 || sny !in 1..4) continue

                if (newFishMap[sny][snx].isNotEmpty()) {
                    secondEatCnt += newFishMap[sny][snx].size
                }
                for (trd in 0 until 4) {
                    var thirdEatCnt = 0
                    val tx = snx + sdx[trd]
                    val ty = sny + sdy[trd]

                    if (tx !in 1..4 || ty !in 1..4) continue

                    if (newFishMap[ty][tx].isNotEmpty()) {
                        thirdEatCnt += newFishMap[ty][tx].size
                    }

                    if (abs(snd-trd) == 2)
                        thirdEatCnt -= newFishMap[ty][tx].size

                    if (max < fisrtEatCnt+secondEatCnt+thirdEatCnt) {
                        max = fisrtEatCnt+secondEatCnt+thirdEatCnt
                        results = Triple(fst, snd, trd)
                    }
                }
            }
        }

//        println(results)

        return if (results.first != -1) {
            results.toList()
        } else
            emptyList()
    }


}
