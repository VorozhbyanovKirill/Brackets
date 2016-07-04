/**
 * Author: Kirill Vorozhbyanov
 * E-mail: VorozhbyanovKirill@gmail.com
 */

package com.AcademicSquirrel.Brackets;

/**
 * Данный класс содержит данные словаря символов.
 */

public class DictionaryBrackets {
    // Открывающий символ.
    public char openSymbol;
    // Закрывающий символ.
    public char closeSymbol;

    public DictionaryBrackets(char openSymbol, char closeSymbol) {
        this.openSymbol = openSymbol;
        this.closeSymbol = closeSymbol;
    }
}
