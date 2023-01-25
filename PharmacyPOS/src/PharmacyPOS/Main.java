package PharmacyPOS;

import java.util.*;

public class Main {
    //scanner
    static Scanner scan = new Scanner(System.in);
    //an instance of object Cashier
    static Cashier cashier = new Cashier();

    static ArrayList<Items> Products = new ArrayList<>(Arrays.asList(new Items(1, 8.75, "Tylenol Paracetamol 650mg 1 Extended Release Caplet", "Medicine"),
            new Items(2, 129.75, "Paracetamol 250 mg/5 mL Strawberry-Flavored Oral Suspension 60 mL", "Medicine"),
            new Items(3, 7.50, "Fern C Plus+ 500mg/10mg capsule 1s", "Medicine"),
            new Items(4, 490.00, "Puritans Pride (Heart Health) Garlic Odorless 1000mg 100 Softgels", "Medicine"),
            new Items(5, 7.50, "Potencee (Kids Vitamins) +ZN (Ascorbic Acid + Zinc) 500mg/10mg 1 Capsule (sold per piece)", "Medicine"),
            new Items(6, 19.50, "Ponstan Mefenamic Acid 250mg 1 Capsule", "Medicine"),
            new Items(7, 15.00, "Cetirizine hydrochloride 10mg 1 Tablet", "Medicine"),
            new Items(8, 15.00, "Glucosamine Tablet 1500mg x 30 Tablet", "Medicine"),
            new Items(9, 83.00, "United TIKI-TIKI B Complex; Vitamins A; C; D and E Drops 30ml", "Medicine"),
            new Items(10, 316.00, "Stresstabs Multivitamins + Iron 30 Tablets", "Medicine"),
            new Items(1, 109.00, "PUREDERM Make up Remover Cleansing Tissue Argan Oil 30sheets", "Beauty"),
            new Items(2, 165.00, "ChicBobbie Baby-doll Eyes Bold Liquid Eyeliner 1.6gv", "Beauty"),
            new Items(3, 185.00, "ChicBobbie Beauty Tools- Make-up Blending Sponge", "Beauty"),
            new Items(4, 248.00, "NIVEA Body Lotion Intensive Moisture Body Milk for dry skin 250ml", "Beauty"),
            new Items(5, 157.00, "Johnsons & Johnsons Regular Lotion 200ml", "Beauty"),
            new Items(6, 229.00, "Maybelline Fit Me Flawless Natural Concealer - 10 Light", "Beauty"),
            new Items(7, 30.00, "COLLAGEN WS Moisturising and Repairing Serum 30ml", "Beauty"),
            new Items(8, 152.00, "NIVEA Lip Caring Lip Scrub with Rosehip Oil 5.5ml", "Beauty"),
            new Items(9, 69.00, "WATSONS Anti - Wrinkle Serum Mask 1 sheet", "Beauty"),
            new Items(10, 97.00, "PONDS Acne Clear Facial Foam with Scrub 50g", "Beauty")));
    static ArrayList<Items> ItemsCart = new ArrayList<>();

    static int counter = 0;
    static float userCash = 0;
    static double[] _SubTotals;

