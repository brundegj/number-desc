/*
 * James Brundege
 * Date: 2018-01-10
 * MIT license: https://opensource.org/licenses/MIT
 */
package jmb.numdesc.api;

import lombok.Data;
import lombok.AllArgsConstructor;

/**
 *
 */
@Data
@AllArgsConstructor
public class NumberDescription {

    private int number;
    private String description;

}
