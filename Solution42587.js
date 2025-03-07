function solution(priorities, location) {
    var answer = 0;
    var queue = priorities.reduce((data, curr, idx) => {
        data.push({ idx: idx, data: curr });
        return data;
    }, []);
    var t = [];
    while (queue.length) {
        const max = queue.reduce((a, b) => (a.data > b.data ? a : b), 0);
        let first = queue.shift();
        max.data <= first.data ? t.push(first) : queue.push(first);
    }
    answer = t.findIndex((n) => n.idx === location);
    return answer + 1;////
}
