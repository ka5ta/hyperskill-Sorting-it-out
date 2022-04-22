package sorting;

public enum SortingType {
    NATURAL, BY_COUNT, ERROR;



    public static SortingType getSortingType(String type){
        if(type.equals("byCount")){
            return SortingType.BY_COUNT;
        } else if(type.equals("natural")){
            return SortingType.NATURAL;
        }
            throw new RuntimeException();
    }
}
