public enum JuniorSizes
{
    GROUNDED(14),
    HANGING(14),
    REACHING(23);

    public final Integer value;

    JuniorSizes(Integer value) {
        this.value = value;
    }
}
// Example usage.
// int v = JuniorSizes.GROUNDED.value;
// System.out.println(v); // 14

