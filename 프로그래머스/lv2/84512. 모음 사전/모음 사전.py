def solution(word):
    answer = 0
    alpha_list = ['', 'A', 'E', 'I', 'O', 'U']
    all_words = set()

    for i1 in range(1, 6):
        for i2 in range(6):
            for i3 in range(6):
                for i4 in range(6):
                    for i5 in range(6):
                        make_word = alpha_list[i1] + alpha_list[i2] + alpha_list[i3] + alpha_list[i4] + alpha_list[i5]
                        all_words.add(make_word)

    all_words = list(all_words)
    all_words = sorted(all_words)
    # print(all_words)
    answer = all_words.index(word)+1
    return answer