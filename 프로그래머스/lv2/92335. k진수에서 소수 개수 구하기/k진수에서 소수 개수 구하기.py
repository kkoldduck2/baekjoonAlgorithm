import math

def solution(n, k):
    def isPrime(x):
        # 2부터 x의 제곱근까지의 모든 수를 확인하며
        for i in range(2, int(math.sqrt(x)) + 1):
            # x가 해당 수로 나누어떨어진다면
            if x % i == 0:
                return False # 소수가 아님
        return True # 소수임

    def convert(n, q):
        rev_base = ''

        while n > 0:
            n, mod = divmod(n, q)
            rev_base += str(mod)

        return rev_base[::-1]

    target = convert(n, k)
    answer = 0

    nums = target.split('0')

    for num in nums:
        if len(num) == 0 :
            continue
        tmpnm = int(num)
        if tmpnm != 1 and isPrime(tmpnm) and num.count('0') == 0:
            # print('조건 충족 : ', tmpnm)
            answer += 1

    return answer