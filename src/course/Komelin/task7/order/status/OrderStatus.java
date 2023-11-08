package course.Komelin.task7.order.status;

public enum OrderStatus {
    CREATED(1),
    COLLECTED(2),
    EXPIRED(3),
    CLOSED(3);

    //
    private final int orderEvolution;
    OrderStatus(int orderEvolution) {
        this.orderEvolution = orderEvolution;
    }

    /**
     *
     * @return index of order evolution. <a href="https://imgur.com/a/jcEdARP">See the schema of order evolution</a>
     */
    public int getOrderEvolution() {
        return this.orderEvolution;
    }
}
