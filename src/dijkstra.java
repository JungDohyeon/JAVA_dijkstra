import java.util.*;

public class dijkstra {
    static class edge implements Comparable <edge> {
        int dest, weight;

        @Override
        public int compareTo(edge o) {
            return Integer.compare(this.weight, o.weight);
        }

        public edge(int dest, int weight) {
            this.dest = dest;   // destination
            this.weight = weight;   // weight
        }
    }

    static int nodeCount;
    static int edgeCount;
    static int[] distance;
    static ArrayList<ArrayList<edge>> dijkstraGraph;

    static void GetDijkstra (int start){
        PriorityQueue<edge> q = new PriorityQueue<>();
        q.offer(new edge(start, 0));
        distance[start] = 0;  // start -> start: 0
        while(!q.isEmpty()){
            edge nowEdge = q.poll();    // poll data
            int dest = nowEdge.dest;
            int val = nowEdge.weight;

            if(distance[dest] < val) {
                continue;
            }

            // advanced for loop
            for(edge adj: dijkstraGraph.get(dest)){
                int compareDistance = distance[dest] + adj.weight;
                if(compareDistance < distance[adj.dest]){   // 새로 얻은 비용 값이 더 작다면 distance 갱신
                    distance[adj.dest] = compareDistance;
                    q.offer(new edge(adj.dest, compareDistance));
                }
            }

//            for(int i = 0; i<dijkstraGraph.get(dest).size(); i++){
//                int compareDistance = distance[dest] + dijkstraGraph.get(dest).get(i).weight;
//                if (compareDistance < distance[dijkstraGraph.get(dest).get(i).to]){
//                    distance[dijkstraGraph.get(dest).get(i).to] = compareDistance;
//                    q.offer(new edge(dijkstraGraph.get(dest).get(i).to, compareDistance));
//                }
//            }
        }
    }

    public static void main(String args[]) {
        System.out.print("Enter the number of node and edge >> ");
        Scanner sc = new Scanner(System.in);
        nodeCount = sc.nextInt();   // number of node
        edgeCount = sc.nextInt();   // number of edge

        dijkstraGraph = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++){
            dijkstraGraph.add(new ArrayList<>());
        }

        distance = new int[nodeCount];
        Arrays.fill(distance, 1000000); // 무한으로 초기화 (Infinite = 1000000)

        for(int i = 0; i < edgeCount; i++){
            System.out.print("Enter 'startpoint' 'destination point' 'weight' >> ");
            int source = sc.nextInt();  // start point
            int dest = sc.nextInt();    // destination point
            int weight = sc.nextInt();  // weight
            dijkstraGraph.get(source).add(new edge(dest, weight));  // source에서 dest까지 간선 가중치 삽입
        }
        GetDijkstra(0);     // 0번을 기준으로 Dijkstra Algorithm
        System.out.println("The shortest path from 0 is: " + Arrays.toString(distance));  // distance에 담긴 최종 결과 배열 출력
    }
}
