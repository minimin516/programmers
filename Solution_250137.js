function solution(bandage, health, attacks) {
    var answer = health;

    const castTime = bandage[0];
    const timeToHeal = bandage[1];
    const bonusHeal = bandage[2];
    const endTime = attacks[attacks.length - 1][0];

    var successCnt = 0;

    for (var i = 1; i < endTime + 1; i++) {
        var attack = attacks.find((data) => {
            return data[0] === i;
        });
        if (attack) {
            answer -= attack[1];
            successCnt = 0;
            if (answer <= 0) return -1;
        } else {
            successCnt += 1;
            answer =
                answer + timeToHeal >= health ? health : answer + timeToHeal;
            if (successCnt === castTime) {
                answer =
                    answer + bonusHeal >= health ? health : answer + bonusHeal;
                successCnt = 0;
            }
        }
    }

    return answer <= 0 ? -1 : answer;
}
