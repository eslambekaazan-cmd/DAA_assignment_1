package kz.nu.daa;

public class Metrics {
    private long comparisons;
    private int maxDepth;
    private int currentDepth;
    private long timeMs;

    public Metrics() {
        reset();
    }

    public void reset() {
        this.comparisons = 0;
        this.maxDepth = 0;
        this.currentDepth = 0;
        this.timeMs = 0;
    }

    public void incComparisons() {
        this.comparisons++;
    }

    public void enterRecursion() {
        this.currentDepth++;
        if (this.currentDepth > this.maxDepth) {
            this.maxDepth = this.currentDepth;
        }
    }

    public void exitRecursion() {
        if (this.currentDepth > 0) {
            this.currentDepth--;
        }
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getTimeMs() {
        return timeMs;
    }

    public void setTimeMs(long timeMs) {
        this.timeMs = timeMs;
    }
}