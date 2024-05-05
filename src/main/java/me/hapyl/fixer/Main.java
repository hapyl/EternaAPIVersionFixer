package me.hapyl.fixer;

public class Main {

    private static Fixer fixer;

    public static void main(String[] args) {
        fixer = new NMSEntityType();

        if (fixer == null) {
            System.out.println("** Nothing to fix! **");
            return;
        }

        System.out.println(STR."** Fixing \{fixer.getClass().getSimpleName()}... **");
        fixer.fix();
    }

}
