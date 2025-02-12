function solution(maps) {
    maps = maps.map((m) => m.split(""));
    const drow = [-1, 1, 0, 0];
    const dcol = [0, 0, -1, 1];
    let sr, sc, er, ec, lr, lc;
    for (let i = 0; i < maps.length; i += 1) {
        for (let j = 0; j < maps[0].length; j += 1) {
            if (maps[i][j] === "S") [sr, sc] = [i, j];
            else if (maps[i][j] === "E") [er, ec] = [i, j];
            else if (maps[i][j] === "L") [lr, lc] = [i, j];
        }
    }
    let cmaps = maps.map((m) => m.slice());
    const q = [];
    const bfs = (r, c, v) => {
        q.push([r, c]);
        cmaps[r][c] = v;
        while (q.length) {
            const [a, b] = q.shift();
            for (let i = 0; i < 4; i += 1) {
                const [dr, dc] = [a + drow[i], b + dcol[i]];
                if (
                    dr < 0 ||
                    dc < 0 ||
                    dr >= maps.length ||
                    dc >= maps[0].length
                )
                    continue;
                if (cmaps[dr][dc] === "X" || cmaps[dr][dc] < 10000) continue;
                else {
                    cmaps[dr][dc] = cmaps[a][b] + 1;
                    q.push([dr, dc]);
                }
            }
        }
        return cmaps;
    };
    let t = bfs(sr, sc, 0)[lr][lc];
    if (t !== "L") {
        cmaps = maps.map((m) => m.slice());
        t = bfs(lr, lc, t)[er][ec];
    } else return -1;
    return t !== "E" ? t : -1;
}
