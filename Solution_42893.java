import java.util.*;
import java.util.regex.*;
class Solution {
    static Map<String, Site> map = new HashMap<>(); 
    public int solution(String word, String[] pages) {
        String regex1 = "<meta property=\"og:url\" content=\"https://.*?\"";
        String regex2 = "<a href=\"https://.*?\">";
        String regex3 = "https://[^\"]+";
        String regex4 = "(?<=[^A-Za-z])" + word + "(?=[^A-Za-z])";
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);
        // 대소문자 상관없이
        Pattern pattern4 = Pattern.compile(regex4, Pattern.CASE_INSENSITIVE);
        
        for(int i = 0; i < pages.length; i++){
            String url = findHomeUrl(pages[i], pattern1, pattern3);
            ArrayList<String> links = findLink(pages[i], pattern2, pattern3);
            int score = findWordMatch(pages[i], pattern4);
            Site site = new Site(i, Double.valueOf(score), links);
            map.put(url, site);
        }
        ArrayList<Site> list = new ArrayList<>(map.values());
        
        
        for(Site cur : list){
            for(String url : cur.links){
                if(map.containsKey(url))
                map.get(url).match_score += cur.offer_score;
            }
        }
        
        list.sort((o1, o2) -> {
            int result = o1.match_score.compareTo(o2.match_score);
            if(result != 0) return -result;
            return o1.index - o2.index;
        });
        
        return list.get(0).index;
    }
    
    public static int findWordMatch(String page, Pattern pattern){
        int cnt = 0;
        Matcher matcher = pattern.matcher(page);
        while(matcher.find()){
            cnt++;
        }
        return cnt;
    }
    
    public static ArrayList<String> findLink(String page, Pattern pattern1, Pattern pattern2){
        ArrayList<String> list = new ArrayList<>();
        Matcher matcher1 = pattern1.matcher(page);
        while(matcher1.find()){
            Matcher matcher2 = pattern2.matcher(matcher1.group());
            if(matcher2.find()){
                list.add(matcher2.group());
            }
        }
        return list;
    }
    
    public static String findHomeUrl(String page, Pattern pattern1, Pattern pattern2){
        Matcher matcher1 = pattern1.matcher(page);  
        if(matcher1.find()){
            Matcher matcher2 = pattern2.matcher(matcher1.group());
            if(matcher2.find()){
                return matcher2.group();
            }
        }
        return "";
    }

    static class Site {
        int index;
        Double score;
        ArrayList<String> links;
        Double offer_score;
        Double match_score;
        Site(int index, double score, ArrayList<String> links){
            this.index = index;
            this.score = score;
            this.links = links;
            this.offer_score = score / links.size();
            this.match_score = score;
        }
    }
}