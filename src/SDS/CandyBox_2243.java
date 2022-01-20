package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class CandyBox_2243 {

    static int n;
    static SegTree st = new SegTree(4 * 1000000);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        n = Integer.parseInt(br.readLine());

        st.init(1, 1, 1000000);

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);

            if (A == 1) {
                long B = Long.parseLong(input[1]);
                int res = binSearch(1, 1000000, B);
                bw.write(res + "\n");
                st.update(1, 1, 1000000, res, -1);
            }

            if (A == 2) {
                int B = Integer.parseInt(input[1]);
                int C = Integer.parseInt(input[2]);
                st.update(1, 1, 1000000, B, C);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }


    public static int binSearch(int left, int right, long n) {
        int pl = left;
        int pr = right;

        do {
            int pc = (pl + pr) / 2;
            long sum = st.sum(1, 1, 1000000, 1, pc);

            if (sum < n) {
                pl = pc + 1;
            } else if (sum >= n) {
                pr = pc - 1;
            }
        } while (pl <= pr);

        return pl;
    }

    static class SegTree{
        long[] tree;

        SegTree(int n) {
            tree = new long[n];
        }

        public long init(int node, int start, int end) {
            if (start == end) return tree[node];
            int mid = (start + end) / 2;

            tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);

            return tree[node];
        }

        public void update(int node, int start, int end, int index, int diff) {
            if (index < start || end < index) return;
            tree[node] += diff;

            if(start==end) return;

            int mid = (start + end) / 2;

            update(node * 2, start, mid, index, diff);
            update(node * 2 + 1, mid + 1, end, index, diff);
        }

        public long sum(int node, int start, int end, int left, int right) {
            if (left > end || right < start) return 0;
            if (left <= start && end <= right) return tree[node];

            int mid = (start + end) / 2;

            return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
        }
    }
}
