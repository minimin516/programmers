import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);
        for(int i =0; i<participant.length; i++){
            if(completion.length -1 < i){
                 answer = participant[i];
            }else{
                if(!completion[i].equals(participant[i])){
                    answer = participant[i];
                    return answer;
                }
            }
        }
        /*ArrayList<String> arrCompletion = new ArrayList<>(Arrays.asList(completion));
        for(int i = 0; i<participant.length; i++){
            if(arrCompletion.indexOf(participant[i]) < 0){
                answer = participant[i];
            }else{
                arrCompletion.remove(participant[i]);
            }
        }*/
        return answer;
    }
}
