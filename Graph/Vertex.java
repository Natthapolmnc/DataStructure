class Vertex {
    private String labels;
    private boolean isVisit;

    Vertex(String labels) {
        this.labels = labels;
        this.isVisit = false;
    }

    /**
     * @return the labels
     */
    public String getLabels() {
        return labels;
    }/**
     * @param labels the labels to set
     */
    public void setLabels(String labels) {
        this.labels = labels;
    }/**
     * @param isVisit the isVisit to set
     */
    public void setVisit(boolean isVisit) {
        this.isVisit = isVisit;
    }
    public boolean getVisit() {
        return isVisit;
    }
    

}