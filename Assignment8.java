
// 1. Solution


public class LowestASCIIDeleteSum {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Fill in the dp array
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        LowestASCIIDeleteSum solution = new LowestASCIIDeleteSum();
        String s1 = "sea";
        String s2 = "eat";
        System.out.println(solution.minimumDeleteSum(s1, s2));
        // Output: 231
    }
}



// 2. Solution


import java.util.Stack;

public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        Stack<Integer> leftParentheses = new Stack<>();
        Stack<Integer> stars = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                leftParentheses.push(i);
            } else if (ch == '*') {
                stars.push(i);
            } else if (ch == ')') {
                if (!leftParentheses.isEmpty()) {
                    leftParentheses.pop();
                } else if (!stars.isEmpty()) {
                    stars.pop();
                } else {
                    return false;
                }
            }
        }

        while (!leftParentheses.isEmpty() && !stars.isEmpty()) {
            if (leftParentheses.pop() > stars.pop()) {
                return false;
            }
        }

        return leftParentheses.isEmpty();
    }

    public static void main(String[] args) {
        ValidParenthesisString solution = new ValidParenthesisString();
        String s = "()";
        System.out.println(solution.checkValidString(s)); // Output: true
    }
}



// 3. Solution


public class MinimumStepsToMakeStringsEqual {
    public int minSteps(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        MinimumStepsToMakeStringsEqual solution = new MinimumStepsToMakeStringsEqual();
        String word1 = "sea";
        String word2 = "eat";
        System.out.println(solution.minSteps(word1, word2)); // Output: 2
    }
}




// 4. Solution


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    private int index = 0;

    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        return parseTree(s);
    }

    private TreeNode parseTree(String s) {
        if (index >= s.length()) {
            return null;
        }

        // Find the value of the current node
        int val = 0;
        boolean negative = false;
        char ch = s.charAt(index);

        if (ch == '-') {
            negative = true;
            index++;
        }

        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            val = val * 10 + (s.charAt(index) - '0');
            index++;
        }

        if (negative) {
            val = -val;
        }

        // Create the current node
        TreeNode node = new TreeNode(val);

        // Check if there is a left subtree
        if (index < s.length() && s.charAt(index) == '(') {
            index++; // Move past the '('
            node.left = parseTree(s);
        }

        // Check if there is a right subtree
        if (index < s.length() && s.charAt(index) == '(') {
            index++; // Move past the '('
            node.right = parseTree(s);
        }

        // Move past the ')' if present
        if (index < s.length() && s.charAt(index) == ')') {
            index++;
        }

        return node;
    }

    // Function to traverse the tree in-order and return the values as a list
    private List<Integer> inorderTraversal(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        if (node != null) {
            result.addAll(inorderTraversal(node.left));
            result.add(node.val);
            result.addAll(inorderTraversal(node.right));
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "4(2(3)(1))(6(5))";
        Solution solution = new Solution();
        TreeNode root = solution.str2tree(s);

        List<Integer> result = solution.inorderTraversal(root);
        System.out.println(result); // Output: [4, 2, 6, 3, 1, 5]
    }
}



// 5. Solution


public class Solution {
    public int compress(char[] chars) {
        int writeIndex = 0;
        int currentIndex = 0;
        
        while (currentIndex < chars.length) {
            char currentChar = chars[currentIndex];
            int count = 0;
            
            // Count consecutive repeating characters
            while (currentIndex < chars.length && chars[currentIndex] == currentChar) {
                currentIndex++;
                count++;
            }
            
            // Write the character and its count to the array
            chars[writeIndex++] = currentChar;
            if (count > 1) {
                char[] countArray = Integer.toString(count).toCharArray();
                for (char ch : countArray) {
                    chars[writeIndex++] = ch;
                }
            }
        }
        
        return writeIndex;
    }
}



// 6. Solution


import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        int[] patternCount = new int[26];
        int[] windowCount = new int[26];

        // Count the characters in pattern p
        for (char ch : p.toCharArray()) {
            patternCount[ch - 'a']++;
        }

        int windowSize = p.length();

        // Initialize the window with characters from the start of s
        for (int i = 0; i < windowSize; i++) {
            windowCount[s.charAt(i) - 'a']++;
        }

        // Compare character counts and move the window through s
        for (int i = windowSize; i < s.length(); i++) {
            if (matches(patternCount, windowCount)) {
                result.add(i - windowSize);
            }

            // Move the window by decrementing the count of the character at the start and
            // incrementing the count of the character at the end of the window
            windowCount[s.charAt(i - windowSize) - 'a']--;
            windowCount[s.charAt(i) - 'a']++;
        }

        // Check for the last window
        if (matches(patternCount, windowCount)) {
            result.add(s.length() - windowSize);
        }

        return result;
    }

    // Helper method to check if the character counts in the window match the pattern
    private boolean matches(int[] patternCount, int[] windowCount) {
        for (int i = 0; i < 26; i++) {
            if (patternCount[i] != windowCount[i]) {
                return false;
            }
        }
        return true;
    }
}



// 7. Solution


import java.util.Stack;

public class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        StringBuilder currentStr = new StringBuilder();
        int count = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                count = count * 10 + (ch - '0');
            } else if (ch == '[') {
                countStack.push(count);
                strStack.push(currentStr.toString());
                count = 0;
                currentStr = new StringBuilder();
            } else if (ch == ']') {
                StringBuilder temp = new StringBuilder(strStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(currentStr);
                }
                currentStr = temp;
            } else {
                currentStr.append(ch);
            }
        }

        return currentStr.toString();
    }
}



// 8. Solution



public class Solution {
    public boolean isSwapEqual(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        int diffCount = 0;
        int[] diffIndexes = new int[2];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (diffCount >= 2) {
                    return false; // More than 2 differences, cannot swap
                }
                diffIndexes[diffCount++] = i;
            }
        }

        if (diffCount != 2) {
            return false; // Not exactly 2 differences, cannot swap
        }

        // Check if swapping the characters at the two different positions makes s equal to goal
        return s.charAt(diffIndexes[0]) == goal.charAt(diffIndexes[1]) &&
               s.charAt(diffIndexes[1]) == goal.charAt(diffIndexes[0]);
    }
}
