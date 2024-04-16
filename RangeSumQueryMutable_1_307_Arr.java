package com.sai.designPatterns.fenwickTrees;

class RangeSumQueryMutable_1_307_Arr {
    int[] nums;
    int[] fenwickTree;

    public RangeSumQueryMutable_1_307_Arr(int[] nums) {
        this.nums = nums;
        this.fenwickTree = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            updateFenwickTree(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        int diff = val - nums[index];
        nums[index] = val;
        updateFenwickTree(index + 1, diff);
    }

    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }

    private void updateFenwickTree(int index, int val) {
        while (index < fenwickTree.length) {
            fenwickTree[index] += val;
            index += index & (-index);
        }
    }

    private int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += fenwickTree[index];
            index -= index & (-index);
        }
        return sum;
    }
}
