package dev.loganalyzer.models.datasource;

public class ProtonDbDetails {
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    private String appId;
    private String bestReportedTier;
    private String confidence;
    private double score;
    private String tier;
    private int total;
    private String trendingTier;

    // Getters & setters
    public String getBestReportedTier() {
        return bestReportedTier;
    }

    public void setBestReportedTier(String bestReportedTier) {
        this.bestReportedTier = bestReportedTier;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTrendingTier() {
        return trendingTier;
    }

    public void setTrendingTier(String trendingTier) {
        this.trendingTier = trendingTier;
    }

    @Override
    public String toString() {
        return "ProtonSummary{" +
                "bestReportedTier='" + bestReportedTier + '\'' +
                ", confidence='" + confidence + '\'' +
                ", score=" + score +
                ", tier='" + tier + '\'' +
                ", total=" + total +
                ", trendingTier='" + trendingTier + '\'' +
                '}';
    }
}
