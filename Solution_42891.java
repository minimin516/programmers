import java.util.*;

class Solution {

    public static int solution(int[] food_times, long k) {
        List<Food> foodList = new LinkedList<>();
        for (int i = 0; i < food_times.length; i++) {
            foodList.add(new Food(i + 1, food_times[i]));
        }
        Collections.sort(foodList, Comparator.comparingInt(o -> o.time));

        int i = 0;
        int foodCnt = foodList.size();
        int preTime = 0;
        for (Food food : foodList) {
            long diff = food.time - preTime;
            if (diff > 0) {
                long spend = diff * foodCnt;
                if (k - spend >= 0) {
                    k -= spend;
                    preTime = food.time;
                } else {
                    k = k % foodCnt;
                    foodList.subList(i, foodList.size()).sort(Comparator.comparingInt(o -> o.order));
                    return foodList.get(i + (int) k).order;
                }
            }
            i++;
            foodCnt--;
        }
        return -1;
    }

    public static class Food {
        int order;
        int time;

        public Food(int order, int time) {
            this.order = order;
            this.time = time;
        }
    }
}