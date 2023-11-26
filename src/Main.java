import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class node implements Comparable<node>{
    private int frq;
    private char letter;
    node left,right;
    public node(node left, node right) {
        this.frq = left.getFrq()+right.getFrq();
        this.left = left;
        this.right = right;
    }

    public node(char l,int frq) {
        this.letter=l;
        this.frq=frq;
        left =null;
        right=null;
    }

    @Override
    public int compareTo(node o) {
        return Integer.compare(frq,o.frq);
    }



    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getFrq() {
        return frq;
    }

    public char getLetter() {
        return letter;
    }
}


class huffmanoperations {
    static Map<Character, String> encodeTb = new HashMap<Character, String>();
    static Map<String, Character> decodeTb = new HashMap<String, Character>();

    public static Map<Character, Integer> calcfreq(String s) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (freq.containsKey(s.charAt(i))) {
                freq.put(s.charAt(i), freq.get(s.charAt(i)) + 1);
            } else {
                freq.put(s.charAt(i), 1);
            }
        }
        return freq;
    }

    public static node formHuffmanTree(String s) {
        PriorityQueue<node> pq = new PriorityQueue<>();
        Map<Character, Integer> charFreq = huffmanoperations.calcfreq(s);
        charFreq.forEach((key, value) -> pq.add(new node(key, value)));
        while (pq.size() > 1) {
            pq.add(new node(pq.poll(), pq.poll()));
        }
        return pq.poll();
    }

    public static void generateTb(node root, String s) {
        if (root.left == null && root.right == null) {
            encodeTb.put(root.getLetter(), s);
            decodeTb.put(s, root.getLetter());
        } else {
            generateTb(root.left, s.concat("0"));
            generateTb(root.right, s.concat("1"));
        }
    }

    public static String encode(String s) {
        node r = formHuffmanTree(s);
        generateTb(r, "");
        String res = "";
        for (int i = 0; i < s.length(); ++i) {
            res += encodeTb.get(s.charAt(i));
        }

        return res;

    }

    public static String decode(String s) {
        String temp = "", res = "";
        for (int i = 0; i < s.length(); ++i) {
            temp += s.charAt(i);
            if (decodeTb.containsKey(temp)) {
                res += decodeTb.get(temp);
                temp = "";
            }
        }

        return res;

    }

}

public class Main {
    public static void main(String[] args) {

    String s="bigbobbitesbananas";
    String sa="11100011111011011111000000000100111101010101010101001";
    String res = huffmanoperations.encode(s);
    res = huffmanoperations.decode(res);
    System.out.println(res);
    }
}