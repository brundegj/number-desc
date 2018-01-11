/*
 * James Brundege
 * Date: 2018-01-10
 * MIT license: https://opensource.org/licenses/MIT
 */
package jmb.numdesc.api;

import jmb.numdesc.service.NumberDescriber;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.inject.Inject;

import static jmb.numdesc.api.NumberDescriberController.PATH;

/**
 * Controller for the NumberDescription API. Accepts GET requests to http://server:port/numdesc/{number} where
 * {number} can be any valid integer. Returns a json message containing the English word description of the number.
 */
@Controller
@RequestMapping(PATH + "{id:.+}")
public class NumberDescriberController {

    static final String PATH = "/numdesc/";

    @Inject
    private NumberDescriber numberDescriber;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public NumberDescription describe(@PathVariable("id") int number) {
        return new NumberDescription(number, numberDescriber.describe(number));
    }
}
