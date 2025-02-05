import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        List<String> strList1 = getSubStr(str1.toUpperCase());
        List<String> strList2 = getSubStr(str2.toUpperCase());
        
        List<String> mul = new ArrayList<>();
        List<String> sum = new ArrayList<>();
        for(String s1 : strList1) {
        	if(strList2.contains(s1)) {
        		strList2.remove(s1);
    			mul.add(s1);
        	}
        	sum.add(s1);
        }
        
        for(String s2 : strList2) {
        	sum.add(s2);
        }
        if(sum.size()==0) return 65536;
        double jac = (double)mul.size()/sum.size();
        
        jac = Math.floor(jac*65536);
        
        return (int)jac;
    }
	
    static List<String> getSubStr(String str) {
        List<String> list = new ArrayList<>();
        for(int i=0; i<str.length()-1; i++) {
        	char c1 = str.charAt(i);
        	char c2 = str.charAt(i+1);
        	
        	if((int)c1<65 || (int)c1>90) continue;
        	if((int)c2<65 || (int)c2>90) continue;
        	String s = str.substring(i,i+2);
        	list.add(s);
        }
        
        return list;
    }
}