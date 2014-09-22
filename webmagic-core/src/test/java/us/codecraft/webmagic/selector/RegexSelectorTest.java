package us.codecraft.webmagic.selector;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author code4crafter@gmail.com <br>
 */
public class RegexSelectorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testRegexWithSingleLeftBracket() {
        String regex = "\\d+(";
        new RegexSelector(regex);
    }

    @Test
    public void testRegexWithLeftBracketQuoted() {
        String regex = "\\(.+";
        String source = "(hello world";
        RegexSelector regexSelector = new RegexSelector(regex);
        String select = regexSelector.select(source);
        Assertions.assertThat(select).isEqualTo(source);
    }
}
