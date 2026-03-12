import java.util.*;

class Meal{

    String name;
    String type;
    int calories;

    Meal next;

    Meal(String name,String type,int calories){
        this.name=name;
        this.type=type;
        this.calories=calories;
    }

}

public class DietPlannerBackend{

    // CO2 Linked List
    Meal head=null;

    // CO5 ArrayList
    ArrayList<Integer> calorieList=new ArrayList<>();

    // CO4 HashMap
    HashMap<String,Integer> mealMap=new HashMap<>();

    // CO3 Stack
    Stack<Meal> undoStack=new Stack<>();

    void addMeal(String name,String type,int cal){

        Meal newMeal=new Meal(name,type,cal);

        if(head==null)
            head=newMeal;
        else{

            Meal temp=head;

            while(temp.next!=null)
                temp=temp.next;

            temp.next=newMeal;
        }

        calorieList.add(cal);

        mealMap.put(name,cal);

        undoStack.push(newMeal);

        System.out.println("Meal Added");
    }

    void displayMeals(){

        Meal temp=head;

        while(temp!=null){

            System.out.println(temp.name+" "+temp.type+" "+temp.calories);

            temp=temp.next;
        }

    }

    // CO5 ArrayList total
    int totalCalories(){

        int total=0;

        for(int c:calorieList)
            total+=c;

        return total;
    }

    // CO1 Binary Search
    int searchCalories(int key){

        Collections.sort(calorieList);

        int left=0;
        int right=calorieList.size()-1;

        while(left<=right){

            int mid=(left+right)/2;

            if(calorieList.get(mid)==key)
                return mid;

            if(calorieList.get(mid)<key)
                left=mid+1;

            else
                right=mid-1;

        }

        return -1;
    }

    // CO3 Undo
    void undoMeal(){

        if(!undoStack.isEmpty()){

            Meal m=undoStack.pop();

            System.out.println("Removed meal: "+m.name);
        }

    }

    public static void main(String args[]){

        DietPlannerBackend d=new DietPlannerBackend();

        d.addMeal("Idli","Breakfast",250);
        d.addMeal("Rice","Lunch",500);
        d.addMeal("Fruits","Snack",150);

        System.out.println("\nMeals:");

        d.displayMeals();

        System.out.println("\nTotal Calories: "+d.totalCalories());

        int result=d.searchCalories(500);

        if(result!=-1)
            System.out.println("Calories Found using Binary Search");
        else
            System.out.println("Calories Not Found");

        d.undoMeal();

    }

}