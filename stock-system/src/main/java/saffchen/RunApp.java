package saffchen;

import saffchen.core.CoreStockSystem;

public class RunApp {
    public static void main(String[] args) {
        CoreStockSystem coreStockSystem = new CoreStockSystem();
        coreStockSystem.onEnteringCommand();
    }
}