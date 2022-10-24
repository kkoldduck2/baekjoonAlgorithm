# NxN 행렬
# 도시에 있는 치킨집 중에서 최대 M개를 고르고, 나머지 치킨 집은 모두 폐업시켜야 한다.
# 어떻게 고르면 도시의 치킨 거리가 가장 작게 될지 구하는 프로그램을 작성해라

# idx번째 집을 선택할지 안할지 고르기
import heapq

def combin(idx, selected):
    def findChickenDist(selected):
        sum = 0
        for x, y in house:
            # 해당 집의 치킨 거리 구하기
            dist = float('inf')
            for cx, cy in selected:
                dist = min(dist, abs(x-cx) + abs(y-cy))

            sum += dist
        return sum

    if len(selected) == m:
        global min_dist
        # 도시의 치킨 거리 구하기
        if len(selected) > 0:
            dist = findChickenDist(selected)
            min_dist = min(min_dist, dist)
        return

    if idx >= len(chicken):
        return

    selected.append(chicken[idx])
    combin(idx+1, selected)
    selected.pop()
    combin(idx+1, selected)



n, m = map(int, input().split())
map = [list(map(int, input().split())) for _ in range(n)]
chicken = list()
house = list()
global min_dist
min_dist = float('inf')


for i in range(n):
    for j in range(n):
        if map[i][j] == 2:
            chicken.append((i, j))
        elif map[i][j] == 1:
            house.append((i, j))

combin(0, [])
print(min_dist)



# 폐업시키지 않을 치킨 집을 고르고 -> combination
# 해당 경우마다 도시의 치킨 거리를 구한다. -> 그리도 최소 도시의 치킨거리를 구한다.
