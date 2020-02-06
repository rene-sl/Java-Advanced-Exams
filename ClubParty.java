import java.util.*;

public class ClubParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int capacity = Integer.parseInt(scanner.nextLine());
        String [] data = scanner.nextLine().split("\\s+");
        ArrayDeque<String> halls = new ArrayDeque<>();
        halls = createStack(data);
        ArrayDeque<Integer> companies = new ArrayDeque<>();
        companies = createStackInteger(data);

        ArrayDeque<Integer>currentCompanies = new ArrayDeque<>();
        while(!halls.isEmpty() || !companies.isEmpty()){
            String currentHall = halls.pop();
            int currentHallCapacity = capacity;

            while(0<currentHallCapacity && !companies.isEmpty()){
                int currentCompany = companies.peek();
                if(currentCompany<=currentHallCapacity){
                    currentCompanies.add(currentCompany);
                    currentHallCapacity-=currentCompany;
                    companies.pop();
                    if(companies.isEmpty()){
                        return;
                    }
                } else {
                    break;
                }

            }
            System.out.printf("%s -> ",currentHall);
            while(1<currentCompanies.size()){
                System.out.print(currentCompanies.poll() + ", ");
            }
            System.out.print(currentCompanies.poll());
            System.out.println();
        }





    }
    public static ArrayDeque<String> createStack(String[]array){
        ArrayDeque<String>arrayDeque = new ArrayDeque<>();
        for (String i : array) {
           if(Character.isLetter(i.charAt(0))){
               arrayDeque.push(i);
           }
        }
        return arrayDeque;
    }
    public static ArrayDeque<Integer> createStackInteger(String[]array){
        ArrayDeque<Integer>arrayDeque = new ArrayDeque<>();
        for (String i : array) {
            if(Character.isDigit(i.charAt(0))){
                arrayDeque.push(Integer.parseInt(i));
            }
        }
        return arrayDeque;
    }
}
