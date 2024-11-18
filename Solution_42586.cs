using System;
using System.Collections.Generic;

public class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = new int[1];
        
        List<int> lAnswer = new List<int>();
        
        int nAnswerIdx = 0;
        int nTemp = 0;
        
        for(int i =0; i<progresses.Length; i++){
            int nRemind = 100 - progresses[i];
            int nDay = Convert.ToInt32(Math.Ceiling((double)nRemind / speeds[i]));
            
            
            if(nTemp - nDay >= 0) lAnswer[nAnswerIdx-1]++;
            else{
                nTemp = nDay;
                lAnswer.Add(1);
                nAnswerIdx++;
            }
            answer = lAnswer.ToArray();
            
        }
        return answer;
    }
}