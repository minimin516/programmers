function solution(n, costs) {
    
    let answer = 0;
    let parent = new Array(n).fill(0);
    parent = parent.map((_, idx) => idx);
    
    costs.sort((o1, o2) => o1[2] - o2[2]);
    
    costs.forEach((cost, index) => {
        let st = find(cost[0], parent);
        let ed = find(cost[1], parent);
        
        if (st !== ed) {
            answer += cost[2];
            parent[st] = ed;
        }
    })
    
    return answer;
}

function find (n, parent){
    if (parent[n] === n) return n;
    return parent[n] = find(parent[n], parent);
}
