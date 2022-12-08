from collections import deque

def solution(queue1, queue2):
    q1 = deque(queue1)
    q2 = deque(queue2)
    limit = 600001
    target = (sum(q1) + sum(q2)) // 2
    # print(target)

    cnt = 0
    sum1 = sum(q1)
    sum2 = sum(q2)

    while cnt < limit:
        if sum1 == target and sum2 == target:
            break

        if sum1 > target:
            num = q1.popleft()
            q2.append(num)
            cnt += 1
            sum1 -= num
            sum2 += num

        else:
            num = q2.popleft()
            q1.append(num)
            cnt += 1
            sum2 -= num
            sum1 += num

    if cnt == limit:
        return -1
    return cnt