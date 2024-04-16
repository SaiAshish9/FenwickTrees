package com.sai.designPatterns.fenwickTrees;

// [i - 2^r + 1, i],

class FenwickTree {
    private int[] tree;

    public FenwickTree(int size) {
        tree = new int[size + 1];
    }

    // Update the value at index i by adding delta
    public void update(int i, int delta) {
        while (i < tree.length) {
            tree[i] += delta;
            i += i & (-i); // Move to the next node
        }
    }
//    in Fenwick trees, "i & (-i)" is used to efficiently
//    find the least significant bit (LSB) that is set to 1 in the binary representation of "i".

    // Query the prefix sum up to index i
    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & (-i); // Move to the parent node
        }
        return sum;
    }

    // Query the range sum from index start to index end
    public int queryRange(int start, int end) {
        return query(end) - query(start - 1);
    }

//    i's compliment = invert bits of i - 1

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9, 11};
        FenwickTree fenwickTree = new FenwickTree(nums.length);

        // Build Fenwick Tree
        for (int i = 0; i < nums.length; i++) {
            fenwickTree.update(i + 1, nums[i]);
        }
//      tree: [0, 1, 4, 5, 16, 9, 20]
//      Query prefix sum up to index 3
        int prefixSum3 = fenwickTree.query(3);
        System.out.println("Prefix sum up to index 3: " + prefixSum3);

        // Update value at index 2 to 6
        fenwickTree.update(2, 6);

        // Query range sum from index 1 to index 4
        int rangeSum1to4 = fenwickTree.queryRange(1, 4);
        System.out.println("Range sum from index 1 to index 4: " + rangeSum1to4);
    }
}

// This implementation provides methods to update the Fenwick Tree and
// query the prefix sum up to a specific index or the range sum between two indices.
// You can create an instance of FenwickTree with
// the desired size and use its methods to perform updates and queries efficiently.