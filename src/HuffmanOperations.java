import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class node implements Comparable<node>{
    private final int frequency;
    private char letter;
    node left,right;
    public node(node left, node right) {
        this.frequency = left.getFrequency()+right.getFrequency();
        this.left = left;
        this.right = right;
    }

    public node(char l,int frq) {
        this.letter=l;
        this.frequency =frq;
        left =null;
        right=null;
    }

    @Override
    public int compareTo(node o) {
        return Integer.compare(frequency,o.frequency);
    }



    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getFrequency() {
        return frequency;
    }

    public char getLetter() {
        return letter;
    }
}

public class HuffmanOperations {
    static Map<Character, Integer> charFreq = new HashMap<>();
    static Map<Character, String> encodeTb = new HashMap<Character, String>();
    static Map<String, Character> decodeTb = new HashMap<String, Character>();

    public static Map<Character, Integer> CalculateFrequency(String s) {
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

    public static node FormHuffmanTree() {
        PriorityQueue<node> pq = new PriorityQueue<>();
        charFreq.forEach((key, value) -> pq.add(new node(key, value)));

        while (pq.size() > 1) {
            pq.add(new node(pq.poll(), pq.poll()));
        }
        return pq.poll();
    }

    public static void generateTb(node root, String s) {
        if (root.left == null && root.right == null) {
            if(s==""){
                encodeTb.put(root.getLetter(), "0");
                decodeTb.put("0", root.getLetter());
            }else{
                encodeTb.put(root.getLetter(), s);
                decodeTb.put(s, root.getLetter());
            }
        } else {
            generateTb(root.left, s.concat("0"));
            generateTb(root.right, s.concat("1"));
        }
    }

    public static String Encode(String s) {
        charFreq = HuffmanOperations.CalculateFrequency(s);
        node r = FormHuffmanTree();
        encodeTb.clear();
        decodeTb.clear();
        generateTb(r, "");
        String res = "";
        for (int i = 0; i < s.length(); ++i) {
            res += encodeTb.get(s.charAt(i));
        }

        return res;

    }

    public static String Decode(String s) {
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