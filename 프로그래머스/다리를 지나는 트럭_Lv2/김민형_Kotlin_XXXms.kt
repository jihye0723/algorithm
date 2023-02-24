import java.util.*

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        
        // Init queue
        val waitQueue = LinkedList<Int>()
        for (truck in truck_weights) {
            waitQueue.offer(truck)
        }
        
        // Bridge
        // Init
        val bridge = LinkedList<Int>()
        var currentWeight = 0
        repeat(bridge_length) {
            bridge.offer(0)
        }
        
        // End queue
        val endQueue = LinkedList<Int>()
        
        // Loop
        // waiting_truck become empty
        while (endQueue.size < truck_weights.size) {
            // Pick truck
            val truck = waitQueue.peek()
            if (truck == null) break
            
            // Bridge aging
            val outTruck = bridge.poll()
            currentWeight -= outTruck
            if (outTruck != 0) {
                endQueue.offer(outTruck)
            }
            
            // Across bridge
            if (bridge.isEmpty()) {
                bridge.offer(truck)
                currentWeight += truck
                waitQueue.poll()
            } else if (bridge.size == bridge_length) {
                bridge.offer(0)
            } else {
                if (currentWeight + truck <= weight) {
                    bridge.offer(truck)
                    currentWeight += truck
                    waitQueue.poll()
                } else {
                    bridge.offer(0)
                }
            }
            
            // Count sec
            answer++
        }
        
        while (!bridge.isEmpty()) {
            val outTruck = bridge.poll()
            currentWeight -= outTruck
            if (outTruck != 0) {
                endQueue.offer(outTruck)
            }
            answer++
        }
        
        return answer
    }
}
