def solution(sizes):
    for s in sizes:
        if s[0] < s[1]:
            s[0], s[1] = s[1], s[0]

    sizes.sort(reverse=True)
    max_w, max_h = 0, 0
    for w, h in sizes:
        max_w = max(max_w, w)
        max_h = max(max_h, h)

    return max_w * max_h