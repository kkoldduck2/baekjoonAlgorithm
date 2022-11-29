from collections import defaultdict, OrderedDict
def solution(id_list, report, k):
    # 유저 별 신고한 id -> user_report   [id, 신고한 id set]
    user_report = defaultdict(set)

    for rp in report:
        a, b = rp.split()       # a가 b를 신고함
        user_report[a].add(b)

    # 유져 별 신고 당한 횟수 -> user_reported_cnt    [id, 신고 당한 횟수 ]
    user_reported_cnt = defaultdict(int)
    for reported_users in user_report.values():
        for user in reported_users:
            user_reported_cnt[user] += 1

    result = OrderedDict()
    for id in id_list:
        result[id] = 0

    for id, report_users in user_report.items():
        cnt = 0
        for user in report_users:
            if user_reported_cnt[user] >= k:
                cnt += 1
        result[id] = cnt

    # print(list(result.values()))
    return list(result.values())