import java.util.*

fun main() {
    val solution = Solution()

    solution.solution()
}

class Solution {
    var result = 123456789
    fun solution() {
        val (N, M) = readLine()!!.split(' ')
        val Y = N.toInt()
        val X = M.toInt()
        val graph = Array(Y) { Array(X) {'0'} }
        val cctvStack = Stack<CCTV>()

        repeat(Y) { index ->
            graph[index] = readLine()!!.toList().filter { it.isDigit() }.toTypedArray()
        }

        for (i in 0 until Y) {
            for (j in 0 until X) {
                if (graph[i][j] != '0' && graph[i][j] != '6') {
                    cctvStack.push(CCTV(j, i, graph[i][j]))
                }
            }
        }

        find(graph, cctvStack)

        println(result)
    }

    fun find(graph: Array<Array<Char>>, cctvStack: Stack<CCTV>) {
        if (cctvStack.isEmpty()) {
            var count = 0
            for (i in graph.indices) {
                for (j in graph[i].indices) {
                    if (graph[i][j] == '0')
                        count++
                }
            }
            result = result.coerceAtMost(count)
            return
        }

        val cctv = cctvStack.pop()
        var tempGraph: Array<Array<Char>>
        var tempStack: Stack<CCTV>

        when (cctv.n) {
            '1' -> {
                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goTop(tempGraph, cctv)
                find(tempGraph, tempStack)

                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goBottom(tempGraph, cctv)
                find(tempGraph, tempStack)

                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goLeft(tempGraph, cctv)
                find(tempGraph, tempStack)

                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goRight(tempGraph, cctv)
                find(tempGraph, tempStack)
            }
            '2' -> {
                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goTop(tempGraph, cctv)
                goBottom(tempGraph, cctv)
                find(tempGraph, tempStack)

                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goLeft(tempGraph, cctv)
                goRight(tempGraph, cctv)
                find(tempGraph, tempStack)
            }
            '3' -> {
                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goTop(tempGraph, cctv)
                goRight(tempGraph, cctv)
                find(tempGraph, tempStack)

                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goRight(tempGraph, cctv)
                goBottom(tempGraph, cctv)
                find(tempGraph, tempStack)

                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goBottom(tempGraph, cctv)
                goLeft(tempGraph, cctv)
                find(tempGraph, tempStack)

                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goLeft(tempGraph, cctv)
                goTop(tempGraph, cctv)
                find(tempGraph, tempStack)
            }
            '4' -> {
                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goTop(tempGraph, cctv)
                goLeft(tempGraph, cctv)
                goRight(tempGraph, cctv)
                find(tempGraph, tempStack)

                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goTop(tempGraph, cctv)
                goRight(tempGraph, cctv)
                goBottom(tempGraph, cctv)
                find(tempGraph, tempStack)

                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goRight(tempGraph, cctv)
                goBottom(tempGraph, cctv)
                goLeft(tempGraph, cctv)
                find(tempGraph, tempStack)

                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goTop(tempGraph, cctv)
                goBottom(tempGraph, cctv)
                goLeft(tempGraph, cctv)
                find(tempGraph, tempStack)
            }
            '5' -> {
                tempGraph = copy(graph)
                tempStack = cctvStack.clone() as Stack<CCTV>
                goTop(tempGraph, cctv)
                goBottom(tempGraph, cctv)
                goLeft(tempGraph, cctv)
                goRight(tempGraph, cctv)
                find(tempGraph, tempStack)
            }
            else -> return
        }
    }

    fun copy(graph: Array<Array<Char>>): Array<Array<Char>> {
        val newGraph: Array<Array<Char>> = Array(graph.size) { Array(graph[0].size) {'0'} }
        for (i in graph.indices) {
            for (j in graph[i].indices) {
                newGraph[i][j] = graph[i][j]
            }
        }
        return newGraph
    }

    fun goLeft(graph: Array<Array<Char>>, cctv: CCTV) {
        if (cctv.x == 0) return
        for (x in cctv.x - 1 downTo 0) {
            if (graph[cctv.y][x] == '0')
                graph[cctv.y][x] = '#'
            else if (graph[cctv.y][x] == '6')
                return
        }
    }

    fun goRight(graph: Array<Array<Char>>, cctv: CCTV) {
        if (cctv.x == graph[0].size) return
        for (x in cctv.x + 1 until graph[0].size) {
            if (graph[cctv.y][x] == '0')
                graph[cctv.y][x] = '#'
            else if (graph[cctv.y][x] == '6')
                return
        }
    }

    fun goTop(graph: Array<Array<Char>>, cctv: CCTV) {
        if (cctv.y == 0) return
        for (y in cctv.y - 1 downTo 0) {
            if (graph[y][cctv.x] == '0')
                graph[y][cctv.x] = '#'
            else if (graph[y][cctv.x] == '6')
                return
        }
    }

    fun goBottom(graph: Array<Array<Char>>, cctv: CCTV) {
        if (cctv.y == graph.size) return
        for (y in cctv.y + 1 until graph.size) {
            if (graph[y][cctv.x] == '0')
                graph[y][cctv.x] = '#'
            else if (graph[y][cctv.x] == '6')
                return
        }
    }
}

class CCTV(val x: Int, val y: Int, val n: Char)
