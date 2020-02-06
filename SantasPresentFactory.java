import java.util.*;

public class SantasPresentFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] materialArr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        ArrayDeque<Integer>materialsStack = createStack(materialArr);
        int[] magicArr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        ArrayDeque<Integer>magicsQueue = createQueue(magicArr);
        TreeMap<String,Integer>presents= new TreeMap<>();
        int dollPrice = 150;
        int trainPrice = 250;
        int bearPrice = 300;
        int bicyclePrice = 400;


        while (!materialsStack.isEmpty() && !magicsQueue.isEmpty()){
            int currentMaterial = materialsStack.peek();
            int currentMagic = magicsQueue.getFirst();

            if(currentMagic==0 || currentMaterial==0){
                if (currentMagic==0){
                    magicsQueue.poll();
                }
                if (currentMaterial==0){
                    materialsStack.pop();
                }
                continue;
            }
            int multyplycation =currentMagic*currentMaterial;
            String typePresent ="";
            boolean presentCreated = false;
            switch (multyplycation){
                case 150:
                    typePresent = "Doll";
                   createPresent(presents,typePresent);
                   presentCreated=true;
                    break;
                case 250:
                    typePresent = "Wooden train";
                   createPresent(presents,typePresent);
                    presentCreated=true;
                    break;
                case 300:
                    typePresent = "Teddy bear";
                    createPresent(presents,typePresent);
                    presentCreated=true;
                    break;
                case 400:
                    typePresent = "Bicycle";
                    createPresent(presents,typePresent);
                    presentCreated=true;
                    break;
                default:break;
            }
            if(presentCreated){
                materialsStack.pop();
                magicsQueue.poll();
                continue;
            }
            if(multyplycation<0){
                materialsStack.pop();
                int sum = currentMagic+currentMaterial;
                materialsStack.push(sum);
                magicsQueue.poll();
            } else if (0<multyplycation){
                magicsQueue.poll();
                int currentMat = materialsStack.pop()+15;
                materialsStack.push(currentMat);
            }
        }
        printingResult(presents);
        if(!magicsQueue.isEmpty()){
            System.out.printf("Magic left: ");
            printQueue(magicsQueue);
        } else if (!materialsStack.isEmpty()) {
            System.out.printf("Materials left: ");
            printStack(materialsStack);
        }
        printPresents(presents);
    }

    private static void printPresents(TreeMap<String, Integer> presents) {
        for (Map.Entry<String, Integer> entry : presents.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(),entry.getValue());
        }
    }

    private static void printStack(ArrayDeque<Integer> materialsStack) {
        while (1<materialsStack.size()){
                System.out.print(materialsStack.pop()+", ");
            }
        System.out.print(materialsStack.pop());
        System.out.println();
    }

    private static void printQueue(ArrayDeque<Integer> magicsQueue) {
        while (1<magicsQueue.size()){
                System.out.print(magicsQueue.poll()+", ");
        }
        System.out.print(magicsQueue.poll());
        System.out.println();
    }

    private static ArrayDeque<Integer> createQueue(int[] magicArr) {
        ArrayDeque<Integer>queue = new ArrayDeque<>();
        for (int i : magicArr) {
            queue.add(i);
        }
        return queue;
    }

    private static ArrayDeque<Integer> createStack(int[] materialArr) {
        ArrayDeque<Integer>stack = new ArrayDeque<>();
        for (int i : materialArr) {
            stack.push(i);
        }
        return stack;
    }
    public static TreeMap<String,Integer> createPresent(TreeMap<String,Integer> map,String typePresent){
        if(map.containsKey(typePresent)){
            int coutnt = map.get(typePresent) + 1;
            map.put(typePresent,coutnt);
        } else {
            map.putIfAbsent(typePresent,1);
        }
        return map;
    }

    public static void printingResult(TreeMap<String,Integer> map){
        if(map.containsKey("Doll")&&map.containsKey("Wooden train")){
            System.out.printf("The presents are crafted! Merry Christmas!%n");
        } else if (map.containsKey("Teddy bear") && map.containsKey("Bicycle")){
            System.out.printf("The presents are crafted! Merry Christmas!%n");
        } else {
            System.out.printf("No presents this Christmas!%n");
        }
    }
}
