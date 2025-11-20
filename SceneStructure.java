class SceneStructure
{
    public Integer right_limit;
    public Integer left_limit;
    public Integer top_limit;
    public Integer bottom_limit;

    public Integer id;
    public Boolean is_vine;

    public SceneStructure(Integer right_limit, Integer left_limit, Integer top_limit, Integer bottom_limit,
                          Integer id, Boolean is_vine) {
        this.right_limit = right_limit;
        this.left_limit = left_limit;
        this.top_limit = top_limit;
        this.bottom_limit = bottom_limit;
        this.id = id;
        this.is_vine = is_vine;
    }
}