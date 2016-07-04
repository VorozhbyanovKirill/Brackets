/**
 * Date: 26.06.2016
 * Author: Kirill Vorozhbyanov
 * E-mail: VorozhbyanovKirill@gmail.com
 */

package com.AcademicSquirrel.Brackets;

/**
 * Данный класс содержит текст и флаг правильности записи такового
 * с синтаксической точки зрения математики.
 */

public class ValidTest {

    public String text;
    public Boolean valid;

    public ValidTest(String text, Boolean isValidated) {
        this.text = text;
        this.valid = isValidated;
    }
}
