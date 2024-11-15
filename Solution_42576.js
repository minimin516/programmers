function solution(participant, completion) {
    var answer = "";
    const reduceParticipantInfo = getPersonInfo(participant);
    const reduceCompletionInfo = getPersonInfo(completion);

    const participants = Object.keys(reduceParticipantInfo);
    participants.forEach((name) => {
        if (reduceParticipantInfo[name] !== reduceCompletionInfo[name]) {
            answer = name;
            return;
        }
    });
    return answer;
}

function getPersonInfo(datas) {
    return datas.reduce((curr, name) => {
        curr[name] ? curr[name]++ : (curr[name] = 1);
        return curr;
    }, {});
}
