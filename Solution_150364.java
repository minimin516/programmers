import java.util.*;

class Solution {
    public int[] solution(int[][] edges, int[] target) {
        int[] answer = {};
        
        Node[] nodes = new Node[target.length + 1];
        for(int i=1; i<target.length+1; i++) {
            nodes[i] = new Node(i, target[i-1]);
        }
        
        for(int i=0; i<edges.length; i++) {
            nodes[edges[i][0]].childNodes.add(nodes[edges[i][1]]);
        }
        
        ArrayList<Node> leafs = new ArrayList<>();
        for(int i=1; i<target.length+1; i++) {
            if(nodes[i].initialize()) {
                leafs.add(nodes[i]);
            }
        }
        
        boolean isLast = false;
        ArrayList<Node> drops = new ArrayList<>();
        Node root = nodes[1];
        while(!isLast) {
            isLast = true;
            
            Node drop = root.getNextDrop();
            drops.add(drop);
            drop.dropCnt++;
            
            if(drop.dropCnt > drop.trgt) return new int[] {-1}; 
            
            for(Node leaf : leafs) {
                if(3 * leaf.dropCnt < leaf.trgt) {
                    isLast = false;
                    break;
                }
            }
        }
        
        answer = new int[drops.size()];
        
        for(int i=0; i<answer.length; i++) {
            answer[i] = drops.get(i).getCard(); 
        }
	        
        return answer;
    }
    
    
    class Node {
        int idx;
        int trgt;
        int dropCnt;
        boolean isLeaf = false;
        ArrayList<Node> childNodes = new ArrayList<>();
        Node road = null;
        Node next = null;
        
        Node(int idx, int trgt) {
            this.idx = idx;
            this.trgt = trgt;
        }
        
        boolean initialize() {
            if(childNodes.size() == 0) {
                isLeaf = true;
                return isLeaf;
            }
            
            Collections.sort(childNodes, (o1, o2) -> o1.idx - o2.idx);
        
            Node temp = null;
	        
            for (Node child : childNodes) {
                if (road == null) {
                    road = child;
                    temp = road;
                } else {
                    temp.next = child;
                    temp = temp.next;
                }
            }
            
            temp.next = road;
	        
            return isLeaf;
        }
        
        Node getNextDrop() {
            if(isLeaf) {
                return this;
            } else {
                Node temp = road.getNextDrop();
                road = road.next;
                return temp;
            }
        }
        
        int getCard() {
            dropCnt--;
            
            int card = Math.max(1, trgt - 3 * dropCnt);
            trgt -= card;
            
            return card;
        }
    }
}