    public static void main(String[] args) {

        int code, qty, cartIndex;
        char userInput, cartInput = 'D';

        System.out.println("Welcome to Watsons!");
        System.out.print("\nPress 'A' to Medicine Section.\nPress 'B' to Beauty Section.\nPress 'C' to Cart.\nPress 'E' to Exit.\n- ");
        userInput = Character.toUpperCase(scan.next().charAt(0));
        //shopping loop, loops all the inputs unless exited.
        while (userInput != 'E') {
            switch (userInput) {
                case 'A' -> {
                    System.out.println("Medicine Section");
                    printProducts(userInput);
                    System.out.print("\nPlease select an item: ");
                    code = scan.nextInt();
                    System.out.print("Enter the quantity: ");
                    qty = scan.nextInt();
                    if (Products.get(code - 1).getType().equals("Medicine")) {
                        Products.get(code - 1).setQuantity(qty);
                        addtoCart(Products.get(code - 1));
                    }
                }
                case 'B' -> {
                    System.out.println("Beauty Section");
                    printProducts(userInput);
                    System.out.print("\nPlease select an item: ");
                    code = scan.nextInt();
                    System.out.print("Enter the quantity: ");
                    qty = scan.nextInt();
                    if (Products.get(code + 9).getType().equals("Beauty")) {
                        Products.get(code + 9).setQuantity(qty);
                        addtoCart(Products.get(code + 9));
                    }
                }
                case 'C' -> {
                    System.out.println("\nCart:");
                    displayItems();
                    counter--;
                    while (cartInput != 'E' && !ItemsCart.isEmpty()) {
                        System.out.print("\nPress 'R' to remove an item.\nPress 'E' to exit.\n- ");
                        cartInput = Character.toUpperCase(scan.next().charAt(0));

                        if (cartInput == 'R') {
                            System.out.print("\nPlease select item: ");
                            cartIndex = scan.nextInt();

                            ItemsCart.remove(cartIndex - 1);
                            counter--;
                            System.out.println("\nThe item has been removed successfully.");
                            cartInput = 'E';
                        }
                    }
                    cartInput = 'D';
                }
            }

            System.out.print("\nPress 'A' to Medicine Section.\nPress 'B' to Beauty Section.\nPress 'C' to Cart.\nPress 'E' to Exit.\n- ");
            userInput = Character.toUpperCase(scan.next().charAt(0));
            counter++;
        }
        //Check if the cart is empty
        if (!ItemsCart.isEmpty()) {
            System.out.print("\nAre you a PWD, Pregnant, Senior/Elderly? [y/n] : ");
            userInput = Character.toUpperCase(scan.next().charAt(0));
            //Checkout
            displayCashier(userInput == 'Y');
        }
    }
    //Print products to designated sections
    public static void printProducts(char userInput) {
        for (Items itemsToDisplay : Products) {
            if (itemsToDisplay.getType().equals("Medicine") && userInput == 'A')
                System.out.println(itemsToDisplay.getCode() + "...." + itemsToDisplay.getItem() + "....P" + itemsToDisplay.getPrice());
            if (itemsToDisplay.getType().equals("Beauty") && userInput == 'B')
                System.out.println(itemsToDisplay.getCode() + "...." + itemsToDisplay.getItem() + "....P" + itemsToDisplay.getPrice());
        }
    }

    //Display items in the cart
    public static void displayItems() {
        if (!ItemsCart.isEmpty()) {
            for (Items items : ItemsCart) {
                if (items != null) {
                    System.out.println(items.getItem());
                    System.out.printf("%d\t\t%,.3f\t\t\t\t%,.3f\n", items.getQuantity(), items.getPrice(), (items.getPrice() * items.getQuantity()));
                }
            }
        } else {
            System.out.println("You don't have any items.");
        }
    }
    //Add items into cart
    public static void addtoCart(Items product) {
        if (!ItemsCart.contains(product)) {
            ItemsCart.add(counter, product);
        } else {
            for (Items comparable : ItemsCart) {
                if (comparable.equals(product)) {
                    ItemsCart.set((ItemsCart.indexOf(comparable)), product);
                    counter--;
                    break;
                }
            }
        }
    }
    //Cashier
    public static void displayCashier ( boolean discount){
        System.out.println();
        displayItems();
        //Array holder for the subtotals of the items
        _SubTotals = new double[counter];
        //Variable holder for the original total
        double originalTotal;
        //A loop to multiply the quantity to the price of the items to get the subtotal of each item
        for (int x = 0; x < counter; x++) {
            _SubTotals[x] = cashier.getPerItemTotal(ItemsCart.get(x).getPrice(), ItemsCart.get(x).getQuantity());
        }
        //Adding all the subtotals to get the total of all items
        // If discount equals true total will be discounted
        cashier.setTotal(_SubTotals, discount);
        originalTotal = cashier.Total;
        //Getting the VAT and adding it to the total
        cashier.Total += cashier.getVAT(cashier.Total);
        System.out.printf("\nTotal: \t\t\t\t\t\t%,.3f\n", cashier.Total);
        //If true, display total discounted
        if (discount) {
            cashier.Total = cashier.Total - cashier.Discount;
            System.out.printf("Discounted: \t\t\t\t%,.3f\n", cashier.Total);
        } else
            System.out.printf("Discounted: \t\t\t\t%,.3f\n", 0.000);
        //Ask the user to pay
        System.out.print("Cash:\t\t\t\t\t\t");
        userCash = scan.nextFloat();
        System.out.println(userCash + " " + (userCash < cashier.Total));
        //Check if the user entered a valid amount
        while (userCash < cashier.Total) {
            System.out.println("Enter a valid cash amount!");
            System.out.print("Cash:\t\t\t\t\t\t");
            userCash = scan.nextFloat();
        }
        //Display the users change
        System.out.printf("Change: \t\t\t\t\t%,.3f\n", (cashier.setgetChange(userCash)));
        //Display VAT
        System.out.printf("\nVatable: \t\t\t\t\t%,.3f\n", cashier.Total);
        System.out.printf("VAT: \t\t\t\t\t\t%,.3f\n", cashier.getVAT(originalTotal));
    }

}