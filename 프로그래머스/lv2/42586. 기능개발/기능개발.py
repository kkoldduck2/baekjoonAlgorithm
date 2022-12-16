from collections import deque

def solution(progresses, speeds):
    answer = []
    progresses = deque(progresses)
    speeds = deque(speeds)
    left_times = list()

    n = len(progresses)
    for i in range(n):
        progress = progresses.popleft()
        left_time = (100 - progress) // speeds[i]

        if (100 - progress) % speeds[i] != 0:
            left_time += 1

        left_times.append(left_time)

    before_max = left_times[0]
    cnt = 1
    for i in range(1, n):
        if left_times[i] > before_max:
            answer.append(cnt)
            cnt = 1
        else: # 같이 배포됨
            cnt += 1
        before_max = max(before_max, left_times[i])

    answer.append(cnt)
    return answer