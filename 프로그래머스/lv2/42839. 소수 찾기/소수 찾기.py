import math
def solution(numbers):
    def dfs(idx, visited, tmp):
        def isPrime(num):
            if num == 0:
                return False
            for i in range(2, int(math.sqrt(num))+1):
                if num % i == 0:
                    return False
            return True


        global n
        global primeSet
        if idx == n:
            if tmp == '':
                return
            rst = int(tmp)
            if(rst != 1 and isPrime(rst)):
                # print(rst)
                primeSet.add(rst)
            return

        # 추가 안하기
        dfs(idx+1, visited, tmp)
        
        # 추가하기
        for i in range(n):
            if visited[i] == False:
                visited[i] = True
                dfs(idx + 1, visited, tmp + numbers[i])
                visited[i] = False


    answer = 0
    global n
    n = len(numbers)

    global primeSet
    primeSet = set()

    visited = [False]*n
    dfs(0, visited, "")
    answer = len(primeSet)
    return answer