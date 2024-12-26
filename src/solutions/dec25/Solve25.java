package solutions.dec25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solve25 {
    List<int[]> locks;
    List<int[]> keys;
    public int solveA(List<String> input){
        int result = 0;
        locks = new ArrayList<>();
        keys = new ArrayList<>();

        createLocksAndKeys(input);
        for(int[] lock: locks){
            for(int[] key: keys){
                boolean fits = true;
                for(int i = 0; i < lock.length; i++){
                    if(lock[i] + key[i] > 7){
                        fits = false;
                        break;
                    }
                }
                if(fits){
                    result++;
                }
            }
        }
        return result;
    }

    private void createLocksAndKeys(List<String> input) {
        int[] createLockOrKey = new int[]{0,0,0,0,0};
        boolean lock = false;
        int counter = 0;
        for(String s: input){
            if(s.isEmpty()){
                if(lock && counter == 7){
                    counter = 0;
                    locks.add(createLockOrKey);
                }else if(counter == 7){
                    counter = 0;
                    keys.add(createLockOrKey);
                }
                createLockOrKey = new int[]{0,0,0,0,0};
            }else{
                if(counter == 0 && s.contains("#")){
                    lock = true;
                }else if(counter == 0){
                    lock = false;
                }
                counter++;
                char[] chars = s.toCharArray();
                for(int i = 0; i < chars.length; i++){
                    if(chars[i] == '#'){
                        createLockOrKey[i]++;
                    }
                }
            }
        }
        if(lock){
            locks.add(createLockOrKey);
        }else{
            keys.add(createLockOrKey);
        }
    }
}
