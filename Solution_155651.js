function solution(book_time) {
    const map = new Map();

    for (const [startTime, endTime] of book_time) {
        let st = calcTime(startTime);
        const et = calcTime(endTime);
        for (; st < et + 10; st++) map.set(st, (map.get(st) || 0) + 1);
    }

    return Math.max(...map.values());
}
function calcTime(time) {
    const [hour, minute] = time.split(':').map(x => +x);
    return hour * 60 + minute;
}
