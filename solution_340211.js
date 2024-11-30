const getNextPosition = (r, c, trgtR, trgtC) => {
    if (r !== trgtR) return r > trgtR ? [r - 1, c] : [r + 1, c];
    if (c !== trgtC) return c > trgtC ? [r, c - 1] : [r, c + 1];
    return [r, c];
};

function solution(points, routes) {
    let arr = [];
    let maxIdx = 0;
    routes.forEach((route) => {
        let startPoint = route.shift();
        let history = [points[startPoint - 1]];

        while (route.length) {
            let [nowR, nowC] = history.at(-1);
            let [trgtR, trgtC] = points[route[0] - 1];

            let [nextR, nextC] = getNextPosition(nowR, nowC, trgtR, trgtC);

            history.push([nextR, nextC]);
            if (nextR === trgtR && nextC === trgtC) {
                route.shift();
            }
        }

        maxIdx = Math.max(maxIdx, history.length - 1);
        arr.push(history);
    });

    let answer = 0;
    let idx = 0;
    while (idx <= maxIdx) {
        let points = [];
        for (let i = 0; i < arr.length - 1; i++) {
            for (let j = i + 1; j < arr.length; j++) {
                if (
                    arr[i][idx] &&
                    arr[j][idx] &&
                    arr[i][idx][0] === arr[j][idx][0] &&
                    arr[i][idx][1] === arr[j][idx][1]
                ) {
                    let isInclude = points.some(
                        (point) =>
                            point[0] === arr[i][idx][0] &&
                            point[1] === arr[i][idx][1]
                    );
                    if (!isInclude) {
                        points.push([arr[i][idx][0], arr[i][idx][1]]);
                        ++answer;
                    }
                }
            }
        }
        ++idx;
    }

    return answer;
}
