from collections import defaultdict
def solution(survey, choices):
    answer = ''
    mod = 4
    type_score = defaultdict(int)

    for i in range(len(survey)):
        if choices[i]//mod >= 1:
            type = survey[i][1]
            type_score[type] += choices[i] % mod
        elif choices[i]//mod < 1:
            type = survey[i][0]
            type_score[type] += (mod - choices[i] % mod)

    types = {1: ['R', 'T'], 2: ['C', 'F'], 3: ['J', 'M'], 4: ['A', 'N']}
    # print(type_score)
    for i in range(1, 5):
        if type_score[types[i][0]] < type_score[types[i][1]]:
            answer += types[i][1]
        elif type_score[types[i][0]] > type_score[types[i][1]]:
            answer += types[i][0]
        else:
            answer += sorted(types[i])[0]

    return answer