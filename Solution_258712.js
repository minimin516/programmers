/**  이번달 두 사람 간 선물을 더 많이 준 사람에게 다음달에 적게 받은 사람이 선물 하나를 줘야함
     만약 두 사람의 기록이 동일 할 경우 선물 지수에 따라 선물을 하나 더 줄 사람이 정해진다.
        -. 선물지수 : 전체 선물 준 수 - 전체 선물 받은 수
    !! 원하는 결과 : 다음 달에 선물을 가장 많이 받은 사람의 선물 수
*/

function initFriends(friends, giver = "") {
    var rtnFriends = {};
    var giverCntInfo = {};
    friends.forEach((data) => {
        if (giver === "") rtnFriends[data] = initFriends(friends, data);
        else rtnFriends[data] = 0;
    });
    return rtnFriends;
}

function getAnswer(friends, giveInfo, receiveInfo) {
    var totalGive = [];

    friends.forEach((data, idx) => {
        const arrKey = Object.keys(giveInfo[data]);
        const arrGive = Object.values(giveInfo[data]);
        const arrReceive = Object.values(receiveInfo[data]);
        const giveCnt = arrGive.reduce((a, b) => a + b, 0);
        const receiveCnt = arrReceive.reduce((a, b) => a + b, 0);

        var nextMonthReceiveCnt = 0;
        for (var i in arrGive) {
            if (arrGive[i] - arrReceive[i] === 0) {
                const arrComparGive = Object.values(giveInfo[arrKey[i]]);
                const arrComparReceive = Object.values(receiveInfo[arrKey[i]]);
                const comparGiveCnt = arrComparGive.reduce((a, b) => a + b, 0);
                const comparReceiveCnt = arrComparReceive.reduce(
                    (a, b) => a + b,
                    0
                );
                if (giveCnt - receiveCnt > comparGiveCnt - comparReceiveCnt) {
                    nextMonthReceiveCnt += 1;
                }
            } else {
                nextMonthReceiveCnt += arrGive[i] - arrReceive[i] > 0 ? 1 : 0;
            }
        }
        totalGive.push(nextMonthReceiveCnt);
    });
    return Math.max.apply(null, totalGive);
}

function solution(friends, gifts) {
    var answer = 0;
    var giveInfo = initFriends(friends);
    var receiveInfo = initFriends(friends);
    gifts.forEach((data) => {
        var giverAndReceiver = data.split(" "); // 0 : giver, 1 : receiver
        giveInfo[giverAndReceiver[0]][giverAndReceiver[1]] += 1;
        receiveInfo[giverAndReceiver[1]][giverAndReceiver[0]] += 1;
    });
    answer = getAnswer(friends, giveInfo, receiveInfo);
    return answer;
}
