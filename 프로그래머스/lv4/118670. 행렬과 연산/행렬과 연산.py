from collections import deque
def solution(rc, operations):
    answer = []
    mid_pillar, left_pillar, right_pillar = deque(), deque(), deque()
    for r in rc:
        mid_pillar.append(deque(r[1:-1]))
        left_pillar.append(r[0])
        right_pillar.append(r[-1])

    for operation in operations:
        if operation == 'Rotate':
            if mid_pillar[0]:   # 이게 null일 수 있나?
                right_pillar.appendleft(mid_pillar[0].pop())
                mid_pillar[-1].append(right_pillar.pop())
            else:
                right_pillar.appendleft(left_pillar.popleft())

            if mid_pillar[-1]:
                left_pillar.append(mid_pillar[-1].popleft())
                mid_pillar[0].appendleft(left_pillar.popleft())
            else:
                left_pillar.append(right_pillar.pop())
        else:
            mid_pillar.rotate(1)
            left_pillar.rotate(1)
            right_pillar.rotate(1)

    for left, mid, right in zip(left_pillar, mid_pillar, right_pillar):
        answer.append([left] + list(mid) + [right])

    return answer