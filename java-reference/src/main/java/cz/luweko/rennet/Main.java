package cz.luweko.rennet;

import cz.luweko.rennet.ui.ConsoleUI;

/**
 * Rennet - Sýrařův rádce (Cheese-maker's Advisor)
 *
 * A Java SE console application that helps home cheese makers calculate
 * the correct amount of rennet, estimate yields, and manage recipes.
 *
 * Original version 1.0 - Java SE reference implementation.
 *
 * @author luweko
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ui.run();
    }
}
