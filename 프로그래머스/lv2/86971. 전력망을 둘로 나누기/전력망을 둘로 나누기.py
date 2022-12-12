from collections import defaultdict, deque

def solution(n, wires):
    def searchGraph(graph, start):
        global visited
        visited[start] = True
        q = deque(list())
        q.append(start)
        cnt = 0

        while q:
            now = q.popleft()
            cnt += 1
            for next_node in graph[now]:
                if not visited[next_node]:
                    q.append(next_node)
                    visited[next_node] = True

        return cnt

    answer = 101
    graph = defaultdict(list)

    for u, v in wires:
        graph[u].append(v)
        graph[v].append(u)

    for ru, rv in wires:
        # 제거하고
        graph[ru].remove(rv)
        graph[rv].remove(ru)

        # 그래프 탐색 -> 각 그래프가 갖는 송전탑의 개수
        global visited
        visited = [False] * (n+1)
        nodes = []
        for i in range(1,n+1):
            if not visited[i]:
                node_cnt = searchGraph(graph, i)
                nodes.append(node_cnt)

        answer = min(answer, abs(nodes[0] - nodes[1]))
        # 다시 추가
        graph[ru].append(rv)
        graph[rv].append(ru)

    return answer