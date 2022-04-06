package com.exercise.prep2022;

public class MedianOfSortedArrays {

    /**
     * Given a number, finds the index of the closest smaller number in the array.
     */
    private int findClosestIndex(int value, int[] nums, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return endIndex - 1;
        }
        if (endIndex <= startIndex) {
            return startIndex;
        }
        int len = endIndex - startIndex;
        int mid = Math.round(len/2) + startIndex;
        if (len == 1) {
            if (nums[len - 1] >= value)  {
                return startIndex - 1;
            } else {
                return endIndex;
            }
        }
        if (value < nums[mid] && value > nums[mid - 1]) {
            return mid - 1;
        }
        if (value == nums[mid]) {
            return mid;
        } else if (value < nums[mid]) {
            return findClosestIndex(value, nums, startIndex, (startIndex + mid));
        } else {
            return findClosestIndex(value, nums, (startIndex + mid), endIndex);
        }
    }

    private int[] mergeArraysInlineSlow(final int[] nums1, final int[] nums2) {
        final int len1 = nums1.length;
        final int len2 = nums2.length;
        final int medianLowIndex = (len1+len2)/2;
        int[] mergedArray = new int[(len1+len2)];

        int i = 0;
        int j = 0;
        for (int k = 0; k < (len1 + len2); k++) {
            if ((i > len1 - 1) && (j > len2 - 1)) {
                return mergedArray;
            }
            if (i > len1 - 1) {
                mergedArray[k] = nums2[j];
                j++;
            } else if (j > len2 - 1) {
                mergedArray[k] = nums1[i];
                i++;
            } else if (nums1[i] < nums2[j]) {
                mergedArray[k] = nums1[i];
                i++;
            } else {
                mergedArray[k] = nums2[j];
                j++;
            }
            // If we have reached the median value, and every value hereafter is greater
            // than the value at the median index, we can safely break from the loop.
            if ((k > medianLowIndex + 1) && // Computed up to the median value(s)
                (i == len1 || nums1[i] > mergedArray[k]) && // Exhausted first array or least number is greater than median
                (j == len2 || nums2[j] > mergedArray[k])) { // Exhausted second array or least number is greater than median
                break;
            }
        }

        return mergedArray;
    }
    
    private int[] mergeArraysInline(final int[] nums1, final int[] nums2) {
        final int len1 = nums1.length;
        final int len2 = nums2.length;
        final int medianLowIndex = (len1+len2)/2;
        int[] mergedArray = new int[medianLowIndex + 2];

        int i = 0;
        int j = 0;
        for (int k = 0; k < (medianLowIndex + 2); k++) {
            if ((i > len1 - 1) && (j > len2 - 1)) {
                return mergedArray;
            }
            if (i > len1 - 1) {
                mergedArray[k] = nums2[j];
                j++;
            } else if (j > len2 - 1) {
                mergedArray[k] = nums1[i];
                i++;
            } else if (nums1[i] < nums2[j]) {
                mergedArray[k] = nums1[i];
                i++;
            } else {
                mergedArray[k] = nums2[j];
                j++;
            }
        }

        return mergedArray;
    }

    public double getMedianOfArrays(final int[] nums1, final int[] nums2) {
        final int len1 = nums1.length;
        final int len2 = nums2.length;
        final int medianLowIndex = (len1 + len2) / 2;
        final boolean midIsMedian = ((len1 + len2) % 2 == 1);
        final int[] mergedArray = mergeArraysInline(nums1, nums2);

        if (midIsMedian) {
            System.out.println("Median value is " + (double)mergedArray[medianLowIndex]);
            return (double)mergedArray[medianLowIndex];
        } else {
            double median = ((double)mergedArray[medianLowIndex] + (double)mergedArray[medianLowIndex - 1])/2;
            System.out.println("Median value is " + median);
            return median;
        }
    }
    
    public int[] getClosestValue(int value, int[] nums) {
        System.out.println("Input Array: " + Arrays.toString(nums));
        int closestIndex = findClosestIndex(value, nums, 0, nums.length);
        int closestValue = nums[(closestIndex < 0) ? 0 : ((closestIndex == nums.length) ? (nums.length - 1) : closestIndex)];
        System.out.println("Closest index: " + closestIndex + "; closest value: " + closestValue);
        return new int[]{closestIndex, closestValue};
    }
}
