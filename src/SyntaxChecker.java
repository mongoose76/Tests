import java.util.*;

/**
 * Created by mongoose on 3/31/2018
 */
public class SyntaxChecker {

    Map<Character, Character> delimiters = new HashMap<Character, Character>();

    public SyntaxChecker() {
        delimiters.put('{', '}');
        delimiters.put('[', ']');
        delimiters.put('(', ')');
    }

    private void stashPush(Stack<Character> stash, Character c) {
        stash.push(c);
        printStash(stash);
    }

    private Character stashPop(Stack<Character> stash) {
        Character c = stash.pop();
        printStash(stash);
        return c;
    }

    public boolean checkSyntax(String s) {

        Stack<Character> stash = new Stack<>();

        for (char c: s.toCharArray()) {
            if (delimiters.containsKey(c)) {
                stashPush(stash, delimiters.get(c));
            } else if (delimiters.containsValue(c)) {
                if (stashPop(stash) != c) {
                    return false;
                }
            }
        }

        return stash.size() == 0;
    }

    public static void main(String[] args) {
        SyntaxChecker t = new SyntaxChecker();

        String s = "{ { () [ ) [ ) } }";
        System.out.println(t.checkSyntax(s));
    }

    void printStash(Stack<Character> stash) {
        System.out.println(Arrays.toString(stash.toArray()));
    }
}
