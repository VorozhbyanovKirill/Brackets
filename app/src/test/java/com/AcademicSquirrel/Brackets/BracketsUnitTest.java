/**
 * Date: 26.06.2016
 * Author: Kirill Vorozhbyanov
 * E-mail: VorozhbyanovKirill@gmail.com
 */

package com.AcademicSquirrel.Brackets;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Тестирование алгоритма проверки скобочных
 * алгебраических выражений по заданному словарю.
 */

public class BracketsUnitTest {
    @Test
    public void brackets_isCorrect() throws Exception {
        // Создаем словарь скобок.
        ArrayList<DictionaryBrackets> dictionaryBrackets = new ArrayList<DictionaryBrackets>();
        dictionaryBrackets.add(new DictionaryBrackets('(', ')'));
        dictionaryBrackets.add(new DictionaryBrackets('{', '}'));
        dictionaryBrackets.add(new DictionaryBrackets('[', ']'));
        dictionaryBrackets.add(new DictionaryBrackets('<', '>'));

        // Заполняем список тестов.
        ArrayList<ValidTest> tests = new ArrayList<ValidTest>();
        tests.add(new ValidTest("))((]][[", false));
        tests.add(new ValidTest("( [ { [ ] } ] )", true));
        tests.add(new ValidTest("( [ { ] } )", false));
        tests.add(new ValidTest("((( )( ))( ))", true));
        tests.add(new ValidTest(")(((()))", false));
        tests.add(new ValidTest("{}[]()(()[])", true));
        tests.add(new ValidTest("(}{)", false));
        tests.add(new ValidTest("  ([)   ] ", false));
        tests.add(new ValidTest("(..[..}", false));
        tests.add(new ValidTest("[..(..)..]..{...}..((..)..)", true));
        tests.add(new ValidTest("(222-(2*Х+5))-3*у)", false));
        tests.add(new ValidTest("()[{()}(foo)bar]", true));
        tests.add(new ValidTest("(()((()))(()))", true));
        tests.add(new ValidTest("2+(2+{4-1+[4/2]})+1", true));
        tests.add(new ValidTest("[[[]]][][[]][()]{}[]", true));
        tests.add(new ValidTest("[[[)]]][][[]][()]{}[]", false));
        tests.add(new ValidTest("23412431234", false));
        tests.add(new ValidTest("         4            ", false));
        tests.add(new ValidTest("                    ", false));
        tests.add(new ValidTest("{]", false));
        tests.add(new ValidTest("Hello, World!)", false));
        tests.add(new ValidTest("Hello,< World! >)", false));
        tests.add(new ValidTest("Hello,< World! >", true));

        Brackets brackets = new Brackets(dictionaryBrackets);
        String format = "%-4s%-15s%-30s%-30s%n";

        // Тестируем алгоритм и выводим результаты тестирования.
        for (int i = 0; i < tests.size(); i++) {
            Boolean isValid = brackets.checkBrackets(tests.get(i).text);
            System.out.format(format, (i + 1) + ".", " TEST " + (isValid == tests.get(i).valid ? "SUCCESS" : "ERROR"), tests.get(i).text, "\t VALID - " + isValid.toString().toUpperCase());
        }
    }
}