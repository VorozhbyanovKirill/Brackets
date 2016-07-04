/**
 * Author: Kirill Vorozhbyanov
 * E-mail: VorozhbyanovKirill@gmail.com
 */

package com.AcademicSquirrel.Brackets;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Данный класс позволяет произвести анализ скобочных
 * алгебраических выражений по заданному словарю.
 */

public class Brackets {
    // Словарь скобок
    private ArrayList<DictionaryBrackets> dictionaryBrackets;

    public Brackets(ArrayList<DictionaryBrackets> dictionaryBrackets) {
        this.dictionaryBrackets = dictionaryBrackets;
    }

    // Алгоритм синтаксического анализа скобочных алгеброических выражений.
    public Boolean checkBrackets(String s) {
        // Флаг о том, что стек вообще редактировался.
        Boolean stackWasEditing = false;
        s = s.trim();
        if (s.length() > 0) {
            Stack stack = new Stack();
            for (int i = 0; i < s.length(); i++) {
                if (isBracket(s.charAt(i), Symbol.OPEN)) {
                    stackWasEditing = true;
                    stack.push(s.charAt(i));
                } else {
                    if (isBracket(s.charAt(i), Symbol.CLOSE)) {
                        stackWasEditing = true;
                        char openBracket = getOpenBracketPair(s.charAt(i));
                        if (stack.size() > 0 && (char) stack.peek() == openBracket)
                            stack.pop();
                        else
                            return false;
                    }
                }
            }
            return stack.empty() && stackWasEditing;
        } else
            return false;
    }

    // Проверка на совпадение скобки/символа со словарем.
    private Boolean isBracket(char symbol, Symbol state) {
        for (DictionaryBrackets elem : dictionaryBrackets) {
            switch (state) {
                case OPEN:
                    if (elem.openSymbol == symbol)
                        return true;
                    break;
                case CLOSE:
                    if (elem.closeSymbol == symbol)
                        return true;
                    break;
            }
        }
        return false;
    }

    // Возврат открывающейся пары скобки или пустого символа
    private char getOpenBracketPair(char bracket) {
        for (DictionaryBrackets elem : dictionaryBrackets)
            if (bracket == elem.closeSymbol)
                return elem.openSymbol;
        return '\0';
    }

    // Перечисление возможных состояний символов.
    private enum Symbol {
        OPEN, CLOSE
    }
}
