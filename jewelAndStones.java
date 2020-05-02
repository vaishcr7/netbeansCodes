Solution
You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3
Example 2:

Input: J = "z", S = "ZZ"
Output: 0
Note:

S and J will consist of letters and have length at most 50.
The characters in J are distinct.
   Show Hint #1  
Java	
1
class Solution {
2
    public int numJewelsInStones(String J, String S) {
3
        Map<Character,Integer> mp=new HashMap<>();
4
        for(int i=0;i<J.length();i++)
5
            mp.put(J.charAt(i),0);
6
        int count=0;
7
        for(int i=0;i<S.length();i++)
8
            if(mp.containsKey(S.charAt(i)))
9
                count++;
10
        return count;
11
    }
12
}

Very Basic question
