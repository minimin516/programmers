function solution(numbers) {
    const answer = Array.from({ length: numbers.length }, () => -1);
    const idxArr = [];

    for (let i = 0; i < numbers.length; i++) {
        while (idxArr.length && numbers[idxArr[idxArr.length - 1]] < numbers[i]) {
            answer[idxArr.pop()] = numbers[i];
        }
        idxArr.push(i);//
    }
    return answer;
}