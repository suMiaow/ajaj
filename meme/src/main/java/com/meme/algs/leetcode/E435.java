package com.meme.algs.leetcode;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class E435 {

    public int eraseOverlapIntervals(int[][] intervals) {

        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int result = 0;
        int lastEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] < lastEnd) {
                result++;
            } else {
                lastEnd = interval[1];
            }
        }

        return result;
    }

    static class Activity {
        int index;
        int start;
        @Getter
        int finish;

        public Activity(int index, int start, int finish) {
            this.index = index;
            this.start = start;
            this.finish = finish;
        }

        @Override
        public String toString() {
            return "a" + index + "[" + start + "," + finish + ")";
        }
    }

    public static void main(String[] args) {
        Activity[] activities = {
                new Activity(1, 2, 4),
                new Activity(0, 1, 3),
                new Activity(2, 3, 5),
        };

        Arrays.sort(activities, Comparator.comparingInt(Activity::getFinish));

        System.out.println(Arrays.toString(activities));

        List<Activity> result = select(activities, activities.length);
        System.out.println(result);
    }

    static List<Activity> select(Activity[] activities, int n) {
        List<Activity> result = new ArrayList<>();
        result.add(activities[0]);
        int lastEnd = activities[0].finish;
        for (int i = 1; i < n; i++) {
            Activity activity = activities[i];
            if (activity.start >= lastEnd) {
                result.add(activity);
                lastEnd = activity.finish;
            }
        }
        return result;
    }

}
