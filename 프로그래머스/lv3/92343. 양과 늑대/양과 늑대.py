from collections import defaultdict
def solution(info, edges):
    def dfs(idx, wolf, sheep, can_go):
        global max_sheep
        if info[idx] == 0 :
            sheep += 1
        else:
            wolf += 1

        # base case
        if wolf >= sheep:
            return
        max_sheep = max(max_sheep, sheep)

        # 다음 탐색 위치 갱신
        next_go = list(can_go)
        next_go.remove(idx)
        for child in graph[idx]:
            next_go.append(child)

        # 갈 수 있는 모든 노드 dfs
        for i in next_go:
            dfs(i, wolf, sheep, next_go)


    # make graph
    graph = defaultdict(list)
    for p,c in edges:
        graph[p].append(c)

    global max_sheep
    max_sheep= 0

    # 그래프 탐색
    can_go = list()
    can_go.append(0)
    wolf, sheep = 0,0
    dfs(0, wolf, sheep, can_go)
    return max_sheep