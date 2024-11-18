using System;
using System.Collections;
using System.Collections.Generic;

using System.Linq;

public class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        List<int> lTruck = new List<int>();
        
        int nIdx = 0;
        int nChkWeight = 0;
        answer = bridge_length;
        
        while(nIdx < truck_weights.Length){
            lTruck.Add(truck_weights[nIdx]);
            
            nChkWeight = lTruck.Sum();
            
            if(nIdx + 1 <truck_weights.Length ){
                if(nChkWeight + truck_weights[nIdx + 1] > weight){
                    answer+=2;
                    lTruck.RemoveAt(0);
                }else answer++;
            }else{
                answer++;
            }
            nIdx++;
        }
        
        return answer;
    }
}