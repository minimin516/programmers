function solution(bridge_length, weight, truck_weights) {
    var answer = 0;
    let truck = [...truck_weights];
    let finish = [];
    let passing = [[0, 0]];
    let weightOnBridge = 0;
    let dupCnt = 0;
    while (passing.length || truck.length) {
        if (passing[0][1] === answer) weightOnBridge -= passing.shift()[0];
        // console.log('w eightOnBridge', answer, weightOnBridge)
        if (weightOnBridge + truck[0] <= weight) {
            weightOnBridge += truck[0];
            passing.push([truck.shift(), answer + bridge_length]);
        } else {
            if (passing[0]) answer = passing[0][1] - 1;
        }
        answer++;
    }
    console.log(passing, answer);
    return answer;
}
