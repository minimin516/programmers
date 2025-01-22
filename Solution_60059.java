public class Solution {
  public boolean solution(int[][] key, int[][] lock) {
    int size = lock.length-1;

    for (int i=0; i<4; i++) {
      key = rotate(key);
      int[][] padKeys = pad(key, size);
      for (int j=0; j<padKeys.length-size; j++) {
        for (int k=0; k<padKeys.length-size; k++) {
          if (isValid(lock, padKeys, j, k)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  private boolean isValid(int[][] lock, int[][] padKeys, int j, int k) {
    for (int l=0; l<lock.length; l++) {
      for (int m=0; m<lock.length; m++) {
        if (lock[l][m]+padKeys[j+l][k+m] != 1) {
          return false;
        }
      }
    }
    return true;
  }

  private int[][] pad(int[][] key, int size) {
    int[][] result = new int[key.length + size * 2][key.length + size * 2];

    for (int i = 0; i < key.length; i++) {
      for (int j = 0; j < key.length; j++) {
        result[size+i][size+j] = key[i][j];
      }
    }
    return result;
  }

  private int[][] rotate(int[][] key) {
    int[][] result = new int[key.length][key.length];
    for (int i = 0; i < key.length; i++) {
      for (int j = 0; j < key.length; j++) {
        result[i][j] = key[key.length-1-j][i];
      }
    }
    return result;
  }

  private int[][] copy(int[][] key) {
    int[][] result = new int[key.length][key.length];
    for (int i = 0; i < key.length; i++) {
      for (int j = 0; j < key.length; j++) {
        result[i][j] = key[i][j];
      }
    }
    return result;
  }
}