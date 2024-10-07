package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayBuilderTest {
    private Vocabulary[] vocab;

    /*
     * tests the findIndex method in the ArrayBuilder class
     */
    @org.junit.jupiter.api.Test
    void getIndex() {
        assertEquals(ArrayBuilder.findIndexOf("cat"), 3);
    }

    /*
     * tests the printAll method in the ArrayBuilder class
     */
    @org.junit.jupiter.api.Test
    void print() {
        assertEquals(ArrayBuilder.printAll(), "どうぶつ、動物  animal チーター cheetah いぬ、犬  dog ねこ、猫 cat うし、牛 cow ぶた、豚  pig うま、馬  horse ひつじ、羊 sheep さる、猿 monkey ねずみ、鼠 mouse とら、虎 tiger オオカミ、狼  wolf ");
    }
}