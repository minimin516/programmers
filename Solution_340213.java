import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        LocalTime videoTime = getTime(video_len.split(":"));
        LocalTime posTime = getTime(pos.split(":"));
        LocalTime opstartTime = getTime(op_start.split(":"));
        LocalTime opEndTime = getTime(op_end.split(":"));
        
        posTime = checkSkOpening(opstartTime, opEndTime, posTime);
        
        for(int i=0; i<commands.length; i++){
            posTime = goPrevNext(commands[i], videoTime, posTime);
            posTime = checkSkOpening(opstartTime, opEndTime, posTime);
        }
        
        return posTime.format(DateTimeFormatter.ofPattern("mm:ss"));
    }
    
    private static LocalTime goPrevNext(String command, LocalTime videoTime, LocalTime posTime){
        if(command.equals("prev")){
            if(posTime.getMinute() == 0 && posTime.getSecond() < 10){
                return LocalTime.of(0, 0, 0);
            }else{
                return posTime.minusSeconds(10);
            }
        }else{
            LocalTime nextTime = posTime.plusSeconds(10);
            if(nextTime.isAfter(videoTime)){
                return videoTime;
            }else{
                return nextTime;
            }
        }
    }

    
    private static LocalTime checkSkOpening(LocalTime opstartTime, LocalTime opEndTime, LocalTime posTime){
        if(!posTime.isBefore(opstartTime) && !posTime.isAfter(opEndTime)){
            return opEndTime;
        }else{
            return posTime;
        }
    }
    
    private static LocalTime getTime(String[] time){
        int min = Integer.parseInt(time[0]);
        int sec = Integer.parseInt(time[1]);

        return LocalTime.of(0, min, sec);
    }

}

