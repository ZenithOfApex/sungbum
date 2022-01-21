package SDS;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Boggle_9202 {

    static int W;
    static int[] dr = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dc = {-1, 1, 0, 0, 1, -1, 1, -1};
    static boolean[][] visited;
    static Trie trie = new Trie();
    static HashSet<String> set;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        W = Integer.parseInt(br.readLine());

        for (int i = 0; i < W; i++) {
            String str = br.readLine();
            trie.insert(str);
        }

        br.readLine();

        int b = Integer.parseInt(br.readLine());
        for (int i = 0; i < b; i++) {
            char[][] board = new char[4][4];
            set = new HashSet<>();
            visited = new boolean[4][4];

            for (int r = 0; r < 4; r++) {
                String word = br.readLine();
                for (int c = 0; c < 4; c++) {
                    board[r][c] = word.charAt(c);
                }
            }

            if (i < b - 1) {
                br.readLine();
            }

            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    visited[r][c] = true;
                    dfs(board, r, c, new StringBuilder(board[r][c] + ""));
                    visited[r][c] = false;
                }
            }
            int score =0;
            String longest = "";
            int cnt =0;

            Iterator<String> it = set.iterator();

            while (it.hasNext()) {
                String word = it.next();

                score += getScore(word);
                if (longest.length() < word.length() || (longest.length() == word.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
            cnt = set.size();
            bw.write(score + " " + longest + " " + cnt + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(char[][] board, int r, int c, StringBuilder sb) {
        String word = sb.toString();

        if (trie.contains(word) == -1) {
            return;
        }
        if (trie.contains(word) == 1) {
            set.add(word);
        }
        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if ((0 <= nr && nr < 4) && (0 <= nc && nc < 4) && !visited[nr][nc]) {
                visited[nr][nc] = true;
                sb.append(board[nr][nc]);
                dfs(board, nr, nc, sb);
                visited[nr][nc] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static int getScore(String word) {
        switch (word.length()) {
            case 3:
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 5;
            case 8:
                return 11;
            default:
                return 0;
        }
    }

    public static class Trie {
        private TrieNode rootNode;

        Trie(){
            rootNode = new TrieNode();
        }

        public void insert(String word) {
            TrieNode thisNode = rootNode;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                thisNode = thisNode.getChildNodes().computeIfAbsent(ch, key -> new TrieNode());
            }
            thisNode.setIsLastChar(true);
        }

        public int contains(String word) {
            TrieNode thisNode = rootNode;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                thisNode = thisNode.getChildNodes().get(ch);

                if (thisNode == null) {
                    return -1;
                }
            }
            return thisNode.isLastChar() ? 1 : 0;
        }

        public boolean delete(String word) {
            return delete(rootNode, word, 0);
        }

        public boolean delete(TrieNode thisNode, String word, int idx) {
            char ch = word.charAt(idx++);
            TrieNode childNode = thisNode.getChildNodes().get(ch);

            if (idx == word.length()) {
                if (!childNode.isLastChar()) {
                    return false;
                }
                if (childNode.getChildNodes().isEmpty()) {
                    thisNode.getChildNodes().remove(ch);
                }

                return true;
            }
            if (delete(childNode, word, idx)) {
                if (!childNode.isLastChar() && childNode.getChildNodes().isEmpty()) {
                    thisNode.getChildNodes().remove(ch);
                }
                return true;
            }
            return false;
        }
    }

    public static class TrieNode{
        private HashMap<Character,TrieNode> childNodes;
        private boolean isLastChar;

        TrieNode(){
            childNodes = new HashMap<>();
        }

        public HashMap<Character,TrieNode> getChildNodes() {
            return childNodes;
        }

        public void setIsLastChar(boolean isLastChar) {
            this.isLastChar = isLastChar;
        }

        public boolean isLastChar() {
            return isLastChar;
        }
    }
}
