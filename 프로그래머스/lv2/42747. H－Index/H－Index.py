def solution(citations):
    # x번 이상 인용된 논문의 index를 리턴한다.
    def binarySearch(x):
        global n
        start, end = 0, len(citations)-1
        while start < end:
            mid = (start+end)//2
            if citations[mid] >= x:
                end = mid
            else:
                start = mid+1
        return start

    citations.sort()
    n = len(citations)
    h_idx = -1
    max_val = citations[-1]
    for x in range(max_val+1):          # x : 인용 횟수
        # x번 이상 인용된 논문의 index -> binary search
        i = binarySearch(x)
        # print('x = ',x, ", n-i : ", n-i)
        if n-i >= x:
            h_idx = max(h_idx, x)

    return h_idx