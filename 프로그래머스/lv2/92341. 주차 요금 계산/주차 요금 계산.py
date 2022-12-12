import math
from collections import defaultdict

def solution(fees, records):
    answer = []
    defaultTime, defaultFee, unitTime, unitFee = fees[0], fees[1], fees[2], fees[3]
    last_time = 23 * 60 + 59

    calculate = defaultdict(int)
    total_timespan = defaultdict(int)
    result = defaultdict(int)

    for record in records:
        re = record.split()
        hour, min = map(int, re[0].split(":"))
        min = hour * 60 + min
        carnum = re[1]
        inout = re[2]

        if inout == 'IN':
            calculate[carnum] = min
        elif inout == 'OUT':
            in_time = calculate[carnum]
            out_time = min

            total_timespan[carnum] += (out_time - in_time)
            # time_span = out_time - in_time
            # if time_span <= defaultTime:
            #     result[carnum] = defaultFee
            # else:
            #     time_span -= defaultTime
            #     cost = math.ceil(time_span / unitTime) * unitFee
            #     result[carnum] = defaultFee + cost
            del(calculate[carnum])      # 나간 차량 삭제

    # 아직 안나간 차량 계산
    for carnum, in_time in calculate.items():
        time_span = last_time - in_time
        total_timespan[carnum] += time_span

    # 누적 주차 시간에 따른 주차 요금 계산
    for carnum, time_span in total_timespan.items():
        if time_span <= defaultTime:
            result[carnum] = defaultFee
        else:
            time_span -= defaultTime
            cost = math.ceil(time_span / unitTime) * unitFee
            result[carnum] = defaultFee + cost

    answer = [x[1] for x in sorted(result.items())]
    return answer