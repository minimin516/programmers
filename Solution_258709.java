import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
     static int N = 0;
     static List<Integer> arrSelect = new ArrayList<>();
     static int[][] dices;
     static List<Integer> arrA;
     static List<Integer> arrB;
     static int[] answer;
     static int max = Integer.MIN_VALUE;
    
    public int[] solution(int[][] dice) {
        N = dice.length;
        dices = dice;
        answer = new int[N / 2];
        selectDice(0, 0);
        return answer;
    }
    public static void makeArr(int depth, int[][] dice, int sum, List<Integer> arr) {
        if (depth == N / 2) {
            arr.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            int newSum = sum + dice[depth][i];
            makeArr(depth + 1, dice, newSum, arr);
        }
    }

    public static void makeArrAB() {
        arrA = new ArrayList<>();
        arrB = new ArrayList<>();

        int[][] diceA = new int[N / 2][6];
        int[][] diceB = new int[N / 2][6];
        int a = 0, b = 0;
        for (int i = 0; i < N; i++) {
            if (arrSelect.contains(i)) {
                diceA[a] = dices[i];
                a++;
            } else {
                diceB[b] = dices[i];
                b++;
            }
        }

        makeArr(0, diceA, 0, arrA);
        makeArr(0, diceB, 0, arrB);
    }

    public static int winPercent() {
        int count = 0;

        makeArrAB();

        Collections.sort(arrB);
        for (int i = 0; i < arrA.size(); i++) {
            int number = arrA.get(i);

            int left = 0, right = arrB.size() - 1;

            int index = Integer.MIN_VALUE;
            while (left <= right) {
                int middle = (left + right) / 2;

                if (arrB.get(middle) < number) {
                    left = middle + 1;
                    index = Math.max(index, middle);
                } else {
                    right = middle - 1;
                }
            }
            if (index != Integer.MIN_VALUE) {
                count += index + 1;
            }
        }
        return count;
    }

    public static void selectDice(int depth, int s) {
        if (depth == N / 2) {
            int winning = winPercent();
            if (max < winning) {
                max = winning;
                for (int i = 0; i < arrSelect.size(); i++) {
                    answer[i] = arrSelect.get(i) + 1;
                }
            }
            return;
        }
        for (int i = s; i < N; i++) {
            arrSelect.add(i);
            selectDice(depth + 1, i + 1);
            arrSelect.remove(arrSelect.size() - 1);
        }
    }
}