def solution(k, dungeons):
    def dfs(idx, visited, cnt, left_k):
        global maxCnt
        # if idx == len(visited):
        #     maxCnt = max(maxCnt, cnt)
        #     return
        maxCnt = max(maxCnt, cnt)
        for i in range(n):
            if visited[i] == False and left_k >= dungeons[i][0]:
                visited[i] = True
                dfs(idx+1, visited, cnt+1, left_k - dungeons[i][1])
                visited[i] = False

    n = len(dungeons)
    visited = [False] * n

    global maxCnt
    maxCnt = 0
    dfs(0, visited, 0, k)
    return maxCnt