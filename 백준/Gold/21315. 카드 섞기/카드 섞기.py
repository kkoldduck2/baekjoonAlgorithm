# k < 10
n = int(input())
before = [x for x in range(1,n+1)]
after = list(map(int, input().split()))

# arr에서 2**k개의 카드를 더미의 맨 위로 올린다. 그리고 이는 i번째 단계이다.
def magic(arr, k, i):
    # print("k : ",k,", arr : ", arr)
    if len(arr) == 1 :
        return arr[:]

    # arr에서 2**(K - i + 1)개의 카드를 맨 앞으로 가져온다.
    back = arr[:len(arr) - 2**(k-i+1)]
    # print("back : ",back)
    front = magic(arr[len(arr) - 2**(k-i+1):], k, i+1)
    # print("front : ", front)
    rst = front + back
    return rst


# 첫번째
# rst1 = magic(before, 2, 1)
# print("최종 결과 : ", rst1)

for k1 in range(1, 10):
    if 2**k1 > n:
        break
    rst1 = magic(before, k1, 1)
    # print("rst1 : ", rst1)
    flag = False
    # 두번째
    for k2 in range(1, 10):
        if 2**k2 > n:
            break
        rst2 = magic(rst1, k2, 1)
        # print("rst2 : ", rst2)
        if rst2 == after:
            flag = True
            print(k1, k2)
            break
    if flag:
        break






