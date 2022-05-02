package BaekJoon5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreeTraverse_1991 {
    static int N;
    static ArrayList<Node> nodeList = new ArrayList<>();
    static StringBuilder preString = new StringBuilder();
    static StringBuilder inString = new StringBuilder();
    static StringBuilder postString = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String data = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            Node inputNode = new Node(data);
            inputNode.left = new Node(left);
            inputNode.right = new Node(right);

            nodeList.add(inputNode);
        }
    }

    //pre-order root-left-right
    private static void pre_order(Node node){

    }

    //in-order left-root-right

    //post-order left-right-root

    private static class Node{
        String data;
        Node left;
        Node right;

        public Node(String data){
            this.data = data;
        }
    }
}
