import java.util.*;

class Solution {
	static int answer;

	public int solution(String[] words) {
		answer = 0;
		Node root = new Node();
		for (int i = 0; i < words.length; i++) {
			insert(root, words[i]);
		}
		for (int i = 0; i < words.length; i++) {
			check(root, words[i]);
		}
		return answer;
	}
	// 글자수 확인
	private void check(Node curr, String target) {
		for (char c : target.toCharArray()) {
			curr = curr.children.get(c);
            answer++;
			if (curr.cnt == 1) {
				return;
			}
		}
	}
	
	private void insert(Node curr, String str) {
		for (char c : str.toCharArray()) {
			curr = curr.children.computeIfAbsent(c, l -> new Node());
            curr.cnt++;
		}
	}
}

class Node {
	Map<Character, Node> children;
	int cnt = 0;

	public Node() {
		children = new HashMap<>();
	}
}
