
public class Item {

    private String itemName;

    public Item(String itemName){
        this.itemName = itemName;

    }



    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString(){
        return " " + itemName;
    }

}


